package com.github.alphafoxz.oneboot.core.standard.access_control;

import com.github.alphafoxz.oneboot.core.toolkit.tuple.Tuple2;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 动态访问控制API
 */
public interface AbacDynamicService {
    public Tuple2<Long, Map<String, AbacAttr>> queryAuthorization(long subjectId, long resourceId, @Nullable Long targetSubjectId, @NonNull String actionType);
}
