package com.github.alphafoxz.oneboot.gradle_tasks;

import cn.hutool.core.util.RuntimeUtil;

public class GeneratePresetSys {
    public static EnvFile envFile;

    public static void main(String[] args) {
        envFile = new EnvFile(args[0]);
        RuntimeUtil.exec(
                "cmd", "/c", "start",
                "pnpm", "dlx", "@ddd-tool/create-oneboot@" + envFile.getContent().getOnebootToolVersion(),
                "genVoMapper",
                "--domain-module=domain-preset_sys",
                "--output-module=preset_sys",
                "--project-root=" + args[0]
        );
    }
}
