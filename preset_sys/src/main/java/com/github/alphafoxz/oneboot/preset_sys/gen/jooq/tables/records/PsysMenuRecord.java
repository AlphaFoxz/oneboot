/*
 * This file is generated by jOOQ.
 */
package com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.records;


import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.PsysMenu;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysMenuPo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PsysMenuRecord extends UpdatableRecordImpl<PsysMenuRecord> implements Record15<Long, String, String, String, String, Integer, String, String, String, String, Boolean, Boolean, Boolean, Boolean, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>preset_sys.psys_menu.id</code>. 主键
     */
    public PsysMenuRecord setId(@NonNull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.id</code>. 主键
     */
    @NotNull
    @NonNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.menu_name</code>. 菜单名称
     */
    public PsysMenuRecord setMenuName(@NonNull String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.menu_name</code>. 菜单名称
     */
    @NotNull
    @Size(max = 50)
    @NonNull
    public String getMenuName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.uri_route</code>. 路径路由
     */
    public PsysMenuRecord setUriRoute(@Nullable String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.uri_route</code>. 路径路由
     */
    @Size(max = 200)
    @Nullable
    public String getUriRoute() {
        return (String) get(2);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.component_route</code>. 组件路由
     */
    public PsysMenuRecord setComponentRoute(@Nullable String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.component_route</code>. 组件路由
     */
    @Size(max = 250)
    @Nullable
    public String getComponentRoute() {
        return (String) get(3);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.auth_values</code>. 权限标识
     */
    public PsysMenuRecord setAuthValues(@NonNull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.auth_values</code>. 权限标识
     */
    @NotNull
    @NonNull
    public String getAuthValues() {
        return (String) get(4);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.sort</code>. 排序
     */
    public PsysMenuRecord setSort(@NonNull Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.sort</code>. 排序
     */
    @NotNull
    @NonNull
    public Integer getSort() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.type</code>. 类型（1菜单 2按钮）
     */
    public PsysMenuRecord setType(@NonNull String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.type</code>. 类型（1菜单 2按钮）
     */
    @NotNull
    @Size(max = 1)
    @NonNull
    public String getType() {
        return (String) get(6);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.redirect_uri</code>. 重定向地址
     */
    public PsysMenuRecord setRedirectUri(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.redirect_uri</code>. 重定向地址
     */
    @Size(max = 250)
    @Nullable
    public String getRedirectUri() {
        return (String) get(7);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.icon</code>. 图标
     */
    public PsysMenuRecord setIcon(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.icon</code>. 图标
     */
    @Size(max = 50)
    @Nullable
    public String getIcon() {
        return (String) get(8);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.link_address</code>. 链接地址
     */
    public PsysMenuRecord setLinkAddress(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.link_address</code>. 链接地址
     */
    @Size(max = 250)
    @Nullable
    public String getLinkAddress() {
        return (String) get(9);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.hidden</code>. 是否隐藏
     */
    public PsysMenuRecord setHidden(@NonNull Boolean value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.hidden</code>. 是否隐藏
     */
    @NotNull
    @NonNull
    public Boolean getHidden() {
        return (Boolean) get(10);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.cached</code>. 是否缓存
     */
    public PsysMenuRecord setCached(@NonNull Boolean value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.cached</code>. 是否缓存
     */
    @NotNull
    @NonNull
    public Boolean getCached() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.pinned</code>. 是否固定
     */
    public PsysMenuRecord setPinned(@NonNull Boolean value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.pinned</code>. 是否固定
     */
    @NotNull
    @NonNull
    public Boolean getPinned() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.outer_link</code>. 是否外链
     */
    public PsysMenuRecord setOuterLink(@NonNull Boolean value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.outer_link</code>. 是否外链
     */
    @NotNull
    @NonNull
    public Boolean getOuterLink() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>preset_sys.psys_menu.embed</code>. 是否嵌入
     */
    public PsysMenuRecord setEmbed(@NonNull Boolean value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>preset_sys.psys_menu.embed</code>. 是否嵌入
     */
    @NotNull
    @NonNull
    public Boolean getEmbed() {
        return (Boolean) get(14);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NonNull
    public Row15<Long, String, String, String, String, Integer, String, String, String, String, Boolean, Boolean, Boolean, Boolean, Boolean> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    @NonNull
    public Row15<Long, String, String, String, String, Integer, String, String, String, String, Boolean, Boolean, Boolean, Boolean, Boolean> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    @NonNull
    public Field<Long> field1() {
        return PsysMenu.PSYS_MENU.ID;
    }

    @Override
    @NonNull
    public Field<String> field2() {
        return PsysMenu.PSYS_MENU.MENU_NAME;
    }

    @Override
    @NonNull
    public Field<String> field3() {
        return PsysMenu.PSYS_MENU.URI_ROUTE;
    }

    @Override
    @NonNull
    public Field<String> field4() {
        return PsysMenu.PSYS_MENU.COMPONENT_ROUTE;
    }

    @Override
    @NonNull
    public Field<String> field5() {
        return PsysMenu.PSYS_MENU.AUTH_VALUES;
    }

    @Override
    @NonNull
    public Field<Integer> field6() {
        return PsysMenu.PSYS_MENU.SORT;
    }

    @Override
    @NonNull
    public Field<String> field7() {
        return PsysMenu.PSYS_MENU.TYPE;
    }

    @Override
    @NonNull
    public Field<String> field8() {
        return PsysMenu.PSYS_MENU.REDIRECT_URI;
    }

    @Override
    @NonNull
    public Field<String> field9() {
        return PsysMenu.PSYS_MENU.ICON;
    }

    @Override
    @NonNull
    public Field<String> field10() {
        return PsysMenu.PSYS_MENU.LINK_ADDRESS;
    }

    @Override
    @NonNull
    public Field<Boolean> field11() {
        return PsysMenu.PSYS_MENU.HIDDEN;
    }

    @Override
    @NonNull
    public Field<Boolean> field12() {
        return PsysMenu.PSYS_MENU.CACHED;
    }

    @Override
    @NonNull
    public Field<Boolean> field13() {
        return PsysMenu.PSYS_MENU.PINNED;
    }

    @Override
    @NonNull
    public Field<Boolean> field14() {
        return PsysMenu.PSYS_MENU.OUTER_LINK;
    }

    @Override
    @NonNull
    public Field<Boolean> field15() {
        return PsysMenu.PSYS_MENU.EMBED;
    }

    @Override
    @NonNull
    public Long component1() {
        return getId();
    }

    @Override
    @NonNull
    public String component2() {
        return getMenuName();
    }

    @Override
    @Nullable
    public String component3() {
        return getUriRoute();
    }

    @Override
    @Nullable
    public String component4() {
        return getComponentRoute();
    }

    @Override
    @NonNull
    public String component5() {
        return getAuthValues();
    }

    @Override
    @NonNull
    public Integer component6() {
        return getSort();
    }

    @Override
    @NonNull
    public String component7() {
        return getType();
    }

    @Override
    @Nullable
    public String component8() {
        return getRedirectUri();
    }

    @Override
    @Nullable
    public String component9() {
        return getIcon();
    }

    @Override
    @Nullable
    public String component10() {
        return getLinkAddress();
    }

    @Override
    @NonNull
    public Boolean component11() {
        return getHidden();
    }

    @Override
    @NonNull
    public Boolean component12() {
        return getCached();
    }

    @Override
    @NonNull
    public Boolean component13() {
        return getPinned();
    }

    @Override
    @NonNull
    public Boolean component14() {
        return getOuterLink();
    }

    @Override
    @NonNull
    public Boolean component15() {
        return getEmbed();
    }

    @Override
    @NonNull
    public Long value1() {
        return getId();
    }

    @Override
    @NonNull
    public String value2() {
        return getMenuName();
    }

    @Override
    @Nullable
    public String value3() {
        return getUriRoute();
    }

    @Override
    @Nullable
    public String value4() {
        return getComponentRoute();
    }

    @Override
    @NonNull
    public String value5() {
        return getAuthValues();
    }

    @Override
    @NonNull
    public Integer value6() {
        return getSort();
    }

    @Override
    @NonNull
    public String value7() {
        return getType();
    }

    @Override
    @Nullable
    public String value8() {
        return getRedirectUri();
    }

    @Override
    @Nullable
    public String value9() {
        return getIcon();
    }

    @Override
    @Nullable
    public String value10() {
        return getLinkAddress();
    }

    @Override
    @NonNull
    public Boolean value11() {
        return getHidden();
    }

    @Override
    @NonNull
    public Boolean value12() {
        return getCached();
    }

    @Override
    @NonNull
    public Boolean value13() {
        return getPinned();
    }

    @Override
    @NonNull
    public Boolean value14() {
        return getOuterLink();
    }

    @Override
    @NonNull
    public Boolean value15() {
        return getEmbed();
    }

    @Override
    @NonNull
    public PsysMenuRecord value1(@NonNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value2(@NonNull String value) {
        setMenuName(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value3(@Nullable String value) {
        setUriRoute(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value4(@Nullable String value) {
        setComponentRoute(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value5(@NonNull String value) {
        setAuthValues(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value6(@NonNull Integer value) {
        setSort(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value7(@NonNull String value) {
        setType(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value8(@Nullable String value) {
        setRedirectUri(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value9(@Nullable String value) {
        setIcon(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value10(@Nullable String value) {
        setLinkAddress(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value11(@NonNull Boolean value) {
        setHidden(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value12(@NonNull Boolean value) {
        setCached(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value13(@NonNull Boolean value) {
        setPinned(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value14(@NonNull Boolean value) {
        setOuterLink(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord value15(@NonNull Boolean value) {
        setEmbed(value);
        return this;
    }

    @Override
    @NonNull
    public PsysMenuRecord values(@NonNull Long value1, @NonNull String value2, @Nullable String value3, @Nullable String value4, @NonNull String value5, @NonNull Integer value6, @NonNull String value7, @Nullable String value8, @Nullable String value9, @Nullable String value10, @NonNull Boolean value11, @NonNull Boolean value12, @NonNull Boolean value13, @NonNull Boolean value14, @NonNull Boolean value15) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PsysMenuRecord
     */
    public PsysMenuRecord() {
        super(PsysMenu.PSYS_MENU);
    }

    /**
     * Create a detached, initialised PsysMenuRecord
     */
    public PsysMenuRecord(@NonNull Long id, @NonNull String menuName, @Nullable String uriRoute, @Nullable String componentRoute, @NonNull String authValues, @NonNull Integer sort, @NonNull String type, @Nullable String redirectUri, @Nullable String icon, @Nullable String linkAddress, @NonNull Boolean hidden, @NonNull Boolean cached, @NonNull Boolean pinned, @NonNull Boolean outerLink, @NonNull Boolean embed) {
        super(PsysMenu.PSYS_MENU);

        setId(id);
        setMenuName(menuName);
        setUriRoute(uriRoute);
        setComponentRoute(componentRoute);
        setAuthValues(authValues);
        setSort(sort);
        setType(type);
        setRedirectUri(redirectUri);
        setIcon(icon);
        setLinkAddress(linkAddress);
        setHidden(hidden);
        setCached(cached);
        setPinned(pinned);
        setOuterLink(outerLink);
        setEmbed(embed);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PsysMenuRecord
     */
    public PsysMenuRecord(PsysMenuPo value) {
        super(PsysMenu.PSYS_MENU);

        if (value != null) {
            setId(value.id());
            setMenuName(value.menuName());
            setUriRoute(value.uriRoute());
            setComponentRoute(value.componentRoute());
            setAuthValues(value.authValues());
            setSort(value.sort());
            setType(value.type());
            setRedirectUri(value.redirectUri());
            setIcon(value.icon());
            setLinkAddress(value.linkAddress());
            setHidden(value.hidden());
            setCached(value.cached());
            setPinned(value.pinned());
            setOuterLink(value.outerLink());
            setEmbed(value.embed());
            resetChangedOnNotNull();
        }
    }
}