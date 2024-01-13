create table psys_auth_user
(
    id            bigint      not null
        constraint psys_auth_user_pk
            primary key,
    username      varchar(50) not null,
    password      char(60)    not null,
    nickname      varchar(50) not null,
    subject_id    bigint      not null,
    account_id    bigint      not null,
    enabled       boolean     not null,
    expired       boolean     not null,
    description   varchar(300),
    department_id bigint,
    phone         varchar(20)
);

comment on table psys_auth_user is '用户表';

comment on column psys_auth_user.id is '主键';

comment on column psys_auth_user.username is '用户名称';

comment on column psys_auth_user.password is '密码';

comment on column psys_auth_user.nickname is '昵称';

comment on column psys_auth_user.subject_id is '主体id';

comment on column psys_auth_user.account_id is '账户id';

comment on column psys_auth_user.enabled is '是否可用';

comment on column psys_auth_user.expired is '是否过期';

comment on column psys_auth_user.description is '描述';

comment on column psys_auth_user.department_id is '部门id';

comment on column psys_auth_user.phone is '手机号码';

alter table psys_auth_user
    owner to postgres;

create index psys_auth_user_account_id_index
    on psys_auth_user (account_id);

create unique index psys_auth_user_username_uindex
    on psys_auth_user (username);

INSERT INTO preset_sys.psys_auth_user (id, username, password, nickname, subject_id, account_id, enabled, expired, description, department_id, phone) VALUES (1704372320224808960, 'security_admin', '$2a$10$/mcrnAq/CSm4DEsd7U/imexxb32ZGb3rOPrVm0vAJh8/vkzKEtLSu', '安全管理员', 1704372248082780160, 1706192606767222784, true, false, null, null, null);
INSERT INTO preset_sys.psys_auth_user (id, username, password, nickname, subject_id, account_id, enabled, expired, description, department_id, phone) VALUES (1734113308690223104, 'admin', '$2a$10$VngEJfSKnryFpWJat2x/yelyXoMIqRZJvNIf0dd.voie4NBP19ZNS', '系统管理员', 1734113308765720576, 1734113308765720577, true, false, null, null, null);
