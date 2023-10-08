package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.enums.SdkFileTypeEnum;
import java.util.List;

@Schema(name = "SdkFileInfoDto", description = "")
@Getter
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

    public SdkFileInfoDto setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    public SdkFileInfoDto setParentDir(String parentDir) {
        this.parentDir = parentDir;
        return this;
    }
    public SdkFileInfoDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
    public SdkFileInfoDto setExt(String ext) {
        this.ext = ext;
        return this;
    }
    public SdkFileInfoDto setFileType(SdkFileTypeEnum fileType) {
        this.fileType = fileType;
        return this;
    }
    public SdkFileInfoDto setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        return this;
    }
    public SdkFileInfoDto setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
        return this;
    }
    public SdkFileInfoDto setChildren(List<SdkFileInfoDto> children) {
        this.children = children;
        return this;
    }
}