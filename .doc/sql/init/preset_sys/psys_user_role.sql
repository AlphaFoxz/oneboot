create table psys_user_role
(
    id          bigint                   not null
        constraint psys_user_role_pk
            primary key,
    role_name   varchar(50)              not null,
    description varchar(300),
    enabled     boolean,
    status      smallint,
    role_code   varchar(50),
    create_time timestamp with time zone not null,
    update_time timestamp with time zone,
    remark      varchar(50)
);

comment on table psys_user_role is '角色表';

comment on column psys_user_role.id is '主键';

comment on column psys_user_role.role_name is '角色名称';

comment on column psys_user_role.description is '描述';

comment on column psys_user_role.enabled is '是否启用';

comment on column psys_user_role.status is '状态';

comment on column psys_user_role.role_code is '角色编码';

comment on column psys_user_role.create_time is '创建时间';

comment on column psys_user_role.update_time is '更新时间';

comment on column psys_user_role.remark is '备注';

alter table psys_user_role
    owner to postgres;

