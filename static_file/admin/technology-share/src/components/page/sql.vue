<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>SQL管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: right;">
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加SQL语句';edit=true;resetForm('sqlForm');"  round>添加SQL语句</el-button>
						<el-button type="primary" @click="transferVisible = true;transferDlgTitle='数据传输';" round>数据传输(不选择SQL)</el-button>
						<el-button type="primary" round>数据传输(选择SQL)</el-button>
					</span>
				</el-row>
			</el-header>
			<el-container>
				<el-aside style="width: auto;">
					<el-table v-loading="loading" :data="tableData" :highlight-current-row="true" border style="width: 100%;">
						<el-table-column label="序号" width="60" align="center">
							<template slot-scope="scope">
								{{scope.row.index}}
							</template>
						</el-table-column>
						<el-table-column label="名称" width="150" align="center">
							<template slot-scope="scope">
								{{scope.row.name}}
							</template>
						</el-table-column>
						<el-table-column label="所属数据库" width="150" align="center">
							<template slot-scope="scope">
								{{scope.row.databaseName}}
							</template>
						</el-table-column>
						<el-table-column label="拥有角色" width="150" align="center">
							<template slot-scope="scope">
								{{scope.row.roleNames}}
							</template>
						</el-table-column>
						<el-table-column label="描述" width="200" align="center">
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
						<el-table-column prop="option" label="操作" width="250" align="center">
							<template slot-scope="scope">
								<el-button type="success" icon="ali el-icon-zhixing" @click="doProcess(scope.row)" title="执行SQL" circle></el-button>
								<el-button type="primary" icon="el-icon-info" @click="dialogVisible = true;dlgTitle='SQL语句详情';init(scope);" title="详情" circle></el-button>
								<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改SQL语句';init(scope);" title="修改" circle></el-button>
								<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-aside>
				<el-main>
					<el-input type="textarea" v-model="doProcessSQL" class="processSQL" rows="15" style="overflow-y:visible;" resize="none" disabled></el-input>
					<i style="margin-left: 0px;">执行结果：&emsp;&emsp;&emsp;(耗时：<i>{{processTime|toFixed}}</i>秒)</i>
					<el-input type="textarea" v-model="doProcessResult" rows="15" style="overflow-y:visible;" resize="none" disabled></el-input>
				</el-main>
			</el-container>			
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="40%">
				<el-form :model="sql" :inline="true" status-icon :rules="rules" ref="sqlForm" label-width="100px">
					<el-form-item label="名称" prop="name">
						<el-input type="text" v-model="sql.name"></el-input>
					</el-form-item>
					<el-form-item label="描述" prop="description">
						<el-input type="text" v-model="sql.description"></el-input>
					</el-form-item>
					<el-form-item label="拥有角色" prop="roleIds">
						<el-select v-model="sql.roleIds" style="width:202px;" multiple placeholder="请选择">
							<el-option v-for="item in roles" :key="item.id" :label="item.roleName" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="所属数据库" prop="databaseId">
						<el-select v-model="sql.databaseId" style="width:202px;" placeholder="请选择">
							<el-option v-for="item in databases" :key="item.id" :label="item.name" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="是否启用" prop="enable">
						<el-select v-model="sql.enable" style="width:202px;" placeholder="请选择">
							<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="SQL语句" prop="sql" style="width:100%;">
						<el-input type="textarea" :rows="20" :cols="70" v-model="sql.sqlStatement"></el-input>
					</el-form-item>
					
				</el-form>
				<span v-if="edit" slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;resetForm('sqlForm');">取 消</el-button>
				    <el-button type="danger" @click="resetForm('sqlForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('sqlForm')">确 定</el-button>
				</span>
			</el-dialog>

			<el-dialog :title="transferDlgTitle" :visible.sync="transferVisible" width="40%">
				<!--<el-form :model="sql" :inline="true" status-icon :rules="rules" ref="sqlForm" label-width="100px">
				</el-form>-->
				<el-container>
					<el-main>
						<el-header style="height: 15px;">
							<el-tag style="width:150px;">数据源</el-tag>
						</el-header>
						<el-main>
							<el-select v-model="sql.enable" style="width:202px;" placeholder="请选择">
								<el-option v-for="item in databases" :key="item.id" :label="item.name" :value="item.id">
								</el-option>
							</el-select>
						</el-main>
					</el-main>

					<el-main>
						<i v-for="i in arrow" class="el-icon-d-arrow-right"></i>
					</el-main>

					<el-main>
						<el-header style="height: 15px;">
							<el-tag style="width:150px;">目标</el-tag>
						</el-header>
						<el-main>
							<el-select v-model="sql.enable" style="width:202px;" placeholder="请选择">
								<el-option v-for="item in databases" :key="item.id" :label="item.name" :value="item.id">
								</el-option>
							</el-select>
						</el-main>
					</el-main>
				</el-container>
					<i>耗时：{{timeUse}}ms</i>
				<el-main>
					<el-progress :text-inside="true" :stroke-width="18" :percentage="100"></el-progress>
				</el-main>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" >重置</el-button>
				    <el-button type="primary" >确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'sql',
		data() {
			return {
				tableData: [],
				pageData: {},
				roles: [],
				loading: false,
				edit:false,
				sql: {},
				formData: {},
				queryParam: {},
				doProcessSQL: "",
				doProcessResult: "",
				processTime: 0,
				options: [{
					value: 1,
					label: '启用'
				}, {
					value: 0,
					label: '禁用'
				}],
				databases: [],
				dialogVisible: false,
				transferVisible: false,
				dlgTitle: '',
				transferDlgTitle: '',
				rules: {
					name: [{
						required: true,
						message: '请输入SQL名称',
						trigger: 'blur'
					}],
					enable: [{
						required: true,
						message: '请选择启用状态',
						trigger: 'change'
					}]
				},
				timeUse: 0,
				arrow: 12
			}
		},
		methods: {
			reQuery() {
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/sql/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								name: item.name,
								databaseName: item.databaseName,
								roleNames: item.roleNames,
								description: item.description,
								sqlStatement: item.sqlStatement,
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
			init(scope) {
				if (scope) {
					this.edit = true;
					this.sql = scope.row;
					if (scope.row.enable) {
						this.sql.enable = 1;
					} else {
						this.sql.enable = 0;
					}
					if (scope.row.roleIds) {
						this.sql.roleIds = JSON.parse(scope.row.roleIds);
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
			queryDatabase(){
				this.ts.doPost(this, '/admin/database/queryData',null,null,function(vm,data){
					if(data.data.code == 200){
						vm.databases = data.data.data;
					}
				});
			},
			submitForm(form) {
				var formData = this.sql;
				formData.roleIds = JSON.stringify(this.sql.roleIds);
				var vm = this;
				vm['form'] = form;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/sql/save', formData, null, function(vm, data) {
							var type = '';
							if (data.data.code == 200) {
								vm.reQuery();
								vm.dialogVisible = false;
								vm.resetForm(vm.form);
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
			resetForm(form) {
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.extCss = {};
			},
			deleteOne(id) {
				//根据ID删除
				var vm = this;
				vm.$confirm('此操作将删除该SQL语句, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/sql/deleteById', {
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
			doProcess(row){
				//执行SQL语句
				this.doProcessSQL = row.sqlStatement;

			}
		},
		mounted: function() {
			this.reQuery();
			this.queryRole();
			this.queryDatabase();
		}

	}
</script>
<style scoped>
	.el-textarea.is-disabled >>> .el-textarea__inner{
		color: green;
	}
</style>