<template>
	<el-container>
		<el-main>
			{{type}}
		</el-main>
	</el-container>
</template>
<script>
	export default {
		name: 'articleManage',
		data() {
			return {
				type: '111dfadsfasdfa'
			}
		},
		methods: {
			changeArticle(menuId){
				console.log('进来了');
				console.log(menuId);
			}
		},
		mounted(){
			/*var fullPath = this.$route.fullPath;
			this.type = fullPath;*/
			console.log('=========');
			if(this.ts.changeArticle){
				
			}else {
				this.ts['changeArticle'] = this.changeArticle;
				this.ts['curVue'] = this;
				this.changeArticle(this.ts.menuId);
			}
		}
	}
</script>