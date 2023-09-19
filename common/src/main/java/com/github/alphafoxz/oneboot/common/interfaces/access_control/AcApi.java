package com.github.alphafoxz.oneboot.common.interfaces.access_control;

import org.springframework.lang.NonNull;

/**
 * 访问控制API
 */
public interface AcApi {
    public boolean access(@NonNull Long subjectId,
                          @NonNull String schemaName,
                          @NonNull String tableName,
                          @NonNull Long resourceBizId,
                          @NonNull String actionType,
                          @NonNull Class<? extends AbacPolicy>[] policiesClass);
}
