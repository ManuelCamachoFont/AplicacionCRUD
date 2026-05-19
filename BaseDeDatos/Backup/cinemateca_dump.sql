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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actores`
--

LOCK TABLES `actores` WRITE;
/*!40000 ALTER TABLE `actores` DISABLE KEYS */;
INSERT INTO `actores` VALUES (1,'Leonardo','DiCaprio',15500.00),(2,'Cillian','Murphy',12000.00),(3,'Brad','Pitt',14500.00),(4,'Robert','Downey Jr.',15000.00),(5,'Margot','Robbie',11000.00),(6,'Timothée','Chalamet',7500.00),(7,'Ryan','Gosling',9500.00),(8,'Florence','Pugh',6800.00),(9,'Matt','Damon',10500.00),(10,'Zendaya','Maree',8000.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directores`
--

LOCK TABLES `directores` WRITE;
/*!40000 ALTER TABLE `directores` DISABLE KEYS */;
INSERT INTO `directores` VALUES (1,'Christopher','Nolan','Británica'),(2,'Quentin','Tarantino','Estadounidense'),(3,'Martin','Scorsese','Estadounidense'),(4,'Steven','Spielberg','Estadounidense'),(5,'Denis','Villeneuve','Canadiense'),(6,'Greta','Gerwig','Estadounidense'),(7,'Guillermo','del Toro','Mexicana'),(8,'James','Cameron','Canadiense'),(9,'Ridley','Scott','Británica'),(10,'Bong','Joon-ho','Surcoreana');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'Inception','Ciencia Ficción','2010-07-16',1),(2,'Oppenheimer','Drama / Histórico','2023-07-21',1),(3,'Once Upon a Time in Hollywood','Drama / Comedia','2019-07-26',2),(4,'The Wolf of Wall Street','Biografía / Comedia','2013-12-25',3),(5,'Saving Private Ryan','Bélico / Drama','1998-07-24',4),(6,'Dune: Part Two','Ciencia Ficción / Épico','2024-03-01',5),(7,'Barbie','Comedia / Fantasía','2023-07-21',6),(8,'Avatar: The Way of Water','Ciencia Ficción / Acción','2022-12-16',8),(9,'Gladiator','Acción / Épico','2000-05-05',9),(10,'Parasite','Thiller / Drama','2019-05-30',10);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas_actores`
--

LOCK TABLES `peliculas_actores` WRITE;
/*!40000 ALTER TABLE `peliculas_actores` DISABLE KEYS */;
INSERT INTO `peliculas_actores` VALUES (1,1,1),(2,2,2),(3,2,4),(4,2,8),(5,2,9),(6,3,1),(7,3,3),(8,3,5),(9,4,1),(10,7,5);
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
INSERT INTO `usuarios` VALUES (1,'Administrador','ab20f6e3ee225e70bdeb0363708de19b44a7e1b6d091599a07007cab3929a554','admin'),(2,'Usuario','b1bbef3b6a1cb6f98a451620e6b59f6329e17fa692b48aa148816c71ef08798f','basico');
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

-- Dump completed on 2026-05-19 19:46:20
