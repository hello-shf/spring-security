/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 01/05/2019 18:11:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户编码',
  `create_time` timestamp(0) NOT NULL DEFAULT '2019-01-01 00:00:00' COMMENT '注册时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0：未删除 1：删除',
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (4, 'ef269d06e6b1497fbb209becca248252', '2019-04-22 14:18:28', '2019-05-01 18:11:19', 0, '黎明', 'admin', '1', '122434', '232332');
INSERT INTO `t_user` VALUES (5, 'ef269d06e6b1497fbb209becca248251', '2019-04-22 14:24:10', '2019-04-29 06:55:39', 0, '学友', 'admin1', '1', '18888888888', '8888@qq.com');
INSERT INTO `t_user` VALUES (7, '074aca14664b49ce9165bc597d928078', '2019-01-01 00:00:00', '2019-05-01 18:10:54', 0, '德华', 'admin', '1', '18839339393', '8888@qq.com');
INSERT INTO `t_user` VALUES (13, '0bad7a4fea5f4c129c454cdf658744ec', '2019-01-01 00:00:00', '2019-05-01 18:11:13', 0, '富城', 'admin', '1', '18839339393', '8888@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
