DROP DATABASE IF EXISTS db_autoland;
CREATE DATABASE db_autoland /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE db_autoland;

/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/**---TABLAS---**/
DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
	`idvehiculo` 		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nummatricula` 		varchar(255) 	DEFAULT NULL,
    `marca` 			varchar(255) DEFAULT NULL,
    `modelo` 			varchar(255) DEFAULT NULL,
    `aniofabricacion`	INT		 	DEFAULT NULL,
    `fechcompra` 		date 		DEFAULT NULL) 
ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
    `idrol`	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nomrol` 	varchar(255) DEFAULT NULL)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
	`idempleado` 	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nomempleado` 	varchar(255) 	DEFAULT NULL,
	`apeempleado` 	varchar(255) 	DEFAULT NULL,
	`dni` 			varchar(255)		DEFAULT NULL,
	`telempleado` 	varchar(255)		DEFAULT NULL,
	`direccion` 	varchar(255)	DEFAULT NULL) 
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
	`idusuario` 	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nomusuario` 	varchar(255) DEFAULT NULL,
	`contrasenia` 	varchar(255) DEFAULT NULL,
	`id_empleado`	INT 		NOT NULL,
    `id_rol` 		INT 		NOT NULL,
	`mailusuario` 	varchar(255) DEFAULT NULL,
	`fecharegistro` datetime 		DEFAULT NULL,
CONSTRAINT `FKUsuarios` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`idempleado`),
CONSTRAINT `FKempleado` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`idRol`)) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/**---INSERCIONES---**/

INSERT INTO vehiculo VALUES
		(NULL, "JKM-275", "Volvo", "Tractor Volvo FM12 420", 2005, "2011-07-25"),
		(NULL, "LSD-340", "Scania", "Tractor P410 A4X2", 2021, "2013-08-01"),
		(NULL, "CVV-685", "Volvo", "Tractor Volvo FM 380", 2015, "2016-10-01"),
		(NULL, "DNT-753", "Volvo", "Tractor Volvo FM 460", 2017, "2017-11-23"),
		(NULL, "FGH-904", "Volvo", "Tractor Volvo FH", 2016, "2018-06-12"),
		(NULL, "PYB-123", "Scania", "Tractor P440 A4X2", 2018, "2019-05-01"),
		(NULL, "ZYX-123", "Scania", "Tractor R460 A6X4", 2016, "2020-03-10"),
		(NULL, "FRY-896", "Scania", "Tractor G500 A6X4", 2019, "2021-09-08"),
		(NULL, "GJH-321", "Volvo", "Tractor Volvo FM 380 6x2T", 2021, "2022-02-13"),
		(NULL, "QTH-345", "Scania", "Tractor P450 A4X2", 2023, "2024-03-07");

INSERT INTO rol VALUES 
		(NULL, "Admin"),
		(NULL, "Gestor"),
		(NULL, "Promotor");

INSERT INTO empleado VALUES 
		(NULL, "Gonzalo Jesus", "Yañez Trejo", "73455400", "960713119", "Brazas, 136"),
		(NULL, "Juan Luis", "Barazorda Astete", "12345678", "123456789", "Doña Juana, 149"),
		(NULL, "Mariana Alexandra", "Pajauala Pérez", "87654321", "987654321", "AV. Jirón Miky, 2908"),
		(NULL, "Gustavo Miguel", "Broncano Sotelo", "67812345", "789123456", "Pasaje Cristo Rey, 777"),
		(NULL, "Pedro Pablo", "Ramírez Alva", "53467890", "987650021", "Jr. La Mar 367"),
		(NULL, "Carlos Alberto", "Huamaní Gómez", "12378945", "321654987", "Av. Las Flores, 456"),
		(NULL, "Diego Alejandro", "Chávez Rodríguez", "65478912", "456321789", "Calle Los Cedros, 789"),
		(NULL, "José Antonio", "Gutiérrez Silva", "45632198", "123789654", "Jr. Los Girasoles, 456"),
		(NULL, "Hugo Manuel", "Gómez Gonzales", "98765432", "123654789", "Av. Los Rosales, 789"),
		(NULL, "Luis Felipe", "Cordova Landa", "45698732", "654987123", "Jr. Los Pinos, 789"),
		(NULL, "Marco Antonio", "Gutierrez Vega", "12369875", "789321654", "Av. Los Girasoles, 789");

INSERT INTO usuario VALUES 
		(NULL, "ElAdmin", "E14DM1N", 1, 1, "e1admin@gmail.com", CURDATE()),
		(NULL, "Gest1", "gesT0R1", 2, 2, "juanluis@gmail.com", CURDATE()),
		(NULL, "Gest2", "gesT0R2", 3, 2, "marianapajauala@gmail.com", CURDATE()),
		(NULL, "Emp", "C0NDu1", 4, 3, "gustavomiguel@gmail.com", CURDATE()),
		(NULL, "Emp2", "C0NDu2", 5, 3, "pedropablo@gmail.com", CURDATE()),
		(NULL, "Emp3", "C0NDu3", 6, 3, "carlosalberto@gmail.com", CURDATE()),
		(NULL, "Emp4", "C0NDu4", 7, 3, "diegoalejandro@gmail.com", CURDATE()),
		(NULL, "Emp5", "C0NDu5", 8, 3, "joseantonio@gmail.com", CURDATE()),
		(NULL, "Emp6", "C0NDu6", 9, 3, "hugomanuel@gmail.com", CURDATE()),
		(NULL, "Emp7", "C0NDu7", 10, 3, "luisfelipe@gmail.com", CURDATE()),
		(NULL, "Emp8", "C0NDu8", 11, 3, "marcoantonio@gmail.com", CURDATE());