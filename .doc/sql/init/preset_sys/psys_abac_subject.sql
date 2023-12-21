create table psys_abac_subject
(
    id       bigint not null
        constraint psys_abac_subject_pk
            primary key,
    attr_set jsonb  not null
);

comment on table psys_abac_subject is '属性访问控制_主体属性表';

comment on column psys_abac_subject.id is '主键';

comment on column psys_abac_subject.attr_set is '主体属性集合';

alter table psys_abac_subject
    owner to postgres;

INSERT INTO preset_sys.psys_abac_subject (id, attr_set) VALUES (1704372248082780160, '["SECURITY_ADMIN"]');
