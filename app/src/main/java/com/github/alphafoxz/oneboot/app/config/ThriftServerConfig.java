package com.github.alphafoxz.oneboot.app.config;

import com.github.alphafoxz.oneboot.app.toolkit.ThriftProcessorUtil;
import com.github.alphafoxz.oneboot.common.Iface.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.config.CommonConfig;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

@Slf4j
@Configuration
@ConditionalOnMissingBean(name = "sdkThriftServerConfig")
public class ThriftServerConfig implements ApplicationListener<ContextClosedEvent> {
    private static TServer server;
    @Resource
    private AppProperties appProperties;

    @Autowired
    public void startServer(CommonConfig commonConfig) {
        if (!this.appProperties.getThrift().getEnabled()) {
            return;
        }
        ThreadUtil.execAsync(() -> {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfig.getBasePackage(), OnebootModuleConfig.class)) {
                Object bean = SpringUtil.getBean(aClass);
                if (bean instanceof OnebootModuleConfig config) {
                    ThriftProcessorUtil.getProcessorByPackage(config.getPackage() + ".service").forEach(processor::registerProcessor);
                }
            }
            switch (this.appProperties.getThrift().getTServer()) {
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
            return new TNonblockingServerSocket(appProperties.getThrift().getPort());
        } catch (Exception e) {
            log.error("初始化异常", e);
            return null;
        }
    }

    private TServerTransport serverTransport() {
        try {
            return new TServerSocket(appProperties.getThrift().getPort());
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
