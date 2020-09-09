/*
 Navicat MySQL Data Transfer

 Source Server         : 我的阿里云数据库
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 39.105.45.77:3306
 Source Schema         : asset_manage

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 09/09/2020 17:14:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset_apply
-- ----------------------------
DROP TABLE IF EXISTS `asset_apply`;
CREATE TABLE `asset_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL COMMENT '类型1:借用、2:申领、3:采购、4:反馈',
  `number` int(11) DEFAULT NULL COMMENT '申请数量',
  `user_id` int(11) NOT NULL COMMENT '申请人员id',
  `asset_id` int(11) DEFAULT NULL COMMENT '资产id',
  `address` varchar(512) DEFAULT NULL COMMENT '使用地点',
  `purpose` varchar(512) DEFAULT NULL COMMENT '使用用途',
  `start_time` datetime DEFAULT NULL COMMENT '使用时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `urgency` int(2) DEFAULT NULL COMMENT '紧急程度 1:不急 2:一般 3:急用',
  `remarks` varchar(512) DEFAULT NULL COMMENT '备注',
  `result` int(2) NOT NULL DEFAULT '0' COMMENT '处理结果 0待处理,1同意,2拒绝',
  `image1` varchar(256) DEFAULT NULL COMMENT '图片1',
  `image2` varchar(256) DEFAULT NULL COMMENT '图片2',
  `image3` varchar(256) DEFAULT NULL COMMENT '图片3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for asset_info
-- ----------------------------
DROP TABLE IF EXISTS `asset_info`;
CREATE TABLE `asset_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_name` varchar(64) NOT NULL COMMENT '资源名称',
  `type_id` int(2) NOT NULL COMMENT '资源类型ID',
  `version` varchar(128) NOT NULL COMMENT '品牌型号',
  `manufacture` varchar(128) NOT NULL COMMENT '生产商',
  `price` bigint(18) NOT NULL COMMENT '采购价格',
  `status` int(2) DEFAULT '0' COMMENT '资源状态,0:空闲 1:借用中 2:使用中 3:维修中 4:报废',
  `purchase_date` datetime NOT NULL COMMENT '采购时间',
  `life` int(11) DEFAULT '1' COMMENT '寿命',
  `use_type` int(2) NOT NULL COMMENT '是否能借用 0,借用 1、领用 2、使用 ',
  `manage_id` int(11) DEFAULT NULL COMMENT '管理员ID',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库ID',
  `map_id` int(11) DEFAULT NULL COMMENT '地图ID',
  `asset_num` int(11) NOT NULL COMMENT '资产数量',
  `remarks` varchar(512) DEFAULT NULL COMMENT '备注',
  `image1` varchar(256) DEFAULT NULL COMMENT '图片1',
  `image2` varchar(256) DEFAULT NULL COMMENT '图片2',
  `image3` varchar(256) DEFAULT NULL COMMENT '图片3',
  `is_delete` int(2) DEFAULT '0' COMMENT '是否已删除 0否，1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for asset_map
-- ----------------------------
DROP TABLE IF EXISTS `asset_map`;
CREATE TABLE `asset_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `map_name` varchar(512) DEFAULT NULL COMMENT '地图上房间名',
  `map_x` int(11) DEFAULT NULL COMMENT 'x坐标',
  `map_y` int(11) DEFAULT NULL COMMENT 'y坐标',
  `map_length` int(11) DEFAULT NULL COMMENT '长度',
  `map_width` int(11) DEFAULT NULL COMMENT '宽度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1221 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for asset_type
-- ----------------------------
DROP TABLE IF EXISTS `asset_type`;
CREATE TABLE `asset_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(512) DEFAULT NULL COMMENT '类型名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oper_log
-- ----------------------------
DROP TABLE IF EXISTS `oper_log`;
CREATE TABLE `oper_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '操作员id',
  `remarks` varchar(512) NOT NULL COMMENT '操作内容',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `signature` varchar(512) DEFAULT NULL COMMENT '用户个性签名或介绍',
  `telephone` varchar(15) NOT NULL COMMENT '电话号码',
  `email` varchar(32) NOT NULL COMMENT '电子邮箱',
  `job_level` int(2) DEFAULT '0' COMMENT '用户等级0:普通学生用户,1:教师用户,2:维修人员,3，管理员，9:超级管理员',
  `status` int(2) DEFAULT '3' COMMENT '用户状态,0,正常,1锁定,3、待处理',
  `is_delete` int(2) DEFAULT NULL COMMENT '是否已删除 0否，1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '仓库名称',
  `address` varchar(64) NOT NULL COMMENT '仓库地址',
  `manage_id` int(11) NOT NULL COMMENT '管理员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remarks` varchar(512) DEFAULT NULL COMMENT '备注',
  `map_id` int(11) DEFAULT NULL COMMENT '地图ID',
  `is_delete` int(2) DEFAULT '0' COMMENT '是否已删除 0否，1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
