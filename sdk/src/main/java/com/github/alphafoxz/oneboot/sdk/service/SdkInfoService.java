package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringRequestDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkInfoIface;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Set;

@Slf4j
@Getter
@Service
public class SdkInfoService implements SdkInfoIface.Iface {
    private static final String FILE_SEPARATOR = File.separator;

    @Resource
    private Snowflake snowflake;
    @Resource
    private CommonConfiguration commonConfiguration;
    @Resource
    private SdkThriftService sdkThriftService;

    public SdkListResponseDto checkThriftErr() {
        String projectRootPath = SdkConstants.PROJECT_ROOT_PATH;
        final String dataPath = projectRootPath + SdkConstants.THRIFT_TEMPLATE_PATH;
        final String restfulPath = projectRootPath + SdkConstants.RESTFUL_TEMPLATE_PATH;
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        //生成目录： /.sdk/thrift/data 和 /.sdk/thrift/restful
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
            try {
                Object bean = SpringUtil.getBean(aClass);
                if (bean instanceof OnebootModuleConfig config) {
                    String moduleName = config.getModuleName();
                    //跨语言rpc
                    FileUtil.mkdir(dataPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "ifaces");
                    FileUtil.mkdir(dataPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "dtos");
                    FileUtil.mkdir(dataPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "enums");
                    //restful接口
                    FileUtil.mkdir(restfulPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "apis");
                    FileUtil.mkdir(restfulPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "dtos");
                    FileUtil.mkdir(restfulPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "enums");
                }
            } catch (Exception e) {
                log.error("sdk初始化thrift各目录异常", e);
                errors.add("sdk初始化thrift各目录异常：" + ExceptionUtil.getSimpleMessage(e));
            }
        }
        SdkStringResponseDto executableFilePath = sdkThriftService.getExecutableFilePath();
        if (!executableFilePath.isSuccess()) {
            errors.add(executableFilePath.getMessage());
        }
        if (!errors.isEmpty()) {
            result.setData(errors);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public SdkListResponseDto checkRestApiImplements() throws TException {
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
            Object bean = SpringUtil.getBean(aClass);
            if (bean instanceof OnebootModuleConfig config) {
                for (Class<?> apiClass : ClassUtil.scanPackage(config.getPackage() + ".gen.restful.apis")) {
                    Set<Class<?>> implClasses = ClassUtil.scanPackageBySuper(config.getPackage(), apiClass);
                    if (CollUtil.isEmpty(implClasses)) {
                        errors.add("【restAPI接口未实现】" + apiClass.getName());
                    } else {
                        for (Class<?> implClass : implClasses) {
                            if (implClass.getAnnotation(Controller.class) == null && implClass.getAnnotation(RestController.class) == null) {
                                errors.add("【restAPI未注册为Controller的实现类】" + implClass.getName());
                            }
                        }
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            result.setSuccess(false);
            result.setData(errors);
        }
        return result;
    }

    @Override
    public SdkListResponseDto checkRpcImplements() throws TException {
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
            Object bean = SpringUtil.getBean(aClass);
            if (bean instanceof OnebootModuleConfig config) {
                for (Class<?> ifaceClass : ClassUtil.scanPackage(config.getPackage() + ".gen.thrift.ifaces")) {
                    for (Class<?> innerClass : ifaceClass.getInterfaces()) {
                        if (innerClass.getName().endsWith("$Iface")) {
                            Set<Class<?>> implClasses = ClassUtil.scanPackageBySuper(config.getPackage(), innerClass);
                            boolean isImpl = false;
                            if (CollUtil.isNotEmpty(implClasses)) {
                                for (Class<?> implClass : implClasses) {
                                    if (implClass.getName().endsWith("$Client")) {
                                        continue;
                                    }
                                    isImpl = true;
                                    if (implClass.getAnnotation(Service.class) == null && implClass.getAnnotation(Component.class) == null
                                    ) {
                                        errors.add("【RPC未注册为Service的实现类】" + implClass.getName());
                                    }
                                }
                            }
                            if (!isImpl) {
                                errors.add("【RPC接口未实现】" + ifaceClass.getName());
                            }
                        }
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            result.setSuccess(false);
            result.setData(errors);
        }
        return result;
    }

    @Override
    public SdkStringResponseDto getJavaNamespace() throws TException {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        result.setData(commonConfiguration.getBasePackage());
        return result;
    }

    @Override
    public SdkListResponseDto deleteFile(SdkStringRequestDto filePath) throws TException {
        File file = FileUtil.file(filePath.getData());
        SdkListResponseDto dto = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        if (!file.exists() || !StrUtil.startWith(file.getAbsolutePath(), SdkConstants.PROJECT_ROOT_PATH)) {
            dto.setMessage("【文件路径非法】" + file.getAbsolutePath());
            dto.setSuccess(false);
            return dto;
        }
        FileUtil.del(file);
        return dto;
    }

    @Override
    public SdkStringResponseDto createOrUpdateFile(SdkStringRequestDto filePath, String fileContent) throws TException {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        File file = FileUtil.file(filePath.getData());
        if (!file.exists() || !StrUtil.startWith(file.getAbsolutePath(), SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        } else if (file.isDirectory()) {
            result.setMessage("【不能以文件夹为目标】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        }
        FileUtil.writeUtf8String(fileContent, file);
        return result;
    }
}
