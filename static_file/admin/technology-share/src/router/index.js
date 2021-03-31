import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/base/home'
import Login from '@/components/page/login'
import Permission from '@/components/page/permission'
import Role from '@/components/page/role'
import User from '@/components/page/user'
import Type from '@/components/page/type'
import File from '@/components/page/file'
import Database from '@/components/page/database'
import Redis from '@/components/page/redis'
import Icon from '@/components/page/icon'
import Css from '@/components/page/css'
import Js from '@/components/page/js'
import Label from '@/components/page/label'



import Article from '@/components/page/article'
import SQL from '@/components/page/sql'
import YpDelUser from '@/components/page/ypDelUser'
import YpEquipmentError from '@/components/page/ypEquipmentError'
import YpDataStatistics from '@/components/page/ypDataStatistics'
import YpVersionControl from '@/components/page/ypVersionControl'



Vue.use(Router)

export default new Router({
	routes: [
		{
			path: '/home',
			name: 'Home',
			component: Home,
			children: [
				{
					path: 'permission',
					name: 'Permission',
					component: Permission
				},
				{
					path: 'role',
					name: 'Role',
					component: Role
				},
				{
					path: 'user',
					name: 'User',
					component: User
				},
				{
					path: 'type',
					name: 'Type',
					component: Type
				},
				{
					path: 'file',
					name: 'File',
					component: File
				},
				{
					path: 'database',
					name: 'Database',
					component: Database
				},
				{
					path: 'redis',
					name: 'Redis',
					component: Redis
				},
				{
					path: 'icon',
					name: 'Icon',
					component: Icon
				},
				{
					path: 'css',
					name: 'Css',
					component: Css
				},
				{
					path: 'js',
					name: 'Js',
					component: Js
				},
				{
					path: 'label',
					name: 'Label',
					component: Label
				},
				{
					path: 'article',
					name: 'article',
					component: Article
				},
				{
					path: 'sql',
					name: 'sql',
					component: SQL
				},
				{
					path: 'ypDelUser',
					name: 'YpDelUser',
					component: YpDelUser
				},
				{
					path: 'ypEquipmentError',
					name: 'YpEquipmentError',
					component: YpEquipmentError
				},
				{
					path: 'ypDataStatistics',
					name: 'YpDataStatistics',
					component: YpDataStatistics
				},
				{
					path: 'ypVersionControl',
					name: 'YpVersionControl',
					component: YpVersionControl
				}
			]
		},
		{
			path: '/',
			name: '登录',
			component: Login
		}
	]
})
