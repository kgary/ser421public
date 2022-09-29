-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: booktown
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` int(11) NOT NULL,
  `last_name` text,
  `first_name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'George','Bush'),(2,'Ryan','Gosling'),(16,'Alcott','Louisa May'),(115,'Poe','Edgar Allen'),(1111,'Denham','Ariel'),(1212,'Worsley','John'),(1213,'Brookins','Andrew'),(1533,'Brautigan','Richard'),(1644,'Hogarth','Burne'),(1717,'Brite','Poppy Z.'),(1866,'Herbert','Frank'),(2001,'Clarke','Arthur C.'),(2031,'Brown','Margaret Wise'),(2112,'Gorey','Edward'),(4156,'King','Stephen'),(7805,'Lutz','Mark'),(7806,'Christiansen','Tom'),(15990,'Bourgeois','Paulette'),(25041,'Bianco','Margery Williams');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (156,'The Tell-Tale Heart',115,9),(190,'Little Women',16,6),(1234,'The Velveteen Rabbit',25041,3),(1501,'Goodnight Moon',2031,2),(1590,'Bartholomew and the Oobleck',1809,2),(1608,'The Cat in the Hat',1809,2),(2038,'Dynamic Anatomy',1644,0),(4267,'2001: A Space Odyssey',2001,15),(4513,'Dune',1866,15),(7808,'The Shining',4156,9),(25908,'Franklin in the Dark',15990,2),(41472,'Practical PostgreSQL',1212,4),(41473,'Programming Python',7805,4),(41477,'Learning Python',7805,4),(41478,'Perl Cookbook',7806,4);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subject` text,
  `location` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'Business','Productivity Ave'),(2,'Children\'s Books','Kids Ct'),(3,'Classics','Academic Rd'),(4,'Computers','Productivity Ave'),(5,'Cooking','Creativity St'),(6,'Drama','Main St'),(7,'Entertainment','Main St'),(8,'History','Academic Rd'),(9,'Horror','Black Raven Dr'),(10,'Mystery','Black Raven Dr'),(11,'Poetry','Sunset Dr'),(12,'Religion',NULL),(13,'Romance','Main St'),(14,'Science','Productivity Ave'),(15,'Science Fiction','Main St'),(16,'Arts','Creativity St');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'booktown'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 14:04:22
