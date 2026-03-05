CREATE DATABASE  IF NOT EXISTS `p_cine` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `p_cine`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: p_cine
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `actores`
--

DROP TABLE IF EXISTS `actores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actores` (
  `idActor` int NOT NULL AUTO_INCREMENT,
  `nombreActor` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `apellidosActor` varchar(120) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `salarioActor` decimal(9,2) NOT NULL,
  PRIMARY KEY (`idActor`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actores`
--

LOCK TABLES `actores` WRITE;
/*!40000 ALTER TABLE `actores` DISABLE KEYS */;
INSERT INTO `actores` VALUES (1,'Carlos Cuarto','Parera ',1283.85),(3,'Manuel Fernandez','Romero ',4234234.00),(4,'Josefina Manuela','Postigo Romero',3100.23),(5,'Belen ','Mora',400.44),(6,'Javier Carlos','Hernandez',800.55),(7,'Jesus María','Rodríguez de la Fuente',4000.00),(8,'Penélope','Cruz',4800.96);
/*!40000 ALTER TABLE `actores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directores`
--

DROP TABLE IF EXISTS `directores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `directores` (
  `idDirector` int NOT NULL AUTO_INCREMENT,
  `nombreDirector` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `apellidosDirector` varchar(120) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `nacionalidadDirector` varchar(45) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idDirector`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directores`
--

LOCK TABLES `directores` WRITE;
/*!40000 ALTER TABLE `directores` DISABLE KEYS */;
INSERT INTO `directores` VALUES (2,'Alba ','Duino','Italiana'),(3,'Javi','Del Olmo Ordoñez','Español'),(5,'Javier','Limón Aguado','Peruano'),(6,'Pedro','Gonzalez','Venezolano');
/*!40000 ALTER TABLE `directores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas` (
  `idPelicula` int NOT NULL AUTO_INCREMENT,
  `tituloPelicula` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `generoPelicula` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `fechaEstrenoPelicula` date NOT NULL,
  `idDirectorFK` int NOT NULL,
  PRIMARY KEY (`idPelicula`),
  KEY `idDirectorFK` (`idDirectorFK`),
  CONSTRAINT `peliculas_ibfk_1` FOREIGN KEY (`idDirectorFK`) REFERENCES `directores` (`idDirector`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (2,'Tiburón','Romántica','1987-10-05',5),(3,'Halloween','Drama','1987-12-07',5),(4,'Scream','Drama','2003-05-24',6);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas_actores`
--

DROP TABLE IF EXISTS `peliculas_actores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas_actores` (
  `idPeliculasActores` int NOT NULL AUTO_INCREMENT,
  `idPeliculaFK` int NOT NULL,
  `idActorFK` int NOT NULL,
  PRIMARY KEY (`idPeliculasActores`),
  KEY `idPeliculaFK` (`idPeliculaFK`),
  KEY `idActorFK` (`idActorFK`),
  CONSTRAINT `peliculas_actores_ibfk_1` FOREIGN KEY (`idPeliculaFK`) REFERENCES `peliculas` (`idPelicula`),
  CONSTRAINT `peliculas_actores_ibfk_2` FOREIGN KEY (`idActorFK`) REFERENCES `actores` (`idActor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas_actores`
--

LOCK TABLES `peliculas_actores` WRITE;
/*!40000 ALTER TABLE `peliculas_actores` DISABLE KEYS */;
/*!40000 ALTER TABLE `peliculas_actores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `claveUsuario` varchar(256) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `tipoUsuario` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador','ab20f6e3ee225e70bdeb0363708de19b44a7e1b6d091599a07007cab3929a554','admin'),(2,'Usuario','ab20f6e3ee225e70bdeb0363708de19b44a7e1b6d091599a07007cab3929a554','basico');
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

-- Dump completed on 2026-03-05 13:05:43
