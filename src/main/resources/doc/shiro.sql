CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `available` varchar(4) DEFAULT NULL,
  `open` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`s_id`,`name`,`type`,`parent_id`,`permission`,`available`,`open`) values (0,'root','menu',-1,'','1',1),(1,'用户权限','menu',0,'','1',1),(2,'用户添加11','menu',1,'user:create','1',0),(3,'用户修改','menu',1,'user:update','1',1),(4,'用户查询','menu',1,'user:view','1',0),(5,'用户删除','menu',1,'user:delete','1',1),(6,'导航','menu',0,'page:top','1',0),(7,'角色权限','menu',0,NULL,'1',0),(8,'角色添加','menu',7,'role:create','1',1),(9,'角色修改','menu',7,'role:update','1',1),(10,'角色查询','menu',7,'role:view','1',1),(11,'角色删除','menu',7,'role:delete','1',1),(12,'资源权限','menu',0,NULL,'1',0),(13,'资源添加','menu',12,'resource:create','1',1),(14,'资源修改','menu',12,'resource:update','1',1),(15,'资源查询','menu',12,'resource:view','1',2),(16,'资源删除','menu',12,'resource:delete','1',2),(17,'角色查询','menu',7,NULL,'1',1),(18,'页面显示','menu',0,'page:*','1',0),(19,'分配角色','menu',1,'user:allow_role','1',2),(20,'分配资源','menu',7,'role:*','1',1);
