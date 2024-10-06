package com.github.alphafoxz.oneboot.preset_sys.gen.mapper;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.EmailVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.NicknameVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PhoneVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;

public interface UserVoMapper {
    default String stringToEmailVo(EmailVo source) {
        return source.value();
    }

    default EmailVo emailVoToString(String source) {
        return new EmailVo(source);
    }

    default String stringToIpVo(IpVo source) {
        return source.value();
    }

    default IpVo ipVoToString(String source) {
        return new IpVo(source);
    }

    default String stringToNicknameVo(NicknameVo source) {
        return source.value();
    }

    default NicknameVo nicknameVoToString(String source) {
        return new NicknameVo(source);
    }

    default String stringToPasswordVo(PasswordVo source) {
        return source.value();
    }

    default PasswordVo passwordVoToString(String source) {
        return new PasswordVo(source, null);
    }

    default String stringToPhoneVo(PhoneVo source) {
        return source.value();
    }

    default PhoneVo phoneVoToString(String source) {
        return new PhoneVo(source);
    }

    default String stringToUsernameVo(UsernameVo source) {
        return source.value();
    }

    default UsernameVo usernameVoToString(String source) {
        return new UsernameVo(source);
    }


}
