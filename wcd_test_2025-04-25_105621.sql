-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wcd_test
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'mu'),(2,'liver'),(3,'real');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `blood_type` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `height` float NOT NULL,
  `name` varchar(32) NOT NULL,
  `weight` int NOT NULL,
  `club_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh60stqlv4r5dk5hp5gcwvo0n7` (`club_id`),
  CONSTRAINT `FKh60stqlv4r5dk5hp5gcwvo0n7` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`),
  CONSTRAINT `player_chk_1` CHECK (((`weight` <= 85) and (`weight` >= 55)))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (3,0x01,'A','2000-03-12 00:00:00.000000',1.71,'Test User',68,1),(4,0x01,'A','2000-09-19 00:00:00.000000',1.74,'Lê Minh Họa',63,1),(5,0x00,'B','2000-02-20 00:00:00.000000',1.79,'thắng 2',75,3),(6,0x00,'O','2002-02-20 00:00:00.000000',1.85,'Lê Minh Họa 1',85,1),(7,0x00,'A','2000-02-22 00:00:00.000000',1.75,'thắng 10',75,1),(8,0x01,'A','2011-11-11 00:00:00.000000',1.75,'Lê Minh Họa',85,1);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;

--
-- Dumping routines for database 'wcd_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-25 10:56:46
