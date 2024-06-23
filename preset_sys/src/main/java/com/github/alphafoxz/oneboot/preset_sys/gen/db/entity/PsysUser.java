package com.github.alphafoxz.oneboot.preset_sys.gen.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import java.time.OffsetDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(schema = "preset_sys")
@SequenceGenerator(name = PsysUser.SEQ_NAME)
@Comment("系统用户表")
public class PsysUser {
    public static final String SEQ_NAME = "psys_users_pk";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PsysUser.SEQ_NAME)
    private Long id;

    @Comment("用户名")
    @Column(nullable = false)
    private String username;

    @Comment("昵称")
    @Column(nullable = false)
    private String nickname;

    @Comment("密码")
    @Column(nullable = false)
    private String password;

    @Comment("账号id")
    @Column(nullable = false, updatable = false)
    private Long accountId;

    @Comment("手机号")
    private String phone;

    @Comment("邮箱")
    private String email;

    @Comment("主体id")
    @Column(nullable = false, updatable = false)
    private Long subjectId;

    @Comment("创建时间")
    @Column(updatable = false)
    private OffsetDateTime createTime;
}
