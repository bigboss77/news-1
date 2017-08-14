/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.27 : Database - news
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`news` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `news`;

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `newId` int(11) DEFAULT NULL,
  `userIp` varchar(20) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

insert  into `t_comment`(`commentId`,`newId`,`userIp`,`commentDate`,`content`) values (29,94,'0:0:0:0:0:0:0:1','2017-08-13 22:21:33','哈哈');

/*Table structure for table `t_ip` */

DROP TABLE IF EXISTS `t_ip`;

CREATE TABLE `t_ip` (
  `AddressId` int(11) NOT NULL AUTO_INCREMENT,
  `newId` int(11) DEFAULT NULL,
  `AddressName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AddressId`),
  KEY `newId` (`newId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

/*Data for the table `t_ip` */

insert  into `t_ip`(`AddressId`,`newId`,`AddressName`) values (34,109,'0:0:0:0:0:0:0:1'),(35,107,'0:0:0:0:0:0:0:1'),(36,106,'0:0:0:0:0:0:0:1'),(44,95,'0:0:0:0:0:0:0:1'),(45,82,'0:0:0:0:0:0:0:1'),(46,110,'0:0:0:0:0:0:0:1'),(47,111,'0:0:0:0:0:0:0:1'),(48,49,'0:0:0:0:0:0:0:1'),(49,104,'0:0:0:0:0:0:0:1'),(50,103,'0:0:0:0:0:0:0:1'),(51,94,'0:0:0:0:0:0:0:1'),(52,102,'0:0:0:0:0:0:0:1'),(53,51,'0:0:0:0:0:0:0:1'),(54,50,'0:0:0:0:0:0:0:1'),(55,101,'0:0:0:0:0:0:0:1'),(56,52,'0:0:0:0:0:0:0:1'),(57,53,'0:0:0:0:0:0:0:1'),(58,64,'0:0:0:0:0:0:0:1'),(59,65,'0:0:0:0:0:0:0:1'),(60,66,'0:0:0:0:0:0:0:1'),(61,67,'0:0:0:0:0:0:0:1'),(62,68,'0:0:0:0:0:0:0:1'),(63,69,'0:0:0:0:0:0:0:1'),(64,71,'0:0:0:0:0:0:0:1'),(65,72,'0:0:0:0:0:0:0:1'),(66,73,'0:0:0:0:0:0:0:1'),(67,74,'0:0:0:0:0:0:0:1'),(68,75,'0:0:0:0:0:0:0:1'),(69,76,'0:0:0:0:0:0:0:1'),(70,77,'0:0:0:0:0:0:0:1'),(71,78,'0:0:0:0:0:0:0:1'),(72,79,'0:0:0:0:0:0:0:1'),(73,80,'0:0:0:0:0:0:0:1'),(74,81,'0:0:0:0:0:0:0:1'),(75,83,'0:0:0:0:0:0:0:1'),(76,84,'0:0:0:0:0:0:0:1'),(77,85,'0:0:0:0:0:0:0:1'),(78,100,'0:0:0:0:0:0:0:1');

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `linkId` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(20) DEFAULT NULL,
  `linkUrl` varchar(40) DEFAULT NULL,
  `linkEmail` varchar(40) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_link` */

insert  into `t_link`(`linkId`,`linkName`,`linkUrl`,`linkEmail`,`orderNum`) values (2,'lipop博客','http://blog.i20forever.cn','87394645',1),(10,'百度','http://www.baidu.com','baidu@bb.com',2);

/*Table structure for table `t_news` */

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `newId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `content` text,
  `publishDate` datetime DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `isHead` tinyint(4) DEFAULT NULL,
  `isImage` tinyint(4) DEFAULT NULL,
  `imageName` varchar(20) DEFAULT NULL,
  `isHot` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`newId`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `t_news_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `t_newstype` (`newsTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

insert  into `t_news`(`newId`,`title`,`content`,`publishDate`,`author`,`typeId`,`click`,`isHead`,`isImage`,`imageName`,`isHot`) values (49,'2133啊','2133444我','2014-03-04 17:23:44','213344',3,3,1,1,'20140304060416.jpg',1),(50,'汪峰是互联网上名副其实的King','<p>不知不觉在百度百家写了很多东西了，多得都吓到自己了，主要是一路写到现在，仍然是文笔幼稚、面目可憎，阿弥陀佛，善哉善哉。</p>\r\n\r\n<p>处于多年的媒体习惯，写东西对我来说不仅仅是满足一己私欲，同时也想借此了解一下网友们口味，美其名曰趋势、指数、习惯。而在这个过程中我真的发现了一点什么。往下看。（括号里为阅读量）</p>\r\n','2014-03-13 15:17:07','小锋',1,2,0,1,'20140313031707.jpg',1),(51,'马来西亚巫师作法再寻失联飞机：这次更奇葩','马航客机MH370失联已经进入第六天了，但搜寻依然没有取得突破性的进展。<br />\r\n此前曾有报道称为了搜寻失联的马航客机，马来西亚不惜用尽各种方法，只要不违反伊斯兰教教义，欢迎任何人参与搜索行动，甚至连马来巫师也不例外。<br />\r\n著名马来巫师伊布拉欣马今（IbrahimMatZin）曾于前日前往吉隆坡国际机场，以透过灵异力量寻找失踪飞机所在位置。他在吉隆坡国际机场入口处祈祷后表示有两种感觉，即飞机还在空中飞或已坠海，但不会坠落在陆地上。<br />\r\n随而昨天，伊布拉欣马今在吉隆坡机场继续做法，这次的道具更加奇葩，除了专门的&ldquo;法器&rdquo;之外还动用了两个椰子，但报道中并未提及这次的结果如何，不知道这次大师会得出什么结论。<br />\r\n<img alt=\"\" src=\"userImage/20140313065252.jpg\" /><br />\r\n<img alt=\"\" src=\"userImage/20140313065421.jpg\" /><br />\r\n<img alt=\"\" src=\"userImage/20140313065432.jpg\" /><br />\r\n<img alt=\"\" src=\"userImage/20140313065443.jpg\" /><br />\r\n<img alt=\"\" src=\"userImage/20140313065452.jpg\" /><br />\r\n&nbsp;','2014-03-13 18:54:57','小锋',2,18,1,1,'20140313065252.jpg',1),(52,'娱乐新闻1','娱乐新闻1','2014-03-13 20:41:24','小锋',1,1,0,1,'20140313084123.jpg',1),(53,'娱乐新闻2','娱乐新闻2','2014-03-13 20:41:58','小锋',1,1,0,0,NULL,1),(64,'娱乐新闻3','娱乐新闻3','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(65,'娱乐新闻4','娱乐新闻4','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(66,'娱乐新闻5','娱乐新闻5','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(67,'娱乐新闻6','娱乐新闻6','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(68,'娱乐新闻7','娱乐新闻7','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(69,'娱乐新闻8','娱乐新闻8','2014-03-13 21:18:17','小锋',1,1,0,0,NULL,0),(71,'政治新闻2','政治新闻8','2014-03-13 22:13:56','小锋',2,2,0,0,NULL,0),(72,'政治新闻3','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(73,'政治新闻4','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(74,'政治新闻5','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(75,'政治新闻6','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(76,'政治新闻7','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(77,'政治新闻8','政治新闻8','2014-03-13 22:13:56','小锋',2,1,0,0,NULL,0),(78,'经济新闻1','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(79,'经济新闻3','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(80,'经济新闻3','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(81,'经济新闻4','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(82,'经济新闻5','经济新闻8','2014-03-13 22:14:56','小锋',3,0,0,0,NULL,0),(83,'经济新闻6','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(84,'经济新闻7','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(85,'经济新闻8','经济新闻8','2014-03-13 22:14:56','小锋',3,1,0,0,NULL,0),(86,'文化新闻1','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(87,'文化新闻4','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(88,'文化新闻4','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(89,'文化新闻4','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(90,'文化新闻5','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(91,'文化新闻6','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(92,'文化新闻7','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(93,'文化新闻8','文化新闻8','2014-03-13 22:20:38','小锋',4,0,0,0,NULL,0),(99,'小道新闻6','小道新闻8','2014-03-13 22:21:03','小锋',5,0,0,0,NULL,0),(100,'小道新闻7','小道新闻8','2014-03-13 22:21:03','小锋',5,1,0,0,NULL,0),(101,'小道新闻8','小道新闻8','2014-03-13 22:21:03','小锋',5,1,0,0,NULL,0),(103,'昆明火车站暴恐案直击:暴徒见人就砍现场混乱血腥','<img alt=\"\" src=\"userImage/20140313111425.jpg\" /><br />\r\n<br />\r\n　3月1日晚拍摄的昆明火车站一售票大厅外散落的行李INSERT INTO t_news VALUES(手机拍摄)。3月1日晚9时20分，10余名统一着装的暴徒蒙面持刀在云南昆明火车站广场、售票厅等处砍杀无辜群众，截至3月2日1时，暴力案件已造成28名群众遇难、113名群众受伤。公安干警当场击毙5名暴徒，其余暴徒仍在围捕中。新华社记者蔺以光摄<br />\r\n<br />\r\n　　昆明火车站暴力恐怖事件直击<br />\r\n<br />\r\n　　新华网北京３月２日新媒体专电INSERT INTO t_news VALUES(新华社&ldquo;中国网事&rdquo;记者)３月１日晚９时２０分，１０余名统一着装的暴徒蒙面持刀在云南昆明火车站广场、售票厅等处砍杀无辜群众，截至３月２日１时，暴力案件已造成２８名群众遇难、１１３名群众受伤。公安干警当场击毙５名暴徒，其余暴徒仍在围捕中。<br />\r\n<br />\r\n　　云南省政法委认为，这是一起有组织、有计划、严重暴力恐怖袭击事件，性质极其恶劣，并对后续工作进行迅速安排。<br />\r\n<br />\r\n　　暴徒如此残忍，竟在人群密集的车站持刀肆意砍杀无辜群众。人们不禁要问：这些人为什么要这样心狠？<br />\r\n&nbsp;','2014-03-13 23:14:48','小锋',5,7,0,1,'20140313111448.jpg',1),(104,'香港少女穿热裤短裙便利店偷零食被捕2','<img alt=\"\" src=\"userImage/20140314090311.jpg\" style=\"height:338px; width:400px\" /><br />\r\n&nbsp;\r\n<p>　<a href=\"http://www.chinanews.com/\" target=\"_blank\">中新网</a>3月13日电 据香港《文汇报》报道，香港牛头角彩盈鸷发生店铺盗窃案，3名少年男女昨INSERT INTO t_news VALUES(12日)午进入该鸷商场一间便利店，涉嫌扮顾客购物，乘机偷取包括三文治和朱古力饮料等零食，夺门逃走时被职员揭发，警员到场将他们拘捕带署扣查。</p>\r\n\r\n<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n	<tbody>\r\n		<tr>\r\n			<td>&nbsp;</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>　　事发昨下午3时许，一名10余岁少年与2名分别穿热裤和短裙、年龄相若的少女，一同走进彩盈鸷商场一间便利店，在货架和雪柜旁徘徊状似选购货物，其间有人涉嫌从货架和雪柜中偷取包括三文治、花生和纸包朱古力饮品等零食，并收藏在衫袋内欲走向店门离开。但该店职员见他们形迹可疑，早已从旁暗中监视，随即将他们截停以及报警求助。</p>\r\n\r\n<p>　　警员接报迅速赶至调查，怀疑3名少年男女涉嫌店铺盗窃，并当场起出赃物，于是将3人拘捕，其间该名少年一度试图发难突围逃走，但终被警员制服，与其余2名少女一并被带署扣查。2</p>\r\n','2014-03-14 09:03:35','小锋',1,9,1,1,'20140313031707.jpg',1),(106,'美女啊','<p><img alt=\"\" src=\"http://localhost:8080/News/NewsImages/2016011405.jpg\" style=\"height:375px; width:600px\" /></p>\r\n','2017-08-13 22:17:26','lipoprotein',1,0,1,1,'9-1606261GK8.jpg',1);

/*Table structure for table `t_newstype` */

DROP TABLE IF EXISTS `t_newstype`;

CREATE TABLE `t_newstype` (
  `newsTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`newsTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_newstype` */

insert  into `t_newstype`(`newsTypeId`,`typeName`) values (1,'娱乐新闻'),(2,'政治新闻'),(3,'经济新闻'),(4,'文化新闻'),(5,'小道新闻'),(6,'屌炸天新闻');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`userName`,`password`) values (1,'1','xMpCOKC5I4INzFCab3WEmw==');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
