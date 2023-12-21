create table psys_abac_resource
(
    id                bigint not null
        constraint psys_abac_resource_pk
            primary key,
    owner_subject_id  bigint not null,
    resource_attr_set jsonb  not null,
    action_type_set   jsonb  not null,
    protection_id     bigint not null,
    business_id       bigint
);

comment on table psys_abac_resource is '属性访问控制_资源表';

comment on column psys_abac_resource.id is '主键';

comment on column psys_abac_resource.owner_subject_id is '拥有该资源的主体id';

comment on column psys_abac_resource.resource_attr_set is '资源属性集合';

comment on column psys_abac_resource.action_type_set is '操作类型集合';

comment on column psys_abac_resource.protection_id is '资源保护列表id';

comment on column psys_abac_resource.business_id is '业务id';

alter table psys_abac_resource
    owner to postgres;

create index psys_abac_resource_business_id_index
    on psys_abac_resource (business_id);

INSERT INTO preset_sys.psys_abac_resource (id, owner_subject_id, resource_attr_set, action_type_set, protection_id, business_id) VALUES (1704389955196948480, 1704372248082780160, '[]', '["c", "r", "u", "d"]', 1704389766184833024, null);
