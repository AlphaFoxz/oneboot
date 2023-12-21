create table app_test
(
    id               bigint not null
        constraint app_test_pk
            primary key,
    test_json        jsonb,
    test_date        date,
    test_timestamp   timestamp,
    test_bool        boolean,
    test_double      double precision,
    test_float       real,
    test_timestamptz timestamp with time zone,
    test_time        time,
    test_timetz      time with time zone,
    test_daterange   daterange,
    test_varchar50   varchar(50)
);

comment on table app_test is '测试表';

comment on column app_test.id is '主键';

comment on column app_test.test_json is 'JSON数据';

comment on column app_test.test_date is '测试日期';

comment on column app_test.test_timestamp is '测试日期时间戳';

comment on column app_test.test_bool is '测试布尔';

comment on column app_test.test_double is '测试双精度浮点数';

comment on column app_test.test_float is '测试单精度浮点数';

comment on column app_test.test_timestamptz is '测试日期时间戳时区';

comment on column app_test.test_time is '测试时间';

comment on column app_test.test_timetz is '测试时间时区';

comment on column app_test.test_daterange is '测试日期区间';

comment on column app_test.test_varchar50 is '测试zi';

alter table app_test
    owner to postgres;

INSERT INTO app.app_test (id, test_json, test_date, test_timestamp, test_bool, test_double, test_float, test_timestamptz, test_time, test_timetz, test_daterange, test_varchar50) VALUES (1, null, null, null, null, null, null, '2023-09-21 03:08:10.422117 +00:00', null, null, null, 'test');
