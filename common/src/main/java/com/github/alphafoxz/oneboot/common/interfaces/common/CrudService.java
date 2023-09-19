package com.github.alphafoxz.oneboot.common.interfaces.common;

import org.jooq.Condition;
import org.jooq.SortField;
import org.jooq.impl.TableImpl;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 增删改查Service
 *
 * @param <TABLE>  Jooq表
 * @param <PO>     Jooq po实体类
 * @param <RECORD> Jooq record类
 */
public interface CrudService<TABLE extends TableImpl<RECORD>, PO extends java.lang.Record, RECORD extends UpdatableRecordImpl<RECORD>> {
    @NonNull
    public TABLE getTable();

    @NonNull
    public Class<PO> getPoClass();

    public void startTransaction();

    public void rollbackTransaction();

    public void commitTransaction();

    public int insert(@NonNull RECORD record);

    public int insertMany(@NonNull RECORD... records);

    @Nullable
    public PO selectOne(@Nullable SortField<?>[] orderBy, @NonNull Condition... conditions);

    @Nullable
    public default PO selectOne(@NonNull Condition... conditions) {
        return selectOne(null, conditions);
    }

    @Nullable
    public PO selectOne(long id);

    @NonNull
    public Page<PO> selectPage(int pageNum, int pageSize, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions);

    @NonNull
    public default Page<PO> selectPage(int pageNum, int pageSize, @Nullable Condition... conditions) {
        return selectPage(pageNum, pageSize, null, conditions);
    }

    @NonNull
    public List<PO> selectList(int limit, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions);

    @NonNull
    public default List<PO> selectList(int limit, @Nullable Condition... conditions) {
        return selectList(limit, null, conditions);
    }

    public int selectCount(@Nullable Condition... conditions);

    public int update(@NonNull RECORD record);

    public int deleteById(@NonNull RECORD record);

    public int deleteById(long id);

    public int[] deleteByIds(@NonNull RECORD... records);

    public int deleteByIds(@NonNull long... ids);
}
