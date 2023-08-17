package com.github.alphafoxz.oneboot.sdk;

import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;

public interface SdkConstants {
    static String getProjectRootPath() {
        String projectRootPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
        // 解决Eclipse中user.dir不是根目录的问题
        try {
            String s = projectRootPath;
            while (!FileUtil.file(s, "sdk").exists()) {
                s += "/..";
            }
            return s;
        } catch (Exception e) {
            return projectRootPath;
        }
    }
}