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
'��ɫVSȨ��';

comment on column KIWIPEACH.R_ROLE_PERMISSION.ROLE_ID is
'��ɫ���';

comment on column KIWIPEACH.R_ROLE_PERMISSION.PERMISSION_ID is
'Ȩ�ޱ�־���';

comment on column KIWIPEACH.R_ROLE_PERMISSION.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.R_ROLE_PERMISSION.UPDATE_TIME is
'�޸�ʱ��';

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
'ϵͳ������־';

comment on column KIWIPEACH.SYS_ACCESSLOG.ID is
'���';

comment on column KIWIPEACH.SYS_ACCESSLOG.USER_NAME is
'�û����';

comment on column KIWIPEACH.SYS_ACCESSLOG.IP is
'ip';

comment on column KIWIPEACH.SYS_ACCESSLOG.START_TIME is
'���ʿ�ʼʱ��';

comment on column KIWIPEACH.SYS_ACCESSLOG.URL is
'���ʵ�ַ';

comment on column KIWIPEACH.SYS_ACCESSLOG.METHOD is
'���󷽷�';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_BODY is
'���';

comment on column KIWIPEACH.SYS_ACCESSLOG.RESPONSE_BODY is
'������Ϣ';

comment on column KIWIPEACH.SYS_ACCESSLOG.STATUS is
'http��Ӧ״̬��';

comment on column KIWIPEACH.SYS_ACCESSLOG.END_TIME is
'���ʽ���ʱ��';

comment on column KIWIPEACH.SYS_ACCESSLOG.NAME is
'ͳ��Ŀ�깦������';

comment on column KIWIPEACH.SYS_ACCESSLOG.REQUEST_HEADER is
'����ͷ��Ϣ';

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
   VALUE                NUMBER(4,0)          not null,
   ENABLE               NUMBER(1,0)          default 1 not null,
   DESCRIPTION          VARCHAR2(200),
   PARENT_ID            VARCHAR2(32),
   TYPE                 VARCHAR2(16)         default 'sys',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE
);

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
   NAME                 VARCHAR2(32)         not null,
   WEIGHT               NUMBER(1,0)          default 1,
   ICON                 VARCHAR2(32)         default 'defalut_permission_icon',
   CREATE_TIME          TIMESTAMP            default SYSDATE,
   UPDATE_TIME          TIMESTAMP            default SYSDATE,
   DESCRIPTION          VARCHAR2(32),
   constraint PK_SYS_PERMISSION primary key (ID)
);

comment on table KIWIPEACH.SYS_PERMISSION is
'ϵͳȨ��';

comment on column KIWIPEACH.SYS_PERMISSION.ID is
'Ȩ�ޱ��';

comment on column KIWIPEACH.SYS_PERMISSION.NAME is
'Ȩ��Ӣ�ı���(��ʽ:��Դ+ģ��+����,blog:page:access)';

comment on column KIWIPEACH.SYS_PERMISSION.WEIGHT is
'Ȩ��Ȩ��';

comment on column KIWIPEACH.SYS_PERMISSION.ICON is
'Ȩ��ͼ��';

comment on column KIWIPEACH.SYS_PERMISSION.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.SYS_PERMISSION.UPDATE_TIME is
'�޸�ʱ��';

comment on column KIWIPEACH.SYS_PERMISSION.DESCRIPTION is
'Ȩ������';

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

comment on column KIWIPEACH.SYS_ROLE.DESCRIPTION is
'��ɫ������Ϣ';

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

comment on column KIWIPEACH.SYS_USER.OPEN_ID is
'��վ��������վ�������û�Ψһ��־';

comment on column KIWIPEACH.SYS_USER.PLATFORM is
'ƽ̨[qq,gitee,github,system]';

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
'����';

comment on column KIWIPEACH.T_BLOG.ID is
'���ͱ��';

comment on column KIWIPEACH.T_BLOG.USER_ID is
'�û����';

comment on column KIWIPEACH.T_BLOG.CATE_ID is
'������(0:δ����)';

comment on column KIWIPEACH.T_BLOG.TITLE is
'���ͱ���';

comment on column KIWIPEACH.T_BLOG.STAR_COUNT is
'���͵���';

comment on column KIWIPEACH.T_BLOG.VIEW_COUNT is
'�������';

comment on column KIWIPEACH.T_BLOG.TOP is
'�Ƿ��ö�';

comment on column KIWIPEACH.T_BLOG.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_BLOG.UPDATE_TIME is
'�޸�ʱ��';

comment on column KIWIPEACH.T_BLOG.CONTENT_TYPE is
'������������[0:markdown 1:html��]';

comment on column KIWIPEACH.T_BLOG.INTRODUCTION is
'���ͼ��';

comment on column KIWIPEACH.T_BLOG.ICON_URL is
'����ͼ��';

comment on column KIWIPEACH.T_BLOG.CONTENT is
'�������ݣ������������ʹ�Ų�ͬ���������ݣ�';

comment on column KIWIPEACH.T_BLOG.COMMENT_COUNT is
'�������������������ۻ��ǻظ�����Ҫ�Դ��ֶ�+1��';

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

comment on column KIWIPEACH.T_BLOG_CATEGORY.PARENT_ID is
'���������';

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
'���ۻظ���';

comment on column KIWIPEACH.T_COMMENT_REPLY.ID is
'���۱��';

comment on column KIWIPEACH.T_COMMENT_REPLY.TYPE is
'���ۻظ�����[B_COMMENT,B_COMMENT_REPLY,B_REPLY_REPLY,L_COMMENT,L_COMMENT_REPLY,L_REPLY_REPLY]';

comment on column KIWIPEACH.T_COMMENT_REPLY.PARENT_ID is
'���׽ڵ�(����ʱ����Ϊ���ڵ㣻�ظ�ʱ����Ϊ���ڵ�)';

comment on column KIWIPEACH.T_COMMENT_REPLY.ACTIVE_USER_ID is
'�������ۻ��߻ظ�����';

comment on column KIWIPEACH.T_COMMENT_REPLY.PASSIVE_USER_ID is
'�����ۻ��߻ظ�����';

comment on column KIWIPEACH.T_COMMENT_REPLY.CONTENT is
'��������';

comment on column KIWIPEACH.T_COMMENT_REPLY.DELETED is
'�����������Ƿ�ɾ��[0:���� 1:ɾ��]';

comment on column KIWIPEACH.T_COMMENT_REPLY.CREATE_TIME is
'����ʱ��';

comment on column KIWIPEACH.T_COMMENT_REPLY.UPDATE_TIME is
'�޸�ʱ��';

comment on column KIWIPEACH.T_COMMENT_REPLY.STAR_COUNT is
'���ۻظ���������';

comment on column KIWIPEACH.T_COMMENT_REPLY.REPLY_COUNT is
'���ۻظ�ͳ�ƣ�ע�⣺ֻ�����۲��д˸���ظ��޴˸��';

