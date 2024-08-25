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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (5,'Joao Victor','Rua A, 123','94002-8922','teste@teste'),(6,'Je Min Lee','Rua B, 111','99999-9998','lee@teste.com'),(7,'Ricardo da Silva Sauro','Rua C, 2345','97751-8922','ricardinho123@teste.com'),(9,'Marcelo Godoy','Rua Palmeira, 985','90000-9995','marcelo@teste.com'),(10,'José Luiz Martins','Rua Salvador, 436','98989-9999','zeluiz@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `idEstoque` int NOT NULL AUTO_INCREMENT,
  `Quantidade` int NOT NULL,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstoque`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,86,'Massas'),(2,88,'Molho-Tomate'),(3,97,'Calabresas'),(4,99,'Queijo'),(5,99,'Tomate-fatia'),(6,97,'Frango'),(7,97,'Catupiry'),(8,99,'Nutella'),(9,99,'brocolis'),(10,99,'Mussarela'),(11,99,'Pepperoni');
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
INSERT INTO `horarios` VALUES ('Lucas','02-03-2024 16:45:31'),('Lucas','02-03-2024 16:46:03'),('Ana','02-03-2024 16:59:40'),('Lucas','02-03-2024 17:01:06'),('Lucas','02-03-2024 17:11:47'),('Lucas','02-03-2024 17:13:21'),('Lucas','02-03-2024 17:14:34'),('Lucas','02-03-2024 17:49:26'),('Lucas','02-03-2024 17:51:37'),('Lucas','02-03-2024 18:29:53'),('Lucas','02-03-2024 18:45:16'),('Lucas','02-03-2024 18:46:12'),('Lucas','02-03-2024 19:06:43'),('Lucas','02-03-2024 19:07:45'),('Lucas','02-03-2024 19:22:09'),('Lucas','02-03-2024 19:24:26'),('Lucas','02-03-2024 19:30:02'),('Ana','02-03-2024 19:30:19'),('Lucas','02-03-2024 21:57:30'),('Lucas','02-03-2024 22:00:51'),('Lucas','17-08-2024 10:54:36'),('joao','17-08-2024 10:56:01'),('joao','17-08-2024 11:01:07'),('Joao','17-08-2024 11:02:50'),('Joao','17-08-2024 11:12:12'),('Joao','17-08-2024 11:16:09'),('Joao','17-08-2024 11:19:26'),('Joao','17-08-2024 11:24:59'),('Joao','17-08-2024 11:27:27'),('Joao','17-08-2024 11:51:15'),('Joao','17-08-2024 11:52:26'),('Joao','17-08-2024 11:59:52'),('Lucas','17-08-2024 12:19:58'),('Joao','17-08-2024 12:29:03'),('Joao','17-08-2024 12:41:31'),('Joao','17-08-2024 12:42:26'),('Joao','17-08-2024 12:44:14'),('Joao','17-08-2024 12:44:54'),('Joao','17-08-2024 13:01:44'),('Joao','17-08-2024 13:04:56'),('Joao','17-08-2024 13:05:31'),('Joao','17-08-2024 13:06:35'),('Joao','17-08-2024 13:08:48'),('Joao','17-08-2024 13:09:30'),('Joao','17-08-2024 13:09:55'),('Joao','17-08-2024 13:12:06'),('Joao','17-08-2024 13:15:24'),('Joao','17-08-2024 13:19:07'),('Joao','17-08-2024 13:24:04'),('Joao','17-08-2024 13:24:35'),('Joao','17-08-2024 13:25:42'),('Joao','17-08-2024 13:26:16'),('Joao','17-08-2024 13:26:34'),('Joao','17-08-2024 13:27:12'),('Lucas','17-08-2024 13:28:08'),('Joao','17-08-2024 13:43:32'),('Joao','17-08-2024 13:55:48'),('Joao','17-08-2024 13:57:54'),('Joao','17-08-2024 13:59:32'),('jee','17-08-2024 14:01:31'),('Joao','17-08-2024 14:01:47'),('Joao','17-08-2024 14:04:00'),('Joao','17-08-2024 14:04:15'),('Joao','17-08-2024 14:08:04'),('Joao','17-08-2024 14:08:51'),('Joao','17-08-2024 14:40:08'),('Ana','17-08-2024 15:23:24'),('Joao','17-08-2024 16:47:20'),('Joao','17-08-2024 17:07:06'),('Joao','17-08-2024 17:09:49'),('Joao','17-08-2024 17:12:57'),('Joao','17-08-2024 17:25:28'),('Joao','17-08-2024 17:30:07'),('Joao','17-08-2024 17:32:29'),('Joao','17-08-2024 17:35:32'),('Joao','17-08-2024 17:59:36'),('Joao','17-08-2024 18:06:36'),('Lucas','17-08-2024 18:07:15'),('Joao','18-08-2024 09:41:29'),('Joao','18-08-2024 09:44:35'),('Joao','18-08-2024 09:44:48'),('Joao','18-08-2024 09:45:11'),('Joao','18-08-2024 09:45:44'),('Joao','18-08-2024 10:04:36'),('Lucas','18-08-2024 10:04:45'),('Joao','18-08-2024 10:04:56'),('Joao','18-08-2024 10:06:47'),('pedro','18-08-2024 10:07:14'),('Joao','18-08-2024 10:07:58'),('Joao','18-08-2024 10:34:31'),('Joao','18-08-2024 10:35:22'),('Joao','18-08-2024 10:38:58'),('Joao','18-08-2024 10:39:57'),('Joao','18-08-2024 10:40:21'),('Joao','18-08-2024 10:43:49'),('Joao','18-08-2024 10:46:31'),('Joao','18-08-2024 10:47:03'),('Joao','18-08-2024 10:54:40'),('Joao','18-08-2024 10:57:17'),('Joao','18-08-2024 11:03:32'),('Joao','18-08-2024 11:04:51'),('Joao','18-08-2024 11:12:38'),('Joao','18-08-2024 11:13:29'),('Joao','18-08-2024 11:13:57'),('pedro','18-08-2024 11:14:50'),('Joao','18-08-2024 11:15:47'),('Joao','18-08-2024 11:37:25'),('pedro','18-08-2024 11:37:36'),('Joao','18-08-2024 11:39:15'),('Joao','18-08-2024 11:49:58'),('Joao','18-08-2024 11:51:15'),('Joao','18-08-2024 11:51:25'),('Joao','18-08-2024 14:35:00'),('Joao','18-08-2024 14:40:28'),('Joao','18-08-2024 14:46:51'),('Joao','18-08-2024 14:50:38'),('Joao','18-08-2024 14:53:08'),('Joao','18-08-2024 14:54:47'),('Joao','18-08-2024 14:55:49'),('Joao','18-08-2024 14:56:12'),('pedro','18-08-2024 14:56:24'),('Bernardo','18-08-2024 14:57:40'),('Joao','18-08-2024 14:59:00'),('Joao','18-08-2024 15:01:22'),('Joao','18-08-2024 15:02:05'),('Joao','18-08-2024 15:08:34'),('Joao','18-08-2024 15:15:08'),('Joao','24-08-2024 09:26:28'),('Joao','24-08-2024 09:28:56'),('Joao','24-08-2024 09:29:30'),('Joao','24-08-2024 09:30:33'),('Joao','24-08-2024 10:04:49'),('Joao','24-08-2024 11:35:33'),('Joao','24-08-2024 11:37:33'),('Joao','24-08-2024 11:41:19'),('Joao','24-08-2024 11:46:50'),('Joao','24-08-2024 11:47:31'),('Joao','24-08-2024 12:03:07'),('Joao','24-08-2024 12:27:45'),('Joao','24-08-2024 12:28:48'),('Joao','24-08-2024 12:41:41'),('Joao','24-08-2024 12:42:23'),('Joao','24-08-2024 12:47:59'),('Joao','24-08-2024 12:50:41'),('Joao','24-08-2024 13:09:36'),('Joao','24-08-2024 13:11:23'),('Joao','24-08-2024 13:16:39'),('Joao','24-08-2024 13:24:52'),('Joao','24-08-2024 13:40:36'),('Joao','24-08-2024 14:17:32'),('Joao','24-08-2024 14:32:09'),('Joao','24-08-2024 14:38:34'),('Joao','24-08-2024 14:50:40'),('Joao','24-08-2024 15:16:27'),('Joao','24-08-2024 15:24:32');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receitas`
--

DROP TABLE IF EXISTS `receitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receitas` (
  `Nome` varchar(45) DEFAULT NULL,
  `Topping1` varchar(45) DEFAULT NULL,
  `Topping2` varchar(45) DEFAULT NULL,
  `molho` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receitas`
--

LOCK TABLES `receitas` WRITE;
/*!40000 ALTER TABLE `receitas` DISABLE KEYS */;
INSERT INTO `receitas` VALUES ('Calabresa','Calabresas','Nenhum','Sim'),('Marguerita','Queijo','Tomate-fatia','Sim'),('Frango','Frango','Catupiry','Sim'),('Nutella','Nutella','Morangos','Não'),('Pepperoni','Pepperoni','Mussarela','Sim'),('brocolis','brocolis','','Sim');
/*!40000 ALTER TABLE `receitas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Senha` varchar(45) NOT NULL,
  `Cargo` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Lucas','123','Admin'),(2,'Robertinho','321','Funcionário'),(5,'Ana','321','Funcionário'),(7,'Joao','123','Admin'),(9,'pedro','1234','Admin');
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

-- Dump completed on 2024-08-25 12:53:19
