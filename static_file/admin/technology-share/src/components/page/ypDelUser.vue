<template>
  <el-container>
    <el-header>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>通用模块</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-header>
    <el-container>
      <div id = "hik"></div>
    </el-container>
  </el-container>
</template>
<script>
export default {
  name: "ypDelUser",
  data() {
    return {
      mobile: "",
      oWebControl: null
    };
  },
  methods: {
    
  },
  mounted: function () {

    console.log(jc);
    // this.oWebControl = this.hik.WebControlInit(
    //   'hik',
    //   this.hik.cbConnectSuccess,
    //   this.hik.cbConnectError,
    //   this.hik.cbConnectClose
    // );
  }
};
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