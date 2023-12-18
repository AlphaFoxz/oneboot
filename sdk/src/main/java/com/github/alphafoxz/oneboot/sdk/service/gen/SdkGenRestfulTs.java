package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.service.gen.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseRestfulSyntaxTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Slf4j
@Service
public class SdkGenRestfulTs implements RestfulCodeGenerator {
    private static final String TAB = "  ";
    final String GET_MAPPING = "GetMapping";
    final String POST_MAPPING = "PostMapping";
    final String DELETE_MAPPING = "DeleteMapping";
    final String PUT_MAPPING = "PutMapping";
    final String PATCH_MAPPING = "PatchMapping";

    @Override
    public Set<CodeFile> genCodeFileSet(@NonNull ParseRestfulSyntaxTreeUtil.RestfulRootBean restfulRoot, String targetDir) {
        Set<CodeFile> result = CollUtil.newHashSet();
        result.add(genTsFile(restfulRoot, targetDir));
        for (var includeBean : restfulRoot.getIncludeBeanSet()) {
            result.add(genTsFile(includeBean, targetDir));
        }
        return result;
    }

    private CodeFile genTsFile(@NonNull ParseRestfulSyntaxTreeUtil.RestfulRootIface restfulRoot, @NonNull String genDir) {
        final String docFormat = """
                /**
                 * {}
                 */""";
        final String innerDocFormat = """
                  /**
                   * {}
                   */\
                """;
        CodeFile codeFile = new CodeFile();
        String prefix = genDir + "/" + StrUtil.replace(restfulRoot.getRootBean().getTsNameSpace(), ".", "/");
        codeFile.setPath(prefix + "/" + restfulRoot.getFileName() + ".ts");
        codeFile.setFileName(restfulRoot.getFileName());
        ParseRestfulSyntaxTreeUtil.RootBean rootBean = restfulRoot.getRootBean();
        String namespace = rootBean.getTsNameSpace();
        StringJoiner code = new StringJoiner("\n");
        for (var includeBean : rootBean.getIncludeList()) {
            String includeValue = includeBean.getIncludeValue();
            String includeName = includeValue.substring(includeValue.lastIndexOf("/") + 1, includeValue.lastIndexOf("."));
            includeValue = includeValue.substring(0, includeValue.lastIndexOf("."));
            code.add("import * as " + includeName + " from '" + includeValue + "'");
        }
        if (namespace.contains("apis")) {
            String importStr = "import { requireAxios as _requireAxios, requireJSON as _requireJSON, type Page as _Page } from '";
            int layer = StrUtil.count(namespace, ".") + 1;
            for (int i = 0; i < layer; i++) {
                importStr += "../";
            }
            importStr += "apisUtil'";
            code.add(importStr);
            code.add("");
            code.add("""
                    let _axiosInstance: any
                    const _axios = async () => {
                      if (_axiosInstance) {
                        return _axiosInstance
                      }
                      _axiosInstance = await _requireAxios()
                      return _axiosInstance
                    }
                    """);
            for (var interfaceBean : rootBean.getInterfaceList()) {
                code.add(genApiCode(interfaceBean));
            }
        }
        code.add("");
        for (var classBean : rootBean.getClassList()) {
            for (var commentBean : classBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
            if (classBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, classBean.getDoc().getCommentValue()));
            }
            code.add("export type " + classBean.getClassName() + " = {");
            for (var fieldBean : classBean.getClassFieldList()) {
                for (var commentBean : fieldBean.getCommentList()) {
                    code.add(TAB + "// " + commentBean.getCommentValue());
                }
                if (fieldBean.getDoc() != null) {
                    code.add(StrUtil.format(innerDocFormat, fieldBean.getDoc().getCommentValue()));
                }
                String fieldTsString = fieldBean.getType().tsString() + (ParseRestfulSyntaxTreeUtil.Modifier.OPTIONAL.equals(fieldBean.getModifier()) ? " | null" : "");
                code.add(TAB + fieldBean.getFieldName() + ": " + fieldTsString);
            }
            code.add("}");
        }
        for (var enumBean : rootBean.getEnumList()) {
            for (var commentBean : enumBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
            if (enumBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, enumBean.getDoc().getCommentValue()));
            }
            code.add("export enum " + enumBean.getEnumName() + " {");
            for (var enumInstance : enumBean.getEnumInstance()) {
                for (var commentBean : enumInstance.getCommentList()) {
                    code.add(TAB + "// " + commentBean.getCommentValue());
                }
                if (enumInstance.getDoc() != null) {
                    code.add(StrUtil.format(innerDocFormat, enumInstance.getDoc().getCommentValue()));
                }
                code.add(TAB + enumInstance.getInstanceName() + " = " + enumInstance.getInstanceConstant() + ",");
            }
            code.add("}");
        }
        code.add("");
        codeFile.setContent(code.toString());
        return codeFile;
    }

    /**
     * 创建api代码
     * 由于post方式请求体为json对象，但是当参数很简单或者复用的可能性极低时，为这种参数额外建模纯属于无效工作量
     * 所以在请求时统一放进一个对象，后端生成的代码做对应的解析处理
     */
    private String genApiCode(ParseRestfulSyntaxTreeUtil.InterfaceBean interfaceBean) {
        StringJoiner code = new StringJoiner("\n");
        String serviceUri = "";
        List<String> requestMappingValues = interfaceBean.getAnnotationMap().get(RequestMapping.class.getSimpleName());
        if (requestMappingValues == null) {
            throw new OnebootGenCodeException("api缺少uri映射", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (CollUtil.isNotEmpty(requestMappingValues)) {
            serviceUri = requestMappingValues.get(0);
        }
        for (ParseRestfulSyntaxTreeUtil.CommentBean commentBean : interfaceBean.getCommentList()) {
            code.add("// " + commentBean.getCommentValue());
        }
        if (interfaceBean.getDoc() != null) {
            StringJoiner apiDoc = new StringJoiner("\n * ", "/**\n * ", "\n */");
            for (String s : interfaceBean.getDoc().getCommentValue().split("\n", -1)) {
                apiDoc.add(s);
            }
            code.add(apiDoc.toString());
        }
        code.add("export const " + interfaceBean.getInterfaceName() + " = {");
        for (var interfaceFunction : interfaceBean.getInterfaceFunctionList()) {
            boolean isPost = false;
            String functionUri = "";
            String executeFormat = "";
            String executeParam = "";
            boolean isPage = false;
            for (var commentBean : interfaceFunction.getCommentList()) {
                code.add(TAB + "// " + commentBean.getCommentValue());
            }
            {
                // 处理方法注释
                StringJoiner innerDoc = new StringJoiner("\n" + TAB + " * ", TAB + "/**\n" + TAB + " * ", "\n" + TAB + " */");
                innerDoc.setEmptyValue("");
                if (interfaceFunction.getDoc() != null) {
                    for (String s : interfaceFunction.getDoc().getCommentValue().split("\n", -1)) {
                        innerDoc.add(s);
                    }
                }
                for (var param : interfaceFunction.getParamList()) {
                    if (param.getDoc() != null) {
                        int i = 0;
                        for (String s : param.getDoc().getCommentValue().split("\n", -1)) {
                            String prefix = i == 0 ? "@param p_" + param.getParamName() + " " : TAB + TAB;
                            innerDoc.add(prefix + s);
                            i++;
                        }
                    }
                }
                code.add(innerDoc.toString());
            }
            // 处理@注解
            for (Map.Entry<String, List<String>> annoEntry : interfaceFunction.getAnnotationMap().entrySet()) {
                switch (annoEntry.getKey()) {
                    case "Page" -> {
                        isPage = true;
                    }
                    case "RequestMapping" -> {
                        throw new OnebootGenCodeException("@uri不允许注解在具体方法上，必须指定一个特定的http方法，请使用@postUri或@getUri", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    case GET_MAPPING -> {
                        functionUri = annoEntry.getValue().get(0);
                        executeFormat = TAB + TAB + "return (await (await _axios()).get(`{}`)).data";
                    }
                    case DELETE_MAPPING -> {
                        functionUri = annoEntry.getValue().get(0);
                        executeFormat = TAB + TAB + "return (await (await _axios()).delete(`{}`)).data";
                    }
                    case POST_MAPPING -> {
                        functionUri = annoEntry.getValue().get(0);
                        executeFormat = TAB + TAB + "return (await (await _axios()).post(`{}`, {})).data";
                        isPost = true;
                    }
                    case PUT_MAPPING -> {
                        functionUri = annoEntry.getValue().get(0);
                        executeFormat = TAB + TAB + "return (await (await _axios()).put(`{}`, {})).data";
                        isPost = true;
                    }
                    case PATCH_MAPPING -> {
                        functionUri = annoEntry.getValue().get(0);
                        executeFormat = TAB + TAB + "return (await (await _axios()).patch(`{}`, {})).data";
                        isPost = true;
                    }
                }
            }
            Set<String> pathVarSet = CollUtil.newHashSet();
            for (String match : ReUtil.findAllGroup0("\\{\\s*\\w+\\s*}", functionUri)) {
                String pathVar = match.substring(1, match.length() - 1).trim();
                functionUri = StrUtil.replaceFirst(functionUri, match, "${encodeURI(p_" + pathVar + ".toString())}");
                pathVarSet.add(pathVar);
            }
            if (isPost && interfaceFunction.getParamList().size() == 1 && !interfaceFunction.getParamList().get(0).getParamType().isIntype()) {
                // 只有单个参数
                executeParam = "p_" + interfaceFunction.getParamList().get(0).getParamName();
            } else {
                StringJoiner executeParamJoiner = isPost ? new StringJoiner(", ", "{ ", " }") : new StringJoiner("&", "?", "");
                executeParamJoiner.setEmptyValue("");
                for (var param : interfaceFunction.getParamList()) {
                    if (pathVarSet.contains(param.getParamName())) {
                        continue;
                    }
                    if (isPost) {
                        executeParamJoiner.add(param.getParamName() + ": p_" + param.getParamName());
                        continue;
                    }
                    if (param.getParamType().isIntype()) {
                        executeParamJoiner.add(param.getParamName() + "=" + "${encodeURI(p_" + param.getParamName() + ")}");
                    } else {
                        executeParamJoiner.add(param.getParamName() + "=" + "${encodeURI(_requireJSON().stringify(p_" + param.getParamName() + "))}");
                    }
                }
                executeParam = executeParamJoiner.toString();
            }
            String functionHeader = TAB + interfaceFunction.getFunctionName() + ": async ";
            StringJoiner paramStringJoiner = new StringJoiner(", ", "(", ")");
            for (var param : interfaceFunction.getParamList()) {
                paramStringJoiner.add("p_" + param.getParamName() + ": " + param.getParamType().tsString());
            }
            String returnTypeString = isPage ? "_Page<" + interfaceFunction.getReturnType().tsString() + ">" : interfaceFunction.getReturnType().tsString();
            code.add(functionHeader + paramStringJoiner + ": Promise<" + returnTypeString + "> => {");
            if (isPost) {
                code.add(StrUtil.format(executeFormat, serviceUri + functionUri, executeParam));
            } else {
                code.add(StrUtil.format(executeFormat, serviceUri + functionUri + executeParam));
            }
            code.add(TAB + "},");
        }
        code.add("}");
        return code.toString();
    }
}
