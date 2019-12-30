<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>Redis管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row style="width: 50%;float: left;">
					<span style="float: right;">
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加Redis链接';"  round>添加Redis链接</el-button>
					</span>
				</el-row>
				<el-row style="width: 50%;float: left;">
					<span style="float: right;">
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加Redis键值';"  round>切换数据库</el-button>
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加Redis键值';"  round>添加Redis键值</el-button>
					</span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" :data="tableData" @row-click="rowClick" :highlight-current-row="true" border style="width: 50%;float: left;">
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="Host" width="120" align="center">
						<template slot-scope="scope">
							{{scope.row.host}}
						</template>
					</el-table-column>
					<el-table-column label="Port" width="60" align="center">
						<template slot-scope="scope">
							{{scope.row.port}}
						</template>
					</el-table-column>
					<el-table-column label="登录用户" width="190" align="center">
						<template slot-scope="scope">
							{{scope.row.username}}
						</template>
					</el-table-column>
					<el-table-column label="登录密码" width="190" align="center">
						<template slot-scope="scope">
							{{scope.row.password}}
						</template>
					</el-table-column>
					<el-table-column label="是否启用" width="80" align="center">
						<template slot-scope="scope">
							<span v-if="scope.row.enable">启用</span>
							<span v-if="!scope.row.enable">禁用</span>
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="120" align="center">
						<template slot-scope="scope">
							<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改Redis链接信息';init(scope);" title="修改" circle></el-button>
							<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
				<el-table v-loading="loading" :data="redisData" max-height="800px" :highlight-current-row="true" border style="width: 45%;float: left;">
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="Key" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.key}}
						</template>
					</el-table-column>
					<el-table-column label="数据结构" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.type}}
						</template>
					</el-table-column>
					<el-table-column label="过期时间" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.persist}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="130" align="center">
						<template slot-scope="scope">
							<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="40%">
				<el-form :model="redis" :inline="true" status-icon :rules="rules" ref="redisForm" label-width="100px">
					<el-form-item label="Host"  prop="host">
						<el-input type="text" v-model="redis.host"></el-input>
					</el-form-item>
					<el-form-item label="Port"  prop="port">
						<el-input type="text" v-model="redis.port"></el-input>
					</el-form-item>
					<el-form-item label="登录用户"  prop="username">
						<el-input type="text" v-model="redis.username"></el-input>
					</el-form-item>
					<el-form-item label="登录密码"  prop="password">
						<el-input type="text" v-model="redis.password"></el-input>
					</el-form-item>
					<el-form-item label="用户角色"  prop="roleIds">
						<el-select v-model="redis.roleIds" style="width:202px;" multiple placeholder="请选择">
							<el-option v-for="item in roles" :key="item.id" :label="item.roleName" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="是否启用"  prop="enable">
						<el-select v-model="redis.enable" style="width:202px;" placeholder="请选择">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
				</el-form>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" @click="resetForm('redisForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('redisForm')">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>	
</template>
<script>
	export default{
		name:'redis',
		data(){
			return {
				tableData:[],
				pageData:{},
				redisData:[],
				roles:[],
				loading:false,
				redis:{},
				formData:{},
				queryParam:{},
				options: [{
					value: 1,
					label: '启用'
				}, {
					value: 0,
					label: '禁用'
				}],
				dialogVisible:false,
				dlgTitle:'',
				rules:{},
				curRedisHost:'',
				curRedisPort:'',
				curRedisUsername:'',
				curRedisPassword:''
			}
		},
		methods:{
			reQuery(){
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/redis/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								host: item.host,
								port: item.port,
								username: item.username,
								password: item.password,
								enable: item.enable
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			reQueryRedis(){
				this.loading = false;
				var vm = this;
				var queryParam = {
					host:vm.curRedisHost,
					port:vm.curRedisPort,
					username:vm.curRedisUsername,
					password:vm.curRedisPassword
				};
				vm.ts.doPost(vm, '/admin/redis/queryRedisData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						console.log(data.data);
						vm.redisData = $.map(data.data.data, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								key: item.key,
								type: item.type,
								persist: item.persist
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			init(scope){
				if(scope){
					this.redis = scope.row;
					if(scope.row.enable){
						this.redis.enable = 1;
					}else {
						this.redis.enable = 0;
					}
					if(scope.row.roleIds){
						this.redis.roleIds = JSON.parse(scope.row.roleIds);
					}
				}else {
					this.redis = {};
				}
			},
			queryRole() {
				this.ts.doPost(this, '/admin/role/queryData', null, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.roles = data.data.data;
					}
				});
			},
			submitForm(form){
				var formData = this.redis;
				formData.roleIds = JSON.stringify(this.redis.roleIds);
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/redis/save', formData, null, function(vm, data) {
							var type = '';
							if (data.data.code == 200) {
								vm.reQuery();
								vm.dialogVisible = false;
								type = 'success';
							} else {
								type = 'error';
							}
							vm.$message({
								message: data.data.msg,
								type: type
							});
						});
					} else {
						return false;
					}
				});
			},
			resetForm(form){
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.extCss = {};
			},
			deleteOne(id){
				//根据ID删除
				var vm = this;
				vm.$confirm('此操作将删除该Redis链接, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/redis/deleteById', {
								id: id
							}, null, function(vm, data) {
								if (data.data.code == 200) {
									vm.reQuery();
									vm.instance.confirmButtonLoading = false;
									vm.done();
								}
							});
						} else {
							done();
						}
					}
				}).then(() => {
					this.$message({
						type: 'success',
						message: '删除成功!'
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			rowClick(row, column, event){
				this.curRedisHost = row.host;
				this.curRedisPort = row.port;
				this.curRedisUsername = row.username;
				this.curRedisPassword = row.password;
				this.reQueryRedis();
			}
		},
		mounted:function(){
			this.reQuery();
			this.queryRole();
		}
		
	}
	
</script>
<style scoped>
	
</style>