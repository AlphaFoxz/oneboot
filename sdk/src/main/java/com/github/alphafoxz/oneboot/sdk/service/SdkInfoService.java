package com.github.alphafoxz.oneboot.sdk.service;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ExceptionUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Getter
@Service
public class SdkInfoService {
    private String thriftExecutablePath;

    public List<String> checkThriftErr() {
        List<String> result = CollUtil.newArrayList();
        File executableFile = null;
        String projectRootPath = SdkConstants.PROJECT_ROOT_PATH;
        String binDir = projectRootPath + "/.sdk/bin";
        String sdkIfaceDir = projectRootPath + "/.sdk/thrift/data/sdk/iface";
        String sdkStructDir = projectRootPath + "/.sdk/thrift/data/sdk/struct";
        String sdkEnumsDir = projectRootPath + "/.sdk/thrift/data/sdk/enums";

        String commonIfaceDir = projectRootPath + "/.sdk/thrift/data/common/iface";
        String commonStructDir = projectRootPath + "/.sdk/thrift/data/common/struct";
        String commonEnumsDir = projectRootPath + "/.sdk/thrift/data/common/enums";

        String systemIfaceDir = projectRootPath + "/.sdk/thrift/data/system/iface";
        String systemStructDir = projectRootPath + "/.sdk/thrift/data/system/struct";
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
            result.add("sdk初始化thrift各目录异常：" + ExceptionUtil.getSimpleMessage(e));
        }
        for (File file : FileUtil.loopFiles(binDir)) {
            if (StrUtil.containsIgnoreCase(file.getName(), "thrift")) {
                executableFile = file;
            }
        }
        if (executableFile == null) {
            result.add(".sdk/bin目录中缺少thrift文件");
        } else {
            thriftExecutablePath = executableFile.getAbsolutePath();
        }
        return result;
    }
}
