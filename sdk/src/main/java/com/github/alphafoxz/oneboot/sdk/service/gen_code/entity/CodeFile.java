package com.github.alphafoxz.oneboot.sdk.service.gen_code.entity;

import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Data
public class CodeFile {
    private String content;
    private String path;
    private String fileName;
    private Charset charset = StandardCharsets.UTF_8;

    public boolean write() {
        File javaFile = FileUtil.file(path);
        try {
            FileUtil.mkParentDirs(javaFile);
            FileUtil.writeString(content, path, charset);
            log.debug("生成代码：\n路径 {}\n内容 {}", path, content);
        } catch (Throwable e) {
            log.error("写文件失败", e);
            return false;
        }
        return true;
    }

    public boolean equals(Object o) {
        if (o instanceof CodeFile codeFile) {
            return StrUtil.equals(this.path, codeFile.getPath());
        }
        return false;
    }
}
