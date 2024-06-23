package com.github.alphafoxz.oneboot.app.configuration;

import com.github.alphafoxz.oneboot.preset_sys.gen.db.entity.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.db.repo.PsysUserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.OffsetDateTime;
import java.util.TimeZone;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.alphafoxz.oneboot.*.gen.db")
@EntityScan(basePackages = "com.github.alphafoxz.oneboot.*.gen.db")
public class JpaConfiguration {
    @Resource
    HibernateProperties hibernateProperties;
    @Resource
    PsysUserRepo psysUserRepo;

    @PostConstruct
    public void initPresetSys() {
        if (!"create".equalsIgnoreCase(hibernateProperties.getDdlAuto())) {
            return;
        }
        OffsetDateTime now = OffsetDateTime.now(TimeZone.getDefault().toZoneId());
        PsysUser seadmin = new PsysUser()
                .setUsername("security_admin")
                .setPassword("$2a$10$/mcrnAq/CSm4DEsd7U/imexxb32ZGb3rOPrVm0vAJh8/vkzKEtLSu")
                .setNickname("安全管理员")
                .setSubjectId(1704372248082780160L)
                .setAccountId(1706192606767222784L)
                .setCreateTime(now);
        psysUserRepo.save(seadmin);
        PsysUser admin = new PsysUser()
                .setUsername("admin")
                .setPassword("$2a$10$VngEJfSKnryFpWJat2x/yelyXoMIqRZJvNIf0dd.voie4NBP19ZNS")
                .setNickname("系统管理员")
                .setSubjectId(1734113308765720576L)
                .setAccountId(1734113308765720577L)
                .setCreateTime(now);
        psysUserRepo.save(admin);
    }
}
