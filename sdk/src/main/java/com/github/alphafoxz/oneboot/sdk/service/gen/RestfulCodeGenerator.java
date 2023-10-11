package com.github.alphafoxz.oneboot.sdk.service.gen;

import com.github.alphafoxz.oneboot.sdk.service.gen.entity.CodeFile;
import com.github.alphafoxz.oneboot.sdk.toolkit.ParseRestfulSyntaxTreeUtil;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface RestfulCodeGenerator {
    public Set<CodeFile> genCodeFileSet(@NonNull ParseRestfulSyntaxTreeUtil.RestfulRootBean restfulRootBean, String targetDir);

    default public boolean genAndWriteCodeFiles(@NonNull ParseRestfulSyntaxTreeUtil.RestfulRootBean restfulRootBean, String targetDir) {
        boolean b = true;
        Set<CodeFile> codeFiles = genCodeFileSet(restfulRootBean, targetDir);
        for (CodeFile codeFile : codeFiles) {
            b = b && codeFile.writeToLocal();
        }
        return b;
    }
}
