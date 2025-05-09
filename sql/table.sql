
DROP DATABASE IF EXISTS dodooj;
CREATE DATABASE dodooj;

use dodooj;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    id           bigint auto_increment comment 'id' primary key,
    account  varchar(256)                           not null unique comment '账号',
    password varchar(512)                           not null comment '密码',
    user_name     varchar(256)                           null comment '用户昵称',
    avatar   varchar(1024)                          null comment '用户头像',
    profile  varchar(512)                           null comment '用户简介',
    role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
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
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    answer      text                               null comment '题目答案',
    template    text                               null comment '回答模板',
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
    uid     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_question_id (id),
    index idx_uid (uid)
) comment '题目提交';

-- 帖子表
DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumb_count   int      default 0                 not null comment '点赞数',
    favour_count  int      default 0                 not null comment '收藏数',
    uid     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_uid (uid)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表
DROP TABLE IF EXISTS post_thumb;
CREATE TABLE IF NOT EXISTS post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    pid     bigint                             not null comment '帖子 id',
    uid     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_pid (pid),
    index idx_uid (uid)
) comment '帖子点赞';

-- 帖子收藏表
DROP TABLE IF EXISTS post_favour;
CREATE TABLE IF NOT EXISTS post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    pid     bigint                             not null comment '帖子 id',
    uid     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_pid (pid),
    index idx_uid (uid)
) comment '帖子收藏';
