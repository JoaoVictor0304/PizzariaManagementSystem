-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzariadatabase
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `nomecli` varchar(50) NOT NULL,
  `endcli` varchar(100) NOT NULL,
  `fonecli` varchar(50) NOT NULL,
  `emailcli` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `fonecli_UNIQUE` (`fonecli`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (5,'João Victor','Rua A, 123','94002-8922','teste@teste'),(6,'Je Min Lee','Rua B, 111','99999-9997','lee@teste.com'),(7,'Ricardo da Silva Sauro','Rua C, 2345','97751-8922','ricardinho123@teste.com'),(9,'Marcelo Godoy','Rua Palmeira, 985','90000-9995','marcelo@teste.com'),(10,'José Luiz Martins','Rua Salvador, 436','98989-9999','zeluiz@gmail.com'),(14,'Pedro Henrique','Rua Tuiuti, 6478','97458-5881','pedro@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `idIngrediente` int NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idIngrediente`),
  CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`idIngrediente`) REFERENCES `ingrediente` (`idIngrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,1.20),(2,10.00),(3,1000.00);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horarios` (
  `Nome` varchar(45) DEFAULT NULL,
  `Horario` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES ('Lucas','02-03-2024 16:45:31'),('Lucas','02-03-2024 16:46:03'),('Ana','02-03-2024 16:59:40'),('Lucas','02-03-2024 17:01:06'),('Lucas','02-03-2024 17:11:47'),('Lucas','02-03-2024 17:13:21'),('Lucas','02-03-2024 17:14:34'),('Lucas','02-03-2024 17:49:26'),('Lucas','02-03-2024 17:51:37'),('Lucas','02-03-2024 18:29:53'),('Lucas','02-03-2024 18:45:16'),('Lucas','02-03-2024 18:46:12'),('Lucas','02-03-2024 19:06:43'),('Lucas','02-03-2024 19:07:45'),('Lucas','02-03-2024 19:22:09'),('Lucas','02-03-2024 19:24:26'),('Lucas','02-03-2024 19:30:02'),('Ana','02-03-2024 19:30:19'),('Lucas','02-03-2024 21:57:30'),('Lucas','02-03-2024 22:00:51'),('Lucas','17-08-2024 10:54:36'),('joao','17-08-2024 10:56:01'),('joao','17-08-2024 11:01:07'),('Joao','17-08-2024 11:02:50'),('Joao','17-08-2024 11:12:12'),('Joao','17-08-2024 11:16:09'),('Joao','17-08-2024 11:19:26'),('Joao','17-08-2024 11:24:59'),('Joao','17-08-2024 11:27:27'),('Joao','17-08-2024 11:51:15'),('Joao','17-08-2024 11:52:26'),('Joao','17-08-2024 11:59:52'),('Lucas','17-08-2024 12:19:58'),('Joao','17-08-2024 12:29:03'),('Joao','17-08-2024 12:41:31'),('Joao','17-08-2024 12:42:26'),('Joao','17-08-2024 12:44:14'),('Joao','17-08-2024 12:44:54'),('Joao','17-08-2024 13:01:44'),('Joao','17-08-2024 13:04:56'),('Joao','17-08-2024 13:05:31'),('Joao','17-08-2024 13:06:35'),('Joao','17-08-2024 13:08:48'),('Joao','17-08-2024 13:09:30'),('Joao','17-08-2024 13:09:55'),('Joao','17-08-2024 13:12:06'),('Joao','17-08-2024 13:15:24'),('Joao','17-08-2024 13:19:07'),('Joao','17-08-2024 13:24:04'),('Joao','17-08-2024 13:24:35'),('Joao','17-08-2024 13:25:42'),('Joao','17-08-2024 13:26:16'),('Joao','17-08-2024 13:26:34'),('Joao','17-08-2024 13:27:12'),('Lucas','17-08-2024 13:28:08'),('Joao','17-08-2024 13:43:32'),('Joao','17-08-2024 13:55:48'),('Joao','17-08-2024 13:57:54'),('Joao','17-08-2024 13:59:32'),('jee','17-08-2024 14:01:31'),('Joao','17-08-2024 14:01:47'),('Joao','17-08-2024 14:04:00'),('Joao','17-08-2024 14:04:15'),('Joao','17-08-2024 14:08:04'),('Joao','17-08-2024 14:08:51'),('Joao','17-08-2024 14:40:08'),('Ana','17-08-2024 15:23:24'),('Joao','17-08-2024 16:47:20'),('Joao','17-08-2024 17:07:06'),('Joao','17-08-2024 17:09:49'),('Joao','17-08-2024 17:12:57'),('Joao','17-08-2024 17:25:28'),('Joao','17-08-2024 17:30:07'),('Joao','17-08-2024 17:32:29'),('Joao','17-08-2024 17:35:32'),('Joao','17-08-2024 17:59:36'),('Joao','17-08-2024 18:06:36'),('Lucas','17-08-2024 18:07:15'),('Joao','18-08-2024 09:41:29'),('Joao','18-08-2024 09:44:35'),('Joao','18-08-2024 09:44:48'),('Joao','18-08-2024 09:45:11'),('Joao','18-08-2024 09:45:44'),('Joao','18-08-2024 10:04:36'),('Lucas','18-08-2024 10:04:45'),('Joao','18-08-2024 10:04:56'),('Joao','18-08-2024 10:06:47'),('pedro','18-08-2024 10:07:14'),('Joao','18-08-2024 10:07:58'),('Joao','18-08-2024 10:34:31'),('Joao','18-08-2024 10:35:22'),('Joao','18-08-2024 10:38:58'),('Joao','18-08-2024 10:39:57'),('Joao','18-08-2024 10:40:21'),('Joao','18-08-2024 10:43:49'),('Joao','18-08-2024 10:46:31'),('Joao','18-08-2024 10:47:03'),('Joao','18-08-2024 10:54:40'),('Joao','18-08-2024 10:57:17'),('Joao','18-08-2024 11:03:32'),('Joao','18-08-2024 11:04:51'),('Joao','18-08-2024 11:12:38'),('Joao','18-08-2024 11:13:29'),('Joao','18-08-2024 11:13:57'),('pedro','18-08-2024 11:14:50'),('Joao','18-08-2024 11:15:47'),('Joao','18-08-2024 11:37:25'),('pedro','18-08-2024 11:37:36'),('Joao','18-08-2024 11:39:15'),('Joao','18-08-2024 11:49:58'),('Joao','18-08-2024 11:51:15'),('Joao','18-08-2024 11:51:25'),('Joao','18-08-2024 14:35:00'),('Joao','18-08-2024 14:40:28'),('Joao','18-08-2024 14:46:51'),('Joao','18-08-2024 14:50:38'),('Joao','18-08-2024 14:53:08'),('Joao','18-08-2024 14:54:47'),('Joao','18-08-2024 14:55:49'),('Joao','18-08-2024 14:56:12'),('pedro','18-08-2024 14:56:24'),('Bernardo','18-08-2024 14:57:40'),('Joao','18-08-2024 14:59:00'),('Joao','18-08-2024 15:01:22'),('Joao','18-08-2024 15:02:05'),('Joao','18-08-2024 15:08:34'),('Joao','18-08-2024 15:15:08'),('Joao','24-08-2024 09:26:28'),('Joao','24-08-2024 09:28:56'),('Joao','24-08-2024 09:29:30'),('Joao','24-08-2024 09:30:33'),('Joao','24-08-2024 10:04:49'),('Joao','24-08-2024 11:35:33'),('Joao','24-08-2024 11:37:33'),('Joao','24-08-2024 11:41:19'),('Joao','24-08-2024 11:46:50'),('Joao','24-08-2024 11:47:31'),('Joao','24-08-2024 12:03:07'),('Joao','24-08-2024 12:27:45'),('Joao','24-08-2024 12:28:48'),('Joao','24-08-2024 12:41:41'),('Joao','24-08-2024 12:42:23'),('Joao','24-08-2024 12:47:59'),('Joao','24-08-2024 12:50:41'),('Joao','24-08-2024 13:09:36'),('Joao','24-08-2024 13:11:23'),('Joao','24-08-2024 13:16:39'),('Joao','24-08-2024 13:24:52'),('Joao','24-08-2024 13:40:36'),('Joao','24-08-2024 14:17:32'),('Joao','24-08-2024 14:32:09'),('Joao','24-08-2024 14:38:34'),('Joao','24-08-2024 14:50:40'),('Joao','24-08-2024 15:16:27'),('Joao','24-08-2024 15:24:32'),('Joao','31-08-2024 10:30:01'),('Joao','31-08-2024 11:27:40'),('Lucas','31-08-2024 11:27:54'),('Joao','31-08-2024 11:28:03'),('Joao','31-08-2024 11:29:10'),('Joao','31-08-2024 11:45:27'),('Joao','31-08-2024 11:49:33'),('Joao','31-08-2024 12:10:17'),('Joao','31-08-2024 13:17:08'),('Joao','31-08-2024 13:20:56'),('Joao','31-08-2024 13:25:04'),('Joao','31-08-2024 13:28:08'),('Joao','31-08-2024 13:28:53'),('Joao','31-08-2024 13:34:37'),('Joao','31-08-2024 13:34:48'),('Lucas','31-08-2024 13:34:57'),('Joao','31-08-2024 13:36:45'),('Joao','31-08-2024 13:46:35'),('Joao','31-08-2024 13:47:44'),('Joao','31-08-2024 13:47:59'),('Joao','31-08-2024 13:48:18'),('Joao','31-08-2024 13:56:40'),('Joao','31-08-2024 14:08:28'),('Joao','31-08-2024 14:08:43'),('Joao','31-08-2024 14:09:49'),('Joao','31-08-2024 14:15:49'),('João Victor','31-08-2024 14:32:48'),('João Victor','31-08-2024 14:44:31'),('Pedro Henrique','31-08-2024 14:44:47'),('João Victor','31-08-2024 14:56:05'),('João Victor','31-08-2024 14:57:23'),('João Victor','31-08-2024 14:59:03'),('Administrador','31-08-2024 15:00:33'),('Pedro Henrique','31-08-2024 15:03:25'),('João Victor','31-08-2024 15:03:43'),('Pedro Henrique','31-08-2024 15:04:09'),('Pedro Henrique','31-08-2024 15:04:40'),('João Victor','31-08-2024 15:04:51'),('Pedro Henrique','31-08-2024 15:05:23'),('Pedro Henrique','31-08-2024 15:06:01'),('João Victor','31-08-2024 15:06:15'),('João Victor','31-08-2024 15:11:25'),('João Victor','31-08-2024 15:19:53'),('João Victor','31-08-2024 15:20:14'),('Pedro Henrique','31-08-2024 15:21:04'),('Pedro Henrique','31-08-2024 15:21:21'),('Administrador','31-08-2024 15:25:12'),('João Victor','31-08-2024 15:25:59'),('João Victor','31-08-2024 15:26:23'),('João Victor','31-08-2024 15:32:29'),('João Victor','31-08-2024 15:43:40'),('João Victor','31-08-2024 15:45:51'),('João Victor','31-08-2024 15:46:33'),('João Victor','31-08-2024 15:47:29'),('João Victor','31-08-2024 16:12:51'),('João Victor','31-08-2024 16:14:18'),('João Victor','31-08-2024 16:14:49'),('João Victor','31-08-2024 16:16:36'),('João Victor','31-08-2024 16:17:24'),('João Victor','31-08-2024 16:20:43'),('João Victor','31-08-2024 16:21:22'),('João Victor','31-08-2024 16:22:56'),('Administrador','31-08-2024 16:26:40'),('João Victor','31-08-2024 16:49:51'),('João Victor','31-08-2024 16:53:16'),('João Victor','31-08-2024 17:01:40'),('João Victor','31-08-2024 17:03:41'),('João Victor','31-08-2024 17:20:28'),('João Victor','31-08-2024 17:28:07'),('João Victor','31-08-2024 19:36:23'),('João Victor','31-08-2024 20:26:09'),('Jorge Cheirador','31-08-2024 20:28:03'),('João Victor','31-08-2024 20:28:21'),('João Victor','31-08-2024 20:47:17'),('João Victor','31-08-2024 21:02:10'),('João Victor','31-08-2024 21:14:02'),('João Victor','31-08-2024 21:21:31'),('João Victor','31-08-2024 21:24:10'),('João Victor','31-08-2024 21:24:41'),('João Victor','31-08-2024 21:25:10'),('João Victor','31-08-2024 21:27:03'),('João Victor','01-09-2024 09:51:20'),('João Victor','01-09-2024 09:52:11'),('João Victor','01-09-2024 10:23:48'),('João Victor','01-09-2024 10:29:54'),('João Victor','01-09-2024 10:30:43'),('João Victor','01-09-2024 10:31:25'),('João Victor','01-09-2024 10:37:16'),('João Victor','01-09-2024 10:50:37'),('Joao Paulo','01-09-2024 10:51:25'),('João Victor','01-09-2024 10:51:35'),('João Victor','01-09-2024 10:54:17'),('João Victor','01-09-2024 10:57:12'),('João Victor','01-09-2024 10:57:20'),('João Victor','01-09-2024 11:51:09'),('João Victor','01-09-2024 12:24:58'),('João Victor','01-09-2024 12:27:12'),('João Victor','01-09-2024 12:33:27'),('João Victor','01-09-2024 12:36:37'),('João Victor','01-09-2024 12:37:40'),('João Victor','01-09-2024 12:38:36'),('João Victor','01-09-2024 12:43:18'),('João Victor','01-09-2024 12:45:43'),('João Victor','01-09-2024 12:46:03'),('João Victor','01-09-2024 12:52:52'),('João Victor','01-09-2024 12:59:10'),('João Victor','01-09-2024 13:01:53'),('João Victor','01-09-2024 13:03:12'),('João Victor','01-09-2024 13:07:05'),('João Victor','01-09-2024 13:18:00'),('João Victor','01-09-2024 13:19:29'),('João Victor','01-09-2024 13:29:08'),('João Victor','01-09-2024 13:30:22'),('João Victor','01-09-2024 13:36:35'),('João Victor','01-09-2024 13:38:30'),('João Victor','01-09-2024 13:44:38'),('João Victor','01-09-2024 13:46:44'),('João Victor','01-09-2024 13:48:59'),('João Victor','01-09-2024 13:50:25'),('João Victor','01-09-2024 13:57:22'),('João Victor','01-09-2024 14:00:35'),('João Victor','01-09-2024 14:09:43'),('João Victor','01-09-2024 14:10:15'),('João Victor','01-09-2024 14:10:45'),('João Victor','01-09-2024 14:11:42'),('João Victor','01-09-2024 15:19:29'),('João Victor','01-09-2024 15:24:37'),('Pedro Henrique','01-09-2024 15:30:19'),('João Victor','01-09-2024 15:30:38'),('João Victor','01-09-2024 15:33:25'),('João Victor','01-09-2024 15:34:20'),('João Victor','01-09-2024 15:36:53'),('João Victor','01-09-2024 15:37:01'),('João Victor','01-09-2024 22:28:18'),('João Victor','01-09-2024 22:29:54'),('João Victor','01-09-2024 22:30:10'),('Marcos','01-09-2024 22:34:06'),('João Victor','01-09-2024 22:34:22'),('Marcos Avelino','01-09-2024 22:35:04'),('João Victor','01-09-2024 22:35:36'),('João Victor','01-09-2024 22:39:17'),('Administrador','01-09-2024 22:40:34'),('João Victor','04-09-2024 13:07:53'),('João Victor','07-09-2024 07:14:18'),('João Victor','07-09-2024 11:15:30'),('João Victor','07-09-2024 11:16:31'),('João Victor','07-09-2024 11:17:00'),('João Victor','07-09-2024 11:17:57'),('João Victor','07-09-2024 11:43:58'),('João Victor','07-09-2024 11:44:57'),('João Victor','07-09-2024 11:47:16'),('João Victor','07-09-2024 11:59:05'),('João Victor','07-09-2024 11:59:37'),('João Victor','07-09-2024 12:04:26'),('João Victor','07-09-2024 12:06:11'),('João Victor','07-09-2024 12:14:12'),('João Victor','07-09-2024 12:15:52'),('João Victor','07-09-2024 12:21:28'),('João Victor','07-09-2024 12:26:57'),('João Victor','07-09-2024 12:33:29'),('João Victor','07-09-2024 13:14:33'),('João Victor','07-09-2024 17:28:32'),('Pedro Henrique','07-09-2024 17:28:42'),('João Victor','07-09-2024 17:29:01'),('João Victor','07-09-2024 17:33:49'),('Pedro Henrique','07-09-2024 17:33:59'),('Pedro Henrique','07-09-2024 17:34:29'),('Pedro Henrique','07-09-2024 17:35:01'),('João Victor','07-09-2024 17:35:51'),('João Victor','07-09-2024 17:58:28'),('João Victor','07-09-2024 18:00:06'),('João Victor','07-09-2024 18:11:55'),('João Victor','07-09-2024 18:26:47'),('João Victor','07-09-2024 18:28:00'),('João Victor','07-09-2024 18:29:10'),('João Victor','07-09-2024 19:39:58'),('João Victor','07-09-2024 19:40:55'),('João Victor','07-09-2024 19:53:42'),('João Victor','07-09-2024 19:59:57'),('João Victor','07-09-2024 20:01:01'),('João Victor','07-09-2024 20:02:14'),('João Victor','07-09-2024 20:04:00'),('João Victor','07-09-2024 20:06:32'),('João Victor','07-09-2024 20:13:13'),('João Victor','07-09-2024 20:15:28'),('João Victor','07-09-2024 20:28:45'),('João Victor','07-09-2024 20:29:13'),('João Victor','07-09-2024 20:45:33');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingrediente` (
  `idIngrediente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `unidadeMedida` varchar(10) NOT NULL,
  PRIMARY KEY (`idIngrediente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` VALUES (1,'Mussarela','g'),(2,'Molho de tomate','l'),(3,'Azeitona','unidade'),(4,'Pepperoni','g'),(5,'Brócolis','g'),(6,'Massa','kg');
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itempedido`
--

DROP TABLE IF EXISTS `itempedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itempedido` (
  `idItemPedido` int NOT NULL AUTO_INCREMENT,
  `idPedido` int NOT NULL,
  `idProduto` int NOT NULL,
  `tamanho` enum('Brotinho','Grande') NOT NULL,
  `quantidade` int NOT NULL,
  `precoUnitario` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) GENERATED ALWAYS AS ((`quantidade` * `precoUnitario`)) STORED,
  PRIMARY KEY (`idItemPedido`),
  KEY `idPedido` (`idPedido`),
  KEY `idProduto` (`idProduto`),
  CONSTRAINT `itempedido_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `tbpedido` (`idPedido`),
  CONSTRAINT `itempedido_ibfk_2` FOREIGN KEY (`idProduto`) REFERENCES `tbproduto` (`idProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itempedido`
--

LOCK TABLES `itempedido` WRITE;
/*!40000 ALTER TABLE `itempedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `itempedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas` (
  `idMesa` int NOT NULL,
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas`
--

LOCK TABLES `mesas` WRITE;
/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idPedidos` int NOT NULL AUTO_INCREMENT,
  `Comida` varchar(45) NOT NULL,
  `Mesa` int NOT NULL,
  PRIMARY KEY (`idPedidos`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpedido`
--

DROP TABLE IF EXISTS `tbpedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbpedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `datapedido` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tipoPedido` enum('Balcão','Delivery') NOT NULL,
  `status` enum('Aguardando','Em Preparação','Pronto','Entregue') NOT NULL,
  `idcliente` int DEFAULT NULL,
  `idUsuarios` int DEFAULT NULL,
  `metodoPagamento` enum('Dinheiro','Cartão','Pix') NOT NULL,
  `observacoes` text,
  PRIMARY KEY (`idPedido`),
  KEY `idcliente` (`idcliente`),
  KEY `idUsuarios` (`idUsuarios`),
  CONSTRAINT `tbpedido_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `tbpedido_ibfk_2` FOREIGN KEY (`idUsuarios`) REFERENCES `usuarios` (`idUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpedido`
--

LOCK TABLES `tbpedido` WRITE;
/*!40000 ALTER TABLE `tbpedido` DISABLE KEYS */;
INSERT INTO `tbpedido` VALUES (1,'2024-09-07 11:44:05','Delivery','Aguardando',5,1,'Cartão','');
/*!40000 ALTER TABLE `tbpedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbproduto`
--

DROP TABLE IF EXISTS `tbproduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbproduto` (
  `idProduto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` text,
  `precoBrotinho` decimal(10,2) NOT NULL,
  `precoGrande` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbproduto`
--

LOCK TABLES `tbproduto` WRITE;
/*!40000 ALTER TABLE `tbproduto` DISABLE KEYS */;
INSERT INTO `tbproduto` VALUES (1,'Mussarela','Pizza de mussarela com molho de tomate e azeitonas',30.00,60.00),(2,'Brócolis','Pizza de brócolis, molho de tomate e azeitonas',30.00,60.00),(3,'Pepperoni','Pizza de pepperoni, molho de tomate e mussarela',35.00,65.00);
/*!40000 ALTER TABLE `tbproduto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbprodutoingrediente`
--

DROP TABLE IF EXISTS `tbprodutoingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbprodutoingrediente` (
  `idProduto` int NOT NULL,
  `idIngrediente` int NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idProduto`,`idIngrediente`),
  KEY `idIngrediente` (`idIngrediente`),
  CONSTRAINT `tbprodutoingrediente_ibfk_1` FOREIGN KEY (`idProduto`) REFERENCES `tbproduto` (`idProduto`),
  CONSTRAINT `tbprodutoingrediente_ibfk_2` FOREIGN KEY (`idIngrediente`) REFERENCES `ingrediente` (`idIngrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbprodutoingrediente`
--

LOCK TABLES `tbprodutoingrediente` WRITE;
/*!40000 ALTER TABLE `tbprodutoingrediente` DISABLE KEYS */;
INSERT INTO `tbprodutoingrediente` VALUES (1,1,0.20),(1,2,0.10),(1,3,5.00),(2,1,0.20),(2,3,8.00),(3,1,0.20),(3,2,0.20),(3,3,8.00);
/*!40000 ALTER TABLE `tbprodutoingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'João Victor','joao','123','admin'),(2,'Administrador','admin','admin','admin'),(3,'Pedro Henrique','pedro','123','user');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-07 21:04:04
