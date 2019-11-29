<template>
	<el-container>
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>基础配置</el-breadcrumb-item>
				<el-breadcrumb-item>权限管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<el-container>
			<el-aside>
				
			</el-aside>
			<el-main>
				
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'permission'
	}
</script>
<style scoped>
	.el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}
	
	.el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
	}
</style>