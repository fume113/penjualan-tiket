-- MySQL dump 10.13  Distrib 8.0.28, for macos12.0 (x86_64)
--
-- Host: 127.0.0.1    Database: penjualan_tiket
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.5-MariaDB-1:10.6.5+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `saldo`
--

DROP TABLE IF EXISTS `saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saldo` (
  `id_saldo` int(8) NOT NULL AUTO_INCREMENT,
  `id_user` int(8) NOT NULL,
  `saldo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_saldo`),
  KEY `saldo_FK` (`id_user`),
  CONSTRAINT `saldo_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (1,5,200000);
/*!40000 ALTER TABLE `saldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiket`
--

DROP TABLE IF EXISTS `tiket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiket` (
  `id_tiket` int(8) NOT NULL AUTO_INCREMENT,
  `nama_tiket` varchar(255) NOT NULL,
  `jenis_tiket` enum('ANAK','DEWASA') DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tiket`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiket`
--

LOCK TABLES `tiket` WRITE;
/*!40000 ALTER TABLE `tiket` DISABLE KEYS */;
INSERT INTO `tiket` VALUES (1,'WeekEnd Ticket','ANAK',10000),(2,'Tiket weekday Dewasa','DEWASA',15000);
/*!40000 ALTER TABLE `tiket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaksi` (
  `id_transaksi` int(8) NOT NULL AUTO_INCREMENT,
  `id_user` int(8) NOT NULL,
  `id_tiket` int(8) NOT NULL,
  `jumlah_tiket` int(3) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `metode_pembayaran` varchar(5) NOT NULL,
  `kode_tiket` varchar(25) NOT NULL,
  `tanggal_booking` date DEFAULT NULL,
  `tanggal_pembelian` date DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `transaksi_FK` (`id_tiket`),
  KEY `transaksi_FK_1` (`id_user`),
  CONSTRAINT `transaksi_FK` FOREIGN KEY (`id_tiket`) REFERENCES `tiket` (`id_tiket`),
  CONSTRAINT `transaksi_FK_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES (1,5,1,1,10,'saldo','123sd','2022-02-16','2022-02-16','UNUSED'),(2,5,1,2,20000,'saldo','0','2022-03-03','2022-02-16','UNUSED'),(3,5,1,1,10000,'saldo','X2ub47GIqC','2022-02-16','2022-02-16','USED');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `alamat` text DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `no_hp` int(13) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `no_hp` (`no_hp`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ahmad','1234567','ibu ganirah','ahers ss',12380123,'ip@gateaw.com','user'),(2,'b','123456','bandung','ahers',2839203,'b@b.com','user'),(3,'c','123','c','ah',12390,'c@c.com','user'),(4,'asd','asd','asd','asd',123,'asd@asd.com','admin'),(5,'bcd','123','bcde','bcd',12343,'bcd@bcd.com','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id_voucher` int(8) NOT NULL AUTO_INCREMENT,
  `id_user` int(8) NOT NULL,
  `kode_voucher` varchar(8) NOT NULL,
  `nominal` int(11) NOT NULL,
  `status` enum('used','unused') DEFAULT NULL,
  PRIMARY KEY (`id_voucher`),
  KEY `voucher_FK` (`id_user`),
  CONSTRAINT `voucher_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,1,'abcd',100000,'used'),(2,1,'efgh',100000,'used');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'penjualan_tiket'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-16 19:47:36
