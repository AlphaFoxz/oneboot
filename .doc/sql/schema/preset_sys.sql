create table if not exists preset_sys.psys_abac_resource_protection
(
    id            bigint      not null,
    resource_type char        not null,
    table_name    varchar(50) not null,
    schema_name   varchar(20) not null,
    enabled       boolean     not null,
    constraint psys_abac_resource_protection_pk
    primary key (id)
    );

comment on table preset_sys.psys_abac_resource_protection is '访问控制_资源保护表';

comment on column preset_sys.psys_abac_resource_protection.id is '主键';

comment on column preset_sys.psys_abac_resource_protection.resource_type is '资源类型 0表 1记录';

comment on column preset_sys.psys_abac_resource_protection.table_name is '表名';

comment on column preset_sys.psys_abac_resource_protection.schema_name is '结构名';

comment on column preset_sys.psys_abac_resource_protection.enabled is '是否生效';

create table if not exists preset_sys.psys_abac_resource
(
    id                bigint not null,
    owner_subject_id  bigint not null,
    resource_attr_set jsonb  not null,
    action_type_set   jsonb  not null,
    protection_id     bigint not null,
    business_id       bigint,
    constraint psys_abac_resource_pk
    primary key (id)
    );

comment on table preset_sys.psys_abac_resource is '属性访问控制_资源表';

comment on column preset_sys.psys_abac_resource.id is '主键';

comment on column preset_sys.psys_abac_resource.owner_subject_id is '拥有该资源的主体id';

comment on column preset_sys.psys_abac_resource.resource_attr_set is '资源属性集合';

comment on column preset_sys.psys_abac_resource.action_type_set is '操作类型集合';

comment on column preset_sys.psys_abac_resource.protection_id is '资源保护列表id';

comment on column preset_sys.psys_abac_resource.business_id is '业务id';

create index if not exists psys_abac_resource_business_id_index
    on preset_sys.psys_abac_resource (business_id);

create table if not exists preset_sys.psys_abac_subject
(
    id       bigint not null,
    attr_set jsonb  not null,
    constraint psys_abac_subject_pk
    primary key (id)
    );

comment on table preset_sys.psys_abac_subject is '属性访问控制_主体属性表';

comment on column preset_sys.psys_abac_subject.id is '主键';

comment on column preset_sys.psys_abac_subject.attr_set is '主体属性集合';

create table if not exists preset_sys.psys_auth_user
(
    id         bigint      not null,
    username   varchar(50) not null,
    password   char(60)    not null,
    nickname   varchar(50) not null,
    subject_id bigint      not null,
    account_id bigint      not null,
    enabled    boolean     not null,
    expired    boolean     not null,
    constraint psys_auth_user_pk
    primary key (id)
    );

comment on table preset_sys.psys_auth_user is '用户表';

comment on column preset_sys.psys_auth_user.id is '主键';

comment on column preset_sys.psys_auth_user.username is '用户名称';

comment on column preset_sys.psys_auth_user.password is '密码';

comment on column preset_sys.psys_auth_user.nickname is '昵称';

comment on column preset_sys.psys_auth_user.subject_id is '主体id';

comment on column preset_sys.psys_auth_user.account_id is '账户id';

comment on column preset_sys.psys_auth_user.enabled is '是否可用';

comment on column preset_sys.psys_auth_user.expired is '是否过期';

create index if not exists psys_auth_user_account_id_index
    on preset_sys.psys_auth_user (account_id);

create unique index if not exists psys_auth_user_username_uindex
    on preset_sys.psys_auth_user (username);

create table if not exists preset_sys.psys_abac_group
(
    id             bigint not null,
    group_attr_set jsonb  not null,
    business_id    bigint not null,
    constraint psys_abac_group_pk
    primary key (id)
    );

comment on column preset_sys.psys_abac_group.id is '主键';

comment on column preset_sys.psys_abac_group.group_attr_set is '组标签集合';

comment on column preset_sys.psys_abac_group.business_id is '业务id';

create table if not exists preset_sys.psys_abac_dynamic_authorization
(
    id                 bigint not null,
    authorization_type char   not null,
    subject_attr_set   jsonb  not null,
    timeout_until      timestamp with time zone,
    resource_id        bigint not null,
    owner_subject_id   bigint not null,
    target_subject_id  bigint,
    constraint psys_abac_dynamic_authorization_pk
    primary key (id)
    );

comment on table preset_sys.psys_abac_dynamic_authorization is '动态访问控制_授权表';

comment on column preset_sys.psys_abac_dynamic_authorization.id is '主键';

comment on column preset_sys.psys_abac_dynamic_authorization.authorization_type is '授权类型 0主动 1被动';

comment on column preset_sys.psys_abac_dynamic_authorization.subject_attr_set is '授权主体属性集合';

comment on column preset_sys.psys_abac_dynamic_authorization.timeout_until is '授权过期时间';

comment on column preset_sys.psys_abac_dynamic_authorization.resource_id is '资源属性id';

comment on column preset_sys.psys_abac_dynamic_authorization.owner_subject_id is '资源所有者主体Id';

comment on column preset_sys.psys_abac_dynamic_authorization.target_subject_id is '授权目标主体Id';

create table if not exists preset_sys.psys_auth_account
(
    id      bigint  not null,
    expired boolean not null,
    enabled boolean not null,
    constraint psys_auth_account_pk
    primary key (id)
    );

comment on column preset_sys.psys_auth_account.id is '主键';

comment on column preset_sys.psys_auth_account.expired is '是否过期';

comment on column preset_sys.psys_auth_account.enabled is '是否可用';

create table if not exists preset_sys.psys_auth_token
(
);

