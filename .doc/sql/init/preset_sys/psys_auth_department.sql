create table psys_auth_department
(
    id        bigint      not null
        constraint psys_auth_department_pk
            primary key,
    dept_name varchar(50) not null,
    sort      integer,
    status    smallint
);

comment on table psys_auth_department is '部门机构';

comment on column psys_auth_department.id is '主键';

comment on column psys_auth_department.dept_name is '部门名称';

comment on column psys_auth_department.sort is '排序';

comment on column psys_auth_department.status is '状态';

alter table psys_auth_department
    owner to postgres;

