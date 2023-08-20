package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ExceptionUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
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

    public SdkListResponseDto checkThriftErr() {
        String projectRootPath = SdkConstants.PROJECT_ROOT_PATH;
        final String dataPath = projectRootPath + SdkConstants.THRIFT_DATA_PATH;
        final String restfulPath = projectRootPath + SdkConstants.THRIFT_RESTFUL_PATH;
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        List<String> errors = CollUtil.newArrayList();
        File executableFile = null;
        //生成目录： /.sdk/thrift/data 和 /.sdk/thrift/restful
        try {
            //跨语言rpc
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "ifaces");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(dataPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "enums");
            //restful接口
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "common" + FILE_SEPARATOR + "apis");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "preset_sys" + FILE_SEPARATOR + "apis");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "app" + FILE_SEPARATOR + "apis");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "dtos");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "enums");
            FileUtil.mkdir(restfulPath + FILE_SEPARATOR + "sdk" + FILE_SEPARATOR + "apis");
        } catch (Exception e) {
            log.error("sdk初始化thrift各目录异常", e);
            errors.add("sdk初始化thrift各目录异常：" + ExceptionUtil.getSimpleMessage(e));
        }
        if (!errors.isEmpty()) {
            result.setData(errors);
            result.setSuccess(false);
        }
        return result;
    }
}
