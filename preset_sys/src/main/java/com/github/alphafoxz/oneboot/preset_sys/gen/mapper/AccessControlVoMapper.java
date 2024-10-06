package com.github.alphafoxz.oneboot.preset_sys.gen.mapper;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

public interface AccessControlVoMapper {
    default String stringToAbacAttrVo(AbacAttrVo source) {
        return source.name();
    }

    default AbacAttrVo abacAttrVoToString(String source) {
        return new AbacAttrVo(null, source);
    }


}
