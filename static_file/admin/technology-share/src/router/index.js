import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/base/home'
import Login from '@/components/page/login'
import Permission from '@/components/page/permission'
import Role from '@/components/page/role'
import User from '@/components/page/user'

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
