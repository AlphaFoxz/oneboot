package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.CommonConstants;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringRequestDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkGenCodeIface;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftAstUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

// 注意 @javax.annotation.processing.Generated 和 @javax.annotation.processing.Generated的转换
// 执行命令为 thrift-0.18.1.exe -out F:\idea_projects\oneboot\sdk\src\main\java --gen java .\CommandIface.thrift
@Slf4j
@Service
public class SdkGenCodeService implements SdkGenCodeIface.Iface {
    @Resource
    private Snowflake snowflake;
    @Resource
    private SdkInfoService sdkInfoService;
    @Resource
    private SdkThriftService sdkThriftService;

    public SdkListResponseDto generateTypescript(SdkStringRequestDto jsonDto) {
        // TODO
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), jsonDto.getTaskId(), false);
        return result;
    }

    @Override
    public SdkStringResponseDto getTemplateContentByPath(SdkStringRequestDto pathDto) {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), pathDto.getTaskId(), false);
        try {
            result.setData(FileUtil.readUtf8String(pathDto.getData()));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("{}文件不存在", pathDto.getData());
            result.setMessage(pathDto.getData() + "文件不存在");
        }
        return result;
    }

    @Override
    public SdkListResponseDto generateJavaApi(SdkStringRequestDto jsonDto, Map<String, SdkStringRequestDto> importJsonDto) {
        // FIXME
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), jsonDto.getTaskId(), false);
        // 检查基本SDK环境
        SdkListResponseDto checkInfo = sdkInfoService.checkThriftErr();
        if (!checkInfo.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkInfo.getMessage());
            result.setMessage("检查thrift时发现错误");
            return result;
        }
        // 读取抽象语法树
        Map<String, Object> astMap;
        JacksonJsonParser parser = new JacksonJsonParser();
        try {
            astMap = parser.parseMap(jsonDto.getData());
            if (astMap == null || astMap.isEmpty()) {
                throw new RuntimeException("ast解析结果为空");
            }
        } catch (Exception e) {
            result.setMessage("解析json异常，请检查传参\n" + e.getMessage());
            return result;
        }
        ParseThriftAstUtil.ThriftRootBean thriftRoot = ParseThriftAstUtil.parseRoot(astMap);
        if (!thriftRoot.getNamespaceMap().containsKey(ParseThriftAstUtil.NamespaceBean.NamespaceLangEnum.JAVA)) {
            result.setMessage("语法树中缺少namespace");
            return result;
        }
        StringJoiner pubCode = new StringJoiner("\n");
        pubCode.add("package " + thriftRoot.getNamespaceMap().get(ParseThriftAstUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        pubCode.add("""
                import io.swagger.v3.oas.annotations.Operation;
                import io.swagger.v3.oas.annotations.tags.Tag;
                import org.springframework.http.ResponseEntity;
                """);
        for (String aclass : thriftRoot.getImportClassList()) {
            pubCode.add("import " + aclass + ";");
        }
        pubCode.add("");
        for (ParseThriftAstUtil.ServiceBean serviceBean : thriftRoot.getServiceList()) {
            StringJoiner serviceCode = new StringJoiner("");
            serviceCode.add(pubCode.toString());
            {
                //解析普通注释
                List<ParseThriftAstUtil.CommentBean> commentList = serviceBean.getCommentList();
                if (CollUtil.isNotEmpty(commentList)) {
                    for (ParseThriftAstUtil.CommentBean commentBean : commentList) {
                        serviceCode.add("// " + commentBean.getCommentValue().trim() + "\n");
                    }
                }
            }
            {
                //解析API注释
                ParseThriftAstUtil.CommentBean serviceDoc = serviceBean.getDoc();
                String tagAnno = "@Tag(name = \"{}\", description = \"{}\")\n";
                if (serviceDoc == null) {
                    serviceCode.add(StrUtil.format(tagAnno, "", serviceBean.getServiceName()));
                } else {
                    String commentString = serviceDoc.getCommentValue();
                    commentString = StrUtil.replace(commentString, "\"", " ");
                    commentString = StrUtil.replace(commentString, "\\", "");
                    serviceCode.add(StrUtil.format(tagAnno, commentString.trim(), serviceBean.getServiceName()));
                }
            }
            serviceCode.add("public interface " + serviceBean.getServiceName() + "{\n");
            {
                //解析接口方法
                for (ParseThriftAstUtil.ServiceBean.ServiceFunctionBean serviceFunction : serviceBean.getServiceFunctionList()) {
                    //解析普通注释
                    List<ParseThriftAstUtil.CommentBean> commentList = serviceFunction.getCommentList();
                    if (CollUtil.isNotEmpty(commentList)) {
                        for (ParseThriftAstUtil.CommentBean commentBean : commentList) {
                            serviceCode.add("    // " + commentBean.getCommentValue().trim() + "\n");
                        }
                    }
                    ParseThriftAstUtil.CommentBean functionDoc = serviceFunction.getDoc();
                    String operationAnno = "    @Operation(summary = \"{}\")\n";
                    if (functionDoc == null) {
                        serviceCode.add(StrUtil.format(operationAnno, ""));
                    } else {
                        String commentString = functionDoc.getCommentValue();
                        commentString = StrUtil.replace(commentString, "\"", " ");
                        commentString = StrUtil.replace(commentString, "\\", "");
                        serviceCode.add(StrUtil.format(operationAnno, commentString.trim()));
                    }
                    StringJoiner paramCode = new StringJoiner(", ");
                    serviceFunction.getParamList().forEach(param -> paramCode.add(param.getParamType().javaString() + " " + param.getParamName()));
                    serviceCode.add("    public ResponseEntity<" + serviceFunction.getReturnType().javaString() + "> ")
                            .add(serviceFunction.getFunctionName() + "(" + paramCode + ");\n");
                }
            }
            serviceCode.add("}");
            //FIXME 未完成
            System.err.println(serviceCode);
        }
        //生成Api接口
        result.setSuccess(true);
        return result;
    }

    @Override
    public SdkListResponseDto generateJavaDto(SdkStringRequestDto jsonStr) throws TException {
        // TODO
        SdkListResponseDto result = new SdkListResponseDto(snowflake.nextId(), jsonStr.getTaskId(), false);
        // 检查基本SDK环境
        SdkListResponseDto checkInfo = sdkInfoService.checkThriftErr();
        if (!checkInfo.isSuccess()) {
            log.error("检查thrift时发现错误 {}", checkInfo.getMessage());
            result.setMessage("检查thrift时发现错误");
            return result;
        }
        //生成Api接口
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
            if (namespace.startsWith(CommonConstants.BASE_PACKAGE + "." + SdkConstants.SDK_MODULE_NAME + ".")) {
                outPath.add(SdkConstants.SDK_MODULE_NAME).add("src").add("main").add("java");
            } else {
                //TODO 其他包是否强制路径？考虑提供check方法检查
                continue;
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
