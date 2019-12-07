<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>通用模块</el-breadcrumb-item>
				<el-breadcrumb-item>文章管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;">
					<el-input style="width: 60%" v-model="queryParam.title" @keyup.enter.native="reQuery();" placeholder="请输入文章标题" ></el-input>
					<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery();" round>查询</el-button>
				</span>
					<span style="float: right;">
					<el-button type="danger" round>批量删除</el-button>
					<el-button type="primary" @click="dialogVisible=true;dlgTitle='添加文章'" round>添加</el-button>
				</span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" :data="tableData" :highlight-current-row="true" border style="width: 100%;">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="文章标题" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.title}}
						</template>
					</el-table-column>
					<el-table-column label="文章类型" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.typeName}}
						</template>
					</el-table-column>
					<el-table-column label="文章内容摘要" width="250" align="center">
						<template slot-scope="scope">
							{{scope.row.content|contentFilter}}
						</template>
					</el-table-column>
					<el-table-column label="作者" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.author}}
						</template>
					</el-table-column>
					<el-table-column label="文章状态" width="80" align="center">
						<!-- 0:草稿 1:发布 -->
						<template slot-scope="scope">
							{{scope.row.status}}
						</template>
					</el-table-column>
					<el-table-column label="是否启用" width="80" align="center">
						<!-- 0:禁用 1:启用 -->
						<template slot-scope="scope">
							{{scope.row.enable}}
						</template>
					</el-table-column>
					<el-table-column label="是否前十条" width="100" align="center">
						<!-- 0:不是 1:是 -->
						<template slot-scope="scope">
							{{scope.row.topTen}}
						</template>
					</el-table-column>
					<el-table-column label="发布时间" width="160" align="center">
						<template slot-scope="scope">
							{{scope.row.publishTime}}
						</template>
					</el-table-column>
					<el-table-column label="创建时间" width="160" align="center">
						<template slot-scope="scope">
							{{scope.row.createTime}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="primary" icon="el-icon-info" title="详情" circle></el-button>
							<el-button type="warning" @click="dialogVisible=true;dlgTitle='修改文章';init(scope)" icon="el-icon-edit" title="修改" circle></el-button>
							<el-button type="danger" @click="deleteOne(scope.row.id);" icon="el-icon-delete" title="删除" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="block" align="left">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
					</el-pagination>
				</div>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" :before-close="handleClose" width="50%">
				<el-form :model="article" :inline="true" status-icon :rules="rules" ref="articleForm" label-width="100px">
					<el-form-item label="文章标题" prop="title">
						<el-input type="text" v-model="article.title"></el-input>
					</el-form-item>
					<el-form-item label="作者" prop="author">
						<el-input type="text" v-model="article.author"></el-input>
					</el-form-item>
					<el-form-item label="文章分类" prop="typeId">
						<!--<el-input type="text" v-model="article.typeId"></el-input>-->
						<el-select style="width:202px;" v-model="article.typeId" clearable @clear="typeClear" placeholder="请选择">
							<el-option style="height: auto;" :label="typeOption.label" :value="typeOption.value">
								<el-tree :check-strictly="true" :data="data" ref="tree" :accordion="accordion" @node-click="treeNodeClick" node-key="id" :props="defaultProps">
								</el-tree>
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="文章状态" prop="status">
						<!-- 0:草稿1:发布 -->
						<el-input type="text" v-model="article.status"></el-input>
					</el-form-item>
					<el-form-item label="是否启用" prop="enable">
						<!-- 0:禁用1:启用 -->
						<el-input type="text" v-model="article.enable"></el-input>
					</el-form-item>
					<el-form-item label="是否前十条" prop="topTen">
						<!-- 0:禁用1:启用 -->
						<el-input type="text" v-model="article.topTen"></el-input>
					</el-form-item>
					<el-form-item label="文章内容" prop="content">
						<ckeditor :editor="editor" v-model="article.content" :config="editorConfig"></ckeditor>
					</el-form-item>
				</el-form>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible=false; ">取消</el-button>
				    <el-button type="danger " @click="resetForm( 'articleForm') ">重置</el-button>
				    <el-button type="primary " @click="submitForm( 'articleForm') ">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
		<!--<ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>-->
	</el-container>
</template>
<script>
	import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
	export default {
		name: 'articleManage',
		data() {
			return {
				editor: ClassicEditor,
				editorConfig: {
					language: 'zh-cn',
					ckfinder: {
						uploadUrl: '/upload/uploadCKEditorFile'
					}
				},
				loading: false,
				data:[],
				tableData: [],
				pageData: {},
				defaultProps: '',
				accordion:true,
				queryParam: {
					pageNum: '',
					pageSize: ''
				},
				article: {},
				articleForm: {},
				typeOption: {
					value:'',
					label:''
				},
				rules: {
					title: [{
						required: true,
						message: '请输入文章标题',
						trigger: 'blur'
					}]
				},
				dialogVisible: false,
				dlgTitle: ''

			}
		},
		filters: {
			contentFilter(content) {
				return content ? content.substring(0, 10) : ''
			}
		},
		methods: {
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
				vm.ts.doPost(vm, '/admin/article/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								title: item.title,
								typeName: item.typeName,
								content: item.content,
								author: item.author,
								status: item.status,
								enable: item.enable,
								topTen: item.topTen,
								publishTime: item.publishTime,
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
			enableDisable: function(scope) {

			},
			init: function(scope) {
				if(scope){
					this.article = scope.row;
					this.typeOption.value = scope.row.typeId;
					this.typeOption.label = scope.row.typeName;
					if(!this.article.content){
						this.article.content = "";
					}
				}else {
					this.resetForm();	
				}
			},
			handleClose: function(done) {
				done();
			},
			resetForm: function(form) {
				var fm = this.$refs[form];
				if (fm) {
					fm.resetFields();
				}
				this.$refs.tree.setCheckedKeys([]);
				this.article = {
				};
				this.typeClear();
			},
			treeNodeClick(data,node){
				this.typeOption.value = data.id;
				this.typeOption.label = data.label;
				this.article.typeId = data.id;
			},
			typeClear:function(){
				this.typeOption = {
					value:'',
					label:''
				}
				this.article.typeId = '';
			},
			submitForm: function(form) {
				var formData = this.article;
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/article/save', formData, null, function(vm, data) {
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
			deleteOne(id){
				//根据ID删除权限，包含子权限
				var vm = this;
				vm.$confirm('此操作将删除该文章, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/article/deleteById', {
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
		mounted: function() {
			this.reQuery();
			this.reQueryTree();
		}
	}
</script>
<style scoped>

</style>