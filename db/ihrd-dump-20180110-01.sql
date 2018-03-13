-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.25-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table ihrd_dev.cms_book
CREATE TABLE IF NOT EXISTS `cms_book` (
  `id` varchar(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_name_origin` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.cms_news
CREATE TABLE IF NOT EXISTS `cms_news` (
  `id` varchar(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `headline` varchar(255) DEFAULT NULL,
  `post_date` datetime DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_trx_news_01` (`user_id`),
  CONSTRAINT `fk_trx_news_01` FOREIGN KEY (`user_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_assurance
CREATE TABLE IF NOT EXISTS `mst_assurance` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_department
CREATE TABLE IF NOT EXISTS `mst_department` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_doctor
CREATE TABLE IF NOT EXISTS `mst_doctor` (
  `id` varchar(36) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `specialist` varchar(100) NOT NULL,
  `hospital_id` varchar(36) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_employee
CREATE TABLE IF NOT EXISTS `mst_employee` (
  `id` varchar(36) NOT NULL,
  `nik` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birth_place` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('male','famale') NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number1` varchar(50) DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `phone_number2` varchar(50) DEFAULT NULL,
  `npwp_no` varchar(50) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `current_department_id` varchar(36) DEFAULT NULL,
  `current_job_id` varchar(36) DEFAULT NULL,
  `current_salary` varchar(50) NOT NULL DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  `status` enum('active','leave','resign') NOT NULL DEFAULT 'active',
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mst_employee_01` (`current_department_id`),
  KEY `fk_mst_employee_02` (`current_job_id`),
  CONSTRAINT `fk_mst_employee_01` FOREIGN KEY (`current_department_id`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_mst_employee_02` FOREIGN KEY (`current_job_id`) REFERENCES `mst_job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_hospital
CREATE TABLE IF NOT EXISTS `mst_hospital` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number1` varchar(20) DEFAULT NULL,
  `phone_number2` varchar(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.mst_job
CREATE TABLE IF NOT EXISTS `mst_job` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_permission
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` varchar(36) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_role
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` varchar(36) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_role_permission
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `permission_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_sys_role_permission_01` (`permission_id`),
  KEY `fk_sys_role_permission_02` (`role_id`),
  CONSTRAINT `fk_sys_role_permission_01` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `fk_sys_role_permission_02` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_user
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  `type` enum('none','employee') NOT NULL DEFAULT 'none',
  `ref_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_user_role
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.sys_user_token
CREATE TABLE IF NOT EXISTS `sys_user_token` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_sys_user_token_01` (`user_id`),
  CONSTRAINT `fk_sys_user_token_01` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_attendance
CREATE TABLE IF NOT EXISTS `trx_attendance` (
  `id` varchar(36) NOT NULL,
  `employee_id` varchar(36) NOT NULL,
  `attendance_date` date DEFAULT NULL,
  `shift` tinyint(1) NOT NULL DEFAULT '1',
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `overtime` int(11) DEFAULT NULL,
  `attend` tinyint(1) DEFAULT NULL,
  `reason_type` enum('sick','leave','alpha') DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_delay_request
CREATE TABLE IF NOT EXISTS `trx_delay_request` (
  `id` varchar(36) NOT NULL,
  `request_by` varchar(36) NOT NULL,
  `request_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `status` enum('new','approved','rejected') NOT NULL DEFAULT 'new',
  `note` varchar(255) DEFAULT NULL,
  `processed_by` varchar(36) DEFAULT NULL,
  `process_note` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_job_history
CREATE TABLE IF NOT EXISTS `trx_job_history` (
  `id` varchar(36) NOT NULL,
  `employee_id` varchar(36) NOT NULL,
  `job_id` varchar(36) NOT NULL,
  `deparment_id` varchar(36) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_trx_job_history_01` (`deparment_id`),
  KEY `fk_trx_job_history_02` (`job_id`),
  KEY `fk_trx_job_history_03` (`employee_id`),
  CONSTRAINT `fk_trx_job_history_01` FOREIGN KEY (`deparment_id`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_trx_job_history_02` FOREIGN KEY (`job_id`) REFERENCES `mst_job` (`id`),
  CONSTRAINT `fk_trx_job_history_03` FOREIGN KEY (`employee_id`) REFERENCES `mst_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_leave_request
CREATE TABLE IF NOT EXISTS `trx_leave_request` (
  `id` varchar(36) NOT NULL,
  `request_by` varchar(36) NOT NULL,
  `request_date` datetime NOT NULL,
  `type` enum('leave','sick','permission') NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `half_day` tinyint(1) NOT NULL DEFAULT '0',
  `status` enum('new','approved','rejected') NOT NULL DEFAULT 'new',
  `note` varchar(255) DEFAULT NULL,
  `processed_by` varchar(36) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `last_action` varchar(100) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  `process_note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_overtime_request
CREATE TABLE IF NOT EXISTS `trx_overtime_request` (
  `id` varchar(36) NOT NULL,
  `request_by` varchar(36) NOT NULL,
  `request_date` datetime NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `duration` int(11) NOT NULL,
  `status` enum('new','approved','rejected') NOT NULL DEFAULT 'new',
  `note` varchar(255) DEFAULT NULL,
  `processed_by` varchar(36) DEFAULT NULL,
  `process_note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ihrd_dev.trx_paycheck
CREATE TABLE IF NOT EXISTS `trx_paycheck` (
  `id` varchar(36) NOT NULL,
  `employee_id` varchar(36) NOT NULL,
  `salary_date` date NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_name_origin` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(36) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(36) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id_salary_date_deleted` (`employee_id`,`salary_date`,`deleted`),
  CONSTRAINT `FK_trx_paycheck_mst_employee` FOREIGN KEY (`employee_id`) REFERENCES `mst_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for view ihrd_dev.v_employee
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_employee` (
	`id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`nik` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`full_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`birth_place` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`birth_date` DATE NOT NULL,
	`gender` ENUM('male','famale') NOT NULL COLLATE 'latin1_swedish_ci',
	`email` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`phone_number1` VARCHAR(50) NULL COLLATE 'latin1_swedish_ci',
	`join_date` DATE NULL,
	`phone_number2` VARCHAR(50) NULL COLLATE 'latin1_swedish_ci',
	`npwp_no` VARCHAR(50) NULL COLLATE 'latin1_swedish_ci',
	`picture` VARCHAR(255) NULL COLLATE 'latin1_swedish_ci',
	`current_department_id` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`current_job_id` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`current_salary` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`created_date` DATETIME NULL,
	`created_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`updated_date` DATETIME NULL,
	`updated_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`last_action` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`deleted` INT(1) NOT NULL,
	`status` ENUM('active','leave','resign') NOT NULL COLLATE 'latin1_swedish_ci',
	`address` VARCHAR(255) NULL COLLATE 'latin1_swedish_ci',
	`current_department_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`current_job_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Dumping structure for view ihrd_dev.v_leave_request
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_leave_request` (
	`id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`request_by` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`request_date` DATETIME NOT NULL,
	`type` ENUM('leave','sick','permission') NOT NULL COLLATE 'latin1_swedish_ci',
	`start_date` DATETIME NOT NULL,
	`end_date` DATETIME NOT NULL,
	`duration` INT(11) NOT NULL,
	`half_day` TINYINT(1) NOT NULL,
	`status` ENUM('new','approved','rejected') NOT NULL COLLATE 'latin1_swedish_ci',
	`note` VARCHAR(255) NULL COLLATE 'latin1_swedish_ci',
	`processed_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`created_date` DATETIME NULL,
	`created_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`updated_date` DATETIME NULL,
	`updated_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`last_action` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`deleted` INT(1) NOT NULL,
	`process_note` VARCHAR(255) NULL COLLATE 'latin1_swedish_ci',
	`nik` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`full_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`job_id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`job_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`department_id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`department_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Dumping structure for view ihrd_dev.v_user
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_user` (
	`id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
	`username` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`passwd` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`full_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`created_date` DATETIME NULL,
	`created_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`updated_date` DATETIME NULL,
	`updated_by` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`last_action` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`deleted` INT(11) NOT NULL,
	`type` VARCHAR(8) NOT NULL COLLATE 'latin1_swedish_ci',
	`ref_id` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`nik` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`birth_date` VARCHAR(10) NOT NULL COLLATE 'utf8mb4_general_ci',
	`birth_place` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`phone_number1` VARCHAR(50) NULL COLLATE 'latin1_swedish_ci',
	`phone_number2` VARCHAR(50) NULL COLLATE 'latin1_swedish_ci',
	`gender` VARCHAR(6) NOT NULL COLLATE 'latin1_swedish_ci',
	`current_department_id` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`current_job_id` VARCHAR(36) NULL COLLATE 'latin1_swedish_ci',
	`current_department_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`current_job_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`address` VARCHAR(255) NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Dumping structure for view ihrd_dev.v_employee
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_employee`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `v_employee` AS select
    emp.*,
    dept.name as current_department_name,
    j.name as current_job_name
  from mst_employee emp
    join mst_department dept on dept.id = emp.current_department_id
    join mst_job j on j.id = emp.current_job_id ;

-- Dumping structure for view ihrd_dev.v_leave_request
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_leave_request`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `v_leave_request` AS select
    lr.*,
    emp.nik,
    emp.full_name as full_name,
    j.id as job_id,
    j.name as job_name,
    dept.id as department_id,
    dept.name as department_name
  from trx_leave_request lr
    join mst_employee emp on emp.id = lr.request_by
    join mst_job j on j.id = emp.current_job_id
    join mst_department dept on dept .id = emp.current_department_id ;

-- Dumping structure for view ihrd_dev.v_user
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `v_user` AS select
    u.*,
    emp.nik,
    emp.birth_date,
    emp.birth_place,
    emp.phone_number1,
    emp.phone_number2,
    emp.gender,
    emp.current_department_id,
    emp.current_job_id,
    dept.name as current_department_name,
    j.name as current_job_name,
    emp.address
  from sys_user u
    join mst_employee emp on emp.id = u.ref_id
    join mst_department dept on dept.id = emp.current_department_id
    join mst_job j on j.id = emp.current_job_id
  where u.type = 'employee'
  union all
  select
    u.*,
    '' nik,
    '' birth_date,
    '' birth_place,
    '' phone_number1,
    '' phone_number2,
    '' gender,
    '' current_department_id,
    '' current_job_id,
    '' current_department_name,
    '' current_job_name,
    '' address
  from sys_user u
    join mst_employee emp on emp.id = u.ref_id
    join mst_department dept on dept.id = emp.current_department_id
    join mst_job j on j.id = emp.current_job_id
  where u.type = 'none' ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
