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
   TAG_ID               varchar(32) not null comment '��ǩ���',
   BLOG_ID              varchar(32) not null comment '���ͱ��',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (TAG_ID, BLOG_ID)
);

alter table KIWIPEACH.R_TAG_BLOG comment '����VS��ǩ';

/*==============================================================*/
/* Table: R_USER_PERMISSION                                     */
/*==============================================================*/
create table KIWIPEACH.R_USER_PERMISSION
(
   USER_ID              varchar(32) not null comment '�û����',
   PERMISSION_ID        varchar(32) not null comment 'Ȩ�ޱ�־���',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (USER_ID, PERMISSION_ID)
);

alter table KIWIPEACH.R_USER_PERMISSION comment '�û�VSȨ��';

/*==============================================================*/
/* Table: R_USER_ROLE                                           */
/*==============================================================*/
create table KIWIPEACH.R_USER_ROLE
(
   USER_ID              varchar(32) not null comment '�û����',
   ROLE_ID              varchar(32) not null comment '��ɫ���',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ROLE_ID, USER_ID)
);

alter table KIWIPEACH.R_USER_ROLE comment '�û�VS��ɫ';

/*==============================================================*/
/* Table: SYS_ACCESSLOG                                         */
/*==============================================================*/
create table KIWIPEACH.SYS_ACCESSLOG
(
   ID                   varchar(32) not null comment '���',
   TIME                 datetime comment '����ʱ��',
   SESSIONID            varchar(255) comment 'sessionid',
   REFERER              varchar(255) comment 'Դ��ַ',
   URL                  varchar(300) comment '���ʵ�ַ',
   IP                   varchar(255) comment 'ip',
   USERAGENT            varchar(255) comment '����ͻ���',
   METHOD               varchar(255) comment '���󷽷�',
   PARAMS               varchar(1000) comment '���',
   RESPONSE             text comment '������Ϣ',
   GENERAL              varchar(1000) comment 'ͨ����Ϣ',
   REQUEST_HEADER       varchar(1000) comment '����ͷ��Ϣ',
   RESPONSE_HEADER      varchar(1000) comment '��Ӧ����Ϣ',
   USERID               varchar(32) comment '�û����',
   USERNAME             varchar(40) comment '�û���',
   STATUS               numeric(1,0) comment '״̬',
   primary key (ID)
);

alter table KIWIPEACH.SYS_ACCESSLOG comment 'ϵͳ������־';

/*==============================================================*/
/* Table: SYS_FUNCTION                                          */
/*==============================================================*/
create table KIWIPEACH.SYS_FUNCTION
(
   ID                   varchar(32) not null comment '����ID',
   LOCATION             varchar(256) comment '����·��(ҳ������)',
   TEXT                 varchar(100) not null comment '��������',
   PARENTID             varchar(32) comment '���ڵ���',
   WEIGHT               numeric(4,0) comment 'ͬ��Ȩ��',
   NODE_TYPE            varchar(8) not null comment '�ڵ�����(ҳ������)',
   ICON                 varchar(200) comment 'ͼ��',
   DESCRIPTION          varchar(1024) comment '������Ϣ',
   PERMISSION_ID        varchar(100) comment '�˵�Ȩ�ޱ�־���',
   primary key (ID)
);

alter table KIWIPEACH.SYS_FUNCTION comment 'ϵͳ�˵���';

/*==============================================================*/
/* Table: SYS_PARAM                                             */
/*==============================================================*/
create table KIWIPEACH.SYS_PARAM
(
   ID                   varchar(32) not null comment '�������',
   NAME                 varchar(16) not null comment '��������',
   VALUE                numeric(4,0) not null comment '����ֵ',
   ENABLE               numeric(1,0) not null default 1 comment '�Ƿ����[0:������ 1����]',
   DESCRIPTION          varchar(200) comment '����',
   PARENT_ID            varchar(32) comment '���ڵ���(���м������Եı������)',
   TYPE                 varchar(16) default 'sys' comment 'sys:ϵͳ�������� code:�������Ͷ��� cascade:�������Ͷ���',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '����ʱ��'
);

alter table KIWIPEACH.SYS_PARAM comment 'ϵͳ����';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table KIWIPEACH.SYS_PERMISSION
(
   ID                   varchar(32) not null comment 'Ȩ�ޱ��',
   NAME                 varchar(16) not null comment 'Ȩ������',
   WEIGHT               numeric(1,0) default 1 comment 'Ȩ��Ȩ��',
   ICON                 varchar(32) default 'defalut_permission_icon' comment 'Ȩ��ͼ��',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   CODE                 varchar(32) comment 'Ȩ��Ӣ�ı���(��ʽ:��Դ+ģ��+����,blog:page:access)',
   primary key (ID)
);

alter table KIWIPEACH.SYS_PERMISSION comment 'ϵͳȨ��';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_ROLE
(
   ID                   varchar(32) not null comment '��ɫ���',
   NAME                 varchar(16) not null comment '��ɫ����',
   WEIGHT               numeric(1,0) default 1 comment 'Ȩ��',
   ICON                 varchar(32) default 'defalut_role_icon' comment 'ͼ��',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '����ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.SYS_ROLE comment 'ϵͳ��ɫ';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table KIWIPEACH.SYS_USER
(
   ID                   varchar(32) not null comment '�û���ţ��������ɣ����Թۿ���Աע���Ⱥ�',
   THIRD_USER_ID        varchar(32) comment '�����˺�ID�������û���ţ�',
   USER_NAME            varchar(32) comment '��¼�˺�(������½�˺�)',
   PASSWORD             varchar(64) comment '����(��վע�����룬ֻ��ע�����������½���ƹ���Ϣ���û�����)',
   NICK_NAME            varchar(16) not null comment '�û��ǳƣ�Ĭ��ȡ����ƽ̨�ǳƣ����ߵ�½����,����ϵͳ��ʹ�ò����û��룩',
   HEAD_URL             varchar(100) not null default 'default_head_url' comment '�û�ͷ�� (Ĭ������ƽ̨ͷ�񣬿ɺ������и���ͷ��,����ϵͳʹ��Ĭ��ͷ��)',
   EMAIL                varchar(32) comment '�û����� (�û���Ϣ����ʱ���ṩ)',
   ACCOUNT_LOCK         numeric(1,0) not null default 0 comment '�˺��Ƿ�����[0:���� 1:����] ������һЩ�Ƿ��˺ŵĿ��ƣ�',
   LOCK_REASON          numeric(2,0) comment '�˺�����ԭ��[1:��������](�ı��˺�״̬������)',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.SYS_USER comment 'ϵͳ�û�';

/*==============================================================*/
/* Table: T_BLOG                                                */
/*==============================================================*/
create table KIWIPEACH.T_BLOG
(
   ID                   varchar(32) not null comment '���ͱ��',
   USER_ID              varchar(32) not null comment '�û����',
   CATE_ID              varchar(32) comment '������',
   TITLE                varchar(50) not null comment '���ͱ���',
   CONTENT              varchar(4000) not null comment '��������',
   STAR_COUNT           numeric(8,0) comment '���͵���',
   VIEWS                numeric(8,0) comment '�������',
   TOP                  numeric(1,0) comment '�Ƿ��ö�',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG comment '����';

/*==============================================================*/
/* Table: T_BLOG_CATEGORY                                       */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_CATEGORY
(
   ID                   varchar(32) not null comment '������',
   NAME                 varchar(16) not null comment '��������',
   WEIGHT               numeric(3,0) default 1 comment '����ͬ��Ȩ��',
   ICON                 varchar(32) default 'default_category_icon' comment '����ͼ��',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_CATEGORY comment '����';

/*==============================================================*/
/* Table: T_BLOG_TAG                                            */
/*==============================================================*/
create table KIWIPEACH.T_BLOG_TAG
(
   ID                   varchar(32) not null comment '��ǩ���',
   NAME                 varchar(32) not null comment '��ǩ����',
   WEIGHT               numeric(3,0) default 1 comment '��ǩȨ��',
   ICON                 varchar(256) default 'default_tag_icon' comment '��ǩͼ��',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_TAG comment '��ǩ';

/*==============================================================*/
/* Table: T_COMMENT_MSG                                         */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_MSG
(
   ID                   varchar(32) not null comment '���۱��',
   PARENT_COMMENT_TYPE  varchar(32) not null default '0' comment '���ۻظ�����(0������һ������,1�����Ͷ������� 2������һ������ 3�����Զ�������)����',
   TARGET_ID            varchar(32) not null default '0' comment '���ۻظ�����(0������һ������,1�����Ͷ������� 2������һ������ 3�����Զ�������)���',
   USER_AID             varchar(32) not null comment '��ɫA',
   USER_BID             varchar(32) not null comment '��ɫB',
   CONTENT              varchar(300) not null comment '��������',
   DELETED              numeric(1,0) default 0 comment '�����������Ƿ�ɾ��[0:���� 1:ɾ��]',
   CREATE_TIME          timestamp default NOW() comment '����ʱ��',
   UPDATE_TIME          timestamp default NOW() comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.T_COMMENT_MSG comment '����';

