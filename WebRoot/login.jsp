<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>高速数据流采集器</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta name="Author" content="JacksonCode">
    <link id="bs-css" href="${ctx}/resource/css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="${ctx}/resource/css/charisma-app.css" rel="stylesheet"> 
    <link rel="icon" href="${ctx}/resource/img/logo.png">
    
	<script src="${ctx}/resource/bower_components/jquery/jquery.min.js"></script>
 	<script src="${ctx}/resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> 
	<script type="text/javascript">
		function onSubmit(){
			var user = $("input[name=username]").val();
			var pwd = $("input[name=password]").val();
			if(user==null || user.trim()==""){
				showDialog(" 请 输 入 用 户 名  ");
				return false;
			}else if(pwd==null || pwd.trim()==""){
				showDialog(" 请 输 入 密 码  ");
				return false;
			}
			return true;
		};
		function showDialog(msg){
			$('#warn_msg').html(msg);
	        $('#warn').modal('show');
	    };
	</script>
</head>

<body>
<div class="ch-container">
    <div class="row">
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2  style="letter-spacing:30px;margin-left:36px;">高速数据流采集器</h2>
        </div>
    </div> 

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info" style="letter-spacing:5px;"> 高速数据流采集器开发小组  </div>
            <form class="form-horizontal" onsubmit="return onSubmit();" action="${ctx}/authentication" method="post">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user" style="color:rgb(141, 66, 49);"></i></span>
                        <input name="username" type="text"  placeholder="请 输 入 用 户 名" class="form-control" value="" />
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock" style="color:rgb(141, 66, 49);"></i></span>
                        <input name="password" type="password"  placeholder="请 输 入 密 码" class="form-control" value="" />
                    </div>
                    <div class="clearfix"></div>
                    <p class="center col-md-5">
                        <span id="error" style="color:#b50000;font:24px;font-weight:700;">${loginError}</span>
                        <button type="submit" class="btn btn-primary">登&nbsp;录</button>
                    </p>
                </fieldset>
            </form>
        </div>
    </div> 
</div> 

</div> 
<%@include file="pages/dialog/warn.jsp" %>
</body>
</html>
