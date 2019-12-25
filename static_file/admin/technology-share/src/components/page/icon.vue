<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>图标管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;">
						<el-input style="width: 60%" v-model="queryParam.iconClass" @keyup.enter.native="reQuery();" placeholder="请输入图标class" ></el-input>
						<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery();" round>查询</el-button>
					</span>
					<span style="float: right;">
						<el-button type="danger" @click="deleteBatch" round>批量删除</el-button>
						<el-button type="primary" @click="dialogVisible=true;dlgTitle='添加图标';init()" round>添加</el-button>
					</span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" @selection-change="rowSelect" @select-all="selectAll" :data="tableData" :highlight-current-row="true" border style="width: 100%;">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="iconClass" width="300" align="center">
						<template slot-scope="scope">
							{{scope.row.iconClass}}
						</template>
					</el-table-column>
					<el-table-column label="图标" width="100" align="center">
						<template slot-scope="scope" >
							<i :class="scope.row.iconClass" style="font-size: 40px;line-height: 40px;" ></i>
						</template>
					</el-table-column>
					<el-table-column label="创建时间" width="160" align="center">
						<template slot-scope="scope">
							{{scope.row.createTime}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="danger" @click="deleteOne(scope.row.id);" icon="el-icon-delete" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="block" align="left">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
					</el-pagination>
				</div>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="35%">
				<el-form :model="icon" :inline="true" status-icon :rules="rules" ref="iconForm" label-width="100px">
					<el-form-item label="图标class" prop="iconClass">
						<el-input type="text" :autofocus="true" v-model="icon.iconClass"></el-input>
					</el-form-item>
				</el-form>		
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible = false;">取 消</el-button>
				    <el-button type="danger" @click="resetForm('iconForm')">重置</el-button>
				    <el-button type="primary" @click="submitForm('iconForm')">确 定</el-button>
				</span>
			</el-dialog>		
		</el-container>
	</el-container>
</template>
<script>
	export default{
		name:'icon',
		data(){
			return {
				tableData:[],
				pageData:{
					pageNum:'',
					pageSize:''
				},
				queryParam:{},
				selections: [],
				loading:false,
				icon:{},
				dlgTitle:'',
				dialogVisible:false,
				autofocus:true,
				rules:{
					iconClass:[{
						required: true,
						message: '请输入图标class',
						trigger: 'blur'	
					}]
				}
			}
		},
		methods:{
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
			reQuery(){
				this.loading=false;
				var queryParam = this.queryParam;
				queryParam.pageNum = this.pageData.current;
				queryParam.pageSize = this.pageData.size;
				var vm = this;
				vm.ts.doPost(vm, '/admin/icon/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (data.data.data.current - 1) * data.data.data.size + index +1,
								id: item.id,
								iconClass: item.iconClass,
								createTime: item.createTime
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			init(scope){
				if(scope){
					this.icon = scope.row;
				}else {
					this.icon = {};
				}
			},
			submitForm(form){
				var formData = this.icon;
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/icon/save', formData, null, function(vm, data) {
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
				this.icon = {};
			},
			deleteOne(id){
				//根据ID删除权限，包含子权限
				var vm = this;
				vm.$confirm('此操作将删除该图标, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/icon/deleteById', {
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
			deleteBatch(){
				var selections = this.selections;
				if (!selections || selections.length == 0) {
					this.$message('请选择需要删除的图标');
					return;
				}
				//根据ID删除
				var vm = this;
				vm.$confirm('此操作将删除所有选中的图标, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/icon/deleteBatchById', {
								deleteList: JSON.stringify(selections)
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
		mounted:function(){
			this.reQuery();
		}
	}
</script>
<style scoped>
	
</style>