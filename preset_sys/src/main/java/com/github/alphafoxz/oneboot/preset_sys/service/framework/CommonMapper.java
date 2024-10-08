package com.github.alphafoxz.oneboot.preset_sys.service.framework;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.JSONUtil;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommonMapper {
    public Long asLong(OffsetDateTime source) {
        return source == null ? null : source.toInstant().toEpochMilli();
    }

    public Long asLong(Date source) {
        return source == null ? null : source.getTime();
    }

    public String asString(Collection<?> source) {
        return source == null ? null : JSONUtil.toJsonStr(source);
    }

    public String asString(TemporalAccessor source) {
        return source == null ? null : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(source);
    }

    public Date asDate(Long source) {
        return source == null ? null : new Date(source);
    }

    public OffsetDateTime asOffsetDateTime(Long source) {
        return source == null ? null : OffsetDateTime.ofInstant(new Date(source).toInstant(), ZoneId.systemDefault());
    }

    public OffsetDateTime asOffsetDateTime(String source) {
        return source == null ? null : OffsetDateTime.parse(source);
    }

    public Set<String> asStringSet(String source) {
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

    public List<String> asStringList(String source) {
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
}
