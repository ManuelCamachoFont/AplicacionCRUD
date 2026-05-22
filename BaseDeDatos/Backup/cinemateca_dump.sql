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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actores`
--

LOCK TABLES `actores` WRITE;
/*!40000 ALTER TABLE `actores` DISABLE KEYS */;
INSERT INTO `actores` VALUES (1,'Leonardo','DiCaprio',85000.00),(2,'Samuel','L. Jackson',62000.00),(3,'Sam','Neill',25000.00),(4,'Matt','Damon',58000.00),(5,'Timothée','Chalamet',45000.00),(6,'Rumi','Hiiragi',12000.00),(7,'Antonio','Banderas',35000.00);
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
  `nacionalidadDirector` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`idDirector`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directores`
--

LOCK TABLES `directores` WRITE;
/*!40000 ALTER TABLE `directores` DISABLE KEYS */;
INSERT INTO `directores` VALUES (1,'Christopher','Nolan','Británica'),(2,'Quentin','Tarantino','Estadounidense'),(3,'Steven','Spielberg','Estadounidense'),(4,'Martin','Scorsese','Estadounidense'),(5,'Denis','Villeneuve','Canadiense'),(6,'Hayao','Miyazaki','Japonesa'),(7,'Pedro','Almodóvar','Española');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'Inception','Ciencia Ficción','2010-07-16',1),(2,'Pulp Fiction','Crimen','1994-10-14',2),(3,'Jurassic Park','Aventura','1993-06-11',3),(4,'The Departed','Suspense','2006-10-06',4),(5,'Dune: Part Two','Ciencia Ficción','2024-03-01',5),(6,'El viaje de Chihiro','Animación','2001-07-20',6),(7,'Dolor y gloria','Drama','2019-03-22',7);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas_actores`
--

DROP TABLE IF EXISTS `peliculas_actores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas_actores` (
  `idPeliculaActor` int NOT NULL AUTO_INCREMENT,
  `idPeliculaFK` int NOT NULL,
  `idActorFK` int NOT NULL,
  PRIMARY KEY (`idPeliculaActor`),
  KEY `idPeliculaFK` (`idPeliculaFK`),
  KEY `idActorFK` (`idActorFK`),
  CONSTRAINT `peliculas_actores_ibfk_1` FOREIGN KEY (`idPeliculaFK`) REFERENCES `peliculas` (`idPelicula`),
  CONSTRAINT `peliculas_actores_ibfk_2` FOREIGN KEY (`idActorFK`) REFERENCES `actores` (`idActor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas_actores`
--

LOCK TABLES `peliculas_actores` WRITE;
/*!40000 ALTER TABLE `peliculas_actores` DISABLE KEYS */;
INSERT INTO `peliculas_actores` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7);
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

-- Dump completed on 2026-05-22  9:59:46
