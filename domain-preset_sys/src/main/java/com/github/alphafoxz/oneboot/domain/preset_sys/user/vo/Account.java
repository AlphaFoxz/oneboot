package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


/**
 * 账号
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class Account {

	private java.time.OffsetDateTime createTime;
	private Email email;
	private String id;
	private Password password;
	private String phone;
	private java.time.OffsetDateTime updateTime;

}//end Account