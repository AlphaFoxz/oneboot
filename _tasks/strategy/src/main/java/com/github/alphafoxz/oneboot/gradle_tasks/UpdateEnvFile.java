package com.github.alphafoxz.oneboot.gradle_tasks;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

public class UpdateEnvFile {
    public static EnvFile envFile;

    public static void main(String[] args) {
        envFile = new EnvFile(args[0]);
        updateOnebootTool();
        envFile.save();
    }

    public static void updateOnebootTool() {
        var request = HttpUtil.createGet("https://registry.npmjs.org/" + URLUtil.encode("@ddd-tool/create-oneboot"))
                .header("Cache-Control", "no-cache")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36")
                .enableDefaultCookie();
        var packageInfo = JSONUtil.parseObj(request.execute().body());
        envFile.getContent().setOnebootToolVersion(packageInfo.getJSONObject("dist-tags").getStr("latest"));
    }
}
