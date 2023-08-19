package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ExceptionUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.structs.SdkListResponseStruct;
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
        String projectRootPath = SdkConstants.PROJECT_ROOT_PATH;
        final String dataPath = projectRootPath + SdkConstants.THRIFT_DATA_PATH;
        final String restfulPath = projectRootPath + SdkConstants.THRIFT_RESTFUL_PATH;
        SdkListResponseStruct result = new SdkListResponseStruct(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        File executableFile = null;
        String binDir = projectRootPath + "/.sdk/bin";
        //生成目录： /.sdk/thrift/data 和 /.sdk/thrift/restful
        try {
            //跨语言rpc
            FileUtil.mkParentDirs(binDir);
            FileUtil.mkParentDirs(dataPath + "/common/ifaces");
            FileUtil.mkParentDirs(dataPath + "/common/structs");
            FileUtil.mkParentDirs(dataPath + "/common/enums");
            FileUtil.mkParentDirs(dataPath + "/sdk/ifaces");
            FileUtil.mkParentDirs(dataPath + "/sdk/structs");
            FileUtil.mkParentDirs(dataPath + "/sdk/enums");
            FileUtil.mkParentDirs(dataPath + "/system/ifaces");
            FileUtil.mkParentDirs(dataPath + "/system/structs");
            FileUtil.mkParentDirs(dataPath + "/system/enums");
            FileUtil.mkParentDirs(dataPath + "/api/ifaces");
            FileUtil.mkParentDirs(dataPath + "/api/structs");
            FileUtil.mkParentDirs(dataPath + "/api/enums");
            //restful接口
            FileUtil.mkParentDirs(restfulPath + "/sdk/entities");
            FileUtil.mkParentDirs(restfulPath + "/sdk/enums");
            FileUtil.mkParentDirs(restfulPath + "/sdk/repos");
            FileUtil.mkParentDirs(restfulPath + "/common/entities");
            FileUtil.mkParentDirs(restfulPath + "/common/enums");
            FileUtil.mkParentDirs(restfulPath + "/common/repos");
            FileUtil.mkParentDirs(restfulPath + "/system/entities");
            FileUtil.mkParentDirs(restfulPath + "/system/enums");
            FileUtil.mkParentDirs(restfulPath + "/system/repos");
            FileUtil.mkParentDirs(restfulPath + "/api/entities");
            FileUtil.mkParentDirs(restfulPath + "/api/enums");
            FileUtil.mkParentDirs(restfulPath + "/api/repos");
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
