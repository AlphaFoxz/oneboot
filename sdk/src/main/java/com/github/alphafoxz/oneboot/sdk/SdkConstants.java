package com.github.alphafoxz.oneboot.sdk;

import cn.hutool.system.OsInfo;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SystemUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public final class SdkConstants {
    private static final String FILE_SEPARATOR = File.separator;
    public static final String PROJECT_ROOT_PATH;
    public static final OsTypeEnum OS_TYPE;
    public static final String THRIFT_TEMPLATE_PATH = FILE_SEPARATOR + ".sdk" + FILE_SEPARATOR + "gen" + FILE_SEPARATOR + "thrift";
    public static final String RESTFUL_TEMPLATE_PATH = FILE_SEPARATOR + ".sdk" + FILE_SEPARATOR + "gen" + FILE_SEPARATOR + "restful";

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