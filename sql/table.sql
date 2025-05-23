
DROP DATABASE IF EXISTS dodooj;
CREATE DATABASE dodooj;

use dodooj;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    id           bigint auto_increment comment 'id' primary key,
    account  varchar(64)                           not null unique comment '账号',
    password varchar(64)                           not null comment '密码',
    email    varchar(64)                           null comment '邮箱',
    user_name     varchar(256)                           null comment '用户昵称',
    avatar   varchar(1024)                          null comment '用户头像',
    profile  varchar(512)                           null comment '用户简介',
    role     varchar(256) default 'USER'            not null comment '用户角色：USER/ADMIN/BAN',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint       default 0                 not null comment '是否删除',
    index idx_uid (id)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 题目表
DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(128)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(512)                      null comment '标签列表（json 数组）',
    answer      text                               null comment '题目答案',
    submit_count   int      default 0                 not null comment '题目提交数',
    accepted_count int      default 0                 not null comment '题目通过数',
    judge_case   text                               null comment '判题用例（json 数组）',
    judge_config text                               null comment '判题配置（json 对象）',
    thumb_num    int      default 0                 not null comment '点赞数',
    favour_num   int      default 0                 not null comment '收藏数',
    uid      bigint                             not null comment '创建用户 id',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete    tinyint  default 0                 not null comment '是否删除',
    index idx_uid (id)
) comment '题目' collate = utf8mb4_unicode_ci;

-- 题目提交表
DROP TABLE IF EXISTS submit;
CREATE TABLE IF NOT EXISTS submit
(
    id         bigint auto_increment comment 'id' primary key,
    language   varchar(128)                       not null comment '编程语言',
    code       text                               not null comment '用户代码',
    judge_info  text                               null comment '判题信息（json 对象）',
    status     int      default 0                 not null comment '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
    question_id bigint                             not null comment '题目 id',
    question_title       varchar(128)                       null comment '题目标题',
    uid     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_question_id (id),
    index idx_uid (uid)
) comment '题目提交' collate = utf8mb4_unicode_ci;

-- 标签表
DROP TABLE IF EXISTS tag;
CREATE TABLE IF NOT EXISTS tag(
    id         bigint auto_increment comment 'id' primary key,
    content    varchar(32)                       not null comment '标签内容',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_tag_id (id)
) comment '标签' collate = utf8mb4_unicode_ci;
