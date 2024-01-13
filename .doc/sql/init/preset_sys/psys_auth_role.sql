create table psys_auth_role
(
    id          bigint      not null
        constraint psys_auth_role_pk
            primary key,
    role_num    varchar(50),
    role_name   varchar(50) not null,
    description varchar(300),
    enabled     boolean,
    status      smallint,
    role_code   varchar(50)
);

comment on table psys_auth_role is '角色表';

comment on column psys_auth_role.id is '主键';

comment on column psys_auth_role.role_num is '角色编号';

comment on column psys_auth_role.role_name is '角色名称';

comment on column psys_auth_role.description is '描述';

comment on column psys_auth_role.enabled is '是否启用';

comment on column psys_auth_role.status is '状态';

comment on column psys_auth_role.role_code is '角色bi';

alter table psys_auth_role
    owner to postgres;

