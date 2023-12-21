package com.github.alphafoxz.oneboot.sdk.configuration;

import com.github.alphafoxz.oneboot.app.toolkit.ThriftProcessorUtil;
import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.standard.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ClassUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SpringUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ThreadUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.net.InetSocketAddress;

@Slf4j
@Configuration
public class SdkThriftServerConfiguration implements ApplicationListener<ContextClosedEvent> {
    private static TServer server;
    @Resource
    private SdkProperties sdkProperties;
    @Resource
    private CommonConfiguration commonConfiguration;

    public static volatile Integer serverPort = 0;

    @Autowired
    public void startServer(SdkProperties sdkProperties) {
        ThreadUtil.execAsync(() -> {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
                Object bean = SpringUtil.getBean(aClass);
                if (bean instanceof OnebootModuleConfig config) {
                    ThriftProcessorUtil.getProcessorByPackage(config.getPackage() + ".service").forEach(processor::registerProcessor);
                }
            }
            switch (sdkProperties.getThrift().getTServer()) {
                case T_THREADED_SELECTOR_SERVER -> {
                    TThreadedSelectorServer.Args args1 = new TThreadedSelectorServer.Args(nonblockingServerTransport());
                    args1.processor(processor);
                    server = new TThreadedSelectorServer(args1);
                }
                case T_SIMPLE_SERVER -> {
                    TThreadPoolServer.Args args3 = new TThreadPoolServer.Args(serverTransport());
                    args3.processor(processor);
                    args3.maxWorkerThreads(10);
                    args3.minWorkerThreads(2);
                    server = new TSimpleServer(args3);
                }
                default -> {
                    TThreadPoolServer.Args args4 = new TThreadPoolServer.Args(serverTransport());
                    args4.processor(processor);
                    args4.maxWorkerThreads(10);
                    args4.minWorkerThreads(2);
                    server = new TThreadPoolServer(args4);
                }

            }
            log.info("Starting the thrift server...");
            server.serve();
        }, true);
    }

    private TNonblockingServerTransport nonblockingServerTransport() {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(sdkProperties.getThrift().getPort());
            serverPort = socket.getPort();
            return socket;
        } catch (Exception e) {
            log.error("初始化异常", e);
            return null;
        }
    }

    private TServerTransport serverTransport() {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(sdkProperties.getThrift().getPort());
            serverPort = inetSocketAddress.getPort();
            return new TServerSocket(inetSocketAddress);
        } catch (Exception e) {
            log.error("初始化异常", e);
            return null;
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (server != null) {
            server.stop();
        }
    }
}
