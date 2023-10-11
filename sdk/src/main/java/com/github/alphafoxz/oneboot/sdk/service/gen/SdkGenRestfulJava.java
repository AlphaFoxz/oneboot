package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.github.alphafoxz.oneboot.common.configuration.CommonConfiguration;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.service.gen.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseRestfulSyntaxTreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Slf4j
@Service
public class SdkGenRestfulJava implements RestfulCodeGenerator {
    private static final String TAB = "    ";
    private final List<String> postAnnoList = CollUtil.newArrayList("PostMapping", "PutMapping", "PatchMapping");
    @Resource
    private CommonConfiguration commonConfiguration;

    @Override
    public Set<CodeFile> genCodeFileSet(@NonNull ParseRestfulSyntaxTreeUtil.RestfulRootBean restfulRoot, String targetDir) {
        Set<CodeFile> result = CollUtil.newHashSet();
        generateRestJavaIfaces(restfulRoot, result);
        generateRestJavaEnums(restfulRoot, result);
        generateRestJavaDtos(restfulRoot, result);
        for (var includeBean : restfulRoot.getIncludeBeanSet()) {
            generateRestJavaIfaces(includeBean, result);
            generateRestJavaDtos(includeBean, result);
            generateRestJavaEnums(includeBean, result);
        }
        return result;
    }

    private CodeFile genApiFile(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, ParseRestfulSyntaxTreeUtil.InterfaceBean interfaceBean) {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseRestfulSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("""
                import io.swagger.v3.oas.annotations.Operation;
                import io.swagger.v3.oas.annotations.tags.Tag;
                import org.springframework.http.ResponseEntity;
                """);
        code.add("import " + commonConfiguration.getPackage() + ".interfaces.framework.HttpController;");
        for (String str : interfaceBean.getImportTypeName()) {
            code.add("import " + str + ";");
        }
        code.add("");
        {
            //解析普通注释
            for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : interfaceBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue().trim());
            }
            //解析@interface注解
            for (Map.Entry<String, List<String>> stringListEntry : interfaceBean.getAnnotationMap().entrySet()) {
                String annoCode = "@" + stringListEntry.getKey();
                if (CollUtil.isNotEmpty(stringListEntry.getValue())) {
                    StringJoiner valueJoiner = new StringJoiner("\", \"", "({\"", "\"})");
                    valueJoiner.setEmptyValue("");
                    for (String s : stringListEntry.getValue()) {
                        valueJoiner.add(s);
                    }
                    annoCode += valueJoiner;
                }
                code.add(annoCode);
            }
            //解析API注释
            ParseRestfulSyntaxTreeUtil.CommentBean interfaceDoc = interfaceBean.getDoc();
            code.add(StrUtil.format("@Tag(name = {}, description = {})", JSONUtil.quote(interfaceBean.getInterfaceName(), true), commentDocToStringWrapParam(interfaceDoc)));
        }
        code.add(StrUtil.format("public interface {} extends HttpController {", interfaceBean.getInterfaceName()));
        {
            //解析接口方法
            for (var interfaceFunction : interfaceBean.getInterfaceFunctionList()) {
                if (interfaceFunction.getAnnotationMap().get("RequestMapping") != null) {
                    throw new OnebootGenCodeException("@uri不允许注解在具体方法上，必须指定一个特定的http方法，请使用@postUri或@getUri", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                code.add(genInterfaceFunctionCode(interfaceFunction));
            }
        }
        code.add("}");
        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(restfulRoot, interfaceBean.getInterfaceName()));
        codeFile.setFileName(interfaceBean.getInterfaceName() + ".java");
        return codeFile;
    }

    private void generateRestJavaIfaces(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, @NonNull Set<CodeFile> result) throws OnebootGenCodeException {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        for (var interfaceBean : rootBean.getInterfaceList()) {
            result.add(genApiFile(restfulRoot, interfaceBean));
        }
    }

    private CodeFile genEnumFile(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, ParseRestfulSyntaxTreeUtil.EnumBean enumBean) {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseRestfulSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("import io.swagger.v3.oas.annotations.media.Schema;\n");
        // 生成Enum的注释
        code.add(StrUtil.format("@Schema(description = {})", commentDocToStringWrapParam(enumBean.getDoc())));
        if (enumBean.getCommentList() != null) {
            for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : enumBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
        }
        code.add("public enum " + enumBean.getEnumName() + " {");
        for (ParseRestfulSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
            //生成枚举的注释
            for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
                code.add(TAB + "// " + commentBean.getCommentValue());
            }
            if (enumInstance.getDoc() != null) {
                code.add(TAB + "/**" + enumInstance.getDoc().getCommentValue() + "*/");
            }
            code.add(TAB + enumInstance.getInstanceName() + ",");
        }
        code.add("}");
        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(restfulRoot, enumBean.getEnumName()));
        codeFile.setFileName(enumBean.getEnumName() + ".java");
        return codeFile;
    }

    private void generateRestJavaEnums(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, @NonNull Set<CodeFile> result) throws OnebootGenCodeException {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        for (var enumBean : rootBean.getEnumList()) {
            result.add(genEnumFile(restfulRoot, enumBean));
        }
    }

    private CodeFile genDtoFile(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, ParseRestfulSyntaxTreeUtil.ClassBean classBean) {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        CodeFile codeFile = new CodeFile();
        StringJoiner code = new StringJoiner("\n");
        code.add("package " + rootBean.getNamespaceMap().get(ParseRestfulSyntaxTreeUtil.NamespaceBean.NamespaceLangEnum.JAVA) + ";\n");
        code.add("import io.swagger.v3.oas.annotations.media.Schema;");
        code.add("import lombok.Getter;");
        for (String str : classBean.getImportTypeName()) {
            code.add("import " + str + ";");
        }
        code.add("");
        {
            //解析普通注释
            List<ParseRestfulSyntaxTreeUtil.CommentBean> commentList = classBean.getCommentList();
            if (CollUtil.isNotEmpty(commentList)) {
                for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : commentList) {
                    code.add("// " + commentBean.getCommentValue().trim());
                }
            }
        }
        {
            //解析注解注释
            ParseRestfulSyntaxTreeUtil.CommentBean classDoc = classBean.getDoc();
            code.add(StrUtil.format("@Schema(name = {}, description = {})", JSONUtil.quote(classBean.getClassName(), true), commentDocToStringWrapParam(classDoc)));
        }
        code.add("@Getter");
        code.add("public class " + classBean.getClassName() + " {");
        for (var fieldBean : classBean.getClassFieldList()) {
            if (CollUtil.isNotEmpty(fieldBean.getCommentList())) {
                for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : fieldBean.getCommentList()) {
                    code.add(TAB + "//" + commentBean.getCommentValue().trim());
                }
            }
            code.add(StrUtil.format(TAB + "@Schema(name = {}, description = {})", JSONUtil.quote(fieldBean.getFieldName(), true), commentDocToStringWrapParam(fieldBean.getDoc())));
            code.add(TAB + "private " + fieldBean.getType().javaString() + " " + fieldBean.getFieldName() + ";");
        }
        code.add("");
        for (var fieldBean : classBean.getClassFieldList()) {
            String format = TAB + "public {} {}({} {}) {";
            code.add(StrUtil.format(format,
                    classBean.getClassName(),
                    StrUtil.upperFirstAndAddPre(fieldBean.getFieldName(), "set"),
                    fieldBean.getType().javaString(),
                    fieldBean.getFieldName()
            ));
            format = TAB + TAB + "this.{} = {};";
            code.add(StrUtil.format(format, fieldBean.getFieldName(), fieldBean.getFieldName()));
            code.add(TAB + TAB + "return this;");
            code.add(TAB + "}");
        }
        code.add("}");

        codeFile.setContent(code.toString());
        codeFile.setPath(getRestGeneratePath(restfulRoot, classBean.getClassName()));
        codeFile.setFileName(classBean.getClassName() + ".java");
        return codeFile;
    }

    /**
     * 本方法包含的特殊处理：
     * 由于post方式请求体为json对象，但是当参数很简单或者复用的可能性极低时，为这种参数额外建模纯属于无效工作量
     * 所以就将其转化为一个单个参数的接口，用java.util.Map接收参数
     */
    private String genInterfaceFunctionCode(ParseRestfulSyntaxTreeUtil.InterfaceBean.InterfaceFunctionBean interfaceFunction) {
        StringJoiner code = new StringJoiner("\n");
        // 是否分页
        boolean isPage = false;
        boolean isPost = false;
        boolean hasRequestParam = false;
        boolean hasResponseParam = false;
        Set<String> pathVarSet = CollUtil.newHashSet();
        {
            //解析普通注释
            List<ParseRestfulSyntaxTreeUtil.CommentBean> commentList = interfaceFunction.getCommentList();
            if (CollUtil.isNotEmpty(commentList)) {
                for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : commentList) {
                    code.add(TAB + "// " + commentBean.getCommentValue().trim());
                }
            }
            //解析@interface注解
            for (Map.Entry<String, List<String>> annoEntry : interfaceFunction.getAnnotationMap().entrySet()) {
                switch (annoEntry.getKey()) {
                    case "Page" -> {
                        isPage = true;
                        continue;
                    }
                    case "HttpServletRequest" -> {
                        hasRequestParam = true;
                        continue;
                    }
                    case "HttpServletResponse" -> {
                        hasResponseParam = true;
                        continue;
                    }
                }
                String annoCode = TAB + "@" + annoEntry.getKey();
                if (CollUtil.isNotEmpty(annoEntry.getValue())) {
                    StringJoiner valueJoiner = new StringJoiner("\", \"", "({\"", "\"})");
                    valueJoiner.setEmptyValue("");
                    for (String s : annoEntry.getValue()) {
                        List<String> all = ReUtil.findAll("\\{\\s*\\w+\\s*}", s, 0);
                        for (String pathVar : all) {
                            pathVarSet.add(pathVar.substring(1, pathVar.length() - 1).trim());
                        }
                        valueJoiner.add(s);
                    }
                    annoCode += valueJoiner;
                }
                if (CollUtil.contains(postAnnoList, annoEntry.getKey())) {
                    isPost = true;
                }
                code.add(annoCode);
            }
            //解析API注释
            ParseRestfulSyntaxTreeUtil.CommentBean functionDoc = interfaceFunction.getDoc();
            code.add(StrUtil.format(TAB + "@Operation(summary = {})", commentDocToStringWrapParam(functionDoc)));
        }
        String returnType = interfaceFunction.getReturnType().javaString();
        if ("void".equals(returnType)) {
            returnType = "?";
        }
        if (isPage) {
            returnType = "Page<" + returnType + ">";
        }
        returnType = "ResponseEntity<" + returnType + ">";
        if (isPost && !interfaceFunction.getParamList().isEmpty() && (interfaceFunction.getParamList().size() > 1 || interfaceFunction.getParamList().get(0).getParamType().isIntype())) {
            // NOTE 对多个参数的post方法进行特殊处理
            StringJoiner outerParamCode1 = new StringJoiner(",\n", "\n", "\n" + TAB);
            outerParamCode1.add(TAB + TAB + "@RequestBody java.util.Map<String, Object> _requestMap");
            StringJoiner outerParamCode2 = new StringJoiner(",\n", "\n", "\n" + TAB);
            StringJoiner innerParamCode = new StringJoiner(", ");
            for (var param : interfaceFunction.getParamList()) {
                outerParamCode2.add(StrUtil.format(TAB + TAB + TAB + "{} {}", param.getParamType().javaString(), param.getParamName()));
                innerParamCode.add(StrUtil.format("({}) _requestMap.get(\"{}\")", param.getParamType().javaString(), param.getParamName()));
            }
            if (hasRequestParam) {
                outerParamCode1.add(TAB + TAB + TAB + "HttpServletRequest _request");
                innerParamCode.add("_request");
                outerParamCode2.add(TAB + TAB + TAB + "HttpServletRequest _request");
            }
            if (hasResponseParam) {
                outerParamCode1.add(TAB + TAB + TAB + "HttpServletResponse _response");
                innerParamCode.add("_response");
                outerParamCode2.add(TAB + TAB + TAB + "HttpServletResponse _response");
            }
            String format = """
                        public default {} {}({}) {
                            return {}({});
                        }
                        public {} {}({});
                    """;
            code.add(StrUtil.format(format,
                    returnType,
                    interfaceFunction.getFunctionName(),
                    outerParamCode1.toString(),
                    interfaceFunction.getFunctionName(),
                    innerParamCode,
                    returnType,
                    interfaceFunction.getFunctionName(),
                    outerParamCode2
            ));
            return code.toString();
        }
        StringJoiner paramCode = new StringJoiner(",\n", "\n", "\n" + TAB);
        for (var param : interfaceFunction.getParamList()) {
            String format = isPost ? TAB + TAB + TAB + "@Parameter(description = {}) @RequestBody {}{} {}" : TAB + TAB + TAB + "@Parameter(description = {}) {}{} {}";
            paramCode.add(StrUtil.format(format, commentDocToStringWrapParam(param.getDoc()), pathVarSet.contains(param.getParamName()) ? "@PathVariable " : "", param.getParamType().javaString(), param.getParamName()));
        }
        if (hasRequestParam) {
            paramCode.add(TAB + TAB + TAB + "HttpServletRequest _request");
        }
        if (hasResponseParam) {
            paramCode.add(TAB + TAB + TAB + "HttpServletResponse _response");
        }
        String format = TAB + "public {} {}({});\n";
        code.add(StrUtil.format(format,
                returnType,
                interfaceFunction.getFunctionName(),
                (!interfaceFunction.getParamList().isEmpty() ? paramCode.toString() : "")
        ));
        return code.toString();
    }

    private void generateRestJavaDtos(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, Set<CodeFile> result) throws OnebootGenCodeException {
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        for (var classBean : rootBean.getClassList()) {
            result.add(genDtoFile(restfulRoot, classBean));
        }
    }

    private String commentDocToStringWrapParam(ParseRestfulSyntaxTreeUtil.CommentBean comment) {
        String value;
        if (comment == null || StrUtil.isBlank(comment.getCommentValue())) {
            value = "";
        } else {
            value = comment.getCommentValue().trim();
        }
        return JSONUtil.quote(value, true);
    }

    private String getRestGeneratePath(ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, String fileName) {
        String namespace = restfulRoot.getRootBean().getJavaNameSpace();
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
