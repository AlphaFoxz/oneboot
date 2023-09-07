package com.github.alphafoxz.oneboot.preset_sys.controller;

import cn.hutool.core.lang.Snowflake;
import jakarta.annotation.Resource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_USER;

@RequestMapping("/psys/user")
@RestController
public class PsysSysUserController {
    @Resource
    DSLContext dslContext;
    @Resource
    Snowflake snowflake;

    @GetMapping("/query")
    public ResponseEntity<?> query() {
        Result<Record> fetch = dslContext
                .select(PSYS_USER.fields())
                .from(PSYS_USER)
                .fetch();
        return ResponseEntity.ok(fetch.intoMaps());
    }

    @GetMapping("/insert")
    public ResponseEntity<?> insert() {
        dslContext
                .insertInto(PSYS_USER, PSYS_USER.ID)
                .values(snowflake.nextId())
                .execute();
        return ResponseEntity.ok("OK");
    }
}
