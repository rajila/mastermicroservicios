-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: localhost    Database: peliculadb
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `tbl_actor`
--

DROP TABLE IF EXISTS `tbl_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_actor` (
  `id` int NOT NULL,
  `fechanacimiento` datetime NOT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_actor_pais_idx` (`id_pais`),
  CONSTRAINT `fk_actor_pais` FOREIGN KEY (`id_pais`) REFERENCES `tbl_pais` (`id`),
  CONSTRAINT `fk_actor_usuario` FOREIGN KEY (`id`) REFERENCES `tbl_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_actor`
--

LOCK TABLES `tbl_actor` WRITE;
/*!40000 ALTER TABLE `tbl_actor` DISABLE KEYS */;
INSERT INTO `tbl_actor` VALUES (7,'1963-12-18 00:00:00',6),(8,'1986-06-12 00:00:00',1),(9,'1988-10-12 00:00:00',1),(10,'2003-04-22 00:00:00',5),(11,'1974-04-02 00:00:00',2),(15,'1965-04-03 00:00:00',6),(16,'1981-06-13 00:00:00',6),(17,'1956-12-17 00:00:00',6);
/*!40000 ALTER TABLE `tbl_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_director`
--

DROP TABLE IF EXISTS `tbl_director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_director` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_director_usuario` FOREIGN KEY (`id`) REFERENCES `tbl_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_director`
--

LOCK TABLES `tbl_director` WRITE;
/*!40000 ALTER TABLE `tbl_director` DISABLE KEYS */;
INSERT INTO `tbl_director` VALUES (1),(2),(3),(4),(5),(6),(12);
/*!40000 ALTER TABLE `tbl_director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_genero`
--

DROP TABLE IF EXISTS `tbl_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_genero` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_genero`
--

LOCK TABLES `tbl_genero` WRITE;
/*!40000 ALTER TABLE `tbl_genero` DISABLE KEYS */;
INSERT INTO `tbl_genero` VALUES (1,'CF','Ciencia Ficción'),(2,'AV','Aventuras'),(3,'ACC','Acción'),(4,'TRR','Terror'),(5,'COM','Comedia'),(6,'DR','Drama'),(7,'DOC','Documentales');
/*!40000 ALTER TABLE `tbl_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pais`
--

DROP TABLE IF EXISTS `tbl_pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pais`
--

LOCK TABLES `tbl_pais` WRITE;
/*!40000 ALTER TABLE `tbl_pais` DISABLE KEYS */;
INSERT INTO `tbl_pais` VALUES (1,'ES','España'),(2,'AR','Argentina'),(3,'EC','Ecuador'),(4,'FR','Francia'),(5,'IT','Italia'),(6,'US','Estados Unidos de América');
/*!40000 ALTER TABLE `tbl_pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pelicula`
--

DROP TABLE IF EXISTS `tbl_pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pelicula` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(250) NOT NULL,
  `anio` int NOT NULL,
  `duracion` int NOT NULL,
  `sinopsis` varchar(1000) NOT NULL,
  `portada` varchar(250) NOT NULL,
  `id_director` int NOT NULL,
  `id_pais` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pelicula_director_idx` (`id_director`),
  KEY `fk_pelicula_pais_idx` (`id_pais`),
  CONSTRAINT `fk_pelicula_director` FOREIGN KEY (`id_director`) REFERENCES `tbl_director` (`id`),
  CONSTRAINT `fk_pelicula_pais` FOREIGN KEY (`id_pais`) REFERENCES `tbl_pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pelicula`
--

LOCK TABLES `tbl_pelicula` WRITE;
/*!40000 ALTER TABLE `tbl_pelicula` DISABLE KEYS */;
INSERT INTO `tbl_pelicula` VALUES (1,'Avengers',2012,135,'Cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial, Nick Fury, director de la Agencia SHIELD, decide reclutar a un equipo para salvar al mundo de un desastre casi seguro. Adaptación del cómic de Marvel \"Los Vengadores\", el legendario grupo de superhéroes formado por Ironman, Hulk, Thor y el Capitán América entre otros. (FILMAFFINITY)','f7f24ac7-afbb-40c8-995f-4718955a18bd_avengers.png',12,6),(2,'America Pie',2009,93,'A Rob (Bug Hall), Nathan (Kevin M. Horton) y Lube (Brandon Hardesty) son tres amigos del instituto East Great Falls que se proponen como misión conseguir el éxito entre las chicas. Tras varios intentos fallidos, accidentalmente descubren oculta en una biblioteca la “Biblia del Sexo”, una legendaria guía sobre la seducción que contiene multitud de secretos para el éxito sexual','997dbd83-f436-4d26-9038-368bb24c3272_images.jpeg',1,6),(3,'Playa',2008,120,'Impulsado por el deseo de vivir experiencias y emociones apasionantes, Richard (Leonardo DiCaprio), un joven mochilero, viaja a Thailandia. En Bangkok se aloja en un hotel de mala muerte, donde conoce a una pareja de franceses, Étienne','02806ae8-01b9-49cf-bcde-7ea3b0b4975d_20090364.jpg',3,5);
/*!40000 ALTER TABLE `tbl_pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pelicula_actor`
--

DROP TABLE IF EXISTS `tbl_pelicula_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pelicula_actor` (
  `id_actor` int NOT NULL,
  `id_pelicula` int NOT NULL,
  PRIMARY KEY (`id_actor`,`id_pelicula`),
  KEY `fk_peliculaactor_pelicula_idx` (`id_pelicula`),
  CONSTRAINT `fk_peliculaactor_actor` FOREIGN KEY (`id_actor`) REFERENCES `tbl_actor` (`id`),
  CONSTRAINT `fk_peliculaactor_pelicula` FOREIGN KEY (`id_pelicula`) REFERENCES `tbl_pelicula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pelicula_actor`
--

LOCK TABLES `tbl_pelicula_actor` WRITE;
/*!40000 ALTER TABLE `tbl_pelicula_actor` DISABLE KEYS */;
INSERT INTO `tbl_pelicula_actor` VALUES (15,1),(16,1),(17,2),(8,3),(9,3);
/*!40000 ALTER TABLE `tbl_pelicula_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pelicula_genero`
--

DROP TABLE IF EXISTS `tbl_pelicula_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pelicula_genero` (
  `id_pelicula` int NOT NULL,
  `id_genero` int NOT NULL,
  PRIMARY KEY (`id_pelicula`,`id_genero`),
  KEY `fk_peliculagenero_genero_idx` (`id_genero`),
  CONSTRAINT `fk_peliculagenero_genero` FOREIGN KEY (`id_genero`) REFERENCES `tbl_genero` (`id`),
  CONSTRAINT `fk_peliculagenero_pelicula` FOREIGN KEY (`id_pelicula`) REFERENCES `tbl_pelicula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pelicula_genero`
--

LOCK TABLES `tbl_pelicula_genero` WRITE;
/*!40000 ALTER TABLE `tbl_pelicula_genero` DISABLE KEYS */;
INSERT INTO `tbl_pelicula_genero` VALUES (1,1),(3,1),(1,2),(3,2),(2,3),(3,4),(2,5);
/*!40000 ALTER TABLE `tbl_pelicula_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `apellido` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'Woody','Allen'),(2,'Steven','Spielberg'),(3,'David','Fincher'),(4,'Claudia','Zuleta'),(5,'Carlos','Saura'),(6,'Mario','Camus'),(7,'Brad','Pitt'),(8,'Mario','Casas'),(9,'Blanca','Suarez'),(10,'Ginevra','Francesconi'),(11,'Eleonora','Wexler'),(12,'Joss','Whedon'),(13,'Robert','Downey Jr'),(14,'Robert','Downey Jr'),(15,'Robert','Downey'),(16,'Chris','Evans'),(17,'Eugene','Levy');
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-01 16:27:57
