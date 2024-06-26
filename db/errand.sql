create database errand;

use errand;

-- admin

create table `admin`
(
    `id`       int(10)                                                       not null auto_increment comment 'id',
    `username` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '用户名',
    `password` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '密码',
    `name`     varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '姓名',
    `avatar`   varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '头像',
    `role`     varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '角色',
    `phone`    varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '电话',
    `email`    varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '邮箱',
    primary key (`id`) using btree
) engine = InnoDB
  AUTO_INCREMENT = 2
  character set utf8mb4
  collate utf8mb4_unicode_ci comment ='管理员'
  ROW_FORMAT = dynamic;

insert into `admin`
values (1, 'admin', 'admin', '管理员',
        'https://cdn.nlark.com/yuque/0/2023/jpeg/anonymous/1688442318375-9a308fd1-6ddb-464f-b104-4fe52dab3f8f.jpeg?x-oss-process=image%2Fresize%2Cm_fill%2Cw_48%2Ch_48%2Fformat%2Cpng',
        'ADMIN', '16638748874', 'nanchengyu02@qq.com');


-- table notice
create table `notice`
(
    `id`      int(10)                                                       not null auto_increment comment 'id',
    `title`   varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '标题',
    `content` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '内容',
    `time`    varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '创建时间',
    `user`    varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '创建人',

    primary key (`id`) using btree
) engine = InnoDB
  AUTO_INCREMENT = 2
  character set utf8mb4
  collate utf8mb4_unicode_ci comment ='公告信息表'
  ROW_FORMAT = dynamic;

-- table user
create table `user`
(
    `id`       int(10)                                                       not null auto_increment comment 'id',
    `username` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '用户名',
    `password` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '密码',
    `name`     varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '姓名',
    `avatar`   varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT 'https://cdn.nlark.com/yuque/0/2023/jpeg/anonymous/1688442318375-9a308fd1-6ddb-464f-b104-4fe52dab3f8f.jpeg?x-oss-process=image%2Fresize%2Cm_fill%2Cw_48%2Ch_48%2Fformat%2Cpng' comment '头像',
    `role`     varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '角色',
    `sex`      varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '性别',
    `phone`    varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '电话',
    `account`  decimal(10, 2)                                                     DEFAULT '0.00' null comment '余额',
    primary key (`id`) using btree
) engine = InnoDB
  AUTO_INCREMENT = 2
  character set utf8mb4
  collate utf8mb4_unicode_ci comment ='用户表'
  ROW_FORMAT = dynamic;

-- table logs
create table `logs`
(
    -- id int(11)
    `id`        int(10)                                                       not null auto_increment comment 'id',
    `operation` varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '操作名称',
    `type`      varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '操作类型',
    `ip`        varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment 'ip地址',
    `user`      varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '操作人',
    `time`      varchar(255) character set utf8mb4 collate utf8mb4_unicode_ci null DEFAULT null comment '系统日志',

    primary key (`id`) using btree
) engine = InnoDB
  AUTO_INCREMENT = 2
  character set utf8mb4
  collate utf8mb4_unicode_ci comment ='系统日志'
  ROW_FORMAT = dynamic;

-- table orders

CREATE TABLE `orders` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                          `order_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单编号',
                          `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物品名称',
                          `descr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                          `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物品图片',
                          `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物品类型',
                          `weight` double(10,2) DEFAULT NULL COMMENT '物品重量',
                          `price` double(10,2) DEFAULT NULL COMMENT '小费',
                          `user_id` int(11) DEFAULT NULL COMMENT '发起人ID',
                          `accept_id` int(11) DEFAULT NULL COMMENT '接单人ID',
                          `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
                          `accept_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接单时间',
                          `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
                          `address_id` int(11) DEFAULT NULL COMMENT '取货地址ID',
                          `target_id` int(11) DEFAULT NULL COMMENT '送货地址ID',
                          `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单备注',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';


-- table address

CREATE TABLE `address` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
                           `door_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '门牌号',
                           `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
                           `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
                           `user_id` int(11) DEFAULT NULL COMMENT '关联用户ID',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='联系人信息';


-- table certification
CREATE TABLE `certification` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `user id` int(11) DEFAULT NULL COMMENT '账号',
                                 `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
                                 `avatar` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '本人照片',
                                 `phone` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系方式',
                                 `card_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '身份证号码',
                                 `card1` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '身份证正面',
                                 `card2` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '身份证反面',
                                 `address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '常住地址',
                                 `status` varchar(255) CHARACTER SET utf8mb4 DEFAULT '待审核' COMMENT '审核状态',
                                 `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核理由',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='骑手认证';