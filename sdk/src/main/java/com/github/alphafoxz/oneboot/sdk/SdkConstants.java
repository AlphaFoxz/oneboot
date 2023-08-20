package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.system.OsInfo;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public final class SdkConstants {
    private static final String FILE_SEPARATOR = File.separator;
    public static final String SDK_MODULE_NAME = "sdk";
    public static final String PROJECT_ROOT_PATH;
    public static final OsTypeEnum OS_TYPE;
    public static final String THRIFT_DATA_PATH = FILE_SEPARATOR + ".sdk" + FILE_SEPARATOR + "thrift" + FILE_SEPARATOR + "data";
    public static final String THRIFT_RESTFUL_PATH = FILE_SEPARATOR + ".sdk" + FILE_SEPARATOR + "thrift" + FILE_SEPARATOR + "restful";
    private static Map<String, TProcessor> sdkThriftProcessors;

    static {
        // 解决Eclipse中user.dir不是根目录的问题
        String path = System.getProperty("user.dir");
        boolean isChange = false;
        while (!FileUtil.file(path, ".sdk").exists()) {
            path += FILE_SEPARATOR + "..";
            isChange = true;
        }
        if (isChange) {
            PROJECT_ROOT_PATH = FileUtil.file(path).getAbsolutePath();
        } else {
            PROJECT_ROOT_PATH = path;
        }
        OsInfo osInfo = SystemUtil.getOsInfo();
        if (osInfo.isMac()) {
            OS_TYPE = OsTypeEnum.MAC;
        } else if (osInfo.isLinux()) {
            OS_TYPE = OsTypeEnum.LINUX;
        } else {
            OS_TYPE = OsTypeEnum.WINDOWS;
        }
    }


    public static enum OsTypeEnum {
        WINDOWS,
        LINUX,
        MAC,
    }
}