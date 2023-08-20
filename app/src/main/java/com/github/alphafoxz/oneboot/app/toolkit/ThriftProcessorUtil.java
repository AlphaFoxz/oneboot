package com.github.alphafoxz.oneboot.app.toolkit;

import com.github.alphafoxz.oneboot.common.toolkit.coding.ClassUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReflectUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SpringUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ThriftProcessorUtil {
    public synchronized static Map<String, TProcessor> getProcessorByPackage(String packageName) {
        Map<String, TProcessor> result = new LinkedHashMap<>();
        Set<Class<?>> classes = ClassUtil.scanPackage(packageName);
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(Service.class)) {
                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> iface : interfaces) {
                    if (iface.getPackageName().contains("thrift")) {
                        try {
                            Class<?> pClass = Class.forName(StrUtil.replace(iface.getName(), "$Iface", "$Processor"));
                            Class<?> spClass = Class.forName(StrUtil.replace(iface.getName(), "$Iface", ""));
                            result.put(StrUtil.lowerFirst(spClass.getSimpleName()), (TProcessor) ReflectUtil.newInstance(pClass, SpringUtil.getBean(aClass)));
                        } catch (Exception e) {
                            log.error("反射Processor异常", e);
                        }
                    }
                }
            }
        }
        return result;
    }
}
