/*
SQLyog Community v9.63 
MySQL - 5.5.27 : Database - ppm_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ppm_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ppm_db`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `booking_id` varchar(10) NOT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `vendor_id` varchar(10) DEFAULT NULL,
  `service_id` varchar(10) DEFAULT NULL,
  `booking_date_time` datetime DEFAULT NULL,
  `session_start_time` datetime DEFAULT NULL,
  `session_end_time` datetime DEFAULT NULL,
  `booking_timestamp` datetime DEFAULT NULL,
  `booking_status` enum('UPCOMING','ONGOING','COMPLETED','CANCELLED') DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

/*Table structure for table `crowd` */

DROP TABLE IF EXISTS `crowd`;

CREATE TABLE `crowd` (
  `crowd_id` varchar(10) NOT NULL,
  `crowd_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`crowd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `crowd` */

insert  into `crowd`(`crowd_id`,`crowd_type`) values ('1','CORPORATE'),('2','CHILDREN'),('3','WOMEN'),('4','ALL');

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `service_id` varchar(10) NOT NULL,
  `service_name` varchar(64) DEFAULT NULL,
  `service_description` varchar(100) DEFAULT NULL,
  `service_type` enum('booking_type','non_booking_type') DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `service` */

insert  into `service`(`service_id`,`service_name`,`service_description`,`service_type`) values ('101','ZUMBA',NULL,NULL),('102','GYM',NULL,NULL),('103','TRX',NULL,NULL),('104','CYCLING',NULL,NULL),('105','Cross Functional Training',NULL,NULL),('106','Swimming',NULL,NULL),('107','Yoga',NULL,NULL),('108','salsa',NULL,NULL),('109','tabata',NULL,NULL),('110','personal training',NULL,NULL);

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `vendor_id` varchar(10) NOT NULL,
  `vendor_name` varchar(64) DEFAULT NULL,
  `vendor_address` varchar(128) DEFAULT NULL,
  `vendor_contact` decimal(10,0) DEFAULT NULL,
  `vendor_email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor` */

insert  into `vendor`(`vendor_id`,`vendor_name`,`vendor_address`,`vendor_contact`,`vendor_email`) values ('1001','Universal Gym','Gurgaon','9910887765','universalgym@gmail.com'),('1002','Gold\'s Gym','Gurgaon new colony','9988776655','gold@gmail.com');

/*Table structure for table `vendor_account` */

DROP TABLE IF EXISTS `vendor_account`;

CREATE TABLE `vendor_account` (
  `vendor_id` varchar(10) DEFAULT NULL,
  `account_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor_account` */

/*Table structure for table `vendor_details` */

DROP TABLE IF EXISTS `vendor_details`;

CREATE TABLE `vendor_details` (
  `vendor_id` varchar(10) DEFAULT NULL,
  `service_id` varchar(10) DEFAULT NULL,
  `images_path` varchar(30) DEFAULT NULL,
  `video_path` varchar(30) DEFAULT NULL,
  KEY `vendor_id` (`vendor_id`),
  CONSTRAINT `vendor_details_ibfk_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor_details` */

insert  into `vendor_details`(`vendor_id`,`service_id`,`images_path`,`video_path`) values ('1001','101','1001/101/images','1001/101/videos'),('1001','102','1001/102/images','1001/102/videos'),('1002','104','1001/104/images','1001/104/videos'),('1002','103','1001/103/images','1001/103/videos');

/*Table structure for table `vendor_service` */

DROP TABLE IF EXISTS `vendor_service`;

CREATE TABLE `vendor_service` (
  `vendor_id` varchar(10) NOT NULL,
  `service_id` varchar(10) NOT NULL,
  `service_day_time` datetime DEFAULT NULL,
  `default_capacity` varchar(10) DEFAULT NULL,
  `crowd_id` varchar(10) DEFAULT NULL,
  `service_curr_rate` varchar(30) DEFAULT NULL,
  `last_updated_rate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor_service` */

insert  into `vendor_service`(`vendor_id`,`service_id`,`service_day_time`,`default_capacity`,`crowd_id`,`service_curr_rate`,`last_updated_rate`) values ('1001','101','2018-08-02 04:00:00','50','4','4','2018-07-29 22:38:14'),('1002','104','2018-08-03 21:00:00','30','3','5.2','2018-07-29 22:39:05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
