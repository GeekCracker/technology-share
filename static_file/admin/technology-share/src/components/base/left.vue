<template>
	<el-menu default-active="1-4-1" :background-color="backgroundColor" :router="router" :text-color="textColor" class="el-menu-vertical-demo" @select="handleSelect" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
		<el-submenu v-for="(permission,index) in permissionTree" :index="(index+1)+''">
			<template slot="title">
				<i class="el-icon-location"></i>
				<span slot="title">{{permission.permissionName}}</span>
			</template>
			<div v-if="permission.children && permission.children.length > 0" v-for="(c1,i1) in permission.children">
				<div v-if="c1.children && c1.children.length > 0">
					<el-submenu :index='c1.permissionLink'>
						<template>
							<span slot="title">{{c1.permissionName}}</span>
						</template>
						<div v-for="(c2,i2) in c1.children">
							<div v-if="c2.children && c2.children.length > 0">
								<el-submenu :index='c2.permissionLink'>
									<template>
										<span slot="title">{{c2.permissionName}}</span>
									</template>
									<div v-for="(c3,i3) in c2.children">
										<div v-if="c3.children && c3.children.length > 0">
											<el-submenu :index='c3.permissionLink'>
												<template>
													<span slot="title">{{c3.permissionName}}</span>
												</template>
												<div v-for="(c4,index) in c3.children">
												<div v-if="c4.children && c4.children.length > 0">
													<el-submenu :index='c4.permissionLink'>
														<template>
															<span slot="title">{{c4.permissionName}}</span>
														</template>
														<div v-for="(c5,index) in c4.children">
															<el-menu-item :index="c5.permissionLink" >{{c5.permissionName}}</el-menu-item>
														</div>
													</el-submenu>
												</div>
												<div v-else>
													<el-menu-item :index="c4.permissionLink">{{c4.permissionName}}</el-menu-item>	
												</div>
											</div>
											</el-submenu>
										</div>
										<div v-else>
											<el-menu-item :index="c3.permissionLink">{{c3.permissionName}}</el-menu-item>
										</div>
									</div>
								</el-submenu>
							</div>
							<div v-else>
								<el-menu-item :index="c2.permissionLink">{{c2.permissionName}}</el-menu-item>
							</div>
						</div>
					</el-submenu>
				</div>
				<div v-else>
					<el-menu-item :index="c1.permissionLink" >{{c1.permissionName}}</el-menu-item>
				</div>
			</div>
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
				isCollapse: false,
				router:true,
				permissionTree: []
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				//				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				//				console.log(key, keyPath);
			},
			handleSelect(key,keyPath){
//				console.log(key,keyPath);
			}
		},
		mounted: function() {
			var userJSON = localStorage.getItem("user");
			var user = JSON.parse(userJSON);
			this.permissionTree = user.role.permissionTree
//			console.log(user.role.permissionTree);
		}
	}
</script>
<style scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		min-height: 800px;
	}
</style>