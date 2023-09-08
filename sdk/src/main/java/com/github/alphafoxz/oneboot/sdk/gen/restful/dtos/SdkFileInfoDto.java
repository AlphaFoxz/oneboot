package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.alphafoxz.oneboot.sdk.gen.restful.enums.SdkFileTypeEnum;
import java.util.List;

@Schema(name = "SdkFileInfoDto", description = "")
@Data
public class SdkFileInfoDto {
    @Schema(name = "filePath", description = "")
    private String filePath;
    @Schema(name = "parentDir", description = "")
    private String parentDir;
    @Schema(name = "fileName", description = "")
    private String fileName;
    @Schema(name = "ext", description = "")
    private String ext;
    @Schema(name = "fileType", description = "")
    private SdkFileTypeEnum fileType;
    @Schema(name = "isReadOnly", description = "")
    private Boolean isReadOnly;
    @Schema(name = "isEmpty", description = "")
    private Boolean isEmpty;
    @Schema(name = "children", description = "")
    private List<SdkFileInfoDto> children;
}