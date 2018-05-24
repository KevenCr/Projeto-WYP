/*
SQLyog Community v13.0.1 (32 bit)
MySQL - 10.1.32-MariaDB : Database - wyp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wyp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `wyp`;

/*Table structure for table `arvore` */

DROP TABLE IF EXISTS `arvore`;

CREATE TABLE `arvore` (
  `id_arvore` int(11) NOT NULL AUTO_INCREMENT,
  `id_familia` int(11) NOT NULL,
  `id_nome` int(11) NOT NULL,
  `id_grau` int(11) NOT NULL,
  `id_nome1` int(11) NOT NULL,
  PRIMARY KEY (`id_arvore`),
  KEY `fk_id_familia_idx` (`id_familia`),
  KEY `fk_id_nome1_idx` (`id_nome`),
  KEY `fk_id_grau_idx` (`id_grau`),
  KEY `fk_id_nome1_idx1` (`id_nome1`),
  CONSTRAINT `fk_id_familia` FOREIGN KEY (`id_familia`) REFERENCES `familia` (`id_familia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_grau` FOREIGN KEY (`id_grau`) REFERENCES `grau` (`id_grau`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_nome` FOREIGN KEY (`id_nome`) REFERENCES `nome` (`id_nome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_nome1` FOREIGN KEY (`id_nome1`) REFERENCES `nome` (`id_nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `arvore` */

insert  into `arvore`(`id_arvore`,`id_familia`,`id_nome`,`id_grau`,`id_nome1`) values 
(4,2,7,4,8),
(5,2,9,1,10),
(6,4,11,3,12),
(7,2,13,3,14),
(8,5,15,12,16),
(9,2,17,3,18),
(10,6,19,1,20),
(11,6,21,8,22),
(12,6,23,3,24);

/*Table structure for table `familia` */

DROP TABLE IF EXISTS `familia`;

CREATE TABLE `familia` (
  `id_familia` int(11) NOT NULL AUTO_INCREMENT,
  `familia` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_login` int(11) NOT NULL,
  PRIMARY KEY (`id_familia`),
  KEY `fk_id_login_idx` (`id_login`),
  CONSTRAINT `fk_id_login` FOREIGN KEY (`id_login`) REFERENCES `login` (`id_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `familia` */

insert  into `familia`(`id_familia`,`familia`,`id_login`) values 
(2,'Cavalcante',1),
(4,'Rodrigues de Sousa',2),
(5,'Nunes',1),
(6,'Ribeiro',3);

/*Table structure for table `grau` */

DROP TABLE IF EXISTS `grau`;

CREATE TABLE `grau` (
  `id_grau` int(11) NOT NULL AUTO_INCREMENT,
  `grau` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_grau`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `grau` */

insert  into `grau`(`id_grau`,`grau`) values 
(1,'PAI'),
(2,'MÃE'),
(3,'FILHO'),
(4,'FILHA'),
(5,'AVÔ'),
(6,'AVÓ'),
(7,'TIO'),
(8,'TIA'),
(9,'SOBRINHO'),
(10,'SOBRINHA'),
(11,'GENRO'),
(12,'NORA'),
(13,'CUNHADO'),
(14,'CUNHADA');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id_login` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `senha` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `login` */

insert  into `login`(`id_login`,`login`,`senha`) values 
(1,'Keven','1234'),
(2,'Eduardo','senha'),
(3,'rckflip','');

/*Table structure for table `nome` */

DROP TABLE IF EXISTS `nome`;

CREATE TABLE `nome` (
  `id_nome` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sexo` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_nome`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `nome` */

insert  into `nome`(`id_nome`,`nome`,`sexo`) values 
(7,'Rosely Cavalcante','MULHER'),
(8,'José Holanda','HOMEM'),
(9,'João','HOMEM'),
(10,'Mateus','HOMEM'),
(11,'Keven Eduardo','HOMEM'),
(12,'Manoel Rodrigues','HOMEM'),
(13,'Felipe Cavalcante','HOMEM'),
(14,'Rosely Cavalcante','MULHER'),
(15,'Erica Regina','MULHER'),
(16,'Rosely Cavalcante','MULHER'),
(17,'Kevy Leonardo ','HOMEM'),
(18,'Manoel Rodrigues','HOMEM'),
(19,'RAMIR ','HOMEM'),
(20,'RAMIR JUNIOR','HOMEM'),
(21,'PAMELA','MULHER'),
(22,'RAMIR JUNIOR','HOMEM'),
(23,'RAMIR JUNIOR','HOMEM'),
(24,'ENI SERRA','MULHER');

/*Table structure for table `vw_arvore` */

DROP TABLE IF EXISTS `vw_arvore`;

/*!50001 DROP VIEW IF EXISTS `vw_arvore` */;
/*!50001 DROP TABLE IF EXISTS `vw_arvore` */;

/*!50001 CREATE TABLE  `vw_arvore`(
 `id_arvore` int(11) ,
 `nome1` varchar(20) ,
 `sexo1` varchar(6) ,
 `grau` varchar(45) ,
 `nome2` varchar(20) ,
 `sexo2` varchar(6) 
)*/;

/*View structure for view vw_arvore */

/*!50001 DROP TABLE IF EXISTS `vw_arvore` */;
/*!50001 DROP VIEW IF EXISTS `vw_arvore` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_arvore` AS select `a`.`id_arvore` AS `id_arvore`,`n1`.`nome` AS `nome1`,`n1`.`sexo` AS `sexo1`,`g`.`grau` AS `grau`,`n`.`nome` AS `nome2`,`n`.`sexo` AS `sexo2` from (((`arvore` `a` join `nome` `n` on((`n`.`id_nome` = `a`.`id_nome1`))) join `grau` `g` on((`g`.`id_grau` = `a`.`id_grau`))) join `nome` `n1` on((`n1`.`id_nome` = `a`.`id_nome`))) where (`a`.`id_familia` = 2) order by `a`.`id_arvore` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
