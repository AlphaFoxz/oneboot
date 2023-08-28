package com.github.alphafoxz.oneboot.sdk.toolkit;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.MapUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkThriftTemplateDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.File;
import java.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
@Slf4j
public final class ParseThriftSyntaxTreeUtil {
    public static ThriftRootBean parseThriftRoot(SdkThriftTemplateDto dto) throws TException {
        String filePath = dto.getFilePath();
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));

        ThriftRootBean thriftRootBean = new ThriftRootBean(dto.getIncludes());
        thriftRootBean.setFilePath(filePath);
        thriftRootBean.setContent(dto.getContent());
        thriftRootBean.setFileName(fileName);
        RootBean rootBean = new RootBeanBuilder(thriftRootBean, null, dto).build();
        rootBean.setName(fileName);
        thriftRootBean.setRootBean(rootBean);
        return thriftRootBean;
    }

    @Data
    public static class ThriftIncludeBean implements ThriftRootIface {
        private String filePath;
        private String fileName;
        private String content;
        private final RootBean rootBean;
        private final ThriftRootBean parentRootBean;

        public ThriftIncludeBean(ThriftRootBean parentRootBean, SdkThriftTemplateDto dto) throws TException {
            filePath = dto.getFilePath();
            fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
            content = dto.getContent();
            rootBean = new RootBeanBuilder(parentRootBean, this, dto).build();
            this.parentRootBean = parentRootBean;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ThriftIncludeBean includeBean) {
                return StrUtil.equals(this.filePath, includeBean.getFilePath());
            }
            return false;
        }
    }

    @Data
    public static class ThriftRootBean implements ThriftRootIface {
        private String filePath;
        private String fileName;
        private String content;
        private final Set<ThriftIncludeBean> includeBeanSet = CollUtil.newHashSet();
        private final Map<String, SdkThriftTemplateDto> includeDtoMap;
        private RootBean rootBean;

        public ThriftRootBean(Map<String, SdkThriftTemplateDto> includeDtoMap) {
            this.includeDtoMap = includeDtoMap;
        }

        public ThriftIncludeBean getThriftIncludeBeanByFilePath(String filePath) {
            for (ThriftIncludeBean includeBean : includeBeanSet) {
                if (StrUtil.equals(includeBean.getFilePath(), filePath)) {
                    return includeBean;
                }
            }
            return null;
        }

        public ThriftIncludeBean getThriftIncludeBeanByFileName(String fileName) {
            for (ThriftIncludeBean includeBean : includeBeanSet) {
                if (StrUtil.equals(includeBean.getFileName(), fileName)) {
                    return includeBean;
                }
            }
            return null;
        }
    }

    @Data
    public static class RootBean {
        private String name;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
        private final List<IncludeBean> includeList = CollUtil.newArrayList();
        private final Map<NamespaceBean.NamespaceLangEnum, String> namespaceMap = MapUtil.newHashMap();
        private final List<ServiceBean> serviceList = CollUtil.newArrayList();
        private final List<EnumBean> enumList = CollUtil.newArrayList();
        private final List<StructBean> structList = CollUtil.newArrayList();

        public void addComment(CommentBean commentBean) {
            commentList.add(commentBean);
        }

        public void addInclude(IncludeBean includeBean) {
            includeList.add(includeBean);
        }

        public void addNamespace(NamespaceBean namespaceBean) {
            namespaceMap.put(namespaceBean.namespaceLang, namespaceBean.namespaceValue);
        }

        public void addService(ServiceBean serviceBean) {
            serviceList.add(serviceBean);
        }

        public void addEnum(EnumBean enumBean) {
            enumList.add(enumBean);
        }

        public void addStruct(StructBean structBean) {
            structList.add(structBean);
        }

        public String getJavaNameSpace() {
            return namespaceMap.get(NamespaceBean.NamespaceLangEnum.JAVA);
        }
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
        private String structName;
        private List<StructAttributeBean> structAttribute = CollUtil.newArrayList();
        private final Set<String> importTypeName = new HashSet<>();

        public void addStructAttribute(StructAttributeBean structAttribute) {
            importTypeName.addAll(structAttribute.getImportTypeName());
            this.structAttribute.add(structAttribute);
        }

        @Data
        public static class StructAttributeBean {
            private CommentBean doc;
            private final List<CommentBean> commentList = CollUtil.newArrayList();
            private Integer attributeConstant;
            private Modifier modifier;
            private Type type;
            private String attributeName;
            private final Set<String> importTypeName = new HashSet<>();

            public void setType(Type type) {
                importTypeName.addAll(type.getImportTypeName());
                this.type = type;
            }
        }
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
        private String enumName;
        private List<EnumInstance> enumInstance = CollUtil.newArrayList();

        @Data
        public static class EnumInstance {
            private CommentBean doc;
            private final List<CommentBean> commentList = CollUtil.newArrayList();
            private String instanceName;
            private Integer instanceConstant;
        }
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
        private CommentBean doc;
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

    private static class RootBeanBuilder {
        private static final String PAIRS = "pairs";
        private static final String RULE = "rule";
        private static final String INNER = "inner";

        private final SdkThriftTemplateDto dto;
        private final ThriftRootBean thriftRootBean;
        private final ThriftIncludeBean thriftIncludeBean;
        private final RootBean rootBean;

        public RootBeanBuilder(ThriftRootBean thriftRootBean, ThriftIncludeBean thriftIncludeBean, SdkThriftTemplateDto dto) {
            this.thriftRootBean = thriftRootBean;
            this.thriftIncludeBean = thriftIncludeBean;
            Map<String, SdkThriftTemplateDto> namespaceMap = MapUtil.newHashMap();
            for (Map.Entry<String, SdkThriftTemplateDto> entry : dto.getIncludes().entrySet()) {
                String filePath = entry.getKey();
                if (!filePath.contains(File.separator) || !filePath.contains(".")) {
                    continue;
                }
                String namespace = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
                namespaceMap.put(namespace, entry.getValue());
            }
            dto.getIncludes().putAll(namespaceMap);
            this.dto = dto;
            rootBean = new RootBean();
        }

        private RootBean build() throws TException {
            this.parseRoot();
            return rootBean;
        }

        private void parseRoot() throws TException {
            JacksonJsonParser parser = new JacksonJsonParser();
            Map<String, Object> rootAst = parser.parseMap(dto.getAst());
            List rootPairs = (List) rootAst.get(PAIRS);
            if (rootPairs == null || rootPairs.isEmpty()) {
                throw new TException("根节点不能为空");
            }
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
                            rootBean.addInclude(parseInclude(innerMap));
                        }
                        case "namespace" -> {
                            targetDoc = null;
                            targetCommentList.clear();
                            rootBean.addNamespace(parseNamespace(innerMap));
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
                            rootBean.addEnum(enumBean);
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
                            rootBean.addStruct(structBean);
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
                            rootBean.addService(serviceBean);
                        }
                        default -> log.error("未定义的类型{}，请检查Java代码", ruleName, new RuntimeException());
                    }
                } else if ("EOI".equals(ruleName)) {
                    return;
                } else {
                    log.error("解析{}时发现未定义的inner类型，请检查Java代码", ruleName, new RuntimeException());
                }
            }
        }


        public NamespaceBean parseNamespace(Map namespaceAst) {
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

        public IncludeBean parseInclude(Map includeAst) {
            IncludeBean result = new IncludeBean();
            for (Map pairMap : (List<Map>) includeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                String inner = (String) pairMap.get(INNER);
                if (ruleName.equals("include_value")) {
                    result.setIncludeValue(inner);
                }
            }
            return result;
        }

        public CommentBean parseComment(Map commentAst) throws TException {
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
            throw new TException("非预期的注解：\n" + JSONUtil.toJsonStr(commentAst));
        }

        public EnumBean parseEnum(Map enumAst) throws TException {
            EnumBean result = new EnumBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            for (Map pairMap : (List<Map>) enumAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "COMMENT" -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case "enum_name" -> {
                        if (targetDoc != null) {
                            result.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            result.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.setEnumName((String) pairMap.get(INNER));
                    }
                    case "enum_instance" -> {
                        EnumBean.EnumInstance enumInstance = parseEnumInstance((Map) pairMap.get(INNER));
                        if (targetDoc != null) {
                            enumInstance.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            enumInstance.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.getEnumInstance().add(enumInstance);
                    }
                }
            }
            return result;
        }

        public EnumBean.EnumInstance parseEnumInstance(Map enumInstanceAst) {
            EnumBean.EnumInstance result = new EnumBean.EnumInstance();
            for (Map pairMap : (List<Map>) enumInstanceAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "enum_instance_constant" ->
                            result.setInstanceConstant(Integer.parseInt((String) pairMap.get(INNER)));
                    case "enum_instance_name" -> result.setInstanceName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public StructBean parseStruct(Map structAst) throws TException {
            StructBean result = new StructBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            for (Map pairMap : (List<Map>) structAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "COMMENT" -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case "struct_name" -> {
                        if (targetDoc != null) {
                            result.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            result.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.setStructName((String) pairMap.get(INNER));
                    }
                    case "struct_attribute" -> {
                        StructBean.StructAttributeBean structAttribute = parseStructAttribute((Map) pairMap.get(INNER));
                        if (targetDoc != null) {
                            structAttribute.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            structAttribute.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.addStructAttribute(structAttribute);
                    }
                }
            }
            return result;
        }

        public StructBean.StructAttributeBean parseStructAttribute(Map structAttributeAst) throws TException {
            StructBean.StructAttributeBean result = new StructBean.StructAttributeBean();
            for (Map pairMap : (List<Map>) structAttributeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "struct_attribute_constant" ->
                            result.setAttributeConstant(Integer.parseInt((String) pairMap.get(INNER)));
                    case "modifier" -> result.setModifier(Modifier.getEnumByName((String) pairMap.get(INNER)));
                    case "contain_list" -> result.setType(parseContainList((Map) pairMap.get(INNER)));
                    case "contain_map" -> result.setType(parseContainMap((Map) pairMap.get(INNER)));
                    case "contain_set" -> result.setType(parseContainSet((Map) pairMap.get(INNER)));
                    case "intype" -> result.setType(parseInType((String) pairMap.get(INNER)));
                    case "utype" -> result.setType(parseUtType((Map) pairMap.get(INNER)));
                    case "struct_attribute_name" -> result.setAttributeName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public ServiceBean parseService(Map serviceAst) throws TException {
            ServiceBean result = new ServiceBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            for (Map pairMap : (List<Map>) serviceAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "COMMENT" -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case "service_name" -> result.setServiceName((String) pairMap.get(INNER));
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

        public ServiceBean.ServiceFunctionBean parseServiceFunction(Map serviceFunctionAst) throws TException {
            ServiceBean.ServiceFunctionBean result = new ServiceBean.ServiceFunctionBean();
            CommentBean doc = null;
            for (Map pairMap : (List<Map>) serviceFunctionAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "type" -> result.setReturnType(Objects.requireNonNull(parseType((Map) pairMap.get(INNER))));
                    case "void" -> {
                        Type returnType = new Type();
                        returnType.setJavaSimpleName("void");
                        result.setReturnType(returnType);
                    }
                    case "service_function_name" -> result.setFunctionName((String) pairMap.get(INNER));
                    case "COMMENT" -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            doc = comment;
                        }
                    }
                    case "param" -> {
                        result.getImportTypeName().add("io.swagger.v3.oas.annotations.Parameter");
                        Param param = parseParam((Map) pairMap.get(INNER));
                        if (doc != null) {
                            param.setDoc(doc);
                        }
                        doc = null;
                        result.addParam(param);
                    }
                }
            }
            return result;
        }

        public Param parseParam(Map paramAst) throws TException {
            Param result = new Param();
            for (Map pairMap : (List<Map>) paramAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "param_number" -> result.setParamNumber(Integer.parseInt((String) pairMap.get(INNER)));
                    case "modifier" -> result.setModifier(Modifier.getEnumByName((String) pairMap.get(INNER)));
                    case "type" -> result.setParamType(Objects.requireNonNull(parseType((Map) pairMap.get(INNER))));
                    case "param_name" -> result.setParamName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public Type parseType(Map typeAst) throws TException {
            for (Map pairMap : (List<Map>) typeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case "intype" -> {
                        return parseInType((String) pairMap.get(INNER));
                    }
                    case "contain_map" -> {
                        return parseContainMap((Map) pairMap.get(INNER));
                    }
                    case "contain_list" -> {
                        return parseContainList((Map) pairMap.get(INNER));
                    }
                    case "contain_set" -> {
                        return parseContainSet((Map) pairMap.get(INNER));
                    }
                    case "utype" -> {
                        return parseUtType((Map) pairMap.get(INNER));
                    }
                }
            }
            return null;
        }

        public Type parseInType(String inner) {
            Type result = new Type();
            result.setIntype(true);
            result.setJavaSimpleName(Type.INTYPE_MAP.get(inner));
            return result;
        }

        public Type parseContainMap(Map ast) throws TException {
            Type result = new Type();
            result.setJavaSimpleName("Map");
            result.setMap(true);
            result.getImportTypeName().add(Map.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if ("contain_map_keytype".equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                } else if ("contain_map_valuetype".equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT2(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public Type parseContainList(Map ast) throws TException {
            Type result = new Type();
            result.setJavaSimpleName("List");
            result.setCollection(true);
            result.getImportTypeName().add(List.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if ("contain_list_type".equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public Type parseContainSet(Map ast) throws TException {
            Type result = new Type();
            result.setJavaSimpleName("Set");
            result.setCollection(true);
            result.getImportTypeName().add(Set.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if ("contain_list_type".equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public Type parseUtType(Map ast) throws TException {
            Type result = new Type();
            String importPackage = null;
            String importClass = null;
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if ("utype_namespace".equals(innerRuleName)) {
                    String namespace = (String) innerMap.get(INNER);
                    ThriftIncludeBean thriftIncludeBean = thriftRootBean.getThriftIncludeBeanByFileName(namespace);
                    if (thriftIncludeBean == null) {
                        SdkThriftTemplateDto includeDto = thriftRootBean.getIncludeDtoMap().get(namespace);
                        thriftIncludeBean = new ThriftIncludeBean(thriftRootBean, includeDto);
                        thriftRootBean.getIncludeBeanSet().add(thriftIncludeBean);
                    }
                    String packageName = thriftIncludeBean.getRootBean().getJavaNameSpace();
                    if (packageName == null) {
                        throw new TException("import数据类型有误，请检查");
                    }
                    importPackage = packageName;
                } else if ("utype_customname".equals(innerRuleName)) {
                    String includeValue = (String) innerMap.get(INNER);
                    result.setJavaSimpleName(includeValue);
//                    注释，在java中同一个包的不同类之间不需要import
//                    if (importPackage != null) {
//                        importPackage = rootBean.getJavaNameSpace();
//                    }
                    if (importPackage != null) {
                        result.getImportTypeName().add(importPackage + "." + includeValue);
                    }
                }
            }
            return result;
        }
    }

    public interface ThriftRootIface {
        RootBean getRootBean();
    }
}
