package com.github.alphafoxz.oneboot.sdk.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SdkInfoController", description = "Sdk导航页面")
@RestController
@RequestMapping("/_sdk")
public class SdkIndexController {
    @GetMapping(value = {"", "/", "/index"})
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
                        <a href="/_sdk/thrift/generateAll" target="_blank">生成所有rpc代码</a>
                    </div>
                </body>
                </html>
                """;
    }
}
