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
    public static final String BASE_PACKAGE = "com.github.alphafoxz.oneboot";
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

    public synchronized static Map<String, TProcessor> getSdkThriftProcessors(String packageName) {
        if (sdkThriftProcessors != null) {
            return sdkThriftProcessors;
        }
        sdkThriftProcessors = new LinkedHashMap<>();
        Set<Class<?>> classes = ClassUtil.scanPackage(packageName);
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(Service.class)) {
                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> iface : interfaces) {
                    if (iface.getPackageName().contains("thrift")) {
                        try {
                            Class<?> pClass = Class.forName(StrUtil.replace(iface.getName(), "$Iface", "$Processor"));
                            Class<?> spClass = Class.forName(StrUtil.replace(iface.getName(), "$Iface", ""));
                            sdkThriftProcessors.put(StrUtil.lowerFirst(spClass.getSimpleName()), (TProcessor) ReflectUtil.newInstance(pClass, SpringUtil.getBean(aClass)));
                        } catch (Exception e) {
                            log.error("反射Processor异常", e);
                        }
                    }
                }
            }
        }
        return sdkThriftProcessors;
    }

    public static enum OsTypeEnum {
        WINDOWS,
        LINUX,
        MAC,
    }
}