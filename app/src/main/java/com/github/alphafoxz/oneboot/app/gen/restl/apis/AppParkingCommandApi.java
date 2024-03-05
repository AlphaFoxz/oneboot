package com.github.alphafoxz.oneboot.app.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.service.HttpController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/app/command"})
@Tag(name = "AppParkingCommandApi", description = "停车业务命令接口")
public interface AppParkingCommandApi extends HttpController {
    @PostMapping(value = {"/checkIn"})
    @Operation(summary = "入场请求", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public default ResponseEntity<Boolean> checkIn(
        @RequestBody java.util.Map<String, Object> _requestMap
    ) {
        return checkIn((String) _requestMap.get("plate"), (String) _requestMap.get("time"));
    }

    public ResponseEntity<Boolean> checkIn(
            String plate,
            String time
    );

    @PostMapping(value = {"/checkOut"})
    @Operation(summary = "出场请求", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public default ResponseEntity<Boolean> checkOut(
        @RequestBody java.util.Map<String, Object> _requestMap
    ) {
        return checkOut((String) _requestMap.get("plate"), (String) _requestMap.get("time"));
    }

    public ResponseEntity<Boolean> checkOut(
            String plate,
            String time
    );

    @PostMapping(value = {"/notifyPay"})
    @Operation(summary = "告知付款请求", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public default ResponseEntity<Boolean> notifyPay(
        @RequestBody java.util.Map<String, Object> _requestMap
    ) {
        return notifyPay((String) _requestMap.get("plate"), (String) _requestMap.get("time"), (Integer) _requestMap.get("amount"));
    }

    public ResponseEntity<Boolean> notifyPay(
            String plate,
            String time,
            Integer amount
    );

}