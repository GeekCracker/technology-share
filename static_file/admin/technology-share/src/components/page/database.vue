<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>MySQL管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: right;">
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加MySQL链接';"  round>添加</el-button>
					</span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" :data="tableData" :highlight-current-row="true" border style="width: 100%;">
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="链接名称" width="120" align="center">
						<template slot-scope="scope">
							{{scope.row.name}}
						</template>
					</el-table-column>
					<el-table-column label="链接地址" width="350" align="center">
						<template slot-scope="scope">
							{{scope.row.url}}
						</template>
					</el-table-column>
					<el-table-column label="登录用户名" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.username}}
						</template>
					</el-table-column>
					<el-table-column label="登陆密码" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.password}}
						</template>
					</el-table-column>
					<el-table-column label="描述信息" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.description}}
						</template>
					</el-table-column>
					<el-table-column label="是否启用" width="80" align="center">
						<template slot-scope="scope">
							<span v-if="scope.row.enable">启用</span>
							<span v-if="!scope.row.enable">禁用</span>
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="primary" icon="el-icon-info" title="详情" circle></el-button>
							<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改MySQL链接';init(scope);" title="修改" circle></el-button>
							<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="40%">
				<el-form :model="database" :inline="true" status-icon :rules="rules" ref="databaseForm" label-width="100px">
					<el-form-item label="链接名称"  prop="name">
						<el-input type="text" v-model="database.name"></el-input>
					</el-form-item>
					<el-form-item label="MySQL链接"  prop="url">
						<el-input type="text" v-model="database.url"></el-input>
					</el-form-item>
					<el-form-item label="登录用户名"  prop="username">
						<el-input type="text" v-model="database.username"></el-input>
					</el-form-item>
					<el-form-item label="登陆密码"  prop="password">
						<el-input type="text" v-model="database.password"></el-input>
					</el-form-item>
					<el-form-item label="描述信息"  prop="description">
						<el-input type="text" v-model="database.description"></el-input>
					</el-form-item>
					<el-form-item label="拥有角色"  prop="roleIds">
						<el-select v-model="database.roleIds" style="width:202px;" multiple placeholder="请选择">
							<el-option v-for="item in roles" :key="item.id" :label="item.roleName" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="是否启用"  prop="enable">
						<el-select v-model="database.enable" style="width:202px;" placeholder="请选择">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
				</el-form>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" @click="resetForm('databaseForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('databaseForm')">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>	
</template>
<script>
	export default{
		name:'database',
		data(){
			return {
				tableData:[],
				pageData:{},
				roles:[],
				loading:false,
				database:{},
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
				rules:{}
			}
		},
		methods:{
			reQuery(){
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/database/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								name: item.name,
								url: item.url,
								username: item.username,
								password: item.password,
								description: item.description,
								roleIds: item.roleIds,
								roleName: item.roleName,
								enable: item.enable
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			init(scope){
				if(scope){
					this.database = scope.row;
					if(scope.row.enable){
						this.database.enable = 1;
					}else {
						this.database.enable = 0;
					}
					if(scope.row.roleIds){
						this.database.roleIds = JSON.parse(scope.row.roleIds);
					}
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
				var formData = this.database;
				formData.roleIds = JSON.stringify(this.database.roleIds);
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/database/save', formData, null, function(vm, data) {
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
				vm.$confirm('此操作将删除该MySQL链接, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/database/deleteById', {
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