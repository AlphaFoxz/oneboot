package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


/**
 * 令牌
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class Token {

	/**
	 * 访问令牌
	 */
	private String accessToken;
	/**
	 * 过期时间
	 */
	private java.time.OffsetDateTime expireTime;
	/**
	 * 刷新令牌
	 */
	private String refreshToken;

}//end Token