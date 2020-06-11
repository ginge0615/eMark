-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: emart-main
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile_phone` varchar(15) DEFAULT NULL,
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES (1,'buyer1','1','ginge@sina.com1','1','2020-05-22 12:59:01'),(2,'buyer2','1','ginge@sina.com2','2','2020-05-22 13:02:33');
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_idx1` (`buyer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (10,1,2,2);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  `brief_details` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Electronic',NULL),(2,'Dress',NULL),(3,'Book',NULL),(4,'Toys',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_code` varchar(20) DEFAULT NULL,
  `percentage` decimal(3,1) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `discount_code_UNIQUE` (`discount_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'1000001',0.3,'2020-01-01 00:00:00','2020-12-31 00:00:00',NULL),(2,'1000002',0.1,'2020-01-01 00:00:00','2020-07-31 00:00:00',NULL);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sell_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `subcategory_id` int(11) DEFAULT NULL,
  `manufactur_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `price` decimal(8,1) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `sales_volume` int(11) DEFAULT NULL,
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `item-seller-idx` (`sell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,1,1,2,'A5S',1000.0,500,205,'2020-05-24 00:00:00'),(2,2,1,1,1,'Glax',1200.0,99,18,'2020-05-24 22:39:04'),(3,1,1,1,2,'A6S',1200.0,166,10,'2020-05-24 23:29:55');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_description`
--

DROP TABLE IF EXISTS `item_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `seq` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_description`
--

LOCK TABLES `item_description` WRITE;
/*!40000 ALTER TABLE `item_description` DISABLE KEYS */;
INSERT INTO `item_description` VALUES (1,1,1,'Memory:2G'),(2,1,2,'Display:1080p'),(3,1,3,'Cpu:Gaotong'),(4,2,1,'Memory:3G'),(5,3,1,'Memory:2G');
/*!40000 ALTER TABLE `item_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_picture`
--

DROP TABLE IF EXISTS `item_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `seq` int(11) DEFAULT NULL,
  `picture_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_picture_idx` (`item_id`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_picture`
--

LOCK TABLES `item_picture` WRITE;
/*!40000 ALTER TABLE `item_picture` DISABLE KEYS */;
INSERT INTO `item_picture` VALUES (1,1,1,'/picture/b769bbb2-7770-4e59-b2fc-6156cf609e0b.jpg'),(2,1,2,'/picture/56b3757e-8344-4fde-a325-883e65ed144c.jpg'),(3,2,1,'/picture/33398828-7c42-4f6e-ae4b-7d6f299509d4.jpg'),(4,3,1,'/picture/6664b1d2-d0e5-45be-9805-e83140eaca96.jpg');
/*!40000 ALTER TABLE `item_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `item_view`
--

DROP TABLE IF EXISTS `item_view`;
/*!50001 DROP VIEW IF EXISTS `item_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `item_view` AS SELECT 
 1 AS `id`,
 1 AS `item_name`,
 1 AS `seller_id`,
 1 AS `seller`,
 1 AS `cover`,
 1 AS `category_id`,
 1 AS `category`,
 1 AS `subcategory_id`,
 1 AS `subcategory`,
 1 AS `manufactur_id`,
 1 AS `manufactur`,
 1 AS `price`,
 1 AS `stock`,
 1 AS `sales_volume`,
 1 AS `tax`,
 1 AS `search_context`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `manufactur`
--

DROP TABLE IF EXISTS `manufactur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufactur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manufactur_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufactur`
--

LOCK TABLES `manufactur` WRITE;
/*!40000 ALTER TABLE `manufactur` DISABLE KEYS */;
INSERT INTO `manufactur` VALUES (1,'Samsung'),(2,'OPPO'),(3,'XIAOMI'),(4,'HUAWEI');
/*!40000 ALTER TABLE `manufactur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `price` decimal(8,1) DEFAULT NULL,
  `purchase_number` int(11) DEFAULT NULL,
  `transaction_amount` decimal(15,1) DEFAULT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_history`
--

LOCK TABLES `purchase_history` WRITE;
/*!40000 ALTER TABLE `purchase_history` DISABLE KEYS */;
INSERT INTO `purchase_history` VALUES (1,1,2,1,2,1320.0,8,10560.0,'2020-05-26 23:31:04'),(2,1,1,2,3,1320.0,1,1320.0,'2020-05-26 23:31:17'),(3,1,1,3,3,1320.0,1,1320.0,'2020-05-27 21:59:19'),(4,1,1,4,3,1320.0,1,1320.0,'2020-05-27 22:19:28'),(5,1,1,5,3,1320.0,1,1320.0,'2020-05-27 22:28:13'),(6,1,1,6,3,1320.0,1,1320.0,'2020-05-27 22:35:09'),(7,1,1,7,3,1320.0,1,1320.0,'2020-05-27 22:35:39'),(8,1,1,8,3,1320.0,1,1320.0,'2020-05-27 22:39:02'),(9,1,2,9,2,1320.0,1,1320.0,'2020-05-27 22:45:27'),(10,1,2,10,2,1320.0,1,1320.0,'2020-05-27 23:13:35'),(11,1,1,11,1,1100.0,1,1100.0,'2020-05-27 23:16:20'),(12,1,1,12,1,1100.0,1,1100.0,'2020-05-27 23:33:45'),(13,1,1,13,1,990.0,2,1980.0,'2020-05-28 22:10:49');
/*!40000 ALTER TABLE `purchase_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `GSTIN` varchar(20) DEFAULT NULL,
  `brief_about_company` varchar(300) DEFAULT NULL,
  `postal_address` varchar(100) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
INSERT INTO `seller` VALUES (1,'seller1','1','company1','12','aaaaaaaaaaaaa','address1','http://test.com','ginge@sina.com','11','2020-05-22 14:20:02'),(2,'seller2','1','company2','1',NULL,'1',NULL,'ginge@sina.com2','1','2020-05-22 14:20:02'),(3,'seller3','1','test company','12',NULL,'address',NULL,'seller3@sina.com','11','2020-05-24 14:37:09');
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subcategory_name` varchar(50) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `brief_details` varchar(300) DEFAULT NULL,
  `GST` decimal(4,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,'Mobile',1,NULL,0.100),(2,'TV',1,NULL,0.100),(3,'MP4',1,NULL,0.100),(4,'Windbreaker',2,NULL,0.100),(5,'Skirt',2,NULL,0.100),(6,'Shoes',2,NULL,0.100),(7,'Cartoon',3,NULL,0.100),(8,'Novel',3,NULL,0.100);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,2,'1','2020-05-26 23:31:04'),(2,1,1,'1','2020-05-26 23:31:17'),(3,1,1,'1','2020-05-27 21:59:19'),(4,1,1,'1','2020-05-27 22:19:28'),(5,1,1,'1','2020-05-27 22:28:13'),(6,1,1,'1','2020-05-27 22:35:09'),(7,1,1,'1','2020-05-27 22:35:39'),(8,1,1,'1','2020-05-27 22:39:02'),(9,1,2,'1','2020-05-27 22:45:27'),(10,1,2,'1','2020-05-27 23:13:35'),(11,1,1,'1','2020-05-27 23:16:20'),(12,1,1,'1','2020-05-27 23:33:45'),(13,1,1,'1','2020-05-28 22:10:49');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'emart-main'
--
/*!50003 DROP FUNCTION IF EXISTS `getCategoryName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETCATEGORYNAME`(pId int) RETURNS varchar(50) CHARSET utf8
BEGIN
	declare categoryName varchar(50);
    select category_name from category where id = pId into categoryName;
RETURN categoryName;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getCoverPicture` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETCOVERPICTURE`(pItemId int) RETURNS varchar(100) CHARSET utf8
BEGIN
	declare picturePath varchar(100);
    select picture_path from item_picture where item_id = pItemId and seq = 1 into picturePath;
RETURN picturePath;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getManufacturName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETMANUFACTURNAME`(pId int) RETURNS varchar(50) CHARSET utf8
BEGIN
	declare manufacturName varchar(50);
    select manufactur_name from manufactur where id = pId into manufacturName;
RETURN manufacturName;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getSubCategoryName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETSUBCATEGORYNAME`(pId int) RETURNS varchar(50) CHARSET utf8
BEGIN
	declare categoryName varchar(50);
    select subcategory_name from subcategory where id = pId into categoryName;
RETURN categoryName;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getTax` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETTAX`(pId int) RETURNS decimal(3,1)
BEGIN
	declare tax DECIMAL(3,1);
    select GST from subcategory where id = pId into tax;
RETURN tax;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `item_view`
--

/*!50001 DROP VIEW IF EXISTS `item_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `item_view` AS select `t`.`id` AS `id`,`t`.`item_name` AS `item_name`,`t`.`seller_id` AS `seller_id`,`t`.`seller` AS `seller`,`t`.`picture` AS `cover`,`t`.`category_id` AS `category_id`,`t`.`category` AS `category`,`t`.`subcategory_id` AS `subcategory_id`,`t`.`subcategory` AS `subcategory`,`t`.`manufactur_id` AS `manufactur_id`,`t`.`manufactur` AS `manufactur`,`t`.`price` AS `price`,`t`.`stock` AS `stock`,`t`.`sales_volume` AS `sales_volume`,(`t`.`price` * `t`.`tax`) AS `tax`,concat(`t`.`item_name`,'|',`t`.`seller`,'|',`t`.`category`,'|',`t`.`subcategory`,'|',`t`.`manufactur`) AS `search_context` from (select `emart-main`.`item`.`id` AS `id`,`emart-main`.`item`.`item_name` AS `item_name`,`emart-main`.`seller`.`id` AS `seller_id`,`emart-main`.`seller`.`username` AS `seller`,`GETCOVERPICTURE`(`emart-main`.`item`.`id`) AS `picture`,`emart-main`.`item`.`category_id` AS `category_id`,`GETCATEGORYNAME`(`emart-main`.`item`.`category_id`) AS `category`,`emart-main`.`item`.`subcategory_id` AS `subcategory_id`,`GETSUBCATEGORYNAME`(`emart-main`.`item`.`subcategory_id`) AS `subcategory`,`emart-main`.`item`.`manufactur_id` AS `manufactur_id`,`GETMANUFACTURNAME`(`emart-main`.`item`.`manufactur_id`) AS `manufactur`,`emart-main`.`item`.`price` AS `price`,`emart-main`.`item`.`stock` AS `stock`,`emart-main`.`item`.`sales_volume` AS `sales_volume`,`GETTAX`(`emart-main`.`item`.`subcategory_id`) AS `tax` from (`emart-main`.`item` join `emart-main`.`seller`) where (`emart-main`.`item`.`sell_id` = `emart-main`.`seller`.`id`)) `t` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-11 21:31:48
