package com.github.alphafoxz.oneboot.common.interfaces.access_control;

import cn.hutool.core.lang.Pair;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 动态访问控制API
 */
public interface DacApi {
    public Pair<Long, Map<String, AbacAttr>> queryAuthorization(long subjectId, long resourceId, @Nullable Long targetSubjectId, @NonNull String actionType);
}
