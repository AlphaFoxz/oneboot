package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

@Schema(name = "SdkCodeTemplateDto", description = "")
@Getter
public class SdkCodeTemplateDto {
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