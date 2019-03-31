/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/3/31 23:58:42                           */
/*==============================================================*/


drop table if exists KIWIPEACH.R_ROLE_PERMISSION;

drop table if exists KIWIPEACH.R_TAG_BLOG;

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

drop table if exists KIWIPEACH.T_COMMENT_REPLY;

/*==============================================================*/
/* User: KIWIPEACH                                              */
/*==============================================================*/
create user KIWIPEACH;

/*==============================================================*/
/* Table: R_ROLE_PERMISSION                                     */
/*==============================================================*/
create table KIWIPEACH.R_ROLE_PERMISSION
(
   ROLE_ID              varchar(32) not null comment '角色编号',
   PERMISSION_ID        varchar(32) not null comment '权限标志编号',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   primary key (ROLE_ID, PERMISSION_ID)
);

alter table KIWIPEACH.R_ROLE_PERMISSION comment '角色VS权限';

/*==============================================================*/
/* Table: R_TAG_BLOG                                            */
/*==============================================================*/
create table KIWIPEACH.R_TAG_BLOG
(
   TAG_ID               varchar(32) not null comment '标签编号',
   BLOG_ID              varchar(32) not null comment '博客编号',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   primary key (TAG_ID, BLOG_ID)
);

alter table KIWIPEACH.R_TAG_BLOG comment '博客VS标签';

/*==============================================================*/
/* Table: R_USER_ROLE                                           */
/*==============================================================*/
create table KIWIPEACH.R_USER_ROLE
(
   USER_ID              varchar(32) not null comment '用户编号',
   ROLE_ID              varchar(32) not null comment '角色编号',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   primary key (ROLE_ID, USER_ID)
);

alter table KIWIPEACH.R_USER_ROLE comment '用户VS角色';

/*==============================================================*/
/* Table: SYS_ACCESSLOG                                         */
/*==============================================================*/
create table KIWIPEACH.SYS_ACCESSLOG
(
   ID                   varchar(32) not null comment '编号',
   USER_NAME            varchar(32) comment '用户编号',
   IP                   varchar(255) comment 'ip',
   START_TIME           datetime default NULL comment '访问开始时间',
   URL                  varchar(300) comment '访问地址',
   METHOD               varchar(255) comment '请求方法',
   REQUEST_BODY         text comment '入参',
   RESPONSE_BODY        text comment '返回信息',
   STATUS               varchar(8) comment 'http相应状态码',
   END_TIME             datetime comment '访问结束时间',
   NAME                 varchar(64) comment '统计目标功能名称',
   REQUEST_HEADER       text comment '请求头信息',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '更新时间'
);

alter table KIWIPEACH.SYS_PARAM comment '系统参数';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table KIWIPEACH.SYS_PERMISSION
(
   ID                   varchar(32) not null comment '权限编号',
   NAME                 varchar(32) not null comment '权限英文编码(格式:资源+模块+操作,blog:page:access)',
   WEIGHT               numeric(1,0) default 1 comment '权限权重',
   ICON                 varchar(32) default 'defalut_permission_icon' comment '权限图标',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   DESCRIPTION          varchar(32) comment '权限描述',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '更新时间',
   DESCRIPTION          varchar(200) comment '角色描述信息',
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
   ACCOUNT_LOCK         numeric(1,0) default 0 comment '账号是否锁定[0:正常 1:锁定] （对于一些非法账号的控制）',
   LOCK_REASON          numeric(2,0) comment '账号锁定原因[1:不当言论](改变账号状态的理由)',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   OPEN_ID              varchar(32) comment '本站给其他网站交互的用户唯一标志',
   PLATFORM             varchar(16) comment '平台[qq,gitee,github,system]',
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
   CATE_ID              varchar(32) default '0' comment '分类编号(0:未分类)',
   TITLE                varchar(80) not null comment '博客标题',
   STAR_COUNT           numeric(8,0) default 0 comment '博客点赞',
   VIEW_COUNT           numeric(8,0) default 0 comment '浏览总量',
   TOP                  numeric(1,0) default 0 comment '是否置顶',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   CONTENT_TYPE         varchar(8) default '1' comment '博客内容类型[0:markdown 1:html）]',
   INTRODUCTION         varchar(400) comment '博客简介',
   ICON_URL             varchar(200) comment '博客图标',
   CONTENT              text comment '博客内容（根据内容类型存放不同的内容数据）',
   COMMENT_COUNT        numeric(8,0) comment '评论数量（无论是评论还是回复都需要对此字段+1）',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   PARENT_ID            varchar(32) comment '父类分类编号',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_TAG comment '标签';

/*==============================================================*/
/* Table: T_COMMENT_REPLY                                       */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_REPLY
(
   ID                   varchar(32) not null comment '评论编号',
   TYPE                 varchar(32) not null default '0' comment '评论回复类型[B_COMMENT,B_COMMENT_REPLY,B_REPLY_REPLY,L_COMMENT,L_COMMENT_REPLY,L_REPLY_REPLY]',
   PARENT_ID            varchar(32) not null default '0' comment '父亲节点(评论时博客为父节点；回复时评论为父节点)',
   ACTIVE_USER_ID       varchar(32) not null comment '主动评论或者回复的人',
   PASSIVE_USER_ID      varchar(32) not null comment '被评论或者回复的人',
   CONTENT              varchar(300) not null comment '评论内容',
   DELETED              numeric(1,0) default 0 comment '该评论留言是否删除[0:正常 1:删除]',
   CREATE_TIME          timestamp default 'SYSDATE' comment '创建时间',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '修改时间',
   STAR_COUNT           numeric(8,0) default 0 comment '评论回复点赞数量',
   REPLY_COUNT          numeric(8,0) default 0 comment '评论回复统计（注意：只有评论才有此概念，回复无此概念）',
   primary key (ID)
);

alter table KIWIPEACH.T_COMMENT_REPLY comment '评论回复表';

