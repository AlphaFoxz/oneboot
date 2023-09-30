create table if not exists app.app_test
(
    id               bigint not null,
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
    test_varchar50   varchar(50),
    constraint app_test_pk
    primary key (id)
    );

comment on table app.app_test is '测试表';

comment on column app.app_test.id is '主键';

comment on column app.app_test.test_json is 'JSON数据';

comment on column app.app_test.test_date is '测试日期';

comment on column app.app_test.test_timestamp is '测试日期时间戳';

comment on column app.app_test.test_bool is '测试布尔';

comment on column app.app_test.test_double is '测试双精度浮点数';

comment on column app.app_test.test_float is '测试单精度浮点数';

comment on column app.app_test.test_timestamptz is '测试日期时间戳时区';

comment on column app.app_test.test_time is '测试时间';

comment on column app.app_test.test_timetz is '测试时间时区';

comment on column app.app_test.test_daterange is '测试日期区间';

comment on column app.app_test.test_varchar50 is '测试zi';

