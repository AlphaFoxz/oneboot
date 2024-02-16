create table psys_account
(
    id      bigint  not null
        constraint psys_account_pk
            primary key,
    expired boolean not null,
    enabled boolean not null
);

comment on column psys_account.id is '主键';

comment on column psys_account.expired is '是否过期';

comment on column psys_account.enabled is '是否可用';

alter table psys_account
    owner to postgres;

INSERT INTO preset_sys.psys_account (id, expired, enabled) VALUES (1706192606767222784, false, true);
INSERT INTO preset_sys.psys_account (id, expired, enabled) VALUES (1734113308765720577, false, true);
