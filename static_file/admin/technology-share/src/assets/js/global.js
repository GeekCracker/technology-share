function init(){
	
}
/**
 * @param vm 当前vue对象
 * @param url 请求url
 * @param params 请求参数
 * @param conf 配置信息
 * @param func 回调函数
 */
function doGet(vm,url,params,headers,func){
	if(!vm){
		throw 'vm is not defined';
	}
	if(!url){
		throw 'url is not defined';
	}
	vm.axios.get(
		url,
		{
			params:params,
			headers:headers
		}
	)
	.then(function(data){
		if(func){
			func(vm,data);
		}
	})
	.catch(function(err){
		console.log(err);
	});
}
/**
 * @param vm 当前vue对象
 * @param url 请求url
 * @param data 请求数据
 * @param conf 配置信息
 * @param func 回调函数
 */
function doPost(vm,url,data,conf,func){
	if(!vm){
		throw 'vm is not defined';
	}
	if(!url){
		throw 'url is not defined'
	}
	vm.axios.post(
		url,
		data,
		conf
	)
	.then(function(data){
		if(func){
			func(vm,data);
		}
	})
	.catch(function(err){
		console.log(err);
	});
}
function loginChuangDingIDI(vue,func){
	vue.ts.doPost(vue, '/api/auth',{username:"admin",password:"1q2w3e4r"},null, function(vm, data) {
		if (data.data.code == 200) {
			vm.queryHeader.headers.Authorization = "Authorization "+data.data.data.token;
			if(func){
				func(vm);
			}
		} else {
			vm.queryHeader.headers.Authorization = '';
		}
	});
}
export default{
	init,
	doGet,
	doPost,
	loginChuangDingIDI
}
