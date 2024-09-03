set search_path = preset_sys;

create table psys_user
(
	id bigserial,
	username varchar not null,
	nickname varchar not null,
	user_status integer not null,
	create_time timestamp with time zone not null,
	_version timestamp with time zone not null,
	constraint psys_user_pk
		primary key (id)
);

comment on table psys_user is '用户表';

comment on column psys_user.id is '主键';

comment on column psys_user.username is '用户名';

comment on column psys_user.nickname is '昵称';

comment on column psys_user.user_status is '用户状态';

comment on column psys_user.create_time is '创建时间';

comment on column psys_user._version is '版本';

alter table psys_user owner to postgres;

create table psys_account
(
	id bigserial,
	create_time timestamp with time zone not null,
	_version timestamp with time zone not null,
	password varchar not null,
	email varchar,
	phone varchar,
	constraint psys_account_pk
		primary key (id)
);

comment on table psys_account is '账户表';

comment on column psys_account.id is '主键';

comment on column psys_account.create_time is '创建时间';

comment on column psys_account._version is '版本';

comment on column psys_account.password is '密码';

comment on column psys_account.email is '邮箱';

comment on column psys_account.phone is '手机号';

alter table psys_account owner to postgres;
