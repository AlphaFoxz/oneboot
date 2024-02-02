package com.github.alphafoxz.oneboot.sdk.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import org.springframework.lang.Nullable;
import com.github.alphafoxz.oneboot.sdk.gen.restl.enums.SdkFileTypeEnum;

@Schema(name = "SdkFileInfoDto", description = "")
@Accessors(chain = true)
@Getter
@Setter
public class SdkFileInfoDto {
    @Schema(name = "filePath", description = "")
    private String filePath;
    @Schema(name = "parentDir", description = "")
    private String parentDir;
    @Schema(name = "fileName", description = "")
    private String fileName;
    @Schema(name = "separator", description = "")
    private String separator;
    @Schema(name = "content", description = "")
    @Nullable
    private String content;
    @Schema(name = "ext", description = "")
    @Nullable
    private String ext;
    /**
     * @see SdkFileTypeEnum
     */
    @Schema(name = "fileType", description = "")
    private Integer fileType;
    @Schema(name = "isReadOnly", description = "")
    private Boolean isReadOnly;
    @Schema(name = "isEmpty", description = "")
    private Boolean isEmpty;
    @Schema(name = "children", description = "")
    @Nullable
    private List<SdkFileInfoDto> children;
}