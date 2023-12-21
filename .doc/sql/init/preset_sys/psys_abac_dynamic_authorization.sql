create table psys_abac_dynamic_authorization
(
    id                 bigint not null
        constraint psys_abac_dynamic_authorization_pk
            primary key,
    authorization_type char   not null,
    subject_attr_set   jsonb  not null,
    timeout_until      timestamp with time zone,
    resource_id        bigint not null,
    owner_subject_id   bigint not null,
    target_subject_id  bigint
);

comment on table psys_abac_dynamic_authorization is '动态访问控制_授权表';

comment on column psys_abac_dynamic_authorization.id is '主键';

comment on column psys_abac_dynamic_authorization.authorization_type is '授权类型 0主动 1被动';

comment on column psys_abac_dynamic_authorization.subject_attr_set is '授权主体属性集合';

comment on column psys_abac_dynamic_authorization.timeout_until is '授权过期时间';

comment on column psys_abac_dynamic_authorization.resource_id is '资源属性id';

comment on column psys_abac_dynamic_authorization.owner_subject_id is '资源所有者主体Id';

comment on column psys_abac_dynamic_authorization.target_subject_id is '授权目标主体Id';

alter table psys_abac_dynamic_authorization
    owner to postgres;

