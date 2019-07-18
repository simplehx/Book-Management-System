/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.16 : Database - book_mis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book_mis` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `book_mis`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `press` varchar(30) DEFAULT NULL,
  `details` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`price`,`author`,`press`,`details`) values 
(6,'DDD',5,'BBB','AAA','CCC'),
(14,'CCC',1.1,'BBB','AAA','CCC'),
(17,'ZZZ',111,'ZZZ','AAA','ZZZ'),
(19,'DDD',2.2,'DDD','AAA','DDD'),
(20,'PPP',1.23,'PPP','AAA','PPP'),
(22,'HHH',222,'HHH','AAA','HHH'),
(23,'FFF',333,'FFF','AAA','FFF'),
(25,'BBBB',12,'AAA','AAA','AAA'),
(39,'AAA',100,'AAA','AAA','AAA'),
(40,'AAA',1.23,'AAA','AAA','AAA'),
(42,'AAA',1.23,'AAA','AAA','AAA'),
(43,'AAA',1.23,'AAA','AAA','AAA'),
(44,'AAA',100,'AAA','AAA','AAA'),
(46,'AAA',1.23,'CCC','AAA','AAA'),
(47,'BBB',1.23,'AAA','AAA','AAA'),
(48,'AAA',50,'AAA','AAA','AAA'),
(50,'AAA',1.23,'AAA','AAA','AAA'),
(51,'AAA',1.23,'AAA','AAA','AAA'),
(52,'AAA',1.23,'AAA','AAA','AAA'),
(53,'BBB',88,'BBB','BBB','BBB'),
(54,'CCC',11,'CCC','CCC','CCC');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `role` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`role`) values 
(1,'admin','123',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
