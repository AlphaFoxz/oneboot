create table psys_abac_resource_protection
(
    id            bigint      not null
        constraint psys_abac_resource_protection_pk
            primary key,
    resource_type char        not null,
    table_name    varchar(50) not null,
    schema_name   varchar(20) not null,
    enabled       boolean     not null
);

comment on table psys_abac_resource_protection is '访问控制_资源保护表';

comment on column psys_abac_resource_protection.id is '主键';

comment on column psys_abac_resource_protection.resource_type is '资源类型 0表 1记录';

comment on column psys_abac_resource_protection.table_name is '表名';

comment on column psys_abac_resource_protection.schema_name is '结构名';

comment on column psys_abac_resource_protection.enabled is '是否生效';

alter table psys_abac_resource_protection
    owner to postgres;

INSERT INTO preset_sys.psys_abac_resource_protection (id, resource_type, table_name, schema_name, enabled) VALUES (1704389766184833024, '0', 'app_test', 'app', true);
