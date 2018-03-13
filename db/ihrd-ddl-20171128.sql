-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.1.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table ihrd_dev.sys_permission
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` varchar(36) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;

-- Dumping structure for table ihrd_dev.sys_role
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` varchar(36) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_role: ~3 rows (approximately)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `code`, `name`, `description`) VALUES
	('9895d949-d3b6-11e7-9026-dc85de90e3b2', 'root', 'Super Admin', 'Super Administrator'),
	('989a8f54-d3b6-11e7-9026-dc85de90e3b2', 'admin', 'Administrator', 'Administrator'),
	('989a904a-d3b6-11e7-9026-dc85de90e3b2', 'staff', 'Staff', 'Administrator');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- Dumping structure for table ihrd_dev.sys_role_permission
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `permission_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_role_permission_01` (`permission_id`),
  KEY `fk_sys_role_permission_02` (`role_id`),
  CONSTRAINT `fk_sys_role_permission_01` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `fk_sys_role_permission_02` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;

-- Dumping structure for table ihrd_dev.sys_user
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `passwd`, `full_name`) VALUES
	('65ae5a95-d3b0-11e7-9026-dc85de90e3b2', 'wendi', 'wendi', 'Wendi Gunawan'),
	('65e0f71f-d3b0-11e7-9026-dc85de90e3b2', 'mark', 'mark', 'Mark Muhammad'),
	('65f5d3a8-d3b0-11e7-9026-dc85de90e3b2', 'hakim', 'hakim', 'Hakim Marsudi');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- Dumping structure for table ihrd_dev.sys_user_role
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_user_role: ~6 rows (approximately)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
	('9257ca29-d3b7-11e7-9026-dc85de90e3b2', '65ae5a95-d3b0-11e7-9026-dc85de90e3b2', '9895d949-d3b6-11e7-9026-dc85de90e3b2'),
	('925a2cef-d3b7-11e7-9026-dc85de90e3b2', '65ae5a95-d3b0-11e7-9026-dc85de90e3b2', '989a904a-d3b6-11e7-9026-dc85de90e3b2'),
	('925a2e3b-d3b7-11e7-9026-dc85de90e3b2', '65e0f71f-d3b0-11e7-9026-dc85de90e3b2', '989a8f54-d3b6-11e7-9026-dc85de90e3b2'),
	('925a2eb7-d3b7-11e7-9026-dc85de90e3b2', '65e0f71f-d3b0-11e7-9026-dc85de90e3b2', '989a904a-d3b6-11e7-9026-dc85de90e3b2'),
	('925a2f2e-d3b7-11e7-9026-dc85de90e3b2', '65f5d3a8-d3b0-11e7-9026-dc85de90e3b2', '989a8f54-d3b6-11e7-9026-dc85de90e3b2'),
	('925a3e97-d3b7-11e7-9026-dc85de90e3b2', '65f5d3a8-d3b0-11e7-9026-dc85de90e3b2', '989a904a-d3b6-11e7-9026-dc85de90e3b2');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- Dumping structure for table ihrd_dev.sys_user_token
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE IF NOT EXISTS `sys_user_token` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_user_token_01` (`user_id`),
  CONSTRAINT `fk_sys_user_token_01` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ihrd_dev.sys_user_token: ~1 rows (approximately)
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` (`id`, `user_id`, `token`, `expired_date`) VALUES
	('6cce16c3-626f-4d7d-942d-f8b7a9432908', '65ae5a95-d3b0-11e7-9026-dc85de90e3b2', 'ZgVTvUk5mwb1Y', '2017-11-28 04:00:14');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
