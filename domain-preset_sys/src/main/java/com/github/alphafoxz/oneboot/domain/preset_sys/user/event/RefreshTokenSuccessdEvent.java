package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Token;

/**
 * 刷新令牌成功事件
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class RefreshTokenSuccessdEvent {

    private Token token;

}//end RefreshTokenSuccessdEvent