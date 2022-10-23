/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_MARIADB
 Source Server Type    : MariaDB
 Source Server Version : 100803
 Source Host           : localhost:3309
 Source Schema         : z_acf

 Target Server Type    : MariaDB
 Target Server Version : 100803
 File Encoding         : 65001

 Date: 09/10/2022 03:58:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for complex_data
-- ----------------------------
DROP TABLE IF EXISTS `complex_data`;
CREATE TABLE `complex_data`  (
  `id` bigint(20) NOT NULL,
  `first_name` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_name` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` char(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `gender` char(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `my_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for insert_demo
-- ----------------------------
DROP TABLE IF EXISTS `insert_demo`;
CREATE TABLE `insert_demo`  (
  `id` bigint(20) NOT NULL,
  `first_name` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_name` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mockdata
-- ----------------------------
DROP TABLE IF EXISTS `mockdata`;
CREATE TABLE `mockdata`  (
  `id` bigint(20) NULL DEFAULT NULL,
  `jobtitle` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `emailaddress` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `firstnamelastname` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `randoms` varchar(10000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kosmetik` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ktp` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kampus` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `mat_kul` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `institusi` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `skill` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `departemen` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `saham` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `no_cc` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `obat` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tgl` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
