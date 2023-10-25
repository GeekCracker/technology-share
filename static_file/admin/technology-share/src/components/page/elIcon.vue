<template>
	<el-container>
		<el-header>
			<el-row>
				<span>
					<el-input style="width: 60%" v-model="queryParam.iconClass" v-on:input="reQuery()" placeholder="请输入图标class" ></el-input>
				</span>
			</el-row>
		</el-header>
		<ul class="icon-list" @click="click($event)">
			<li v-for="item in icons" :key="item.id">
				<span>
					<i :class="item.iconClass"></i>
					<span class="icon-name">{{item.iconClass}}</span>
				</span>
			</li>
		</ul>
	</el-container>
</template>
<script>
	export default{
		name:'elIcon',
		data(){
			return {
				icons:[],
				queryParam:{
					iconClass:''
				}
			}
		},
		methods:{
			click:function(event){
				var target = event.target;
				var className = target.className;
				if(!className){
					this.$emit('iconSelect', $(target).find('i')[0].className);
				}else {
					if("icon-name" == className){
						this.$emit('iconSelect', target.innerText);
					}else{
						console.log(target);
						this.$emit('iconSelect', className);
					}
				}
			},
			reQuery(){
				this.ts.doPost(this, '/admin/icon/queryData',this.queryParam, null, function(vm, data) {
					if (data.data.code == 200) {
						vm.icons = data.data.data;
					} else {

					}
				});
			}
		},
		mounted:function(){
			this.reQuery();
		}
	}
</script>
<style scoped>
	li{
		display: inline-block;
		float: left;
		border: 1px solid darkgray;
	}
	li span{
		display: inline-block;
		width: 110px;
		height: 110px;
		font-size: 14px;
	}
	li span i{
		margin: 0 auto;
		display: block;
		font-size:30px;
		width: 50px;
		height: 50px;
	}
	li span span{
		width: 100px;
	}
	li:hover{
		color:#409eff;
		cursor: pointer;
	}
</style>