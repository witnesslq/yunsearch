/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : yunsearch

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-04-12 23:41:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yun_data
-- ----------------------------
DROP TABLE IF EXISTS `yun_data`;
CREATE TABLE `yun_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `share_id` bigint(20) DEFAULT NULL,
  `data_id` bigint(20) DEFAULT NULL,
  `share_name` varchar(255) DEFAULT NULL,
  `uk` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `share_time` datetime DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_single_share` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_yun_data_shareid` (`share_id`) USING BTREE,
  KEY `index_yun_data_uk` (`uk`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=135796 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yun_user
-- ----------------------------
DROP TABLE IF EXISTS `yun_user`;
CREATE TABLE `yun_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `uk` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `pubshare_count` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `pubshare_crawled` bit(1) DEFAULT NULL,
  `follow_crawled` bit(1) DEFAULT NULL,
  `follow_time` datetime DEFAULT NULL,
  `fans_crawled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_yun_user_uk` (`uk`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52628 DEFAULT CHARSET=utf8;
