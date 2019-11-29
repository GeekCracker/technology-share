function init(){
	
}

/**
 * 
 * @param vm 当前vue对象
 * @param url 请求url
 * @param type 请求方式get、post、put、delete等
 * @param param 请求参数
 * @param conf 配置信息
 * @param func 回调函数
 */
function doQuery(vm,url,type,param,conf,func){

	if(!vm){
		throw 'vm is not defined';
	}
	if(!url){
		throw 'url is not defined';
	}
	vm.axios({
		type:type,
		url:url,
		params:param,
		conf:conf
	})
	.then(function(data){
		if(func){
			func(vm,data);
		}
	})
	.catch(function(err){
		console.log(err);
	});
}
export default{
	init,
	doQuery
}
