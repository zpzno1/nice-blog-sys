prompt PL/SQL Developer import file
prompt Created on 2019年4月12日 by kiwipeach
set feedback off
set define off
prompt Dropping R_ROLE_PERMISSION...
drop table R_ROLE_PERMISSION cascade constraints;
prompt Dropping R_TAG_BLOG...
drop table R_TAG_BLOG cascade constraints;
prompt Dropping R_USER_ROLE...
drop table R_USER_ROLE cascade constraints;
prompt Dropping SYS_FUNCTION...
drop table SYS_FUNCTION cascade constraints;
prompt Dropping SYS_PARAM...
drop table SYS_PARAM cascade constraints;
prompt Dropping SYS_PERMISSION...
drop table SYS_PERMISSION cascade constraints;
prompt Dropping SYS_ROLE...
drop table SYS_ROLE cascade constraints;
prompt Dropping SYS_USER...
drop table SYS_USER cascade constraints;
prompt Dropping T_BLOG_CATEGORY...
drop table T_BLOG_CATEGORY cascade constraints;
prompt Dropping T_BLOG_TAG...
drop table T_BLOG_TAG cascade constraints;
prompt Dropping T_COMMENT_REPLY...
drop table T_COMMENT_REPLY cascade constraints;
prompt Creating R_ROLE_PERMISSION...
create table R_ROLE_PERMISSION
(
  ROLE_ID       VARCHAR2(32) not null,
  PERMISSION_ID VARCHAR2(32) not null,
  CREATE_TIME   TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME   TIMESTAMP(6) default SYSDATE
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table R_ROLE_PERMISSION
  is '角色VS权限';
comment on column R_ROLE_PERMISSION.ROLE_ID
  is '角色编号';
comment on column R_ROLE_PERMISSION.PERMISSION_ID
  is '权限标志编号';
comment on column R_ROLE_PERMISSION.CREATE_TIME
  is '创建时间';
comment on column R_ROLE_PERMISSION.UPDATE_TIME
  is '修改时间';
alter table R_ROLE_PERMISSION
  add constraint PK_R_USER_PERMISSIO primary key (ROLE_ID, PERMISSION_ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating R_TAG_BLOG...
create table R_TAG_BLOG
(
  TAG_ID      VARCHAR2(32) not null,
  BLOG_ID     VARCHAR2(32) not null,
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table R_TAG_BLOG
  is '博客VS标签';
comment on column R_TAG_BLOG.TAG_ID
  is '标签编号';
comment on column R_TAG_BLOG.BLOG_ID
  is '博客编号';
comment on column R_TAG_BLOG.CREATE_TIME
  is '创建时间';
comment on column R_TAG_BLOG.UPDATE_TIME
  is '修改时间';
alter table R_TAG_BLOG
  add constraint PK_R_TAG_BLOG primary key (TAG_ID, BLOG_ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating R_USER_ROLE...
create table R_USER_ROLE
(
  USER_ID     VARCHAR2(32) not null,
  ROLE_ID     VARCHAR2(32) not null,
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table R_USER_ROLE
  is '用户VS角色';
comment on column R_USER_ROLE.USER_ID
  is '用户编号';
comment on column R_USER_ROLE.ROLE_ID
  is '角色编号';
comment on column R_USER_ROLE.CREATE_TIME
  is '创建时间';
comment on column R_USER_ROLE.UPDATE_TIME
  is '修改时间';
alter table R_USER_ROLE
  add constraint PK_R_USER_ROLE primary key (ROLE_ID, USER_ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_FUNCTION...
create table SYS_FUNCTION
(
  ID            VARCHAR2(32) not null,
  LOCATION      VARCHAR2(256),
  TEXT          VARCHAR2(100) not null,
  PARENTID      VARCHAR2(32),
  WEIGHT        NUMBER(4),
  NODE_TYPE     VARCHAR2(8) not null,
  ICON          VARCHAR2(200),
  DESCRIPTION   VARCHAR2(1024),
  PERMISSION_ID VARCHAR2(100)
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table SYS_FUNCTION
  is '系统菜单表';
comment on column SYS_FUNCTION.ID
  is '功能ID';
comment on column SYS_FUNCTION.LOCATION
  is '功能路径(页面或操作)';
comment on column SYS_FUNCTION.TEXT
  is '功能名称';
comment on column SYS_FUNCTION.PARENTID
  is '父节点编号';
comment on column SYS_FUNCTION.WEIGHT
  is '同级权重';
comment on column SYS_FUNCTION.NODE_TYPE
  is '节点类型(页面或操作)';
comment on column SYS_FUNCTION.ICON
  is '图标';
comment on column SYS_FUNCTION.DESCRIPTION
  is '描述信息';
comment on column SYS_FUNCTION.PERMISSION_ID
  is '菜单权限标志编号';
alter table SYS_FUNCTION
  add constraint PK_SYS_FUNCTION primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_PARAM...
create table SYS_PARAM
(
  ID          VARCHAR2(32) not null,
  NAME        VARCHAR2(16) not null,
  VALUE       NUMBER(4) not null,
  ENABLE      NUMBER(1) default 1 not null,
  DESCRIPTION VARCHAR2(200),
  PARENT_ID   VARCHAR2(32),
  TYPE        VARCHAR2(16) default 'sys',
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table SYS_PARAM
  is '系统参数';
comment on column SYS_PARAM.ID
  is '参数编号';
comment on column SYS_PARAM.NAME
  is '参数名称';
comment on column SYS_PARAM.VALUE
  is '参数值';
comment on column SYS_PARAM.ENABLE
  is '是否可用[0:不可用 1可用]';
comment on column SYS_PARAM.DESCRIPTION
  is '描述';
comment on column SYS_PARAM.PARENT_ID
  is '父节点编号(具有级联属性的编码参数)';
comment on column SYS_PARAM.TYPE
  is 'sys:系统参数单条 code:编码类型多条 cascade:级联类型多条';
comment on column SYS_PARAM.CREATE_TIME
  is '创建时间';
comment on column SYS_PARAM.UPDATE_TIME
  is '更新时间';

prompt Creating SYS_PERMISSION...
create table SYS_PERMISSION
(
  ID          VARCHAR2(32) not null,
  NAME        VARCHAR2(32) not null,
  WEIGHT      NUMBER(1) default 1,
  ICON        VARCHAR2(32) default 'defalut_permission_icon',
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE,
  DESCRIPTION VARCHAR2(32)
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table SYS_PERMISSION
  is '系统权限';
comment on column SYS_PERMISSION.ID
  is '权限编号';
comment on column SYS_PERMISSION.NAME
  is '权限英文编码(格式:资源+模块+操作,blog:page:access)';
comment on column SYS_PERMISSION.WEIGHT
  is '权限权重';
comment on column SYS_PERMISSION.ICON
  is '权限图标';
comment on column SYS_PERMISSION.CREATE_TIME
  is '创建时间';
comment on column SYS_PERMISSION.UPDATE_TIME
  is '修改时间';
comment on column SYS_PERMISSION.DESCRIPTION
  is '权限描述';
alter table SYS_PERMISSION
  add constraint PK_SYS_PERMISSION primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  ID          VARCHAR2(32) not null,
  NAME        VARCHAR2(16) not null,
  WEIGHT      NUMBER(1) default 1,
  ICON        VARCHAR2(32) default 'defalut_role_icon',
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE,
  DESCRIPTION VARCHAR2(200)
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table SYS_ROLE
  is '系统角色';
comment on column SYS_ROLE.ID
  is '角色编号';
comment on column SYS_ROLE.NAME
  is '角色名称';
comment on column SYS_ROLE.WEIGHT
  is '权重';
comment on column SYS_ROLE.ICON
  is '图标';
comment on column SYS_ROLE.CREATE_TIME
  is '创建时间';
comment on column SYS_ROLE.UPDATE_TIME
  is '更新时间';
comment on column SYS_ROLE.DESCRIPTION
  is '角色描述信息';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_USER...
create table SYS_USER
(
  ID            VARCHAR2(32) not null,
  THIRD_USER_ID VARCHAR2(32),
  USER_NAME     VARCHAR2(32),
  PASSWORD      VARCHAR2(64),
  NICK_NAME     VARCHAR2(16) not null,
  HEAD_URL      VARCHAR2(100) default 'default_head_url' not null,
  EMAIL         VARCHAR2(32),
  ACCOUNT_LOCK  NUMBER(1) default 0,
  LOCK_REASON   NUMBER(2),
  CREATE_TIME   TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME   TIMESTAMP(6) default SYSDATE,
  OPEN_ID       VARCHAR2(32),
  PLATFORM      VARCHAR2(16)
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table SYS_USER
  is '系统用户';
comment on column SYS_USER.ID
  is '用户编号（序列生成，可以观看会员注册先后）';
comment on column SYS_USER.THIRD_USER_ID
  is '三方账号ID（三方用户编号）';
comment on column SYS_USER.USER_NAME
  is '登录账号(三方登陆账号)';
comment on column SYS_USER.PASSWORD
  is '密码(本站注册密码，只有注册或者三方登陆完善过信息的用户才有)';
comment on column SYS_USER.NICK_NAME
  is '用户昵称（默认取三方平台昵称，或者登陆名称,本地系统则使用博客用户码）';
comment on column SYS_USER.HEAD_URL
  is '用户头像 (默认三方平台头像，可后期自行更换头像,本地系统使用默认头像)';
comment on column SYS_USER.EMAIL
  is '用户邮箱 (用户信息完善时候提供)';
comment on column SYS_USER.ACCOUNT_LOCK
  is '账号是否锁定[0:正常 1:锁定] （对于一些非法账号的控制）';
comment on column SYS_USER.LOCK_REASON
  is '账号锁定原因[1:不当言论](改变账号状态的理由)';
comment on column SYS_USER.CREATE_TIME
  is '创建时间';
comment on column SYS_USER.UPDATE_TIME
  is '修改时间';
comment on column SYS_USER.OPEN_ID
  is '本站给其他网站交互的用户唯一标志';
comment on column SYS_USER.PLATFORM
  is '平台[qq,gitee,github,system]';
alter table SYS_USER
  add constraint PK_SYS_USER primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_BLOG_CATEGORY...
create table T_BLOG_CATEGORY
(
  ID          VARCHAR2(32) not null,
  NAME        VARCHAR2(16) not null,
  WEIGHT      NUMBER(3) default 1,
  ICON        VARCHAR2(32) default 'default_category_icon',
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE,
  PARENT_ID   VARCHAR2(32)
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_BLOG_CATEGORY
  is '分类';
comment on column T_BLOG_CATEGORY.ID
  is '分类编号';
comment on column T_BLOG_CATEGORY.NAME
  is '分类名称';
comment on column T_BLOG_CATEGORY.WEIGHT
  is '分类同级权重';
comment on column T_BLOG_CATEGORY.ICON
  is '分类图标';
comment on column T_BLOG_CATEGORY.CREATE_TIME
  is '创建时间';
comment on column T_BLOG_CATEGORY.UPDATE_TIME
  is '修改时间';
comment on column T_BLOG_CATEGORY.PARENT_ID
  is '父类分类编号';
alter table T_BLOG_CATEGORY
  add constraint PK_T_BLOG_CATEGORY primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_BLOG_TAG...
create table T_BLOG_TAG
(
  ID          VARCHAR2(32) not null,
  NAME        VARCHAR2(32) not null,
  WEIGHT      NUMBER(3) default 1,
  ICON        VARCHAR2(256) default 'default_tag_icon',
  CREATE_TIME TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME TIMESTAMP(6) default SYSDATE
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_BLOG_TAG
  is '标签';
comment on column T_BLOG_TAG.ID
  is '标签编号';
comment on column T_BLOG_TAG.NAME
  is '标签名称';
comment on column T_BLOG_TAG.WEIGHT
  is '标签权重';
comment on column T_BLOG_TAG.ICON
  is '标签图标';
comment on column T_BLOG_TAG.CREATE_TIME
  is '创建时间';
comment on column T_BLOG_TAG.UPDATE_TIME
  is '修改时间';
alter table T_BLOG_TAG
  add constraint PK_T_BLOG_TAG primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_COMMENT_REPLY...
create table T_COMMENT_REPLY
(
  ID              VARCHAR2(32) not null,
  TYPE            VARCHAR2(32) default 0 not null,
  PARENT_ID       VARCHAR2(32) default 0 not null,
  ACTIVE_USER_ID  VARCHAR2(32) not null,
  PASSIVE_USER_ID VARCHAR2(32) not null,
  CONTENT         VARCHAR2(300) not null,
  DELETED         NUMBER(1) default 0,
  CREATE_TIME     TIMESTAMP(6) default SYSDATE,
  UPDATE_TIME     TIMESTAMP(6) default SYSDATE,
  STAR_COUNT      NUMBER(8) default 0,
  REPLY_COUNT     NUMBER(8) default 0
)
tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_COMMENT_REPLY
  is '评论回复表';
comment on column T_COMMENT_REPLY.ID
  is '评论编号';
comment on column T_COMMENT_REPLY.TYPE
  is '评论回复类型[B_COMMENT,B_COMMENT_REPLY,B_REPLY_REPLY,L_COMMENT,L_COMMENT_REPLY,L_REPLY_REPLY]';
comment on column T_COMMENT_REPLY.PARENT_ID
  is '父亲节点(评论时博客为父节点；回复时评论为父节点)';
comment on column T_COMMENT_REPLY.ACTIVE_USER_ID
  is '主动评论或者回复的人';
comment on column T_COMMENT_REPLY.PASSIVE_USER_ID
  is '被评论或者回复的人';
comment on column T_COMMENT_REPLY.CONTENT
  is '评论内容';
comment on column T_COMMENT_REPLY.DELETED
  is '该评论留言是否删除[0:正常 1:删除]';
comment on column T_COMMENT_REPLY.CREATE_TIME
  is '创建时间';
comment on column T_COMMENT_REPLY.UPDATE_TIME
  is '修改时间';
comment on column T_COMMENT_REPLY.STAR_COUNT
  is '评论回复点赞数量';
comment on column T_COMMENT_REPLY.REPLY_COUNT
  is '评论回复统计（注意：只有评论才有此概念，回复无此概念）';
alter table T_COMMENT_REPLY
  add constraint PK_T_COMMENT primary key (ID)
  using index 
  tablespace KIWIPEACH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for R_ROLE_PERMISSION...
alter table R_ROLE_PERMISSION disable all triggers;
prompt Disabling triggers for R_TAG_BLOG...
alter table R_TAG_BLOG disable all triggers;
prompt Disabling triggers for R_USER_ROLE...
alter table R_USER_ROLE disable all triggers;
prompt Disabling triggers for SYS_FUNCTION...
alter table SYS_FUNCTION disable all triggers;
prompt Disabling triggers for SYS_PARAM...
alter table SYS_PARAM disable all triggers;
prompt Disabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for T_BLOG_CATEGORY...
alter table T_BLOG_CATEGORY disable all triggers;
prompt Disabling triggers for T_BLOG_TAG...
alter table T_BLOG_TAG disable all triggers;
prompt Disabling triggers for T_COMMENT_REPLY...
alter table T_COMMENT_REPLY disable all triggers;
prompt Loading R_ROLE_PERMISSION...
insert into R_ROLE_PERMISSION (ROLE_ID, PERMISSION_ID, CREATE_TIME, UPDATE_TIME)
values ('1001', '1028303318796029953', to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_ROLE_PERMISSION (ROLE_ID, PERMISSION_ID, CREATE_TIME, UPDATE_TIME)
values ('1002', '1028303318796029953', to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_ROLE_PERMISSION (ROLE_ID, PERMISSION_ID, CREATE_TIME, UPDATE_TIME)
values ('1001', '1028303318796029954', to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_ROLE_PERMISSION (ROLE_ID, PERMISSION_ID, CREATE_TIME, UPDATE_TIME)
values ('1002', '1028303318796029954', to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 21:12:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 4 records loaded
prompt Loading R_TAG_BLOG...
insert into R_TAG_BLOG (TAG_ID, BLOG_ID, CREATE_TIME, UPDATE_TIME)
values ('1001', '109950', to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_TAG_BLOG (TAG_ID, BLOG_ID, CREATE_TIME, UPDATE_TIME)
values ('1003', '109950', to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_TAG_BLOG (TAG_ID, BLOG_ID, CREATE_TIME, UPDATE_TIME)
values ('1001', '1099529205357580289', to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_TAG_BLOG (TAG_ID, BLOG_ID, CREATE_TIME, UPDATE_TIME)
values ('1004', '1099529205357580289', to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:58:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 4 records loaded
prompt Loading R_USER_ROLE...
insert into R_USER_ROLE (USER_ID, ROLE_ID, CREATE_TIME, UPDATE_TIME)
values ('1107167437933723649', '1001', to_timestamp('17-03-2019 14:31:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 14:31:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_USER_ROLE (USER_ID, ROLE_ID, CREATE_TIME, UPDATE_TIME)
values ('1107167387618852865', '1001', to_timestamp('17-03-2019 14:30:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 14:30:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into R_USER_ROLE (USER_ID, ROLE_ID, CREATE_TIME, UPDATE_TIME)
values ('1107134791473442818', '1001', to_timestamp('17-03-2019 12:21:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 12:21:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 3 records loaded
prompt Loading SYS_FUNCTION...
prompt Table is empty
prompt Loading SYS_PARAM...
prompt Table is empty
prompt Loading SYS_PERMISSION...
insert into SYS_PERMISSION (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, DESCRIPTION)
values ('1028303318796029953', 'blog:comment:create', 1, 'icon-1.png', to_timestamp('11-08-2018 23:33:11.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-08-2018 23:33:11.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '博客评论权限');
insert into SYS_PERMISSION (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, DESCRIPTION)
values ('1028303318796029954', 'blog:page:about', 2, 'icon-1.png', to_timestamp('16-03-2019 21:08:46.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 21:08:46.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '关于页面查看权限');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, DESCRIPTION)
values ('1001', 'BlogUser', 1, 'defalut_role_icon', to_timestamp('22-01-2019 11:36:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-01-2019 11:36:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '本站普通会员，拥有查看更多博主博客信息的权力。');
insert into SYS_ROLE (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, DESCRIPTION)
values ('1002', 'admin', 1, 'defalut_role_icon', to_timestamp('22-01-2019 11:36:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-01-2019 11:36:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '本博客管理员权限');
commit;
prompt 2 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (ID, THIRD_USER_ID, USER_NAME, PASSWORD, NICK_NAME, HEAD_URL, EMAIL, ACCOUNT_LOCK, LOCK_REASON, CREATE_TIME, UPDATE_TIME, OPEN_ID, PLATFORM)
values ('1107167437933723649', '6FF96B97CF726B2D1DD31798135782FA', 'qq_kiwipeach', null, 'kiwipeach9527', 'http://qzapp.qlogo.cn/qzapp/101495727/6FF96B97CF726B2D1DD31798135782FA/30', null, 0, null, to_timestamp('17-03-2019 14:31:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 14:31:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'qq');
insert into SYS_USER (ID, THIRD_USER_ID, USER_NAME, PASSWORD, NICK_NAME, HEAD_URL, EMAIL, ACCOUNT_LOCK, LOCK_REASON, CREATE_TIME, UPDATE_TIME, OPEN_ID, PLATFORM)
values ('1', null, 'kiwipeach', '3a322406a9067b292325e13989c404dd1bf38ebf', '刘卜铷', 'https://gitee.com/uploads/78/1387578_kakaluote444.png', '1099501218@qq.com', 0, null, to_timestamp('28-02-2019 23:28:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 23:28:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'system');
insert into SYS_USER (ID, THIRD_USER_ID, USER_NAME, PASSWORD, NICK_NAME, HEAD_URL, EMAIL, ACCOUNT_LOCK, LOCK_REASON, CREATE_TIME, UPDATE_TIME, OPEN_ID, PLATFORM)
values ('1107167387618852865', 'MDQ6VXNlcjE1NjY4ODc1', 'github_kakaluote444', null, 'kakaluote444', 'https://avatars2.githubusercontent.com/u/15668875?v=4', null, 0, null, to_timestamp('17-03-2019 14:30:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 14:30:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'github');
insert into SYS_USER (ID, THIRD_USER_ID, USER_NAME, PASSWORD, NICK_NAME, HEAD_URL, EMAIL, ACCOUNT_LOCK, LOCK_REASON, CREATE_TIME, UPDATE_TIME, OPEN_ID, PLATFORM)
values ('1107134791473442818', '1387578', 'gitee_KiWiPeach', null, 'kiwipeach', 'https://avatar.gitee.com/uploads/78/1387578_kakaluote444.png?1508983211', null, 0, null, to_timestamp('17-03-2019 12:21:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 12:21:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'gitee');
commit;
prompt 4 records loaded
prompt Loading T_BLOG_CATEGORY...
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1011', '棉宝', 1, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '101');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1012', '点评', 2, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '101');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1013', '感悟', 3, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '101');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1021', 'Java', 1, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '102');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1022', 'Spring Framework', 2, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '102');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1023', 'SpringBoot', 3, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '102');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1031', 'JavaScript', 1, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '103');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1032', 'JQuery', 2, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '103');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1041', 'Git', 4, 'default_category_icon', to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:23:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '104');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('106', '开发工具', 7, 'default_category_icon', to_timestamp('28-02-2019 22:28:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:28:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1051', 'Oracle', 1, 'default_category_icon', to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '105');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1052', 'MySQL', 2, 'default_category_icon', to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '105');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1053', 'MongoDB', 3, 'default_category_icon', to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '105');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1054', 'H2', 4, 'default_category_icon', to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:34:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '105');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1042', 'Linux', 1, 'default_category_icon', to_timestamp('28-02-2019 22:38:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:38:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '104');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1043', 'Docker', 2, 'default_category_icon', to_timestamp('28-02-2019 22:38:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:38:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '104');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1062', 'Google', 2, 'default_category_icon', to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '106');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1061', 'IDEA', 1, 'default_category_icon', to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '106');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('1063', 'Windows', 3, 'default_category_icon', to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-02-2019 22:39:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '106');
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('0', '默认分类', 1, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('101', '生活类', 2, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('102', '后端技术', 3, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('103', '前端技术', 4, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('104', '运维部署', 5, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into T_BLOG_CATEGORY (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME, PARENT_ID)
values ('105', '数据库', 6, 'default_category_icon', to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 23:00:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
commit;
prompt 25 records loaded
prompt Loading T_BLOG_TAG...
insert into T_BLOG_TAG (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME)
values ('1001', 'Java', 1, 'default_tag_icon', to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into T_BLOG_TAG (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME)
values ('1002', 'JavaScript', 2, 'default_tag_icon', to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into T_BLOG_TAG (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME)
values ('1003', 'Python', 3, 'default_tag_icon', to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into T_BLOG_TAG (ID, NAME, WEIGHT, ICON, CREATE_TIME, UPDATE_TIME)
values ('1004', 'Oracle', 4, 'default_tag_icon', to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('24-02-2019 22:48:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 4 records loaded
prompt Loading T_COMMENT_REPLY...
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107293961384480769', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲的', 0, to_timestamp('17-03-2019 22:53:47.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:53:47.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107294005927989250', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '范德萨范德萨发', 0, to_timestamp('17-03-2019 22:53:58.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:53:58.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107294076203552770', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '555555555555555', 0, to_timestamp('17-03-2019 22:54:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:54:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107987801812619265', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '999', 0, to_timestamp('19-03-2019 20:50:52.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('19-03-2019 20:50:52.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108747276324483074', 'B_COMMENT_REPLY', '1108742773005135873', '1107134791473442818', '1108742773005135873', '666', 0, to_timestamp('21-03-2019 23:08:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:08:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108747514326069250', 'B_COMMENT_REPLY', '1108742773005135873', '1107134791473442818', '1108742773005135873', '342432', 0, to_timestamp('21-03-2019 23:09:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:09:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108747326299615233', 'B_COMMENT_REPLY', '1108742773005135873', '1107134791473442818', '1108742773005135873', '777', 0, to_timestamp('21-03-2019 23:08:56.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:08:56.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108749266400096258', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '不到黄河心不死', 0, to_timestamp('21-03-2019 23:16:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:16:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108759989675143169', 'B_COMMENT_REPLY', '1108749266400096258', '1107134791473442818', '1108749266400096258', '354254', 0, to_timestamp('21-03-2019 23:59:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:59:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109095448100052994', '0', '0', '1107134791473442818', '1107167437933723649', '曾经沧海难为水', 0, to_timestamp('22-03-2019 22:12:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 22:12:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109295079975108610', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '9535454', 0, to_timestamp('23-03-2019 11:25:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:25:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109295288029364225', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:26:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:26:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109295404144476162', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:26:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:26:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109295702388850689', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:27:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:27:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109295813848285185', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:28:26.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:28:26.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109296017888591874', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:29:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:29:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109344405416534017', 'B_COMMENT_REPLY', '1109305938642288642', '1107167387618852865', '1107167437933723649', '你好世界', 0, to_timestamp('23-03-2019 14:41:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 14:41:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109345645479288833', 'B_COMMENT_REPLY', '1109305938642288642', '1107167387618852865', '1107167437933723649', '5555', 0, to_timestamp('23-03-2019 14:46:27.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 14:46:27.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109345693290160130', 'B_COMMENT_REPLY', '1109305938642288642', '1107167387618852865', '1107167437933723649', '22432432', 0, to_timestamp('23-03-2019 14:46:38.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 14:46:38.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111190315159195649', 'B_COMMENT_REPLY', '1109305938642288642', '1107134791473442818', '1107167437933723649', '555', 0, to_timestamp('28-03-2019 16:56:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 16:56:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 1);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108759846263500802', 'B_COMMENT_REPLY', '1108749266400096258', '1107134791473442818', '1108749266400096258', '354254', 0, to_timestamp('21-03-2019 23:58:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:58:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1001', 'B_BLOG_COMMENT', 'B001', '1107134791473442818', '卜铷', '你的博客很漂亮', 0, to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1002', 'BLOG_REPLY', '1001', '1107134791473442818', '小红', '谢谢夸奖^_^ ', 0, to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1003', 'B_BLOG_COMMENT', 'B001', '1107134791473442818', '卜铷', '你做了多久呀？这么漂亮的博客', 0, to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1004', 'BLOG_REPLY', '1001', '1107134791473442818', '小红', '你的头像很漂亮', 0, to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1005', 'BLOG_REPLY', '1001', '1107134791473442818', '张三', '傻逼!!!', 0, to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:21:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1006', 'BLOG_REPLY', '1003', '1107134791473442818', '小兰', '哈哈，花了一年多，你信么？', 0, to_timestamp('11-03-2019 22:28:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('11-03-2019 22:28:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1106838349278134274', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '8999888', 0, to_timestamp('16-03-2019 16:43:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 16:43:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107270423566893057', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '人人为我', 0, to_timestamp('17-03-2019 21:20:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:20:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107293771277651970', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲的', 0, to_timestamp('17-03-2019 22:53:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:53:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 1);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107293934922616833', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲的', 0, to_timestamp('17-03-2019 22:53:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:53:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107669591737360386', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '114131', 0, to_timestamp('18-03-2019 23:46:24.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('18-03-2019 23:46:24.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107669990892494850', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '1321321', 0, to_timestamp('18-03-2019 23:47:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('18-03-2019 23:47:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109096255902031874', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '曾经沧海难为水', 0, to_timestamp('22-03-2019 22:15:28.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 22:15:28.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1106969873415692290', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '你好世界', 0, to_timestamp('17-03-2019 01:25:58.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 01:25:58.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1106970226211184641', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '世界很美丽', 0, to_timestamp('17-03-2019 01:27:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 01:27:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1106971427849920513', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '2431324', 0, to_timestamp('17-03-2019 01:32:09.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 01:32:09.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107267959513956354', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '大幅度', 0, to_timestamp('17-03-2019 21:10:28.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:10:28.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107269333383397378', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '你总是那么滴帅！', 0, to_timestamp('17-03-2019 21:15:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:15:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107272701917020161', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '入伍任务热无', 0, to_timestamp('17-03-2019 21:29:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:29:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107272837481119745', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 21:29:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:29:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107272856250630146', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 21:29:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:29:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107273000597602306', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '法发大水', 0, to_timestamp('17-03-2019 21:30:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:30:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107273184337477633', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲的', 0, to_timestamp('17-03-2019 21:31:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:31:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107273463774593026', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', 'safdafsafdsaf', 0, to_timestamp('17-03-2019 21:32:20.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:32:20.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107273885675438081', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '范德萨范德萨', 0, to_timestamp('17-03-2019 21:34:01.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:34:01.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107274061194477570', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '热污染', 0, to_timestamp('17-03-2019 21:34:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:34:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107274185073246210', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '333333333333333333', 0, to_timestamp('17-03-2019 21:35:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:35:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107274210771746817', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '132132', 0, to_timestamp('17-03-2019 21:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107274795008933889', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '77777777777777777777777', 0, to_timestamp('17-03-2019 21:37:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:37:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107274963628343297', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '地方萨芬', 0, to_timestamp('17-03-2019 21:38:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:38:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107275314507038721', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '地方萨菲罗斯', 0, to_timestamp('17-03-2019 21:39:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:39:41.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107275446401122305', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '地方萨菲罗斯0000', 0, to_timestamp('17-03-2019 21:40:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:40:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107275656036630529', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '24324', 0, to_timestamp('17-03-2019 21:41:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:41:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107275686566969345', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '===', 0, to_timestamp('17-03-2019 21:41:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:41:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107276038540378114', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '亿一i', 0, to_timestamp('17-03-2019 21:42:34.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 21:42:34.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108735187946090498', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '大家新年快乐', 0, to_timestamp('21-03-2019 22:20:42.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:20:42.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108737024879276034', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '666', 0, to_timestamp('21-03-2019 22:28:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:28:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741253186498561', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '真男人', 0, to_timestamp('21-03-2019 22:44:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:44:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741453401600001', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '444', 0, to_timestamp('21-03-2019 22:45:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:45:36.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741641008623618', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '11111111111111111', 0, to_timestamp('21-03-2019 22:46:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:46:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741709778432002', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '窗前明月光', 0, to_timestamp('21-03-2019 22:46:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:46:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741897137991681', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '疑是地上霜', 0, to_timestamp('21-03-2019 22:47:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:47:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108741995657998337', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '举头望明月', 0, to_timestamp('21-03-2019 22:47:45.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:47:45.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108742324785033218', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '低头思故乡', 0, to_timestamp('21-03-2019 22:49:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:49:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108742773005135873', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '黄河之水天上来', 0, to_timestamp('21-03-2019 22:50:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 22:50:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1110175016386908161', 'B_COMMENT_REPLY', '1109305938642288642', '1107167437933723649', '1107167437933723649', '好人啊-_-||', 0, to_timestamp('25-03-2019 21:42:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('25-03-2019 21:42:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111264941960916994', 'B_COMMENT_REPLY', '109950', '1107134791473442818', '1107167437933723649', '很不错呀', 0, to_timestamp('28-03-2019 21:53:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 21:53:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111266522613739522', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发顺丰', 0, to_timestamp('28-03-2019 21:59:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 21:59:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 1);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111266672585273346', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '邮寄包裹', 0, to_timestamp('28-03-2019 21:59:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 21:59:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 4, 2);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111266710795382785', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '很明显啊', 0, to_timestamp('28-03-2019 22:00:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 22:00:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 4);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111282774514135041', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '很好', 0, to_timestamp('28-03-2019 23:03:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 23:03:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111282863903141890', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '(⊙o⊙)哦', 0, to_timestamp('28-03-2019 23:04:16.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 23:04:16.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111284724265709570', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '甜蜜蜜', 0, to_timestamp('28-03-2019 23:11:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 23:11:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111287509346480130', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '报仇雪恨', 0, to_timestamp('28-03-2019 23:22:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 23:22:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111288006044348417', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '真棒', 0, to_timestamp('28-03-2019 23:24:42.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('28-03-2019 23:24:42.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111302694421458946', 'B_COMMENT_REPLY', '1111266672585273346', '1107134791473442818', '1107134791473442818', '666', 0, to_timestamp('29-03-2019 00:23:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:23:04.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111303310694739969', 'B_COMMENT_REPLY', '1107292960350916609', '1107134791473442818', '1107134791473442818', '444', 0, to_timestamp('29-03-2019 00:25:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:25:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111306999568330753', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', 'nice', 0, to_timestamp('29-03-2019 00:40:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:40:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 9, 5);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111308678837628930', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '很棒喔', 0, to_timestamp('29-03-2019 00:46:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:46:50.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111308917262839810', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '很棒喔', 0, to_timestamp('29-03-2019 00:47:47.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:47:47.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111309306431336450', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '12313', 0, to_timestamp('29-03-2019 00:49:20.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:49:20.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111309580109672450', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '55555', 0, to_timestamp('29-03-2019 00:50:25.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:50:25.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111311984997756929', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', 'henhao', 0, to_timestamp('29-03-2019 00:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 00:59:59.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111312378196979714', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '真心世界', 0, to_timestamp('29-03-2019 01:01:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 01:01:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111312427274530818', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', '发的萨菲', 0, to_timestamp('29-03-2019 01:01:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 01:01:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111312586838437890', 'B_COMMENT_REPLY', '1107293771277651970', '1107134791473442818', '1107134791473442818', '哈哈', 0, to_timestamp('29-03-2019 01:02:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 01:02:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111312626977927169', 'B_COMMENT_REPLY', '1107292960350916609', '1107134791473442818', '1107134791473442818', '你也很棒', 0, to_timestamp('29-03-2019 01:02:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 01:02:32.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292828481998849', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发放大法', 0, to_timestamp('17-03-2019 22:49:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:49:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292885927186434', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 22:49:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:49:31.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292960350916609', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 22:49:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:49:48.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 2);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107293237208535041', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 22:50:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:50:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107293583901315073', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发的萨菲', 0, to_timestamp('17-03-2019 22:52:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:52:17.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108768571753803777', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '天下武功出少林', 0, to_timestamp('22-03-2019 00:33:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:33:22.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108758072865632257', 'B_COMMENT_REPLY', '1108749266400096258', '1107134791473442818', '1108749266400096258', '1321321', 0, to_timestamp('21-03-2019 23:51:38.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 23:51:38.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108768696400130049', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '哈哈，煞笔', 0, to_timestamp('22-03-2019 00:33:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:33:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108768774644871169', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '13213', 0, to_timestamp('22-03-2019 00:34:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:34:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108770648110444546', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '呃1呃1呃', 0, to_timestamp('22-03-2019 00:41:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:41:37.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108770931737669634', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '142432', 0, to_timestamp('22-03-2019 00:42:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:42:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108770978931978242', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '66666666', 0, to_timestamp('22-03-2019 00:42:56.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:42:56.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
commit;
prompt 100 records committed...
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108771328573353985', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '777777', 0, to_timestamp('22-03-2019 00:44:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:44:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108771541010657282', 'B_BLOG_COMMENT', '1102746452463685634', '1107134791473442818', '1107167437933723649', '8888888', 0, to_timestamp('22-03-2019 00:45:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:45:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108772345926955009', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '222', 0, to_timestamp('22-03-2019 00:48:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:48:21.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108773538510188545', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '64565', 0, to_timestamp('22-03-2019 00:53:06.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:53:06.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108775713835626497', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '脂肪怪人', 0, to_timestamp('22-03-2019 01:01:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 01:01:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109095563728625665', '0', '0', '1107134791473442818', '1107167437933723649', '曾经沧海难为水', 0, to_timestamp('22-03-2019 22:12:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 22:12:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109095736924020737', '0', '0', '1107134791473442818', '1107167437933723649', '曾经沧海难为水', 0, to_timestamp('22-03-2019 22:13:24.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 22:13:24.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109096154118856706', '0', '0', '1107134791473442818', '1107167437933723649', '曾经沧海难为水', 0, to_timestamp('22-03-2019 22:15:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 22:15:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109311995133378562', 'B_COMMENT_REPLY', '1109305938642288642', '1107167437933723649', '1107167437933723649', '回复', 0, to_timestamp('23-03-2019 12:32:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 12:32:44.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109312174666366977', 'B_COMMENT_REPLY', '1109305938642288642', '1107167437933723649', '1107167437933723649', '很好支持', 0, to_timestamp('23-03-2019 12:33:27.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 12:33:27.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111614994392391681', 'B_COMMENT_REPLY', '1111306999568330753', '1107134791473442818', '1107134791473442818', 'hello', 0, to_timestamp('29-03-2019 21:04:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 21:04:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1111615036436094978', 'B_COMMENT_REPLY', '1111266522613739522', '1107134791473442818', '1107134791473442818', 'nice', 0, to_timestamp('29-03-2019 21:04:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('29-03-2019 21:04:12.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1106849127750176769', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '5435435435432453', 0, to_timestamp('16-03-2019 17:26:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('16-03-2019 17:26:10.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292358791254018', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发放大法', 0, to_timestamp('17-03-2019 22:47:25.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:47:25.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292668834205697', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发放大法', 0, to_timestamp('17-03-2019 22:48:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:48:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107292771682734081', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '发放大法', 0, to_timestamp('17-03-2019 22:49:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('17-03-2019 22:49:03.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1107672482124521473', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', '2313213', 0, to_timestamp('18-03-2019 23:57:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('18-03-2019 23:57:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109296017888591875', 'B_BLOG_COMMENT', '0', '1107167437933723649', '1107167437933723649', '432432', 0, to_timestamp('23-03-2019 11:29:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:29:15.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108760314981167106', 'B_COMMENT_REPLY', '1108749266400096258', '1107134791473442818', '1108749266400096258', '243243243243', 0, to_timestamp('22-03-2019 00:00:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:00:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108641870481424385', 'B_BLOG_COMMENT', '109950', '1107134791473442818', '1107167437933723649', 'thanks', 0, to_timestamp('21-03-2019 16:09:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('21-03-2019 16:09:54.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109296557045399554', 'B_BLOG_COMMENT', '109950', '1107167437933723649', '1107167437933723649', '242432', 0, to_timestamp('23-03-2019 11:31:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 11:31:23.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1108760365421867010', 'B_COMMENT_REPLY', '1108749266400096258', '1107134791473442818', '1108749266400096258', '243243243243', 0, to_timestamp('22-03-2019 00:00:45.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('22-03-2019 00:00:45.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109305938642288642', 'B_BLOG_COMMENT', '109950', '1107167437933723649', '1107167437933723649', '构建文明和谐城市', 0, to_timestamp('23-03-2019 12:08:40.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 12:08:40.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 6);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109309003260837889', 'B_COMMENT_REPLY', '1109305938642288642', '1107167437933723649', '1107167437933723649', '回复大哥', 0, to_timestamp('23-03-2019 12:20:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 12:20:51.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
insert into T_COMMENT_REPLY (ID, TYPE, PARENT_ID, ACTIVE_USER_ID, PASSIVE_USER_ID, CONTENT, DELETED, CREATE_TIME, UPDATE_TIME, STAR_COUNT, REPLY_COUNT)
values ('1109309582041235457', 'B_COMMENT_REPLY', '1109305938642288642', '1107167437933723649', '1107167437933723649', '回复大哥', 0, to_timestamp('23-03-2019 12:23:08.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('23-03-2019 12:23:08.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, 0);
commit;
prompt 125 records loaded
prompt Enabling triggers for R_ROLE_PERMISSION...
alter table R_ROLE_PERMISSION enable all triggers;
prompt Enabling triggers for R_TAG_BLOG...
alter table R_TAG_BLOG enable all triggers;
prompt Enabling triggers for R_USER_ROLE...
alter table R_USER_ROLE enable all triggers;
prompt Enabling triggers for SYS_FUNCTION...
alter table SYS_FUNCTION enable all triggers;
prompt Enabling triggers for SYS_PARAM...
alter table SYS_PARAM enable all triggers;
prompt Enabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for T_BLOG_CATEGORY...
alter table T_BLOG_CATEGORY enable all triggers;
prompt Enabling triggers for T_BLOG_TAG...
alter table T_BLOG_TAG enable all triggers;
prompt Enabling triggers for T_COMMENT_REPLY...
alter table T_COMMENT_REPLY enable all triggers;
set feedback on
set define on
prompt Done.
