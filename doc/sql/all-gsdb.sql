-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: gsdb
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `gs_author`
--
CREATE DATABASE gsdb;
USE gsdb;
DROP TABLE IF EXISTS `gs_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_author` (
  `id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `resource_type` varchar(45) DEFAULT NULL COMMENT '资源类型 1：菜单 2：按钮',
  PRIMARY KEY (`id`),
  KEY `resource_id_idx` (`resource_id`),
  KEY `group_id_idx` (`group_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `gs_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_author`
--

LOCK TABLES `gs_author` WRITE;
/*!40000 ALTER TABLE `gs_author` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_group`
--

DROP TABLE IF EXISTS `gs_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_group` (
  `id` int(11) NOT NULL COMMENT '分组ID',
  `group_name` varchar(45) DEFAULT NULL COMMENT '分组名称',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_group`
--

LOCK TABLES `gs_group` WRITE;
/*!40000 ALTER TABLE `gs_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_menu`
--

DROP TABLE IF EXISTS `gs_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_menu` (
  `id` int(11) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(45) DEFAULT NULL COMMENT '菜单名称',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_menu`
--

LOCK TABLES `gs_menu` WRITE;
/*!40000 ALTER TABLE `gs_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_operation`
--

DROP TABLE IF EXISTS `gs_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_operation` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `btn_code` varchar(45) DEFAULT NULL COMMENT '按钮页面ID',
  `btn_title` varchar(45) DEFAULT NULL COMMENT '按钮说明',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `menu_id_idx` (`menu_id`),
  CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `gs_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_operation`
--

LOCK TABLES `gs_operation` WRITE;
/*!40000 ALTER TABLE `gs_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_role`
--

DROP TABLE IF EXISTS `gs_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `role` varchar(45) DEFAULT NULL COMMENT '角色',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `need_show` int(11) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_role`
--

LOCK TABLES `gs_role` WRITE;
/*!40000 ALTER TABLE `gs_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_user`
--

DROP TABLE IF EXISTS `gs_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `username` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `roles` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户角色',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_user`
--

LOCK TABLES `gs_user` WRITE;
/*!40000 ALTER TABLE `gs_user` DISABLE KEYS */;
INSERT INTO `gs_user` VALUES (1,'anoy',11,'anoy','$2a$10$V734fKPVXlXBxxDg0HjkjeX8k5TaOMIpbefTLrnOtx2unP.B4U1qa','ROLE_USER',1559719525000,1559719525000),(2,'admin',100,'admin','$2a$10$V734fKPVXlXBxxDg0HjkjeX8k5TaOMIpbefTLrnOtx2unP.B4U1qa','ROLE_USER,ROLE_ADMIN',1559719525000,1559719525000),(5,NULL,NULL,'test','$2a$10$V734fKPVXlXBxxDg0HjkjeX8k5TaOMIpbefTLrnOtx2unP.B4U1qa',NULL,1559788837051,1559788837051);
/*!40000 ALTER TABLE `gs_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gs_user_role`
--

DROP TABLE IF EXISTS `gs_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gs_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `gs_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `gs_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gs_user_role`
--

LOCK TABLES `gs_user_role` WRITE;
/*!40000 ALTER TABLE `gs_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-11 13:24:13
