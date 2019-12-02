<template>
	<el-container>
		
		<el-form ref="form" :model="form" span="24" label-width="80px">
			<h2>技术分享-后台管理系统</h2><br /><br />
			<el-form-item label="用户名：">
				<el-input v-model="form.username"></el-input>
			</el-form-item>
			<el-form-item label="密码：">
				<el-input type="password" v-model="form.password"></el-input>
			</el-form-item>
			<el-form-item>
			    <el-button type="primary" @click="login">立即登录</el-button>
			    <el-button>忘记密码</el-button>
		  	</el-form-item>
		</el-form>
	</el-container>
</template>
<script>
	export default {
		name: 'login',
		data() {
			return {
				form: {
					username:'',
					password:''
				}
			}
		},
		methods: {
			login(){
				this.ts.doGet(this,'/admin/user/login',this.form,null,function(vm,data){
					if(data.data.code == 200){
						var user = data.data.data;
						localStorage.setItem("user",JSON.stringify(user));
						localStorage.setItem("username",vm.form.username);
						localStorage.setItem("password",vm.form.password);
						vm.$router.push({
							path: '/home',
						});
					}else {
						vm.$message({
				          message: data.data.msg,
				          type: 'warning'
				        });
					}
				});
			}
		},
		mounted: function() {
			this.form.username = localStorage.getItem("username");
			this.form.password = localStorage.getItem("password");
		}
	}
</script>
<style scoped>
	.el-container{
		background-repeat:no-repeat;
		background-size:cover;
		background-image: url(../../../static/img/17.jpeg);
	}
	.el-form{
		margin: 15% auto 20% 20%;
	}
</style>