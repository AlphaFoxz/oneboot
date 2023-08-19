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
    private static final String FILE_SEPARATOR = File.separator;

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
        String binDir = projectRootPath + File.separator + ".sdk" + File.separator + "bin";
        //生成目录： /.sdk/thrift/data 和 /.sdk/thrift/restful
        try {
            FileUtil.mkdir(binDir);
            //跨语言rpc
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "structs");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "structs");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "structs");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "structs");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "enums");
            //restful接口
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "entities");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "repos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "entities");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "repos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "entities");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "system" + FILE_SEPARATOR + "repos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "entities");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "api" + FILE_SEPARATOR + "repos");
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
