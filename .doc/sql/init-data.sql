INSERT INTO preset_sys.psys_abac_resource (id, owner_subject_id, resource_attr_set, action_type_set, protection_id, business_id) VALUES (1704389955196948480, 1704372248082780160, '[]', '["c", "r", "u", "d"]', 1704389766184833024, null);

INSERT INTO preset_sys.psys_abac_resource_protection (id, resource_type, table_name, schema_name) VALUES (1704389766184833024, '0', 'app_test', 'app');

INSERT INTO preset_sys.psys_abac_subject (id, attr_set) VALUES (1704372248082780160, '["SECURITY_ADMIN"]');

INSERT INTO preset_sys.psys_hr_user (id, username, password, salt, nickname, subject_id) VALUES (1704372320224808960, 'security_admin', 'security_admin', '1234', '安全管理员', 1704372248082780160);

INSERT INTO app.app_test (id, test_json, test_date, test_timestamp, test_bool, test_double, test_float, test_timestamptz, test_time, test_timetz, test_daterange, test_varchar50) VALUES (1, null, null, null, null, null, null, '2023-09-21 03:08:10.422117 +00:00', null, null, null, 'test');
