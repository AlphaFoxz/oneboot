package com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Nickname;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Phone;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UserStatus;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:38
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class User {

	private java.util.Map<String, String> abacAttrs;
	private java.time.OffsetDateTime createTime;
	private Long id;
	private Nickname nickname;
	private Phone phone;
	private UserStatus status;
	private java.time.OffsetDateTime updateTime;
	private Username username;

}//end User