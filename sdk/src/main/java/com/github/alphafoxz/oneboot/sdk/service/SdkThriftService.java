package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.config.SdkThriftServerConfig;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.*;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkThriftIface;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SdkThriftService implements SdkThriftIface.Iface {
    @Resource
    private Snowflake snowflake;
    private File executableFile;

    @Override
    public SdkLongResponseDto getServerPort() {
        SdkLongResponseDto result = new SdkLongResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        result.setData(SdkThriftServerConfig.serverPort);
        return result;
    }

    @Override
    public SdkStringResponseDto getExecutableFilePath() {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        String binDir = SdkConstants.PROJECT_ROOT_PATH + File.separator + ".sdk" + File.separator + "bin";
        try {
            FileUtil.mkdir(binDir);
        } catch (Exception e) {
            result.setMessage("获取可执行文件目录异常，请检查：" + binDir);
            result.setSuccess(false);
            return result;
        }
        for (File file : FileUtil.loopFiles(binDir)) {
            if (StrUtil.containsIgnoreCase(file.getName(), "thrift")) {
                executableFile = file;
            }
        }
        if (executableFile == null) {
            result.setMessage(binDir + "目录中缺少thrift可执行文件");
            result.setSuccess(false);
            return result;
        }
        result.setData(executableFile.getAbsolutePath());
        return result;
    }

    @Override
    public SdkThriftTemplateResponseDto getTemplateContentByPath(SdkStringRequestDto pathDto) {
        SdkThriftTemplateResponseDto result = new SdkThriftTemplateResponseDto(snowflake.nextId(), pathDto.getTaskId(), false);
        try {
            File file = FileUtil.file(pathDto.getData());
            String content = FileUtil.readUtf8String(file);
            SdkThriftTemplateDto template = new SdkThriftTemplateDto();
            template.setContent(content);
            template.setFilePath(file.getAbsolutePath());
            template.setFileSeparator(File.separator);
            template.setNamespace(MapUtil.newHashMap());
            template.setIncludes(MapUtil.newHashMap());
            result.setData(template);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("{} 读取文件异常", pathDto.getData());
            result.setMessage(pathDto.getData() + "读取文件异常");
        }
        return result;
    }

    @Override
    public SdkThriftTemplateResponseDto getTemplateContentByIncludePath(SdkStringRequestDto templatePathDto, String includePath) throws TException {
        SdkThriftTemplateResponseDto result = new SdkThriftTemplateResponseDto(snowflake.nextId(), snowflake.nextId(), false);
        try {
            File templateFile = FileUtil.file(templatePathDto.getData());
            String targetPath = templateFile.getPath();
            targetPath = targetPath.substring(0, targetPath.lastIndexOf(File.separator) + 1);
            targetPath += includePath;
            File targetFile = FileUtil.file(targetPath);
            SdkThriftTemplateDto dto = new SdkThriftTemplateDto();
            dto.setContent(FileUtil.readString(targetFile, StandardCharsets.UTF_8));
            dto.setFilePath(targetFile.getAbsolutePath());
            dto.setFileSeparator(File.separator);
            dto.setNamespace(MapUtil.newHashMap());
            dto.setIncludes(MapUtil.newHashMap());
            result.setData(dto);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("{} 读取文件异常", includePath);
            result.setMessage(includePath + " 读取文件异常");
        }
        return result;
    }
}
