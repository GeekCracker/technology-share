<template>
  <el-container>
    <el-header>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>通用模块</el-breadcrumb-item>
        <el-breadcrumb-item>柚浦智能-每日数据统计</el-breadcrumb-item>
      </el-breadcrumb>
    </el-header>
    <el-container>
      <el-aside width="55%">
        <el-container class="outter">
          <el-container class="inner">
            <el-header> 昨日用户获取金币总数（结果1）<br /> 日期：{{yesterday|dateFormat('YYYY-MM-DD')}}</el-header>
            <el-main> 
                <span class="inner-span">{{goldGetSumYesterday}}</span>
            </el-main>
          </el-container>
          <el-container class="inner">
            <el-header> 昨日获取金币总人数（结果2）<br /> 日期：{{yesterday|dateFormat('YYYY-MM-DD')}}</el-header>
            <el-main>
                <span class="inner-span">{{getUserCountYesterday}}</span>
            </el-main>
          </el-container>
          <el-container class="inner">
            <el-header> 昨日购买商品交易总额（结果3）<br /> 日期：{{yesterday|dateFormat('YYYY-MM-DD')}}</el-header>
            <el-main> 
                <span class="inner-span">{{productTradeSumYesterday}}</span>
            </el-main>
          </el-container>
        </el-container>
        <el-container class="outter">
          <el-container class="inner">
            <el-header> 指定日期用户获取金币总数（结果1）<br />
                <el-date-picker @change="dataStatisticsGoldGetSum" v-model="goldGetSumTime" value-format="yyyy-MM-dd" align="right" type="date" placeholder="选择日期"></el-date-picker>
            </el-header>
            <el-main>
                <span class="inner-span">{{goldGetSum}}</span>
            </el-main>
          </el-container>
          <el-container class="inner">
            <el-header> 指定日期获取金币总人数（结果2）<br />
                <el-date-picker @change="dataStatisticsGoldGetUserCount" v-model="getUserCountTime" value-format="yyyy-MM-dd" align="right" type="date" placeholder="选择日期"></el-date-picker>
            </el-header>
            <el-main> 
                <span class="inner-span">{{getUserCount}}</span>
            </el-main>
          </el-container>
          <el-container class="inner">
            <el-header> 指定日期购买商品交易总额（结果3）<br />
                <el-date-picker @change="dataStatisticsProductTradeSum" v-model="productTradeSumTime" value-format="yyyy-MM-dd" align="right" type="date" placeholder="选择日期"></el-date-picker>
            </el-header>
            <el-main> 
                <span class="inner-span">{{productTradeSum}}</span>
            </el-main>
          </el-container>
        </el-container>
        <el-container class="outter">
          <el-container class="inner">
            <el-header> 指定日期范围每日用户获取金币总数（结果1） </el-header>
            <el-main> </el-main>
          </el-container>
          <el-container class="inner">
            <el-header>指定日期范围每日获取金币总人数（结果2）</el-header>
            <el-main> </el-main>
          </el-container>
          <el-container class="inner">
            <el-header>指定日期范围每日购买商品交易总额（结果3） </el-header>
            <el-main> </el-main>
          </el-container>
        </el-container>
      </el-aside>
      <el-main>
        <el-header style="margin: 5px; background-color: #e5f6ff">
          报名信息
        </el-header>
        <el-main
          style="margin: 5px; background-color: #e5f6ff; min-height: 800px"
        >
            <el-table v-loading="loading" :data="applyInfos" :highlight-current-row="true" border style="width: 100%;" max-height="810">
                <el-table-column label="ID" width="70" prop="id" align="center" :sortable="true">
                    <template slot-scope="scope">
                        {{scope.row.id}}
                    </template>
                </el-table-column>
                <el-table-column label="活动名称" width="200" align="center">
                    <template slot-scope="scope">
                        {{scope.row.activityName}}
                    </template>
                </el-table-column>
                <el-table-column label="报名信息" width="600" align="center">
                    <template slot-scope="scope">
                        {{scope.row.applyInfo}}
                    </template>
                </el-table-column>
                <el-table-column label="报名时间" width="160" align="center">
                    <template slot-scope="scope">
                        {{scope.row.createdTime|dateFormat}}
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  name: "ypDataStatistics",
  data() {
    return {
        yesterday:new Date().setDate(new Date().getDate() - 1),
        goldGetSumYesterday:0,
        getUserCountYesterday:0,
        productTradeSumYesterday:0,
        goldGetSumTime:'',
        getUserCountTime:'',
        productTradeSumTime:'',
        goldGetSum:0,
        getUserCount:0,
        productTradeSum:0,
        applyInfos:[],
        loading: true
    };
  },
  methods: {
    dataStatisticsGoldGetSumYesterday(){
        var vm = this;
        vm.ts.doGet(vm, '/yp-admin/dataStatisticsGoldGetSumYesterday', null, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.goldGetSumYesterday = data.data.data;
            }
        });
    },
    dataStatisticsGoldGetUserCountYesterday(){
        var vm = this;
        vm.ts.doGet(vm, '/yp-admin/dataStatisticsGoldGetUserCountYesterday', null, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.getUserCountYesterday = data.data.data;
            }
        });
    },
    dataStatisticsProductTradeSumYesterday(){
        var vm = this;
        vm.ts.doGet(vm, '/yp-admin/dataStatisticsProductTradeSumYesterday', null, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.productTradeSumYesterday = data.data.data;
            }
        });
    },
    dataStatisticsGoldGetSum(time){
        var vm = this;
        var queryParam = {};
        queryParam.date = time;
        vm.ts.doPost(vm, '/yp-admin/dataStatisticsGoldGetSum', queryParam, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.goldGetSum = data.data.data;
            }
        });
    },
    dataStatisticsGoldGetUserCount(time){
        var vm = this;
        var queryParam = {};
        queryParam.date = time;
        vm.ts.doPost(vm, '/yp-admin/dataStatisticsGoldGetUserCount', queryParam, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.getUserCount = data.data.data;
            }
        });
    },
    dataStatisticsProductTradeSum(time){
        var vm = this;
        var queryParam = {};
        queryParam = time;
        vm.ts.doPost(vm, '/yp-admin/dataStatisticsProductTradeSum', queryParam, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.productTradeSum = data.data.data;
            }
        });
    },
    dataStatisticsApplyInfo(){
        var vm = this;
        vm.ts.doGet(vm, '/yp-admin/applyInfo', null, {"headers":{"Content-Type":"application/json"}}, function(vm, data) {
            if (data.data.code == 200) {
                vm.applyInfos = data.data.data;
                vm.loading = false;
            }
        });
    }
  },
  mounted: function () {
      this.dataStatisticsGoldGetSumYesterday();
      this.dataStatisticsGoldGetUserCountYesterday();
      this.dataStatisticsProductTradeSumYesterday();
      this.dataStatisticsApplyInfo();
  }
};
</script>
<style scoped>
.el-aside {
  color: #333;
  text-align: center;
}

.el-main {
  margin: 0px;
  padding: 5px;
}

.el-table {
  margin: 0px;
}

.outter {
  height: 300px;
}

.inner {
  background-color: #e5f6ff;
  max-width: 33%;
  margin: 0.5%;
}

.inner-span{
  display: inline-block;
  color: red;
  line-height: 200px;
  width: 100%;
  font-size: 80px;

}
</style>