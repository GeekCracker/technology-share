<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>通用模块</el-breadcrumb-item>
				<el-breadcrumb-item>柚浦智能-设备列表</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-header>
				<el-row>
					<span style="float: left;width: 25%">
						<el-input v-model="queryParam.uniqueNumber" placeholder="请输入设备唯一编号" ></el-input>
                    </span>
					<span style="float: left;width: 25%">
						<el-input v-model="queryParam.activeCode" placeholder="请输入设备激活码" ></el-input>
                    </span>
					<span style="float: left;width: 10%">
						<el-button type="primary" icon="iconfont el-iconchaxun" @click="reQuery()" round>查询</el-button>
                    </span>
				</el-row>
			</el-header>
			<el-main>
				<el-table v-loading="loading" :data="tableData" :highlight-current-row="true" border style="width: 100%;">
					<el-table-column label="序号" width="60" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="小区名称" width="160" align="center">
						<template slot-scope="scope">
							{{scope.row.communityName}}
						</template>
					</el-table-column>
					<el-table-column label="设备布控位置" width="140" align="center">
						<template slot-scope="scope">
							{{scope.row.memo}}
						</template>
					</el-table-column>
                    <el-table-column label="设备唯一编号" width="260" align="center">
						<template slot-scope="scope">
							{{scope.row.uniqueNumber}}
						</template>
					</el-table-column>
					<el-table-column label="设备激活码" width="280" align="center">
						<template slot-scope="scope">
							{{scope.row.activeCode}}
						</template>
					</el-table-column>
                    <el-table-column label="厂商" width="100" align="center">
						<template slot-scope="scope">
							{{scope.row.source|source}}
						</template>
					</el-table-column>
					<el-table-column label="同步状态" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.syncStatus|status}}
						</template>
					</el-table-column>
					<el-table-column label="添加时间" width="160" align="center">
						<template slot-scope="scope">
							{{scope.row.createdTime|dateFormat}}
						</template>
					</el-table-column>
					<el-table-column prop="option" label="操作" width="auto" align="center">
						<template slot-scope="scope">
							<el-button type="success" icon="el-icon-document-copy" @click="dialogVisible = true;dlgTitle='同步记录';queryErrorLog(scope.row.id);" title="同步记录" circle></el-button>
						</template>
					</el-table-column>
				</el-table>
                <div class="block" align="left">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageData.current" :page-sizes="[10, 20, 50, 100]" :page-size="pageData.size" layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
                    </el-pagination>
                </div>
			</el-main>
			<el-dialog :title="dlgTitle" :visible.sync="dialogVisible" width="90%">
                <el-table v-loading="loading" :data="logData" :highlight-current-row="true" border style="width: 100%;">
                    <el-table-column label="序号" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.index}}
						</template>
					</el-table-column>
					<el-table-column label="数据类型" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.type|logType}}
						</template>
					</el-table-column>
                    <el-table-column label="幢" width="50" align="center">
						<template slot-scope="scope">
							{{scope.row.buildingNo}}
						</template>
					</el-table-column>
                    <el-table-column label="单元" width="50" align="center">
						<template slot-scope="scope">
							{{scope.row.unitNo}}
						</template>
					</el-table-column>
                    <el-table-column label="起始楼层" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.floorStart}}
						</template>
					</el-table-column>
                    <el-table-column label="结束楼层" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.floorEnd}}
						</template>
					</el-table-column>
                    <el-table-column label="起始户室" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.roomStart}}
						</template>
					</el-table-column>
                    <el-table-column label="结束户室" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.roomEnd}}
						</template>
					</el-table-column>
                    <el-table-column label="同步状态" width="80" align="center">
						<template slot-scope="scope">
							{{scope.row.status|status}}
						</template>
					</el-table-column>
                    <el-table-column label="信息" width="950" align="center">
						<template slot-scope="scope">
							{{scope.row.message}}
						</template>
					</el-table-column>
                    <el-table-column label="同步时间" width="auto" align="center">
						<template slot-scope="scope">
							{{scope.row.syncTime|dateFormat}}
						</template>
					</el-table-column>
                </el-table>
				<span slot="footer" class="dialog-footer">
				    <el-button type="primary" @click="dialogVisible = false;">确 定</el-button>
				</span>
			</el-dialog>
		</el-container>
	</el-container>	
</template>
<script>
	export default{
		name:'ypEquipmentError',
		data(){
			return {
				tableData:[],
				pageData:{
                    current:1,
                    size:10
                },
                logData:[],
				roles:[],
				loading:false,
				formData:{},
				queryParam:{},
				options: [{
					value: 1,
					label: '启用'
				}, {
					value: 0,
					label: '禁用'
				}],
				dialogVisible:false,
				dlgTitle:'',
				rules:{}
			}
        },
        filters:{
            source(source){
                if(source){
                    switch(source){
                        case 1:
                            return '悉尔';
                        case 4:
                            return '云门禁';
                        case 5:
                            return '集团云门禁';
                        case 6:
                            return '顾特';    

                    }
                }
            },
            logType(type){
                if(type){
                    switch(type){
                        case 1:
                            return '小区信息';
                        case 2:
                            return '单元户室'
                        case 3:
                            return '设备';
                        case 4:
                            return '用户';
                        case 5:
                            return '门禁卡';
                    }
                }
            },
            status(status){
                if(status){
                    switch(status){
                        case 1:
							return '同步成功';
						case 0:
							return '同步中';
                        case -1:
                            return '同步失败';
                    }
                }
            }   
        },
		methods:{
			reQuery(){
				this.loading = false;
				var queryParam = this.queryParam;
				queryParam.page = this.pageData.current;
                queryParam.rows = this.pageData.size;
                var vm = this;
				vm.ts.doPost(vm, '/yp-admin/queryEquipmentPageData', queryParam, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
					if (data.data.code == 200) {
                        vm.pageData = data.data.data;
						vm.tableData = $.map(data.data.data.records, function(item, index) {
							return {
								index: (data.data.data.size * (data.data.data.current -1) + index + 1),
								id: item.id,
								communityName: item.communityName,
								uniqueNumber: item.uniqueNumber,
								activeCode: item.activeCode,
								memo: item.memo,
								source: item.source,
								syncStatus: item.syncStatus,
								createdTime: item.createdTime
                                
							}
						});
						vm.loading = false;
					} else {

					}
				});
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
            queryErrorLog(equipmentId){
                var vm = this;
				vm.ts.doGet(vm, '/yp-admin/querySyncEquipmentStatusData/'+equipmentId, null, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
					if (data.data.code == 200) {
						vm.logData = $.map(data.data.data, function(item, index) {
							return {
								index: index + 1,
								id: item.id,
								type: item.type,
								buildingNo: item.buildingNo,
								unitNo: item.unitNo,
								floorStart: item.floorStart,
                                floorEnd: item.floorEnd,
                                roomStart: item.roomStart,
                                roomEnd: item.roomEnd,
                                status: item.status,
                                message: item.message,
                                syncTime: item.syncTime
							}
						});
						vm.loading = false;
					} else {

					}
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