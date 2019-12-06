// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/js/jquery-3.4.1.js'
import axios from 'axios'
import qs from 'qs'
import VueAxios from 'vue-axios'
import moment from 'moment'
import 'moment/locale/zh-cn'
moment.locale('zh-cn'); 

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import ts from './assets/js/global.js'
import CKEditor from '@ckeditor/ckeditor5-vue'
import '@ckeditor/ckeditor5-build-classic/build/translations/zh-cn';

// 创建axios实例对象
let instance = axios.create({
  timeout: 6000,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
})

// 请求拦截器，将Request Payload请求数据格式 ，转换为FormData格式
instance.interceptors.request.use(
  config => {
    config.data = qs.stringify(config.data) // 转为formdata数据格式
    return config
  },
  error => Promise.error(error)
)


Vue.config.productionTip = false
Vue.prototype.ts = ts

Vue.use(ElementUI)
//使用axios实例对象
Vue.use(VueAxios, instance)
Vue.use(CKEditor)

//添加全局时间过滤器
Vue.filter('dateFormat',function(data,format){
	if(!data || data == ''){
		return '';
	}
	//默认时间格式
	if(!format){
		format = 'YYYY-MM-DD HH:mm:ss';
	}
	return moment(data).format(format);
});


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
