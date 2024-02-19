package com.github.alphafoxz.oneboot.preset_sys.service.framework;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.JSONUtil;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommonMapper {
    public Long asLong(OffsetDateTime source) {
        return source == null ? null : source.toInstant().toEpochMilli();
    }

    public String asString(OffsetDateTime source) {
        return source == null ? null : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(source);
    }

    public OffsetDateTime asOffsetDateTime(Long source) {
        return source == null ? null : OffsetDateTime.ofInstant(new Date(source).toInstant(), ZoneId.systemDefault());
    }

    public OffsetDateTime asOffsetDateTime(String source) {
        return source == null ? null : OffsetDateTime.parse(source);
    }

    public Set<String> toStringSet(String source) {
        if (source == null) {
            return null;
        }
        if (StrUtil.isWrap(source, "[", "]")) {
            source = source.substring(1, source.length() - 1);
        }
        String[] arr = source.split(",");
        boolean isWrap = false;
        for (String s : arr) {
            if (!StrUtil.isWrap(s, "\"")) {
                isWrap = false;
                break;
            }
            isWrap = true;
        }
        if (!isWrap) {
            return CollUtil.newHashSet(arr);
        }
        return CollUtil.newHashSet(Arrays.stream(arr).map(s -> JSONUtil.unquote(s, true)).collect(Collectors.toSet()));
    }

    public List<String> toStringList(String source) {
        if (source == null) {
            return null;
        }
        if (StrUtil.isWrap(source, "[", "]")) {
            source = source.substring(1, source.length() - 1);
        }
        String[] arr = source.split(",");
        boolean isWrap = false;
        for (String s : arr) {
            if (!StrUtil.isWrap(s, "\"")) {
                isWrap = false;
                break;
            }
            isWrap = true;
        }
        if (!isWrap) {
            return Arrays.asList(arr);
        }
        return Arrays.stream(arr).map(s -> JSONUtil.unquote(s, true)).collect(Collectors.toList());
    }

    public String toString(Collection<?> source) {
        return source == null ? null : JSONUtil.toJsonStr(source);
    }
}
