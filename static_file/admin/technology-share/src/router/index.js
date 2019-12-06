import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/base/home'
import Login from '@/components/page/login'
import Permission from '@/components/page/permission'
import Role from '@/components/page/role'
import User from '@/components/page/user'
import Type from '@/components/page/type'
import Icon from '@/components/page/icon'
import Css from '@/components/page/css'
import Js from '@/components/page/js'


import Article from '@/components/page/article'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children:[
           {
        	   path:'permission',
        	   name: 'Permission',
        	   component: Permission
           },
           {
        	   path:'role',
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
        	   path: 'article',
        	   name: 'article',
        	   component: Article
           }
      ]
    },
    {
    	path:'/',
    	name:'登录',
    	component:Login
    }
  ]
})
