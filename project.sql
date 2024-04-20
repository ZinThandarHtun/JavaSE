-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.5.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discount_allowed`
--

DROP TABLE IF EXISTS `discount_allowed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount_allowed` (
  `Date` varchar(30) DEFAULT NULL,
  `Discount` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_allowed`
--

LOCK TABLES `discount_allowed` WRITE;
/*!40000 ALTER TABLE `discount_allowed` DISABLE KEYS */;
INSERT INTO `discount_allowed` VALUES ('5/15/22','700');
/*!40000 ALTER TABLE `discount_allowed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_received`
--

DROP TABLE IF EXISTS `discount_received`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount_received` (
  `Date` varchar(30) DEFAULT NULL,
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Discount` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_received`
--

LOCK TABLES `discount_received` WRITE;
/*!40000 ALTER TABLE `discount_received` DISABLE KEYS */;
INSERT INTO `discount_received` VALUES ('5/16/22','BB','18000'),('5/15/22','ZZ','13850');
/*!40000 ALTER TABLE `discount_received` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `Date` varchar(30) DEFAULT NULL,
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Code` varchar(30) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  `Purchase_Price` varchar(30) DEFAULT NULL,
  `Amount` varchar(30) DEFAULT NULL,
  KEY `Code` (`Code`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `stock` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES ('5/16/22','AA','0001','Lipstick 01','Lipstick','10','4500','45000'),('5/16/22','AA','0201','Mascara 01','Mascara','10','1500','15000'),('5/16/22','BB','0302','Lotion 02','Lotion','10','3500','35000'),('5/16/22','BB','0101','Foundation 01','Foundation','10','8500','85000'),('5/16/22','BB','0201','Mascara 01','Mascara','10','1500','15000'),('5/15/22','ZZ','0302','Lotion 02','Lotion','10','3500','35000'),('5/15/22','ZZ','0101','Foundation 01','Foundation','10','8500','85000'),('5/15/22','ZZ','0201','Mascara 01','Mascara','10','1500','15000'),('5/14/22','BB','0102','Foundation 02','Foundation','10','8500','85000'),('5/14/22','BB','0201','Mascara 01','Mascara','10','1500','15000');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `Date` varchar(30) DEFAULT NULL,
  `Code` varchar(30) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  `Sale_Price` varchar(30) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  KEY `Code` (`Code`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `stock` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES ('5/15/22','0102','Foundation 02','Foundation','1','10000',10000),('5/15/22','0201','Mascara 01','Mascara','1','2500',2500),('5/15/22','0302','Lotion 02','Lotion','1','4200',4200),('5/15/22','0001','Lipstick 01','Lipstick','2','6000',12000),('5/16/22','0101','Foundation 01','Foundation','1','10000',10000),('5/16/22','0201','Mascara 01','Mascara','1','2500',2500),('5/16/22','0001','Lipstick 01','Lipstick','1','6000',6000);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `Date` varchar(30) DEFAULT NULL,
  `Code` varchar(30) NOT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  `Purchase_Price` varchar(30) DEFAULT NULL,
  `Sale_Price` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('5/16/22','0001','Lipstick 01','Lipstick','7','4500','6000'),('5/16/22','0101','Foundation 01','Foundation','19','8500','10000'),('5/16/22','0102','Foundation 02','Foundation','19','8500','10000'),('5/16/22','0201','Mascara 01','Mascara','43','1500','2500'),('5/16/22','0202','Mascara 02','Mascara','5','1500','2500'),('5/16/22','0302','Lotion 02','Lotion','24','3500','4200');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temppurchase`
--

DROP TABLE IF EXISTS `temppurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temppurchase` (
  `Date` varchar(30) DEFAULT NULL,
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Code` varchar(30) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  `Purchase_Price` varchar(30) DEFAULT NULL,
  `Amount` varchar(30) DEFAULT NULL,
  KEY `Code` (`Code`),
  CONSTRAINT `temppurchase_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `stock` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temppurchase`
--

LOCK TABLES `temppurchase` WRITE;
/*!40000 ALTER TABLE `temppurchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `temppurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempsale`
--

DROP TABLE IF EXISTS `tempsale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempsale` (
  `Date` varchar(30) DEFAULT NULL,
  `Code` varchar(30) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `Quantity` varchar(30) DEFAULT NULL,
  `Sale_Price` varchar(30) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  KEY `Code` (`Code`),
  CONSTRAINT `tempsale_ibfk_1` FOREIGN KEY (`Code`) REFERENCES `stock` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempsale`
--

LOCK TABLES `tempsale` WRITE;
/*!40000 ALTER TABLE `tempsale` DISABLE KEYS */;
/*!40000 ALTER TABLE `tempsale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `phoneno` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (16,'zin','zin','Female','zin@gmail.com','Taungoo','09773420407'),(17,'Thaw Thaw','Thaw',' Male','thaw@gmail.com','Yangon','09691294865'),(18,'Aye Aye','aye',' Female','aye@gmail.com','Insein','09428184947'),(19,'Khin','khin',' Female','khin@gmail.com','Yangon','0997248008');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 16:42:18
