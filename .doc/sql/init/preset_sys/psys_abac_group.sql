create table psys_abac_group
(
    id             bigint not null
        constraint psys_abac_group_pk
            primary key,
    group_attr_set jsonb  not null,
    business_id    bigint not null
);

comment on column psys_abac_group.id is '主键';

comment on column psys_abac_group.group_attr_set is '组标签集合';

comment on column psys_abac_group.business_id is '业务id';

alter table psys_abac_group
    owner to postgres;

