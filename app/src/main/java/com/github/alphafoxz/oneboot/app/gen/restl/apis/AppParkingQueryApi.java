package com.github.alphafoxz.oneboot.app.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.service.HttpController;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.alphafoxz.oneboot.app.gen.restl.dtos.AppParkingDailyRevenueVO;
import com.github.alphafoxz.oneboot.app.gen.restl.dtos.AppParkingHistoryVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/app/query"})
@Tag(name = "AppParkingQueryApi", description = "停车业务查询接口")
public interface AppParkingQueryApi extends HttpController {
    @GetMapping(value = {"/parkingHistory"})
    @Operation(summary = "查询停车历史", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<AppParkingHistoryVO> parkingHistory();

    @GetMapping(value = {"/totalInPark"})
    @Operation(summary = "查询总停车数", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Integer> totalInPark();

    @GetMapping(value = {"/dailyRevenue"})
    @Operation(summary = "查询营业额", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<AppParkingDailyRevenueVO> dailyRevenue();

}