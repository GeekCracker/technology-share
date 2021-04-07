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
moment.locale('zh-cn')

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import ts from './assets/js/global.js'
import hik from './assets/js/hik.js'
import CKEditor from '@ckeditor/ckeditor5-vue'
import '@ckeditor/ckeditor5-build-classic/build/translations/zh-cn'
import vUploader from 'v-uploader'//文件上传插件
import vPlayBack from 'v-playback'//视频预览插件

// 创建axios实例对象
let instance = axios.create({
  timeout: 6000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器，将Request Payload请求数据格式 ，转换为FormData格式
// instance.interceptors.request.use(
//   config => {
// 	var url = config.url;
// 	if(!url.startsWith("/yp-admin")){
// 		config.data = qs.stringify(config.data) // 转为formdata数据格式
// 	}
//     return config
//   },
//   error => Promise.error(error)
// )

// v-uploader plugin global config
const uploaderConfig = {
  // file uploader service url
  uploadFileUrl: '/upload/uploadVUploaderFile',
  // file delete service url
  deleteFileUrl: '/upload/deleteFile',
  // set the way to show upload message(upload fail message)
  showMessage: (vue, message) => {
    //using v-dialogs to show message
    vue.$message({message: message,type: "error"});
    //vue.$dlg.alert(message, {messageType: 'error'});
  }
};



Vue.config.productionTip = false
Vue.prototype.ts = ts
Vue.prototype.hik = hik
Vue.use(ElementUI)
//使用axios实例对象
Vue.use(VueAxios, instance)
Vue.use(CKEditor)//富文本编辑器
Vue.use(vUploader, uploaderConfig);
Vue.use(vPlayBack)

//添加全局时间过滤器
//时间格式转换
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
//文件大小单位转换
Vue.filter('sizeConvert',function(data,format){
	if(!data || data == ''){
		return '';
	}
	//默认为MB
	if(!format){
		format = 'MB';
	}
	if("KB" == format.toUpperCase()){
		return (Number(data)/1024).toFixed(2) + "KB";
	}else if("MB" == format.toUpperCase()){
		return (Number(data)/(1024 * 1024)).toFixed(2) + "MB";
	}else if("GB" == format.toUpperCase()){
		return (Number(data)/(1024 * 1024 * 1024)).toFixed(2) + "GB";
	}else if("TB" == format.toUpperCase()){
		return (Number(data)/(1024 * 1024 * 1024 * 1024)).toFixed(2) + "TB";
	}
});
//保留小数点位数
Vue.filter('toFixed',function(data,format){
	//如果为null或非数字
	if(data == null || isNaN(data)){
		return '';
	}
	if(!format){
		format = 2;
	}
	return Number(data).toFixed(format);
});
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
