/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2023-06-23 18:02:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', 'Beijin', 'Beijing');
INSERT INTO `t_address` VALUES ('2', 'Hubei', 'Huanggang');
INSERT INTO `t_address` VALUES ('3', 'Chongqing', 'Chongqing');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `addr_id` int(11) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `audit_status` char(1) NOT NULL DEFAULT '0',
  `process_status` tinyint(1) NOT NULL DEFAULT '1',
  `audit_stauts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmdissy7ikj0gkeu0nr0hk1bwn` (`address_id`),
  KEY `idx_last_name` (`last_name`) USING BTREE,
  CONSTRAINT `FKmdissy7ikj0gkeu0nr0hk1bwn` FOREIGN KEY (`address_id`) REFERENCES `t_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('1', '98', '2019-08-25 20:00:50', 'tttx@126.com', 'kevin', '3', '0', '5', null);
INSERT INTO `t_person` VALUES ('4', '101', '2019-08-25 20:00:50', 'dd@126.com', 'dd', '1', '1', '3', null);
INSERT INTO `t_person` VALUES ('5', '102', '2019-08-25 20:00:50', 'ee@126.com', 'ee', '1', '0', '4', null);
INSERT INTO `t_person` VALUES ('6', '103', '2019-08-25 20:00:50', 'ff@126.com', 'ff', '1', '1', '1', null);
INSERT INTO `t_person` VALUES ('8', '105', '2019-08-25 20:00:50', 'hh@126.com', 'hh', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('9', '106', '2019-08-25 20:00:50', 'ii@126.com', 'ii', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('10', '107', '2019-08-25 20:00:50', 'jj@126.com', 'jj', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('11', '108', '2019-08-25 20:00:50', 'kk@126.com', 'kk', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('12', '109', '2019-08-25 20:00:50', 'll@126.com', 'll', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('13', '110', '2019-08-25 20:00:50', 'mm@126.com', 'mm', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('14', '111', '2019-08-25 20:00:50', 'nn@126.com', 'nn', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('15', '112', '2019-08-25 20:00:50', 'oo@126.com', 'oo', '1', '0', '1', null);
INSERT INTO `t_person` VALUES ('16', '113', '2019-08-25 20:00:50', 'pp@126.com', 'pp', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('17', '114', '2019-08-25 20:00:50', 'qq@126.com', 'Tom', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('18', '115', '2019-08-25 20:00:50', 'rr@126.com', 'rr', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('19', '116', '2019-08-25 20:00:50', 'ss@126.com', 'ss', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('20', '117', '2019-08-25 20:00:50', 'tt@126.com', 'tt', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('21', '118', '2019-08-25 20:00:50', 'uu@126.com', 'uu', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('22', '119', '2019-08-25 20:00:50', 'vv@126.com', 'vv', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('23', '120', '2019-08-25 20:00:50', 'ww@126.com', 'ww', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('25', '122', '2019-08-25 20:00:50', 'yy@126.com', 'yy', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('26', '123', '2019-08-25 20:00:50', 'zz@126.com', 'zz', '2', '0', '1', null);
INSERT INTO `t_person` VALUES ('27', null, '2023-06-18 17:23:40', 'over@126.com', 'over2', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('28', '98', '2021-11-11 23:43:13', 'aa@126.com', 'aa', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('31', '101', '2021-11-11 23:43:13', 'dd@126.com', 'dd', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('32', '102', '2021-11-11 23:43:13', 'ee@126.com', 'ee', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('33', '103', '2021-11-11 23:43:13', 'ff@126.com', 'ff', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('35', '105', '2021-11-11 23:43:13', 'hh@126.com', 'hh', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('36', '106', '2021-11-11 23:43:13', 'ii@126.com', 'ii', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('37', '107', '2021-11-11 23:43:13', 'jj@126.com', 'jj', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('38', '108', '2021-11-11 23:43:13', 'kk@126.com', 'kk', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('39', '109', '2021-11-11 23:43:13', 'll@126.com', 'll', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('40', '110', '2021-11-11 23:43:13', 'mm@126.com', 'mm', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('41', '111', '2021-11-11 23:43:13', 'nn@126.com', 'nn', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('42', '112', '2021-11-11 23:43:13', 'oo@126.com', 'oo', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('43', '113', '2021-11-11 23:43:13', 'pp@126.com', 'pp', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('44', '114', '2021-11-11 23:43:13', 'qq@126.com', 'qq', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('45', '115', '2021-11-11 23:43:13', 'rr@126.com', 'rr', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('46', '116', '2021-11-11 23:43:13', 'ss@126.com', 'ss', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('47', '117', '2021-11-11 23:43:13', 'tt@126.com', 'tt', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('48', '118', '2021-11-11 23:43:13', 'uu@126.com', 'uu', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('49', '119', '2021-11-11 23:43:13', 'vv@126.com', 'vv', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('50', '120', '2021-11-11 23:43:13', 'ww@126.com', 'ww', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('52', '122', '2021-11-11 23:43:13', 'yy@126.com', 'yy', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('53', '123', '2021-11-11 23:43:13', 'zz@126.com', 'zz', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('54', '98', '2023-06-18 17:23:40', 'aa@126.com', 'aa', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('55', '99', '2023-06-18 17:23:40', 'bb@126.com', 'bb', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('56', '100', '2023-06-18 17:23:40', 'cc@126.com', 'cc', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('57', '101', '2023-06-18 17:23:40', 'dd@126.com', 'dd', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('58', '102', '2023-06-18 17:23:40', 'ee@126.com', 'ee', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('59', '103', '2023-06-18 17:23:40', 'ff@126.com', 'ff', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('60', '104', '2023-06-18 17:23:40', 'gg@126.com', 'gg', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('61', '105', '2023-06-18 17:23:40', 'hh@126.com', 'hh', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('62', '106', '2023-06-18 17:23:40', 'ii@126.com', 'ii', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('63', '107', '2023-06-18 17:23:40', 'jj@126.com', 'jj', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('64', '108', '2023-06-18 17:23:40', 'kk@126.com', 'kk', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('65', '109', '2023-06-18 17:23:40', 'll@126.com', 'll', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('66', '110', '2023-06-18 17:23:40', 'mm@126.com', 'mm', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('67', '111', '2023-06-18 17:23:40', 'nn@126.com', 'nn', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('68', '112', '2023-06-18 17:23:40', 'oo@126.com', 'oo', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('69', '113', '2023-06-18 17:23:40', 'pp@126.com', 'pp', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('70', '114', '2023-06-18 17:23:40', 'qq@126.com', 'qq', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('71', '115', '2023-06-18 17:23:40', 'rr@126.com', 'rr', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('72', '116', '2023-06-18 17:23:40', 'ss@126.com', 'ss', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('73', '117', '2023-06-18 17:23:40', 'tt@126.com', 'tt', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('74', '118', '2023-06-18 17:23:40', 'uu@126.com', 'uu', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('75', '119', '2023-06-18 17:23:40', 'vv@126.com', 'vv', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('76', '120', '2023-06-18 17:23:40', 'ww@126.com', 'ww', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('77', '121', '2023-06-18 17:23:40', 'xx@126.com', 'xx', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('78', '122', '2023-06-18 17:23:40', 'yy@126.com', 'yy', null, '0', '1', null);
INSERT INTO `t_person` VALUES ('79', '123', '2023-06-18 17:23:40', 'zz@126.com', 'zz', null, '0', '1', null);
