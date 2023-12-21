create table psys_menu
(
    id              bigint      not null
        constraint psys_menu_pk
            primary key,
    menu_name       varchar(50) not null,
    uri_route       varchar(200),
    component_route varchar(250),
    auth_values     jsonb       not null,
    sort            integer     not null,
    type            char        not null,
    redirect_uri    varchar(250),
    icon            varchar(50),
    link_address    varchar(250),
    hidden          boolean     not null,
    cached          boolean     not null,
    pinned          boolean     not null,
    outer_link      boolean     not null,
    embed           boolean     not null
);

comment on column psys_menu.id is '主键';

comment on column psys_menu.menu_name is '菜单名称';

comment on column psys_menu.uri_route is '路径路由';

comment on column psys_menu.component_route is '组件路由';

comment on column psys_menu.auth_values is '权限标识';

comment on column psys_menu.sort is '排序';

comment on column psys_menu.type is '类型（1菜单 2按钮）';

comment on column psys_menu.redirect_uri is '重定向地址';

comment on column psys_menu.icon is '图标';

comment on column psys_menu.link_address is '链接地址';

comment on column psys_menu.hidden is '是否隐藏';

comment on column psys_menu.cached is '是否缓存';

comment on column psys_menu.pinned is '是否固定';

comment on column psys_menu.outer_link is '是否外链';

comment on column psys_menu.embed is '是否嵌入';

alter table psys_menu
    owner to postgres;

