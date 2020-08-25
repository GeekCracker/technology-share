<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>用户管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;">
					<el-input style="width: 60%" v-model="queryParam.userName" placeholder="请输入用户名称" ></el-input>
					<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery()" round>查询</el-button>
				</span>
					<span style="float: right;">
					<el-button type="primary" @click="dialogVisible=true;dlgTitle='添加用户';resetForm('userForm');" round>添加</el-button>
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
					<el-table-column label="用户名称" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.userName}}
						</template>
					</el-table-column>
					<el-table-column label="联系方式" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.userPhone}}
						</template>
					</el-table-column>
					<el-table-column label="角色" width="120" align="center">
						<template slot-scope="scope">
							{{scope.row.roleName}}
						</template>
					</el-table-column>
					<el-table-column label="用户状态" width="120" align="center">
						<template slot-scope="scope">
							<span v-if="scope.row.userStatus">启用</span>
							<span v-if="!scope.row.userStatus">禁用</span>
						</template>
					</el-table-column>
					<el-table-column label="最后登录时间" width="120" align="center">
						<template slot-scope="scope">
							{{scope.row.lastLoginTime}}
						</template>
					</el-table-column>
					<el-table-column label="创建人" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.createBy}}
						</template>
					</el-table-column>
					<el-table-column label="创建时间" width="120" align="center">
						<template slot-scope="scope">
							{{scope.row.createTime}}
						</template>
					</el-table-column>

					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="primary" icon="el-icon-info" title="详情" circle></el-button>
							<el-button type="warning" icon="el-icon-edit" @click="dialogVisible = true;dlgTitle='修改用户';init(scope);" title="修改" circle></el-button>
							<el-button type="danger" @click="deleteOne(scope.row.id)" icon="el-icon-delete" title="删除" circle></el-button>
							<i title="禁用" @click="enableDisable(scope)" v-if="scope.row.userStatus" style="font-size: 50px;cursor: pointer; color:#409eff;line-height: 50px;vertical-align:middle;margin-left: 10px;" class="el-icon-open"></i>
							<!-- 启用状态，触发禁用方法 -->
							<i title="启用" @click="enableDisable(scope)" v-if="!scope.row.userStatus" style="font-size: 50px;cursor: pointer; line-height: 50px;vertical-align:middle;margin-left: 10px;" class="el-icon-turn-off"></i>
							<!-- 禁用状态，触发启用方法 -->
						</template>
					</el-table-column>
				</el-table>
				<div class="block" align="left">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
					</el-pagination>
				</div>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" :before-close="handleClose" width="50%">
				<el-form :model="user" :inline="true" status-icon :rules="rules" ref="userForm" label-width="100px">
					<el-form-item label="用户名称" prop="userName">
						<el-input type="text" v-model="user.userName"></el-input>
					</el-form-item>
					<el-form-item label="用户头像" prop="userHead">
						<el-upload class="avatar-uploader" list-type="picture-card" :show-file-list="false" ref="upload" action="/upload/uploadFile" :limit="1" :on-preview="handlePictureCardPreview" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :on-remove="handleRemove">
							<img v-if="user.userHead && user.userHead != ''" :src="user.userHead" class="avatar-uploader-icon" />
							<i v-else class="el-icon-plus avatar-uploader-icon"></i>
						</el-upload>
						<el-dialog :visible.sync="userHeadDialogVisible">
							<img width="100%" :src="user.userHead" alt="用户头像">
						</el-dialog>
					</el-form-item>
					<el-form-item label="初始密码" prop="userPassword">
						<el-input type="text" v-model="user.userPassword" placeholder="无法查看，请自行保存"></el-input>
					</el-form-item>
					<el-form-item label="联系方式" prop="userPhone ">
						<el-input type="text " v-model="user.userPhone "></el-input>
					</el-form-item>
					<el-form-item label="用户角色" prop="roleId ">
						<el-select v-model="user.roleId" placeholder="请选择">
							<el-option v-for="item in roles" :key="item.id" :label="item.roleName" :value="item.id">
							</el-option>
						</el-select>
						<!--<el-input type="text " v-model="user.roleId "></el-input>-->
					</el-form-item>
					<el-form-item label="排序序号 " prop="userSort ">
						<el-input type="text " v-model="user.userSort "></el-input>
					</el-form-item>
				</el-form>
				<span slot="footer" class="dialog-footer">
				    <el-button @click="dialogVisible=false; ">取消</el-button>
				    <el-button type="danger " @click="resetForm( 'userForm') ">重置</el-button>
				    <el-button type="primary " @click="submitForm( 'userForm') ">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'user',
		data() {
			return {
				tableData: [],
				pageData: {
					current: 1,
					size: 10
				},
				queryParam: {
					userName: '',
					pageNum: '',
					pageSize: ''
				},
				user: {
					userName: '',
					userHead: null,
					userPhone: ''
				},
				roles: [],
				dlgTitle: '',
				dialogVisible: false,
				userHeadDialogVisible: false,
				loading: true,
				rules: {
					userName: [{
						required: true,
						message: '请输入用户名称',
						trigger: 'blur'
					}]
				},
				disabled: false
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
				vm.ts.doPost(vm, '/admin/user/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (index + 1),
								id: item.id,
								userName: item.userName,
								userHead: item.userHead,
								userPhone: item.userPhone,
								userStatus: item.userStatus,
								lastLoginTime: item.lastLoginTime,
								roleName: item.roleName
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			queryRole() {
				this.ts.doPost(this, '/admin/role/queryData', null, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.roles = data.data.data;
					}
				});
			},
			enableDisable: function(scope) {
				scope.row.userStatus = !scope.row.userStatus;
				this.ts.doPost(this, '/admin/user/enableDisable', {
					id: scope.row.id
				}, null, function(vm, data) {
					var type = '';
					if (data.data.code == 200) {
						type = 'success';
					} else {
						type = 'error';
					}
					vm.$message({
						type: type,
						message: data.data.msg
					});
				});
			},
			deleteOne: function(id) {
				//根据ID删除用户
				var vm = this;
				vm.$confirm('此操作将删除该用户, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/user/deleteById', {
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
			handleAvatarSuccess(res, file) {
				//this.user.userHead = URL.createObjectURL(file.raw);
				this.user.userHead = res.data.bigUri;
			},
			beforeAvatarUpload(file) {
				const isJPG = file.type === 'image/jpeg';
				const isLt2M = file.size / 1024 / 1024 < 2;

				if (!isJPG) {
					this.$message.error('上传头像图片只能是 JPG 格式!');
				}
				if (!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			},
			handlePictureCardPreview(file) {
				this.userHeadDialogVisible = true;
			},
			handleRemove(file, fileList) {},
			init(data) {
				this.user = data.row;
			},
			handleClose(done) {
				this.$refs['upload'].clearFiles();
				done();
			},
			submitForm: function(form) {
				var formData = this.user;
				var vm = this;
				this.$refs[form].validate((valid) => {
					if (valid) {
						vm.ts.doPost(vm, '/admin/user/save', formData, null, function(vm, data) {
							var type = '';
							if (data.data.code == 200) {
								vm.reQuery();
								vm.dialogVisible = false;
								vm.$refs['upload'].clearFiles();
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
				this.user = {};
			}
		},
		mounted: function() {
			this.reQuery();
			this.queryRole();
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
	
	.el-form-item {
		width: 45%;
	}
	
	.avatar-uploader-icon {
		width: 150px;
		height: 150px;
	}
</style>