<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>标签管理</el-breadcrumb-item>
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
								<el-input v-model="queryParam.labelName" @keyup.enter.native="reQuery();" placeholder="请输入标签名称" style="width: 60%"></el-input>
								<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery()" round>查询</el-button>
							</span>
							<span style="float: right;">
								<!--<el-button type="danger" @click="deleteBatch" round>批量删除</el-button>-->
								<el-button type="primary" @click="dialogVisible = true;dlgTitle='添加标签';resetForm('labelForm');" round>添加</el-button>
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
							<el-table-column label="标签名称" width="190" align="center">
								<template slot-scope="scope">
									{{scope.row.labelName}}
								</template>
							</el-table-column>
							<el-table-column prop="option" label="操作" width="auto" align="center">
								<template slot-scope="scope">
									<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改权限';init(scope);" title="修改" circle></el-button>
									<el-button type="danger" icon="el-icon-delete" @click="deleteOne(scope.row.id)" title="删除" circle></el-button>
								</template>
							</el-table-column>
						</el-table>
						<div class="block" align="left">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.page" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
							</el-pagination>
						</div>
					</el-main>
					<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="35%">
						<el-form :model="label" :inline="true" status-icon :rules="rules" ref="labelForm" label-width="100px">
							<el-form-item label="标签名称" prop="labelName">
								<el-input type="text" v-model="label.labelName"></el-input>
							</el-form-item>
							<el-form-item label="标签类型" prop="type">
								<el-select v-model="label.type" clearable style="width: 202px;" placeholder="请选择">
									<el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="是否标签组" prop="permissionType">
								<el-select v-model="label.group" clearable style="width: 202px;" placeholder="请选择">
									<el-option v-for="item in groups" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
								</el-select>
							</el-form-item>
						</el-form>
						<span slot="footer" class="dialog-footer">
						    <el-button @click="dialogVisible = false;">取 消</el-button>
						    <el-button type="danger" @click="resetForm('labelForm')">重置</el-button>
						    <el-button type="primary" @click="submitForm('labelForm')">确 定</el-button>
						</span>
					</el-dialog>
				</el-container>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'labelManage',
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
				label:{
					labelName:"",
					type:"",
					group:""
					
				},
				types: [
					{
						value: 1,
						label: '分类型'
					},
					{
						value: 2,
						label: '数值类型'
					},
					{
						value: 3,
						label: '时间类型'
					},
					{
						value: 4,
						label: '数值转时间类型'
					},
					{
						value: 5,
						label: '时间转数值类型'
					}
				],
				groups: [
					{
						value: 1,
						label: '标签组'
					},
					{
						value: 2,
						label: '标签'
					},
					{
						value: 3,
						label: '标签下选项'
					}
				],
				queryHeader:{
					headers: { Authorization:"" }
				},
				queryParam:{
					labelName:'',
					parentId:'',
					pageNum:'',
					pageSize:''
				},
				rules: {
					labelName: [{
						required: true,
						message: '请输入标签名称',
						trigger: 'blur'
					}],
					type: [{
						required: true,
						message: '请选择标签类别',
						trigger: 'change'
					}],
					group: [{
						required: true,
						message: '请选择是否标签组',
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
							label: item.labelName,
							children: vm.deep(vm, item.children)
						}
					} else {
						return {
							id: item.id,
							label: item.labelName
						}
					}
				});
				return items;
			},
			init(data) {
				this.label = data.row;
			},
			iconSelect(data) {
				this.label.labelIcon = data;
				this.iconDialogVisible = false;
			},
			handleCurrentChange(val) {
				//修改当前页码
				this.pageData.page = val;
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
				queryParam.page = this.pageData.page;
				queryParam.size = this.pageData.size;
				queryParam.group = 0;
				var vm = this;
				vm.ts.doPost(vm, '/api/label/findLabels', queryParam, this.queryHeader, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.data, function(item, index) {
							return {
								index: (data.data.data.page - 1) * data.data.data.size + index +1,
								id: item.id,
								labelName: item.labelName
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			reQueryTree() {				
				this.ts.loginChuangDingIDI(this,function(vm){
					vm.ts.doGet(vm, '/api/label/labelTree', vm.form, vm.queryHeader.headers, function(vm, data) {
						if (data.data.code == 200) {
							vm.data = vm.deep(vm, data.data.data);
						} else {
	
						}
					})	
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
							vm.ts.doPost(vm, '/api/label/deleteById', {
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
				var formData = this.label;
				formData.parentId = this.curParentId;
				var vm = this;
				var conf = this.queryHeader;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/api/label/save', formData, conf, function(vm, data) {
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