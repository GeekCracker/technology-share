<template>
	<el-container>	
		<el-container>
			<el-aside style="width:auto;">
				<left></left>
			</el-aside>
			<el-main style="padding: 0;">
				<el-header style="padding: 0;">
					<headNav></headNav>
				</el-header>
				<el-main style="padding: 0;">
					<transition name="slide-fade" :duration="1000">
						<router-view></router-view>
					</transition>
				</el-main>	
			</el-main>
		</el-container>			
	</el-container>
</template>
<script>
	import headNav from './headNav'
	import left from './left'
	export default {
		name: 'home',
		components: {
			headNav,
			left
		}
	}
</script>