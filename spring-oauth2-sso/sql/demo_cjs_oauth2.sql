/*
 Navicat Premium Data Transfer

 Source Server         : vm-3306
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : shf.com:3306
 Source Schema         : demo_cjs_oauth2

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 24/05/2020 22:18:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('cjs-OrderManagement', NULL, '$2a$10$8yVwRGY6zB8wv5o0kRgD0ep/HVcvtSZUZsYu/586Egxc1hv3cI9Q6', 'all', 'authorization_code,refresh_token', 'http://localhost:8083/orderSystem/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('cjs-UserManagement', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'password,authorization_code,client_credentials,refresh_token', 'http://localhost:8082/memberSystem/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('cloudDemo', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'authorization_code,refresh_token', 'http://localhost:8082/memberSystem/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('demo_resource', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'authorization_code,refresh_token', 'http://localhost:8020/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('OrderManagement', NULL, '$2a$10$8yVwRGY6zB8wv5o0kRgD0ep/HVcvtSZUZsYu/586Egxc1hv3cI9Q6', 'all', 'authorization_code,refresh_token', 'http://localhost:8083/orderSystem/login', NULL, 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('sso_client1', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'password,authorization_code,client_credentials,refresh_token', 'http://localhost:6010/shf/login', NULL, 7200, 8400, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('UserManagement', NULL, '$2a$10$ZRmPFVgE6o2aoaK6hv49pOt5BZIKBDLywCaFkuAs6zYmRkpKHgyuO', 'all', 'password,authorization_code,client_credentials,refresh_token', 'http://localhost:8090/portal/login', NULL, 7200, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT 0 COMMENT '父ID',
  `type` tinyint(4) NOT NULL COMMENT '资源类型（1：菜单，2：按钮，3：操作）',
  `name` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '资源名称',
  `code` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '资源标识（或者叫权限字符串）',
  `uri` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '资源URI',
  `seq` int(11) DEFAULT 1 COMMENT '序号',
  `create_user` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_user` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 3, 'add', 'member:add', '/**', 1, 'system', '2019-03-03 18:50:17', 'system', '2019-03-03 18:50:20');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '员工', 'normal', '普通员工', 'system', '2019-02-12 11:14:41', NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '部门经理', 'manager', '部门经理', 'system', '2019-02-12 11:15:37', NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '客服', 'kefu', '客服', 'system', '2019-02-12 11:16:27', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 2, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态（0：锁定，1：解锁）',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', 'abc@123.com', 1, 'system', '2019-02-12 11:12:19', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'zhangsan', '123456', '张三', 'zhangsan@126.com', 1, 'system', '2019-02-12 11:13:27', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);
INSERT INTO `sys_user_role` VALUES (3, 1, 3);
INSERT INTO `sys_user_role` VALUES (4, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
