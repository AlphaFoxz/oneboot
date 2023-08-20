package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkInfoApi;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_sdk")
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
                        <a href="/_sdk/info/checkErr" target="_blank">检查sdk模块基本功能</a>
                    </div>
                    <div>
                        <a href="/_sdk/thrift/getServerPort" target="_blank">获取当前服务的rpc端口</a>
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
    @GetMapping("/info/rootPath")
    public ResponseEntity<SdkStringResponseDto> rootPath() {
        SdkStringResponseDto result = new SdkStringResponseDto(snowflake.nextId(), snowflake.nextId(), true);
        result.setData(SdkConstants.PROJECT_ROOT_PATH);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/info/checkErr")
    public ResponseEntity<SdkListResponseDto> checkErr() {
        return ResponseEntity.ok(sdkInfoService.checkThriftErr());
    }
}
