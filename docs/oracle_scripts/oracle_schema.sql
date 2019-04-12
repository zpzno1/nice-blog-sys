/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2019/4/12 12:40:41                           */
/*==============================================================*/


drop table KIWIPEACH.R_ROLE_PERMISSION cascade constraints;

drop table KIWIPEACH.R_TAG_BLOG cascade constraints;

drop table KIWIPEACH.R_USER_ROLE cascade constraints;

drop table KIWIPEACH.SYS_ACCESSLOG cascade constraints;

drop table KIWIPEACH.SYS_FUNCTION cascade constraints;

drop table KIWIPEACH.SYS_PARAM cascade constraints;

drop table KIWIPEACH.SYS_PERMISSION cascade constraints;

drop table KIWIPEACH.SYS_ROLE cascade constraints;

drop table KIWIPEACH.SYS_USER cascade constraints;

drop table KIWIPEACH.T_BLOG cascade constraints;

drop table KIWIPEACH.T_BLOG_CATEGORY cascade constraints;

drop table KIWIPEACH.T_BLOG_TAG cascade constraints;

drop table KIWIPEACH.T_COMMENT_REPLY cascade constraints;

drop user KIWIPEACH;

/*==============================================================*/
/* User: KIWIPEACH                                              */
/*==============================================================*/
create user KIWIPEACH 
  identified by "";

/*==============================================================*/
/* Table: R_ROLE_PERMISSION                                     */
/*==============================================================*/
create table KIWIPEACH.R_ROLE_PERMISSION 
(
   ROLE_ID              VARCHAR2(32)         not null,
   PERMISSION_ID        VARCHAR2(32)         not null,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_R_USER_PERMISSIO primary key (ROLE_ID, PERMISSION_ID)
);

comment on table KIWIPEACH.R_ROLE_PERMISSION is
'角色VS权限';

comment on column KIWIPEACH.R_ROLE_PERMISSION.ROLE_ID is
'角色编号';

comment on column KIWIPEACH.R_ROLE_PERMISSION.PERMISSION_ID is
'权限标志编号';

comment on column KIWIPEACH.R_ROLE_PERMISSION.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.R_ROLE_PERMISSION.UPDATE_TIME is
'修改时间';

/*==============================================================*/
/* Table: R_TAG_BLOG                                            */
/*==============================================================*/
create table KIWIPEACH.R_TAG_BLOG 
(
   TAG_ID               VARCHAR2(32)         not null,
   BLOG_ID              VARCHAR2(32)         not null,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_R_TAG_BLOG primary key (TAG_ID, BLOG_ID)
);

comment on table KIWIPEACH.R_TAG_BLOG is
'博客VS标签';

comment on column KIWIPEACH.R_TAG_BLOG.TAG_ID is
'标签编号';

comment on column KIWIPEACH.R_TAG_BLOG.BLOG_ID is
'博客编号';

comment on column KIWIPEACH.R_TAG_BLOG.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.R_TAG_BLOG.UPDATE_TIME is
'修改时间';

/*==============================================================*/
/* Table: R_USER_ROLE                                           */
/*==============================================================*/
create table KIWIPEACH.R_USER_ROLE 
(
   USER_ID              VARCHAR2(32)         not null,
   ROLE_ID              VARCHAR2(32)         not null,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_R_USER_ROLE primary key (ROLE_ID, USER_ID)
);

comment on table KIWIPEACH.R_USER_ROLE is
'用户VS角色';

comment on column KIWIPEACH.R_USER_ROLE.USER_ID is
'用户编号';

comment on column KIWIPEACH.R_USER_ROLE.ROLE_ID is
'角色编号';

comment on column KIWIPEACH.R_USER_ROLE.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.R_USER_ROLE.UPDATE_TIME is
'修改时间';

/*==============================================================*/
/* Table: SYS_ACCESSLOG                                         */
/*==============================================================*/
create table KIWIPEACH.SYS_ACCESSLOG 
(
   ID                   VARCHAR2(32)         not null,
   USER_NAME            VARCHAR2(32),
   IP                   VARCHAR2(255),
   START_TIME           DATE                 default NULL,
   URL                  VARCHAR2(300),
   METHOD               VARCHAR2(255),
   REQUEST_BODY         CLOB,
   RESPONSE_BODY        CLOB,
   STATUS               VARCHAR2(8),
   END_TIME             DATE,
   NAME                 VARCHAR2(64),
   REQUEST_HEADER       CLOB,
   constraint PK_SYS_ACCESSLOG primary key (ID)
);

comment on table KIWIPEACH.SYS_ACCESSLOG is
'系统访问日志';

comment on column KIWIPEACH.SYS_ACCESSLOG.ID is
'编号';

comment on column KIWIPEACH.SYS_ACCESSLOG.USER_NAME is
'用户编号';

comment on column KIWIPEACH.SYS_ACCESSLOG.IP is
'ip';

comment on column KIWIPEACH.SYS_ACCESSLOG.START_TIME is
'访问开始时间';

comment on column KIWIPEACH.SYS_ACCESSLOG.URL is
'访问地址';

comment on column KIWIPEACH.SYS_ACCESSLOG.METHOD is
'请求方法';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_BODY is
'入参';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE_BODY is
'返回信息';

comment on column KIWIPEACH.SYS_ACCESSLOG.STATUS is
'http相应状态码';

comment on column KIWIPEACH.SYS_ACCESSLOG.END_TIME is
'访问结束时间';

comment on column KIWIPEACH.SYS_ACCESSLOG.NAME is
'统计目标功能名称';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_HEADER is
'请求头信息';

/*==============================================================*/
/* Table: SYS_FUNCTION                                          */
/*==============================================================*/
create table KIWIPEACH.SYS_FUNCTION 
(
   ID                   VARCHAR2(32)         not null,
   LOCATION             VARCHAR2(256),
   TEXT                 VARCHAR2(100)        not null,
   PARENTID             VARCHAR2(32),
   WEIGHT               NUMBER(4,0),
   NODE_TYPE            VARCHAR2(8)          not null,
   ICON                 VARCHAR2(200),
   DESCRIPTION          VARCHAR2(1024),
   PERMISSION_ID        VARCHAR2(100),
   constraint PK_SYS_FUNCTION primary key (ID)
);

comment on table KIWIPEACH.SYS_FUNCTION is
'系统菜单表';

comment on column KIWIPEACH.SYS_FUNCTION.ID is
'功能ID';

comment on column KIWIPEACH.SYS_FUNCTION.LOCATION is
'功能路径(页面或操作)';

comment on column KIWIPEACH.SYS_FUNCTION.TEXT is
'功能名称';

comment on column KIWIPEACH.SYS_FUNCTION.PARENTID is
'父节点编号';

comment on column KIWIPEACH.SYS_FUNCTION.WEIGHT is
'同级权重';

comment on column KIWIPEACH.SYS_FUNCTION.NODE_TYPE is
'节点类型(页面或操作)';

comment on column KIWIPEACH.SYS_FUNCTION.ICON is
'图标';

comment on column KIWIPEACH.SYS_FUNCTION.DESCRIPTION is
'描述信息';

comment on column KIWIPEACH.SYS_FUNCTION.PERMISSION_ID is
'菜单权限标志编号';

/*==============================================================*/
/* Table: SYS_PARAM                                             */
/*==============================================================*/
create table KIWIPEACH.SYS_PARAM 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(16)         not null,
   VALUE                NUMBER(4,0)          not null,
   ENABLE               NUMBER(1,0)          default 1 not null,
   DESCRIPTION          VARCHAR2(200),
   PARENT_ID            VARCHAR2(32),
   TYPE                 VARCHAR2(16)         default 'sys',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE
);

comment on table KIWIPEACH.SYS_PARAM is
'系统参数';

comment on column KIWIPEACH.SYS_PARAM.ID is
'参数编号';

comment on column KIWIPEACH.SYS_PARAM.NAME is
'参数名称';

comment on column KIWIPEACH.SYS_PARAM.VALUE is
'参数值';

comment on column KIWIPEACH.SYS_PARAM.ENABLE is
'是否可用[0:不可用 1可用]';

comment on column KIWIPEACH.SYS_PARAM.DESCRIPTION is
'描述';

comment on column KIWIPEACH.SYS_PARAM.PARENT_ID is
'父节点编号(具有级联属性的编码参数)';

comment on column KIWIPEACH.SYS_PARAM.TYPE is
'sys:系统参数单条 code:编码类型多条 cascade:级联类型多条';

comment on column KIWIPEACH.SYS_PARAM.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.SYS_PARAM.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table KIWIPEACH.SYS_PERMISSION 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(32)         not null,
   WEIGHT               NUMBER(1,0)          default 1,
   ICON                 VARCHAR2(32)         default 'defalut_permission_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   DESCRIPTION          VARCHAR2(32),
   constraint PK_SYS_PERMISSION primary key (ID)
);

comment on table KIWIPEACH.SYS_PERMISSION is
'系统权限';

comment on column KIWIPEACH.SYS_PERMISSION.ID is
'权限编号';

comment on column KIWIPEACH.SYS_PERMISSION.NAME is
'权限英文编码(格式:资源+模块+操作,blog:page:access)';

comment on column KIWIPEACH.SYS_PERMISSION.WEIGHT is
'权限权重';

comment on column KIWIPEACH.SYS_PERMISSION.ICON is
'权限图标';

comment on column KIWIPEACH.SYS_PERMISSION.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.SYS_PERMISSION.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.SYS_PERMISSION.DESCRIPTION is
'权限描述';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_ROLE 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(16)         not null,
   WEIGHT               NUMBER(1,0)          default 1,
   ICON                 VARCHAR2(32)         default 'defalut_role_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   DESCRIPTION          VARCHAR2(200),
   constraint PK_SYS_ROLE primary key (ID)
);

comment on table KIWIPEACH.SYS_ROLE is
'系统角色';

comment on column KIWIPEACH.SYS_ROLE.ID is
'角色编号';

comment on column KIWIPEACH.SYS_ROLE.NAME is
'角色名称';

comment on column KIWIPEACH.SYS_ROLE.WEIGHT is
'权重';

comment on column KIWIPEACH.SYS_ROLE.ICON is
'图标';

comment on column KIWIPEACH.SYS_ROLE.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.SYS_ROLE.UPDATE_TIME is
'更新时间';

comment on column KIWIPEACH.SYS_ROLE.DESCRIPTION is
'角色描述信息';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_USER 
(
   ID                   VARCHAR2(32)         not null,
   THIRD_USER_ID        VARCHAR2(32),
   USER_NAME            VARCHAR2(32),
   PASSWORD             VARCHAR2(64),
   NICK_NAME            VARCHAR2(16)         not null,
   HEAD_URL             VARCHAR2(100)        default 'default_head_url' not null,
   EMAIL                VARCHAR2(32),
   ACCOUNT_LOCK         NUMBER(1,0)          default 0,
   LOCK_REASON          NUMBER(2,0),
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   OPEN_ID              VARCHAR2(32),
   PLATFORM             VARCHAR2(16),
   constraint PK_SYS_USER primary key (ID)
);

comment on table KIWIPEACH.SYS_USER is
'系统用户';

comment on column KIWIPEACH.SYS_USER.ID is
'用户编号（序列生成，可以观看会员注册先后）';

comment on column KIWIPEACH.SYS_USER.THIRD_USER_ID is
'三方账号ID（三方用户编号）';

comment on column KIWIPEACH.SYS_USER.USER_NAME is
'登录账号(三方登陆账号)';

comment on column KIWIPEACH.SYS_USER.PASSWORD is
'密码(本站注册密码，只有注册或者三方登陆完善过信息的用户才有)';

comment on column KIWIPEACH.SYS_USER.NICK_NAME is
'用户昵称（默认取三方平台昵称，或者登陆名称,本地系统则使用博客用户码）';

comment on column KIWIPEACH.SYS_USER.HEAD_URL is
'用户头像 (默认三方平台头像，可后期自行更换头像,本地系统使用默认头像)';

comment on column KIWIPEACH.SYS_USER.EMAIL is
'用户邮箱 (用户信息完善时候提供)';

comment on column KIWIPEACH.SYS_USER.ACCOUNT_LOCK is
'账号是否锁定[0:正常 1:锁定] （对于一些非法账号的控制）';

comment on column KIWIPEACH.SYS_USER.LOCK_REASON is
'账号锁定原因[1:不当言论](改变账号状态的理由)';

comment on column KIWIPEACH.SYS_USER.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.SYS_USER.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.SYS_USER.OPEN_ID is
'本站给其他网站交互的用户唯一标志';

comment on column KIWIPEACH.SYS_USER.PLATFORM is
'平台[qq,gitee,github,system]';

/*==============================================================*/
/* Table: T_BLOG                                                */
/*==============================================================*/
create table KIWIPEACH.T_BLOG 
(
   ID                   VARCHAR2(32)         not null,
   USER_ID              VARCHAR2(32)         not null,
   CATE_ID              VARCHAR2(32)         default '0',
   TITLE                VARCHAR2(400)        not null,
   STAR_COUNT           NUMBER(8,0)          default 0,
   VIEW_COUNT           NUMBER(8,0)          default 0,
   TOP                  NUMBER(1,0)          default 0,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   CONTENT_TYPE         VARCHAR2(8)          default '1',
   INTRODUCTION         VARCHAR2(400),
   ICON_URL             VARCHAR2(200),
   CONTENT              CLOB,
   COMMENT_COUNT        NUMBER(8,0)          default 0,
   constraint PK_T_BLOG primary key (ID)
);

comment on table KIWIPEACH.T_BLOG is
'博客';

comment on column KIWIPEACH.T_BLOG.ID is
'博客编号';

comment on column KIWIPEACH.T_BLOG.USER_ID is
'用户编号';

comment on column KIWIPEACH.T_BLOG.CATE_ID is
'分类编号(0:未分类)';

comment on column KIWIPEACH.T_BLOG.TITLE is
'博客标题';

comment on column KIWIPEACH.T_BLOG.STAR_COUNT is
'博客点赞';

comment on column KIWIPEACH.T_BLOG.VIEW_COUNT is
'浏览总量';

comment on column KIWIPEACH.T_BLOG.TOP is
'是否置顶';

comment on column KIWIPEACH.T_BLOG.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_BLOG.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.T_BLOG.CONTENT_TYPE is
'博客内容类型[0:markdown 1:html）]';

comment on column KIWIPEACH.T_BLOG.INTRODUCTION is
'博客简介';

comment on column KIWIPEACH.T_BLOG.ICON_URL is
'博客图标';

comment on column KIWIPEACH.T_BLOG.CONTENT is
'博客内容（根据内容类型存放不同的内容数据）';

comment on column KIWIPEACH.T_BLOG.COMMENT_COUNT is
'评论数量（无论是评论还是回复都需要对此字段+1）';

/*==============================================================*/
/* Table: T_BLOG_CATEGORY                                       */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_CATEGORY 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(16)         not null,
   WEIGHT               NUMBER(3,0)          default 1,
   ICON                 VARCHAR2(32)         default 'default_category_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   PARENT_ID            VARCHAR2(32),
   constraint PK_T_BLOG_CATEGORY primary key (ID)
);

comment on table KIWIPEACH.T_BLOG_CATEGORY is
'分类';

comment on column KIWIPEACH.T_BLOG_CATEGORY.ID is
'分类编号';

comment on column KIWIPEACH.T_BLOG_CATEGORY.NAME is
'分类名称';

comment on column KIWIPEACH.T_BLOG_CATEGORY.WEIGHT is
'分类同级权重';

comment on column KIWIPEACH.T_BLOG_CATEGORY.ICON is
'分类图标';

comment on column KIWIPEACH.T_BLOG_CATEGORY.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_BLOG_CATEGORY.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.T_BLOG_CATEGORY.PARENT_ID is
'父类分类编号';

/*==============================================================*/
/* Table: T_BLOG_TAG                                            */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_TAG 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(32)         not null,
   WEIGHT               NUMBER(3,0)          default 1,
   ICON                 VARCHAR2(256)        default 'default_tag_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_T_BLOG_TAG primary key (ID)
);

comment on table KIWIPEACH.T_BLOG_TAG is
'标签';

comment on column KIWIPEACH.T_BLOG_TAG.ID is
'标签编号';

comment on column KIWIPEACH.T_BLOG_TAG.NAME is
'标签名称';

comment on column KIWIPEACH.T_BLOG_TAG.WEIGHT is
'标签权重';

comment on column KIWIPEACH.T_BLOG_TAG.ICON is
'标签图标';

comment on column KIWIPEACH.T_BLOG_TAG.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_BLOG_TAG.UPDATE_TIME is
'修改时间';

/*==============================================================*/
/* Table: T_COMMENT_REPLY                                       */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_REPLY 
(
   ID                   VARCHAR2(32)         not null,
   TYPE                 VARCHAR2(32)         default '0' not null,
   PARENT_ID            VARCHAR2(32)         default '0' not null,
   ACTIVE_USER_ID       VARCHAR2(32)         not null,
   PASSIVE_USER_ID      VARCHAR2(32)         not null,
   CONTENT              VARCHAR2(300)        not null,
   DELETED              NUMBER(1,0)          default 0,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   STAR_COUNT           NUMBER(8,0)          default 0,
   REPLY_COUNT          NUMBER(8,0)          default 0,
   constraint PK_T_COMMENT primary key (ID)
);

comment on table KIWIPEACH.T_COMMENT_REPLY is
'评论回复表';

comment on column KIWIPEACH.T_COMMENT_REPLY.ID is
'评论编号';

comment on column KIWIPEACH.T_COMMENT_REPLY.TYPE is
'评论回复类型[B_COMMENT,B_COMMENT_REPLY,B_REPLY_REPLY,L_COMMENT,L_COMMENT_REPLY,L_REPLY_REPLY]';

comment on column KIWIPEACH.T_COMMENT_REPLY.PARENT_ID is
'父亲节点(评论时博客为父节点；回复时评论为父节点)';

comment on column KIWIPEACH.T_COMMENT_REPLY.ACTIVE_USER_ID is
'主动评论或者回复的人';

comment on column KIWIPEACH.T_COMMENT_REPLY.PASSIVE_USER_ID is
'被评论或者回复的人';

comment on column KIWIPEACH.T_COMMENT_REPLY.CONTENT is
'评论内容';

comment on column KIWIPEACH.T_COMMENT_REPLY.DELETED is
'该评论留言是否删除[0:正常 1:删除]';

comment on column KIWIPEACH.T_COMMENT_REPLY.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_COMMENT_REPLY.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.T_COMMENT_REPLY.STAR_COUNT is
'评论回复点赞数量';

comment on column KIWIPEACH.T_COMMENT_REPLY.REPLY_COUNT is
'评论回复统计（注意：只有评论才有此概念，回复无此概念）';

