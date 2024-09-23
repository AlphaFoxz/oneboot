package com.github.alphafoxz.oneboot.gradle_tasks;

import cn.hutool.core.util.RuntimeUtil;

public class CheckTools {
    public static void main(String[] args) {
        try {
            RuntimeUtil.exec("node", "-v");
        } catch (Exception e) {
            throw new RuntimeException("未安装node，请检查环境变量，确保有全局的可执行的node指令。" + e.getMessage());
        }
        try {
            RuntimeUtil.exec("pnpm.cmd", "-v");
        } catch (Exception _e) {
            try {
                RuntimeUtil.exec("pnpm", "-v");
            } catch (Exception e) {
                throw new RuntimeException("未安装pnpm，请检查环境变量，确保有全局的可执行的pnpm指令。" + e.getMessage());
            }
        }
    }
}
