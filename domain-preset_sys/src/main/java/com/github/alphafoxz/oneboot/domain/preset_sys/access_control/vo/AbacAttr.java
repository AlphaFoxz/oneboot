package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


/**
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:35
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class AbacAttr {

	private String name;
	private String value;

}//end AbacAttr