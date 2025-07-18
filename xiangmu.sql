/*
 Navicat Premium Dump SQL

 Source Server         : nihhhh
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : xiangmu

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 18/07/2025 17:04:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT 2 COMMENT '1:超级管理员 2:普通管理员',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'admin', '12345678901', '12345678901@163.com', 1, '2025-07-17 11:09:34', NULL);
INSERT INTO `admin` VALUES (3, 'wang', '123456', '王一', '13123412753', '13123412753@163.com', 2, '2025-07-17 11:00:05', NULL);

-- ----------------------------
-- Table structure for hotel_info
-- ----------------------------
DROP TABLE IF EXISTS `hotel_info`;
CREATE TABLE `hotel_info`  (
  `hotel_id` int NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hotel_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `longitude` decimal(10, 6) NULL DEFAULT NULL,
  `latitude` decimal(10, 6) NULL DEFAULT NULL,
  `business_license_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `legal_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `has_breakfast` tinyint(1) NULL DEFAULT 0,
  `has_parking` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`hotel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_info
-- ----------------------------
INSERT INTO `hotel_info` VALUES (1, '金凯大酒店', '位于市中心的豪华酒店，提供优质的服务和舒适的住宿环境', 116.397128, 39.916527, 'L123456789', '张三', '2025-07-17 09:56:55', 1, 1);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `balance` decimal(10, 2) NULL DEFAULT 0.00,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'testwang', '123456', '王一', '13123412753', '333820200102122887', 111234.00, '2025-07-17 10:02:42');
INSERT INTO `member` VALUES (2, 'testhou', '123456', '侯二', '12312312345', '3213214561646431', 3471.00, '2025-07-18 15:16:09');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `notice_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_id`(`member_id` ASC) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 1, '订单被管理员删除', '您的订单（房间:1301，入住:Fri Jul 18 00:00:00 CST 2025，离店:Fri Jul 18 00:00:00 CST 2025）已被管理员删除，如有疑问请联系客服。', '2025-07-17 15:33:58', 0);
INSERT INTO `notice` VALUES (2, 2, '订单被管理员删除', '您的订单（房间:1302，入住:Fri Jul 25 00:00:00 CST 2025，离店:Thu Jul 31 00:00:00 CST 2025）已被管理员删除，如有疑问请联系客服。', '2025-07-17 15:35:19', 0);

-- ----------------------------
-- Table structure for room_number
-- ----------------------------
DROP TABLE IF EXISTS `room_number`;
CREATE TABLE `room_number`  (
  `room_number_id` int NOT NULL AUTO_INCREMENT,
  `room_number_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_type_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0-空闲 1-已入住 2-退房打扫中',
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_number_id`) USING BTREE,
  INDEX `room_type_id`(`room_type_id` ASC) USING BTREE,
  CONSTRAINT `room_number_ibfk_1` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_number
-- ----------------------------
INSERT INTO `room_number` VALUES (1, '201', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (2, '202', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (3, '203', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (4, '204', 6, 1, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (5, '301', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (6, '302', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (7, '303', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (8, '304', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (9, '401', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (10, '402', 6, 0, '2025-07-17 09:56:55');
INSERT INTO `room_number` VALUES (11, '403', 6, 0, '2025-07-17 14:44:36');
INSERT INTO `room_number` VALUES (12, '501', 6, 0, '2025-07-17 14:47:14');
INSERT INTO `room_number` VALUES (13, '502', 6, 0, '2025-07-17 14:47:23');
INSERT INTO `room_number` VALUES (14, '503', 6, 0, '2025-07-17 14:47:42');
INSERT INTO `room_number` VALUES (15, '601', 5, 0, '2025-07-17 14:47:54');
INSERT INTO `room_number` VALUES (16, '602', 5, 0, '2025-07-17 14:48:04');
INSERT INTO `room_number` VALUES (17, '701', 5, 0, '2025-07-17 14:48:15');
INSERT INTO `room_number` VALUES (18, '702', 5, 0, '2025-07-17 14:48:25');
INSERT INTO `room_number` VALUES (19, '801', 5, 0, '2025-07-17 14:48:40');
INSERT INTO `room_number` VALUES (20, '802', 5, 0, '2025-07-17 14:48:51');
INSERT INTO `room_number` VALUES (21, '901', 4, 0, '2025-07-17 14:48:59');
INSERT INTO `room_number` VALUES (22, '902', 4, 0, '2025-07-17 14:49:15');
INSERT INTO `room_number` VALUES (23, '1001', 4, 0, '2025-07-17 14:49:24');
INSERT INTO `room_number` VALUES (24, '1002', 4, 0, '2025-07-17 14:49:33');
INSERT INTO `room_number` VALUES (25, '1101', 4, 0, '2025-07-17 14:49:54');
INSERT INTO `room_number` VALUES (26, '1102', 4, 0, '2025-07-17 14:50:04');
INSERT INTO `room_number` VALUES (27, '1201', 3, 1, '2025-07-17 14:50:12');
INSERT INTO `room_number` VALUES (28, '1202', 3, 0, '2025-07-17 14:50:17');
INSERT INTO `room_number` VALUES (29, '1301', 3, 0, '2025-07-17 14:50:29');
INSERT INTO `room_number` VALUES (30, '1302', 3, 0, '2025-07-17 14:50:35');
INSERT INTO `room_number` VALUES (31, '1401', 3, 0, '2025-07-17 14:50:44');
INSERT INTO `room_number` VALUES (32, '1402', 3, 0, '2025-07-17 14:50:56');
INSERT INTO `room_number` VALUES (33, '1501', 2, 0, '2025-07-17 14:51:05');
INSERT INTO `room_number` VALUES (34, '1502', 2, 0, '2025-07-17 14:51:13');
INSERT INTO `room_number` VALUES (35, '1601', 2, 0, '2025-07-17 14:51:37');
INSERT INTO `room_number` VALUES (36, '1602', 2, 0, '2025-07-17 14:51:44');
INSERT INTO `room_number` VALUES (37, '1701', 2, 0, '2025-07-18 14:17:23');
INSERT INTO `room_number` VALUES (38, '1702', 2, 0, '2025-07-17 14:51:58');
INSERT INTO `room_number` VALUES (39, '1801', 1, 0, '2025-07-18 14:23:28');
INSERT INTO `room_number` VALUES (40, '1802', 1, 1, '2025-07-17 14:52:12');

-- ----------------------------
-- Table structure for room_order
-- ----------------------------
DROP TABLE IF EXISTS `room_order`;
CREATE TABLE `room_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `order_status` int NULL DEFAULT 1 COMMENT '1:已预订 2:已入住 3:已完成 4:已取消',
  `checkin_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `enter_time` date NOT NULL,
  `leave_time` date NOT NULL,
  `room_type_id` int NOT NULL,
  `room_number_id` int NOT NULL,
  `room_number_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `guest_id_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `member_id`(`member_id` ASC) USING BTREE,
  INDEX `room_type_id`(`room_type_id` ASC) USING BTREE,
  INDEX `room_number_id`(`room_number_id` ASC) USING BTREE,
  CONSTRAINT `room_order_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `room_order_ibfk_2` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `room_order_ibfk_3` FOREIGN KEY (`room_number_id`) REFERENCES `room_number` (`room_number_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room_order
-- ----------------------------
INSERT INTO `room_order` VALUES (9, 1, 'testwang', NULL, 1, '2025-07-17T15:34:11.713794100', '2025-07-18', '2025-07-18', 1, 40, '1802', NULL, '2025-07-17 15:34:11');
INSERT INTO `room_order` VALUES (10, 2, 'testhou', NULL, 1, '2025-07-17T15:34:34.187051', '2025-07-18', '2025-07-18', 3, 27, '1201', NULL, '2025-07-17 15:34:34');
INSERT INTO `room_order` VALUES (13, 2, 'testhou', NULL, 1, '2025-07-17T16:11:36.824317500', '2025-07-29', '2025-07-31', 6, 4, '204', NULL, '2025-07-17 16:11:36');

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `room_type_id` int NOT NULL AUTO_INCREMENT,
  `room_type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_size_type` enum('小','中','大') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `area_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `floor_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `image1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `capacity` int NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (1, '总统套房', '大', '60 平方米、独立起居室、书房、无线上网', '18层', 1999.00, 'img1', 4, '2025-07-17 09:56:55');
INSERT INTO `room_type` VALUES (2, '豪华双床房', '中', '40 平方米、无线上网、55 英寸高清电视', '15层-17层', 999.00, 'img2', 2, '2025-07-17 09:56:55');
INSERT INTO `room_type` VALUES (3, '豪华大床房', '中', '40 平方米、无线上网、55 英寸高清电视', '12层-14层', 888.00, 'img3', 2, '2025-07-17 09:56:55');
INSERT INTO `room_type` VALUES (4, '标准双床房', '中', '40 平方米、55 英寸高清电视', '9层-11层', 555.00, 'img4', 2, '2025-07-17 10:37:49');
INSERT INTO `room_type` VALUES (5, '标准大床房', '中', '40 平方米、55 英寸高清电视', '6层-8层', 444.00, 'img5', 2, '2025-07-17 10:38:25');
INSERT INTO `room_type` VALUES (6, '标准单人间', '小', '35 平方米、55 英寸高清电视、无障碍淋浴间', '2层-5层', 199.00, 'img6', 1, '2025-07-18 09:54:45');

-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_admin
-- ----------------------------

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `protect_admin_delete`;
delimiter ;;
CREATE TRIGGER `protect_admin_delete` BEFORE DELETE ON `admin` FOR EACH ROW BEGIN
    IF OLD.username = 'admin' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'admin账号受保护，无法删除';
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
