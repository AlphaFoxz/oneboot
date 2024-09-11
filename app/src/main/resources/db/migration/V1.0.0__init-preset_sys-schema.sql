set search_path = preset_sys;

create table psys_user
(
    id          bigserial
        constraint psys_user_pk
            primary key,
    username    varchar                  not null,
    nickname    varchar                  not null,
    user_status varchar                  not null,
    create_time timestamp with time zone not null,
    _version    timestamp with time zone not null,
    account_id  bigint                   not null
);

comment on table psys_user is '用户表';

comment on column psys_user.id is '主键';

comment on column psys_user.username is '用户名';

comment on column psys_user.nickname is '昵称';

comment on column psys_user.user_status is '用户状态';

comment on column psys_user.create_time is '创建时间';

comment on column psys_user._version is '版本';

comment on column psys_user.account_id is '账户id';

alter table psys_user
    owner to postgres;

create table psys_account
(
    id             bigserial
        constraint psys_account_pk
            primary key,
    create_time    timestamp with time zone not null,
    _version       timestamp with time zone not null,
    password       varchar                  not null,
    email          varchar,
    phone          varchar,
    account_status varchar                  not null
);

comment on table psys_account is '账户表';

comment on column psys_account.id is '主键';

comment on column psys_account.create_time is '创建时间';

comment on column psys_account._version is '版本';

comment on column psys_account.password is '密码';

comment on column psys_account.email is '邮箱';

comment on column psys_account.phone is '手机号';

comment on column psys_account.account_status is '状态';

alter table psys_account
    owner to postgres;

create table psys_token
(
    id            bigserial,
    access_token  varchar                  not null,
    refresh_token varchar                  not null,
    create_time   timestamp with time zone not null,
    subject_id    bigint                   not null,
    expire_time   timestamp with time zone not null
);

comment on table psys_token is '令牌表';

comment on column psys_token.id is '主键';

comment on column psys_token.access_token is '访问令牌';

comment on column psys_token.refresh_token is '刷新令牌';

comment on column psys_token.create_time is '创建时间';

comment on column psys_token.subject_id is '主体id（用户id）';

comment on column psys_token.expire_time is '过期时间';

alter table psys_token
    owner to postgres;

