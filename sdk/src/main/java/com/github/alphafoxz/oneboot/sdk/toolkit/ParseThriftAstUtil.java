package com.github.alphafoxz.oneboot.sdk.toolkit;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import jdk.jfr.ContentType;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public final class ParseThriftAstUtil {
    private static final String PAIRS = "pairs";
    private static final String RULE = "rule";
    private static final String INNER = "inner";

    public static ThriftRootBean parseRoot(Map<String, ?> rootAst) {
        List rootPairs = (List) rootAst.get(PAIRS);
        if (rootPairs == null || rootPairs.isEmpty()) {
            return null;
        }
        ThriftRootBean rootBean = new ThriftRootBean();
        CommentBean targetDoc = null;
        final List<CommentBean> targetCommentList = CollUtil.newArrayList();
        for (Map pairMap : (List<Map>) rootAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            if (pairMap.get(INNER) instanceof Map innerMap) {
                switch (ruleName) {
                    case "COMMENT" -> {
                        CommentBean comment = parseComment(innerMap);
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case "include" -> {
                        targetDoc = null;
                        targetCommentList.clear();
                        rootBean.getIncludeList().add(parseInclude(innerMap));
                    }
                    case "namespace" -> {
                        targetDoc = null;
                        targetCommentList.clear();
                        NamespaceBean namespace = parseNamespace(innerMap);
                        rootBean.getNamespaceMap().put(namespace.getNamespaceLang(), namespace.getNamespaceValue());
                    }
                    case "enum" -> {
                        EnumBean enumBean = parseEnum(innerMap);
                        if (targetDoc != null) {
                            enumBean.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (CollUtil.isNotEmpty(targetCommentList)) {
                            enumBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        rootBean.getEnumList().add(enumBean);
                    }
                    case "struct" -> {
                        StructBean structBean = parseStruct(innerMap);
                        if (targetDoc != null) {
                            structBean.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (CollUtil.isNotEmpty(targetCommentList)) {
                            structBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        rootBean.getStructList().add(structBean);
                    }
                    case "service" -> {
                        ServiceBean serviceBean = parseService(innerMap);
                        if (targetDoc != null) {
                            serviceBean.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (CollUtil.isNotEmpty(targetCommentList)) {
                            serviceBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        rootBean.getServiceList().add(serviceBean);
                    }
                    default -> log.error("未定义的类型{}，请检查Java代码", ruleName, new RuntimeException());
                }
            } else if ("EOI".equals(ruleName)) {
                return rootBean;
            } else {
                log.error("解析{}时发现未定义的inner类型，请检查Java代码", ruleName, new RuntimeException());
            }
        }
        return rootBean;
    }

    public static NamespaceBean parseNamespace(Map namespaceAst) {
        NamespaceBean result = new NamespaceBean();
        for (Map pairMap : (List<Map>) namespaceAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            String inner = (String) pairMap.get(INNER);
            switch (ruleName) {
                case "namespace_lang" -> result.setNamespaceLang(NamespaceBean.getEnumByName(inner));
                case "namespace_value" -> result.setNamespaceValue(inner);
            }
        }
        return result;
    }

    public static IncludeBean parseInclude(Map includeAst) {
        IncludeBean result = new IncludeBean();
        for (Map pairMap : (List<Map>) includeAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            String inner = (String) pairMap.get(INNER);
            switch (ruleName) {
                case "include_value" -> result.setIncludeValue(inner);
            }
        }
        return result;
    }

    public static CommentBean parseComment(Map commentAst) {
        for (Map pairMap : (List<Map>) commentAst.get(PAIRS)) {
            CommentBean result = new CommentBean();
            String ruleName = (String) pairMap.get(RULE);
            switch (ruleName) {
                case "comment_line" -> result.setCommentType(CommentBean.CommentTypeEnum.LINE);
                case "comment_block" -> result.setCommentType(CommentBean.CommentTypeEnum.BLOCK);
            }
            result.setCommentValue((String) pairMap.get(INNER));
            return result;
        }
        return null;
    }

    public static EnumBean parseEnum(Map enumAst) {
        //FIXME
        EnumBean result = new EnumBean();
        return result;
    }

    public static StructBean parseStruct(Map structAst) {
        StructBean result = new StructBean();
        //FIXME
        return result;
    }

    public static ServiceBean parseService(Map serviceAst) {
        ServiceBean result = new ServiceBean();
        CommentBean targetDoc = null;
        final List<CommentBean> targetCommentList = CollUtil.newArrayList();
        for (Map pairMap : (List<Map>) serviceAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            switch (ruleName) {
                case "service_name" -> result.setServiceName((String) pairMap.get(INNER));
                case "COMMENT" -> {
                    CommentBean comment = parseComment((Map) pairMap.get(INNER));
                    if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                        targetDoc = comment;
                    } else {
                        targetCommentList.add(comment);
                    }
                }
                case "service_function" -> {
                    ServiceBean.ServiceFunctionBean serviceFunction = parseServiceFunction((Map) pairMap.get(INNER));
                    if (targetDoc != null) {
                        serviceFunction.setDoc(targetDoc);
                        targetDoc = null;
                    }
                    if (!targetCommentList.isEmpty()) {
                        serviceFunction.getCommentList().addAll(targetCommentList);
                        targetCommentList.clear();
                    }
                    result.addServiceFunction(serviceFunction);
                }
            }
        }
        return result;
    }

    public static ServiceBean.ServiceFunctionBean parseServiceFunction(Map serviceFunctionAst) {
        ServiceBean.ServiceFunctionBean result = new ServiceBean.ServiceFunctionBean();
        for (Map pairMap : (List<Map>) serviceFunctionAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            switch (ruleName) {
                case "type" -> result.setReturnType(parseType((Map) pairMap.get(INNER)));
                case "service_function_name" -> result.setFunctionName((String) pairMap.get(INNER));
                case "param" -> result.addParam(parseParam((Map) pairMap.get(INNER)));
            }
        }
        return result;
    }

    public static Param parseParam(Map paramAst) {
        Param result = new Param();
        for (Map pairMap : (List<Map>) paramAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            switch (ruleName) {
                case "param_number" -> result.setParamNumber(Integer.parseInt((String) pairMap.get(INNER)));
                case "modifier" -> result.setModifier(Modifier.getEnumByName((String) pairMap.get(INNER)));
                case "type" -> result.setParamType(parseType((Map) pairMap.get(INNER)));
                case "param_name" -> result.setParamName((String) pairMap.get(INNER));
            }
        }
        return result;
    }

    public static Type parseType(Map typeAst) {
        Type result = new Type();
        for (Map pairMap : (List<Map>) typeAst.get(PAIRS)) {
            String ruleName = (String) pairMap.get(RULE);
            switch (ruleName) {
                case "intype" -> {
                    result.setIntype(true);
                    result.setJavaSimpleName(Type.INTYPE_MAP.get((String) pairMap.get(INNER)));
                }
                case "contain_map" -> {
                    result.setJavaSimpleName("Map");
                    result.setMap(true);
                    result.getImportTypeName().add(Map.class.getName());
                    Map innerAst = (Map) pairMap.get(INNER);
                    for (Map innerMap : (List<Map>) innerAst.get(PAIRS)) {
                        String innerRuleName = (String) innerMap.get(RULE);
                        if ("contain_map_keytype".equals(innerRuleName)) {
                            Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                            result.setT1(parseType((Map) lastMap.get(INNER)));
                        } else if ("contain_map_valuetype".equals(innerRuleName)) {
                            Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                            result.setT2(parseType((Map) lastMap.get(INNER)));
                        }
                    }
                }
                case "contain_list" -> {
                    result.setJavaSimpleName("List");
                    result.setCollection(true);
                    result.getImportTypeName().add(List.class.getName());
                    Map innerAst = (Map) pairMap.get(INNER);
                    for (Map innerMap : (List<Map>) innerAst.get(PAIRS)) {
                        String innerRuleName = (String) innerMap.get(RULE);
                        if ("contain_list_type".equals(innerRuleName)) {
                            Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                            result.setT1(parseType((Map) lastMap.get(INNER)));
                        }
                    }
                }
                case "contain_set" -> {
                    result.setJavaSimpleName("Set");
                    result.setCollection(true);
                    result.getImportTypeName().add(Set.class.getName());
                    Map innerAst = (Map) pairMap.get(INNER);
                    for (Map innerMap : (List<Map>) innerAst.get(PAIRS)) {
                        String innerRuleName = (String) innerMap.get(RULE);
                        if ("contain_list_type".equals(innerRuleName)) {
                            Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                            result.setT1(parseType((Map) lastMap.get(INNER)));
                        }
                    }
                }
                case "utype" -> {
                    Map innerAst = (Map) pairMap.get(INNER);
                    for (Map innerMap : (List<Map>) innerAst.get(PAIRS)) {
                        String innerRuleName = (String) innerMap.get(RULE);
                        if ("utype_namespace".equals(innerRuleName)) {
                            //FIXME 应该为Java包名
                            result.getImportTypeName().add((String) innerMap.get(INNER));
                        } else if ("utype_customname".equals(innerRuleName)) {
                            result.setJavaSimpleName((String) innerMap.get(INNER));
                        }
                    }
                }
            }
        }
        return result;
    }

    @Getter
    public static class ThriftRootBean {
        private final Map<NamespaceBean.NamespaceLangEnum, String> namespaceMap = MapUtil.newHashMap();
        private final List<CommentBean> commentList = CollUtil.newArrayList();
        private final List<IncludeBean> includeList = CollUtil.newArrayList();
        private final List<EnumBean> enumList = CollUtil.newArrayList();
        private final List<StructBean> structList = CollUtil.newArrayList();
        private final List<ServiceBean> serviceList = CollUtil.newArrayList();
    }

    @Data
    public static class NamespaceBean {
        private NamespaceLangEnum namespaceLang;
        private String namespaceValue;

        public static enum NamespaceLangEnum {
            C_GLIB,
            CL,
            DART,
            DELPHI,
            D,
            ERL,
            GO,
            GV,
            HAXE,
            HTML,
            JAVAME,
            JAVA,
            JSON,
            JS,
            KOTLIN,
            LUA,
            MARKDOWN,
            NETSTD,
            OCAML,
            PERL,
            PHP,
            PY,
            RB,
            RS,
            ST,
            SWIFT,
        }

        public static NamespaceLangEnum getEnumByName(String namespaceLang) {
            for (NamespaceLangEnum value : NamespaceLangEnum.values()) {
                if (value.name().equalsIgnoreCase(StrUtil.trim(namespaceLang))) {
                    return value;
                }
            }
            return null;
        }
    }

    @Data
    public static class IncludeBean {
        private String includeValue;
    }

    @Data
    public static class StructBean {
        private CommentBean doc;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
    }

    @Data
    public static class ServiceBean {
        private CommentBean doc;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
        private String serviceName;
        private final List<ServiceFunctionBean> serviceFunctionList = CollUtil.newArrayList();
        private final Set<String> importTypeName = new HashSet<>();

        public void addServiceFunction(ServiceFunctionBean serviceFunction) {
            importTypeName.addAll(serviceFunction.getImportTypeName());
            this.serviceFunctionList.add(serviceFunction);
        }

        @Data
        public static class ServiceFunctionBean {
            private CommentBean doc;
            private final List<CommentBean> commentList = CollUtil.newArrayList();
            private Type returnType;
            private String functionName;
            private final List<Param> paramList = CollUtil.newArrayList();
            private final Set<String> importTypeName = new HashSet<>();

            public void setReturnType(Type returnType) {
                importTypeName.addAll(returnType.getImportTypeName());
                this.returnType = returnType;
            }

            public void addParam(Param param) {
                importTypeName.addAll(param.getImportTypeName());
                paramList.add(param);
            }
        }
    }

    @Data
    public static class EnumBean {
        private CommentBean doc;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
    }

    @Data
    public static class CommentBean {
        private String commentValue;
        private CommentTypeEnum commentType;

        public static enum CommentTypeEnum {
            LINE,
            BLOCK
        }
    }

    @Data
    public static class Type {
        public static final Map<String, String> INTYPE_MAP = MapUtil.newHashMap();

        static {
            INTYPE_MAP.put("bool", "Boolean");
            INTYPE_MAP.put("byte", "Byte");
            INTYPE_MAP.put("i16", "Short");
            INTYPE_MAP.put("i32", "Integer");
            INTYPE_MAP.put("i64", "Long");
            INTYPE_MAP.put("double", "Double");
            INTYPE_MAP.put("binary", "String");
            INTYPE_MAP.put("string", "String");
        }

        @Deprecated
        private String namespace;
        private String javaSimpleName;
        private Type t1;
        private Type t2;
        private boolean isIntype = false;
        private boolean isMap = false;
        private boolean isCollection = false;
        private final Set<String> importTypeName = new HashSet<>();

        public void setT1(Type t1) {
            importTypeName.addAll(t1.getImportTypeName());
            this.t1 = t1;
        }

        public void setT2(Type t2) {
            importTypeName.addAll(t2.getImportTypeName());
            this.t2 = t2;
        }

        public String javaString() {
            if (this.isMap) {
                return javaSimpleName + "<" + t1.javaString() + ", " + t2.javaString() + ">";
            } else if (this.isCollection) {
                return javaSimpleName + "<" + t1.javaString() + ">";
            } else {
                return javaSimpleName;
            }
        }
    }

    @Data
    public static class Param {
        private Integer paramNumber;
        private Type paramType;
        private String paramName;
        private Modifier modifier;
        private final Set<String> importTypeName = new HashSet<>();

        public void setParamType(Type paramType) {
            importTypeName.addAll(paramType.getImportTypeName());
            this.paramType = paramType;
        }
    }

    public static enum Modifier {
        REQUIRED,
        OPTIONAL;

        public static Modifier getEnumByName(String name) {
            for (Modifier value : Modifier.values()) {
                if (value.name().equalsIgnoreCase(name.trim())) {
                    return value;
                }
            }
            return null;
        }
    }
}
