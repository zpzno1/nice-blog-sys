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
   ROLE_ID              varchar(32) not null comment '��ɫ���',
   PERMISSION_ID        varchar(32) not null comment 'Ȩ�ޱ�־���',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   primary key (ROLE_ID, PERMISSION_ID)
);

alter table KIWIPEACH.R_ROLE_PERMISSION comment '��ɫVSȨ��';

/*==============================================================*/
/* Table: R_TAG_BLOG                                            */
/*==============================================================*/
create table KIWIPEACH.R_TAG_BLOG
(
   TAG_ID               varchar(32) not null comment '��ǩ���',
   BLOG_ID              varchar(32) not null comment '���ͱ��',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   primary key (TAG_ID, BLOG_ID)
);

alter table KIWIPEACH.R_TAG_BLOG comment '����VS��ǩ';

/*==============================================================*/
/* Table: R_USER_ROLE                                           */
/*==============================================================*/
create table KIWIPEACH.R_USER_ROLE
(
   USER_ID              varchar(32) not null comment '�û����',
   ROLE_ID              varchar(32) not null comment '��ɫ���',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   primary key (ROLE_ID, USER_ID)
);

alter table KIWIPEACH.R_USER_ROLE comment '�û�VS��ɫ';

/*==============================================================*/
/* Table: SYS_ACCESSLOG                                         */
/*==============================================================*/
create table KIWIPEACH.SYS_ACCESSLOG
(
   ID                   varchar(32) not null comment '���',
   USER_NAME            varchar(32) comment '�û����',
   IP                   varchar(255) comment 'ip',
   START_TIME           datetime default NULL comment '���ʿ�ʼʱ��',
   URL                  varchar(300) comment '���ʵ�ַ',
   METHOD               varchar(255) comment '���󷽷�',
   REQUEST_BODY         text comment '���',
   RESPONSE_BODY        text comment '������Ϣ',
   STATUS               varchar(8) comment 'http��Ӧ״̬��',
   END_TIME             datetime comment '���ʽ���ʱ��',
   NAME                 varchar(64) comment 'ͳ��Ŀ�깦������',
   REQUEST_HEADER       text comment '����ͷ��Ϣ',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��'
);

alter table KIWIPEACH.SYS_PARAM comment 'ϵͳ����';

/*==============================================================*/
/* Table: SYS_PERMISSION                                        */
/*==============================================================*/
create table KIWIPEACH.SYS_PERMISSION
(
   ID                   varchar(32) not null comment 'Ȩ�ޱ��',
   NAME                 varchar(32) not null comment 'Ȩ��Ӣ�ı���(��ʽ:��Դ+ģ��+����,blog:page:access)',
   WEIGHT               numeric(1,0) default 1 comment 'Ȩ��Ȩ��',
   ICON                 varchar(32) default 'defalut_permission_icon' comment 'Ȩ��ͼ��',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   DESCRIPTION          varchar(32) comment 'Ȩ������',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   DESCRIPTION          varchar(200) comment '��ɫ������Ϣ',
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
   ACCOUNT_LOCK         numeric(1,0) default 0 comment '�˺��Ƿ�����[0:���� 1:����] ������һЩ�Ƿ��˺ŵĿ��ƣ�',
   LOCK_REASON          numeric(2,0) comment '�˺�����ԭ��[1:��������](�ı��˺�״̬������)',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   OPEN_ID              varchar(32) comment '��վ��������վ�������û�Ψһ��־',
   PLATFORM             varchar(16) comment 'ƽ̨[qq,gitee,github,system]',
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
   CATE_ID              varchar(32) default '0' comment '������(0:δ����)',
   TITLE                varchar(80) not null comment '���ͱ���',
   STAR_COUNT           numeric(8,0) default 0 comment '���͵���',
   VIEW_COUNT           numeric(8,0) default 0 comment '�������',
   TOP                  numeric(1,0) default 0 comment '�Ƿ��ö�',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   CONTENT_TYPE         varchar(8) default '1' comment '������������[0:markdown 1:html��]',
   INTRODUCTION         varchar(400) comment '���ͼ��',
   ICON_URL             varchar(200) comment '����ͼ��',
   CONTENT              text comment '�������ݣ������������ʹ�Ų�ͬ���������ݣ�',
   COMMENT_COUNT        numeric(8,0) comment '�������������������ۻ��ǻظ�����Ҫ�Դ��ֶ�+1��',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   PARENT_ID            varchar(32) comment '���������',
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
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   primary key (ID)
);

alter table KIWIPEACH.T_BLOG_TAG comment '��ǩ';

/*==============================================================*/
/* Table: T_COMMENT_REPLY                                       */
/*==============================================================*/
create table KIWIPEACH.T_COMMENT_REPLY
(
   ID                   varchar(32) not null comment '���۱��',
   TYPE                 varchar(32) not null default '0' comment '���ۻظ�����[B_COMMENT,B_COMMENT_REPLY,B_REPLY_REPLY,L_COMMENT,L_COMMENT_REPLY,L_REPLY_REPLY]',
   PARENT_ID            varchar(32) not null default '0' comment '���׽ڵ�(����ʱ����Ϊ���ڵ㣻�ظ�ʱ����Ϊ���ڵ�)',
   ACTIVE_USER_ID       varchar(32) not null comment '�������ۻ��߻ظ�����',
   PASSIVE_USER_ID      varchar(32) not null comment '�����ۻ��߻ظ�����',
   CONTENT              varchar(300) not null comment '��������',
   DELETED              numeric(1,0) default 0 comment '�����������Ƿ�ɾ��[0:���� 1:ɾ��]',
   CREATE_TIME          timestamp default 'SYSDATE' comment '����ʱ��',
   UPDATE_TIME          timestamp default 'SYSDATE' comment '�޸�ʱ��',
   STAR_COUNT           numeric(8,0) default 0 comment '���ۻظ���������',
   REPLY_COUNT          numeric(8,0) default 0 comment '���ۻظ�ͳ�ƣ�ע�⣺ֻ�����۲��д˸���ظ��޴˸��',
   primary key (ID)
);

alter table KIWIPEACH.T_COMMENT_REPLY comment '���ۻظ���';

