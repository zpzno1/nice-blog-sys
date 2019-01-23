/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2019/1/23 21:49:07                           */
/*==============================================================*/


drop table KIWIPEACH.R_TAG_BLOG cascade constraints;

drop table KIWIPEACH.R_USER_PERMISSION cascade constraints;

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

drop table KIWIPEACH.T_COMMENT_MSG cascade constraints;

drop user KIWIPEACH;

/*==============================================================*/
/* User: KIWIPEACH                                              */
/*==============================================================*/
create user KIWIPEACH 
  identified by "";

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
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
/* Table: R_USER_PERMISSION                                     */
/*==============================================================*/
create table KIWIPEACH.R_USER_PERMISSION 
(
   USER_ID              VARCHAR2(32)         not null,
   PERMISSION_ID        VARCHAR2(32)         not null,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_R_USER_PERMISSIO primary key (USER_ID, PERMISSION_ID)
         using index pctfree 10
   initrans 2
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

comment on table KIWIPEACH.R_USER_PERMISSION is
'用户VS权限';

comment on column KIWIPEACH.R_USER_PERMISSION.USER_ID is
'用户编号';

comment on column KIWIPEACH.R_USER_PERMISSION.PERMISSION_ID is
'权限标志编号';

comment on column KIWIPEACH.R_USER_PERMISSION.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.R_USER_PERMISSION.UPDATE_TIME is
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
         using index pctfree 10
   initrans 2
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
   TIME                 DATE,
   SESSIONID            VARCHAR2(255),
   REFERER              VARCHAR2(255),
   URL                  VARCHAR2(300),
   IP                   VARCHAR2(255),
   USERAGENT            VARCHAR2(255),
   METHOD               VARCHAR2(255),
   PARAMS               VARCHAR2(1000),
   RESPONSE             CLOB,
   GENERAL              VARCHAR2(1000),
   REQUEST_HEADER       VARCHAR2(1000),
   RESPONSE_HEADER      VARCHAR2(1000),
   USERID               VARCHAR2(32),
   USERNAME             VARCHAR2(40),
   STATUS               NUMBER(1),
   constraint PK_SYS_ACCESSLOG primary key (ID)
         using index pctfree 10
   initrans 2
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
tablespace KIWIPEACH_DATA
logging
 nocompress
 lob
 (RESPONSE)
    store as
         basicfile
 (tablespace KIWIPEACH_DATA
        chunk 8192
        pctversion 10
 nocache logging
         enable storage in row)
 monitoring
 noparallel;

comment on table KIWIPEACH.SYS_ACCESSLOG is
'系统访问日志';

comment on column KIWIPEACH.SYS_ACCESSLOG.ID is
'编号';

comment on column KIWIPEACH.SYS_ACCESSLOG.TIME is
'访问时间';

comment on column KIWIPEACH.SYS_ACCESSLOG.SESSIONID is
'sessionid';

comment on column KIWIPEACH.SYS_ACCESSLOG.REFERER is
'源地址';

comment on column KIWIPEACH.SYS_ACCESSLOG.URL is
'访问地址';

comment on column KIWIPEACH.SYS_ACCESSLOG.IP is
'ip';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERAGENT is
'代理客户端';

comment on column KIWIPEACH.SYS_ACCESSLOG.METHOD is
'请求方法';

comment on column KIWIPEACH.SYS_ACCESSLOG.PARAMS is
'入参';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE is
'返回信息';

comment on column KIWIPEACH.SYS_ACCESSLOG.GENERAL is
'通常信息';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_HEADER is
'请求头信息';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE_HEADER is
'响应体信息';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERID is
'用户编号';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERNAME is
'用户名';

comment on column KIWIPEACH.SYS_ACCESSLOG.STATUS is
'状态';

/*==============================================================*/
/* Table: SYS_FUNCTION                                          */
/*==============================================================*/
create table KIWIPEACH.SYS_FUNCTION 
(
   ID                   VARCHAR2(32)         not null,
   LOCATION             VARCHAR2(256),
   TEXT                 VARCHAR2(100)        not null,
   PARENTID             VARCHAR2(32),
   WEIGHT               NUMBER(4),
   NODE_TYPE            VARCHAR2(8)          not null,
   ICON                 VARCHAR2(200),
   DESCRIPTION          VARCHAR2(1024),
   PERMISSION_ID        VARCHAR2(100),
   constraint PK_SYS_FUNCTION primary key (ID)
         using index pctfree 10
   initrans 2
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
   VALUE                NUMBER(4)            not null,
   ENABLE               NUMBER(1)            default 1 not null,
   DESCRIPTION          VARCHAR2(200),
   PARENT_ID            VARCHAR2(32),
   TYPE                 VARCHAR2(16)         default 'sys',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
   NAME                 VARCHAR2(16)         not null,
   WEIGHT               NUMBER(1)            default 1,
   ICON                 VARCHAR2(32)         default 'defalut_permission_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   CODE                 VARCHAR2(32),
   constraint PK_SYS_PERMISSION primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

comment on table KIWIPEACH.SYS_PERMISSION is
'系统权限';

comment on column KIWIPEACH.SYS_PERMISSION.ID is
'权限编号';

comment on column KIWIPEACH.SYS_PERMISSION.NAME is
'权限名称';

comment on column KIWIPEACH.SYS_PERMISSION.WEIGHT is
'权限权重';

comment on column KIWIPEACH.SYS_PERMISSION.ICON is
'权限图标';

comment on column KIWIPEACH.SYS_PERMISSION.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.SYS_PERMISSION.UPDATE_TIME is
'修改时间';

comment on column KIWIPEACH.SYS_PERMISSION.CODE is
'权限英文编码(格式:资源+模块+操作,blog:page:access)';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_ROLE 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(16)         not null,
   WEIGHT               NUMBER(1)            default 1,
   ICON                 VARCHAR2(32)         default 'defalut_role_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_SYS_ROLE primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
   ACCOUNT_LOCK         NUMBER(1)            default 0 not null,
   LOCK_REASON          NUMBER(2),
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_SYS_USER primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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

/*==============================================================*/
/* Table: T_BLOG                                                */
/*==============================================================*/
create table KIWIPEACH.T_BLOG 
(
   ID                   VARCHAR2(32)         not null,
   USER_ID              VARCHAR2(32)         not null,
   CATE_ID              VARCHAR2(32),
   TITLE                VARCHAR2(50)         not null,
   CONTENT              VARCHAR2(4000)       not null,
   STAR_COUNT           NUMBER(8),
   VIEWS                NUMBER(8),
   TOP                  NUMBER(1),
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_T_BLOG primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

comment on table KIWIPEACH.T_BLOG is
'博客';

comment on column KIWIPEACH.T_BLOG.ID is
'博客编号';

comment on column KIWIPEACH.T_BLOG.USER_ID is
'用户编号';

comment on column KIWIPEACH.T_BLOG.CATE_ID is
'分类编号';

comment on column KIWIPEACH.T_BLOG.TITLE is
'博客标题';

comment on column KIWIPEACH.T_BLOG.CONTENT is
'博客内容';

comment on column KIWIPEACH.T_BLOG.STAR_COUNT is
'博客点赞';

comment on column KIWIPEACH.T_BLOG.VIEWS is
'浏览总量';

comment on column KIWIPEACH.T_BLOG.TOP is
'是否置顶';

comment on column KIWIPEACH.T_BLOG.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_BLOG.UPDATE_TIME is
'修改时间';

/*==============================================================*/
/* Table: T_BLOG_CATEGORY                                       */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_CATEGORY 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(16)         not null,
   WEIGHT               NUMBER(3)            default 1,
   ICON                 VARCHAR2(32)         default 'default_category_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_T_BLOG_CATEGORY primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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

/*==============================================================*/
/* Table: T_BLOG_TAG                                            */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_TAG 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(32)         not null,
   WEIGHT               NUMBER(3)            default 1,
   ICON                 VARCHAR2(256)        default 'default_tag_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_T_BLOG_TAG primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

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
/* Table: T_COMMENT_MSG                                         */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_MSG 
(
   ID                   VARCHAR2(32)         not null,
   PARENT_COMMENT_TYPE  VARCHAR2(32)         default '0' not null,
   TARGET_ID            VARCHAR2(32)         default '0' not null,
   USER_AID             VARCHAR2(32)         not null,
   USER_BID             VARCHAR2(32)         not null,
   CONTENT              VARCHAR2(300)        not null,
   DELETED              NUMBER(1)            default 0,
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   constraint PK_T_COMMENT primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
   )
   logging
   tablespace KIWIPEACH_DATA
)
pctfree 10
initrans 1
storage
(
    initial 128K
    next 8K
    minextents 1
)
tablespace KIWIPEACH_DATA
logging
 nocompress
 monitoring
 noparallel;

comment on table KIWIPEACH.T_COMMENT_MSG is
'评论';

comment on column KIWIPEACH.T_COMMENT_MSG.ID is
'评论编号';

comment on column KIWIPEACH.T_COMMENT_MSG.PARENT_COMMENT_TYPE is
'评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)类型';

comment on column KIWIPEACH.T_COMMENT_MSG.TARGET_ID is
'评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)编号';

comment on column KIWIPEACH.T_COMMENT_MSG.USER_AID is
'角色A';

comment on column KIWIPEACH.T_COMMENT_MSG.USER_BID is
'角色B';

comment on column KIWIPEACH.T_COMMENT_MSG.CONTENT is
'评论内容';

comment on column KIWIPEACH.T_COMMENT_MSG.DELETED is
'该评论留言是否删除[0:正常 1:删除]';

comment on column KIWIPEACH.T_COMMENT_MSG.CREATE_TIME is
'创建时间';

comment on column KIWIPEACH.T_COMMENT_MSG.UPDATE_TIME is
'修改时间';

