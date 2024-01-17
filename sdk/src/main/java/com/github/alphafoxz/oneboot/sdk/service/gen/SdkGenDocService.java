package com.github.alphafoxz.oneboot.sdk.service.gen;

import cn.hutool.poi.word.Word07Writer;
import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import jakarta.annotation.Resource;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;

/**
 * 生成文档service
 */
@Service
public class SdkGenDocService {
    @Resource
    private DSLContext dslContext;

    public File generateWordApi(String moduleName) {
        File file = FileUtil.createTempFile(".docx.tmp", true);
        Word07Writer writer = new Word07Writer();
        // 添加段落（标题）
        writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "我是第一部分", "我是第二部分");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
        // 写出到文件
        writer.flush(file);
        // 关闭
        writer.close();
        return file;
    }

    public File generateExcelApi(String moduleName) {
        File file = FileUtil.createTempFile();
        FileUtil.writeUtf8String("", file);
        return file;
    }
}
