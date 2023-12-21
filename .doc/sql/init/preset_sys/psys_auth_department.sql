create table psys_auth_department
(
    id bigint
);

comment on table psys_auth_department is '部门机构';

comment on column psys_auth_department.id is '主键';

alter table psys_auth_department
    owner to postgres;

