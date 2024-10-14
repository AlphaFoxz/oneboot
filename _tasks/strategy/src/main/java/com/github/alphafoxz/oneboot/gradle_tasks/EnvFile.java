package com.github.alphafoxz.oneboot.gradle_tasks;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.io.File;
import java.util.StringJoiner;

public class EnvFile {
    private final String filePath;
    @Getter
    private final Content content;

    public EnvFile(String rootPath) {
        this.filePath = new StringJoiner(File.separator).add(rootPath).add("_tasks").add("env.json").toString();
        this.content = JSONUtil.parse(FileUtil.readUtf8String(this.filePath)).toBean(Content.class);
    }

    public void save() {
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(content), filePath);
    }

    @Data
    public static class Content {
        @NonNull
        private String onebootToolVersion = "";
    }
}
