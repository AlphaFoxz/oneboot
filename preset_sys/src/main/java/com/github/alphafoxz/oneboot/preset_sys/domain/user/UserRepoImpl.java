package com.github.alphafoxz.oneboot.preset_sys.domain.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserAgg;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserAggImpl;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.UserRepo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysAccountRecord;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records.PsysTokenRecord;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.*;

@Repository
@RequiredArgsConstructor
public class UserRepoImpl implements UserRepo {
    private final DSLContext dsl;
    private final UserMapper userMapper;

    @Override
    public @NonNull UserAgg findBySubjectId(UsernameVo username) {
        var user = dsl
                .selectFrom(PSYS_USER)
                .where(PSYS_USER.USERNAME.eq(username.value()))
                .fetchOne();
        PsysAccountRecord account;
        PsysTokenRecord token;
        if (user != null) {
            account = dsl.selectFrom(PSYS_ACCOUNT)
                    .where(PSYS_ACCOUNT.ID.eq(user.getAccountId()))
                    .fetchOne();
            token = dsl.selectFrom(PSYS_TOKEN)
                    .where(PSYS_TOKEN.SUBJECT_ID.eq(user.getId()))
                    .and(PSYS_TOKEN.EXPIRE_TIME.gt(OffsetDateTime.now()))
                    .fetchOne();
            return new UserAggImpl(
                    userMapper.psysAccountRecordToAccountVo(account),
                    userMapper.psysTokenRecordToTokenVo(token),
                    userMapper.psysUserRecordToUserVo(user)
            );
        } else {
            user = dsl.newRecord(PSYS_USER);
            account = dsl.newRecord(PSYS_ACCOUNT);
        }
        return new UserAggImpl(
                userMapper.psysAccountRecordToAccountVo(account),
                userMapper.psysTokenRecordToTokenVo(null),
                userMapper.psysUserRecordToUserVo(user)
        );
    }

    @Override
    public @NonNull UserAgg findById(Long userId) {
        var user = dsl;
        return null;
    }

    @Override
    public @NonNull UserAgg findByUsername(UsernameVo username) {
        return null;
    }

    @Override
    public @NonNull TokenVo createToken(Long userId) {
        return null;
    }

    @Nullable
    @Override
    public TokenVo refreshToken(Long userId, TokenVo oldToken) {
        return null;
    }

    @Override
    public void save(UserAgg user) {
    }

    @Override
    @NonNull
    public Long nextUserId() {
        return 0L;
    }

    @Override
    @NonNull
    public Long nextAccountId() {
        return 0L;
    }
}
