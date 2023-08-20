package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.config.SdkThriftServerConfig;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkThriftIface;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

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
}
