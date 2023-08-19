package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkThriftIface;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.structs.SdkListResponseStruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;

// 注意 @javax.annotation.processing.Generated 和 @javax.annotation.processing.Generated的转换
// 执行命令为 thrift-0.18.1.exe -out F:\idea_projects\oneboot\sdk\src\main\java --gen java .\CommandIface.thrift
@Slf4j
@Service
public class SdkThriftService implements SdkThriftIface.Iface {
    @Resource
    private SdkInfoService sdkInfoService;
    @Resource
    private Snowflake snowflake;

    @Override
    public SdkListResponseStruct generateAll() {
        SdkListResponseStruct result = new SdkListResponseStruct(snowflake.nextId(), snowflake.nextId(), false);
        SdkListResponseStruct checkThrift = sdkInfoService.checkThriftErr();
        if (!checkThrift.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkThrift);
            result.setMessage("检查thrift时发现错误");
            result.setData(checkThrift.getData());
            return result;
        }
        String rootPath = SdkConstants.PROJECT_ROOT_PATH;
        for (File file : FileUtil.loopFiles(rootPath + "/.sdk/thrift/data")) {
            String namespace = readJavaNamespace(file);
            if (namespace == null) {
                log.warn("{} 文件未检测到namespace，跳过", file.getAbsolutePath());
                continue;
            }
            StringJoiner outPath = new StringJoiner(File.separator);
            outPath.add(rootPath);
            if (namespace.startsWith(SdkConstants.BASE_PACKAGE + "." + SdkConstants.SDK_MODULE_NAME + ".")) {
                outPath.add(SdkConstants.SDK_MODULE_NAME).add("src").add("main").add("java");
            } else {
                //TODO 其他包是否强制路径？考虑提供check方法检查
                continue;
            }
            StringJoiner command = new StringJoiner(" ");
            command.add(sdkInfoService.getThriftExecutablePath());
            command.add("--out").add(outPath.toString());
            command.add("--gen").add("java");
            command.add(file.getAbsolutePath());
            Process exec;
            int code;
            try {
                exec = Runtime.getRuntime().exec(command.toString());
                code = exec.waitFor();
            } catch (Exception e) {
                log.error("执行thrift指令异常", e);
                result.setMessage("执行thrift指令异常");
                return result;
            }
            if (code != 0) {
                log.error("{} 文件生成失败，错误代码{}", file.getAbsolutePath(), code);
                result.setMessage(file.getAbsolutePath() + " 文件生成失败，错误代码" + code);
                return result;
            }
            outPath.add(File.separator).add(StrUtil.replace(namespace, ".", File.separator));
            fixFile(outPath.toString());
        }
        result.setSuccess(true);
        return result;
    }

    private String readJavaNamespace(File thriftFile) {
        if (!StrUtil.endWithIgnoreCase(thriftFile.getName(), "thrift")) {
            log.error("{} 拓展名错误！不是一个有效的thrift文件", thriftFile.getAbsolutePath());
            return null;
        }
        List<String> content = CollUtil.newArrayList();
        FileUtil.readLines(thriftFile, StandardCharsets.UTF_8, content);
        for (String lineStr : content) {
            lineStr = lineStr.trim();
            if (lineStr.startsWith("namespace") && ReUtil.isMatch("^namespace[\\s]+java[\\s]+[^\\s]+$", lineStr)) {
                return lineStr.split("\\s")[2];
            }
        }
        log.error("thrift文件缺少namespace");
        return null;
    }

    private void fixFile(String path) {
        for (File file : FileUtil.loopFiles(path)) {
            String s = FileUtil.readUtf8String(file);
            FileUtil.writeUtf8String(StrUtil.replace(s, "@javax.annotation.Generated", "@javax.annotation.processing.Generated"), file);
        }
    }
}
