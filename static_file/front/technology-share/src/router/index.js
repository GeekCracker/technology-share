import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/base/home'
import Article from '@/components/page/article'

Vue.use(Router)

export default new Router({
	routes: [{
		path: '/',
		name: 'Home',
		component: Home,
		children: [{
			path: 'article',
			name: 'article',
			component: Article
		}]
	}]
})