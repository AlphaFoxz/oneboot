package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

@Schema(name = "SdkThriftTemplateDto", description = "")
@Getter
public class SdkThriftTemplateDto {
    @Schema(name = "filePath", description = "")
    private String filePath;
    @Schema(name = "fileSeparator", description = "")
    private String fileSeparator;
    @Schema(name = "namespace", description = "")
    private Map<String, String> namespace;
    @Schema(name = "ast", description = "")
    private String ast;
    @Schema(name = "content", description = "")
    private String content;
    @Schema(name = "includes", description = "")
    private Map<String, SdkThriftTemplateDto> includes;

    public SdkThriftTemplateDto setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    public SdkThriftTemplateDto setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
        return this;
    }
    public SdkThriftTemplateDto setNamespace(Map<String, String> namespace) {
        this.namespace = namespace;
        return this;
    }
    public SdkThriftTemplateDto setAst(String ast) {
        this.ast = ast;
        return this;
    }
    public SdkThriftTemplateDto setContent(String content) {
        this.content = content;
        return this;
    }
    public SdkThriftTemplateDto setIncludes(Map<String, SdkThriftTemplateDto> includes) {
        this.includes = includes;
        return this;
    }
}