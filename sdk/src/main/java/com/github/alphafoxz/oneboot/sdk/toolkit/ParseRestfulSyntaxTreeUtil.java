package com.github.alphafoxz.oneboot.sdk.toolkit;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootApiDesignException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootGenCodeException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkCodeTemplateDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings({"unchecked", "rawtypes"})
@Slf4j
public final class ParseRestfulSyntaxTreeUtil implements RestfulTokenDefine {
    public static RestfulRootBean parseRestfulRoot(SdkCodeTemplateDto dto) {
        String filePath = dto.getFilePath();
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));

        RestfulRootBean restfulRootBean = new RestfulRootBean(dto.getImports());
        restfulRootBean.setFilePath(filePath);
        restfulRootBean.setContent(dto.getContent());
        restfulRootBean.setFileName(fileName);
        RootBean rootBean = new RootBeanBuilder(restfulRootBean, null, dto).build();
        rootBean.setName(fileName);
        restfulRootBean.setRootBean(rootBean);
        return restfulRootBean;
    }

    @Getter
    @Setter
    public static class RestfulIncludeBean implements RestfulRootIface {
        private String filePath;
        private String fileName;
        private String content;
        private final RootBean rootBean;
        private final RestfulRootBean parentRootBean;

        public RestfulIncludeBean(RestfulRootBean parentRootBean, SdkCodeTemplateDto dto) {
            filePath = dto.getFilePath();
            fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
            content = dto.getContent();
            rootBean = new RootBeanBuilder(parentRootBean, this, dto).build();
            this.parentRootBean = parentRootBean;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof RestfulIncludeBean includeBean) {
                return StrUtil.equals(this.filePath, includeBean.getFilePath());
            }
            return false;
        }
    }

    @Data
    public static class RestfulRootBean implements RestfulRootIface {
        private String filePath;
        private String fileName;
        private String content;
        private final Set<RestfulIncludeBean> includeBeanSet = CollUtil.newHashSet();
        private final Map<String, SdkCodeTemplateDto> includeDtoMap;
        private RootBean rootBean;

        public RestfulRootBean(Map<String, SdkCodeTemplateDto> includeDtoMap) {
            this.includeDtoMap = includeDtoMap;
        }

        public RestfulIncludeBean getRestfulIncludeBeanByFilePath(String filePath) {
            for (RestfulIncludeBean includeBean : includeBeanSet) {
                if (StrUtil.equals(includeBean.getFilePath(), filePath)) {
                    return includeBean;
                }
            }
            return null;
        }

        public RestfulIncludeBean getRestfulIncludeBeanByFileName(String fileName) {
            for (RestfulIncludeBean includeBean : includeBeanSet) {
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
        private final List<InterfaceBean> interfaceList = CollUtil.newArrayList();
        private final List<EnumBean> enumList = CollUtil.newArrayList();
        private final List<ClassBean> classList = CollUtil.newArrayList();

        public void addComment(CommentBean commentBean) {
            commentList.add(commentBean);
        }

        public void addImport(IncludeBean includeBean) {
            includeList.add(includeBean);
        }

        public void addNamespace(NamespaceBean namespaceBean) {
            namespaceMap.put(namespaceBean.namespaceLang, namespaceBean.namespaceValue);
        }

        public void addInterface(InterfaceBean interfaceBean) {
            interfaceList.add(interfaceBean);
        }

        public void addEnum(EnumBean enumBean) {
            enumList.add(enumBean);
        }

        public void addClass(ClassBean classBean) {
            classList.add(classBean);
        }

        public String getJavaNameSpace() {
            return namespaceMap.get(NamespaceBean.NamespaceLangEnum.JAVA);
        }

        public String getTsNameSpace() {
            return namespaceMap.get(NamespaceBean.NamespaceLangEnum.TS);
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
            TS,
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
    public static class ClassBean {
        private CommentBean doc;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
        private final Map<String, List<String>> annotationMap = MapUtil.newHashMap();
        private String className;
        private List<ClassFieldBean> classFieldList = CollUtil.newArrayList();
        private final Set<String> importTypeName = CollUtil.newHashSet();

        public void addClassField(ClassFieldBean classField) {
            importTypeName.addAll(classField.getImportTypeName());
            this.classFieldList.add(classField);
        }

        @Data
        public static class ClassFieldBean {
            private CommentBean doc;
            private final List<CommentBean> commentList = CollUtil.newArrayList();
            private Modifier modifier;
            private TypeBean type;
            private String fieldName;
            private final Set<String> importTypeName = CollUtil.newHashSet();

            public void setType(TypeBean type) {
                importTypeName.addAll(type.getImportJavaTypeName());
                this.type = type;
            }
        }
    }

    @Data
    public static class InterfaceBean {
        private CommentBean doc;
        private final List<CommentBean> commentList = CollUtil.newArrayList();
        private final Map<String, List<String>> annotationMap = MapUtil.newHashMap();
        private String interfaceName;
        private final List<InterfaceFunctionBean> interfaceFunctionList = CollUtil.newArrayList();
        private final Set<String> importTypeName = CollUtil.newHashSet();

        public void addInterfaceFunction(InterfaceFunctionBean interfaceFunction) {
            importTypeName.addAll(interfaceFunction.getImportTypeName());
            this.interfaceFunctionList.add(interfaceFunction);
        }

        @Data
        public static class InterfaceFunctionBean {
            private CommentBean doc;
            private final List<CommentBean> commentList = CollUtil.newArrayList();
            private final Map<String, List<String>> annotationMap = MapUtil.newHashMap();
            private TypeBean returnType;
            private String functionName;
            private final List<ParamBean> paramList = CollUtil.newArrayList();
            private final Set<String> importTypeName = CollUtil.newHashSet();

            public void setReturnType(TypeBean returnType) {
                importTypeName.addAll(returnType.getImportJavaTypeName());
                this.returnType = returnType;
            }

            public void addParam(ParamBean param) {
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
        private CommentTypeEnum commentType;
        private String commentValue;

        public static enum CommentTypeEnum {
            LINE,
            BLOCK,
        }
    }

    @Data
    public static class TypeBean {
        public static final Map<String, String> JAVA_INTYPE_MAP = MapUtil.newHashMap();
        public static final Map<String, String> TS_INTYPE_MAP = MapUtil.newHashMap();

        static {
            JAVA_INTYPE_MAP.put(Intypes.BOOLEAN, "Boolean");
            JAVA_INTYPE_MAP.put(Intypes.BYTE, "Byte");
            JAVA_INTYPE_MAP.put(Intypes.I16, "Short");
            JAVA_INTYPE_MAP.put(Intypes.I32, "Integer");
            JAVA_INTYPE_MAP.put(Intypes.I64, "Long");
            JAVA_INTYPE_MAP.put(Intypes.SHORT, "Short");
            JAVA_INTYPE_MAP.put(Intypes.INT, "Integer");
            JAVA_INTYPE_MAP.put(Intypes.LONG, "Long");
            JAVA_INTYPE_MAP.put(Intypes.DOUBLE, "Double");
            JAVA_INTYPE_MAP.put(Intypes.BINARY, "String");
            JAVA_INTYPE_MAP.put(Intypes.STRING, "String");

            TS_INTYPE_MAP.put(Intypes.BOOLEAN, "boolean");
            TS_INTYPE_MAP.put(Intypes.BYTE, "Blob");
            TS_INTYPE_MAP.put(Intypes.I16, "number");
            TS_INTYPE_MAP.put(Intypes.I32, "number");
            TS_INTYPE_MAP.put(Intypes.I64, "bigint");
            TS_INTYPE_MAP.put(Intypes.SHORT, "number");
            TS_INTYPE_MAP.put(Intypes.INT, "number");
            TS_INTYPE_MAP.put(Intypes.LONG, "bigint");
            TS_INTYPE_MAP.put(Intypes.DOUBLE, "number");
            TS_INTYPE_MAP.put(Intypes.BINARY, "string");
            TS_INTYPE_MAP.put(Intypes.STRING, "string");
        }

        private String javaSimpleName;
        private String tsSimpleName;
        private TypeBean t1;
        private TypeBean t2;
        private boolean isIntype = false;
        private boolean isMap = false;
        private boolean isCollection = false;
        private boolean isNullable = false;
        private final Set<String> importJavaTypeName = CollUtil.newHashSet();

        public void setT1(TypeBean t1) {
            importJavaTypeName.addAll(t1.getImportJavaTypeName());
            this.t1 = t1;
        }

        public void setT2(TypeBean t2) {
            importJavaTypeName.addAll(t2.getImportJavaTypeName());
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

        public String tsString() {
            String str;
            if (this.isMap) {
                str = "Record<" + t1.tsString() + ", " + t2.tsString() + ">";
            } else if (this.isCollection) {
                str = tsSimpleName + "[ " + t1.tsString() + " ]";
            } else {
                str = tsSimpleName;
            }
            if (this.isNullable) {
                str += " | undefined";
            }
            return str;
        }
    }

    @Data
    public static class ParamBean {
        private CommentBean doc;
        private TypeBean paramType;
        private String paramName;
        private Modifier modifier;
        private final Set<String> importTypeName = CollUtil.newHashSet();

        public void setParamType(TypeBean paramType) {
            importTypeName.addAll(paramType.getImportJavaTypeName());
            this.paramType = paramType;
        }
    }

    @Data
    public static class AnnotationBean {
        private String annotationName;
        private List<String> annotationValueList = CollUtil.newArrayList();
        private final Set<String> importTypeName = CollUtil.newHashSet();
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

        private final SdkCodeTemplateDto dto;
        private final RestfulRootBean restfulRootBean;
        private final RestfulIncludeBean restfulIncludeBean;
        private final RootBean rootBean;

        public RootBeanBuilder(RestfulRootBean restfulRootBean, RestfulIncludeBean restfulImportBean, SdkCodeTemplateDto dto) {
            this.restfulRootBean = restfulRootBean;
            this.restfulIncludeBean = restfulImportBean;
            Map<String, SdkCodeTemplateDto> namespaceMap = MapUtil.newHashMap();
            for (Map.Entry<String, SdkCodeTemplateDto> entry : dto.getImports().entrySet()) {
                String filePath = entry.getKey();
                if (!filePath.contains(File.separator) || !filePath.contains(".")) {
                    continue;
                }
                String namespace = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
                namespaceMap.put(namespace, entry.getValue());
            }
            dto.getImports().putAll(namespaceMap);
            this.dto = dto;
            rootBean = new RootBean();
        }

        private RootBean build() {
            this.parseRoot();
            return rootBean;
        }

        private void parseRoot() {
            JacksonJsonParser parser = new JacksonJsonParser();
            Map<String, Object> rootAst = parser.parseMap(dto.getAst());
            List rootPairs = (List) rootAst.get(PAIRS);
            if (rootPairs == null || rootPairs.isEmpty()) {
                throw new OnebootGenCodeException("根节点不能为空", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            final Set<String> importPackage = CollUtil.newHashSet();
            final Map<String, List<String>> annotationMap = MapUtil.newHashMap();
            for (Map pairMap : (List<Map>) rootAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                if (pairMap.get(INNER) instanceof Map innerMap) {
                    switch (ruleName) {
                        case COMMENT -> {
                            CommentBean comment = parseComment(innerMap);
                            if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                                targetDoc = comment;
                            } else {
                                targetCommentList.add(comment);
                            }
                        }
                        case ANNOTATION -> {
                            AnnotationBean anno = parseAnnotation(innerMap);
                            importPackage.addAll(anno.getImportTypeName());
                            annotationMap.put(anno.getAnnotationName(), anno.getAnnotationValueList());
                        }
                        case IMPORT -> {
                            targetDoc = null;
                            targetCommentList.clear();
                            importPackage.clear();
                            annotationMap.clear();
                            rootBean.addImport(parseImport(innerMap));
                        }
                        case NAMESPACE -> {
                            targetDoc = null;
                            targetCommentList.clear();
                            importPackage.clear();
                            annotationMap.clear();
                            rootBean.addNamespace(parseNamespace(innerMap));
                        }
                        case ENUM -> {
                            EnumBean enumBean = parseEnum(innerMap);
                            if (targetDoc != null) {
                                enumBean.setDoc(targetDoc);
                                targetDoc = null;
                            }
                            enumBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                            importPackage.clear();
                            annotationMap.clear();
                            rootBean.addEnum(enumBean);
                        }
                        case CLASS -> {
                            ClassBean classBean = parseClass(innerMap);
                            if (targetDoc != null) {
                                classBean.setDoc(targetDoc);
                                targetDoc = null;
                            }
                            classBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                            classBean.getImportTypeName().addAll(importPackage);
                            importPackage.clear();
                            classBean.getAnnotationMap().putAll(annotationMap);
                            annotationMap.clear();
                            rootBean.addClass(classBean);
                        }
                        case INTERFACE -> {
                            InterfaceBean interfaceBean = parseInterface(innerMap);
                            if (targetDoc != null) {
                                interfaceBean.setDoc(targetDoc);
                                targetDoc = null;
                            }
                            interfaceBean.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                            interfaceBean.getImportTypeName().addAll(importPackage);
                            importPackage.clear();
                            interfaceBean.getAnnotationMap().putAll(annotationMap);
                            annotationMap.clear();
                            rootBean.addInterface(interfaceBean);
                        }
                        default -> {
                            String msg = StrUtil.format("未定义的类型{}，请检查Java代码", ruleName);
                            log.error(msg);
                            throw new OnebootApiDesignException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                } else if (EOI.equals(ruleName)) {
                    return;
                } else {
                    String msg = StrUtil.format("解析{}时发现未定义的inner类型，请检查Java代码", ruleName);
                    log.error(msg);
                    throw new OnebootApiDesignException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }


        public NamespaceBean parseNamespace(Map namespaceAst) {
            NamespaceBean result = new NamespaceBean();
            for (Map pairMap : (List<Map>) namespaceAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                String inner = (String) pairMap.get(INNER);
                switch (ruleName) {
                    case NAMESPACE_LANG -> result.setNamespaceLang(NamespaceBean.getEnumByName(inner));
                    case NAMESPACE_VALUE -> result.setNamespaceValue(inner);
                }
            }
            return result;
        }

        public IncludeBean parseImport(Map includeAst) {
            IncludeBean result = new IncludeBean();
            for (Map pairMap : (List<Map>) includeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                String inner = (String) pairMap.get(INNER);
                if (ruleName.equals(IMPORT_VALUE)) {
                    result.setIncludeValue(inner);
                }
            }
            return result;
        }

        public CommentBean parseComment(Map commentAst) {
            for (Map pairMap : (List<Map>) commentAst.get(PAIRS)) {
                CommentBean result = new CommentBean();
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case COMMENT_LINE -> {
                        result.setCommentType(CommentBean.CommentTypeEnum.LINE);
                        result.setCommentValue((String) pairMap.get(INNER));
                    }
                    case COMMENT_BLOCK -> {
                        result.setCommentType(CommentBean.CommentTypeEnum.BLOCK);
                        result.setCommentValue((String) pairMap.get(INNER));
                    }
                }
                return result;
            }
            throw new OnebootGenCodeException("非预期的注解：\n" + JSONUtil.toJsonStr(commentAst), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        public AnnotationBean parseAnnotation(Map annoAst) {
            AnnotationBean result = new AnnotationBean();
            for (Map annoPair : (List<Map>) annoAst.get(PAIRS)) {
                String annoRule = (String) annoPair.get(RULE);
                switch (annoRule) {
                    case ANNOTATION_NAME -> {
                        String annoName = (String) annoPair.get(INNER);
                        if (StrUtil.equalsIgnoreCase(annoName, "uri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.RequestMapping");
                            result.setAnnotationName("RequestMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "postUri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.PostMapping");
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.RequestBody");
                            result.setAnnotationName("PostMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "getUri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.GetMapping");
                            result.setAnnotationName("GetMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "putUri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.PutMapping");
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.RequestBody");
                            result.setAnnotationName("PutMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "patchUri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.PatchMapping");
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.RequestBody");
                            result.setAnnotationName("PatchMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "deleteUri")) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.DeleteMapping");
                            result.setAnnotationName("DeleteMapping");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "page")) { // 分页查询
                            result.getImportTypeName().add("org.springframework.data.domain.Page");
                            result.setAnnotationName("Page");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "request")) { //注入request
                            result.getImportTypeName().add("jakarta.servlet.http.HttpServletRequest");
                            result.setAnnotationName("HttpServletRequest");
                        } else if (StrUtil.equalsIgnoreCase(annoName, "response")) { //注入response
                            result.getImportTypeName().add("jakarta.servlet.http.HttpServletResponse");
                            result.setAnnotationName("HttpServletResponse");
                        } else {
                            result.setAnnotationName(annoName);
                            log.error("未预料的注解");
                        }
                    }
                    case ANNOTATION_VALUE -> {
                        String value = (String) annoPair.get(INNER);
                        if (StrUtil.isBlank(value)) {
                            continue;
                        }
                        if (ReUtil.contains("\\{\\s*\\w+\\s*}", value)) {
                            result.getImportTypeName().add("org.springframework.web.bind.annotation.PathVariable");
                        }
                        for (String v : StrUtil.split(value, ",")) {
                            result.getAnnotationValueList().add(v);
                        }
                    }
                }
            }
            return result;
        }

        public EnumBean parseEnum(Map enumAst) {
            EnumBean result = new EnumBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            for (Map pairMap : (List<Map>) enumAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case COMMENT -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case ENUM_NAME -> {
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
                    case ENUM_INSTANCE -> {
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
                    case ENUM_INSTANCE_CONSTANT ->
                            result.setInstanceConstant(Integer.parseInt((String) pairMap.get(INNER)));
                    case ENUM_INSTANCE_NAME -> result.setInstanceName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public ClassBean parseClass(Map structAst) {
            ClassBean result = new ClassBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            for (Map pairMap : (List<Map>) structAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case COMMENT -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case CLASS_NAME -> {
                        if (targetDoc != null) {
                            result.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            result.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.setClassName((String) pairMap.get(INNER));
                    }
                    case CLASS_FIELD -> {
                        ClassBean.ClassFieldBean structAttribute = parseStructAttribute((Map) pairMap.get(INNER));
                        if (targetDoc != null) {
                            structAttribute.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        if (!targetCommentList.isEmpty()) {
                            structAttribute.getCommentList().addAll(targetCommentList);
                            targetCommentList.clear();
                        }
                        result.addClassField(structAttribute);
                    }
                }
            }
            return result;
        }

        public ClassBean.ClassFieldBean parseStructAttribute(Map structAttributeAst) {
            ClassBean.ClassFieldBean result = new ClassBean.ClassFieldBean();
            for (Map pairMap : (List<Map>) structAttributeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case MODIFIER -> result.setModifier(Modifier.getEnumByName((String) pairMap.get(INNER)));
                    case CONTAIN_LIST -> result.setType(parseContainList((Map) pairMap.get(INNER)));
                    case CONTAIN_MAP -> result.setType(parseContainMap((Map) pairMap.get(INNER)));
                    case CONTAIN_SET -> result.setType(parseContainSet((Map) pairMap.get(INNER)));
                    case INTYPE -> result.setType(parseIntype((String) pairMap.get(INNER)));
                    case UTYPE -> result.setType(parseUtType((Map) pairMap.get(INNER)));
                    case CLASS_FIELD_NAME -> result.setFieldName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public InterfaceBean parseInterface(Map serviceAst) {
            InterfaceBean result = new InterfaceBean();
            CommentBean targetDoc = null;
            final List<CommentBean> targetCommentList = CollUtil.newArrayList();
            final Set<String> functionImportPackage = CollUtil.newHashSet();
            final Map<String, List<String>> annoMap = MapUtil.newHashMap();
            for (Map pairMap : (List<Map>) serviceAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case COMMENT -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            targetDoc = comment;
                        } else {
                            targetCommentList.add(comment);
                        }
                    }
                    case ANNOTATION -> {
                        AnnotationBean anno = parseAnnotation((Map) pairMap.get(INNER));
                        result.getImportTypeName().addAll(anno.getImportTypeName());
                        annoMap.put(anno.getAnnotationName(), anno.getAnnotationValueList());
                    }
                    case INTERFACE_NAME -> result.setInterfaceName((String) pairMap.get(INNER));
                    case INTERFACE_FUNCTION -> {
                        InterfaceBean.InterfaceFunctionBean serviceFunction = parseServiceFunction((Map) pairMap.get(INNER));
                        if (targetDoc != null) {
                            serviceFunction.setDoc(targetDoc);
                            targetDoc = null;
                        }
                        serviceFunction.getCommentList().addAll(targetCommentList);
                        targetCommentList.clear();
                        serviceFunction.getImportTypeName().addAll(functionImportPackage);
                        functionImportPackage.clear();
                        serviceFunction.getAnnotationMap().putAll(annoMap);
                        annoMap.clear();
                        result.addInterfaceFunction(serviceFunction);
                    }
                }
            }
            return result;
        }

        public InterfaceBean.InterfaceFunctionBean parseServiceFunction(Map serviceFunctionAst) {
            InterfaceBean.InterfaceFunctionBean result = new InterfaceBean.InterfaceFunctionBean();
            CommentBean doc = null;
            for (Map pairMap : (List<Map>) serviceFunctionAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case TYPE -> result.setReturnType(Objects.requireNonNull(parseType((Map) pairMap.get(INNER))));
                    case VOID -> { // 没有返回值
                        TypeBean returnType = new TypeBean();
                        returnType.setJavaSimpleName("void");
                        returnType.setTsSimpleName("null");
                        result.setReturnType(returnType);
                    }
                    case INTERFACE_FUNCTION_NAME -> result.setFunctionName((String) pairMap.get(INNER));
                    case COMMENT -> {
                        CommentBean comment = parseComment((Map) pairMap.get(INNER));
                        if (CommentBean.CommentTypeEnum.BLOCK.equals(comment.getCommentType())) {
                            doc = comment;
                        }
                    }
                    case PARAM -> {
                        result.getImportTypeName().add("io.swagger.v3.oas.annotations.Parameter");
                        ParamBean param = parseParam((Map) pairMap.get(INNER));
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

        public ParamBean parseParam(Map paramAst) {
            ParamBean result = new ParamBean();
            for (Map pairMap : (List<Map>) paramAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case MODIFIER -> result.setModifier(Modifier.getEnumByName((String) pairMap.get(INNER)));
                    case TYPE -> result.setParamType(Objects.requireNonNull(parseType((Map) pairMap.get(INNER))));
                    case PARAM_NAME -> result.setParamName((String) pairMap.get(INNER));
                }
            }
            return result;
        }

        public TypeBean parseType(Map typeAst) {
            for (Map pairMap : (List<Map>) typeAst.get(PAIRS)) {
                String ruleName = (String) pairMap.get(RULE);
                switch (ruleName) {
                    case INTYPE -> {
                        return parseIntype((String) pairMap.get(INNER));
                    }
                    case CONTAIN_MAP -> {
                        return parseContainMap((Map) pairMap.get(INNER));
                    }
                    case CONTAIN_LIST -> {
                        return parseContainList((Map) pairMap.get(INNER));
                    }
                    case CONTAIN_SET -> {
                        return parseContainSet((Map) pairMap.get(INNER));
                    }
                    case UTYPE -> {
                        return parseUtType((Map) pairMap.get(INNER));
                    }
                }
            }
            return null;
        }

        public TypeBean parseIntype(String intypeString) {
            TypeBean result = new TypeBean();
            result.setIntype(true);
            result.setJavaSimpleName(TypeBean.JAVA_INTYPE_MAP.get(intypeString));
            result.setTsSimpleName(TypeBean.TS_INTYPE_MAP.get(intypeString));
            return result;
        }

        public TypeBean parseContainMap(Map ast) {
            TypeBean result = new TypeBean();
            result.setJavaSimpleName("Map");
            result.setTsSimpleName("Record");
            result.setMap(true);
            result.getImportJavaTypeName().add(Map.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if (CONTAIN_MAP_KEYTYPE.equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                } else if (CONTAIN_MAP_VALUETYPE.equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT2(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public TypeBean parseContainList(Map ast) {
            TypeBean result = new TypeBean();
            result.setJavaSimpleName("List");
            result.setTsSimpleName("");
            result.setCollection(true);
            result.getImportJavaTypeName().add(List.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if (CONTAIN_LIST_TYPE.equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public TypeBean parseContainSet(Map ast) {
            TypeBean result = new TypeBean();
            result.setJavaSimpleName("Set");
            result.setTsSimpleName("");
            result.setCollection(true);
            result.getImportJavaTypeName().add(Set.class.getName());
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if (CONTAIN_SET_TYPE.equals(innerRuleName)) {
                    Map lastMap = (Map) ((List) ((Map) innerMap.get(INNER)).get(PAIRS)).get(0);
                    result.setT1(Objects.requireNonNull(parseType((Map) lastMap.get(INNER))));
                }
            }
            return result;
        }

        public TypeBean parseUtType(Map ast) {
            TypeBean result = new TypeBean();
            String importJavaPackage = null;
            String importTsTypeName = "";
            for (Map innerMap : (List<Map>) ast.get(PAIRS)) {
                String innerRuleName = (String) innerMap.get(RULE);
                if (UTYPE_NAMESPACE.equals(innerRuleName)) {
                    String namespace = (String) innerMap.get(INNER);
                    importTsTypeName += namespace + ".";
                    RestfulIncludeBean restfulIncludeBean = restfulRootBean.getRestfulIncludeBeanByFileName(namespace);
                    if (restfulIncludeBean == null) {
                        SdkCodeTemplateDto includeDto = restfulRootBean.getIncludeDtoMap().get(namespace);
                        restfulIncludeBean = new RestfulIncludeBean(restfulRootBean, includeDto);
                        restfulRootBean.getIncludeBeanSet().add(restfulIncludeBean);
                    }
                    String javaPackageName = restfulIncludeBean.getRootBean().getJavaNameSpace();
                    if (javaPackageName == null) {
                        throw new OnebootGenCodeException("import数据类型有误，请检查", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    importJavaPackage = javaPackageName;
                } else if (UTYPE_CUSTOMNAME.equals(innerRuleName)) {
                    String includeValue = (String) innerMap.get(INNER);
                    importTsTypeName += includeValue;
                    if (importJavaPackage != null) {
                        result.getImportJavaTypeName().add(importJavaPackage + "." + includeValue);
                    }
                    result.setJavaSimpleName(includeValue);
//                    注释，在java中同一个包的不同类之间不需要import
//                    if (importJavaPackage != null) {
//                        importJavaPackage = rootBean.getJavaNameSpace();
//                    }

                }
            }
            result.setTsSimpleName(importTsTypeName);
            return result;
        }
    }

    public interface RestfulRootIface {
        RootBean getRootBean();

        String getFileName();
    }
}
