package com.github.alphafoxz.oneboot.sdk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_sdk")
public class SdkIndexController {
    @GetMapping(value = {"", "/index"})
    public String index() {
        final String HTML = """
                <html>
                <head>
                    <meta charset="utf-8">
                    <title>SDK工具简易导航</title>
                </head>
                <body>
                    <div>
                        <a href="/_sdk/info/checkErr">检查sdk模块基本功能</a>
                    </div>
                    <div>
                        <a href="/_sdk/thrift/generateAll">生成所有thrift代码</a>
                    </div>
                </body>
                </html>
                """;
        return HTML;
    }
}
