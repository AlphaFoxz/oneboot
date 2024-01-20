package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.standard.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkFileInfoDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkFileTreeResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.enums.SdkFileTypeEnum;
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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Slf4j
@Getter
@Service
public class RestfulDslInfoService implements SdkInfoIface.Iface {
    private static final String FILE_SEPARATOR = File.separator;

    @Resource
    private Snowflake snowflake;
    @Resource
    private CommonConfiguration commonConfiguration;
    @Resource
    private SdkThriftService sdkThriftService;

    public SdkListResponseDto checkThriftErr() {
        final String thriftPath = SdkConstants.SDK_GEN_THRIFT_TEMPLATE_PATH;
        final String restfulPath = SdkConstants.SDK_GEN_RESTFUL_TEMPLATE_PATH;
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        //生成目录： /.sdk/thrift/data 和 /.sdk/thrift/restful
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
            try {
                Object bean = SpringUtil.getBean(aClass);
                if (bean instanceof OnebootModuleConfig config) {
                    String moduleName = config.getModuleName();
                    //跨语言rpc
                    FileUtil.mkdir(thriftPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "ifaces");
                    FileUtil.mkdir(thriftPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "dtos");
                    FileUtil.mkdir(thriftPath + FILE_SEPARATOR + moduleName + FILE_SEPARATOR + "enums");
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
        String path = filePath.getData();
        File file = FileUtil.file(path);
        if (StrUtil.isBlank(path) || !path.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
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

    @Override
    public SdkStringResponseDto createFolder(SdkStringRequestDto folderPath) throws TException {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        String path = folderPath.data;
        if (StrUtil.isBlank(path) || !path.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + path);
            result.setSuccess(false);
            return result;
        }
        try {
            FileUtil.mkdir(path);
        } catch (Exception e) {
            result.setMessage("【文件夹创建失败】" + e.getMessage());
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    @Override
    public SdkStringResponseDto renameFile(SdkStringRequestDto filePath, String newPath) throws TException {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        File file = FileUtil.file(filePath.getData());
        if (!file.exists() || !StrUtil.startWith(file.getAbsolutePath(), SdkConstants.PROJECT_ROOT_PATH) || !newPath.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        }
        try {
            file.renameTo(FileUtil.file(newPath));
        } catch (Exception e) {
            result.setMessage("【文件重命名失败】" + e.getMessage());
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    public com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto getTemplateContentByPath(String filePath) {
        com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto result = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto()
                .setId(snowflake.nextId())
                .setTaskId(snowflake.nextId())
                .setSuccess(false);
        try {
            File file = FileUtil.file(filePath);
            String content = FileUtil.readUtf8String(file);
            com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto template = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto();
            template.setContent(content);
            template.setFilePath(file.getAbsolutePath());
            template.setFileSeparator(File.separator);
            template.setNamespace(MapUtil.newHashMap());
            template.setImports(MapUtil.newHashMap());
            result.setData(template);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("{} 读取文件异常", filePath);
            result.setMessage(filePath + "读取文件异常");
        }
        return result;
    }

    public SdkFileTreeResponseDto getRestfulTemplateFileTree() {
        SdkFileTreeResponseDto result = new SdkFileTreeResponseDto().setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        try {
            result.setData(readFileTree(FileUtil.file(SdkConstants.SDK_GEN_RESTFUL_TEMPLATE_PATH), 0));
            return result;
        } catch (Exception e) {
            log.error("{} 读取文件异常", SdkConstants.SDK_GEN_RESTFUL_TEMPLATE_PATH);
            result.setMessage(SdkConstants.SDK_GEN_RESTFUL_TEMPLATE_PATH + " 读取文件异常");
            result.setSuccess(false);
            return result;
        }
    }

    private SdkFileInfoDto readFileTree(File fileOrDir, int level) {
        SdkFileInfoDto dto = new SdkFileInfoDto();
        dto.setSeparator(File.separator);
        dto.setIsReadOnly(level <= 2);
        dto.setFilePath(fileOrDir.getAbsolutePath());
        dto.setFileName(fileOrDir.getName());
        dto.setParentDir(fileOrDir.getParentFile().getAbsolutePath());
        if (FileUtil.isFile(fileOrDir)) {
            // 文件类型
            dto.setExt(FileUtil.getSuffix(fileOrDir));
            dto.setFileType(SdkFileTypeEnum.LOCAL_FILE.getValue());
            dto.setIsEmpty(FileUtil.size(fileOrDir) == 0);
            dto.setContent(FileUtil.readUtf8String(fileOrDir));
            return dto;
        }
        // 目录类型
        dto.setFileType(SdkFileTypeEnum.LOCAL_DIR.getValue());
        dto.setChildren(CollUtil.newArrayList());
        File[] innerFiles = FileUtil.ls(fileOrDir.getAbsolutePath());
        boolean isEmpty = innerFiles == null || innerFiles.length == 0;
        dto.setIsEmpty(isEmpty);
        if (isEmpty) {
            return dto;
        }
        for (File innerFile : innerFiles) {
            dto.getChildren().add(readFileTree(innerFile, level + 1));
        }
        return dto;
    }

    public com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkListResponseDto deleteFile(String filePath) {
        File file = FileUtil.file(filePath);
        com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkListResponseDto dto = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkListResponseDto()
                .setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        if (!file.exists() || !StrUtil.startWith(file.getAbsolutePath(), SdkConstants.PROJECT_ROOT_PATH)) {
            dto.setMessage("【文件路径非法】" + file.getAbsolutePath());
            dto.setSuccess(false);
            return dto;
        }
        FileUtil.del(file);
        return dto;
    }

    public com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto renameFile(String filePath, String newPath) {
        com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto result = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto()
                .setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        File file = FileUtil.file(filePath);
        if (!file.exists() || !StrUtil.startWith(file.getAbsolutePath(), SdkConstants.PROJECT_ROOT_PATH) || !newPath.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        }
        try {
            file.renameTo(FileUtil.file(newPath));
        } catch (Exception e) {
            result.setMessage("【文件重命名失败】" + e.getMessage());
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    public com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto createFolder(String folderPath) {
        com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto result = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto()
                .setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        if (StrUtil.isBlank(folderPath) || !folderPath.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + folderPath);
            result.setSuccess(false);
            return result;
        }
        try {
            FileUtil.mkdir(folderPath);
        } catch (Exception e) {
            result.setMessage("【文件夹创建失败】" + e.getMessage());
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    public com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto createOrUpdateFile(String filePath, String content) {
        com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto result = new com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto()
                .setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        File file = FileUtil.file(filePath);
        if (StrUtil.isBlank(filePath) || !filePath.startsWith(SdkConstants.PROJECT_ROOT_PATH)) {
            result.setMessage("【文件路径非法】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        } else if (file.isDirectory()) {
            result.setMessage("【不能以文件夹为目标】" + file.getAbsolutePath());
            result.setSuccess(false);
            return result;
        }
        FileUtil.writeUtf8String(content, file);
        return result;
    }

    public SdkCodeTemplateResponseDto getTemplateContentByImportPath(String tempPath, String importPath) {
        SdkCodeTemplateResponseDto result = new SdkCodeTemplateResponseDto()
                .setId(snowflake.nextId()).setTaskId(snowflake.nextId()).setSuccess(true);
        try {
            File templateFile = FileUtil.file(tempPath);
            String targetPath = templateFile.getPath();
            targetPath = targetPath.substring(0, targetPath.lastIndexOf(File.separator) + 1);
            targetPath += importPath;
            File targetFile = FileUtil.file(targetPath);
            com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto dto = new SdkCodeTemplateDto();
            dto.setContent(FileUtil.readString(targetFile, StandardCharsets.UTF_8));
            dto.setFilePath(targetFile.getAbsolutePath());
            dto.setFileSeparator(File.separator);
            dto.setNamespace(MapUtil.newHashMap());
            dto.setImports(MapUtil.newHashMap());
            result.setData(dto);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("{} 读取文件异常", importPath);
            result.setMessage(importPath + " 读取文件异常");
        }
        return result;
    }
}
