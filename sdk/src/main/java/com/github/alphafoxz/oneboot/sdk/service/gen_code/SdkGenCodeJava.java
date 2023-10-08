package com.github.alphafoxz.oneboot.sdk.service.gen_code;

import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.service.gen_code.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftSyntaxTreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Slf4j
@Service
public class SdkGenCodeJava implements CodeGenerator {
    private static final String TAB = "    ";
    @Resource
    private CommonConfiguration commonConfiguration;

    @Override
    public Set<CodeFile> genCodeFileSet(@NonNull ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRoot, String targetDir) {
        Set<CodeFile> result = CollUtil.newHashSet();
        generateRestJavaIfaces(thriftRoot, result);
        generateRestJavaEnums(thriftRoot, result);
        generateRestJavaDtos(thriftRoot, result);
        for (ParseThriftSyntaxTreeUtil.ThriftIncludeBean includeBean : thriftRoot.getIncludeBeanSet()) {
            generateRestJavaIfaces(includeBean, result);
            generateRestJavaDtos(includeBean, result);
            generateRestJavaEnums(includeBean, result);
        }
        return result;
    }

    private CodeFile genApiFile(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, ParseThriftSyntaxTreeUtil.ServiceBean serviceBean) {
        final List<String> postAnnoList = CollUtil.newArrayList("PostMapping", "PutMapping", "PatchMapping");
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("""
                import io.swagger.v3.oas.annotations.Operation;
                import io.swagger.v3.oas.annotations.tags.Tag;
                import org.springframework.http.ResponseEntity;
                """);
        code.add("import " + commonConfiguration.getPackage() + ".interfaces.framework.HttpController;");
        for (String str : serviceBean.getImportTypeName()) {
            code.add("import " + str + ";");
        }
        code.add("");
        {
            //解析普通注释
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : serviceBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue().trim());
            }
            //解析@interface注解
            for (Map.Entry<String, List<String>> stringListEntry : serviceBean.getCommentAnnoMap().entrySet()) {
                String annoCode = "@" + stringListEntry.getKey();
                if (CollUtil.isNotEmpty(stringListEntry.getValue())) {
                    StringJoiner valueJoiner = new StringJoiner("\", \"", "{\"", "\"}");
                    for (String s : stringListEntry.getValue()) {
                        valueJoiner.add(s);
                    }
                    annoCode += "(" + valueJoiner + ")";
                }
                code.add(annoCode);
            }
            //解析API注释
            ParseThriftSyntaxTreeUtil.CommentBean serviceDoc = serviceBean.getDoc();
            code.add(StrUtil.format("@Tag(name = {}, description = {})", JSONUtil.quote(serviceBean.getServiceName(), true), commentDocToStringWrapParam(serviceDoc)));
        }
        code.add("public interface " + serviceBean.getServiceName() + " extends HttpController {");
        {
            //解析接口方法
            for (ParseThriftSyntaxTreeUtil.ServiceBean.ServiceFunctionBean serviceFunction : serviceBean.getServiceFunctionList()) {
                // 是否分页
                boolean isPage = false;
                boolean isPost = false;
                Set<String> pathVarSet = CollUtil.newHashSet();
                {
                    //解析普通注释
                    List<ParseThriftSyntaxTreeUtil.CommentBean> commentList = serviceFunction.getCommentList();
                    if (CollUtil.isNotEmpty(commentList)) {
                        for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : commentList) {
                            code.add(TAB + "// " + commentBean.getCommentValue().trim());
                        }
                    }
                    //解析@interface注解
                    for (Map.Entry<String, List<String>> stringListEntry : serviceFunction.getCommentAnnoMap().entrySet()) {
                        if (stringListEntry.getKey().equals("Page")) {
                            isPage = true;
                            continue;
                        }
                        String annoCode = TAB + "@" + stringListEntry.getKey();
                        if (CollUtil.isNotEmpty(stringListEntry.getValue())) {
                            StringJoiner valueJoiner = new StringJoiner("\", \"", "{\"", "\"}");
                            for (String s : stringListEntry.getValue()) {
                                List<String> all = ReUtil.findAll("\\{\\s*\\w+\\s*}", s, 0);
                                for (String pathVar : all) {
                                    pathVarSet.add(pathVar.substring(1, pathVar.length() - 1).trim());
                                }
                                valueJoiner.add(s);
                            }
                            annoCode += "(" + valueJoiner + ")";
                        }
                        if (CollUtil.contains(postAnnoList, stringListEntry.getKey())) {
                            isPost = true;
                        }
                        code.add(annoCode);
                    }
                    //解析API注释
                    ParseThriftSyntaxTreeUtil.CommentBean functionDoc = serviceFunction.getDoc();
                    code.add(StrUtil.format(TAB + "@Operation(summary = {})", commentDocToStringWrapParam(functionDoc)));
                }
                StringJoiner paramCode = new StringJoiner(",\n", "\n", "\n" + TAB);
                for (ParseThriftSyntaxTreeUtil.Param param : serviceFunction.getParamList()) {
                    String format = isPost ? TAB + TAB + TAB + "@Parameter(description = {}) @RequestBody {}{} {}" : TAB + TAB + TAB + "@Parameter(description = {}) {}{} {}";
                    paramCode.add(StrUtil.format(format, commentDocToStringWrapParam(param.getDoc()), pathVarSet.contains(param.getParamName()) ? "@PathVariable " : "", param.getParamType().javaString(), param.getParamName()));
                }
                String returnType = serviceFunction.getReturnType().javaString();
                if ("void".equals(returnType)) {
                    returnType = "?";
                }
                if (isPage) {
                    returnType = "Page<" + returnType + ">";
                }
                returnType = "ResponseEntity<" + returnType + ">";
                String format = TAB + "public {} {}({});\n";
                code.add(StrUtil.format(format,
                        returnType,
                        serviceFunction.getFunctionName(),
                        (!serviceFunction.getParamList().isEmpty() ? paramCode.toString() : "")
                ));
            }
        }
        code.add("}");
        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(thriftRoot, serviceBean.getServiceName()));
        codeFile.setFileName(serviceBean.getServiceName() + ".java");
        return codeFile;
    }

    private void generateRestJavaIfaces(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, @NonNull Set<CodeFile> result) throws OnebootGenCodeException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        for (ParseThriftSyntaxTreeUtil.ServiceBean serviceBean : rootBean.getServiceList()) {
            result.add(genApiFile(thriftRoot, serviceBean));
        }
    }

    private CodeFile genEnumFile(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, ParseThriftSyntaxTreeUtil.EnumBean enumBean) {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("import io.swagger.v3.oas.annotations.media.Schema;\n");
        // 生成Enum的注释
        code.add(StrUtil.format("@Schema(description = {})", commentDocToStringWrapParam(enumBean.getDoc())));
        if (enumBean.getCommentList() != null) {
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
        }
        code.add("public enum " + enumBean.getEnumName() + " {");
        for (ParseThriftSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
            //生成枚举的注释
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
                code.add(TAB + "// " + commentBean.getCommentValue());
            }
            if (enumInstance.getDoc() != null) {
                code.add(TAB + "/**" + enumInstance.getDoc().getCommentValue() + "*/");
            }
            code.add(TAB + enumInstance.getInstanceName() + ",");
        }
        code.add("}");
        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(thriftRoot, enumBean.getEnumName()));
        codeFile.setFileName(enumBean.getEnumName() + ".java");
        return codeFile;
    }

    private void generateRestJavaEnums(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, @NonNull Set<CodeFile> result) throws OnebootGenCodeException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        for (ParseThriftSyntaxTreeUtil.EnumBean enumBean : rootBean.getEnumList()) {
            result.add(genEnumFile(thriftRoot, enumBean));
        }
    }

    private CodeFile genDtoFile(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, ParseThriftSyntaxTreeUtil.StructBean structBean) {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseThriftSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("import io.swagger.v3.oas.annotations.media.Schema;");
        code.add("import lombok.Getter;");
        for (String str : structBean.getImportTypeName()) {
            code.add("import " + str + ";");
        }
        code.add("");
        {
            //解析普通注释
            List<ParseThriftSyntaxTreeUtil.CommentBean> commentList = structBean.getCommentList();
            if (CollUtil.isNotEmpty(commentList)) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : commentList) {
                    code.add("// " + commentBean.getCommentValue().trim());
                }
            }
        }
        {
            //解析注解注释
            ParseThriftSyntaxTreeUtil.CommentBean structDoc = structBean.getDoc();
            code.add(StrUtil.format("@Schema(name = {}, description = {})", JSONUtil.quote(structBean.getStructName(), true), commentDocToStringWrapParam(structDoc)));
        }
        code.add("@Getter");
        code.add("public class " + structBean.getStructName() + " {");
        for (ParseThriftSyntaxTreeUtil.StructBean.StructAttributeBean attributeBean : structBean.getStructAttribute()) {
            if (CollUtil.isNotEmpty(attributeBean.getCommentList())) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : attributeBean.getCommentList()) {
                    code.add(TAB + "//" + commentBean.getCommentValue().trim());
                }
            }
            code.add(StrUtil.format(TAB + "@Schema(name = {}, description = {})", JSONUtil.quote(attributeBean.getAttributeName(), true), commentDocToStringWrapParam(attributeBean.getDoc())));
            code.add(TAB + "private " + attributeBean.getType().javaString() + " " + attributeBean.getAttributeName() + ";");
        }
        code.add("");
        for (ParseThriftSyntaxTreeUtil.StructBean.StructAttributeBean attributeBean : structBean.getStructAttribute()) {
            String format = TAB + "public {} {}({} {}) {";
            code.add(StrUtil.format(format,
                    structBean.getStructName(),
                    StrUtil.upperFirstAndAddPre(attributeBean.getAttributeName(), "set"),
                    attributeBean.getType().javaString(),
                    attributeBean.getAttributeName()
            ));
            format = TAB + TAB + "this.{} = {};";
            code.add(StrUtil.format(format, attributeBean.getAttributeName(), attributeBean.getAttributeName()));
            code.add(TAB + TAB + "return this;");
            code.add(TAB + "}");
        }
        code.add("}");

        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(thriftRoot, structBean.getStructName()));
        codeFile.setFileName(structBean.getStructName() + ".java");
        return codeFile;
    }

    private void generateRestJavaDtos(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, Set<CodeFile> result) throws OnebootGenCodeException {
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        for (ParseThriftSyntaxTreeUtil.StructBean structBean : rootBean.getStructList()) {
            result.add(genDtoFile(thriftRoot, structBean));
        }
    }

    private String commentDocToStringWrapParam(ParseThriftSyntaxTreeUtil.CommentBean comment) {
        String value;
        if (comment == null || StrUtil.isBlank(comment.getCommentValue())) {
            value = "";
        } else {
            value = comment.getCommentValue().trim();
        }
        return JSONUtil.quote(value, true);
    }

    private String getRestGeneratePath(ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, String fileName) {
        String namespace = thriftRoot.getRootBean().getJavaNameSpace();
        String moduleName = null;
        for (Class<?> aClass : ClassUtil.scanPackageBySuper(commonConfiguration.getBasePackage(), OnebootModuleConfig.class)) {
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
}
