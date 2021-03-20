/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.7.21 : Database - dermatoloskaklinika
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dermatoloskaklinika` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `dermatoloskaklinika`;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikID` int(255) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `korisnickoIme` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sifra` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`korisnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnik` */

/*Table structure for table `lek` */

DROP TABLE IF EXISTS `lek`;

CREATE TABLE `lek` (
  `lekID` int(255) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rokTrajanja` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lekID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lek` */

/*Table structure for table `lekar` */

DROP TABLE IF EXISTS `lekar`;

CREATE TABLE `lekar` (
  `lekarID` int(255) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kontakt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lekarID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lekar` */

/*Table structure for table `pacijent` */

DROP TABLE IF EXISTS `pacijent`;

CREATE TABLE `pacijent` (
  `pacijentID` int(255) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kontakt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pacijentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `pacijent` */

/*Table structure for table `stavkaterapije` */

DROP TABLE IF EXISTS `stavkaterapije`;

CREATE TABLE `stavkaterapije` (
  `redniBroj` int(255) NOT NULL,
  `nacinPripreme` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lekID` int(255) DEFAULT NULL,
  `terapijaID` int(255) NOT NULL,
  PRIMARY KEY (`redniBroj`,`terapijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkaterapije` */

/*Table structure for table `terapija` */

DROP TABLE IF EXISTS `terapija`;

CREATE TABLE `terapija` (
  `terapijaID` int(255) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `pacijentID` int(255) DEFAULT NULL,
  `korisnikID` int(255) DEFAULT NULL,
  `lekarID` int(255) DEFAULT NULL,
  PRIMARY KEY (`terapijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `terapija` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
