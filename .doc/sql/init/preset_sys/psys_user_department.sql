create table psys_user_department
(
    id        bigint      not null
        constraint psys_user_department_pk
            primary key,
    dept_name varchar(50) not null,
    sort      integer,
    status    smallint
);

comment on table psys_user_department is '部门机构';

comment on column psys_user_department.id is '主键';

comment on column psys_user_department.dept_name is '部门名称';

comment on column psys_user_department.sort is '排序';

comment on column psys_user_department.status is '状态';

alter table psys_user_department
    owner to postgres;

