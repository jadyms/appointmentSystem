-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.30-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for star
CREATE DATABASE IF NOT EXISTS `star` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `star`;

-- Dumping structure for table star.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `email` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table star.admin: ~0 rows (approximately)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`email`, `password`) VALUES
	('admin@admin.com', '21232F297A57A5A743894A0E4A801FC3');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table star.bookings
CREATE TABLE IF NOT EXISTS `bookings` (
  `pEmail` varchar(30) NOT NULL,
  `fdate` date NOT NULL,
  `ftime` time NOT NULL,
  `slotStatus` varchar(20) DEFAULT NULL,
  `cEmail` varchar(30) DEFAULT NULL,
  `AptStatus` varchar(20) NOT NULL,
  `dtAptStatus` date DEFAULT NULL,
  PRIMARY KEY (`fdate`,`ftime`,`pEmail`),
  KEY `p_email` (`pEmail`),
  KEY `FK_bookings_customers` (`cEmail`),
  CONSTRAINT `FK_bookings_customers` FOREIGN KEY (`cEmail`) REFERENCES `customers` (`cEmail`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`pEmail`) REFERENCES `providers` (`pEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table star.bookings: ~21 rows (approximately)
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` (`pEmail`, `fdate`, `ftime`, `slotStatus`, `cEmail`, `AptStatus`, `dtAptStatus`) VALUES
	('clare@email.com', '2018-08-10', '10:00:00', 'B', 'anne@email.com', 'Confirmed', '2018-08-10'),
	('clare@email.com', '2018-09-10', '12:00:00', 'A', NULL, 'Pending', NULL),
	('jane@email.com', '2018-09-12', '13:00:00', 'B', 'anne@email.com', 'Pending', NULL),
	('luke@email.com', '2018-09-12', '13:00:00', 'B', 'anne@email.com', 'Confirmed', '2018-09-12'),
	('luke@email.com', '2018-09-13', '14:00:00', 'B', 'carol@email.com', 'Confirmed', '2018-09-13'),
	('mary@email.com', '2018-09-14', '15:00:00', 'B', 'carol@email.com', 'Confirmed', '2018-09-14'),
	('paul@email.com', '2018-09-15', '14:00:00', 'B', 'louise@email.com', 'Confirmed', '2018-09-15'),
	('luke@email.com', '2018-09-16', '17:00:00', 'B', 'brian@email.com', 'Confirmed', '2018-09-16'),
	('peter@email.com', '2018-09-17', '14:00:00', 'B', 'james@email.com', 'No-Show', '2018-09-17'),
	('peter@email.com', '2018-09-18', '13:00:00', 'B', 'james@email.com', 'Confirmed', '2018-09-18'),
	('mary@email.com', '2018-09-19', '14:00:00', 'B', 'john@email.com', 'Confirmed', '2018-09-19'),
	('paul@email.com', '2018-12-03', '12:00:00', 'B', 'john@email.com', 'Pending', NULL),
	('luke@email.com', '2018-12-13', '14:00:00', 'B', 'carol@email.com', 'Pending', NULL),
	('laura@email.com', '2018-12-14', '09:00:00', 'B', 'hugh@email.com', 'Pending', NULL),
	('yuri@email.com', '2018-12-19', '09:00:00', 'B', 'hugh@email.com', 'Pending', NULL),
	('yuri@email.com', '2018-12-21', '09:00:00', 'A', NULL, 'Pending', NULL),
	('yuri@email.com', '2018-12-21', '15:00:00', 'A', NULL, 'Pending', NULL),
	('yuri@email.com', '2018-12-22', '10:00:00', 'B', 'hugh@email.com', 'Completed', NULL),
	('yuri@email.com', '2018-12-29', '14:00:00', 'B', 'anne@email.com', 'No-Show', NULL),
	('steven@email.com', '2018-12-30', '09:00:00', 'B', 'hugh@email.com', 'Cancelled', NULL),
	('yuri@email.com', '2018-12-30', '09:00:00', 'A', NULL, 'Pending', NULL),
	('steven@email.com', '2018-12-31', '14:00:00', 'B', 'anne@email.com', 'Pending', NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;

-- Dumping structure for table star.complaints
CREATE TABLE IF NOT EXISTS `complaints` (
  `c_email` varchar(30) DEFAULT NULL,
  `p_email` varchar(30) DEFAULT NULL,
  `complaint` longtext,
  `date` date DEFAULT NULL,
  `cpID` int(3) NOT NULL AUTO_INCREMENT,
  `admResp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cpID`),
  KEY `c_email` (`c_email`),
  KEY `admResp` (`admResp`),
  CONSTRAINT `complaints_ibfk_1` FOREIGN KEY (`c_email`) REFERENCES `customers` (`cEmail`),
  CONSTRAINT `complaints_ibfk_2` FOREIGN KEY (`admResp`) REFERENCES `admin` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table star.complaints: ~4 rows (approximately)
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` (`c_email`, `p_email`, `complaint`, `date`, `cpID`, `admResp`) VALUES
	('anne@email.com', 'clare@email.com', 'Hairdresser late for work', '2018-08-10', 1, 'admin@admin.com'),
	('louise@email.com', 'luke@email.com', 'I did not have my hair styled as I asked', '2018-09-12', 2, 'admin@admin.com'),
	('john@email.com', 'mary@email.com', 'Extremely rude hairdresser, did not smile', '2018-09-19', 3, 'admin@admin.com'),
	('james@email.com', 'peter@email.com', 'The service took too long', '2018-09-19', 4, 'admin@admin.com'),
	('carol@email.com', 'mary@email.com', 'The service took too long', '2018-09-23', 5, 'admin@admin.com');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;

-- Dumping structure for table star.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `cEmail` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`cEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table star.customers: ~8 rows (approximately)
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`fName`, `lName`, `mobile`, `cEmail`, `password`) VALUES
	('', '', '', '', 'd41d8cd98f00b204e9800998ecf8427e'),
	('Anne', 'Sullivan', '08734787', 'anne@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Brian', 'Gillen', '0859789752', 'brian@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Carol', 'Weiss', '0836784332', 'carol@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Deborah', 'Gleeson', '0837765422', 'deborah@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Hugh', 'Oleary', '0839992222', 'hugh@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Jady', 'Silva', '0834739557', 'jady.ms@outlook.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('James', 'Clark', '0836789112', 'james@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('John', 'Sullivan', '0871234567', 'john@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Louise', 'Stark', '0836789082', 'louise@email.com', '81dc9bdb52d04dc20036dbd8313ed055'),
	('Luna', 'Santos', '0986673322', 'luna@email.com', '81dc9bdb52d04dc20036dbd8313ed055');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;

-- Dumping structure for table star.providers
CREATE TABLE IF NOT EXISTS `providers` (
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `pEmail` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `location` varchar(20) NOT NULL,
  `regStatus` varchar(1) NOT NULL,
  `admResp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pEmail`),
  KEY `admResp` (`admResp`),
  CONSTRAINT `providers_ibfk_1` FOREIGN KEY (`admResp`) REFERENCES `admin` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table star.providers: ~10 rows (approximately)
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` (`fName`, `lName`, `mobile`, `pEmail`, `password`, `location`, `regStatus`, `admResp`) VALUES
	('Clare', 'Rodhes', '0879922567', 'clare@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'City Centre', 'A', 'admin@admin.com'),
	('Greg', 'South', '0837765422', 'greg@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Smithfield', 'P', 'admin@admin.com'),
	('Jady', 'Silva', '0837765422', 'jady@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'City Center', 'P', 'admin@admin.com'),
	('Jane', 'Doe', '0851234567', 'jane@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Dundrum', 'P', 'admin@admin.com'),
	('Laura', 'Oneil', '0846637799', 'laura@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Smithfield', 'P', 'admin@admin.com'),
	('Luke', 'Dalton', '0879928547', 'luke@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Smithfield', 'A', 'admin@admin.com'),
	('Mark', 'OBrien', '0836759922', 'mark@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Liffey Valley', 'P', 'admin@admin.com'),
	('Mary', 'Doherty', '0869922567', 'mary@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Phibsborough', 'A', 'admin@admin.com'),
	('Paul', 'Foster', '0861122567', 'paul@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Liffey Valley', 'A', 'admin@admin.com'),
	('Peter', 'Jonhson', '0879200547', 'peter@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Dundrum', 'A', 'admin@admin.com'),
	('Steven', 'Avery', '0876668998', 'steven@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Phibsborough', 'P', 'admin@admin.com'),
	('Yuri', 'Braga', '083986653', 'yuri@email.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Dundrum', 'P', 'admin@admin.com');
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
