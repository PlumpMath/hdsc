<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>默认BPF规则重置</title>
    <meta name="Description" content="">
    <meta name="Keywords" content="">
    <meta name="Author" content="JacksonCode">
	<style type="text/css">
		body{
			font-family:"微软雅黑";
		}
	</style>
    <link id="bs-css" href="${ctx}/pages/css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="${ctx}/pages/css/charisma-app.css" rel="stylesheet">
    <link rel="icon" href="${ctx}/pages/img/logo.png">
    
	<script src="${ctx}/pages/bower_components/jquery/jquery.min.js"></script>
	<script src="${ctx}/pages/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="${ctx}/pages/bower_components/chosen/chosen.jquery.min.js"></script>
	<script src="${ctx}/pages/js/jquery.cookie.js"></script>
	<script src="${ctx}/pages/js/jquery.history.js"></script>
	<script src="${ctx}/pages/js/charisma.js"></script>
	<script type="text/javascript">
		//查看默认过滤规则
		function showOriginalRule(){
			$.ajax({
				type:"post",
				dataType:"json",
				data:{},
				url:"${ctx}/viewDefBPF",
				success:function(result){
					if(typeof result.bpf != "undefined"){
						$("#old_bpf").val(result.bpf).attr("readonly","readonly");
						$("#originalRule").modal('show');
					}else{
						$("#messageContent").html("您尚未设置过滤规则!");
						$('#messageTip').modal('show');
					}
				}
			});
		};
	
	
        function clearAll(){
     		 $("#bpf").val("");
        };
        
        function clearBPFError(){
        	$("#error_msg").html("");
        };
        
        function saveAll(){ 		
           	var bpf = $("#bpf").val().toLowerCase();
           	if(bpf.trim()==""){
           		$("#error_msg").html("规则详情不能为空！");
           		//$("#bpf").focus();
           		return false;
           	}else if(bpf=="tcp"||bpf=="udp"||bpf=="icmp"||bpf=="arp"){
				 //$("input[name=saveT]").removeClass("disabled");
			}else{
				 $("#error_msg").html("* BPF设置不合法 请重新设置").css("color","#b50000");
				 return false;
			}
           	
           	//需要解绑点击事件再绑定点击事件 否则重复绑定则出错
           	$("#save").unbind("click").click(function(){
	           	var data = {bpf:bpf};
	           	$.ajax({
	           		type:"post",
	           		dataType:"json",
	           		data:data,
	           		url:"${ctx}/saveDefaultBPF",
	           		success:function(result){
	           			if(result.msg){
	           				$("#messageContent").html("BPF规则设置成功!");
							$('#messageTip').modal('show');
	           			}else{
	           				$("#messageContent").html("BPF规则设置失败!");
							$('#messageTip').modal('show');
	           			}
	           		}
	           	});
           	});
        	
        	$('#msg').html("您将使用新规则,是否继续？");
        	$('#message').modal('show');
        }
        
        
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
			$("#savePWD").addClass("disabled");
			//var isValid = false;
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
					$("#confirm_error").html("* 两次密码不一致");
				}else{
					$("#confirm_error").html("");
					//isValid = true;
					$("#savePWD").removeClass("disabled");
				}
			});
			
			//取消按钮点击事件
			$("#cancel").click(function(){
				isValid = false;
				//reset();在弹出错误消息存在，再次打开则消失
			});
			
			//保存按钮点击事件
			$("#savePWD").click(function(){
				//if(isValid){
					var o_pwd = $("#originalPwd").val();
					var n_pwd = $("#newPwd").val();
					$.ajax({
						type:"post",
						data:{user:user,oldP:o_pwd,newP:n_pwd},
						dataType:"json",
						url:"${ctx}/updatePwd",
						success:function(data){
							if(!data.msg){
								$("#messageContent").html("密码修改失败!");
								$('#messageTip').modal('show');
							}else{
								$("#messageContent").html("密码修改成功！");
								$('#messageTip').modal('show');
							}
						}
					});
					reset();
			});
			
			//弹出密码重置窗体
		    $('#updatePwd').modal('show');
		};
		
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
	    
	</script>
</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="display:block;width:220px;"> 
                <img alt="Logo" src="img/logo.png" class="hidden-xs"/>
                <span>高速数据流采集器</span>
            </a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">&nbsp;${user.username}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="showDialog()">密码重置</a></li>
                    <li class="divider"></li>
                    <li><a href="#" onclick="logoutConfirm()">注销</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->           
        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                 <ul class="nav nav-pills nav-stacked main-menu">
                    <li class="nav-header">&nbsp;</li>
                        <li>
                        	<a href="index.jsp">
                        	  <i class="glyphicon glyphicon-home"></i>&nbsp;
                        	  <span>首页</span>
                        	</a>
                        </li>
                        <li class="accordion">
                            <a href="#">
                            	<i class="glyphicon glyphicon-list-alt"></i>&nbsp;
                            	<span>任务管理</span>
                            </a>
                            <ul class="nav nav-pills nav-stacked">
                            	<li><a href="task_add.jsp">&nbsp;添加任务</a></li>
                            </ul>
                        </li>
                        <li class="accordion">
                            <a href="#">
                            	<i class="glyphicon glyphicon-align-left"></i>&nbsp;
                            	<span>BPF规则管理</span>
                            </a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="bpf_reset.jsp">重置BPF规则</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="login.jsp">
                              <i class="glyphicon glyphicon-lock"></i>&nbsp;
                              <span>注销登录</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>
        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            <div>
    <ul class="breadcrumb">
        <li>
            <a href="index.jsp">首页</a>
        </li>
        <li>
            <a href="bpf_reset.jsp">BPF规则设置</a>
        </li>
    </ul>
</div>
 
<div class="row" id="data">  
     <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-content">
                   <form class="form-horizontal" role="form">
<!-- 					  <div class="form-group"> -->
<!-- 					    <label for="bpf_name" class="col-sm-3 control-label">规则名称:</label> -->
<!-- 					    <div class="col-sm-6"> -->
<!-- 					      <input type="text" class="form-control" id="bpf_name"> -->
<!-- 					    </div> -->
<!-- 					    <label for="bpf_name" class="col-sm-3 control-label"></label> -->
<!-- 					  </div> -->
					  <div class="form-group">
					    <label for="bpf" class="col-sm-3 control-label">规则详情:</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" id="bpf" placeholder="仅支持：TCP / UDP / ICMP / ARP (不区分大小写)" onfocus="clearBPFError();">
					    </div>
					     <label class="col-sm-1 control-label"></label>
			            <div class="col-sm-2">
					      <input type="button" class="form-control" value="查看原规则" onclick="showOriginalRule()" >
					    </div>		    
					  </div>
					  <div class="form-group">
					    <label id="error_msg" class="col-sm-10 control-label" style="color:#BD3737;padding-right: 100px;"></label>
					    <div class="col-sm-1">
					      <input type="button" class="form-control btn btn-default" value="重置" onclick="clearAll()" />
					    </div>
					    <div class="col-sm-1">
					      <input type="button" class="form-control btn btn-primary" value="保存" onclick="saveAll()" />
					    </div>
					  </div>
				   </form>
                </div>
            </div>
        </div>
</div>
<!--     <footer class="row"> -->
<!--         <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="#">高速数据流采集器开发小组&nbsp;</a> 2015 - 2016</p> -->

<!--         <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a -->
<!--                 href="#">JacksonCode</a></p> -->
<!--     </footer> -->
</div>
<%@include file="dialog/showOrinRule.jsp" %>
<%@include file="dialog/warning.jsp" %>
<%@include file="dialog/updatePwd.jsp" %>
<%@include file="dialog/logoutConfirm.jsp" %>
<%@include file="dialog/message.jsp" %>
</body>
</html>
