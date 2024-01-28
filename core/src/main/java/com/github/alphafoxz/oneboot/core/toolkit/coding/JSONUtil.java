package com.github.alphafoxz.oneboot.core.toolkit.coding;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootDirtyDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class JSONUtil extends cn.hutool.json.JSONUtil {
    public static String unquote(String string) {
        return unquote(string, true);
    }

    public static String unquote(String string, boolean isWrapped) {
        if (string == null) {
            return null;
        }
        if (StrUtil.isBlank(string)) {
            return string;
        }
        string = string.trim();
        if (string.length() < 2
                || (isWrapped && string.charAt(0) != '"')
                || (isWrapped && string.charAt(string.length() - 1) != '"')
        ) {
            String msg = StrUtil.format("无法解引用的值，请检查是否有脏数据：{}", string);
            log.error(msg);
            throw new OnebootDirtyDataException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        string = StrUtil.replace(string, "\\r\\n", "\n");
        string = StrUtil.replace(string, "\\n", "\n");
        string = StrUtil.replace(string, "\\t", "\t");
        string = StrUtil.replace(string, "\\\"", "\"");
        string = StrUtil.replace(string, "\\\\", "\\");
        return isWrapped ? string.substring(1, string.length() - 1) : string;
    }
}
