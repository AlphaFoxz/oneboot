package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Token;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 登录成功事件
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class LoginSuccessdEvent {

    private Ip ip;
    private java.time.OffsetDateTime time;
    private Token token;
    private Long userId;
    private Username username;

}//end LoginSuccessdEvent