/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : emailsys

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-06-22 09:11:09
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ausertable`
-- ----------------------------
DROP TABLE IF EXISTS `ausertable`;
CREATE TABLE `ausertable` (
  `aname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apwd` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`aname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ausertable
-- ----------------------------
INSERT INTO ausertable VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for `busertable`
-- ----------------------------
DROP TABLE IF EXISTS `busertable`;
CREATE TABLE `busertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bemail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `bpwd` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `islock` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of busertable
-- ----------------------------
INSERT INTO busertable VALUES ('7', '123456', '123456', '0');
INSERT INTO busertable VALUES ('8', '1234567', '1', '0');

-- ----------------------------
-- Table structure for `emailtable`
-- ----------------------------
DROP TABLE IF EXISTS `emailtable`;
CREATE TABLE `emailtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(10000) CHARACTER SET utf8 DEFAULT NULL,
  `attachfilename` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '原文件名',
  `attachfile` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '新文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of emailtable
-- ----------------------------
INSERT INTO emailtable VALUES ('9', '12', 'saassa\r\nasassa\r\nsaas\r\n   sasaas\r\n   \r\n     陈恒', 'Java校内实训成绩.xlsx|过年的节日习俗.doc', '50353177.xlsx|57483740.doc');
INSERT INTO emailtable VALUES ('10', '12', '其味无穷', '过年的节日习俗.doc', '46616146.doc');
INSERT INTO emailtable VALUES ('11', 'Re:12', 'At 2018-05-17 123456 write:                                                                               saassa\r\nasassa\r\nsaas\r\n   sasaas\r\n   \r\n     陈恒', '本科毕业论文答辩评语书写模式.doc|知识图谱核心技术与应用高级培训班--第二期.pdf', '77013996.doc|62770871.pdf');

-- ----------------------------
-- Table structure for `rsrecordtable`
-- ----------------------------
DROP TABLE IF EXISTS `rsrecordtable`;
CREATE TABLE `rsrecordtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_r` int(11) NOT NULL,
  `email_r` varchar(500) CHARACTER SET utf8 NOT NULL COMMENT '接收人邮件地址',
  `id_s` int(11) NOT NULL COMMENT '发件人id',
  `email_s` varchar(500) DEFAULT NULL,
  `id_email` int(11) NOT NULL,
  `sendtime` date NOT NULL,
  `isopen` tinyint(2) NOT NULL DEFAULT '0',
  `isdelete` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id2` (`id_s`),
  KEY `id3` (`id_email`),
  KEY `id1` (`id_r`),
  CONSTRAINT `id1` FOREIGN KEY (`id_r`) REFERENCES `busertable` (`id`),
  CONSTRAINT `id2` FOREIGN KEY (`id_s`) REFERENCES `busertable` (`id`),
  CONSTRAINT `id3` FOREIGN KEY (`id_email`) REFERENCES `emailtable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rsrecordtable
-- ----------------------------
INSERT INTO rsrecordtable VALUES ('11', '8', '1234567', '7', '123456', '9', '2018-05-17', '1', '1');
INSERT INTO rsrecordtable VALUES ('12', '7', '123456', '7', '123456', '9', '2018-05-17', '0', '1');
INSERT INTO rsrecordtable VALUES ('13', '8', '1234567', '7', '123456', '10', '2018-05-17', '1', '0');
INSERT INTO rsrecordtable VALUES ('14', '7', '123456', '8', '1234567', '11', '2018-05-18', '1', '1');
INSERT INTO rsrecordtable VALUES ('15', '8', '1234567', '8', '1234567', '11', '2018-05-18', '1', '1');
