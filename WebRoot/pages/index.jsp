<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>高速数据流采集器</title>
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
	function nextPage(page){
		var num = Number(page.html());
		$.ajax({
			type:"post",
			url:"${ctx}/authentication",
			data:{pageNum:num},
			success:function(result){
				window.location.reload();
			}
		});	
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
		$.ajax({
			type:"post",
			url:"${ctx}/task/delete",
			data:{id:taskId},
			success:function(result){
				if(result.msg){
					window.location.reload();
				}else{
					$("#messageContent").html("数据删除失败!");
					$('#messageTip').modal('show');
				}
			}
		});
	};
	
	function seeDetail(obj){
		var id = obj.parent().parent().find("input[name='taskId']").val();
		/*
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
	    			*
			       $("#taskStatus").modal('show'); 
				}else{
					alert("任务执行失败，BPF有误:"+result.bpf);	
				}
			}
		});
		*/
		window.location.href = "${ctx}/pages/dialog/taskStatusDetail.jsp?id="+id;
	};
	
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
                <img alt="Logo" src="${ctx}/pages/img/logo.png" class="hidden-xs"/>
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
                            <a href="#" onclick="logoutConfirm()">
                              <i class="glyphicon glyphicon-lock"></i>&nbsp;
                              <span onclick="logoutConfirm()" >注销登录</span>
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
            <a href="#">首页</a>
        </li>
    </ul>
</div>
<div class=" row">
    <div class="col-md-3 col-sm-3 col-xs-6" >
        <a data-toggle="tooltip"  class="well top-block" href="#">
            <i class="glyphicon glyphicon-list blue"></i>
            <div>总任务数</div>
            <div>${indexDao.totalTask}</div>
            <span class="notification">${indexDao.totalTask}</span>
        </a>
    </div>

    <div id="task_ing" class="col-md-3 col-sm-3 col-xs-6"  style="margin-left:139px;margin-right:139px;">
        <a data-toggle="tooltip"  class="well top-block" href="#">
<!--         <a data-toggle="tooltip" data-original-title="进行中:12" class="well top-block" href="#"> -->
            <i class="glyphicon glyphicon-tasks green"></i>
            <div>进行中</div>
            <div>${indexDao.task_ing}</div>
            <span class="notification green">${indexDao.task_ing}</span>
        </a>
    </div>

    <div class="col-md-3 col-sm-3 col-xs-6" >
        <a data-toggle="tooltip"  class="well top-block" href="#">
            <i class="glyphicon glyphicon-list-alt yellow"></i>
            <div>已完成</div>
            <div>${indexDao.task_done}</div>
            <span class="notification yellow">${indexDao.task_done}</span>
        </a>
    </div>
</div>

<div class="row" id="data">  
     <div class="box col-md-12">
            <div class="box-inner">
               <!-- <div class="box-header well" data-original-title="">
                    <h2 id="title">任务总数</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                  -->
                <input id="delId" type="hidden" />
                <div class="box-content">
                    <table class="table table-bordered table-striped table-condensed">
                       <c:choose>
                         <c:when test="${!empty indexDao and indexDao.totalTask!=0}">
	                        <thead>
		                        <tr>
		                            <th>任务名</th>
		                            <th>所属用户</th>
		                            <th>执行状态</th>
		                            <th>更多操作</th>
		                        </tr>
	                        </thead>
	                        <tbody>
	                          <c:forEach items="${indexDao.tasks}" var="task">
			                        <tr>
			                            <input type="hidden" value="${task.id}" name="taskId"/>
			                            <td>${task.taskName}</td>
			                            <td class="center">${user.username}</td>
<!-- 			                            <td class="center"><fmt:formatDate value="${task.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> -->
			                            <td class="center">
			                              <c:choose>
			                            	<c:when test="${task.state==1}">
				                            	<span class="label-warning label label-default">${task.stateString}</span>
			                            	</c:when>
			                            	<c:otherwise>
				                            	<span class="label-success label label-default">${task.stateString}</span>
			                            	</c:otherwise>
			                              </c:choose>
			                            </td>
			                            <td class="center">
			                               <a id="seeDetail" onclick="seeDetail($(this));"  class="btn btn-info" href="#" style="width:80px;height:25px;padding:0;">
			                					<i class="glyphicon glyphicon-edit icon-white"></i>&nbsp;查看详情
			            					</a>
			                                <a id="delete" onclick="delConfirm($(this));" class="btn btn-danger" href="#" style="width:60px;height:25px;padding:0;">
			                                    <i class="glyphicon glyphicon-trash icon-white"></i>&nbsp;删除
			                                </a>
			                            </td>
			                        </tr>
	                       		</c:forEach>
	                       	  </tbody>
                         	</c:when>
                         	<c:otherwise>
                         	  <span style="color:#3A87AD;font-size:18px;font-weight:700;display:block;margin-left:470px;">当前无任务记录</span>
                         	</c:otherwise>
                         </c:choose>
                    </table>
                    <ul class="pagination pagination-centered">
                       <c:forEach var="num" varStatus="page" items="${indexDao.page}">
                       	  <c:choose>
                          	<c:when test="${num==1}">
                          		<li><a onclick="nextPage($(this))" href="#">${num}</a></li>
                          	</c:when>
                          	<c:otherwise>
                          		<li><a href="#" onclick="nextPage($(this))">${num}</a></li>
                          	</c:otherwise>
                          </c:choose>
                       </c:forEach>
                    </ul>
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
<%@include file="dialog/updatePwd.jsp" %>
<%@include file="dialog/logoutConfirm.jsp" %>
<%@include file="dialog/deleteConfirm.jsp" %>
<%@include file="dialog/warn.jsp" %>
<%@include file="dialog/message.jsp" %>

</body>

</html>
