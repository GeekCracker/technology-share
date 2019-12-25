<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>角色管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;">
					<el-input style="width: 60%" v-model="queryParam.roleName" placeholder="请输入角色名称" ></el-input>
					<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery()" round>查询</el-button>
				</span>
					<span style="float: right;">
					<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加角色';resetForm('roleForm');"  round>添加</el-button>
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
					<el-table-column label="角色名称" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.roleName}}
						</template>
					</el-table-column>
					<el-table-column label="是否启用" width="80" align="center">
						<template slot-scope="scope">
							<span v-if="scope.row.roleStatus">启用</span>
							<span v-else>禁用</span>
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改角色';init(scope);" title="修改" circle></el-button>
							<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
							<el-button type="info" v-if="!scope.row.permissionStatus" icon="el-icon-success" title="启用" circle></el-button>
							<el-button type="info" v-if="scope.row.permissionStatus" icon="el-icon-error" title="禁用" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="35%">
				<el-container>
					<el-aside>
						<el-header><h3>权限树</h3></el-header>
						<el-tree :check-strictly="true" :data="data" ref="tree" show-checkbox :accordion="accordion" :highlight-current="highlightCurrent" node-key="id" :props="defaultProps">
						</el-tree>
					</el-aside>
					<el-main>
						<el-form :model="role" :inline="true" status-icon :rules="rules" ref="roleForm" label-width="100px">
							<el-form-item label="角色名称" prop="roleName">
								<el-input type="text" v-model="role.roleName"></el-input>
							</el-form-item>
						</el-form>
					</el-main>
				</el-container>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" @click="resetForm('roleForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('roleForm')">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: "role",
		data() {
			return {
				data: [],
				tableData: [],
				defaultProps: '',
				highlightCurrent: true,
				accordion: true,
				queryParam: {
					pageNum: '',
					pageSize: ''
				},
				form: {

				},
				pageData: {},
				loading: false,
				dlgTitle: '',
				dialogVisible: false,
				role: {
					roleName: '',
					roleStatus: true,
					pIds: ''
				},
				roleForm: {},
				rules: {
					roleName: [{
						required: true,
						message: '请输入角色名称',
						trigger: 'blur'
					}]
				}

			}
		},
		methods: {
			rowSelect() {

			},
			selectAll() {

			},
			reQuery() {
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/role/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								roleName: item.roleName,
								roleStatus: item.roleStatus
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			reQueryTree() {
				this.ts.doPost(this, '/admin/permission/permissionTree', this.form, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.data = vm.deep(vm, data.data.data);
					} else {

					}
				});
			},
			deleteOne(id) {
				//根据ID删除角色
				var vm = this;
				vm.$confirm('此操作将删除该角色, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/role/deleteById', {
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
			init(data) {
				this.role = data.row;
				//选择权限树
				this.ts.doPost(this, '/admin/role/queryById', {
					id: data.row.id
				}, null, function(vm, data) {
					if (data.data.code == 200) {
						var permission = data.data.data.permissionTree;
						//根据角色权限树，选择权限树
						vm.$refs.tree.setCheckedKeys([]);
						vm.deepSelect(vm, permission);
					} else {

					}
				});
			},
			submitForm(form) {
				var formData = this.role;
				var keys = this.$refs.tree.getCheckedKeys();
				formData.pIds = JSON.stringify(keys);
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/role/save', formData, null, function(vm, data) {
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
			resetForm(form) {
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.$refs.tree.setCheckedKeys([]);
				this.role = {
					id: '',
					roleName: '',
					roleStatus: true,
					pIds: ''
				};
			},
			deepSelect(vm, data) {
				$.map(data, function(item, index) {
					if (item.children && item.children.length > 0) {
						vm.deepSelect(vm, item.children);
						vm.$refs.tree.setChecked(item.id, true);
					} else {
						vm.$refs.tree.setChecked(item.id, true);
					}
				});
			},
			deep(vm, data) {
				var items = $.map(data, function(item, index) {
					if (item.children.length > 0) {
						return {
							id: item.id,
							label: item.permissionName,
							children: vm.deep(vm, item.children)
						}
					} else {
						return {
							id: item.id,
							label: item.permissionName
						}
					}
				});
				return items;
			}
		},
		mounted: function() {
			this.reQuery();
			this.reQueryTree();
		}
	}
</script>
<style scoped>
	.el-aside {
		background-color: #ffffff;
		color: #333;
		text-align: center;
		min-height: 300px;
	}
	
	.el-main {
		margin: 0px;
		padding: 5px;
	}
	
	.el-table {
		margin: 0px;
	}
	
	.el-header {
		padding: 0;
	}
</style>