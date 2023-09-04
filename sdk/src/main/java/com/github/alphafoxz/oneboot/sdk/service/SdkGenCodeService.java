package com.github.alphafoxz.oneboot.sdk.service;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.Iface.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.config.CommonConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkMapResponseDto;
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
    private static final String TAB = "    ";

    @Resource
    private Snowflake snowflake;
    @Resource
    private SdkInfoService sdkInfoService;
    @Resource
    private SdkThriftService sdkThriftService;
    @Resource
    private CommonConfig commonConfig;

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
        result.getData().put(previewGenerateRestTsPath(thriftRoot, genDir), previewGenerateRestTsCode(thriftRoot));
        for (ParseThriftSyntaxTreeUtil.ThriftIncludeBean includeBean : thriftRoot.getIncludeBeanSet()) {
            result.getData().put(previewGenerateRestTsPath(includeBean, genDir), previewGenerateRestTsCode(includeBean));
        }
        result.setSuccess(true);
        return result;
    }

    private String previewGenerateRestTsPath(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, String genDir) {
        String prefix = genDir + "/" + StrUtil.replace(thriftRoot.getRootBean().getJsNameSpace(), ".", "/");
        return prefix + "/" + thriftRoot.getFileName() + ".ts";
    }

    private String previewGenerateRestTsCode(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) {
        final String docFormat = """
                /**
                 * {}
                 */
                """;
        final String innerDocFormat = """
                    /**
                     * {}
                     */
                """;
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        String namespace = rootBean.getJsNameSpace();
        StringJoiner code = new StringJoiner("");
        for (ParseThriftSyntaxTreeUtil.IncludeBean includeBean : rootBean.getIncludeList()) {
            String includeValue = includeBean.getIncludeValue();
            String includeName = includeValue.substring(includeValue.lastIndexOf("/") + 1, includeValue.lastIndexOf("."));
            includeValue = includeValue.substring(0, includeValue.lastIndexOf("."));
            code.add("import * as " + includeName + " from '" + includeValue + "'\n");
        }
        if (namespace.contains("apis")) {
            code.add("import { requireAxios } from '");
            int layer = StrUtil.count(namespace, ".") + 1;
            for (int i = 0; i < layer; i++) {
                code.add("../");
            }
            code.add("axios'\n");
            code.add("const axios = requireAxios()\n\n");
            for (ParseThriftSyntaxTreeUtil.ServiceBean serviceBean : rootBean.getServiceList()) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : serviceBean.getCommentList()) {
                    code.add("// " + commentBean.getCommentValue() + "\n");
                }
                if (serviceBean.getDoc() != null) {
                    code.add(StrUtil.format(docFormat, serviceBean.getDoc().getCommentValue()));
                }
                code.add("export const " + serviceBean.getServiceName() + " = {\n");
                for (ParseThriftSyntaxTreeUtil.ServiceBean.ServiceFunctionBean functionBean : serviceBean.getServiceFunctionList()) {
                    for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : functionBean.getCommentList()) {
                        code.add(TAB + "// " + commentBean.getCommentValue() + "\n");
                    }
                    if (functionBean.getDoc() != null) {
                        code.add(StrUtil.format(innerDocFormat, functionBean.getDoc().getCommentValue()));
                    }
                    code.add(TAB + functionBean.getFunctionName() + ": async ");
                    StringJoiner paramStringJoiner = new StringJoiner(", ", "(", ")");
                    for (ParseThriftSyntaxTreeUtil.Param param : functionBean.getParamList()) {
                        paramStringJoiner.add(param.getParamName() + ": " + param.getParamType().tsString());
                    }
                    code.add(paramStringJoiner + ": Promise<" + functionBean.getReturnType().tsString() + "> => {\n");
                    //FIXME 确定请求方式
                    code.add(TAB + TAB + "return axios.post('')\n");
                    code.add(TAB + "},\n");
                }
                code.add("}\n");
            }
            return code.toString();
        }
        code.add("\n");
        for (ParseThriftSyntaxTreeUtil.StructBean structBean : rootBean.getStructList()) {
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : structBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue() + "\n");
            }
            if (structBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, structBean.getDoc().getCommentValue()));
            }
            code.add("export type " + structBean.getStructName() + " = {\n");
            for (ParseThriftSyntaxTreeUtil.StructBean.StructAttributeBean attributeBean : structBean.getStructAttribute()) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : attributeBean.getCommentList()) {
                    code.add(TAB + "// " + commentBean.getCommentValue() + "\n");
                }
                if (attributeBean.getDoc() != null) {
                    code.add(StrUtil.format(innerDocFormat, attributeBean.getDoc().getCommentValue()));
                }
                code.add(TAB + attributeBean.getAttributeName() + ": " + attributeBean.getType().tsString() + "\n");
            }
            code.add("}\n");
        }
        for (ParseThriftSyntaxTreeUtil.EnumBean enumBean : rootBean.getEnumList()) {
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue() + "\n");
            }
            if (enumBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, enumBean.getDoc().getCommentValue()));
            }
            code.add("export enum " + enumBean.getEnumName() + " {\n");
            for (ParseThriftSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
                    code.add(TAB + "// " + commentBean.getCommentValue() + "\n");
                }
                if (enumInstance.getDoc() != null) {
                    code.add(StrUtil.format(innerDocFormat, enumInstance.getDoc().getCommentValue()));
                }
                code.add(TAB + enumInstance.getInstanceName() + " = " + enumInstance.getInstanceConstant() + ",\n");
            }
            code.add("}\n");
        }
        return code.toString();
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
        generateRestJavaIfaces(thriftRoot);
        generateRestJavaEnums(thriftRoot);
        generateRestJavaDtos(thriftRoot);
        for (ParseThriftSyntaxTreeUtil.ThriftIncludeBean includeBean : thriftRoot.getIncludeBeanSet()) {
            generateRestJavaIfaces(includeBean);
            generateRestJavaDtos(includeBean);
            generateRestJavaEnums(includeBean);
        }
        result.setSuccess(true);
        return result;
    }

    private void generateRestJavaIfaces(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
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
                            serviceCode.add(TAB + "// " + commentBean.getCommentValue().trim());
                        }
                    }
                    ParseThriftSyntaxTreeUtil.CommentBean functionDoc = serviceFunction.getDoc();
                    serviceCode.add(StrUtil.format(TAB + "@Operation(summary = \"{}\")", commentToStringParam(functionDoc)));
                    StringJoiner paramCode = new StringJoiner(",\n", "\n", "\n" + TAB);
                    for (ParseThriftSyntaxTreeUtil.Param param : serviceFunction.getParamList()) {
                        paramCode.add(TAB + TAB + TAB + "@Parameter(description = \"" + commentToStringParam(param.getDoc()) + "\") " + param.getParamType().javaString() + " " + param.getParamName());
                    }
                    String returnType = "void".equals(serviceFunction.getReturnType().javaString()) ? "ResponseEntity<?>" : "ResponseEntity<" + serviceFunction.getReturnType().javaString() + ">";
                    serviceCode.add(TAB + "public " + returnType + " " + serviceFunction.getFunctionName() + "(" + (!serviceFunction.getParamList().isEmpty() ? paramCode.toString() : "") + ");\n");
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

    private void generateRestJavaEnums(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
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
                    enumCode.add("// " + commentBean.getCommentValue());
                }
            }
            enumCode.add("public enum " + enumBean.getEnumName() + " {");
            for (ParseThriftSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
                //生成枚举的注释
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
                    enumCode.add(TAB + "// " + commentBean.getCommentValue());
                }
                if (enumInstance.getDoc() != null) {
                    enumCode.add(TAB + "/**" + enumInstance.getDoc().getCommentValue() + "*/");
                }
                enumCode.add(TAB + enumInstance.getInstanceName() + ",");
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

    private void generateRestJavaDtos(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot) throws TException {
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
                        structCode.add(TAB + "//" + commentBean.getCommentValue().trim());
                    }
                }
                structCode.add(StrUtil.format(TAB + "@Schema(description = \"{}\")", commentToStringParam(attributeBean.getDoc())));
                structCode.add(TAB + "private " + attributeBean.getType().javaString() + " " + attributeBean.getAttributeName() + ";");
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
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfig.getBasePackage(), OnebootModuleConfig.class)) {
            Object bean = SpringUtil.getBean(aClass);
            if (bean instanceof OnebootModuleConfig config && namespace.startsWith(config.getPackage() + ".")) {
                moduleName = config.getModuleName();
                break;
            }
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

            for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfig.getBasePackage(), OnebootModuleConfig.class)) {
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