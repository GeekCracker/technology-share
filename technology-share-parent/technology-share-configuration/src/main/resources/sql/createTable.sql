DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_name` varchar(31) DEFAULT NULL COMMENT '用户名',
    `user_password` varchar(64) DEFAULT NULL COMMENT '密码',
    `age` int(2) DEFAULT NULL COMMENT '年龄',
    `user_head` varchar(127) DEFAULT NULL COMMENT '头像地址',
    `user_phone` varchar(11) DEFAULT NULL COMMENT '手机号',
    `user_status` tinyint(1) DEFAULT NULL COMMENT '是否启用(1:启用0:禁用)',
    `role_id` bigint(11) DEFAULT NULL COMMENT '角色ID',
    `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
    `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除(1:删除0:不删除)',
    `create_by` varchar(31) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(31) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE IF NOT EXISTS `t_permission` (
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `permission_name` varchar(31) DEFAULT NULL COMMENT '权限名',
    `permission_bit` varchar(64) DEFAULT NULL COMMENT '权限位',
    `permission_link` varchar(127) DEFAULT NULL COMMENT '权限链接',
    `permission_icon` varchar(127) DEFAULT NULL COMMENT '权限图标',
    `permission_sort` int(5) DEFAULT NULL COMMENT '权限排序',
    `permission_status` tinyint(1) DEFAULT NULL COMMENT '是否启用(1:启用0:禁用)',
    `permission_type` tinyint(4) DEFAULT NULL COMMENT '权限类型',
    `parent_id` bigint(11) DEFAULT NULL COMMENT '父级权限ID',
    `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除(1:删除0:不删除)',
    `create_by` varchar(31) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(31) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE IF NOT EXISTS `t_role` (
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_name` varchar(31) DEFAULT NULL COMMENT '角色名',
    `role_status` tinyint(1) DEFAULT NULL COMMENT '是否启用(1:启用0:禁用)',
    `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除(1:删除0:不删除)',
    `create_by` varchar(31) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(31) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE IF NOT EXISTS `t_role_permission` (
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id` bigint(11) DEFAULT NULL COMMENT '角色ID',
    `permission_id` bigint(11) DEFAULT NULL COMMENT '权限ID',
    `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除(1:删除0:不删除)',
    `create_by` varchar(31) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(31) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

