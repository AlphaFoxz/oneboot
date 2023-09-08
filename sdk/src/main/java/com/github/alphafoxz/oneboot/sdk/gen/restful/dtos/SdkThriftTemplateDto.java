package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Map;

@Schema(name = "SdkThriftTemplateDto", description = "")
@Data
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
}