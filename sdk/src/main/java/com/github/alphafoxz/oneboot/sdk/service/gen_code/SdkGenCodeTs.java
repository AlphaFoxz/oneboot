package com.github.alphafoxz.oneboot.sdk.service.gen_code;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.service.gen_code.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftSyntaxTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Slf4j
@Service
public class SdkGenCodeTs implements CodeGenerator {
    private static final String TAB = "  ";

    @Override
    public Set<CodeFile> genCodeFileSet(@NonNull ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRoot, String targetDir) {
        Set<CodeFile> result = CollUtil.newHashSet();
        result.add(genTsFile(thriftRoot, targetDir));
        for (ParseThriftSyntaxTreeUtil.ThriftIncludeBean includeBean : thriftRoot.getIncludeBeanSet()) {
            result.add(genTsFile(includeBean, targetDir));
        }
        return result;
    }

    private CodeFile genTsFile(@NonNull ParseThriftSyntaxTreeUtil.ThriftRootIface thriftRoot, @NonNull String genDir) {
        CodeFile codeFile = new CodeFile();
        String prefix = genDir + "/" + StrUtil.replace(thriftRoot.getRootBean().getJsNameSpace(), ".", "/");
        codeFile.setPath(prefix + "/" + thriftRoot.getFileName() + ".ts");
        codeFile.setFileName(thriftRoot.getFileName());
        final String docFormat = """
                /**
                 * {}
                 */""";
        final String innerDocFormat = """
                  /**
                   * {}
                   */\
                """;
        ParseThriftSyntaxTreeUtil.RootBean rootBean = thriftRoot.getRootBean();
        String namespace = rootBean.getJsNameSpace();
        StringJoiner code = new StringJoiner("\n");
        for (ParseThriftSyntaxTreeUtil.IncludeBean includeBean : rootBean.getIncludeList()) {
            String includeValue = includeBean.getIncludeValue();
            String includeName = includeValue.substring(includeValue.lastIndexOf("/") + 1, includeValue.lastIndexOf("."));
            includeValue = includeValue.substring(0, includeValue.lastIndexOf("."));
            code.add("import * as " + includeName + " from '" + includeValue + "'");
        }
        if (namespace.contains("apis")) {
            String importStr = "import { requireAxios, requireJSON, type _Page } from '";
            int layer = StrUtil.count(namespace, ".") + 1;
            for (int i = 0; i < layer; i++) {
                importStr += "../";
            }
            importStr += "apisUtil'";
            code.add(importStr);
            code.add("const axios = requireAxios()");
            code.add("const jsonUtil = requireJSON()\n");
            for (ParseThriftSyntaxTreeUtil.ServiceBean serviceBean : rootBean.getServiceList()) {
                String serviceUri = "";
                List<String> requestMappingValues = serviceBean.getCommentAnnoMap().get(RequestMapping.class.getSimpleName());
                if (CollUtil.isNotEmpty(requestMappingValues)) {
                    serviceUri = requestMappingValues.get(0);
                }
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : serviceBean.getCommentList()) {
                    code.add("// " + commentBean.getCommentValue());
                }
                if (serviceBean.getDoc() != null) {
                    code.add(StrUtil.format(docFormat, serviceBean.getDoc().getCommentValue()));
                }
                code.add("export const " + serviceBean.getServiceName() + " = {");
                for (ParseThriftSyntaxTreeUtil.ServiceBean.ServiceFunctionBean functionBean : serviceBean.getServiceFunctionList()) {
                    for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : functionBean.getCommentList()) {
                        code.add(TAB + "// " + commentBean.getCommentValue() + "");
                    }
                    if (functionBean.getDoc() != null) {
                        code.add(StrUtil.format(innerDocFormat, functionBean.getDoc().getCommentValue()));
                    }
                    String s = TAB + functionBean.getFunctionName() + ": async ";
                    StringJoiner paramStringJoiner = new StringJoiner(", ", "(", ")");
                    for (ParseThriftSyntaxTreeUtil.Param param : functionBean.getParamList()) {
                        paramStringJoiner.add(param.getParamName() + ": " + param.getParamType().tsString());
                    }
                    code.add(s + paramStringJoiner + ": Promise<" + functionBean.getReturnType().tsString() + "> => {");
                    for (Map.Entry<String, List<String>> functionAnno : functionBean.getCommentAnnoMap().entrySet()) {
                        String annoValue = functionAnno.getValue().get(0);
                        Set<String> pathVarSet = CollUtil.newHashSet();
                        for (String match : ReUtil.findAllGroup0("\\{\\s*\\w+\\s*}", annoValue)) {
                            String pathVar = match.substring(1, match.length() - 1).trim();
                            annoValue = StrUtil.replaceFirst(annoValue, match, "${encodeURI(" + pathVar + ".toString())}");
                            pathVarSet.add(pathVar);
                        }
                        //统计传参
                        StringJoiner getJoiner = new StringJoiner("&", "?", "");
                        StringJoiner postJoiner = new StringJoiner(", ");
                        int paramsCounter = 0;
                        for (ParseThriftSyntaxTreeUtil.Param param : functionBean.getParamList()) {
                            if (pathVarSet.contains(param.getParamName())) {
                                continue;
                            }
                            if (param.getParamType().isIntype()) {
                                getJoiner.add(param.getParamName() + "=" + "${encodeURI(" + param.getParamName() + ")}");
                            } else {
                                getJoiner.add(param.getParamName() + "=" + "${encodeURI(jsonUtil.stringify(" + param.getParamName() + "))}");
                            }
                            if (paramsCounter == 0) {
                                postJoiner.add(param.getParamName());
                            }
                            paramsCounter++;
                        }
                        if (GetMapping.class.getSimpleName().equals(functionAnno.getKey())) {
                            String format = TAB + TAB + "return (await axios.get(`{}`)).data";
                            code.add(StrUtil.format(format, serviceUri + annoValue + getJoiner));
                        } else if (DeleteMapping.class.getSimpleName().equals(functionAnno.getKey())) {
                            String format = TAB + TAB + "return (await axios.delete(`{}`)).data";
                            code.add(StrUtil.format(format, serviceUri + annoValue + getJoiner));
                        } else {
                            if (RequestMapping.class.getSimpleName().equals(functionAnno.getKey())
                                    || PostMapping.class.getSimpleName().equals(functionAnno.getKey())) {
                                if (paramsCounter > 1) {
                                    log.error("post请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName());
                                    throw new OnebootGenCodeException("post请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName(), HttpStatus.INTERNAL_SERVER_ERROR);
                                }
                                String format = TAB + TAB + "return (await axios.post(`{}`, {})).data";
                                code.add(StrUtil.format(format, serviceUri + annoValue, postJoiner.toString()));
                            } else if (PutMapping.class.getSimpleName().equals(functionAnno.getKey())) {
                                if (paramsCounter > 1) {
                                    log.error("put请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName());
                                    throw new OnebootGenCodeException("put请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName(), HttpStatus.INTERNAL_SERVER_ERROR);
                                }
                                String format = TAB + TAB + "return (await axios.put(`{}`, {})).data";
                                code.add(StrUtil.format(format, serviceUri + annoValue, postJoiner.toString()));
                            } else if (PatchMapping.class.getSimpleName().equals(functionAnno.getKey())) {
                                if (paramsCounter > 1) {
                                    log.error("patch请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName());
                                    throw new OnebootGenCodeException("patch请求参数个数不能大于1 " + serviceBean.getServiceName() + "::" + functionBean.getFunctionName(), HttpStatus.INTERNAL_SERVER_ERROR);
                                }
                                String format = TAB + TAB + "return (await axios.patch(`{}`, {})).data";
                                code.add(StrUtil.format(format, serviceUri + annoValue, postJoiner.toString()));
                            }
                        }
                    }
                    code.add(TAB + "},");
                }
                code.add("}");
                code.add("");
            }
            codeFile.setContent(code.toString());
            return codeFile;
        }
        code.add("");
        for (ParseThriftSyntaxTreeUtil.StructBean structBean : rootBean.getStructList()) {
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : structBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
            if (structBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, structBean.getDoc().getCommentValue()));
            }
            code.add("export type " + structBean.getStructName() + " = {");
            for (ParseThriftSyntaxTreeUtil.StructBean.StructAttributeBean attributeBean : structBean.getStructAttribute()) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : attributeBean.getCommentList()) {
                    code.add(TAB + "// " + commentBean.getCommentValue());
                }
                if (attributeBean.getDoc() != null) {
                    code.add(StrUtil.format(innerDocFormat, attributeBean.getDoc().getCommentValue()));
                }
                code.add(TAB + attributeBean.getAttributeName() + ": " + attributeBean.getType().tsString());
            }
            code.add("}");
        }
        for (ParseThriftSyntaxTreeUtil.EnumBean enumBean : rootBean.getEnumList()) {
            for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumBean.getCommentList()) {
                code.add("// " + commentBean.getCommentValue());
            }
            if (enumBean.getDoc() != null) {
                code.add(StrUtil.format(docFormat, enumBean.getDoc().getCommentValue()));
            }
            code.add("export enum " + enumBean.getEnumName() + " {");
            for (ParseThriftSyntaxTreeUtil.EnumBean.EnumInstance enumInstance : enumBean.getEnumInstance()) {
                for (ParseThriftSyntaxTreeUtil.CommentBean commentBean : enumInstance.getCommentList()) {
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
}
