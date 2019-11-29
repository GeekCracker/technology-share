<template>
	<el-container>			
		<el-header>
			<headNav></headNav>
		</el-header>			
		<el-container>
			<el-aside>
				<left></left>
			</el-aside>
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>			
		<el-footer height="77px">
			<foot></foot>
		</el-footer>
	</el-container>
</template>
<script>
	import headNav from './headNav.vue'
	import left from './left.vue'
	import foot from './foot.vue'
	export default {
		name: 'home',
		components: {
			headNav,
			left,
			foot
		},
		data() {
			return {
				activeName:'second'
			}
		},
		methods:{
			handleClick:function(tab,event){
				
			}
		},
		mounted: function() {
			this.ts.init();
		}
	}
</script>
<style scoped>
	.el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
	}
	.el-header,.el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}
</style>