package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ExceptionUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.thrift.structs.SdkListResponseStruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Getter
@Service
public class SdkInfoService {
    @Resource
    private Snowflake snowflake;

    private String thriftExecutablePath;

    public SdkListResponseStruct checkThriftErr() {
        SdkListResponseStruct result = new SdkListResponseStruct(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        File executableFile = null;
        String projectRootPath = SdkConstants.PROJECT_ROOT_PATH;
        String binDir = projectRootPath + "/.sdk/bin";
        String sdkIfaceDir = projectRootPath + "/.sdk/thrift/data/sdk/ifaces";
        String sdkStructDir = projectRootPath + "/.sdk/thrift/data/sdk/structs";
        String sdkEnumsDir = projectRootPath + "/.sdk/thrift/data/sdk/enums";

        String commonIfaceDir = projectRootPath + "/.sdk/thrift/data/common/ifaces";
        String commonStructDir = projectRootPath + "/.sdk/thrift/data/common/structs";
        String commonEnumsDir = projectRootPath + "/.sdk/thrift/data/common/enums";

        String systemIfaceDir = projectRootPath + "/.sdk/thrift/data/system/ifaces";
        String systemStructDir = projectRootPath + "/.sdk/thrift/data/system/structs";
        String systemEnumsDir = projectRootPath + "/.sdk/thrift/data/system/enums";
        try {
            FileUtil.mkParentDirs(binDir);
            FileUtil.mkParentDirs(sdkIfaceDir);
            FileUtil.mkParentDirs(sdkStructDir);
            FileUtil.mkParentDirs(sdkEnumsDir);
            FileUtil.mkParentDirs(commonIfaceDir);
            FileUtil.mkParentDirs(commonStructDir);
            FileUtil.mkParentDirs(commonEnumsDir);
            FileUtil.mkParentDirs(systemIfaceDir);
            FileUtil.mkParentDirs(systemStructDir);
            FileUtil.mkParentDirs(systemEnumsDir);
        } catch (Exception e) {
            log.error("sdk初始化thrift各目录异常", e);
            errors.add("sdk初始化thrift各目录异常：" + ExceptionUtil.getSimpleMessage(e));
        }
        for (File file : FileUtil.loopFiles(binDir)) {
            if (StrUtil.containsIgnoreCase(file.getName(), "thrift")) {
                executableFile = file;
            }
        }
        if (executableFile == null) {
            errors.add(".sdk/bin目录中缺少thrift文件");
        } else {
            thriftExecutablePath = executableFile.getAbsolutePath();
        }
        if (!errors.isEmpty()) {
            result.setData(errors);
            result.setSuccess(false);
        }
        return result;
    }
}
