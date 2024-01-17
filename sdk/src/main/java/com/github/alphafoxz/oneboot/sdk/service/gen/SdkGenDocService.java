package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import jakarta.annotation.Resource;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 生成文档service
 */
@Service
public class SdkGenDocService {
    @Resource
    private DSLContext dslContext;

    public File generateWordApi(String moduleName) {
        File file = FileUtil.createTempFile();
        FileUtil.writeUtf8String("", file);
        return file;
    }

    public File generateExcelApi(String moduleName) {
        File file = FileUtil.createTempFile();
        FileUtil.writeUtf8String("", file);
        return file;
    }
}
