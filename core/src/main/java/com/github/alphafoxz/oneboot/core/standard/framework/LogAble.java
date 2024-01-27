package com.github.alphafoxz.oneboot.core.standard.framework;

import org.slf4j.Logger;
import org.springframework.lang.NonNull;

/**
 * 有日志能力的，通常当抽象类对子类有日志默认实现的时候就继承这个接口
 */
public interface LogAble {
    @NonNull
    public Logger getLogger();
}
