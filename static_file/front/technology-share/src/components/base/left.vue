<template>
	<el-container style="width:300px;height: auto;padding: 0;position: relative;overflow: hidden;">
		<canvas id="canvas" width="300"></canvas>
		<el-container style="position: absolute;">
			<el-header style="width:300px;height:auto;text-align: center;">
				<h2 style="color: white;">技术分享</h2>
				<el-avatar :size="120" :src="avatar"></el-avatar>
			</el-header>
			<el-main style="width: 300px;">
				<el-menu :router="router" @select="selectMenu" style="width:250px;margin:0 auto;text-align:center;border-radius: 30px;overflow: hidden;opacity:0.5;">
					<div v-for='(item,index) in menuTree'>
						<el-submenu v-if="item.children && item.children.length > 0" :index="item.id">
							<template slot="title">
								<i :class="item.icon"></i>
								<span>{{item.name}}</span>
							</template>
							<div v-for="(item1,index1) in item.children">
								<el-submenu v-if="item1.children && item1.children.length > 0" :index="item1.id">
									
								</el-submenu>
								<el-menu-item v-else :index="item1.url">
									<i :class="item1.icon"></i>
									<span>{{item1.name}}</span>
								</el-menu-item>
							</div>
						</el-submenu>
						<el-menu-item v-else :index="item.url">
							<i :class="item.icon"></i>
							<span>{{item.name}}</span>
						</el-menu-item>
					</div>
				</el-menu>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	export default {
		name: 'left',
		data() {
			return {
				menuTree:[],
				router: true,
				avatar: 'https://www.baidu.com/img/bd_logo1.png'
			}
		},
		methods:{
			initMenu(){
				this.ts.doPost(this,'/front/menu/menuTree',null,null,function(vm,data){
					if(data.data.code == 200){
						vm.menuTree = data.data.data;
					}
				});
			},
			selectMenu(index,indexPath){
				console.log(indexPath);
				if(this.ts.changeArticle){
					this.ts.curVue.changeArticle(indexPath[0]);
				}else {	
					this.ts['menuId'] = indexPath[0];
				}
			}
		},
		mounted: function() {
			init();
			this.initMenu();
		}
	}

	function init() {
		var canvas = document.getElementById("canvas");
		var can = canvas.getContext("2d");
		var s = window.screen;
		var w = canvas.width = s.width;
		var h = canvas.height = s.height;
		var text;
		var x;
		can.fillStyle = color2();

		var words = Array(256).join("1").split("");

		setInterval(draw, 50);

		function draw() {
			can.fillStyle = 'rgba(0,0,0,0.05)';
			can.fillRect(0, 0, w, h);
			can.fillStyle = color2();
			words.map(function(y, n) {
				text = String.fromCharCode(Math.ceil(65 + Math.random() * 57));
				x = n * 10;
				can.fillText(text, x, y)
				words[n] = (y > 758 + Math.random() * 484 ? 0 : y + 10);
			});
		}

		function color1() {
			var colors = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f'];
			var color = "";
			for (var i = 0; i < 6; i++) {
				var n = Math.ceil(Math.random() * 15);
				color += "" + colors[n];
			}
			return '#' + color;
		}

		function color2() {
			var color = Math.ceil(Math.random() * 16777215).toString(16);
			while (color.length < 6) {
				color = '0' + color;
			}
			return '#' + color;
		}

		function color3() {
			return "#" + (function(color) {
				return new Array(7 - color.length).join("0") + color;
			})((Math.random() * 0x1000000 << 0).toString(16))
		}
	}
</script>