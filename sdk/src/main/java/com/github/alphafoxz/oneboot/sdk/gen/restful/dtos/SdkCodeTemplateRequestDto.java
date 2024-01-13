package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto;

@Schema(name = "SdkCodeTemplateRequestDto", description = "")
@Accessors(chain = true)
@Getter
@Setter
public class SdkCodeTemplateRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private SdkCodeTemplateDto data;
}