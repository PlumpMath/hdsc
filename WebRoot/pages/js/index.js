
//下一页点击事件
function nextPage(page){
	//if(page.parent().attr("class").indexOf("active")!=-1){
		var num = Number(page.html());
		//请求下一页数据
		$.ajax({
			type:"post",
			url:"${ctx}/authentication",
			data:{pageNum:num},
			success:function(result){
				window.location.reload();
				/*
				var liDoms = page.parent().parent().children();
				$.each(liDoms,function(){
					if($(this).index()==page.parent().index()){
						$(this).removeClass("active");
						$(this).addClass("disabled");
					}
					if($(this).attr("class").indexOf("disabled")!=-1&&$(this).index()!=page.parent().index()){
						$(this).removeClass("disabled");
						$(this).addClass("active");
					}
				});
				*/
			}
		});	
	//}
};
 
//密码重置输入框重置
function reset(){
	$("#username").val("");
	$("#originalPwd").val("");
	$("#origin_pwd_error").html("");
	$("#newPwd").val("");
	$("#confirm").val("");
	$("#confirm_error").html("");
};
//密码重置
function showDialog(){
	reset();
	var isValid = false;
	//获取当前用户名
	var user = '${user.username}';
	$('#username').val(user);
	//原始密码验证
	$("#originalPwd").blur(function(){
		var originalPwd = $(this).val();
		$.ajax({
			type:"post",
			data:{originalPwd:originalPwd,user:user},
			dataType:"json",
			url:"${ctx}/confirmPwd",
			success:function(data){
				if(!data.msg){
					$('#origin_pwd_error').html("* 原密码有误");
					$('#originalPwd').val("");
					$('#originalPwd').focus();
				}else{
					$('#origin_pwd_error').html("");
				}
			}
		});
	});
	
	//新密码核对
	$("#confirm").blur(function(){
		var newPwd = $("#newPwd").val();
		var confirmPwd = $(this).val();
		if(newPwd != confirmPwd){
			$(this).val("");
			$("#newPwd").val("");
			$("#newPwd").focus();
			//输入确认新密码后不能点击输入框右边空白部分 否则以下代码失效
			$("#confirm_error").html("* 密码不一致");
		}else{
			$("#confirm_error").html("");
			isValid = true;
		}
	});
	
	//取消按钮点击事件
	$("#cancel").click(function(){
		isValid = false;
		//reset();在弹出错误消息存在，再次打开则消失
	});
	
	//保存按钮点击事件
	$("#save").click(function(){
		if(isValid){
			var o_pwd = $("#originalPwd").val();
			var n_pwd = $("#newPwd").val();
			$.ajax({
				type:"post",
				data:{user:user,oldP:o_pwd,newP:n_pwd},
				dataType:"json",
				url:"${ctx}/updatePwd",
				success:function(data){
					if(!data.msg){
						alert("密码修改失败");
					}else{
						alert("密码修改成功");
					}
				}
			});
			reset();
		}
	});
	
	//弹出密码重置窗体
    $('#updatePwd').modal('show');
};

//注销确认
function logoutConfirm(){
	 $('#logout_confirm').modal('show');
	 $("#quit").click(function(){
		 //$.post("${ctx}/logout");
		 //$.ajax({
         //type:"post",
         //url:"${ctx}/logout",
         //async:false
         //});
         //以上两种方式执行了response但不能跳转到登录 原因不明
         window.location.href="${ctx}/logout";
	 });
};

function showMsg(){alert(1)};

//注册查看详情 删除事件
/* 注册失败。。。
function registerEvent(){
	alert(1);
	$("#seeDetail").click(function(){
		alert("查看详情");
	});
	alert(2);
	$("#delete").click(function(){
		var status = $(this).parent().prev().children().html();
		alert(status);
	});
};
registerEvent();
*/
function delConfirm(obj){   
	var status = obj.parent().prev().children().html();
	if(status=="任务执行中"){
		$("#warn_msg").html("任务执行中，不能删除！");
		$('#warn').modal('show');
	}else{
		var id = obj.parent().parent().find("input[name='taskId']").val();
		$("#delId").val(id);
		$("#deleteConfirm").modal('show');	
	}
};

function deleteTask(){	
	var taskId = $("#delId").val();
	//alert(taskId);
	$.ajax({
		type:"post",
		url:"${ctx}/task/delete",
		data:{id:taskId},
		success:function(result){
			if(result.msg){
// 						var page = $("li[class='disabled']");
// 						if(page.length>1){
// 							page = page.eq(1).find("a").html();
// 						}else{
// 							page = page.eq(0).find("a").html();
// 						}
// 						$.ajax({
// 							type:"post",
// 							url:"${ctx}/authentication",
// 							data:{pageNum:page},
// 							success:function(result){
// 								window.location.reload();
// 							}
// 						});
				window.location.reload();
			}else{
				alert("数据删除失败");
			}
		}
	});
};

function seeDetail(obj){
	var id = obj.parent().parent().find("input[name='taskId']").val();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${ctx}/task/seeDetail",
		data:{taskId:id},
		success:function(result){
			if(result.error==""){
			    // 基于准备好的dom，初始化echarts图表
				var container = $("#data");
		        var myChart = echarts.init(container);	
		        $.each(option,function(key,value){
		        	if(key=="series"){
		        		value[0].data=result.dataArray;
		        		return false;
		        	}
		        });
		        myChart.setOption(option); 
				/*
				$("#totoal").val(result.packetNumbers).attr({"readonly":true});
    			$("#tcp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#udp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#icmp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#arp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#flow").val(result.tcpNumbers).attr({"readonly":true});
    			$("#size").val(result.tcpNumbers).attr({"readonly":true});
    			
    			*/
		       $("#taskStatus").modal('show'); 
			}else{
				alert("任务执行失败，BPF有误:"+result.bpf);	
			}
		}
	});
};
