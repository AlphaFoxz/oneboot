package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkInfoApi;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import com.github.alphafoxz.oneboot.sdk.service.common.SdkRestfulConvertor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SdkInfoController implements SdkInfoApi {
    @Resource
    private SdkInfoService sdkInfoService;
    @Resource
    private Snowflake snowflake;

    @GetMapping(value = {"", "/index"})
    public String index() {
        return """
                <html>
                <head>
                    <meta charset="utf-8">
                    <title>SDK工具简易导航</title>
                </head>
                <body>
                    <h1>SDK工具简易导航</h1>
                    <h2>状态检查</h2>
                    <div>
                        <a href="/_sdk/info/rootPath" target="_blank">项目根路径</a>
                    </div>
                    <div>
                        <a href="/_sdk/info/checkThriftErr" target="_blank">检查thrift基本功能</a>
                    </div>
                    <div>
                        <a href="/_sdk/info/checkRestApiImplements" target="_blank">检查restfulAPI实现情况</a>
                    </div>
                    <div>
                        <a href="/_sdk/info/checkRpcImplements" target="_blank">检查RPC接口实现情况</a>
                    </div>
                    <div>
                        <a href="/_sdk/thrift/getServerPort" target="_blank">获取当前RPC服务的端口</a>
                    </div>
                    <div>
                        <a href="/_sdk/thrift/getExecutableFilePath" target="_blank">获取thrift可执行文件路径</a>
                    </div>
                    <h2>生成代码</h2>
                    <div>
                        <a href="/_sdk/genCode/generateJavaRpc" target="_blank">生成所有rpc代码</a>
                    </div>
                </body>
                </html>
                """;
    }

    @Override
    public ResponseEntity<SdkStringResponseDto> rootPath() {
        SdkStringResponseDto result = new SdkStringResponseDto();
        result.setId(snowflake.nextId());
        result.setTaskId(snowflake.nextId());
        result.setSuccess(true);
        result.setData(SdkConstants.PROJECT_ROOT_PATH);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<SdkListResponseDto> checkThriftErr() {
        return ResponseEntity.ok(SdkRestfulConvertor.INSTANCE.fromThriftSdkListResponseDto(sdkInfoService.checkThriftErr()));
    }

    @Override
    public ResponseEntity<SdkListResponseDto> checkRestApiImplements() {
        try {
            return ResponseEntity.ok(SdkRestfulConvertor.INSTANCE.fromThriftSdkListResponseDto(sdkInfoService.checkRestApiImplements()));
        } catch (Exception e) {
            log.error("接口异常", e);
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<SdkListResponseDto> checkRpcImplements() {
        try {
            return ResponseEntity.ok(SdkRestfulConvertor.INSTANCE.fromThriftSdkListResponseDto(sdkInfoService.checkRpcImplements()));
        } catch (Exception e) {
            log.error("接口异常", e);
            return ResponseEntity.status(500).build();
        }
    }
}
