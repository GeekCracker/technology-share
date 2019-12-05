<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>权限管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-aside>
				<el-tree :data="data" ref="tree" :accordion="accordion" :highlight-current="highlightCurrent" @node-click="handleClick" node-key="id" :props="defaultProps">
				</el-tree>
			</el-aside>
			<el-main>
				<el-container>
					<el-header style="height: 40px">
						<el-row >
							<span style="float: left;">
								<el-input v-model="queryParam.permissionName" placeholder="请输入权限名称" style="width: 60%"></el-input>
								<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery()" round>查询</el-button>
							</span>
							<span style="float: right;">
								<el-button type="danger" @click="deleteBatch" round>批量删除</el-button>
								<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加权限';resetForm('permissionForm');" round>添加</el-button>
							</span>
						</el-row>
					</el-header>
					<el-main>
						<el-table v-loading="loading" :data="tableData" @selection-change="rowSelect" @select-all="selectAll" border style="width: 100%;">
							<el-table-column type="selection" width="55">
							</el-table-column>
							<el-table-column label="序号" width="80" align="center">
								<template slot-scope="scope">
									{{scope.row.index}}
								</template>
							</el-table-column>
							<el-table-column label="权限名称" width="190" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionName}}
								</template>
							</el-table-column>
							<el-table-column label="权限链接" width="210" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionLink}}
								</template>
							</el-table-column>
							<el-table-column label="权限位" width="210" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionBit}}
								</template>
							</el-table-column>
							<el-table-column label="权限类型" width="80" align="center">
								<template slot-scope="scope">
									<span v-if="scope.row.permissionType == 1">功能权限</span>
									<span v-else-if="scope.row.permissionType == 2">按钮权限</span>
									<span v-else></span>
								</template>
							</el-table-column>
							<el-table-column label="权限图标" width="80" align="center">
								<template slot-scope="scope">
									<i :class="scope.row.permissionIcon" style="font-size: 23px;"></i>
								</template>
							</el-table-column>
							<el-table-column label="权限状态" width="80" align="center">
								<template slot-scope="scope">
									<span v-if="scope.row.permissionStatus">启用</span>
									<span v-if="!scope.row.permissionStatus">禁用</span>
								</template>
							</el-table-column>
							<el-table-column label="排序序号" width="80" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionSort}}
								</template>
							</el-table-column>
							<el-table-column prop="option" label="操作" width="auto" align="center">
								<template slot-scope="scope">
									<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改权限';init(scope);" title="修改" circle></el-button>
									<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
									<el-button type="info" v-if="!scope.row.permissionStatus" icon="el-icon-success" title="启用" circle></el-button>
									<el-button type="info" v-if="scope.row.permissionStatus" icon="el-icon-error" title="禁用" circle></el-button>
								</template>
							</el-table-column>
						</el-table>
						<div class="block" align="left">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
							</el-pagination>
						</div>
					</el-main>
					<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="35%">
						<el-form :model="permission" :inline="true" status-icon :rules="rules" ref="permissionForm" label-width="100px">
							<el-form-item label="权限名称" prop="permissionName">
								<el-input type="text" v-model="permission.permissionName"></el-input>
							</el-form-item>
							<el-form-item label="权限链接" prop="PermissionLink">
								<el-input type="text" v-model="permission.permissionLink"></el-input>
							</el-form-item>
							<el-form-item label="权限位" prop="permissionBit">
								<el-input type="text" v-model="permission.permissionBit"></el-input>
							</el-form-item>
							<el-form-item label="权限类型" prop="permissionType">
								<el-select v-model="permission.permissionType" clearable style="width: 202px;" placeholder="请选择">
									<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="排序序号" prop="permissionSort">
								<el-input type="text" v-model="permission.permissionSort"></el-input>
							</el-form-item>
							<el-form-item label="权限图标" prop="permissionIcon">
								<i :class="permission.permissionIcon" style="display: inline-block;width: 120px;font-size: 30px;"></i>
								<el-button type="primary" @click="iconDialogVisible=true">选择</el-button>
							</el-form-item>
						</el-form>
						<span slot="footer" class="dialog-footer">
						    <el-button @click="dialogVisible = false;">取 消</el-button>
						    <el-button type="danger" @click="resetForm('permissionForm')">重置</el-button>
						    <el-button type="primary" @click="submitForm('permissionForm')">确 定</el-button>
						</span>
					</el-dialog>

					<el-dialog :title="iconTitle" :visible.sync="iconDialogVisible" width="50%">
						<elIcon @iconSelect="iconSelect($event)"></elIcon>
					</el-dialog>
				</el-container>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	import elIcon from './elIcon.vue'
	export default {
		name: 'permission',
		components: {
			elIcon
		},
		data() {
			return {
				data: [],
				pageData: {},
				curParentId: '',
				defaultProps: '',
				highlightCurrent: true,
				accordion: true,
				tableData: [],
				selections: [],
				loading: false,
				dialogVisible: false,
				iconDialogVisible: false,
				dlgTitle: '',
				iconTitle: '请选择图标',
				options: [{
					value: 1,
					label: '功能权限'
				}, {
					value: 2,
					label: '按钮权限'
				}],
				permission: {
					id: '',
					parentId: '',
					permissionName: '',
					permissionLink: '',
					permissionBit: '',
					permissionType: '',
					permissionIcon: '',
					permissionStatus: true,
					permissionSort: '',
					parentId: ''
				},
				queryParam:{
					permissionName:'',
					parentId:'',
					pageNum:'',
					pageSize:''
				},
				rules: {
					permissionName: [{
						required: true,
						message: '请输入权限名称',
						trigger: 'blur'
					}],
					permissionType: [{
						required: true,
						message: '请选择权限类别',
						trigger: 'change'
					}]
				}
			}
		},
		methods: {
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
			},
			init(data) {
				this.permission = data.row;
			},
			iconSelect(data) {
				this.permission.permissionIcon = data;
				this.iconDialogVisible = false;
			},
			handleCurrentChange(val) {
				//修改当前页码
				this.pageData.current = val;
				//重新查询
				this.reQuery();

			},
			handleSizeChange(val) {
				//修改每页显示条数
				this.pageData.size = val;
				//重新查询
				this.reQuery();
			},
			reQuery() {
				this.loading=false;
				var queryParam = this.queryParam;
				queryParam.parentId = this.curParentId;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/permission/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								permissionName: item.permissionName,
								permissionLink: item.permissionLink,
								permissionBit: item.permissionBit,
								permissionType: item.permissionType,
								permissionIcon: item.permissionIcon,
								permissionStatus: item.permissionStatus,
								permissionSort: item.permissionSort,
								parentId: item.parentId
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
				//根据ID删除权限，包含子权限
				var vm = this;
				vm.$confirm('此操作将删除该权限, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/permission/deleteById', {
								id: id
							}, null, function(vm, data) {
								if (data.data.code == 200) {
									vm.reQuery();
									vm.reQueryTree();
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
			deleteBatch: function() {
				var selections = this.selections;
				if (!selections || selections.length == 0) {
					this.$message('请选择需要删除的权限');
					return;
				}
				//根据ID删除权限，包含子权限
				var vm = this;
				vm.$confirm('此操作将删除所有选中的权限, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/permission/deleteBatchById', {
								deleteList: JSON.stringify(selections)
							}, null, null, function(vm, data) {
								if (data.data.code == 200) {
									vm.reQuery();
									vm.reQueryTree();
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
			handleClick(data, node) {
				this.loading = true;
				this.curParentId = data.id;
				if (data.children) {
					//更新右侧权限列表，根据权限查询子权限
					this.reQuery();
				} else {
					this.tableData = [];
					this.loading = false;
				}
			},
			submitForm: function(form) {
				var formData = this.permission;
				formData.parentId = this.curParentId;
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/permission/save', formData, null, function(vm, data) {
							var type = '';
							if (data.data.code == 200) {
								vm.reQueryTree();
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
			resetForm: function(form) {
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.permission = {
					id: '',
					parentId: '',
					permissionName: '',
					permissionLink: '',
					permissionBit: '',
					permissionType: '',
					permissionIcon: '',
					permissionStatus: true,
					permissionSort: '',
					parentId: ''
				};
			},
			selectAll: function(selection) {
				//selection是选中行的数组
				this.selections = $.map(selection, function(item, index) {
					return {
						id: item.id
					};
				});
			},
			rowSelect: function(selection) {
				this.selections = $.map(selection, function(item, index) {
					return {
						id: item.id
					};
				});
			}
		},
		mounted: function() {
			this.reQueryTree();
			this.reQuery();
		}
	}
</script>
<style scoped>
	.el-aside {
		background-color: #ffffff;
		color: #333;
		text-align: center;
		min-height: 800px;
	}
	
	.el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		padding: 5px;
	}
	
	.el-header {
		padding: 0;
	}
</style>