<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>类别管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;">
					<el-input style="width: 60%" placeholder="请输入类别名称" ></el-input>
					<el-button type="primary" icon="iconfont el-iconchaxun" round>查询</el-button>
				</span>
					<span style="float: right;">
					<el-button type="primary" @click="dialogVisible=true;dlgTitle='添加类别';init()" round>添加</el-button>
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
					<el-table-column label="类别名称" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.typeName}}
						</template>
					</el-table-column>
					<el-table-column label="父级类别名称" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.parentTypeName}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="warning" @click="dialogVisible=true;dlgTitle='修改类别';init(scope)" icon="el-icon-edit" title="修改" circle></el-button>
							<el-button type="danger" @click="deleteOne(scope.row.id)" icon="el-icon-delete" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="block" align="left">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
					</el-pagination>
				</div>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="36%">
				<el-container>
					<el-form :model="type" :inline="true" status-icon :rules="rules" ref="typeForm" label-width="100px">
						<el-form-item label="类别名称" prop="typeName">
							<el-input type="text" v-model="type.typeName"></el-input>
						</el-form-item>
						<el-form-item label="父级类别" prop="parentId">
							<el-select  v-model="type.parentId" clearable @clear="parentClear" placeholder="请选择">
								<el-option style="height: auto;" :label="parentOption.label" :value="parentOption.value">
									<el-tree :check-strictly="true" :data="data" ref="tree" :accordion="accordion" @node-click="treeNodeClick" node-key="id" :props="defaultProps">
									</el-tree>
								</el-option>
							</el-select>
						</el-form-item>
					</el-form>
				</el-container>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" @click="resetForm('typeForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('typeForm')">确 定</el-button>
				</span>
			</el-dialog>

		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'type',
		data() {
			return {
				data: [],
				tableData: [],
				pageData: {},
				defaultProps: '',
				accordion:true,
				queryParam: {
					pageNum: '',
					pageSize: ''
				},
				loading: false,
				dialogVisible: false,
				dlgTitle: '',
				type: {},
				parentOption:{
					value:'',
					label:''
				},
				rules: {
					typeName: [{
						required: true,
						message: '请输入类别名称',
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			init(scope) {
				if(scope){
					this.type = scope.row;
					this.parentOption.value = scope.row.parentId;
					this.parentOption.label = scope.row.parentTypeName;
				}else {
					this.resetForm();	
				}
			},
			handleSizeChange(val) {
				this.pageData.size = val;
				this.reQuery();
			},
			handleCurrentChange(val) {
				this.pageData.current = val;
				this.reQuery();
			},
			reQuery() {
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/type/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								typeName: item.typeName,
								parentId: item.parentId,
								parentTypeName: item.parentTypeName,
								createTime: item.createTime
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			reQueryTree() {
				this.ts.doPost(this, '/admin/type/typeTree', this.form, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.data = vm.deep(vm, data.data.data);
					} else {

					}
				});
			},
			deep(vm, data) {
				var items = $.map(data, function(item, index) {
					if (item.children.length > 0) {
						return {
							id: item.id,
							label: item.typeName,
							children: vm.deep(vm, item.children)
						}
					} else {
						return {
							id: item.id,
							label: item.typeName
						}
					}
				});
				return items;
			},
			treeNodeClick(data,node){
				this.parentOption.value = data.id;
				this.parentOption.label = data.label;
				this.type.parentId = data.id;
			},
			parentClear(){
				this.parentOption = {
					value:'',
					label:''
				}
				this.type.parentId = '';
			},
			submitForm(form) {
				var formData = this.type;
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/type/save', formData, null, function(vm, data) {
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
			resetForm(form) {
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.$refs.tree.setCheckedKeys([]);
				this.type = {
					id: '',
					typeName: '',
					parentId: '',
					parentTypeName: true
				};
				this.parentClear();
			},
			deleteOne(id) {
				//根据ID删除权限，包含子权限
				var vm = this;
				vm.$confirm('此操作将删除该类别, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/type/deleteById', {
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
			}
		},
		mounted: function() {
			this.reQuery();
			this.reQueryTree();
		}
	}
</script>
<style scoped>
</style>