SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `t1`;
CREATE TABLE `t1` (
	`id` varchar(18) NOT NULL COMMENT '编号',
	`create_time` varchar(19) DEFAULT '' COMMENT '创建时间',
	`update_time` varchar(19) DEFAULT '' COMMENT '修改时间',
	`is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
	`t1` varchar(20) DEFAULT '' COMMENT 't1',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 't1';
