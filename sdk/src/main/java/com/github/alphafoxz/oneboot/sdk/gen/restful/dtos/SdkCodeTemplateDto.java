package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

@Schema(name = "SdkCodeTemplateDto", description = "代码模板实体")
@Getter
public class SdkCodeTemplateDto {
    @Schema(name = "filePath", description = "文件路径")
    private String filePath;
    @Schema(name = "fileSeparator", description = "系统分隔符")
    private String fileSeparator;
    @Schema(name = "namespace", description = "命名空间")
    private Map<String, String> namespace;
    @Schema(name = "ast", description = "抽象语法树")
    private String ast;
    @Schema(name = "content", description = "文件内容")
    private String content;
    @Schema(name = "includes", description = "包含其他模板")
    private Map<String, SdkCodeTemplateDto> includes;

    public SdkCodeTemplateDto setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    public SdkCodeTemplateDto setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
        return this;
    }
    public SdkCodeTemplateDto setNamespace(Map<String, String> namespace) {
        this.namespace = namespace;
        return this;
    }
    public SdkCodeTemplateDto setAst(String ast) {
        this.ast = ast;
        return this;
    }
    public SdkCodeTemplateDto setContent(String content) {
        this.content = content;
        return this;
    }
    public SdkCodeTemplateDto setIncludes(Map<String, SdkCodeTemplateDto> includes) {
        this.includes = includes;
        return this;
    }
}