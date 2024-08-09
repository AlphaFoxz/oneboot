package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


/**
 * 令牌
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
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