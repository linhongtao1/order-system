/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.1.68-community : Database - ordersys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordersys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ordersys`;

/*Table structure for table `dishesinfo` */

DROP TABLE IF EXISTS `dishesinfo`;

CREATE TABLE `dishesinfo` (
  `dishesId` int(11) NOT NULL AUTO_INCREMENT,
  `dishesName` varchar(50) DEFAULT NULL,
  `dishesDiscript` varchar(100) DEFAULT NULL,
  `dishesImg` varchar(100) DEFAULT NULL,
  `dishesTxt` varchar(400) DEFAULT NULL,
  `recommend` int(11) DEFAULT '0',
  `dishesPrice` float DEFAULT '1',
  PRIMARY KEY (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `dishesinfo` */

insert  into `dishesinfo`(`dishesId`,`dishesName`,`dishesDiscript`,`dishesImg`,`dishesTxt`,`recommend`,`dishesPrice`) values (1,'川香回锅肉','菜色鲜香，肥而不腻','1.jpg','采用上等五花肉制作，配以酸姜、\"酸辣椒\"及\'秘制\'豆豉酱，味道鲜美，营养丰富',1,26.5),(3,'鱼香肉丝','很好吃，但是没有鱼肉','7.jpg','很好吃，但是没有鱼肉.很好吃，但是没有鱼肉.很好吃，但是没有鱼肉.重要的事情说三遍！~',0,25.5),(4,'测试菜品','测试','1.jpg','测试\r\n测试\r\n测试',0,10.5),(5,'测试菜品1','测试菜品，试验一下系统功能是否能够正确处理','7.jpg','测试菜品，试验一下系统功能是否能够正确处理\r\n测试菜品，试验一下系统功能是否能够正确处理\r\n测试菜品，试验一下系统功能是否能够正确处理\r\n重要的事情说三遍~！',1,16),(6,'测试菜品3333','测试菜品，试验一下系统功能是否能够正确处理2222','1.jpg','测试菜品，试验一下系统功能是否能够正确处理\r\n测试菜品，试验一下系统功能是否能够正确处理\r\n测试菜品，试验一下系统功能是否能够正确处理\r\n重要的事情说三遍~！！！！123123123',1,36);

/*Table structure for table `orderdishes` */

DROP TABLE IF EXISTS `orderdishes`;

CREATE TABLE `orderdishes` (
  `odId` int(11) NOT NULL AUTO_INCREMENT,
  `orderReference` int(11) DEFAULT NULL,
  `dishes` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT '1',
  PRIMARY KEY (`odId`),
  KEY `orderReference` (`orderReference`),
  KEY `dishes` (`dishes`),
  CONSTRAINT `orderdishes_ibfk_1` FOREIGN KEY (`orderReference`) REFERENCES `orderinfo` (`orderId`),
  CONSTRAINT `orderdishes_ibfk_2` FOREIGN KEY (`dishes`) REFERENCES `dishesinfo` (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `orderdishes` */

insert  into `orderdishes`(`odId`,`orderReference`,`dishes`,`num`) values (1,4,1,4),(2,4,5,1),(3,4,4,2),(4,5,1,3),(5,5,5,1),(6,6,4,1),(7,6,1,2),(8,6,6,3),(9,6,4,1),(10,7,1,3),(11,7,5,1),(12,7,6,1),(13,8,1,1),(14,8,5,1),(15,9,1,3),(16,10,5,1),(17,10,6,1),(18,11,1,4),(19,11,4,4),(20,11,6,2),(21,12,1,1),(22,12,5,1),(23,13,1,3),(24,13,5,5),(25,13,6,1),(26,14,1,3),(27,14,5,1),(28,15,3,3),(29,15,1,1),(30,16,1,4),(31,16,5,2),(32,17,1,4),(33,17,5,2),(34,18,5,3),(35,18,1,3),(36,19,1,3),(37,19,6,1);

/*Table structure for table `orderinfo` */

DROP TABLE IF EXISTS `orderinfo`;

CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderBeginDate` datetime DEFAULT NULL,
  `orderEndDate` datetime DEFAULT NULL,
  `waiterId` int(11) DEFAULT NULL,
  `orderState` int(11) DEFAULT '0',
  `tableId` int(11) DEFAULT '1',
  PRIMARY KEY (`orderId`),
  KEY `waiterfk` (`waiterId`),
  CONSTRAINT `waiterfk` FOREIGN KEY (`waiterId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `orderinfo` */

insert  into `orderinfo`(`orderId`,`orderBeginDate`,`orderEndDate`,`waiterId`,`orderState`,`tableId`) values (1,'2015-09-09 16:49:29','2015-09-10 11:49:57',1,1,1),(2,'2015-09-09 16:51:35','2015-10-12 11:42:01',1,1,1),(3,'2015-09-09 16:53:59','2015-10-13 10:47:09',1,1,1),(4,'2015-09-09 16:56:16','2015-09-10 14:13:16',1,2,1),(5,'2015-10-12 11:15:41','2015-10-12 11:42:36',17,2,1),(6,'2015-10-12 11:46:57','2015-10-12 11:48:25',17,1,12),(7,'2015-10-12 11:51:15','2015-10-12 11:52:05',17,1,12),(8,'2015-10-12 11:52:31','2015-10-12 11:52:35',17,1,12),(9,'2015-10-13 10:46:53','2015-10-13 10:47:02',17,2,1),(10,'2015-10-13 10:47:31','2015-10-13 10:47:35',17,2,1),(11,'2015-10-13 10:50:39','2015-10-13 10:51:00',17,1,12),(12,'2015-10-13 10:52:57','2015-10-13 10:53:54',17,1,12),(13,'2015-10-13 13:31:11','2015-10-29 14:19:23',17,1,1),(14,'2015-10-13 13:34:47','2015-10-26 16:01:19',17,1,1),(15,'2015-10-13 14:50:51','2015-10-13 14:51:32',17,1,3),(16,'2015-10-26 15:32:39','2015-10-30 13:06:48',17,1,1),(17,'2015-10-26 15:33:43',NULL,17,0,1),(18,'2015-10-29 15:49:04','2015-10-29 15:49:39',17,1,1),(19,'2015-11-04 14:05:57','2015-11-04 14:08:28',17,1,1);

/*Table structure for table `roleinfo` */

DROP TABLE IF EXISTS `roleinfo`;

CREATE TABLE `roleinfo` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `roleinfo` */

insert  into `roleinfo`(`roleId`,`roleName`) values (1,'餐厅管理员'),(2,'后厨人员'),(3,'餐厅服务员');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(30) DEFAULT NULL,
  `userPass` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT '0',
  `faceimg` varchar(150) DEFAULT 'default.jpg',
  PRIMARY KEY (`userId`),
  KEY `fkrole` (`role`),
  CONSTRAINT `fkrole` FOREIGN KEY (`role`) REFERENCES `roleinfo` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`userId`,`userAccount`,`userPass`,`role`,`locked`,`faceimg`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3',1,0,'default.jpg'),(15,'User3','2d86bdac01a3315b95794ffa7360edc3',3,0,'default.jpg'),(16,'User4','2d86bdac01a3315b95794ffa7360edc3',3,0,'default.jpg'),(17,'zyyyl','2d86bdac01a3315b95794ffa7360edc3',3,0,'7.jpg'),(18,'zyyyl3121','2d86bdac01a3315b95794ffa7360edc3',2,0,'1.jpg'),(19,'testSSH','2d86bdac01a3315b95794ffa7360edc3',2,0,'default.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
