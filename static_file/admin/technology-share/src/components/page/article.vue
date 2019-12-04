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
					<el-input style="width: 60%" placeholder="请输入文章标题" ></el-input>
					<el-button type="primary" icon="iconfont el-iconchaxun" round>查询</el-button>
				</span>
					<span style="float: right;">
					<el-button type="primary"  round>添加</el-button>
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
					<el-table-column label="文章类型" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.typeName}}
						</template>
					</el-table-column>
					<el-table-column label="文章内容摘要" width="300" align="center">
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
					<el-table-column label="发布时间" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.publishTime|dateFormat}}
						</template>
					</el-table-column>
					<el-table-column label="创建时间" width="150" align="center">
						<template slot-scope="scope">
							{{scope.row.createTime|dateFormat}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="primary" icon="el-icon-info" title="详情" circle></el-button>
							<el-button type="warning" icon="el-icon-edit" title="修改" circle></el-button>
							<el-button type="danger"  icon="el-icon-delete" title="删除" circle></el-button>
						</template>
					</el-table-column>
					
				</el-table>
			</el-main>

		</el-container>

		<!--<ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>-->
	</el-container>
</template>
<script>
	import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
	export default {
		name: 'articleManage',
		data() {
			return {
				editor: ClassicEditor,
				editorData: '',
				editorConfig: {
					language: 'zh-cn'
				},
				loading:false,
				tableData:[],
				pageData:{},
				queryParam:{
					pageNum:'',
					pageSize:''
				},
				article:{}
				
			}
		},
		filter:{
			contentFilter(content){
				return content ? content.substring(0,10) : ''
			},
			dateFormat(time){
				//时间格式化
				return time;
			}
		},
		methods:{
			enableDisable:function(scope){
				
			},
			reQuery(){
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
			}
		},
		mounted: function() {
			this.reQuery();
		}
	}
</script>
<style scoped>

</style>