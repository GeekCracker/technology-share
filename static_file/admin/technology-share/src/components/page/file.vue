<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>文件管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: right;">
						<el-button type="danger" @click="deleteBatch"  round>批量删除</el-button>
						<el-button type="primary" @click="dialogVisible = true;dlgTitle='上传文件';"  round>上传文件</el-button>
					</span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" :data="tableData" @selection-change="rowSelect" @select-all="selectAll" :highlight-current-row="true" border >
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="文件名" width="200" align="center">
						<template slot-scope="scope">
							{{scope.row.originalName}}
						</template>
					</el-table-column>
					<el-table-column label="图片" width="125" align="center">
						<template slot-scope="scope">
							<img :src="scope.row.smallUri" v-if="/(png|jpg|jpeg|gif)$/.test(scope.row.ext)" @click="preView(scope)" style="width:100px;cursor: pointer;" />
							<img src="../../../static/img/logo.png" v-if="/(mp4)$/.test(scope.row.ext)" @click="preView(scope)" style="width:100px;cursor: pointer;" />
						</template>
					</el-table-column>
					<el-table-column label="占用次数" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.useCount}}
						</template>
					</el-table-column>
					<el-table-column label="文件大小" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.size|sizeConvert('KB')}}
						</template>
					</el-table-column>
					<el-table-column label="UUID" width="310" align="center">
						<template slot-scope="scope">
							{{scope.row.uuid}}
						</template>
					</el-table-column>
					<el-table-column label="小图URI" width="280" align="center">
						<template slot-scope="scope">
							{{scope.row.smallUri}}
						</template>
					</el-table-column>
					<el-table-column label="大图URI" width="280" align="center">
						<template slot-scope="scope">
							{{scope.row.bigUri}}
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
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" @before-close="beforeClose" width="50%">				
				<el-container>
					<v-uploader @done="uploadDone" :multiple="true" file-size-limit="1024MB" :item-limit="24" file-type-exts="jpeg,jpg,gif,png,mp4" upload-file-obj-name="file" style="width: 100%;" ></v-uploader>
				</el-container>
			</el-dialog>
			<el-dialog :title="preViewTitle" :visible.sync="preViewImageVisible" :fullscreen="true" width="100%" height="100%">
				<img :src="curBigImage" width="100%"/>
			</el-dialog>
			<el-dialog :title="preViewTitle" :visible.sync="preViewVideoVisible" :fullscreen="true" :before-close="videoDlgBeforeClose" width="100%" height="100%">
				<v-playback :url="curVideo" :loop="true"></v-playback>
			</el-dialog>
		</el-container>
	</el-container>
</template>
<script>
	export default{
		name:'file',
		data(){
			return {
				tableData:[],
				pageData:{},
				queryParam:{},
				selections: [],
				loading:false,
				preViewImageVisible:false,
				preViewVideoVisible:false,
				dialogVisible:false,
				preViewTitle:'',
				dlgTitle:'',
				curBigImage:'',
				curVideo:''
			}
		},
		methods:{
			handleCurrentChange(val){
				//修改当前页码
				this.pageData.current = val;
				//重新查询
				this.reQuery();
			},
			handleSizeChange(val){
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
				vm.ts.doPost(vm, '/admin/fileInfo/queryPageData', queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (data.data.data.current - 1) * data.data.data.size + index +1,
								id: item.id,
								originalName: item.originalName,
								smallUri: item.smallUri,
								bigUri: item.bigUri,
								useCount: item.useCount,
								size: item.size,
								uuid: item.uuid,
								ext: item.ext
							}
						});
						vm.loading = false;
					} else {

					}
				});
			},
			deleteOne(id){
				//根据ID删除文件
				var vm = this;
				vm.$confirm('此操作将删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/fileInfo/deleteById', {
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
			deleteBatch: function() {
				var selections = this.selections;
				if (!selections || selections.length == 0) {
					this.$message('请选择需要删除的文件');
					return;
				}
				//根据ID删除文件
				var vm = this;
				vm.$confirm('此操作将删除所有选中的文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					beforeClose: function(action, instance, done) {
						if (action === 'confirm') {
							instance.confirmButtonLoading = true;
							instance.confirmButtonText = '执行中...';
							vm.done = done;
							vm.instance = instance;
							vm.ts.doPost(vm, '/admin/fileInfo/deleteBatchById', {
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
			preView(scope){
				if(/(png|jpg|jpeg|gif)$/.test(scope.row.ext)){
					this.preViewTitle="图片预览";
					this.curBigImage = scope.row.bigUri;
					this.preViewImageVisible = true;
				}
				if(/(mp4)/.test(scope.row.ext)){
					this.preViewTitle = "视频预览";
					this.curVideo = scope.row.bigUri;
					this.preViewVideoVisible = true;
					var vdo = document.getElementById("videoPlayerContainer_html5_api");
					if(vdo){
						vdo.src = scope.row.bigUri;
						vdo.play();
					}					
				}
			},
			uploadDone(files){
				if(files && Array.isArray(files) && files.length){
					//console.log(files);
		        }
			},
			beforeClose(done){
				
				done();
			},
			videoDlgBeforeClose(done){
				var vdo = document.getElementById("videoPlayerContainer_html5_api");
				vdo.pause();
				done();
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