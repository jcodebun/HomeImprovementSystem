/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - homeimpsys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`homeimpsys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `homeimpsys`;

/*Table structure for table `h_application` */

DROP TABLE IF EXISTS `h_application`;

CREATE TABLE `h_application` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(225) DEFAULT NULL,
  `applicationName` varchar(225) DEFAULT NULL,
  `applicationId` bigint(20) DEFAULT NULL,
  `city` varchar(225) DEFAULT NULL,
  `state` varchar(225) DEFAULT NULL,
  `addressline1` varchar(225) DEFAULT NULL,
  `addressline2` varchar(225) DEFAULT NULL,
  `pincode` varchar(225) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `fixedIncome` varchar(225) DEFAULT NULL,
  `anotherIncome` varchar(225) DEFAULT NULL,
  `residentDetail` varchar(255) DEFAULT NULL,
  `genralCondition` varchar(225) DEFAULT NULL,
  `workDescription` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_application` */

insert  into `h_application`(`ID`,`userId`,`userName`,`applicationName`,`applicationId`,`city`,`state`,`addressline1`,`addressline2`,`pincode`,`date`,`fixedIncome`,`anotherIncome`,`residentDetail`,`genralCondition`,`workDescription`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,2,'Rupak Mukati','SSDD',201101,'Indore','MP','BDGIGIGD','wdas','653636','2019-03-18 13:39:24','1200000','52000','asffef','efwef','wqfwqfd','Rupak@gmail.com','Rupak@gmail.com','2019-03-18 13:41:05','2019-03-18 13:39:24'),(2,2,'Rupak Mukati','DDDD',201102,'Indore','MP','603 Clarendon Court','Nulla illum est fug','72777','2019-03-18 18:34:05','1200000','52000','Exercitationem aut a','Et do aliquip quia o','Velit consequatur E','Rupak@gmail.com','Rupak@gmail.com','2019-03-18 18:34:05','2019-03-18 18:34:05');

/*Table structure for table `h_employee` */

DROP TABLE IF EXISTS `h_employee`;

CREATE TABLE `h_employee` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `skills` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_h_employee` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_employee` */

insert  into `h_employee`(`ID`,`userId`,`name`,`skills`,`dob`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,4,'Vijay Bhayre','Painter','1997-10-06','dsvsv','Hariom@gmail.com','Hariom@gmail.com','2019-03-18 11:35:13','2019-03-18 11:35:13');

/*Table structure for table `h_project` */

DROP TABLE IF EXISTS `h_project`;

CREATE TABLE `h_project` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `projectId` bigint(20) DEFAULT NULL,
  `applicationId` bigint(20) DEFAULT NULL,
  `applicationName` varchar(225) DEFAULT NULL,
  `employeeQuantity` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `toDate` date DEFAULT NULL,
  `fromDate` date DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_project` */

insert  into `h_project`(`ID`,`name`,`projectId`,`applicationId`,`applicationName`,`employeeQuantity`,`description`,`toDate`,`fromDate`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Lila Bowman',3010103,201101,'SSDD','4','ascdv','2020-06-01','2020-08-01','Hariom@gmail.com','Hariom@gmail.com','2019-03-18 15:58:57','2019-03-19 10:25:19'),(2,'DDDDD',3010104,201102,'DDDD','2','DDD','2020-06-01','2007-05-10','Hariom@gmail.com','Hariom@gmail.com','2019-03-18 18:34:48','2019-03-23 21:41:03');

/*Table structure for table `h_role` */

DROP TABLE IF EXISTS `h_role`;

CREATE TABLE `h_role` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_role` */

insert  into `h_role`(`ID`,`name`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Admin','Adminisration',NULL,NULL,'2019-03-16 20:03:10','2019-03-13 20:03:07'),(2,'Employee','Employee',NULL,NULL,'2019-03-16 20:03:12','2019-03-19 20:03:10'),(3,'Customer','Customer',NULL,NULL,'2019-03-16 20:03:19','2019-03-12 20:03:15');

/*Table structure for table `h_status` */

DROP TABLE IF EXISTS `h_status`;

CREATE TABLE `h_status` (
  `ID` bigint(20) NOT NULL,
  `projectId` bigint(20) DEFAULT NULL,
  `projectName` varchar(225) DEFAULT NULL,
  `applicationId` bigint(20) DEFAULT NULL,
  `applicationName` varchar(225) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_status` */

insert  into `h_status`(`ID`,`projectId`,`projectName`,`applicationId`,`applicationName`,`date`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,3010102,'DDDDD',201101,'SSDD','2019-03-19 10:34:49','VJVJVJVJVJ','Vijay@gmail.com','Vijay@gmail.com','2019-03-18 15:59:14','2019-03-19 10:34:49');

/*Table structure for table `h_user` */

DROP TABLE IF EXISTS `h_user`;

CREATE TABLE `h_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `homeId` bigint(20) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Address` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_h_user` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_user` */

insert  into `h_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`mobileNo`,`roleId`,`homeId`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`Address`) values (1,'Hariom','Mukati','Hariom@gmail.com','321','9165415599',1,100101,'root','root','2019-03-17 09:50:14','2019-03-16 18:56:16','BDGIGIGD'),(2,'Rupak','Mukati','Rupak@gmail.com','123','9165415598',3,100102,'root','root','2019-03-17 09:52:25','2019-03-17 09:52:25','UGB BGGG B'),(3,'Sawan','Mukati','Sawan@gmail.com','123','9165415598',3,100103,'root','root','2019-03-18 11:01:33','2019-03-17 09:54:02','BDGIGIGD'),(4,'Vijay','Bhayre','Vijay@gmail.com','123','9165415598',2,100104,'Hariom@gmail.com','Hariom@gmail.com','2019-03-18 11:34:51','2019-03-18 11:34:59','BDGIGIGD'),(5,'Hariom','Mukati','Vishu@gmail.com','Ha@123','9165415598',3,100105,'root','root','2019-03-23 21:38:25','2019-03-23 21:38:47','Beatae irure rerum e');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
