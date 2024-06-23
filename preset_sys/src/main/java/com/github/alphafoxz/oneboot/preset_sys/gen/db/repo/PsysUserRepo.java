package com.github.alphafoxz.oneboot.preset_sys.gen.db.repo;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.repo.UserRepo;
import com.github.alphafoxz.oneboot.preset_sys.gen.db.entity.PsysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PsysUserRepo extends JpaRepository<PsysUser, Long>, UserRepo {
    @Query(nativeQuery = true, value = """
            SELECT nextval('preset_sys.psys_users_pk_seq')
            """)
    public Long nextSeqValue();
}
