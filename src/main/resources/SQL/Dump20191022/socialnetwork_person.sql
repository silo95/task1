-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: socialnetwork
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `idPerson` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(50) NOT NULL,
  `password` char(64) NOT NULL,
  PRIMARY KEY (`idPerson`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Mario','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(2,'Antonio','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(3,'Gianmarco','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(4,'Tiziana','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(5,'Erica','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(6,'Franco','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(7,'Filippo','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(8,'Sara','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(9,'Lorenzo','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2'),(10,'Matteo','B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-22 16:54:27
