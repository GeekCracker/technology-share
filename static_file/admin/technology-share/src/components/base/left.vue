<template>
	<el-menu default-active="1-4-1" :background-color="backgroundColor" :text-color="textColor" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
		<el-submenu index="1">
			<template slot="title">
				<i class="el-icon-location"></i>
				<span slot="title">导航一</span>
			</template>
			<el-menu-item-group index="1-1">
				<el-menu-item index="1-1-1">选项1</el-menu-item>
				<el-menu-item index="1-1-2">选项2</el-menu-item>
			</el-menu-item-group>
			<el-menu-item-group index="1-2">
				<el-menu-item index="1-2-1">选项3</el-menu-item>
				<el-menu-item index="1-2-2">选项4</el-menu-item>
			</el-menu-item-group>
			<el-submenu index="1-3">
				<template>
					<span slot="title">导航一</span>
				</template>
				<el-menu-item index="1-3-1">选项1</el-menu-item>
				<el-submenu index="1-3-2">
					<template>
						<span slot="title">导航一sss</span>
					</template>
					<el-menu-item index="1-3-2-1">ssss</el-menu-item>
				</el-submenu>
			</el-submenu>
		</el-submenu>
	</el-menu>
</template>
<script>
	export default {
		name: 'left',
		data() {
			return {
				backgroundColor: '#545c64',
				textColor: '#ffffff',
				isCollapse: false
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			}
		},
		mounted: function() {

		}
	}
</script>
<style scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		min-height: 800px;
	}
</style>