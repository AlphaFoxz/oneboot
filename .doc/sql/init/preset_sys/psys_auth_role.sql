create table psys_auth_role
(
    id          bigint      not null
        constraint psys_auth_role_pk
            primary key,
    role_title  varchar(50) not null,
    role_name   integer     not null,
    sort        integer     not null,
    description varchar(300),
    enabled     boolean
);

comment on table psys_auth_role is '角色表';

comment on column psys_auth_role.id is '主键';

comment on column psys_auth_role.role_title is '角色名称';

comment on column psys_auth_role.sort is '排序';

comment on column psys_auth_role.description is '描述';

comment on column psys_auth_role.enabled is '是否启用';

alter table psys_auth_role
    owner to postgres;

