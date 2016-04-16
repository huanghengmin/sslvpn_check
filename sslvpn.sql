-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.13-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for sslvpn
DROP DATABASE IF EXISTS `sslvpn`;
CREATE DATABASE IF NOT EXISTS `sslvpn` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sslvpn`;


-- Dumping structure for table sslvpn.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `modifiedPasswordTime` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `depart` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `start_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `end_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `start_hour` int(11) DEFAULT NULL,
  `end_hour` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_bin,
  `remote_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ip_type` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户表';

-- Dumping data for table sslvpn.account: ~5 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`id`, `user_name`, `password`, `sex`, `phone`, `created_time`, `modified_time`, `modifiedPasswordTime`, `status`, `depart`, `title`, `name`, `email`, `start_ip`, `end_ip`, `start_hour`, `end_hour`, `description`, `remote_ip`, `mac`, `ip_type`) VALUES
	(1, 'admin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2010-07-04 13:52:36', '2015-04-30 11:30:42', NULL, '有效', '信息中心', '主任', '初始化管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.254.254', 9, 18, '这是一个默认的超级用户信息', '192.168.2.176', '5C-63-BF-1D-72-07', 1),
	(2, 'authadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-04-12 14:22:35', '2013-05-07 18:27:30', NULL, '有效', '信息中心', '主任', '授权管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 1, 22, '这是一个默认的授权用户信息', '', NULL, 1),
	(3, 'configadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-06-12 18:04:01', '2013-05-07 18:27:45', NULL, '有效', '信息中心', '主任', '配置管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 9, 23, '这是一个默认的配置用户信息', '', NULL, 1),
	(4, 'auditadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-07-03 10:19:57', '2014-08-26 13:01:36', NULL, '有效', '信息中心', '主任', '审计管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 7, 18, '这是一个默认的审计用户信息', NULL, NULL, 1),
	(5, 'test', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '6557889', '2015-08-05 18:25:52', '2015-08-11 17:59:14', '2015-08-05 18:25:52', '有效', '信息部', '主任', '测试', 'hello@hzih.net', '0.0.0.0', '255.255.255.255', 1, 23, '这是一个测试3333用户信息', NULL, '', 1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- Dumping structure for table sslvpn.account_role
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FK410D03481FCE46BD` (`role_id`),
  KEY `FK410D034811351AF7` (`account_id`),
  KEY `FK410D0348A472BB1A` (`role_id`),
  KEY `FK410D0348CEF66D7A` (`account_id`),
  KEY `FK410D034824B3696E` (`role_id`),
  KEY `FK410D0348B5F52EA6` (`account_id`),
  CONSTRAINT `FK410D034811351AF7` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D0348A472BB1A` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.account_role: ~5 rows (approximately)
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
REPLACE INTO `account_role` (`account_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;


-- Dumping structure for table sslvpn.backup
DROP TABLE IF EXISTS `backup`;
CREATE TABLE IF NOT EXISTS `backup` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `backup_account_id` bigint(20) DEFAULT '0',
  `backup_time` varchar(50) DEFAULT NULL,
  `backup_all` tinyint(4) DEFAULT '0',
  `backup_server` tinyint(4) DEFAULT '0',
  `backup_pki` tinyint(4) DEFAULT '0',
  `backup_net` tinyint(4) DEFAULT '0',
  `backup_file` varchar(50) DEFAULT NULL,
  `backup_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_backup_account` (`backup_account_id`),
  CONSTRAINT `FK_backup_account` FOREIGN KEY (`backup_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.backup: ~0 rows (approximately)
/*!40000 ALTER TABLE `backup` DISABLE KEYS */;
/*!40000 ALTER TABLE `backup` ENABLE KEYS */;


-- Dumping structure for table sslvpn.equipment_log
DROP TABLE IF EXISTS `equipment_log`;
CREATE TABLE IF NOT EXISTS `equipment_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `equipment_name` varchar(255) DEFAULT NULL,
  `log_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.equipment_log: ~0 rows (approximately)
/*!40000 ALTER TABLE `equipment_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_log` ENABLE KEYS */;


-- Dumping structure for table sslvpn.groups
DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deny_access` tinyint(4) DEFAULT '0',
  `group_name` varchar(50) DEFAULT NULL,
  `group_desc` varchar(100) DEFAULT NULL,
  `level` tinyint(4) DEFAULT '0',
  `group_code` varchar(50) DEFAULT NULL,
  `assign_nets` varchar(200) DEFAULT NULL,
  `dynamic_ip_range` varchar(100) DEFAULT NULL,
  `allow_group_ids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.groups: ~3 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
REPLACE INTO `groups` (`id`, `deny_access`, `group_name`, `group_desc`, `level`, `group_code`, `assign_nets`, `dynamic_ip_range`, `allow_group_ids`) VALUES
	(1, 0, '一级组', '一级组', 0, NULL, NULL, NULL, NULL),
	(2, 0, '二级组', '二级组', 1, NULL, NULL, NULL, NULL),
	(3, 0, '三级组', '三级组', 2, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;


-- Dumping structure for table sslvpn.group_source_nets
DROP TABLE IF EXISTS `group_source_nets`;
CREATE TABLE IF NOT EXISTS `group_source_nets` (
  `source_net_id` int(11) NOT NULL DEFAULT '0',
  `group_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`source_net_id`,`group_id`),
  KEY `FK46FE425AD95A9C2F` (`source_net_id`),
  KEY `FK46FE425ABD119723` (`group_id`),
  CONSTRAINT `FK_group_source_nets_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_group_source_nets_source_nets` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.group_source_nets: ~0 rows (approximately)
/*!40000 ALTER TABLE `group_source_nets` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_source_nets` ENABLE KEYS */;


-- Dumping structure for table sslvpn.log
DROP TABLE IF EXISTS `log`;
CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn` varchar(100) DEFAULT NULL,
  `serial_number` varchar(100) DEFAULT NULL,
  `subject_dn` varchar(200) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `trusted_ip` varchar(50) DEFAULT NULL,
  `trusted_port` int(11) DEFAULT NULL,
  `protocol` varchar(16) DEFAULT NULL,
  `remote_ip` varchar(50) DEFAULT NULL,
  `remote_netmask` varchar(50) DEFAULT NULL,
  `bytes_received` bigint(20) DEFAULT '0',
  `bytes_sent` bigint(20) DEFAULT '0',
  `status` tinyint(4) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.log: ~0 rows (approximately)
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


-- Dumping structure for table sslvpn.nat
DROP TABLE IF EXISTS `nat`;
CREATE TABLE IF NOT EXISTS `nat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bindIp` varchar(50) DEFAULT NULL,
  `bindPort` varchar(50) DEFAULT NULL,
  `protocol` varchar(50) DEFAULT NULL,
  `level` tinyint(4) DEFAULT NULL,
  `targetIp` varchar(50) DEFAULT NULL,
  `targetPort` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nat` (`bindIp`,`bindPort`,`protocol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.nat: ~0 rows (approximately)
/*!40000 ALTER TABLE `nat` DISABLE KEYS */;
/*!40000 ALTER TABLE `nat` ENABLE KEYS */;


-- Dumping structure for table sslvpn.permission
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.permission: ~48 rows (approximately)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
REPLACE INTO `permission` (`ID`, `CODE`, `NAME`, `DESCRIPTION`, `PARENT_ID`, `SEQ`) VALUES
	(100, 'TOP_QXGL', '权限管理', NULL, 0, 0),
	(101, 'SECOND_YHGL', '用户管理', NULL, 100, 1),
	(102, 'SECOND_JSGL', '角色管理', NULL, 100, 2),
	(103, 'SECOND_AQCL', '安全策略', NULL, 100, 3),
	(110, 'TOP_WLGL', '网络管理', NULL, 0, 0),
	(111, 'SECOND_JKGL', '接口管理', NULL, 110, 1),
	(112, 'SECOND_LTCS', '连通测试', NULL, 110, 2),
	(113, 'SECOND_LYGL', '路由管理', NULL, 110, 3),
	(114, 'SECOND_PZGL', '安全配置', NULL, 110, 4),
	(120, 'TOP_XTGL', '系统管理', NULL, 0, 0),
	(121, 'SECOND_XTGL', '系统管理', NULL, 120, 1),
	(122, 'SECOND_ZSGL', '证书管理', NULL, 120, 2),
	(123, 'SECOND_FWGL', '服务管理', NULL, 120, 3),
	(124, 'SECOND_SJRB', '双机热备', NULL, 120, 4),
	(130, 'TOP_SJGL', '审计管理', NULL, 0, 0),
	(131, 'SECOND_GLRZ', '管理日志', NULL, 130, 1),
	(132, 'SECOND_RZXZ', '日志下载', NULL, 130, 2),
	(133, 'SECOND_RZZJ', '日志主机', NULL, 130, 3),
	(140, 'TOP_BJGL', '报警管理', NULL, 0, 0),
	(141, 'SECOND_SBBJ', '设备报警', NULL, 140, 1),
	(150, 'TOP_ZYGL', '资源管理', NULL, 0, 0),
	(151, 'SECOND_ZWZY', '子网资源', NULL, 150, 1),
	(152, 'SECOND_XYZY', '协议资源', NULL, 150, 2),
	(160, 'TOP_FWGL', '服务管理', NULL, 0, 0),
	(161, 'SECOND_FWZT', '服务状态', NULL, 160, 1),
	(162, 'SECOND_JBPZ', '基本配置', NULL, 160, 2),
	(163, 'SECOND_ZSPZ', '证书配置', NULL, 160, 3),
	(164, 'SECOND_SJBJ', '数据标记', NULL, 160, 4),
	(170, 'TOP_ZDGL', '终端管理', NULL, 0, 0),
	(171, 'SECOND_KXZD', '可信终端', NULL, 170, 1),
	(172, 'SECOND_ZDYH', '终端用户', NULL, 170, 2),
	(173, 'SECOND_ZDFZ', '终端分组', NULL, 170, 3),
	(174, 'SECOND_ZDZX', '终端在线', NULL, 170, 4),
	(175, 'SECOND_ZDRZ', '终端日志', NULL, 170, 5),
	(176, 'SECOND_ZDJL', '终端记录', NULL, 170, 6),
	(180, 'TOP_XTPZ', '系统配置', NULL, 0, 0),
	(181, 'SECOND_CYPZ', '策略配置', NULL, 180, 1),
	(182, 'SECOND_SJPZ', '时间配置', NULL, 180, 2),
	(190, 'TOP_DXGL', '吊销管理', NULL, 0, 0),
	(191, 'SECOND_DXLB', '吊销列表', NULL, 190, 1),
	(192, 'SECOND_DXWJ', '吊销文件', NULL, 190, 2),
	(193, 'SECOND_DXGX', '吊销更新', NULL, 190, 3),
	(200, 'TOP_JKGL', '监控管理', NULL, 0, 0),
	(201, 'SECOND_ZJJK', '主机监控', NULL, 200, 1),
	(202, 'SECOND_JKBJ', '监控报警', NULL, 200, 2),
	(210, 'TOP_BBSJ', '版本升级', NULL, 0, 0),
	(211, 'SECOND_KFBB', '客户版本', NULL, 210, 1),
	(212, 'SECOND_FWBB', '服务版本', NULL, 210, 2),
	(213, 'SECOND_BFHF', '备份恢复', NULL, 210, 3);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- Dumping structure for table sslvpn.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.role: ~6 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`, `description`, `createdTime`, `modifiedTime`) VALUES
	(1, '初始化管理员', '初始化管理员', '2010-07-04 15:07:08', '2015-06-15 13:59:12'),
	(2, '授权管理员', '授权管理员', '2012-07-03 10:06:20', '2015-05-15 16:07:18'),
	(3, '配置管理员', '配置管理员', '2012-03-14 12:33:05', '2015-06-17 16:30:42'),
	(4, '审计管理员', '审计管理员', '2012-06-12 18:37:24', '2015-06-17 16:29:32'),
	(5, '调试管理员', '调试管理员', '2014-04-01 15:51:14', '2015-08-05 20:30:25'),
	(6, '安全管理员', '安全管理员', '2015-04-21 09:44:32', '2015-04-21 09:44:39');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table sslvpn.role_permission
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKBD40D53851BABF58` (`role_id`),
  KEY `FKBD40D53852A81638` (`permission_id`),
  KEY `FKBD40D538A472BB1A` (`role_id`),
  KEY `FKBD40D53852B0B87A` (`permission_id`),
  KEY `FKBD40D53824B3696E` (`role_id`),
  KEY `FKBD40D5387AC257CE` (`permission_id`),
  CONSTRAINT `FKBD40D5387AC257CE` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538A472BB1A` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.role_permission: ~99 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
REPLACE INTO `role_permission` (`permission_id`, `role_id`) VALUES
	(100, 1),
	(101, 1),
	(102, 1),
	(103, 1),
	(110, 3),
	(111, 3),
	(112, 3),
	(113, 3),
	(114, 3),
	(120, 3),
	(121, 3),
	(122, 3),
	(123, 3),
	(124, 3),
	(140, 3),
	(141, 3),
	(150, 3),
	(151, 3),
	(152, 3),
	(160, 3),
	(161, 3),
	(162, 3),
	(163, 3),
	(164, 3),
	(170, 3),
	(171, 3),
	(172, 3),
	(173, 3),
	(180, 3),
	(181, 3),
	(190, 3),
	(191, 3),
	(192, 3),
	(193, 3),
	(200, 3),
	(202, 3),
	(210, 3),
	(211, 3),
	(212, 3),
	(213, 3),
	(130, 4),
	(131, 4),
	(132, 4),
	(133, 4),
	(170, 4),
	(174, 4),
	(175, 4),
	(176, 4),
	(200, 4),
	(201, 4),
	(202, 4),
	(100, 5),
	(101, 5),
	(102, 5),
	(103, 5),
	(110, 5),
	(111, 5),
	(112, 5),
	(113, 5),
	(114, 5),
	(120, 5),
	(121, 5),
	(122, 5),
	(123, 5),
	(124, 5),
	(130, 5),
	(131, 5),
	(132, 5),
	(133, 5),
	(140, 5),
	(141, 5),
	(150, 5),
	(152, 5),
	(160, 5),
	(161, 5),
	(162, 5),
	(163, 5),
	(164, 5),
	(170, 5),
	(171, 5),
	(172, 5),
	(173, 5),
	(174, 5),
	(175, 5),
	(176, 5),
	(180, 5),
	(181, 5),
	(182, 5),
	(190, 5),
	(191, 5),
	(192, 5),
	(193, 5),
	(200, 5),
	(201, 5),
	(202, 5),
	(210, 5),
	(211, 5),
	(212, 5),
	(213, 5);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;


-- Dumping structure for table sslvpn.route_user
DROP TABLE IF EXISTS `route_user`;
CREATE TABLE IF NOT EXISTS `route_user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_idcard` varchar(20) DEFAULT NULL,
  `user_province` varchar(20) DEFAULT NULL,
  `user_city` varchar(20) DEFAULT NULL,
  `user_organization` varchar(20) DEFAULT NULL,
  `user_institution` varchar(20) DEFAULT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_address` varchar(50) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.route_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `route_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_user` ENABLE KEYS */;


-- Dumping structure for table sslvpn.safe_policy
DROP TABLE IF EXISTS `safe_policy`;
CREATE TABLE IF NOT EXISTS `safe_policy` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `timeout` int(11) DEFAULT NULL,
  `passwordLength` int(11) DEFAULT NULL,
  `errorLimit` int(11) DEFAULT NULL,
  `remoteDisabled` tinyint(1) DEFAULT NULL,
  `macDisabled` tinyint(1) DEFAULT NULL,
  `passwordRules` varchar(255) DEFAULT NULL,
  `lockTime` int(10) NOT NULL DEFAULT '24' COMMENT '锁定时间(小时)',
  `maxSession` int(10) NOT NULL DEFAULT '24',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全策略表';

-- Dumping data for table sslvpn.safe_policy: ~1 rows (approximately)
/*!40000 ALTER TABLE `safe_policy` DISABLE KEYS */;
REPLACE INTO `safe_policy` (`id`, `timeout`, `passwordLength`, `errorLimit`, `remoteDisabled`, `macDisabled`, `passwordRules`, `lockTime`, `maxSession`) VALUES
	(1, 600, 0, 3, 0, 0, '^[0-9a-zA-Z!$#%@^&amp;amp;amp;amp;amp;amp;amp;*()~_+]{8,20}$', 1, 1);
/*!40000 ALTER TABLE `safe_policy` ENABLE KEYS */;


-- Dumping structure for table sslvpn.server
DROP TABLE IF EXISTS `server`;
CREATE TABLE IF NOT EXISTS `server` (
  `id` int(11) NOT NULL,
  `listen` varchar(50) DEFAULT NULL,
  `port` int(11) DEFAULT '1194',
  `protocol` varchar(11) DEFAULT 'udp',
  `server_net` varchar(30) DEFAULT '10.8.0.0',
  `server_mask` varchar(30) DEFAULT '255.255.255.0',
  `check_crl` int(11) DEFAULT '1',
  `traffic_server` int(11) DEFAULT '0',
  `client_to_client` int(11) DEFAULT '1',
  `duplicate_cn` int(11) DEFAULT '0',
  `keep_alive` int(11) DEFAULT '60',
  `keep_alive_interval` int(11) DEFAULT '10',
  `cipher` varchar(50) DEFAULT 'DES-EDE3-CBC',
  `comp_lzo` int(11) DEFAULT '1',
  `max_clients` int(11) DEFAULT '300',
  `log_append` int(11) DEFAULT '1',
  `log_flag` int(11) DEFAULT '1',
  `verb` int(11) DEFAULT '3',
  `mute` int(11) DEFAULT '5',
  `client_dns_type` int(11) DEFAULT '0',
  `client_first_dns` varchar(50) DEFAULT NULL,
  `client_second_dns` varchar(50) DEFAULT NULL,
  `default_domain_suffix` varchar(50) DEFAULT 'sslvpn',
  `use_connect_script` int(11) DEFAULT '1',
  `use_disconnect_script` int(11) DEFAULT '1',
  `use_learn_address_script` int(11) DEFAULT '0',
  `local` varchar(50) DEFAULT NULL,
  `server` varchar(50) DEFAULT NULL,
  `dynamic_net` varchar(100) DEFAULT NULL,
  `static_net` varchar(100) DEFAULT NULL,
  `group_default_net` varchar(250) DEFAULT NULL,
  `private_net` varchar(250) DEFAULT NULL,
  `allow_ping_server` int(11) DEFAULT NULL,
  `allow_private_net` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.server: ~1 rows (approximately)
/*!40000 ALTER TABLE `server` DISABLE KEYS */;
REPLACE INTO `server` (`id`, `listen`, `port`, `protocol`, `server_net`, `server_mask`, `check_crl`, `traffic_server`, `client_to_client`, `duplicate_cn`, `keep_alive`, `keep_alive_interval`, `cipher`, `comp_lzo`, `max_clients`, `log_append`, `log_flag`, `verb`, `mute`, `client_dns_type`, `client_first_dns`, `client_second_dns`, `default_domain_suffix`, `use_connect_script`, `use_disconnect_script`, `use_learn_address_script`, `local`, `server`, `dynamic_net`, `static_net`, `group_default_net`, `private_net`, `allow_ping_server`, `allow_private_net`) VALUES
	(1, '0.0.0.0', 1194, 'udp', '10.8.0.0', '255.255.255.0', 1, 0, 1, 0, 60, 10, 'DES-EDE3-CBC', 1, 300, 1, 1, 3, 5, 2, '', '', 'sslvpn.com', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `server` ENABLE KEYS */;


-- Dumping structure for table sslvpn.server_certificate
DROP TABLE IF EXISTS `server_certificate`;
CREATE TABLE IF NOT EXISTS `server_certificate` (
  `id` int(11) NOT NULL,
  `certificate` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.server_certificate: ~0 rows (approximately)
/*!40000 ALTER TABLE `server_certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_certificate` ENABLE KEYS */;


-- Dumping structure for table sslvpn.server_source_nets
DROP TABLE IF EXISTS `server_source_nets`;
CREATE TABLE IF NOT EXISTS `server_source_nets` (
  `source_net_id` int(11) NOT NULL,
  `server_id` int(11) NOT NULL,
  PRIMARY KEY (`source_net_id`,`server_id`),
  KEY `FK275C751ED95A9C2F` (`source_net_id`),
  KEY `FK275C751EA6E2CD4E` (`server_id`),
  CONSTRAINT `FK275C751EA6E2CD4E` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`),
  CONSTRAINT `FK275C751ED95A9C2F` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`),
  CONSTRAINT `FK_server_source_nets_server` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`),
  CONSTRAINT `FK_server_source_nets_source_nets` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.server_source_nets: ~0 rows (approximately)
/*!40000 ALTER TABLE `server_source_nets` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_source_nets` ENABLE KEYS */;


-- Dumping structure for table sslvpn.source_nets
DROP TABLE IF EXISTS `source_nets`;
CREATE TABLE IF NOT EXISTS `source_nets` (
  `id` int(11) NOT NULL,
  `net` varchar(30) DEFAULT NULL,
  `net_mask` varchar(30) DEFAULT NULL,
  `level` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.source_nets: ~0 rows (approximately)
/*!40000 ALTER TABLE `source_nets` DISABLE KEYS */;
/*!40000 ALTER TABLE `source_nets` ENABLE KEYS */;


-- Dumping structure for table sslvpn.static_ip
DROP TABLE IF EXISTS `static_ip`;
CREATE TABLE IF NOT EXISTS `static_ip` (
  `client_end` int(11) NOT NULL,
  `sever_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`client_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.static_ip: ~64 rows (approximately)
/*!40000 ALTER TABLE `static_ip` DISABLE KEYS */;
REPLACE INTO `static_ip` (`client_end`, `sever_end`) VALUES
	(1, 2),
	(5, 6),
	(9, 10),
	(13, 14),
	(17, 18),
	(21, 22),
	(25, 26),
	(29, 30),
	(33, 34),
	(37, 38),
	(41, 42),
	(45, 46),
	(49, 50),
	(53, 54),
	(57, 58),
	(61, 62),
	(65, 66),
	(69, 70),
	(73, 74),
	(77, 78),
	(81, 82),
	(85, 86),
	(89, 90),
	(93, 94),
	(97, 98),
	(101, 102),
	(105, 106),
	(109, 110),
	(113, 114),
	(117, 118),
	(121, 122),
	(125, 126),
	(129, 130),
	(133, 134),
	(137, 138),
	(141, 142),
	(145, 146),
	(149, 150),
	(153, 154),
	(157, 158),
	(161, 162),
	(165, 166),
	(169, 170),
	(173, 174),
	(177, 178),
	(181, 182),
	(185, 186),
	(189, 190),
	(193, 194),
	(197, 198),
	(201, 202),
	(205, 206),
	(209, 210),
	(213, 214),
	(217, 218),
	(221, 222),
	(225, 226),
	(229, 230),
	(233, 234),
	(237, 238),
	(241, 242),
	(245, 246),
	(249, 250),
	(253, 254);
/*!40000 ALTER TABLE `static_ip` ENABLE KEYS */;


-- Dumping structure for table sslvpn.terminal
DROP TABLE IF EXISTS `terminal`;
CREATE TABLE IF NOT EXISTS `terminal` (
  `id` int(11) NOT NULL,
  `terminal_name` varchar(20) DEFAULT NULL,
  `terminal_type` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `terminal_status` int(11) DEFAULT NULL,
  `terminal_desc` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `mac` varchar(50) DEFAULT NULL,
  `on_line` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB997927C434087DB` (`id`),
  CONSTRAINT `FKB997927C434087DB` FOREIGN KEY (`id`) REFERENCES `route_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.terminal: ~0 rows (approximately)
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;


-- Dumping structure for table sslvpn.trustterminals
DROP TABLE IF EXISTS `trustterminals`;
CREATE TABLE IF NOT EXISTS `trustterminals` (
  `id` bigint(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `mac` varchar(17) DEFAULT NULL,
  `startTime` varchar(8) DEFAULT '00:00:00',
  `endTime` varchar(8) DEFAULT '23:59:59',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.trustterminals: ~0 rows (approximately)
/*!40000 ALTER TABLE `trustterminals` DISABLE KEYS */;
/*!40000 ALTER TABLE `trustterminals` ENABLE KEYS */;


-- Dumping structure for table sslvpn.trust_certificate
DROP TABLE IF EXISTS `trust_certificate`;
CREATE TABLE IF NOT EXISTS `trust_certificate` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `file` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `notbefore` varchar(50) DEFAULT NULL,
  `notafter` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.trust_certificate: ~0 rows (approximately)
/*!40000 ALTER TABLE `trust_certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `trust_certificate` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn` varchar(100) NOT NULL DEFAULT '',
  `subject` varchar(200) NOT NULL DEFAULT '',
  `dynamic_ip` int(11) DEFAULT '1',
  `static_ip` varchar(30) DEFAULT '',
  `allow_all_client` int(11) DEFAULT '1',
  `serial_number` varchar(50) DEFAULT '',
  `enabled` int(11) DEFAULT '1',
  `real_address` varchar(30) DEFAULT '',
  `byte_received` bigint(20) DEFAULT '0',
  `byte_send` bigint(20) DEFAULT '0',
  `connected_since` datetime DEFAULT NULL,
  `virtual_address` varchar(50) DEFAULT '',
  `last_ref` datetime DEFAULT NULL,
  `net_id` varchar(30) DEFAULT '',
  `terminal_id` varchar(30) DEFAULT '',
  `description` varchar(30) DEFAULT '',
  `view_flag` int(11) DEFAULT '0',
  `gps_flag` int(11) DEFAULT '0',
  `level` tinyint(4) DEFAULT '0',
  `id_card` varchar(100) DEFAULT NULL,
  `deny_access` int(11) DEFAULT NULL,
  `allow_all_subnet` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `quota_cycle` int(11) DEFAULT NULL,
  `quota_bytes` bigint(20) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `revoked` int(11) DEFAULT NULL,
  `count_bytes_cycle` bigint(20) DEFAULT NULL,
  `max_bytes` bigint(20) DEFAULT NULL,
  `issueCa` varchar(200) DEFAULT NULL,
  `orgCode` varchar(30) DEFAULT NULL,
  `orgName` varchar(30) DEFAULT NULL,
  `employeeCode` varchar(30) DEFAULT NULL,
  `create_Date` datetime DEFAULT NULL,
  `end_Date` datetime DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `organization` varchar(30) DEFAULT NULL,
  `institutions` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `revoke_status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cn` (`cn`),
  UNIQUE KEY `serial_number` (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_gps
DROP TABLE IF EXISTS `user_gps`;
CREATE TABLE IF NOT EXISTS `user_gps` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `readDate` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF022CE36C9DE2D4E` (`user_id`),
  CONSTRAINT `FKF022CE36C9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_gps: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_gps` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_gps` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_group
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE IF NOT EXISTS `user_group` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `FK72A9010BC9DE2D4E` (`user_id`),
  KEY `FK72A9010BBD119723` (`group_id`),
  CONSTRAINT `FK72A9010BBD119723` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK72A9010BC9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_group: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_nats
DROP TABLE IF EXISTS `user_nats`;
CREATE TABLE IF NOT EXISTS `user_nats` (
  `user_id` int(11) NOT NULL,
  `nat_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`nat_id`),
  KEY `FK1439EF66C9DE2D4E` (`user_id`),
  KEY `FK1439EF66A7054026` (`nat_id`),
  CONSTRAINT `FK_user_nats_nat` FOREIGN KEY (`nat_id`) REFERENCES `nat` (`id`),
  CONSTRAINT `FK_user_nats_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_nats: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_nats` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_nats` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_oper_log
DROP TABLE IF EXISTS `user_oper_log`;
CREATE TABLE IF NOT EXISTS `user_oper_log` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `audit_module` varchar(255) DEFAULT NULL,
  `audit_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_oper_log: ~75 rows (approximately)
/*!40000 ALTER TABLE `user_oper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_oper_log` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_route_user
DROP TABLE IF EXISTS `user_route_user`;
CREATE TABLE IF NOT EXISTS `user_route_user` (
  `user_id` int(11) NOT NULL,
  `route_user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`route_user_id`),
  KEY `FK85FCA4F5C9DE2D4E` (`user_id`),
  KEY `FK85FCA4F5903768D9` (`route_user_id`),
  CONSTRAINT `FK85FCA4F5903768D9` FOREIGN KEY (`route_user_id`) REFERENCES `route_user` (`id`),
  CONSTRAINT `FK85FCA4F5C9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_route_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_route_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_route_user` ENABLE KEYS */;


-- Dumping structure for table sslvpn.user_source_nets
DROP TABLE IF EXISTS `user_source_nets`;
CREATE TABLE IF NOT EXISTS `user_source_nets` (
  `user_id` int(11) NOT NULL,
  `source_net_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`source_net_id`),
  KEY `FK9DF2A5A6D95A9C2F` (`source_net_id`),
  KEY `FK9DF2A5A6C9DE2D4E` (`user_id`),
  CONSTRAINT `FK9DF2A5A6C9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK9DF2A5A6D95A9C2F` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sslvpn.user_source_nets: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_source_nets` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_source_nets` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
