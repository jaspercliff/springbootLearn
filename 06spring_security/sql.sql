-- auto-generated definition
create table sys_role
(
    ID         int unsigned auto_increment comment 'ROLE_ID'
        primary key,
    ROLE_CODE  varchar(30) null comment '角色代码',
    ROLE_NAME  varchar(60) null comment '角色名称',
    ROLE_DESC  varchar(60) null comment '角色说明',
    STATUS     int         null comment '角色状态(1000)',
    CREATED_BY varchar(30) null comment 'CREATED_BY',
    CREATED_AT datetime    null comment 'CREATED_AT',
    UPDATED_BY varchar(30) null comment 'UPDATED_BY',
    UPDATED_AT datetime    null on update CURRENT_TIMESTAMP comment 'UPDATED_AT'
)
    comment '角色表' row_format = DYNAMIC;

-- auto-generated definition
create table sys_user
(
    ID              varchar(30)                         not null comment '账号ID'
        primary key,
    ACCOUNT         varchar(200)                        null comment '账号',
    PASSWORD        varchar(100)                        null comment '密码',
    USERNAME        varchar(200)                        null comment '用户名称',
    AVATAR          varchar(255)                        null comment '头像',
    JOB_NO          int unsigned                        null comment '工号',
    PHONE           varchar(50)                         null comment '手机账号',
    WECHAT          varchar(50)                         null comment '微信账号',
    EMAIL           varchar(50)                         null comment '邮箱',
    SCHOOL          varchar(50)                         null comment '学校',
    ACADEMY         varchar(50)                         null comment '学院',
    LOGIN_LAST_TIME timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后登录时间',
    STATUS          int       default 10001001          null comment '状态(10001001有效,10001002无效)',
    CREATED_BY      varchar(30)                         null comment 'CREATED_BY',
    CREATED_AT      datetime                            null comment 'CREATED_AT',
    UPDATED_BY      varchar(30)                         null comment 'UPDATED_BY',
    UPDATED_AT      datetime  default (now())           null comment 'UPDATED_AT'
)
    comment '用户表' row_format = DYNAMIC;

create table sys_role_user_relation
(
    ID         int auto_increment comment '员工角色主键'
        primary key,
    USER_ID    varchar(50)              null comment '人员ID',
    ROLE_ID    varchar(200)             null comment '角色表CODE',
    CREATED_BY varchar(30)              null comment 'CREATED_BY',
    CREATED_AT datetime                 null comment 'CREATED_AT',
    UPDATED_BY varchar(30)              null comment 'UPDATED_BY',
    UPDATED_AT datetime default (now()) null comment 'UPDATED_AT'
)
    comment '用户角色关联表' row_format = DYNAMIC;
