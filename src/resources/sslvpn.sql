# --------------------------------------------------------
# Host:                         192.168.1.210
# Server version:               5.5.41-0+wheezy1
# Server OS:                    debian-linux-gnu
# HeidiSQL version:             6.0.0.3811
# Date/time:                    2015-04-29 16:16:02
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for sslvpn
DROP DATABASE IF EXISTS `sslvpn`;
CREATE DATABASE IF NOT EXISTS `sslvpn` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sslvpn`;


# Dumping structure for table sslvpn.account
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

# Dumping data for table sslvpn.account: ~5 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`id`, `user_name`, `password`, `sex`, `phone`, `created_time`, `modified_time`, `modifiedPasswordTime`, `status`, `depart`, `title`, `name`, `email`, `start_ip`, `end_ip`, `start_hour`, `end_hour`, `description`, `remote_ip`, `mac`, `ip_type`) VALUES
	(1, 'admin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2010-07-04 13:52:36', '2014-05-22 17:16:38', NULL, '有效', '信息中心', '主任', '初始化管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.254.254', 9, 18, '这是一个默认的超级用户信息', '192.168.2.176', '5C-63-BF-1D-72-07', 1),
	(2, 'authadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-04-12 14:22:35', '2013-05-07 18:27:30', NULL, '有效', '信息中心', '主任', '授权管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 1, 22, '这是一个默认的授权用户信息', '', NULL, 1),
	(3, 'configadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-06-12 18:04:01', '2013-05-07 18:27:45', NULL, '有效', '信息中心', '主任', '配置管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 9, 21, '这是一个默认的配置用户信息', '', NULL, 1),
	(4, 'auditadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-07-03 10:19:57', '2014-08-26 13:01:36', NULL, '有效', '信息中心', '主任', '审计管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 7, 22, '这是一个默认的审计用户信息', NULL, NULL, 1),
	(5, 'test', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2014-04-01 13:33:21', '2015-04-21 09:43:30', NULL, '有效', '信息部', '主任', '测试', '11@hzih.net', '192.168.1.1', '192.168.1.255', 9, 18, '这是一个用户信息', NULL, '', 1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


# Dumping structure for table sslvpn.account_role
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

# Dumping data for table sslvpn.account_role: ~5 rows (approximately)
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
REPLACE INTO `account_role` (`account_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;


# Dumping structure for table sslvpn.backup
DROP TABLE IF EXISTS `backup`;
CREATE TABLE IF NOT EXISTS `backup` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `backup_account_id` bigint(20) DEFAULT '0',
  `backup_time` varchar(50) DEFAULT NULL,
  `backup_content` varchar(50) DEFAULT NULL,
  `backup_file` varchar(50) DEFAULT NULL,
  `backup_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_backup_account` (`backup_account_id`),
  CONSTRAINT `FK_backup_account` FOREIGN KEY (`backup_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.backup: ~1 rows (approximately)
/*!40000 ALTER TABLE `backup` DISABLE KEYS */;
REPLACE INTO `backup` (`id`, `backup_account_id`, `backup_time`, `backup_content`, `backup_file`, `backup_desc`) VALUES
	(2, 1, '2015年04月20日16时36分16秒', '无', 'backup_2015年04月20日16时36分16秒.tar.gz', '初始备份');
/*!40000 ALTER TABLE `backup` ENABLE KEYS */;


# Dumping structure for table sslvpn.groups
DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deny_access` tinyint(4) DEFAULT '0',
  `group_name` varchar(50) DEFAULT NULL,
  `group_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.groups: ~2 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
REPLACE INTO `groups` (`id`, `deny_access`, `group_name`, `group_desc`) VALUES
	(3, 1, 'hhh', 'hhh'),
	(4, 1, 'fdsf', 'fdafd');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;


# Dumping structure for table sslvpn.group_source_nets
DROP TABLE IF EXISTS `group_source_nets`;
CREATE TABLE IF NOT EXISTS `group_source_nets` (
  `source_net_id` int(11) NOT NULL DEFAULT '0',
  `group_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`source_net_id`,`group_id`),
  KEY `FK46FE425A794BE12D` (`source_net_id`),
  KEY `source_net_id` (`source_net_id`),
  KEY `group_id` (`group_id`),
  KEY `FK46FE425AD95A9C2F` (`source_net_id`),
  KEY `FK46FE425ABD119723` (`group_id`),
  CONSTRAINT `FK46FE425A794BE12D` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`),
  CONSTRAINT `FK46FE425ABD119723` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK46FE425AD95A9C2F` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`),
  CONSTRAINT `FK_group_source_nets_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_group_source_nets_source_nets` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.group_source_nets: ~2 rows (approximately)
/*!40000 ALTER TABLE `group_source_nets` DISABLE KEYS */;
REPLACE INTO `group_source_nets` (`source_net_id`, `group_id`) VALUES
	(6, 3),
	(7, 3);
/*!40000 ALTER TABLE `group_source_nets` ENABLE KEYS */;


# Dumping structure for table sslvpn.log
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
  `action` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.log: ~8 rows (approximately)
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
REPLACE INTO `log` (`id`, `cn`, `serial_number`, `subject_dn`, `start_time`, `end_time`, `trusted_ip`, `trusted_port`, `protocol`, `remote_ip`, `remote_netmask`, `bytes_received`, `bytes_sent`, `status`, `action`, `description`) VALUES
	(1, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:16:27', '2015-04-29 15:17:41', '172.16.2.8', 2002, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8452, 0, NULL, NULL),
	(2, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:17:41', '2015-04-29 15:19:10', '172.16.2.8', 2047, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8507, 0, NULL, NULL),
	(3, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:20:48', '2015-04-29 15:21:26', '172.16.2.8', 2102, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8232, 0, NULL, NULL),
	(4, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:22:53', '2015-04-29 15:23:20', '172.16.2.8', 2151, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8177, 0, NULL, NULL),
	(5, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:23:25', '2015-04-29 15:24:35', '172.16.2.8', 2192, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8397, 0, NULL, NULL),
	(6, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:24:35', '2015-04-29 15:25:40', '172.16.2.8', 2237, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8397, 0, NULL, NULL),
	(7, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:25:40', '2015-04-29 15:27:44', '172.16.2.8', 2263, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8727, 0, NULL, NULL),
	(8, '再见', '312206857822436768231914207601381547868', 'C=CN, ST=新疆维吾尔自治区, L=克孜勒苏柯尔克孜自治州, O=创谐你好, OU=智能硬件再见, emailAddress=mhpmii@qq.com, CN=再见', '2015-04-29 15:28:34', '2015-04-29 15:30:37', '172.16.2.8', 2342, 'tcp-server', '10.8.0.6', '255.255.255.0', 6323, 8672, 0, NULL, NULL);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


# Dumping structure for table sslvpn.permission
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

# Dumping data for table sslvpn.permission: ~49 rows (approximately)
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
	(121, 'SECOND_PTSM', '平台说明', NULL, 120, 1),
	(122, 'SECOND_PTGL', '平台管理', NULL, 120, 2),
	(123, 'SECOND_ZSGL', '证书管理', NULL, 120, 3),
	(124, 'SECOND_FWGL', '服务管理', NULL, 120, 4),
	(125, 'SECOND_BFHF', '备份恢复', NULL, 120, 5),
	(126, 'SECOND_BJPZ', '报警配置', NULL, 120, 6),
	(130, 'TOP_SJGL', '审计管理', NULL, 0, 0),
	(131, 'SECOND_GLRZ', '管理日志', NULL, 130, 1),
	(132, 'SECOND_RZXZ', '日志下载', NULL, 130, 2),
	(140, 'TOP_FWZT', '服务状态', NULL, 0, 0),
	(141, 'SECOND_FWZT', '服务状态', NULL, 140, 1),
	(142, 'SECOND_ZXYH', '在线用户', NULL, 140, 2),
	(143, 'SECOND_YHRZ', '用户日志', NULL, 140, 3),
	(150, 'TOP_FWPZ', '服务配置', NULL, 0, 0),
	(151, 'SECOND_WLPZ', '网络配置', NULL, 150, 1),
	(152, 'SECOND_JBPZ', '基本配置', NULL, 150, 2),
	(153, 'SECOND_GJPZ', '高级配置', NULL, 150, 3),
	(154, 'SECOND_ZSPZ', '证书配置', NULL, 150, 4),
	(155, 'SECOND_ZWPZ', '子网配置', NULL, 150, 5),
	(156, 'SECOND_RULE', '规则配置', NULL, 150, 6),
	(157, 'SECOND_SJBJ', '数据标记', NULL, 150, 7),
	(158, 'SECOND_SJRB', '双机热备', NULL, 150, 8),
	(160, 'TOP_YHGL', '用户管理', NULL, 0, 0),
	(161, 'SECOND_YHQX', '用户权限', NULL, 160, 1),
	(162, 'SECOND_YHZQX', '用户组权限', NULL, 160, 2),
	(163, 'SECOND_YHJL', '用户记录', NULL, 160, 3),
	(170, 'TOP_XTPZ', '系统配置', NULL, 0, 0),
	(171, 'SECOND_LDAP', 'LDAP配置', NULL, 170, 1),
	(172, 'SECOND_RZZJ', '日志服务器', NULL, 170, 2),
	(173, 'SECOND_JCPZ', '监测服务器', NULL, 170, 3),
	(174, 'SECOND_CYPZ', '客户端策略', NULL, 170, 4),
	(175, 'SECOND_FWKZ', '访问控制', NULL, 170, 5),
	(180, 'TOP_DXGL', '吊销管理', NULL, 0, 0),
	(181, 'SECOND_DXLB', '吊销列表', NULL, 180, 1),
	(190, 'TOP_JKGL', '监控管理', NULL, 0, 0),
	(191, 'SECOND_ZJJK', '主机监控', NULL, 190, 1),
	(200, 'TOP_BBSJ', '版本升级', NULL, 0, 0),
	(201, 'SECOND_KFBB', '客户端', NULL, 200, 1),
	(202, 'SECOND_FWBB', '服务端', NULL, 200, 2);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


# Dumping structure for table sslvpn.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.role: ~6 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`, `description`, `createdTime`, `modifiedTime`) VALUES
	(1, '初始化管理员', '初始化管理员', '2010-07-04 15:07:08', '2015-04-22 13:27:57'),
	(2, '授权管理员', '授权管理员', '2012-07-03 10:06:20', '2012-07-03 10:06:20'),
	(3, '配置管理员', '配置管理员', '2012-03-14 12:33:05', '2012-03-14 12:33:05'),
	(4, '审计管理员', '审计管理员', '2012-06-12 18:37:24', '2013-09-23 10:01:58'),
	(5, '调试管理员', '调试管理员', '2014-04-01 15:51:14', '2015-04-21 09:44:51'),
	(6, '安全管理员', '安全管理员', '2015-04-21 09:44:32', '2015-04-21 09:44:39');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


# Dumping structure for table sslvpn.role_permission
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

# Dumping data for table sslvpn.role_permission: ~63 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
REPLACE INTO `role_permission` (`permission_id`, `role_id`) VALUES
	(100, 1),
	(101, 1),
	(102, 1),
	(103, 1),
	(110, 1),
	(111, 1),
	(112, 1),
	(113, 1),
	(114, 1),
	(120, 1),
	(121, 1),
	(122, 1),
	(123, 1),
	(124, 1),
	(125, 1),
	(126, 1),
	(130, 1),
	(131, 1),
	(132, 1),
	(140, 1),
	(141, 1),
	(142, 1),
	(143, 1),
	(150, 1),
	(151, 1),
	(152, 1),
	(153, 1),
	(154, 1),
	(155, 1),
	(156, 1),
	(157, 1),
	(158, 1),
	(160, 1),
	(161, 1),
	(162, 1),
	(163, 1),
	(170, 1),
	(171, 1),
	(172, 1),
	(173, 1),
	(174, 1),
	(175, 1),
	(180, 1),
	(181, 1),
	(190, 1),
	(191, 1),
	(200, 1),
	(201, 1),
	(202, 1),
	(100, 5),
	(101, 5),
	(102, 5),
	(103, 5),
	(100, 6),
	(101, 6),
	(102, 6),
	(103, 6),
	(110, 6),
	(111, 6),
	(112, 6),
	(113, 6),
	(114, 6),
	(120, 6);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;


# Dumping structure for table sslvpn.route
DROP TABLE IF EXISTS `route`;
CREATE TABLE IF NOT EXISTS `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route` varchar(50) NOT NULL,
  `net_mask` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.route: ~0 rows (approximately)
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;


# Dumping structure for table sslvpn.route_user
DROP TABLE IF EXISTS `route_user`;
CREATE TABLE IF NOT EXISTS `route_user` (
  `id` int(10) NOT NULL DEFAULT '0',
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

# Dumping data for table sslvpn.route_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `route_user` DISABLE KEYS */;
REPLACE INTO `route_user` (`id`, `user_name`, `user_idcard`, `user_province`, `user_city`, `user_organization`, `user_institution`, `user_phone`, `user_address`, `user_email`) VALUES
	(1, '陈胜', '340811199602255118', '浙江省', '杭州', '杭州创谐有限公司', '财务部', '18658850061', '5901009', '1261089927@qq.com');
/*!40000 ALTER TABLE `route_user` ENABLE KEYS */;


# Dumping structure for table sslvpn.safe_policy
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全策略表';

# Dumping data for table sslvpn.safe_policy: ~1 rows (approximately)
/*!40000 ALTER TABLE `safe_policy` DISABLE KEYS */;
REPLACE INTO `safe_policy` (`id`, `timeout`, `passwordLength`, `errorLimit`, `remoteDisabled`, `macDisabled`, `passwordRules`, `lockTime`) VALUES
	(1, 600, 0, 3, 0, 0, '^[0-9a-zA-Z!$#%@^&amp;amp;amp;amp;amp;amp;amp;*()~_+]{8,20}$', 1);
/*!40000 ALTER TABLE `safe_policy` ENABLE KEYS */;


# Dumping structure for table sslvpn.server
DROP TABLE IF EXISTS `server`;
CREATE TABLE IF NOT EXISTS `server` (
  `id` int(11) NOT NULL DEFAULT '1',
  `listen` varchar(50) NOT NULL,
  `port` int(11) NOT NULL,
  `protocol` varchar(11) NOT NULL,
  `server_net` varchar(30) NOT NULL DEFAULT '10.8.0.0',
  `server_mask` varchar(30) NOT NULL DEFAULT '255.255.255.0',
  `check_crl` tinyint(4) NOT NULL DEFAULT '0',
  `traffic_server` tinyint(4) NOT NULL DEFAULT '0',
  `client_to_client` tinyint(4) NOT NULL DEFAULT '0',
  `duplicate_cn` tinyint(4) NOT NULL DEFAULT '0',
  `keep_alive` int(11) NOT NULL DEFAULT '60',
  `keep_alive_interval` int(11) NOT NULL DEFAULT '10',
  `cipher` varchar(50) NOT NULL DEFAULT 'DES-EDE3-CBC',
  `comp_lzo` tinyint(4) NOT NULL DEFAULT '1',
  `max_clients` int(11) NOT NULL DEFAULT '300',
  `log_append` tinyint(4) NOT NULL DEFAULT '0',
  `log_flag` tinyint(4) NOT NULL DEFAULT '0',
  `verb` int(11) NOT NULL DEFAULT '0',
  `mute` int(11) NOT NULL DEFAULT '5',
  `client_dns_type` tinyint(4) NOT NULL DEFAULT '1',
  `client_first_dns` varchar(50) DEFAULT NULL,
  `client_second_dns` varchar(50) DEFAULT NULL,
  `default_domain_suffix` varchar(50) DEFAULT 'sslvpn.com',
  `use_connect_script` tinyint(4) NOT NULL DEFAULT '1',
  `use_disconnect_script` tinyint(4) NOT NULL DEFAULT '1',
  `use_learn_address_script` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.server: ~1 rows (approximately)
/*!40000 ALTER TABLE `server` DISABLE KEYS */;
REPLACE INTO `server` (`id`, `listen`, `port`, `protocol`, `server_net`, `server_mask`, `check_crl`, `traffic_server`, `client_to_client`, `duplicate_cn`, `keep_alive`, `keep_alive_interval`, `cipher`, `comp_lzo`, `max_clients`, `log_append`, `log_flag`, `verb`, `mute`, `client_dns_type`, `client_first_dns`, `client_second_dns`, `default_domain_suffix`, `use_connect_script`, `use_disconnect_script`, `use_learn_address_script`) VALUES
	(1, '0.0.0.0', 1194, 'tcp', '10.8.0.0', '255.255.255.0', 0, 0, 1, 0, 60, 10, 'DES-EDE3-CBC', 1, 300, 1, 0, 0, 5, 2, '8.8.8.8', '114.114.114.114', 'sslvpn.com', 1, 1, 0);
/*!40000 ALTER TABLE `server` ENABLE KEYS */;


# Dumping structure for table sslvpn.server_certificate
DROP TABLE IF EXISTS `server_certificate`;
CREATE TABLE IF NOT EXISTS `server_certificate` (
  `id` int(10) NOT NULL,
  `certificate` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.server_certificate: ~1 rows (approximately)
/*!40000 ALTER TABLE `server_certificate` DISABLE KEYS */;
REPLACE INTO `server_certificate` (`id`, `certificate`, `name`, `status`, `pwd`) VALUES
	(2, '/usr/app/sslvpn/pki/server.pfx', 'server.pfx', 1, '111111');
/*!40000 ALTER TABLE `server_certificate` ENABLE KEYS */;


# Dumping structure for table sslvpn.server_source_nets
DROP TABLE IF EXISTS `server_source_nets`;
CREATE TABLE IF NOT EXISTS `server_source_nets` (
  `source_net_id` int(11) NOT NULL DEFAULT '0',
  `server_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`source_net_id`,`server_id`),
  KEY `source_net_id` (`source_net_id`),
  KEY `server_id` (`server_id`),
  KEY `FK275C751ED95A9C2F` (`source_net_id`),
  KEY `FK275C751EA6E2CD4E` (`server_id`),
  CONSTRAINT `FK275C751EA6E2CD4E` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`),
  CONSTRAINT `FK275C751ED95A9C2F` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`),
  CONSTRAINT `FK_server_source_nets_server` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`),
  CONSTRAINT `FK_server_source_nets_source_nets` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.server_source_nets: ~3 rows (approximately)
/*!40000 ALTER TABLE `server_source_nets` DISABLE KEYS */;
REPLACE INTO `server_source_nets` (`source_net_id`, `server_id`) VALUES
	(1, 1),
	(5, 1),
	(6, 1);
/*!40000 ALTER TABLE `server_source_nets` ENABLE KEYS */;


# Dumping structure for table sslvpn.source_nets
DROP TABLE IF EXISTS `source_nets`;
CREATE TABLE IF NOT EXISTS `source_nets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `net` varchar(30) DEFAULT NULL,
  `net_mask` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.source_nets: ~4 rows (approximately)
/*!40000 ALTER TABLE `source_nets` DISABLE KEYS */;
REPLACE INTO `source_nets` (`id`, `net`, `net_mask`) VALUES
	(1, '192.168.4.0', '255.255.255.0'),
	(5, '192.168.7.0', '255.255.255.0'),
	(6, '192.168.1.0', '255.255.255.0'),
	(7, '192.168.2.0', '255.255.255.0');
/*!40000 ALTER TABLE `source_nets` ENABLE KEYS */;


# Dumping structure for table sslvpn.static_ip
DROP TABLE IF EXISTS `static_ip`;
CREATE TABLE IF NOT EXISTS `static_ip` (
  `client_end` int(11) NOT NULL DEFAULT '0',
  `sever_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`client_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.static_ip: ~64 rows (approximately)
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


# Dumping structure for table sslvpn.terminal
DROP TABLE IF EXISTS `terminal`;
CREATE TABLE IF NOT EXISTS `terminal` (
  `id` int(10) NOT NULL DEFAULT '0',
  `terminal_name` varchar(20) DEFAULT NULL,
  `terminal_type` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `terminal_status` int(10) DEFAULT NULL,
  `terminal_desc` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `mac` varchar(50) DEFAULT NULL,
  `on_line` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB997927C434087DB` (`id`),
  CONSTRAINT `FKB997927C434087DB` FOREIGN KEY (`id`) REFERENCES `route_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.terminal: ~1 rows (approximately)
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
REPLACE INTO `terminal` (`id`, `terminal_name`, `terminal_type`, `user_name`, `terminal_status`, `terminal_desc`, `ip`, `mac`, `on_line`) VALUES
	(1, 'vivo Xplay3S', '笔记本', '陈胜', 0, '...........', '192.168.2.1', '80', 1);
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;


# Dumping structure for table sslvpn.trustterminals
DROP TABLE IF EXISTS `trustterminals`;
CREATE TABLE IF NOT EXISTS `trustterminals` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `ip` varchar(15) DEFAULT NULL,
  `mac` varchar(17) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.trustterminals: ~1 rows (approximately)
/*!40000 ALTER TABLE `trustterminals` DISABLE KEYS */;
REPLACE INTO `trustterminals` (`id`, `ip`, `mac`, `status`) VALUES
	(1, '192.168.1.1', 'aa:bb:cc:dd:ee:ff', 0);
/*!40000 ALTER TABLE `trustterminals` ENABLE KEYS */;


# Dumping structure for table sslvpn.trust_certificate
DROP TABLE IF EXISTS `trust_certificate`;
CREATE TABLE IF NOT EXISTS `trust_certificate` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `file` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` tinyint(11) NOT NULL DEFAULT '0',
  `subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `notbefore` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `notafter` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.trust_certificate: ~1 rows (approximately)
/*!40000 ALTER TABLE `trust_certificate` DISABLE KEYS */;
REPLACE INTO `trust_certificate` (`id`, `name`, `file`, `status`, `subject`, `notbefore`, `notafter`) VALUES
	(2, 'ca.crt', '/usr/app/sslvpn/pki/ca.crt', 1, 'CN=Trust, L=杭州市, ST=浙江省, C=CN', '2025年04日04日13时50分34秒', '2015年04日07日13时50分34秒');
/*!40000 ALTER TABLE `trust_certificate` ENABLE KEYS */;


# Dumping structure for table sslvpn.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn` varchar(100) DEFAULT NULL,
  `id_card` varchar(100) DEFAULT NULL,
  `deny_access` tinyint(4) DEFAULT '1',
  `dynamic_ip` tinyint(4) DEFAULT '1',
  `static_ip` varchar(30) DEFAULT NULL,
  `allow_all_subnet` tinyint(4) DEFAULT '0',
  `allow_all_client` tinyint(4) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `quota_cycle` int(11) DEFAULT '60',
  `quota_bytes` bigint(20) DEFAULT '30000',
  `active` tinyint(4) DEFAULT '1',
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `serial_number` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `revoked` tinyint(4) DEFAULT '0',
  `enabled` tinyint(4) DEFAULT '1',
  `real_address` varchar(30) DEFAULT NULL,
  `byte_received` bigint(20) DEFAULT '0',
  `byte_send` bigint(20) DEFAULT '0',
  `connected_since` timestamp NULL DEFAULT NULL,
  `virtual_address` varchar(50) DEFAULT NULL,
  `last_ref` timestamp NULL DEFAULT NULL,
  `count_bytes_cycle` bigint(11) DEFAULT NULL,
  `max_bytes` bigint(20) DEFAULT NULL,
  `net_id` varchar(30) DEFAULT NULL,
  `terminal_id` varchar(30) DEFAULT NULL,
  `issueCa` varchar(30) DEFAULT NULL,
  `orgCode` varchar(30) DEFAULT NULL,
  `orgName` varchar(30) DEFAULT NULL,
  `employeeCode` varchar(30) DEFAULT NULL,
  `create_Date` timestamp NULL DEFAULT NULL,
  `end_Date` timestamp NULL DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `organization` varchar(30) DEFAULT NULL,
  `institutions` varchar(30) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `revoke_status` varchar(30) DEFAULT NULL,
  `view_flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.user: ~57 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `cn`, `id_card`, `deny_access`, `dynamic_ip`, `static_ip`, `allow_all_subnet`, `allow_all_client`, `create_time`, `quota_cycle`, `quota_bytes`, `active`, `email`, `phone`, `address`, `serial_number`, `type`, `revoked`, `enabled`, `real_address`, `byte_received`, `byte_send`, `connected_since`, `virtual_address`, `last_ref`, `count_bytes_cycle`, `max_bytes`, `net_id`, `terminal_id`, `issueCa`, `orgCode`, `orgName`, `employeeCode`, `create_Date`, `end_Date`, `province`, `city`, `organization`, `institutions`, `description`, `status`, `revoke_status`, `view_flag`) VALUES
	(3, 'dtest1', '330781111111111111', 0, 0, '192.168.1.5', 1, 1, NULL, 60, 300000, 1, '11@qq.com', '88888888', '11', '35890C801D774BCA82B2AB9D8896888E', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, '', '', 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', 'company', '测试', 'USBKey', '0', NULL, 0),
	(4, 'dphone2', '330781222222222222', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '88888888', '11', '34675BC8B25B45C5AA70BCBC8DA4A0B4', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, '', '', 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '软件公司', '测试', 'TFCard', '0', NULL, 0),
	(5, '张三', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '305C66F3DD42476189ADA7DEB0C31058', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(6, 'xuxu', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'sdf@qq.com', '11111111', 'ad', 'E3D5C1E4016843578E6D32DEC0A05D6F', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, 'null', 'null', 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'han', 'zdf', 'd', NULL, '0', NULL, 0),
	(7, '张三dsfasfasgg', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '9CD5E082D3C740E693AEAA44E458E86B', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(8, '张三123', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '4D5C3FBF3A9D46F18DF1C885BFAB3CA7', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', 'TFCard', '0', NULL, 0),
	(9, 'dJuly1', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq', '88888888', 'qq', '9D33A36D28DE4C86B3A5BC49F45FF5FD', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '北京市', '杭州', '创谐', '测试', 'USBKey', '0', NULL, 0),
	(10, 'dJulypc2', '330781198922222222', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq', '88888888', '11', '9DE7789275424D659CDE5480D975B064', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '北京市', 'hangzhou', 'chuangxie', 'ceshi', 'USBKey', '0', NULL, 0),
	(11, '张三1009', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '31B15301B5934E9B98020546C080824D', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', 'TFCard', '0', NULL, 0),
	(12, '张1', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'D8B01942C1DA44D8BFBE155F9CA673B8', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(13, '张三wssfas', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'AFCDEAC5FB984CF4812BD554DCE93F9B', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(14, '张三wdzcx', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'A1688244C82443FB1D3B7A63389632B', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', 'TFCard', '0', NULL, 0),
	(15, '张三dscvdf', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '4F99832E8F794FFE81F77F47B7B6ECFD', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(16, '张三dzxvasf', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'FC03EC225E4B4E73B8097D05D62D64A8', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(17, '张三wszwaffsdw', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '81CDF8B1794DAAB451D4F6E889C5B3', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(18, '张三wdzggw', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '3103FD3E75CA4F5AB7F04D0F3C957578', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(19, '张三d', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'CBBC411F645946329BDBF60D4EA779F8', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(20, '张三w', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'E2215F3747E84B8F9D7D1331E2A48283', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(21, '张三dsf', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '4751AF2FDA9843CA92C881DA89ECC745', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(22, '黄很明', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '786F197CB5A44058B28A6D312A03FCA0', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(23, 'hhmtest', '430725199002093279', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'ffdf@Q.com', '6556119', 'fdsf', 'DD476CC78D5342F98241E14973C8B2B2', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '河北省', 'fdsf', 'fsf', 'fdsf', NULL, '0', NULL, 0),
	(24, '张131', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '49C9B717F7984DDFB31042ED777B1C8F', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(25, 'qwe', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'qae@qq.com', '13444444444', 'ad', '871DCCA9F5C845238DE90A87B2DA0866', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, 'wqa', 'awd', 'qaw', 'ads', NULL, '0', NULL, 0),
	(26, 'qwe123', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'qae@qq.com', '13444444444', 'ad', 'D1EE40552D104082853A6AFE8A87FEE0', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, 'wqa', 'awd', 'qaw', 'ads', NULL, '0', NULL, 0),
	(27, '张10171', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '1673DB07E8D54C788E4AA1C8C36FA67C', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(28, '张10191', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '1A04351104348F8973533FA35E039D1', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(29, '张1911', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', '5D77F6DE6F0F431A80768A2A692A9F9E', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(30, '张10192', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'E2F4AA84D8A443E6A55E592EA580E448', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '21311314141', '财务部', NULL, '0', NULL, 0),
	(31, 'hhmtest2', '430725199002093279', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'fdsf@QQ.com', '6556119', 'fsf', '98653682DA244DB4B255C164E40CA020', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '山西省', 'fdsf', 'fdsf', 'fdsf', NULL, '0', NULL, 0),
	(32, '张三e', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '18658850061', '5901009', 'F942623F51144186A57A604BC045AF7C', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐有限公司', '财务部', NULL, '0', NULL, 0),
	(33, 'Test', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'fdsfa@QQ.com', '65561119', 'fdsf', 'A49C8F3845B744408219CA3438342241', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '山西省', 'fsf', 'fdsf', 'fds', NULL, '0', NULL, 0),
	(34, 'gfggg', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'fdsfd@qq.com', '6556119', 'vgfdgfsdgf', '2257DA6637F74B13B776E24A7504A700', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '河北省', 'fdffd', 'fdsf', 'fdfd', NULL, '0', NULL, 0),
	(35, 'aserg', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '88888888', 'asdzfv', '689892F7936A4E8896A541095083E20B', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'hangzhou', 'chuangxie', 'ceshi', NULL, '0', NULL, 0),
	(36, 'sxfgbxd', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@QQ.COM', '88888888', '112', '59DF2F00B8C44E9A3390DBA1D5FD0E8', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'hangzhou', 'chuangxie', 'ceshi', NULL, '0', NULL, 0),
	(37, 'test2', '222222222222222222', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'gjgk@ss.com', '6556119', 'fgj', 'C62A29CE16C5460FBF362C2BA2C56D39', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '四川省', 'jgg', 'hfjg', 'fjfj', NULL, '0', NULL, 0),
	(38, 'djuly2', '330781254647895412', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '12@qq.com', '88888888', '共同的音乐台', '567A579690154586A31DA7330AB6D0F4', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '速度', '色调', 'USBKey', '0', NULL, 0),
	(39, 'djuly3', '330781198966666665', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'qw', '11052130666041459CBBADF840CE44A0', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'hangzhou', 'qwef', 'wef', 'TFCard', '0', NULL, 0),
	(40, '王五', '123456789012345678', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '138@139.com', '13809900000', '首体南路1号', '24F6980D6322467984FD64D627F37813', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '北京市', '北京', '一所', '通信事业部', NULL, '0', NULL, 0),
	(41, 'DVSZ', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '122@qq.com', '1111111', '12e', 'CD02D34937A64518B94F8E295899B45C', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'awe', 'afe', 'afr', NULL, '0', NULL, 0),
	(42, 'yf', '111222333444555666', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'fdsf@QQ.com', '6556119', 'fdfs', '32F8B944CB4547E089D14D6D63C86772', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '河北省', 'fds', 'fdsf', 'fdsf', NULL, '0', NULL, 0),
	(43, '陈胜', '340811199602255112', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089928@qq.com', '13735826135', '余杭', '9E88A182060C449A9E668E209A5C4FD7', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '杭州创谐', '有关部门', NULL, '0', NULL, 0),
	(44, 'srt', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'w4@q.com', '22222222', 'w45g', 'C1F76AF0E735462F9645BC8AC416BC92', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'wsg5', 'w4g5', 'w54t', 'TFCard', '0', NULL, 0),
	(45, 'wserg', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@Qq.com', '11111111', 'wr', '89AE68980FC84AD280E8BEB24069260A', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '黑龙江省', 'aer', 'ae', 'er', NULL, '0', NULL, 0),
	(46, 'serg', '111111111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'ager', '43A3521DB7964D27864DD74F16F76EBB', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '吉林省', 'wrsw', 'serg', 'serg', NULL, '0', NULL, 0),
	(47, 'dtest2', '330754123687411111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'aers', '1B5B0B20A2104AF695D817B04E74B364', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'hangzhou', 'azd', 'zd', 'TFCard', '0', NULL, 0),
	(48, 'daaa', '330788888888888888', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '23232323', '23', 'F44E20A4E7BF4188A89B6151C4D29B2A', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '湖南省', 'asfv', 'as', 'aerv', NULL, '0', NULL, 0),
	(49, '陈圣', '340811199602255118', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '13735826135', '背景', '800BA863B9F44B52AD3014E0719ACC7B', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '河北省', '西安', '创协', '财务', NULL, '0', NULL, 0),
	(50, '李天绝', '340811199602255117', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '13735826135', '北京吃化解', 'B840BF58AA354ACB9EC3A69DBD1BD772', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '创协', '财务', NULL, '0', NULL, 0),
	(51, '李珏', '321512513253252352', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1261089927@qq.com', '13645251673', '北京', 'B132A34BB9A4E6782150B6CDAF3B866', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', '杭州', '创协', '财务', NULL, '0', NULL, 0),
	(52, 'qa34', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'aerg@qq.com', '11111111', 'aer', '4DB06B45B4844D48A658BD8DBEC55CF7', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'aerfg', 'aerg', 'aserg', NULL, '0', NULL, 0),
	(53, 'adfb', '330781111111111112', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '1111112', 'awefd', 'F176D4FD6B540AEA72B98A7051BBBE3', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '浙江省', 'aerf', 'aefr', 'aer', NULL, '0', NULL, 0),
	(54, 'zgre', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'srth', '8AEC495F39D94CF48A50061B288B059E', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '山东省', 'aerg', 'serg', 'sergs', NULL, '0', NULL, 0),
	(55, 'zgre1', '330781111111111112', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'srth', '545F18F3B4A140AAA89F0F0C36E94D43', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '山东省', 'aerg', 'serg', 'sergs', NULL, '0', NULL, 0),
	(56, 'dbeijing1', '330781112121211111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, 'sgr@qq.com', '21111111', 'ser', 'AB4963ABF36B442CA72820E8AA8A1EB2', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '北京市', 'ase', 'srt', 'strg', 'USBKey', '0', NULL, 0),
	(57, 'WAEF', '222222222222222222', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'awe', '2046632EF459E93DF3E06073820DC', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '湖南省', 'q3r', 'aefrf', 'es', NULL, '0', NULL, 0),
	(58, 'fgj', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', '1258', '5F826183C80145ADA66D4FB00DF8377C', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '山东省', '好咯', '啦', '出来', NULL, '0', NULL, 0),
	(59, 'sfserf', '330781111111111111', 0, 1, NULL, 1, 0, NULL, 60, 300000, 1, '1@qq.com', '11111111', 'we', 'BF7AAE3A97444EE2BA691EA280E8612C', NULL, 0, 1, NULL, 0, 0, NULL, NULL, NULL, 0, 0, NULL, NULL, 'cn=develop,dc=pkica', NULL, NULL, NULL, NULL, NULL, '湖南省', 'fae', 'a', 'af', NULL, '0', NULL, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


# Dumping structure for table sslvpn.user_gps
DROP TABLE IF EXISTS `user_gps`;
CREATE TABLE IF NOT EXISTS `user_gps` (
  `id` int(10) NOT NULL DEFAULT '0',
  `user_id` int(10) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `readDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF022CE36C9DE2D4E` (`user_id`),
  CONSTRAINT `FKF022CE36C9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.user_gps: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_gps` DISABLE KEYS */;
REPLACE INTO `user_gps` (`id`, `user_id`, `longitude`, `latitude`, `createDate`, `readDate`) VALUES
	(1, 5, '13.2351525', '12.124214', '2014-12-19 18:33:13', NULL),
	(2, 5, '120.167735', '30.181297', '2014-12-22 10:33:37', NULL);
/*!40000 ALTER TABLE `user_gps` ENABLE KEYS */;


# Dumping structure for table sslvpn.user_group
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE IF NOT EXISTS `user_group` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `group_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `FK72A9010B499D7EFA` (`user_id`),
  KEY `FK72A9010B4A432DCF` (`group_id`),
  KEY `FK72A9010BC9DE2D4E` (`user_id`),
  KEY `FK72A9010BBD119723` (`group_id`),
  CONSTRAINT `FK72A9010BBD119723` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK72A9010BC9DE2D4E` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.user_group: ~51 rows (approximately)
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
REPLACE INTO `user_group` (`user_id`, `group_id`) VALUES
	(3, 3),
	(4, 3),
	(5, 3),
	(6, 3),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 3),
	(11, 3),
	(12, 3),
	(13, 3),
	(14, 3),
	(15, 3),
	(16, 3),
	(17, 3),
	(18, 3),
	(19, 3),
	(20, 3),
	(21, 3),
	(22, 3),
	(23, 3),
	(24, 3),
	(25, 3),
	(26, 3),
	(27, 3),
	(28, 3),
	(29, 3),
	(30, 3),
	(31, 3),
	(32, 3),
	(33, 3),
	(34, 3),
	(35, 3),
	(36, 3),
	(37, 3),
	(38, 3),
	(39, 3),
	(40, 3),
	(41, 3),
	(42, 3),
	(43, 3),
	(44, 3),
	(45, 3),
	(46, 3),
	(47, 3),
	(48, 3),
	(49, 3),
	(50, 3),
	(51, 3),
	(52, 3),
	(53, 3);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


# Dumping structure for table sslvpn.user_oper_log
DROP TABLE IF EXISTS `user_oper_log`;
CREATE TABLE IF NOT EXISTS `user_oper_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '审计时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志级别',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `audit_module` varchar(255) DEFAULT NULL COMMENT '审计模块',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1186 DEFAULT CHARSET=utf8 COMMENT='用户操作审计表';

# Dumping data for table sslvpn.user_oper_log: ~162 rows (approximately)
/*!40000 ALTER TABLE `user_oper_log` DISABLE KEYS */;
REPLACE INTO `user_oper_log` (`Id`, `log_time`, `level`, `username`, `audit_module`, `audit_info`) VALUES
	(1024, '2015-04-21 16:09:25', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(1025, '2015-04-21 16:25:34', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1026, '2015-04-21 16:41:35', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1027, '2015-04-21 18:24:42', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1028, '2015-04-21 18:25:42', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1029, '2015-04-21 18:27:50', 'INFO', 'admin', '访问控制', '保存访问控制信息成功'),
	(1030, '2015-04-21 18:32:21', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1031, '2015-04-21 18:32:43', 'INFO', 'admin', '系统备份', '添加信任终端成功'),
	(1032, '2015-04-21 18:32:53', 'INFO', 'admin', '访问控制', '保存访问控制信息成功'),
	(1033, '2015-04-21 18:33:14', 'INFO', 'admin', '日志服务器', '添加日志服务器成功,服务器主机:192.168.1.1,服务器端口:8888'),
	(1034, '2015-04-21 18:33:18', 'INFO', 'admin', '日志服务器', '删除日志服务器成功,服务器主机:192.168.1.1,服务器端口:8888'),
	(1035, '2015-04-21 18:33:22', 'INFO', 'admin', 'LDAP配置', 'LDAP配置保存成功'),
	(1036, '2015-04-21 18:33:34', 'INFO', 'admin', 'LDAP配置', 'LDAP配置保存成功'),
	(1037, '2015-04-21 18:34:51', 'INFO', 'admin', 'LDAP配置', '客户端策略配置保存成功'),
	(1038, '2015-04-21 18:36:18', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1039, '2015-04-21 18:36:35', 'INFO', 'admin', 'LDAP配置', '客户端策略配置保存成功'),
	(1040, '2015-04-21 18:37:57', 'INFO', 'admin', 'LDAP配置', '客户端策略配置保存成功'),
	(1041, '2015-04-21 18:39:54', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1042, '2015-04-21 18:57:06', 'INFO', 'admin', '访问控制', '保存访问控制信息成功'),
	(1043, '2015-04-21 19:01:46', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1044, '2015-04-21 19:02:21', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1045, '2015-04-22 08:51:10', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1046, '2015-04-22 10:15:57', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1047, '2015-04-22 10:16:02', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1048, '2015-04-22 10:16:04', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1049, '2015-04-22 10:16:09', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1050, '2015-04-22 10:16:10', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1051, '2015-04-22 10:16:15', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1052, '2015-04-22 10:16:19', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1053, '2015-04-22 10:25:09', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1054, '2015-04-22 10:25:13', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1055, '2015-04-22 10:32:09', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1056, '2015-04-22 10:33:03', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1057, '2015-04-22 10:33:04', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1058, '2015-04-22 10:41:17', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1059, '2015-04-22 10:41:19', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1060, '2015-04-22 10:41:21', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1061, '2015-04-22 10:41:22', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1062, '2015-04-22 10:41:25', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1063, '2015-04-22 10:41:26', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1064, '2015-04-22 10:41:43', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(1065, '2015-04-22 10:42:21', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1066, '2015-04-22 10:42:21', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1067, '2015-04-22 10:42:25', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1068, '2015-04-22 10:42:26', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1069, '2015-04-22 10:42:31', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1070, '2015-04-22 10:42:31', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1071, '2015-04-22 10:43:19', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1072, '2015-04-22 10:43:19', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1073, '2015-04-22 10:43:20', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1074, '2015-04-22 10:43:20', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1075, '2015-04-22 10:47:05', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1076, '2015-04-22 10:47:05', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1077, '2015-04-22 10:47:06', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1078, '2015-04-22 10:47:06', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1079, '2015-04-22 10:47:11', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(1080, '2015-04-22 10:48:27', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1081, '2015-04-22 10:48:27', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1082, '2015-04-22 10:49:01', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1083, '2015-04-22 11:00:31', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1084, '2015-04-22 11:05:09', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1085, '2015-04-22 11:12:09', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1086, '2015-04-22 11:17:11', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1087, '2015-04-22 11:20:54', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1088, '2015-04-22 11:23:48', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1089, '2015-04-22 11:28:38', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1090, '2015-04-22 11:36:31', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1091, '2015-04-22 11:39:45', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1092, '2015-04-22 11:49:45', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1093, '2015-04-22 11:53:48', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1094, '2015-04-22 11:57:54', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1095, '2015-04-22 13:18:19', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1096, '2015-04-22 13:18:36', 'ERROE', 'admin', '角色管理', '用户更新角色信息失败'),
	(1097, '2015-04-22 13:18:46', 'ERROE', 'admin', '角色管理', '用户更新角色信息失败'),
	(1098, '2015-04-22 13:19:47', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1099, '2015-04-22 13:20:00', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(1100, '2015-04-22 13:20:23', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1101, '2015-04-22 13:20:35', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1102, '2015-04-22 13:20:39', 'INFO', 'admin', '用户管理', '用户检查用户名是否存在成功'),
	(1103, '2015-04-22 13:20:45', 'INFO', 'admin', '用户管理', '用户校验密码是否符合规则成功'),
	(1104, '2015-04-22 13:21:07', 'INFO', 'admin', '用户管理', '用户新增账户fdaf信息成功'),
	(1105, '2015-04-22 13:21:08', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1106, '2015-04-22 13:21:44', 'INFO', 'admin', '用户管理', '用户删除账户fdaf信息成功'),
	(1107, '2015-04-22 13:21:45', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1108, '2015-04-22 13:22:49', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1109, '2015-04-22 13:24:04', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1110, '2015-04-22 13:24:13', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(1111, '2015-04-22 13:24:19', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(1112, '2015-04-22 13:24:52', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1113, '2015-04-22 13:27:32', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1114, '2015-04-22 13:27:57', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(1115, '2015-04-22 13:28:06', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1116, '2015-04-22 14:02:55', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1117, '2015-04-22 14:14:13', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1118, '2015-04-22 14:23:48', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1119, '2015-04-22 14:25:41', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1120, '2015-04-22 14:31:20', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1121, '2015-04-22 15:37:53', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1122, '2015-04-22 15:43:09', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1123, '2015-04-22 15:49:59', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1124, '2015-04-22 16:00:14', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1125, '2015-04-22 16:04:30', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1126, '2015-04-22 16:08:06', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1127, '2015-04-22 16:08:16', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1128, '2015-04-22 16:08:33', 'error', 'admin', '双击热备', '用户更新双机热备配置信息失败'),
	(1129, '2015-04-22 16:08:37', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1130, '2015-04-22 16:08:37', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1131, '2015-04-22 16:08:39', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1132, '2015-04-22 17:13:57', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1133, '2015-04-22 17:38:04', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1134, '2015-04-22 17:42:22', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1135, '2015-04-22 17:48:14', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1136, '2015-04-22 17:48:33', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1137, '2015-04-22 17:48:33', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1138, '2015-04-22 17:48:39', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1139, '2015-04-22 17:48:43', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1140, '2015-04-22 17:53:03', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1141, '2015-04-22 17:53:37', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1142, '2015-04-22 17:54:25', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1143, '2015-04-27 10:32:41', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1144, '2015-04-27 13:39:40', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1145, '2015-04-27 13:52:25', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1146, '2015-04-27 13:54:30', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1147, '2015-04-27 14:10:27', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1148, '2015-04-27 14:11:54', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1149, '2015-04-27 14:55:04', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1150, '2015-04-27 15:21:14', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1151, '2015-04-27 15:29:32', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1152, '2015-04-27 15:35:41', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1153, '2015-04-27 15:38:17', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1154, '2015-04-27 16:39:13', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1155, '2015-04-27 17:01:12', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1156, '2015-04-27 17:09:53', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1157, '2015-04-27 17:18:17', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1158, '2015-04-27 17:23:40', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1159, '2015-04-28 08:56:09', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1160, '2015-04-28 10:04:16', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1161, '2015-04-28 10:06:53', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1162, '2015-04-28 13:24:17', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1163, '2015-04-28 13:54:44', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1164, '2015-04-28 15:02:30', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1165, '2015-04-28 15:04:27', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1166, '2015-04-28 15:04:30', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1167, '2015-04-28 15:36:48', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1168, '2015-04-28 15:38:06', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1169, '2015-04-28 15:38:07', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1170, '2015-04-28 15:38:14', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(1171, '2015-04-28 15:45:39', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1172, '2015-04-28 15:45:44', 'INFO', 'admin', '用户管理', '用户获取所有账号信息成功'),
	(1173, '2015-04-28 15:51:01', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1174, '2015-04-28 15:51:01', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1175, '2015-04-28 15:51:04', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1176, '2015-04-28 15:51:04', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1177, '2015-04-28 15:51:05', 'INFO', 'admin', '管理员日志审计', '用户读取管理员日志审计信息成功 '),
	(1178, '2015-04-28 15:51:05', 'INFO', 'admin', '用户日志审计', '用户获取所有账号名列表成功'),
	(1179, '2015-04-28 15:51:11', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(1180, '2015-04-28 15:52:17', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1181, '2015-04-28 17:11:28', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1182, '2015-04-29 12:48:44', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1183, '2015-04-29 14:42:55', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(1184, '2015-04-29 14:45:20', 'error', 'admin', '双击热备', '用户查找双机热备配置信息失败'),
	(1185, '2015-04-29 15:02:25', 'INFO', 'admin', '日志服务器', '添加日志服务器成功,服务器主机:172.16.2.98,服务器端口:514');
/*!40000 ALTER TABLE `user_oper_log` ENABLE KEYS */;


# Dumping structure for table sslvpn.user_route_user
DROP TABLE IF EXISTS `user_route_user`;
CREATE TABLE IF NOT EXISTS `user_route_user` (
  `user_id` int(10) NOT NULL,
  `route_user_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`route_user_id`),
  KEY `FK85FCA4F5C9DE2D4E` (`user_id`),
  KEY `FK85FCA4F5903768D9` (`route_user_id`),
  CONSTRAINT `FK_user_route_user_route_user` FOREIGN KEY (`route_user_id`) REFERENCES `route_user` (`id`),
  CONSTRAINT `FK_user_route_user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.user_route_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_route_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_route_user` ENABLE KEYS */;


# Dumping structure for table sslvpn.user_source_nets
DROP TABLE IF EXISTS `user_source_nets`;
CREATE TABLE IF NOT EXISTS `user_source_nets` (
  `source_net_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`source_net_id`,`user_id`),
  KEY `FK9DF2A5A6D95A9C2F` (`source_net_id`),
  KEY `FK9DF2A5A6C9DE2D4E` (`user_id`),
  CONSTRAINT `FK__source_nets` FOREIGN KEY (`source_net_id`) REFERENCES `source_nets` (`id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table sslvpn.user_source_nets: ~3 rows (approximately)
/*!40000 ALTER TABLE `user_source_nets` DISABLE KEYS */;
REPLACE INTO `user_source_nets` (`source_net_id`, `user_id`) VALUES
	(1, 3),
	(5, 3),
	(6, 3);
/*!40000 ALTER TABLE `user_source_nets` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
