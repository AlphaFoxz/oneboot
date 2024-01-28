package com.github.alphafoxz.oneboot.core.standard.framework;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.core.standard.access_control.AbacPolicy;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface AbacService extends LogAble {
    public Class<? extends AbacPolicy>[] getCreatePolicies();

    public Class<? extends AbacPolicy>[] getReadPolicies();

    public Class<? extends AbacPolicy>[] getUpdatePolicies();

    public Class<? extends AbacPolicy>[] getLogicDeletePolicies();

    public Class<? extends AbacPolicy>[] getDeletePolicies();

    public default void throw403Error(@NonNull String tableName, @Nullable Long resourceId) {
        if (resourceId != null) {
            getLogger().error("验证失败，对该资源没有权限，表名：{}，资源id：{}", tableName, resourceId);
        }
        throw new OnebootAuthException("当前用户对该资源没有访问或操作权限", HttpStatus.FORBIDDEN);
    }

    public default void throw401Error() {
        throw new OnebootAuthException("当前用户未登录或长时间未操作，请重新登录", HttpStatus.UNAUTHORIZED);
    }
}
