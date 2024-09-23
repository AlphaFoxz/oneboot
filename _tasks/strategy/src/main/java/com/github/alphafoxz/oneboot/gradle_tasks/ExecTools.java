package com.github.alphafoxz.oneboot.gradle_tasks;

import cn.hutool.core.util.RuntimeUtil;

public class ExecTools {
    public static void main(String[] args) {
        RuntimeUtil.exec("cmd", "/c", "start", "pnpm", "create", "create-nuxt-content-git-pages@latest");
    }
}
