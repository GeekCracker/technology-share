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
						<el-row style="text-align: right;">
							<el-button type="danger" round>删除</el-button>
							<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加权限'" round>添加</el-button>
						</el-row>
					</el-header>
					<el-main>
						<el-table v-loading="loading" :data="tableData" border style="width: 100%;">
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
							<el-table-column label="链接地址" width="210" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionLink}}
								</template>
							</el-table-column>
							<el-table-column label="权限类型" width="80" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionType}}
								</template>
							</el-table-column>
							<el-table-column label="权限图标" width="80" align="center">
								<template slot-scope="scope">
									{{scope.row.permissionIcon}}
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
									<el-button type="primary" icon="el-icon-info" title="详情" circle></el-button>
									<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改权限'" title="修改" circle></el-button>
									<el-button type="danger" icon="el-icon-delete" title="删除" circle></el-button>
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
						<el-form v-model="permission" :inline="true" status-icon v-rules="rules" ref="permissionForm" label-width="100px">
							<el-form-item label="权限名称" prop="permissionName">
								<el-input type="text" v-model="permission.permissionName" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="权限链接" prop="permissionLink">
								<el-input type="text" v-model="permission.permissionLink" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="权限链接" prop="permissionLink">
								<el-input type="text" v-model="permission.permissionLink" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="权限链接" prop="permissionLink">
								<el-input type="text" v-model="permission.permissionLink" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="权限链接" prop="permissionLink">
								<el-input type="text" v-model="permission.permissionLink" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<span slot="footer" class="dialog-footer">
						    <el-button @click="dialogVisible = false">取 消</el-button>
						    <el-button type="danger" @click="resetForm('permissionForm')">重置</el-button>
						    <el-button type="primary" @click="submitForm('permissionForm')">确 定</el-button>
						</span>
					</el-dialog>
				</el-container>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'permission',
		data() {
			var validatePermissionName = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入权限名称'));
				} else {
					if (this.permission.permissionName !== '') {
						this.$refs.permissionForm.validateField('permissionName');
					}
					callback();
				}
			};
			var validatePermissionLink = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入权限链接'));
				} else {
					if (this.permission.permissionLink !== '') {
						this.$refs.permissionForm.validateField('permissionLink');
					}
					callback();
				}
			};
			return {
				data: [],
				pageData:{},
				curParentId: '',
				defaultProps: '',
				highlightCurrent: true,
				accordion: true,
				tableData: [],
				loading: false,
				dialogVisible: false,
				dlgTitle: '',
				permission: {
					permissionName: '',
					permissionLink: '',
					permissionType: '',
					permissionIcon: '',
					permissionStatus: '',
					permissionSort: ''
				},
				rules: {
					permissionName: [{
						validator: validatePermissionName,
						trigger: 'blur'
					}],
					permissionLink: [{
						validator: validatePermissionLink,
						trigger: 'blur'
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
			handleCurrentChange(val){
				//修改当前页码
				this.pageData.current = val;
				console.log(val);
				//重新查询
				this.reQuery();
				
			},
			handleSizeChange(val){
				//修改每页显示条数
				this.pageData.size = val;
				console.log(val);
				//重新查询
				this.reQuery();
			},
			reQuery(){
				var parentId = this.curParentId;
				var pageNum = this.pageData.current;
				var pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doQuery(vm, '/admin/permission/queryPageData', 'post', {
					parentId: parentId,
					pageNum:pageNum,
					pageSize:pageSize
				}, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								permissionName: item.permissionName,
								permissionLink: item.permissionLink,
								permissionType: item.permissionType,
								permissionIcon: item.permissionIcon,
								permissionStatus: item.permissionStatus,
								permissionSort: item.permissionSort
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			handleClick(data, checked, node) {
				this.loading = true;
				if (checked && data.children) {
					//更新右侧权限列表，根据权限查询子权限
					this.curParentId = data.id;
					this.reQuery();
				} else {
					this.tableData = [];
					this.loading = false;
				}
			}
		},
		mounted: function() {
			var vm = this;
			vm.ts.doQuery(vm, '/admin/permission/permissionTree', 'post', this.form, null, function(vm, data) {
				if (data.data.code == 200) {
					vm.data = vm.deep(vm, data.data.data);
				} else {

				}
			});
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