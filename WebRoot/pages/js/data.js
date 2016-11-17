var option = {
	//标题，每个图表最多仅有一个标题控件，每个标题控件可设主副标题。
	title: {
		x: 'center',                                //水平安放位置
		text: '任务当前执行状态',                        //主标题文本
		subtext: '即时数据',                   		//副标题文本
		link: 'http://echarts.baidu.com/doc/example.html' //主标题文本超链接
	},
	//提示框，鼠标悬浮交互时的信息提示。
	tooltip: {
		trigger: 'item' //触发类型，默认数据触发
	},
	//工具箱，每个图表最多仅有一个工具箱。
	toolbox: {
		show: true, //显示策略
		feature: {
			//数据视图 
			dataView: {show: true, readOnly: false},
			//还原，复位原始图表
			restore: {show: true},
			magicType: {show: true, type: ['line', 'bar']},  
			//保存图片
			saveAsImage: {show: true}
		}
	},
	//是否启用拖拽重计算特性
	calculable: true,
	//直角坐标系内绘图网格
	grid: {
		borderWidth: 0,//边框线宽
		y: 80,         //直角坐标系内绘图网格左上角纵坐标，数值单位px，支持百分比
		y2: 60         //直角坐标系内绘图网格右下角纵坐标，数值单位px，支持百分比
	},
	//直角坐标系中横轴数组，数组中每一项代表一条横轴坐标轴，仅有一条时可省略数组
	xAxis: [
		{
			type: 'category', //坐标轴类型
			show: false,       //显示策略
			//类目列表，同时也是label内容
			data: ['packetNumbers', 'tcpNumbers', 'udpNumbers', 'icmpNumbers', 'arpNumbers', 'trafficStatistis','packetSize'] //澧炲垹鏁版嵁椤规爣棰�

		}
	],
	//直角坐标系中纵轴数组，数组中每一项代表一条纵轴坐标轴，仅有一条时可省略数组。
	yAxis: [
		{
			type: 'value',   //坐标轴类型
			show: false     //显示策略
		}
	],
	//驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据，其中个别选项仅在部分图表类型中有效
	series: [
		{
			name: '任务当前状态',  //系列名称
			type: 'bar',  //图表类型
			//图形样式
			itemStyle: {
				normal: {
					color: function(params) {
						// build a color map as your need.
						var colorList = [
						  '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
						   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
						   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
						];
						return colorList[params.dataIndex]
					},
					label: {
						show: true,
						position: 'top',
						formatter: '{b}\n{c}'
					}
				}
			},
			data: [21,45,35,65,52,78,198],   //增删数据
			markPoint: {
				tooltip: {
					trigger: 'item',
					backgroundColor: 'rgba(0,0,0,0)',
					formatter: function(params){
						return '<img src="' 
								+ params.data.symbol.replace('image://', '')
								+ '"/>';
					}
				},
				data: [
						{xAxis:0, y: 350, name:'Line', symbolSize:20, symbol: 'image://../asset/ico/折线图.png'},
						{xAxis:1, y: 350, name:'Bar', symbolSize:20, symbol: 'image://../asset/ico/柱状图.png'} 
				]
			}
		}
	]
};   