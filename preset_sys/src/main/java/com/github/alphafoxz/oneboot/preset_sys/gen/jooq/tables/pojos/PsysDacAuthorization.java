/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos;


import java.io.Serializable;

import org.jooq.JSONB;


/**
 * 动态访问控制_授权表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysDacAuthorization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String authorizationType;
    private final JSONB subjectAttrSet;
    private final Long timeoutMs;
    private final Long resourceAttrId;

    public PsysDacAuthorization(PsysDacAuthorization value) {
        this.id = value.id;
        this.authorizationType = value.authorizationType;
        this.subjectAttrSet = value.subjectAttrSet;
        this.timeoutMs = value.timeoutMs;
        this.resourceAttrId = value.resourceAttrId;
    }

    public PsysDacAuthorization(
        Long id,
        String authorizationType,
        JSONB subjectAttrSet,
        Long timeoutMs,
        Long resourceAttrId
    ) {
        this.id = id;
        this.authorizationType = authorizationType;
        this.subjectAttrSet = subjectAttrSet;
        this.timeoutMs = timeoutMs;
        this.resourceAttrId = resourceAttrId;
    }

    /**
     * Getter for <code>preset_sys.psys_dac_authorization.id</code>. 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.authorization_type</code>. 授权类型
     * I主动 P被动
     */
    public String getAuthorizationType() {
        return this.authorizationType;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.subject_attr_set</code>. 授权主体属性集合
     */
    public JSONB getSubjectAttrSet() {
        return this.subjectAttrSet;
    }

    /**
     * Getter for <code>preset_sys.psys_dac_authorization.timeout_ms</code>.
     * 授权过期时间（毫秒）
     */
    public Long getTimeoutMs() {
        return this.timeoutMs;
    }

    /**
     * Getter for
     * <code>preset_sys.psys_dac_authorization.resource_attr_id</code>. 资源属性id
     */
    public Long getResourceAttrId() {
        return this.resourceAttrId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PsysDacAuthorization other = (PsysDacAuthorization) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.authorizationType == null) {
            if (other.authorizationType != null)
                return false;
        }
        else if (!this.authorizationType.equals(other.authorizationType))
            return false;
        if (this.subjectAttrSet == null) {
            if (other.subjectAttrSet != null)
                return false;
        }
        else if (!this.subjectAttrSet.equals(other.subjectAttrSet))
            return false;
        if (this.timeoutMs == null) {
            if (other.timeoutMs != null)
                return false;
        }
        else if (!this.timeoutMs.equals(other.timeoutMs))
            return false;
        if (this.resourceAttrId == null) {
            if (other.resourceAttrId != null)
                return false;
        }
        else if (!this.resourceAttrId.equals(other.resourceAttrId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.authorizationType == null) ? 0 : this.authorizationType.hashCode());
        result = prime * result + ((this.subjectAttrSet == null) ? 0 : this.subjectAttrSet.hashCode());
        result = prime * result + ((this.timeoutMs == null) ? 0 : this.timeoutMs.hashCode());
        result = prime * result + ((this.resourceAttrId == null) ? 0 : this.resourceAttrId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsysDacAuthorization (");

        sb.append(id);
        sb.append(", ").append(authorizationType);
        sb.append(", ").append(subjectAttrSet);
        sb.append(", ").append(timeoutMs);
        sb.append(", ").append(resourceAttrId);

        sb.append(")");
        return sb.toString();
    }
}
