/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/23 21:54:53                           */
/*==============================================================*/


drop table if exists KIWIPEACH.R_TAG_BLOG;

drop table if exists KIWIPEACH.R_USER_PERMISSION;

drop table if exists KIWIPEACH.R_USER_ROLE;

drop table if exists KIWIPEACH.SYS_ACCESSLOG;

drop table if exists KIWIPEACH.SYS_FUNCTION;

drop table if exists KIWIPEACH.SYS_PARAM;

drop table if exists KIWIPEACH.SYS_PERMISSION;

drop table if exists KIWIPEACH.SYS_ROLE;

drop table if exists KIWIPEACH.SYS_USER;

drop table if exists KIWIPEACH.T_BLOG;

drop table if exists KIWIPEACH.T_BLOG_CATEGORY;

drop table if exists KIWIPEACH.T_BLOG_TAG;

drop table if exists KIWIPEACH.T_COMMENT_MSG;

/*==============================================================*/
/* User: KIWIPEACH                                              */
/*==============================================================*/
create user KIWIPEACH;

/*==============================================================*/
/* Table: R_TAG_BLOG                                            */
/*==============================================================*/
create table KIWIPEACH.R_TAG_BLOG
(
   TAG_ID               varchar(32) not null comment '标签编号',
   BLOG_ID              varchar(32) not null comment '博客编号',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (TAG_ID, BLOG_ID)
);

alter table KIWIPEACH.R_TAG_BLOG comment '博客VS标签';

/*==============================================================*/
/* Table: R_USER_PERMISSION                                     */
/*==============================================================*/
create table KIWIPEACH.R_USER_PERMISSION
(
   USER_ID              varchar(32) not null comment '用户编号',
   PERMISSION_ID        varchar(32) not null comment '权限标志编号',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (USER_ID, PERMISSION_ID)
);

alter table KIWIPEACH.R_USER_PERMISSION comment '用户VS权限';

/*==============================================================*/
/* Table: R_USER_ROLE                                           */
/*==============================================================*/
create table KIWIPEACH.R_USER_ROLE
(
   USER_ID              varchar(32) not null comment '用户编号',
   ROLE_ID              varchar(32) not null comment '角色编号',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ROLE_ID, USER_ID)
);

alter table KIWIPEACH.R_USER_ROLE comment '用户VS角色';

/*==============================================================*/
/* Table: SYS_ACCESSLOG                                         */
/*==============================================================*/
create table KIWIPEACH.SYS_ACCESSLOG
(
   ID                   varchar(32) not null comment '编号',
   TIME                 datetime comment '访问时间',
   SESSIONID            varchar(255) comment 'sessionid',
   REFERER              varchar(255) comment '源地址',
   URL                  varchar(300) comment '访问地址',
   IP                   varchar(255) comment 'ip',
   USERAGENT            varchar(255) comment '代理客户端',
   METHOD               varchar(255) comment '请求方法',
   PARAMS               varchar(1000) comment '入参',
   RESPONSE             text comment '返回信息',
   GENERAL              varchar(1000) comment '通常信息',
   REQUEST_HEADER       varchar(1000) comment '请求头信息',
   RESPONSE_HEADER      varchar(1000) comment '响应体信息',
   USERID               varchar(32) comment '用户编号',
   USERNAME             varchar(40) comment '用户名',
   STATUS               numeric(1,0) comment '状态',
   primary key (ID)
);

alter table KIWIPEACH.SYS_ACCESSLOG comment '系统访问日志';

/*==============================================================*/
/* Table: SYS_FUNCTION                                          */
/*==============================================================*/
create table KIWIPEACH.SYS_FUNCTION
(
   ID                   varchar(32) not null comment '功能ID',
   LOCATION             varchar(256) comment '功能路径(页面或操作)',
   TEXT                 varchar(100) not null comment '功能名称',
   PARENTID             varchar(32) comment '父节点编号',
   WEIGHT               numeric(4,0) comment '同级权重',
   NODE_TYPE            varchar(8) not null comment '节点类型(页面或操作)',
   ICON                 varchar(200) comment '图标',
   DESCRIPTION          varchar(1024) comment '描述信息',
   PERMISSION_ID        varchar(100) comment '菜单权限标志编号',
   primary key (ID)
);

alter table KIWIPEACH.SYS_FUNCTION comment '系统菜单表';

/*==============================================================*/
/* Table: SYS_PARAM                                             */
/*==============================================================*/
create table KIWIPEACH.SYS_PARAM
(
   ID                   varchar(32) not null comment '参数编号',
   NAME                 varchar(16) not null comment '参数名称',
   VALUE                numeric(4,0) not null comment '参数值',
   ENABLE               numeric(1,0) not null default 1 comment '是否可用[0:不可用 1可用]',
   DESCRIPTION          varchar(200) comment '描述',
   PARENT_ID            varchar(32) comment '父节点编号(具有级联属性的编码参数)',
   TYPE                 varchar(16) default 'sys' comment 'sys:系统参数单条 code:编码类型多条 cascade:级联类型多条',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '更新时间'
);

alter table KIWIPEACH.SYS_PARAM comment '系统参数';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table KIWIPEACH.SYS_PERMISSION
(
   ID                   varchar(32) not null comment '权限编号',
   NAME                 varchar(16) not null comment '权限名称',
   WEIGHT               numeric(1,0) default 1 comment '权限权重',
   ICON                 varchar(32) default 'defalut_permission_icon' comment '权限图标',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   CODE                 varchar(32) comment '权限英文编码(格式:资源+模块+操作,blog:page:access)',
   primary key (ID)
);

alter table KIWIPEACH.SYS_PERMISSION comment '系统权限';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_ROLE
(
   ID                   varchar(32) not null comment '角色编号',
   NAME                 varchar(16) not null comment '角色名称',
   WEIGHT               numeric(1,0) default 1 comment '权重',
   ICON                 varchar(32) default 'defalut_role_icon' comment '图标',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '更新时间',
   primary key (ID)
);

alter table KIWIPEACH.SYS_ROLE comment '系统角色';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_USER
(
   ID                   varchar(32) not null comment '用户编号（序列生成，可以观看会员注册先后）',
   THIRD_USER_ID        varchar(32) comment '三方账号ID（三方用户编号）',
   USER_NAME            varchar(32) comment '登录账号(三方登陆账号)',
   PASSWORD             varchar(64) comment '密码(本站注册密码，只有注册或者三方登陆完善过信息的用户才有)',
   NICK_NAME            varchar(16) not null comment '用户昵称（默认取三方平台昵称，或者登陆名称,本地系统则使用博客用户码）',
   HEAD_URL             varchar(100) not null default 'default_head_url' comment '用户头像 (默认三方平台头像，可后期自行更换头像,本地系统使用默认头像)',
   EMAIL                varchar(32) comment '用户邮箱 (用户信息完善时候提供)',
   ACCOUNT_LOCK         numeric(1,0) not null default 0 comment '账号是否锁定[0:正常 1:锁定] （对于一些非法账号的控制）',
   LOCK_REASON          numeric(2,0) comment '账号锁定原因[1:不当言论](改变账号状态的理由)',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.SYS_USER comment '系统用户';

/*==============================================================*/
/* Table: T_BLOG                                                */
/*==============================================================*/
create table KIWIPEACH.T_BLOG
(
   ID                   varchar(32) not null comment '博客编号',
   USER_ID              varchar(32) not null comment '用户编号',
   CATE_ID              varchar(32) comment '分类编号',
   TITLE                varchar(50) not null comment '博客标题',
   CONTENT              varchar(4000) not null comment '博客内容',
   STAR_COUNT           numeric(8,0) comment '博客点赞',
   VIEWS                numeric(8,0) comment '浏览总量',
   TOP                  numeric(1,0) comment '是否置顶',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG comment '博客';

/*==============================================================*/
/* Table: T_BLOG_CATEGORY                                       */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_CATEGORY
(
   ID                   varchar(32) not null comment '分类编号',
   NAME                 varchar(16) not null comment '分类名称',
   WEIGHT               numeric(3,0) default 1 comment '分类同级权重',
   ICON                 varchar(32) default 'default_category_icon' comment '分类图标',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_CATEGORY comment '分类';

/*==============================================================*/
/* Table: T_BLOG_TAG                                            */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_TAG
(
   ID                   varchar(32) not null comment '标签编号',
   NAME                 varchar(32) not null comment '标签名称',
   WEIGHT               numeric(3,0) default 1 comment '标签权重',
   ICON                 varchar(256) default 'default_tag_icon' comment '标签图标',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_TAG comment '标签';

/*==============================================================*/
/* Table: T_COMMENT_MSG                                         */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_MSG
(
   ID                   varchar(32) not null comment '评论编号',
   PARENT_COMMENT_TYPE  varchar(32) not null default '0' comment '评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)类型',
   TARGET_ID            varchar(32) not null default '0' comment '评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)编号',
   USER_AID             varchar(32) not null comment '角色A',
   USER_BID             varchar(32) not null comment '角色B',
   CONTENT              varchar(300) not null comment '评论内容',
   DELETED              numeric(1,0) default 0 comment '该评论留言是否删除[0:正常 1:删除]',
   CREATE_TIME          timestamp default NOW() comment '创建时间',
   UPDATE_TIME          timestamp default NOW() comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.T_COMMENT_MSG comment '评论';

