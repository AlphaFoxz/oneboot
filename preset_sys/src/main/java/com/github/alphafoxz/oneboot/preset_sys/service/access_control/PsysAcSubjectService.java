package com.github.alphafoxz.oneboot.preset_sys.service.access_control;

import cn.hutool.json.JSONArray;
import com.github.alphafoxz.oneboot.common.annos.access_control.AcSubjectId;
import com.github.alphafoxz.oneboot.common.ifaces.access_control.AbacAttrIface;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.JSONUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.annos.access_control.PsysAcTable;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysAbacSubject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.Record;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_ABAC_SUBJECT;

/**
 * 访问控制-主体处理
 */
@Slf4j
@Service
public class PsysAcSubjectService {
    @Resource
    private DSLContext dslContext;

    @NonNull
    @PsysAcTable(PsysAbacSubject.class)
    public Set<AbacAttrIface> querySubjectAttrsById(@AcSubjectId @NonNull long subjectId) {
        //XXX 此处应该采用数据缓存
        Record record = dslContext.select(PSYS_ABAC_SUBJECT.fields())
                .from(PSYS_ABAC_SUBJECT)
                .where(PSYS_ABAC_SUBJECT.ID.eq(subjectId))
                .fetchOne();
        if (record == null) {
            String msg = StrUtil.format("查询不到主体信息，请检查数据完整性，subjectId：{}", subjectId);
            log.error(msg);
            throw new RuntimeException(msg);
        }
        com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacSubject psysAbacSubject = record.into(com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAbacSubject.class);
        JSONB attrJsonb = psysAbacSubject.getAttrSet();
        JSONArray jsonArray = JSONUtil.parseArray(attrJsonb.toString());
        Set<AbacAttrIface> attrSet = CollUtil.newHashSet();
        jsonArray.forEach(s -> {
            attrSet.add(AbacAttrIface.of(s.toString()));
        });
        return attrSet;
    }
}
