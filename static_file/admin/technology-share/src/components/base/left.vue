<template>
	<el-container>
		<el-aside style="width: auto;">
			<el-menu default-active="1-4-1" :background-color="backgroundColor" :router="router" :text-color="textColor" class="el-menu-vertical-demo" @select="handleSelect" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
				<el-submenu v-for="(permission,index) in permissionTree" :index="(index+1)+''">
					<template slot="title">
						<i :class="permission.permissionIcon" style="font-size: 23px;margin: 0;"></i>
						<span slot="title">{{permission.permissionName}}</span>
					</template>
					<div v-if="permission.children && permission.children.length > 0" v-for="(c1,i1) in permission.children">
						<el-menu-item :index="c1.permissionLink">
							<template slot="title">
								<i :class="c1.permissionIcon" style="font-size: 23px;margin: 0;"></i>
								<span slot="title">{{c1.permissionName}}</span>
							</template>
						</el-menu-item>
					</div>
				</el-submenu>
			</el-menu>
		</el-aside>
		<el-main style="padding: 0;">
			<el-button style="margin:0;padding: 0;line-height: 900px;" @click="changeIcon" :icon="leftIcon" type="info"></el-button>
		</el-main>
	</el-container>
</template>
<script>
	export default {
		name: 'left',
		data() {
			return {
				backgroundColor: '#545c64',
				textColor: '#ffffff',
				isCollapse: false,
				router: true,
				permissionTree: [],
				leftIcon: 'el-icon-d-arrow-left'
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				//				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				//				console.log(key, keyPath);
			},
			handleSelect(key, keyPath) {
				//				console.log(key,keyPath);
			},
			changeIcon() {
				this.isCollapse = !this.isCollapse;
				if (this.isCollapse) {
					this.leftIcon = 'el-icon-d-arrow-right';
				} else {
					this.leftIcon = 'el-icon-d-arrow-left';
				}
			}
		},
		mounted: function() {
			var userJSON = localStorage.getItem("user");
			var user = JSON.parse(userJSON);
			this.permissionTree = user.role.permissionTree
			var permissionTree = user.role.permissionTree;
			//			console.log(user.role.permissionTree);
		}
	}
</script>
<style scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		min-height: 900px;
	}
</style>