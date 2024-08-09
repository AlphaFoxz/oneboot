package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.exceptions.OnebootBadRequestException;
import org.springframework.http.HttpStatus;

/**
 * 昵称
 *
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class Nickname {

    private String value;

    public Nickname(String value) {
        if (value.length() > 20) {
            throw new OnebootBadRequestException("昵称不能超过20个字符", HttpStatus.BAD_REQUEST);
        }
        this.value = value;
    }
}//end Nickname