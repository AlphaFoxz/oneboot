package com.github.alphafoxz.oneboot.sdk.service.gen_code;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkMapResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkThriftTemplateRequestDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkGenCodeIface;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import com.github.alphafoxz.oneboot.sdk.service.gen_code.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftSyntaxTreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

// 注意 @javax.annotation.processing.Generated 和 @javax.annotation.processing.Generated的转换
// 执行命令为 thrift-0.18.1.exe -out F:\idea_projects\oneboot\sdk\src\main\java --gen java .\CommandIface.thrift
@Slf4j
@Service
public class SdkGenCodeService implements SdkGenCodeIface.Iface {
    private static final String TAB = "    ";

    @Resource
    private Snowflake snowflake;
    @Resource
    private SdkInfoService sdkInfoService;
    @Resource
    private SdkThriftService sdkThriftService;
    @Resource
    private CommonConfiguration commonConfiguration;
    @Resource
    private SdkGenCodeJava sdkGenCodeJava;
    @Resource
    private SdkGenCodeTs sdkGenCodeTs;

    @Override
    public SdkMapResponseDto previewGenerateTsApi(SdkThriftTemplateRequestDto templateDto, String genDir) throws TException {
        SdkMapResponseDto result = new SdkMapResponseDto(snowflake.nextId(), templateDto.getTaskId(), false);
        result.setData(MapUtil.newHashMap());
        // 检查基本SDK环境
        SdkListResponseDto checkInfo = sdkInfoService.checkThriftErr();
        if (!checkInfo.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkInfo.getMessage());
            result.setMessage("检查thrift时发现错误");
            return result;
        }
        ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRoot;
        ParseThriftSyntaxTreeUtil.RootBean rootBean;
        try {
            thriftRoot = ParseThriftSyntaxTreeUtil.parseThriftRoot(templateDto.getData());
            rootBean = thriftRoot.getRootBean();
        } catch (Exception e) {
            log.error("解析json异常，请检查传参", e);
            result.setMessage("解析json异常，请检查传参\n" + e.getMessage());
            return result;
        }
        if (!rootBean.getNamespaceMap().containsKey(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JS)) {
            log.error("语法树中缺少namespace");
            result.setMessage("语法树中缺少namespace");
            return result;
        }
        genDir = StrUtil.replace(genDir, "\\", "/");
        Set<CodeFile> fileSet = sdkGenCodeTs.genCodeFileSet(thriftRoot, genDir);
        for (CodeFile codeFile : fileSet) {
            result.getData().put(codeFile.getPath(), codeFile.getContent());
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public SdkListResponseDto generateJavaApi(SdkThriftTemplateRequestDto templateDto) throws TException {
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), templateDto.getTaskId(), false);
        // 检查基本SDK环境
        SdkListResponseDto checkInfo = sdkInfoService.checkThriftErr();
        if (!checkInfo.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkInfo.getMessage());
            result.setMessage("检查thrift时发现错误");
            return result;
        }
        // 映射语法树
        ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRoot;
        ParseThriftSyntaxTreeUtil.RootBean rootBean;
        try {
            thriftRoot = ParseThriftSyntaxTreeUtil.parseThriftRoot(templateDto.getData());
            rootBean = thriftRoot.getRootBean();
        } catch (Exception e) {
            log.error("解析json异常，请检查传参", e);
            result.setMessage("解析json异常，请检查传参\n" + e.getMessage());
            return result;
        }
        if (!rootBean.getNamespaceMap().containsKey(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA)) {
            log.error("语法树中缺少namespace");
            result.setMessage("语法树中缺少namespace");
            return result;
        }
        boolean b = sdkGenCodeJava.genAndWriteCodeFiles(thriftRoot, null);
        result.setSuccess(b);
        return result;
    }

    @Override
    public SdkListResponseDto generateJavaRpc(long taskId) {
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), snowflake.nextId(), false);
        // 检查基本SDK环境
        SdkListResponseDto checkInfo = sdkInfoService.checkThriftErr();
        if (!checkInfo.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkInfo.getMessage());
            result.setMessage("检查thrift时发现错误");
            return result;
        }
        // 获取可执行文件
        SdkStringResponseDto checkExecutable = sdkThriftService.getExecutableFilePath();
        if (!checkExecutable.isSuccess()) {
            result.setMessage(checkExecutable.getMessage());
            return result;
        }
        String executableFilePath = checkExecutable.getData();
        String rootPath = SdkConstants.PROJECT_ROOT_PATH;
        for (File file : FileUtil.loopFiles(rootPath + "/.sdk/thrift/data")) {
            String namespace = readJavaNamespace(file);
            if (namespace == null) {
                log.warn("{} 文件未检测到namespace，跳过", file.getAbsolutePath());
                continue;
            }
            StringJoiner outPath = new StringJoiner(File.separator);
            outPath.add(rootPath);

            for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
                Object bean = SpringUtil.getBean(aClass);
                if (bean instanceof OnebootModuleConfig config && namespace.startsWith(config.getPackage() + ".")) {
                    outPath.add(config.getModuleName()).add("src").add("main").add("java");
                    break;
                }
            }
            StringJoiner command = new StringJoiner(" ");
            command.add(executableFilePath);
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
                //FIXME errMsg读不到信息
                String errMsg = IoUtil.read(exec.errorReader());
                log.error("{} 文件生成失败，错误信息 {}", file.getAbsolutePath(), errMsg);
                result.setMessage(file.getAbsolutePath() + " 文件生成失败，错误信息 " + errMsg);
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
