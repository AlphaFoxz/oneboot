package com.github.alphafoxz.oneboot.sdk.service.gen_code;

import com.github.alphafoxz.oneboot.sdk.service.gen_code.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseThriftSyntaxTreeUtil;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface CodeGenerator {
    public Set<CodeFile> genCodeFileSet(@NonNull ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRootBean, String targetDir);

    default public boolean genAndWriteCodeFiles(@NonNull ParseThriftSyntaxTreeUtil.ThriftRootBean thriftRootBean, String targetDir) {
        boolean b = true;
        Set<CodeFile> codeFiles = genCodeFileSet(thriftRootBean, targetDir);
        for (CodeFile codeFile : codeFiles) {
            b = b && codeFile.write();
        }
        return b;
    }
}
