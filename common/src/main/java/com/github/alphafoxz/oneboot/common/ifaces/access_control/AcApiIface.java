package com.github.alphafoxz.oneboot.common.ifaces.access_control;

import org.springframework.lang.NonNull;

/**
 * 访问控制API
 */
public interface AcApiIface {
    public boolean access(@NonNull Long subjectId,
                          @NonNull String schemaName,
                          @NonNull String tableName,
                          @NonNull Long resourceId,
                          @NonNull AcActionTypeIface actionType);
}
