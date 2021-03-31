<template>
  <el-container>
    <el-header>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>通用模块</el-breadcrumb-item>
        <el-breadcrumb-item>柚浦智能-后台服务-版本控制</el-breadcrumb-item>
      </el-breadcrumb>
    </el-header>
    <el-main>
      <el-header>
        <el-row class="header-row">
          <span>
            <el-select placeholder="服务名称"></el-select>      
          </span>
          <span>
            <el-input placeholder="版本号"></el-input>      
          </span>
          <span>
            <el-date-picker v-model="time" value-format="yyyy-MM-dd HH:mm:ss" type="datetimerange" range-separator="至" start-placeholder="发布开始时间" end-placeholder="发布结束时间"></el-date-picker>
          </span>
          <span>
            <el-button @click="query" type="primary" icon="iconfont el-iconchaxun" round>查询</el-button>
          </span>
        </el-row>
      </el-header>
      <el-main> </el-main>
    </el-main>
  </el-container>
</template>
<script>
export default {
  name: "ypVersionControl",
  data() {
    return {
        time:[]
    };
  },
  methods: {
      query(){
          console.log(this.time);
      }
  },
  mounted: function () {
  },
};
</script>
<style scoped>
    .header-row span {
        float: left;
        margin-right:5px
    }
</style>