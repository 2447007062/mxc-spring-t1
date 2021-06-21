/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : cxqc

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-04 14:08:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for create_code
-- ----------------------------
DROP TABLE IF EXISTS `create_code`;
CREATE TABLE `create_code` (
  `id` varchar(18) NOT NULL COMMENT '创建代码所需属性类id',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `author` varchar(10) DEFAULT NULL COMMENT '作者',
  `describes` varchar(20) DEFAULT NULL COMMENT '描述',
  `table_name` varchar(50) DEFAULT NULL COMMENT '表名',
  `class_name` varchar(50) DEFAULT NULL COMMENT '类名前部分',
  `class_name_lower` varchar(50) DEFAULT NULL COMMENT '类名首字母小写',
  `key_str` varchar(1000) DEFAULT NULL COMMENT '类字段名拼接',
  `table_key_str` varchar(1000) DEFAULT NULL COMMENT '表字段名拼接',
  `value_str` varchar(2000) DEFAULT NULL COMMENT '字段注释拼接',
  `user_id` varchar(18) DEFAULT NULL,
  `project_id` varchar(18) DEFAULT NULL,
  `table_key_length` varchar(1000) DEFAULT NULL,
  `table_key_type` varchar(1000) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of create_code
-- ----------------------------
INSERT INTO `create_code` VALUES ('260980738361528320', '2019-02-28 14:11:07', '2019-02-28 14:11:07', 'admin', '测试', 'test', 'Test', 'test', 'id,createTime,updateTime,isDeleted,test', 'id,create_time,update_time,is_deleted,test', '编号,创建时间,修改时间,是否删除,测试', '100000000000000001', '260980604013776896', '18,19,19,1,20', 'varchar,varchar,varchar,tinyint,varchar', '0');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` varchar(18) NOT NULL COMMENT '部门ID',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `name` varchar(10) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('153407142047649792', '2018-05-07 17:52:03', '2018-05-07 17:52:51', 'web');
INSERT INTO `department` VALUES ('153407190168899584', '2018-05-07 17:52:14', '2018-05-07 17:52:56', 'ios');
INSERT INTO `department` VALUES ('153407315972853760', '2018-05-07 17:52:44', '2018-05-07 17:53:07', 'android ');
INSERT INTO `department` VALUES ('153407448215064576', '2018-05-07 17:53:16', '2018-05-07 17:53:16', 'java');
INSERT INTO `department` VALUES ('153407490598506496', '2018-05-07 17:53:26', '2018-05-07 17:53:26', 'ui');
INSERT INTO `department` VALUES ('153408259250851840', '2018-05-07 17:56:29', '2018-05-07 18:01:51', '产品');
INSERT INTO `department` VALUES ('153409683468718080', '2018-05-07 18:02:08', '2018-05-07 18:02:08', '技术经理');
INSERT INTO `department` VALUES ('153409705388150784', '2018-05-07 18:02:14', '2018-05-07 18:02:14', '副总经理');
INSERT INTO `department` VALUES ('153409726808461312', '2018-05-07 18:02:19', '2018-05-07 18:02:19', '总经理');

-- ----------------------------
-- Table structure for per_resource
-- ----------------------------
DROP TABLE IF EXISTS `per_resource`;
CREATE TABLE `per_resource` (
  `id` varchar(100) NOT NULL COMMENT '权限-资源ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `res_name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源地址',
  `res_introduce` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `res_order_num` varchar(255) DEFAULT NULL COMMENT '排序顺序',
  `menu_level` varchar(32) DEFAULT NULL COMMENT '菜单标识( 菜单 1 非菜单 0 )',
  `api_url` varchar(255) DEFAULT '' COMMENT '接口url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of per_resource
-- ----------------------------
INSERT INTO `per_resource` VALUES ('138875674302746624', '2018-03-28 15:29:11', '2018-03-28 15:29:11', '0', '系统', '', '系统菜单栏', '1', '0', '');
INSERT INTO `per_resource` VALUES ('138876386558480384', '2018-03-28 15:32:00', '2018-04-04 10:12:56', '138875674302746624', '权限-角色', 'html/perRole/list.html', '权限-角色', '2', '1', '/perRole/select');
INSERT INTO `per_resource` VALUES ('138876386608812032', '2018-03-28 15:32:00', '2018-04-04 10:14:03', '138876386558480384', '权限-角色新增', 'html/perRole/edit.html', '权限-角色新增', '0', '2', '/perRole/insert');
INSERT INTO `per_resource` VALUES ('138876386617200640', '2018-03-28 15:32:00', '2018-04-04 10:12:21', '138876386558480384', '权限-角色删除', '', '权限-角色删除', '1', '2', '/perRole/delete');
INSERT INTO `per_resource` VALUES ('138876386629783552', '2018-03-28 15:32:00', '2018-04-04 10:12:29', '138876386558480384', '权限-角色编辑', 'html/perRole/edit.html', '权限-角色编辑', '2', '2', '/perRole/update');
INSERT INTO `per_resource` VALUES ('138877168636792832', '2018-03-28 15:35:07', '2018-04-04 10:12:46', '138875674302746624', '权限-资源', 'html/perResource/list.html', '权限-资源', '2', '1', '/perResource/select');
INSERT INTO `per_resource` VALUES ('138877168691318784', '2018-03-28 15:35:07', '2018-04-04 10:13:22', '138877168636792832', '权限-资源新增', 'html/perResource/edit.html', '权限-资源新增', '0', '2', '/perResource/insert');
INSERT INTO `per_resource` VALUES ('138877168699707392', '2018-03-28 15:35:07', '2018-04-04 10:14:20', '138877168636792832', '权限-资源删除', '', '权限-资源删除', '1', '2', '/perResource/delete');
INSERT INTO `per_resource` VALUES ('138877168712290304', '2018-03-28 15:35:07', '2018-04-04 10:14:29', '138877168636792832', '权限-资源编辑', 'html/perResource/edit.html', '权限-资源编辑', '2', '2', '/perResource/update');
INSERT INTO `per_resource` VALUES ('138877300795117568', '2018-03-28 15:35:38', '2018-04-04 10:14:35', '138875674302746624', '用户', 'html/sysUser/list.html', '用户列表', '3', '1', '/system/sysUser/select');
INSERT INTO `per_resource` VALUES ('138877300845449216', '2019-02-25 17:46:40', '2019-02-25 17:46:40', '138877300795117568', '用户新增', 'html/sysUser/edit.html', '用户列表新增', '0', '2', '/sysUser/insert');
INSERT INTO `per_resource` VALUES ('138877300858032128', '2018-03-28 15:35:38', '2018-04-04 10:15:04', '138877300795117568', '用户删除', '', '用户删除', '1', '2', '/sysUser/delete');
INSERT INTO `per_resource` VALUES ('138877300874809344', '2019-02-25 17:46:40', '2019-02-25 17:46:40', '138877300795117568', '用户编辑', 'html/sysUser/edit.html', '用户列表编辑', '2', '2', '/sysUser/update');
INSERT INTO `per_resource` VALUES ('142799299057684480', '2018-04-08 11:20:16', '2018-04-08 11:20:16', '0', '代码生成', '', '', '2', '0', '');
INSERT INTO `per_resource` VALUES ('144662312014450688', '2018-04-13 14:43:13', '2018-04-13 15:30:21', '142799299057684480', '代码生成', 'html/createCode/list.html', '代码生成', 'html/createCode/list.html', '1', '/createCode/select');
INSERT INTO `per_resource` VALUES ('144662312106725376', '2018-04-13 14:43:13', '2018-04-13 15:30:31', '144662312014450688', '代码生成新增', 'html/createCode/edit.html', '代码生成新增', 'html/createCode/edit.html', '2', '/createCode/insert');
INSERT INTO `per_resource` VALUES ('144662312127696896', '2018-04-13 14:43:13', '2018-04-13 15:30:37', '144662312014450688', '代码生成删除', '', '代码生成删除', '1', '2', '/createCode/delete');
INSERT INTO `per_resource` VALUES ('144662312144474112', '2018-04-13 14:43:13', '2018-04-13 15:30:45', '144662312014450688', '代码生成编辑', 'html/createCode/edit.html', '代码生成编辑', 'html/createCode/edit.html', '2', '/createCode/update');
INSERT INTO `per_resource` VALUES ('149297071999029248', '2018-04-26 09:40:06', '2018-04-26 09:40:06', '0', '管理', '', '管理资源', '2', '0', '');
INSERT INTO `per_resource` VALUES ('149298642409361408', '2018-04-26 09:46:20', '2018-04-26 09:48:23', '149297071999029248', '项目', 'html/project/list.html', '项目', 'html/project/list.html', '1', '/project/select');
INSERT INTO `per_resource` VALUES ('149298642480664576', '2018-04-26 09:46:20', '2018-04-26 09:46:20', '149298642409361408', '项目新增', '/edit.html', '项目新增', '0', '2', '/project/insert');
INSERT INTO `per_resource` VALUES ('149298642518413312', '2018-04-26 09:46:20', '2018-04-26 09:46:20', '149298642409361408', '项目删除', '', '项目删除', '1', '2', '/project/delete');
INSERT INTO `per_resource` VALUES ('149298642539384832', '2018-04-26 09:46:20', '2018-04-26 09:46:20', '149298642409361408', '项目编辑', '/edit.html', '项目编辑', '2', '2', '/project/update');
INSERT INTO `per_resource` VALUES ('260361503771856896', '2019-02-26 21:10:30', '2019-02-26 21:10:30', '138875674302746624', '公共参数', 'html/sysPublicParam/list.html', '公共参数介绍', '4', '1', '/sysPublicParam/select');
INSERT INTO `per_resource` VALUES ('260361503805411328', '2019-02-26 21:10:30', '2019-02-26 21:10:30', '260361503771856896', '公共参数新增', 'html/sysPublicParam/edit.html', '公共参数介绍新增', '0', '2', '/sysPublicParam/insert');
INSERT INTO `per_resource` VALUES ('260361503826382848', '2019-02-26 21:10:30', '2019-02-26 21:10:30', '260361503771856896', '公共参数删除', '', '公共参数介绍删除', '1', '2', '/sysPublicParam/delete');
INSERT INTO `per_resource` VALUES ('260361503843160064', '2019-02-26 21:10:30', '2019-02-26 21:10:30', '260361503771856896', '公共参数编辑', 'html/sysPublicParam/edit.html', '公共参数介绍编辑', '2', '2', '/sysPublicParam/update');

-- ----------------------------
-- Table structure for per_role
-- ----------------------------
DROP TABLE IF EXISTS `per_role`;
CREATE TABLE `per_role` (
  `id` varchar(18) NOT NULL COMMENT '权限-角色ID',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_type` varchar(20) DEFAULT NULL COMMENT '角色类型',
  `role_introduce` varchar(20) DEFAULT NULL COMMENT '角色介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of per_role
-- ----------------------------
INSERT INTO `per_role` VALUES ('138878261567557632', '2018-03-28 15:39:28', '2019-02-27 21:21:57', '系统管理员', null, '系统管理员');

-- ----------------------------
-- Table structure for per_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `per_role_resource`;
CREATE TABLE `per_role_resource` (
  `id` varchar(18) NOT NULL COMMENT '权限-角色资源ID',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `per_role_id` varchar(18) DEFAULT NULL COMMENT '角色id',
  `per_resource_id` varchar(18) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of per_role_resource
-- ----------------------------
INSERT INTO `per_role_resource` VALUES ('1', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '149297071999029248');
INSERT INTO `per_role_resource` VALUES ('260726777205362689', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '149298642409361408');
INSERT INTO `per_role_resource` VALUES ('260726777205362690', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '149298642539384832');
INSERT INTO `per_role_resource` VALUES ('260726777205362691', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '149298642518413312');
INSERT INTO `per_role_resource` VALUES ('260726777205362692', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '149298642480664576');
INSERT INTO `per_role_resource` VALUES ('260726777205362693', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '142799299057684480');
INSERT INTO `per_role_resource` VALUES ('260726777205362694', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '144662312014450688');
INSERT INTO `per_role_resource` VALUES ('260726777205362695', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '144662312144474112');
INSERT INTO `per_role_resource` VALUES ('260726777205362696', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '144662312127696896');
INSERT INTO `per_role_resource` VALUES ('260726777205362697', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '144662312106725376');
INSERT INTO `per_role_resource` VALUES ('260726777205362698', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138875674302746624');
INSERT INTO `per_role_resource` VALUES ('260726777205362699', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '260361503771856896');
INSERT INTO `per_role_resource` VALUES ('260726777205362700', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '260361503843160064');
INSERT INTO `per_role_resource` VALUES ('260726777205362701', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '260361503826382848');
INSERT INTO `per_role_resource` VALUES ('260726777205362702', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '260361503805411328');
INSERT INTO `per_role_resource` VALUES ('260726777205362703', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877300795117568');
INSERT INTO `per_role_resource` VALUES ('260726777205362704', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877300874809344');
INSERT INTO `per_role_resource` VALUES ('260726777205362705', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877300858032128');
INSERT INTO `per_role_resource` VALUES ('260726777205362706', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877300845449216');
INSERT INTO `per_role_resource` VALUES ('260726777205362707', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877168636792832');
INSERT INTO `per_role_resource` VALUES ('260726777205362708', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877168712290304');
INSERT INTO `per_role_resource` VALUES ('260726777205362709', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877168699707392');
INSERT INTO `per_role_resource` VALUES ('260726777205362710', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138877168691318784');
INSERT INTO `per_role_resource` VALUES ('260726777205362711', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138876386558480384');
INSERT INTO `per_role_resource` VALUES ('260726777205362712', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138876386629783552');
INSERT INTO `per_role_resource` VALUES ('260726777205362713', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138876386617200640');
INSERT INTO `per_role_resource` VALUES ('260726777205362714', '2019-02-27 21:21:58', '2019-02-27 21:21:58', '138878261567557632', '138876386608812032');

-- ----------------------------
-- Table structure for per_user_role
-- ----------------------------
DROP TABLE IF EXISTS `per_user_role`;
CREATE TABLE `per_user_role` (
  `id` varchar(18) NOT NULL COMMENT '权限-用户角色ID',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(18) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(18) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of per_user_role
-- ----------------------------
INSERT INTO `per_user_role` VALUES ('246155614433185792', '2019-01-18 16:21:22', '2019-01-18 16:21:22', '100000000000000001', '138878261567557632');
INSERT INTO `per_user_role` VALUES ('246155614433185793', '2019-01-18 16:21:22', '2019-01-18 16:21:22', '100000000000000001', '138878399652433920');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` varchar(18) NOT NULL COMMENT '项目Id',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `name` varchar(20) DEFAULT NULL COMMENT '项目名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('260980604013776896', '2019-02-28 14:10:35', '2019-02-28 14:10:35', '系统');

-- ----------------------------
-- Table structure for sys_public_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_param`;
CREATE TABLE `sys_public_param` (
  `id` varchar(18) NOT NULL COMMENT '编号',
  `create_time` varchar(19) DEFAULT '' COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT '' COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `param_key` varchar(50) DEFAULT '' COMMENT '参数键',
  `param_value` varchar(255) DEFAULT '' COMMENT '参数值',
  `name` varchar(50) DEFAULT '' COMMENT '参数名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共参数';

-- ----------------------------
-- Records of sys_public_param
-- ----------------------------
INSERT INTO `sys_public_param` VALUES ('260365981518532608', '2019-02-26 21:28:17', '2019-02-28 11:38:49', '0', 'SystemInterceptor_validateSignFlag', 'false', '系统拦截器_是否验证签名');
INSERT INTO `sys_public_param` VALUES ('260366343243698176', '2019-02-26 21:29:43', '2019-02-28 14:03:30', '0', 'SystemInterceptor_validateTokenFlag', 'true', '系统拦截器_是否验证令牌');
INSERT INTO `sys_public_param` VALUES ('260366978869497856', '2019-02-26 21:32:15', '2019-02-28 14:01:57', '0', 'SystemInterceptor_validateAuthFlag', 'true', '系统拦截器_是否验证权限');
INSERT INTO `sys_public_param` VALUES ('260368997634150400', '2019-02-26 21:40:16', '2019-02-26 21:40:16', '0', 'SystemInterceptor_privateKey', 'springbootsystem', '系统拦截器_私有key');
INSERT INTO `sys_public_param` VALUES ('260370445067489280', '2019-02-26 21:46:01', '2019-02-28 14:06:35', '0', 'SystemInterceptor_excludeTokenUrl', '/system/sso/login,/system/createCode/getCodeFileStr', '系统拦截器_不需要验证token的url');
INSERT INTO `sys_public_param` VALUES ('260371769125376000', '2019-02-26 21:51:17', '2019-02-26 21:51:17', '0', 'SystemInterceptor_excludeAuthUrl', '/system/sso/logout', '系统拦截器_不需要验证权限的url');
INSERT INTO `sys_public_param` VALUES ('260625488274395136', '2019-02-27 14:39:28', '2019-02-28 11:38:29', '0', 'APIInterceptor_validateSignFlag', 'false', 'API拦截器_是否验证签名');
INSERT INTO `sys_public_param` VALUES ('260625604087517184', '2019-02-27 14:39:56', '2019-02-28 11:38:21', '0', 'APIInterceptor_validateTokenFlag', 'false', 'API拦截器_是否验证令牌');
INSERT INTO `sys_public_param` VALUES ('260625724157857792', '2019-02-27 14:40:25', '2019-02-27 14:40:25', '0', 'APIInterceptor_validateAuthFlag', 'false', 'API拦截器_是否验证权限');
INSERT INTO `sys_public_param` VALUES ('260626092598104064', '2019-02-27 14:41:52', '2019-02-27 14:48:05', '0', 'APIInterceptor_privateKey', 'springbootapi', 'API拦截器_私有key');
INSERT INTO `sys_public_param` VALUES ('260626465723387904', '2019-02-27 14:43:21', '2019-02-27 14:47:57', '0', 'APIInterceptor_excludeTokenUrlAPI', '/api/api/test', '拦截器_不需要验证token的url');
INSERT INTO `sys_public_param` VALUES ('260626608069677056', '2019-02-27 14:43:55', '2019-02-27 14:47:46', '0', 'APIInterceptor_excludeAuthUrlAPI', '/api/api/test', '拦截器_不需要验证权限的url');
INSERT INTO `sys_public_param` VALUES ('260628149803552768', '2019-02-27 14:50:03', '2019-03-04 14:06:09', '0', 'Project_name', '显城软件', '项目_名称');
INSERT INTO `sys_public_param` VALUES ('260628503853142016', '2019-02-27 14:51:27', '2019-02-27 15:59:46', '0', 'Project_describe', '后台管理模板系统', '项目_描述');
INSERT INTO `sys_public_param` VALUES ('260642093326798848', '2019-02-27 15:45:27', '2019-02-27 15:59:51', '0', 'Project_title', '后台管理系统', '项目_标题');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(18) NOT NULL COMMENT '用户Id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(48) DEFAULT NULL COMMENT '密码',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `project_ids` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `department_id` varchar(18) DEFAULT NULL,
  `ip` varchar(20) DEFAULT '' COMMENT 'ip地址',
  `address` varchar(50) DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('100000000000000001', 'admin', 'fa5ad45m23i51n6401c0b30af0770b00e50f90d80380810f', '2018-03-08 00:00:00', '2019-03-04 14:04:37', '149299399305072640,149671377752952832,149671544765943808,153399189764706304,153399222027292672,156308483145338880,159832046703874048,166329339951058944,166329386482667520,166329527277064192,246155389316501504', '超级管理员', '15000000000', '153409726808461312', '0:0:0:0:0:0:0:1', '本机');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project` (
  `id` varchar(18) NOT NULL COMMENT '用户项目ID',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(18) DEFAULT NULL COMMENT '用户ID',
  `project_id` varchar(18) DEFAULT NULL COMMENT '项目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_project
-- ----------------------------

-- ----------------------------
-- View structure for viwe_sys_user
-- ----------------------------
DROP VIEW IF EXISTS `viwe_sys_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viwe_sys_user` AS select `sys_user`.`id` AS `id`,`sys_user`.`username` AS `username`,`sys_user`.`password` AS `password`,`sys_user`.`create_time` AS `create_time`,`sys_user`.`update_time` AS `update_time`,`sys_user`.`project_ids` AS `project_ids`,`sys_user`.`name` AS `name`,`sys_user`.`phone` AS `phone`,`sys_user`.`department_id` AS `department_id`,`sys_user`.`ip` AS `ip`,`sys_user`.`address` AS `address` from `sys_user` ;
