package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.CommonConstants;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkThriftTemplateRequestDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.ifaces.SdkGenCodeIface;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftSyntaxTreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
        createRestIfaces(thriftRoot);
        createRestEnums(thriftRoot);
        createRestDtos(thriftRoot);
        for (ParseThriftSyntaxTreeUtil.ThriftIncludeBean includeBean : thriftRoot.getIncludeBeanSet()) {
            createRestIfaces(includeBean);
            createRestDtos(includeBean);
            createRestEnums(includeBean);
        }
        result.setSuccess(true);
        return result;
    }

    private void createRestIfaces(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        StringJoiner pubCode = new StringJoiner("\n");
        pubCode.add("package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        pubCode.add("""
                import io.swagger.v3.oas.annotations.Operation;
                import io.swagger.v3.oas.annotations.tags.Tag;
                import org.springframework.http.ResponseEntity;
                """);
        for (ParseThriftSyntaxTreeUtil.ServiceBean serviceBean : rootBean.getServiceList()) {
            StringJoiner serviceCode = new StringJoiner("\n");
            serviceCode.add(pubCode.toString());
            for (String str : serviceBean.getImportTypeName()) {
                serviceCode.add("import " + str + ";");
            }
            serviceCode.add("");
            {
                //解析普通注释
                List<ParseThriftSyntaxTreeUtil.CommentBean> commentList = serviceBean.getCommentList();
                if (CollUtil.isNotEmpty(commentList)) {
                    for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : commentList) {
                        serviceCode.add("// " + commentBean.getCommentValue().trim());
                    }
                }
            }
            {
                //解析API注释
                ParseThriftSyntaxTreeUtil.CommentBean serviceDoc = serviceBean.getDoc();
                serviceCode.add(StrUtil.format("@Tag(name = \"{}\", description = \"{}\")", serviceBean.getServiceName(), commentToStringParam(serviceDoc)));
            }
            serviceCode.add("public interface " + serviceBean.getServiceName() + " {");
            {
                //解析接口方法
                for (ParseThriftSyntaxTreeUtil.ServiceBean.ServiceFunctionBean serviceFunction : serviceBean.getServiceFunctionList()) {
                    //解析普通注释
                    List<ParseThriftSyntaxTreeUtil.CommentBean> commentList = serviceFunction.getCommentList();
                    if (CollUtil.isNotEmpty(commentList)) {
                        for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : commentList) {
                            serviceCode.add("    // " + commentBean.getCommentValue().trim());
                        }
                    }
                    ParseThriftSyntaxTreeUtil.CommentBean functionDoc = serviceFunction.getDoc();
                    serviceCode.add(StrUtil.format("    @Operation(summary = \"{}\")", commentToStringParam(functionDoc)));
                    StringJoiner paramCode = new StringJoiner(",\n", "\n", "\n    ");
                    serviceFunction.getParamList().forEach(param -> paramCode.add("            @Parameter(description = \"" + commentToStringParam(param.getDoc()) + "\") "+param.getParamType().javaString() + " " + param.getParamName()));
                    String returnType = "void".equals(serviceFunction.getReturnType().javaString()) ? "void" : "ResponseEntity<" + serviceFunction.getReturnType().javaString() + ">";
                    serviceCode.add("    public " + returnType + " " + serviceFunction.getFunctionName() + "(" + (paramCode.length() > 0 ? paramCode.toString() : "") + ");\n");
                }
            }
            serviceCode.add("}");
            // 写文件
            String javaFilePath = getRestGeneratePath(thriftRoot, serviceBean.getServiceName());
            File javaFile = FileUtil.file(javaFilePath);
            FileUtil.mkParentDirs(javaFile);
            try {
                javaFile.createNewFile();
            } catch (Exception e) {
                log.error("创建文件失败：{}", javaFilePath, e);
                throw new TException(e);
            }
            FileUtil.writeUtf8String(serviceCode.toString(), javaFile);
            log.debug("生成java代码：\n路径 {}\n内容 {}", javaFilePath, serviceCode);
        }
    }

    private void createRestEnums(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        String pubCode = "package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n\n";
        pubCode += "import io.swagger.v3.oas.annotations.media.Schema;\n\n";
        for (ParseThriftSyntaxTreeUtil.EnumBean enumBean : rootBean.getEnumList()) {
            StringJoiner enumCode = new StringJoiner("\n");
            enumCode.add(pubCode);
            // 生成Enum的注释
            enumCode.add(StrUtil.format("@Schema(description = \"{}\")", commentToStringParam(enumBean.getDoc())));
            if (enumBean.getCommentList() != null) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumBean.getCommentList()) {
                    enumCode.add("    // " + commentBean.getCommentValue());
                }
            }
            enumCode.add("public enum " + enumBean.getEnumName() + " {");
            for (ParseThriftSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
                //生成枚举的注释
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
                    enumCode.add("    // " + commentBean.getCommentValue());
                }
                if (enumInstance.getDoc() != null) {
                    enumCode.add("    /**" + enumInstance.getDoc().getCommentValue() + "*/");
                }
                enumCode.add("    " + enumInstance.getInstanceName() + ",");
            }
            enumCode.add("}");
            // 写文件
            String javaFilePath = getRestGeneratePath(thriftRoot, enumBean.getEnumName());
            File javaFile = FileUtil.file(javaFilePath);
            FileUtil.mkParentDirs(javaFile);
            try {
                javaFile.createNewFile();
            } catch (Exception e) {
                log.error("创建文件失败：{}", javaFilePath, e);
                throw new TException(e);
            }
            FileUtil.writeUtf8String(enumCode.toString(), javaFile);
            log.debug("生成java代码：\n路径 {}\n内容 {}", javaFilePath, enumCode);
        }
    }

    private void createRestDtos(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        StringJoiner pubCode = new StringJoiner("\n");
        pubCode.add("package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        pubCode.add("import io.swagger.v3.oas.annotations.media.Schema;");
        pubCode.add("import lombok.Data;");
        for (ParseThriftSyntaxTreeUtil.StructBean structBean : rootBean.getStructList()) {
            StringJoiner structCode = new StringJoiner("\n");
            structCode.add(pubCode.toString());
            for (String str : structBean.getImportTypeName()) {
                structCode.add("import " + str + ";");
            }
            structCode.add("");
            {
                //解析普通注释
                List<ParseThriftSyntaxTreeUtil.CommentBean> commentList = structBean.getCommentList();
                if (CollUtil.isNotEmpty(commentList)) {
                    for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : commentList) {
                        structCode.add("// " + commentBean.getCommentValue().trim());
                    }
                }
            }
            {
                //解析注解注释
                ParseThriftSyntaxTreeUtil.CommentBean structDoc = structBean.getDoc();
                structCode.add(StrUtil.format("@Schema(description = \"{}\")", structBean.getStructName(), commentToStringParam(structDoc)));
            }
            structCode.add("@Data");
            structCode.add("public class " + structBean.getStructName() + " {");
            for (ParseThriftSyntaxTreeUtil.StructBean.StructAttributeBean attributeBean : structBean.getStructAttribute()) {
                if (CollUtil.isNotEmpty(attributeBean.getCommentList())) {
                    for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : attributeBean.getCommentList()) {
                        structCode.add("    //" + commentBean.getCommentValue().trim());
                    }
                }
                structCode.add(StrUtil.format("    @Schema(description = \"{}\")", commentToStringParam(attributeBean.getDoc())));
                structCode.add("    private " + attributeBean.getType().javaString() + " " + attributeBean.getAttributeName() + ";");
            }
            structCode.add("}");
            // 写文件
            String javaFilePath = getRestGeneratePath(thriftRoot, structBean.getStructName());
            File javaFile = FileUtil.file(javaFilePath);
            FileUtil.mkParentDirs(javaFile);
            try {
                javaFile.createNewFile();
            } catch (Exception e) {
                log.error("创建文件失败：{}", javaFilePath, e);
                throw new TException(e);
            }
            FileUtil.writeUtf8String(structCode.toString(), javaFile);
            log.debug("生成java代码：\n路径 {}\n内容 {}", javaFilePath, structCode);
        }
    }

    private String commentToStringParam(ParseThriftSyntaxTreeUtil.CommentBean comment) {
        if (comment == null || StrUtil.isBlank(comment.getCommentValue())) {
            return "";
        }
        String commentString = comment.getCommentValue();
        commentString = StrUtil.replace(commentString, "\"", "“");
        commentString = StrUtil.replace(commentString, "\\", "");
        commentString = StrUtil.replace(commentString, "\n", " ");
        return commentString.trim();
    }

    private String getRestGeneratePath(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, String fileName) {
        String namespace = thriftRoot.getRootBean().getJavaNameSpace();
        String moduleName = null;
        if (namespace.startsWith(CommonConstants.BASE_PACKAGE + "." + SdkConstants.SDK_MODULE_NAME + ".")) {
            moduleName = SdkConstants.SDK_MODULE_NAME;
        } else if (namespace.startsWith(CommonConstants.BASE_PACKAGE + "." + "app" + ".")) {
            moduleName = "app";
        } else if (namespace.startsWith(CommonConstants.BASE_PACKAGE + "." + "preset_sys" + ".")) {
            moduleName = "preset_sys";
        } else if (namespace.startsWith(CommonConstants.BASE_PACKAGE + "." + "common" + ".")) {
            moduleName = "common";
        }
        StringJoiner javaFilePath = new StringJoiner(File.separator);
        javaFilePath.add(SdkConstants.PROJECT_ROOT_PATH).add(moduleName).add("src").add("main").add("java").add(StrUtil.replace(namespace, ".", File.separator)).add(fileName + ".java");
        return javaFilePath.toString();
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
