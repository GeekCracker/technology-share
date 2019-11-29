import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/base/home'
import Login from '@/components/page/login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children:[
           {
        	   path:'/Home'
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
