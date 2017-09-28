/*
Navicat MySQL Data Transfer

Source Server         : nick
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : mws

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-07-06 16:58:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `catalog`
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `country` int(11) NOT NULL,
  `city` varchar(20) NOT NULL,
  `District` varchar(20) DEFAULT NULL,
  `Street` varchar(50) DEFAULT NULL,
  `FlatNumber` varchar(10) DEFAULT NULL,
  `Rooms` int(5) NOT NULL DEFAULT '0',
  `FlatShortDescription` varchar(200) DEFAULT NULL,
  `Square` double(10,2) NOT NULL DEFAULT '0.00',
  `Level` int(5) NOT NULL,
  `isPrivate` bit(1) NOT NULL DEFAULT b'0',
  `buildDate` int(11) NOT NULL DEFAULT '0',
  `Description` text,
  `publishDate` int(11) NOT NULL DEFAULT '0',
  `Views` int(11) NOT NULL DEFAULT '0',
  `ContactPerson` int(11) NOT NULL DEFAULT '0',
  `default_contact` bit(1) NOT NULL DEFAULT b'1',
  `isActive` bit(1) NOT NULL DEFAULT b'0',
  `def_photo` bit(1) NOT NULL DEFAULT b'1',
  `photo` varchar(50) DEFAULT NULL,
  `price_forSale` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price_forRent` decimal(10,2) NOT NULL DEFAULT '0.00',
  `rentRate` varchar(11) DEFAULT '',
  `isRent` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catalog
-- ----------------------------
INSERT INTO `catalog` VALUES ('3', '2', '1', 'Mariupol', 'Primorskiy', 'Lunina 16', '47', '4', null, '58.60', '4', '', '1420236288', 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris euismod dignissim sapien sit amet rhoncus. Etiam hendrerit ligula porta lacus rutrum auctor. Nulla et mauris consequat, lobortis justo et, eleifend diam. In non molestie ex. Nam suscipit neque eget eleifend consequat. Maecenas ultrices odio purus, vel pellentesque augue finibus auctor. Mauris id porta nibh. ', '1430684032', '0', '0', '', '', '', 'default_flat.jpg', '38000.00', '170.00', 'per/month', '');

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `countryId` int(11) NOT NULL,
  `city` varchar(20) NOT NULL,
  `isActive` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------

-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `contactType` int(11) NOT NULL,
  `value` varchar(100) NOT NULL,
  `pos` int(11) NOT NULL,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('3', '2', '1', '+380967490535', '1', '');

-- ----------------------------
-- Table structure for `contacttype`
-- ----------------------------
DROP TABLE IF EXISTS `contacttype`;
CREATE TABLE `contacttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(50) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacttype
-- ----------------------------
INSERT INTO `contacttype` VALUES ('1', 'Phone', '');
INSERT INTO `contacttype` VALUES ('2', 'Fax', '');
INSERT INTO `contacttype` VALUES ('3', 'Email', '');
INSERT INTO `contacttype` VALUES ('4', 'Skype', '');

-- ----------------------------
-- Table structure for `country`
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(4) NOT NULL,
  `Country` varchar(20) NOT NULL,
  `isActive` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', 'UA', 'Ukraine', '');

-- ----------------------------
-- Table structure for `district`
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cityId` int(11) NOT NULL,
  `District` varchar(15) NOT NULL,
  `isActive` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of district
-- ----------------------------

-- ----------------------------
-- Table structure for `flatservice`
-- ----------------------------
DROP TABLE IF EXISTS `flatservice`;
CREATE TABLE `flatservice` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `flatId` int(11) NOT NULL,
  `serviceType` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flatservice
-- ----------------------------

-- ----------------------------
-- Table structure for `gallery`
-- ----------------------------
DROP TABLE IF EXISTS `gallery`;
CREATE TABLE `gallery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flatId` int(11) NOT NULL,
  `Photo` varchar(50) NOT NULL,
  `FileName` varchar(50) NOT NULL,
  `pos` int(11) NOT NULL DEFAULT '0',
  `Description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gallery
-- ----------------------------
INSERT INTO `gallery` VALUES ('11', '3', '3_2_4.jpg', '3_2_4', '4', null);
INSERT INTO `gallery` VALUES ('12', '3', '3_2_1.jpg', '3_2_1', '1', null);
INSERT INTO `gallery` VALUES ('13', '3', '3_2_2.png', '3_2_2', '2', null);
INSERT INTO `gallery` VALUES ('14', '3', '3_2_3.jpg', '3_2_3', '3', null);
INSERT INTO `gallery` VALUES ('15', '3', '3_2_5.jpg', '3_2_5', '5', null);
INSERT INTO `gallery` VALUES ('16', '3', '3_2_6.jpg', '3_2_6', '6', null);
INSERT INTO `gallery` VALUES ('17', '3', '3_2_7.jpg', '3_2_7', '7', null);
INSERT INTO `gallery` VALUES ('18', '3', '3_2_11.png', '3_2_11', '11', null);
INSERT INTO `gallery` VALUES ('19', '3', '3_2_9.jpg', '3_2_9', '9', null);
INSERT INTO `gallery` VALUES ('20', '3', '3_2_12.png', '3_2_12', '12', null);

-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `ugroup` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `perms` text NOT NULL,
  PRIMARY KEY (`ugroup`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', 'All');
INSERT INTO `permissions` VALUES ('2', 'Guest');
INSERT INTO `permissions` VALUES ('3', 'Users');

-- ----------------------------
-- Table structure for `privatcontact`
-- ----------------------------
DROP TABLE IF EXISTS `privatcontact`;
CREATE TABLE `privatcontact` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Skype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privatcontact
-- ----------------------------

-- ----------------------------
-- Table structure for `services`
-- ----------------------------
DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceName` varchar(100) NOT NULL,
  `pos` int(11) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of services
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ugroup` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `RegDate` int(11) NOT NULL,
  `def_avatar` bit(1) NOT NULL DEFAULT b'1',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', 'NickTer', 'a8fdfd992f3380c08088010c31f09c84', 'Nick', 'Terekhin', '0', '', null);
INSERT INTO `users` VALUES ('2', '3', 'VasiaVas', '14e1b600b1fd579f47433b88e8d85291', 'Vasia', 'Vasiliev', '0', '', null);
INSERT INTO `users` VALUES ('3', '3', 'PetrovPetia', 'ec6a6536ca304edf844d1d248a4f08dc', 'Petia', 'Petrov', '1426467080', '', null);
INSERT INTO `users` VALUES ('4', '3', 'Misha', '1f32aa4c9a1d2ea010adcf2348166a04', 'Ivan', 'Mihailovich', '1426467592', '', null);
INSERT INTO `users` VALUES ('5', '3', 'Igor', 'd9b1d7db4cd6e70935368a1efb10e377', 'Igor', 'Igorian', '1426467829', '', null);

-- ----------------------------
-- Table structure for `user_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `ugroup` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `groupName` varchar(50) NOT NULL,
  PRIMARY KEY (`ugroup`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', 'Administartor');
INSERT INTO `user_group` VALUES ('2', 'Guest');
INSERT INTO `user_group` VALUES ('3', 'Users');

-- ----------------------------
-- Procedure structure for `test`
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test`()
BEGIN
	#Routine body goes here...

END
;;
DELIMITER ;
