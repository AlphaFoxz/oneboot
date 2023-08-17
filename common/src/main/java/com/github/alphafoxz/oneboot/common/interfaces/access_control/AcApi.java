package com.github.alphafoxz.oneboot.common.interfaces.access_control;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 访问控制API
 */
public interface AcApi {
    public boolean access(@NonNull Long subjectId,
                          @NonNull String schemaName,
                          @NonNull String tableName,
                          @Nullable Long resourceBizId,
                          @NonNull String actionType,
                          @NonNull Class<? extends AbacPolicy>[] policiesClass);
}
