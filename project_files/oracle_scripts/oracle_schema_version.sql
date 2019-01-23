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
'����VS��ǩ';

comment on column KIWIPEACH.R_TAG_BLOG.TAG_ID is
'��ǩ���';

comment on column KIWIPEACH.R_TAG_BLOG.BLOG_ID is
'���ͱ��';

comment on column KIWIPEACH.R_TAG_BLOG.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.R_TAG_BLOG.UPDATE_TIME is
'�޸�ʱ��';

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
'�û�VSȨ��';

comment on column KIWIPEACH.R_USER_PERMISSION.USER_ID is
'�û����';

comment on column KIWIPEACH.R_USER_PERMISSION.PERMISSION_ID is
'Ȩ�ޱ�־���';

comment on column KIWIPEACH.R_USER_PERMISSION.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.R_USER_PERMISSION.UPDATE_TIME is
'�޸�ʱ��';

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
'�û�VS��ɫ';

comment on column KIWIPEACH.R_USER_ROLE.USER_ID is
'�û����';

comment on column KIWIPEACH.R_USER_ROLE.ROLE_ID is
'��ɫ���';

comment on column KIWIPEACH.R_USER_ROLE.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.R_USER_ROLE.UPDATE_TIME is
'�޸�ʱ��';

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
'ϵͳ������־';

comment on column KIWIPEACH.SYS_ACCESSLOG.ID is
'���';

comment on column KIWIPEACH.SYS_ACCESSLOG.TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_ACCESSLOG.SESSIONID is
'sessionid';

comment on column KIWIPEACH.SYS_ACCESSLOG.REFERER is
'Դ��ַ';

comment on column KIWIPEACH.SYS_ACCESSLOG.URL is
'���ʵ�ַ';

comment on column KIWIPEACH.SYS_ACCESSLOG.IP is
'ip';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERAGENT is
'����ͻ���';

comment on column KIWIPEACH.SYS_ACCESSLOG.METHOD is
'���󷽷�';

comment on column KIWIPEACH.SYS_ACCESSLOG.PARAMS is
'���';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE is
'������Ϣ';

comment on column KIWIPEACH.SYS_ACCESSLOG.GENERAL is
'ͨ����Ϣ';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_HEADER is
'����ͷ��Ϣ';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE_HEADER is
'��Ӧ����Ϣ';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERID is
'�û����';

comment on column KIWIPEACH.SYS_ACCESSLOG.USERNAME is
'�û���';

comment on column KIWIPEACH.SYS_ACCESSLOG.STATUS is
'״̬';

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
'ϵͳ�˵���';

comment on column KIWIPEACH.SYS_FUNCTION.ID is
'����ID';

comment on column KIWIPEACH.SYS_FUNCTION.LOCATION is
'����·��(ҳ������)';

comment on column KIWIPEACH.SYS_FUNCTION.TEXT is
'��������';

comment on column KIWIPEACH.SYS_FUNCTION.PARENTID is
'���ڵ���';

comment on column KIWIPEACH.SYS_FUNCTION.WEIGHT is
'ͬ��Ȩ��';

comment on column KIWIPEACH.SYS_FUNCTION.NODE_TYPE is
'�ڵ�����(ҳ������)';

comment on column KIWIPEACH.SYS_FUNCTION.ICON is
'ͼ��';

comment on column KIWIPEACH.SYS_FUNCTION.DESCRIPTION is
'������Ϣ';

comment on column KIWIPEACH.SYS_FUNCTION.PERMISSION_ID is
'�˵�Ȩ�ޱ�־���';

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
'ϵͳ����';

comment on column KIWIPEACH.SYS_PARAM.ID is
'�������';

comment on column KIWIPEACH.SYS_PARAM.NAME is
'��������';

comment on column KIWIPEACH.SYS_PARAM.VALUE is
'����ֵ';

comment on column KIWIPEACH.SYS_PARAM.ENABLE is
'�Ƿ����[0:������ 1����]';

comment on column KIWIPEACH.SYS_PARAM.DESCRIPTION is
'����';

comment on column KIWIPEACH.SYS_PARAM.PARENT_ID is
'���ڵ���(���м������Եı������)';

comment on column KIWIPEACH.SYS_PARAM.TYPE is
'sys:ϵͳ�������� code:�������Ͷ��� cascade:�������Ͷ���';

comment on column KIWIPEACH.SYS_PARAM.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_PARAM.UPDATE_TIME is
'����ʱ��';

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
'ϵͳȨ��';

comment on column KIWIPEACH.SYS_PERMISSION.ID is
'Ȩ�ޱ��';

comment on column KIWIPEACH.SYS_PERMISSION.NAME is
'Ȩ������';

comment on column KIWIPEACH.SYS_PERMISSION.WEIGHT is
'Ȩ��Ȩ��';

comment on column KIWIPEACH.SYS_PERMISSION.ICON is
'Ȩ��ͼ��';

comment on column KIWIPEACH.SYS_PERMISSION.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_PERMISSION.UPDATE_TIME is
'�޸�ʱ��';

comment on column KIWIPEACH.SYS_PERMISSION.CODE is
'Ȩ��Ӣ�ı���(��ʽ:��Դ+ģ��+����,blog:page:access)';

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
'ϵͳ��ɫ';

comment on column KIWIPEACH.SYS_ROLE.ID is
'��ɫ���';

comment on column KIWIPEACH.SYS_ROLE.NAME is
'��ɫ����';

comment on column KIWIPEACH.SYS_ROLE.WEIGHT is
'Ȩ��';

comment on column KIWIPEACH.SYS_ROLE.ICON is
'ͼ��';

comment on column KIWIPEACH.SYS_ROLE.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_ROLE.UPDATE_TIME is
'����ʱ��';

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
'ϵͳ�û�';

comment on column KIWIPEACH.SYS_USER.ID is
'�û���ţ��������ɣ����Թۿ���Աע���Ⱥ�';

comment on column KIWIPEACH.SYS_USER.THIRD_USER_ID is
'�����˺�ID�������û���ţ�';

comment on column KIWIPEACH.SYS_USER.USER_NAME is
'��¼�˺�(������½�˺�)';

comment on column KIWIPEACH.SYS_USER.PASSWORD is
'����(��վע�����룬ֻ��ע�����������½���ƹ���Ϣ���û�����)';

comment on column KIWIPEACH.SYS_USER.NICK_NAME is
'�û��ǳƣ�Ĭ��ȡ����ƽ̨�ǳƣ����ߵ�½����,����ϵͳ��ʹ�ò����û��룩';

comment on column KIWIPEACH.SYS_USER.HEAD_URL is
'�û�ͷ�� (Ĭ������ƽ̨ͷ�񣬿ɺ������и���ͷ��,����ϵͳʹ��Ĭ��ͷ��)';

comment on column KIWIPEACH.SYS_USER.EMAIL is
'�û����� (�û���Ϣ����ʱ���ṩ)';

comment on column KIWIPEACH.SYS_USER.ACCOUNT_LOCK is
'�˺��Ƿ�����[0:���� 1:����] ������һЩ�Ƿ��˺ŵĿ��ƣ�';

comment on column KIWIPEACH.SYS_USER.LOCK_REASON is
'�˺�����ԭ��[1:��������](�ı��˺�״̬������)';

comment on column KIWIPEACH.SYS_USER.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_USER.UPDATE_TIME is
'�޸�ʱ��';

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
'����';

comment on column KIWIPEACH.T_BLOG.ID is
'���ͱ��';

comment on column KIWIPEACH.T_BLOG.USER_ID is
'�û����';

comment on column KIWIPEACH.T_BLOG.CATE_ID is
'������';

comment on column KIWIPEACH.T_BLOG.TITLE is
'���ͱ���';

comment on column KIWIPEACH.T_BLOG.CONTENT is
'��������';

comment on column KIWIPEACH.T_BLOG.STAR_COUNT is
'���͵���';

comment on column KIWIPEACH.T_BLOG.VIEWS is
'�������';

comment on column KIWIPEACH.T_BLOG.TOP is
'�Ƿ��ö�';

comment on column KIWIPEACH.T_BLOG.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_BLOG.UPDATE_TIME is
'�޸�ʱ��';

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
'����';

comment on column KIWIPEACH.T_BLOG_CATEGORY.ID is
'������';

comment on column KIWIPEACH.T_BLOG_CATEGORY.NAME is
'��������';

comment on column KIWIPEACH.T_BLOG_CATEGORY.WEIGHT is
'����ͬ��Ȩ��';

comment on column KIWIPEACH.T_BLOG_CATEGORY.ICON is
'����ͼ��';

comment on column KIWIPEACH.T_BLOG_CATEGORY.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_BLOG_CATEGORY.UPDATE_TIME is
'�޸�ʱ��';

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
'��ǩ';

comment on column KIWIPEACH.T_BLOG_TAG.ID is
'��ǩ���';

comment on column KIWIPEACH.T_BLOG_TAG.NAME is
'��ǩ����';

comment on column KIWIPEACH.T_BLOG_TAG.WEIGHT is
'��ǩȨ��';

comment on column KIWIPEACH.T_BLOG_TAG.ICON is
'��ǩͼ��';

comment on column KIWIPEACH.T_BLOG_TAG.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_BLOG_TAG.UPDATE_TIME is
'�޸�ʱ��';

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
'����';

comment on column KIWIPEACH.T_COMMENT_MSG.ID is
'���۱��';

comment on column KIWIPEACH.T_COMMENT_MSG.PARENT_COMMENT_TYPE is
'���ۻظ�����(0������һ������,1�����Ͷ������� 2������һ������ 3�����Զ�������)����';

comment on column KIWIPEACH.T_COMMENT_MSG.TARGET_ID is
'���ۻظ�����(0������һ������,1�����Ͷ������� 2������һ������ 3�����Զ�������)���';

comment on column KIWIPEACH.T_COMMENT_MSG.USER_AID is
'��ɫA';

comment on column KIWIPEACH.T_COMMENT_MSG.USER_BID is
'��ɫB';

comment on column KIWIPEACH.T_COMMENT_MSG.CONTENT is
'��������';

comment on column KIWIPEACH.T_COMMENT_MSG.DELETED is
'�����������Ƿ�ɾ��[0:���� 1:ɾ��]';

comment on column KIWIPEACH.T_COMMENT_MSG.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_COMMENT_MSG.UPDATE_TIME is
'�޸�ʱ��';

