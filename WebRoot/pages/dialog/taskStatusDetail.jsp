<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>查看详情</title>
		<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
		<style type="text/css">
			#container, #sliders {
				min-width: 310px; 
				max-width: 800px;
				margin: 0 auto;
			}
			#container {
				height: 400px; 
			}
		</style>
		<link rel="icon" href="../img/logo.png">
		<script type="text/javascript">
			 
			$(function () {
				// Set up the chart
				var taskId = ${param.id};
				var taskStatus;
				var taskInfo;
				$.ajax({
					type:"post",
					dataType:"json",
					url:"${ctx}/task/seeDetail",
					data:{taskId:taskId},
					async:false,
					success:function(result){
						//alert(typeof result.dataArray[0]);
						//taskStatus = [80,109,145,162,154,131,68];
						//alert(typeof taskStatus[0]);
						taskInfo = result;
						taskStatus = result.dataArray;
					}
				});
				if(typeof taskStatus=="undefined"){//任务尚未执行 未查询到任何数据
					$("#container").html("<a href='../index.jsp' style='color:#3088e9;display:block;width:400px;height:30px;margin:100px auto;padding:20px;'>任务处于队列等待中，请稍后查看详情 ，点击返回首页</a>");
					return false;
				}else if(taskInfo.error==1){//任务执行时出错
					$("#container").html("<a href='../index.jsp' style='color:#3088e9;display:block;width:400px;height:30px;margin:100px auto;padding:20px;'>任务执行时出错，您的BPF有误:"+taskInfo.bpf+"</a>");
					return false;
				}
				var chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						type: 'column',
						options3d: {
							enabled: true,
							alpha: 0,
							beta: 0,
							depth: 50,
							viewDistance: 25
						}
					},
					title: {
						text: '任务状态图',
						style:{
								color:"#69f",
								fontSize: "24px",
								fontWeight: "blod",
								fontFamily: "Courir new"
						}
					},
					subtitle: {
						text: ''
					},
					xAxis:{
						categories:['总包数','TCP','UDP','ICMP','ARP','流量','包大小']
					},
					yAxis:{
						tickInterval: 1000,  //自定义刻度  
						max:300000,			//纵轴的最大值  
			            min: 0,				//纵轴的最小值  
						title: {//纵轴标题  
							text: ''  
						},  
						labels : {  
							formatter : function() {//设置纵坐标值的样式  
							 //return this.value + '%'; 
							 return this.value;
							}  
						}  
					},
					plotOptions: {
						column: {
							depth: 25
						}
					},
					tooltip: {
						//提示框外观修饰
						backgroundColor: '#d7e8f9',   // 背景颜色
						borderColor: '#fff',          // 边框颜色
						borderRadius: 10,             // 边框圆角
						borderWidth: 1,               // 边框宽度
						shadow: true,                 // 是否显示阴影
						animation: true ,             // 是否启用动画效果
						/*style: {                      // 文字内容相关样式
							color: "#ff0000",
							fontSize: "12px",
							fontWeight: "blod",
							fontFamily: "Courir new"
						},*/
						formatter: function() {
							return this.x+':'+this.y							
						}
					},
					series: [{
						name:'数据值',
					    data:taskStatus
				    }]
				});	
				
				function showValues() {
			        $('#alpha-value').html(chart.options.chart.options3d.alpha);
			        $('#beta-value').html(chart.options.chart.options3d.beta);
			        $('#depth-value').html(chart.options.chart.options3d.depth);
			    }

			    // Activate the sliders
			    $('#sliders input').on('input change', function () {
			        chart.options.chart.options3d[this.id] = this.value;
			        showValues();
			        chart.redraw(false);
			    });

			    showValues();
			});
		</script>
	</head>
	<body>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts-3d.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>

<div id="container" style="margin-top:50px;margin-bottom:1px;box-shadow: 1px 1px 25px rgba(96, 125, 139, 0.52);"></div>
<div id="sliders" style="position:relative;box-shadow:0 0 10px rgba(96, 125, 139, 0.52);">
	<table>
        <tr>
        	<td>Alpha Angle</td>
        	<td><input id="alpha" type="range" min="0" max="45" value="15"/> <span id="alpha-value" class="value"></span></td>
        </tr>
        <tr>
        	<td>Beta Angle</td>
        	<td><input id="beta" type="range" min="-45" max="45" value="15"/> <span id="beta-value" class="value"></span></td>
        </tr>
        <tr>
        	<td>Depth</td>
        	<td><input id="depth" type="range" min="20" max="100" value="50"/> <span id="depth-value" class="value"></span></td>
        </tr>
    </table>
	<div style="position:absolute;right:5px;bottom:5px;">
		<a href="../index.jsp" style="text-decoration:none;color:#00329b;font-weight:700;">返回首页</a>
	</div>    
</div>
	</body>
</html>
