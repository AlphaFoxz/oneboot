package com.github.alphafoxz.oneboot.core.standard.jooq;

import org.jooq.Table;
import org.jooq.TableRecord;
import org.jooq.impl.TableRecordImpl;

public class TableRecordExt<T extends TableRecord<T>> extends TableRecordImpl<T> {
    public TableRecordExt(Table<T> table) {
        super(table);
    }

    @SuppressWarnings("all")
    public <U> U toVo() {
        return (U) this;
    }
}
