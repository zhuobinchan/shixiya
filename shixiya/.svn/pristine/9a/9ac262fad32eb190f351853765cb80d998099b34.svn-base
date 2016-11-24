<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--IE8兼容-->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<!--禁止缩放-->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!--手机满屏变大-->
<meta name="format-detection" content="telephone=no">
<!--去掉手机强制某些数值识别成链接-->
<title>能力匹配</title>
</head>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  <!--IE8兼容-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" /><!--禁止缩放-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">  <!--手机满屏变大-->
    <meta name="format-detection" content="telephone=no"> <!--去掉手机强制某些数值识别成链接-->
    <title>能力匹配</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/ability.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/person.css">
    
    <style>
        body,html{width: 100%;height: 100%;overflow-x: hidden;}
        .main{width: 100%;height: 100%;margin: 0;padding: 5px 0;background: url("../../images/ability-bg.png") 0 0/100% 100% no-repeat;}
        .txt2-footer>span{float: left;margin-left: 3px}
        .tuwen-model>li{padding:0 4px 0 6px; margin: 8px 0}
        .s-title{color: #075090!important;}
    </style>
</head>


<body>

    <div class="main main2 model" style="display: none">
        正在为你匹配，请稍候...
        <div class="main2-logo">
        	<img src="" >
        </div>
        <div class="choose-container choose2">
            匹配中...
        </div>
    </div>
	
	 <div class="main3 model" style="display: none">
        <div class="choose-container choose3" >
            再测一次
        </div>
        <div class="main3-title">
            为您匹配
            <div class="line-dot"></div>
        </div>
        <ul class="tuwen-model" style="width: 96%;margin-top: 0;padding: 0 2%;">
            
    </div>
	<input id="lableNames" type="hidden" value="<%= request.getParameter("lableNames") %>">
		<jsp:include page="foot.jsp"></jsp:include>
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	
	
	<script src="<%=path%>/omg/js/abilityAjax/abilityAjax.js"></script>
	<script type="text/javascript">
	
			var m2 = $(".main2"), m3 = $(".main3");
	
			m2.css("display", "block").siblings(".model").css(
					"display", "none");
			var mm = setTimeout(function() {
				m3.css("display", "block").siblings(".model").css(
						"display", "none");
			}, 3000);
			
			showCourseFromLableByAjax($("#lableNames").val());
			showBroadcastFromLableByAjax($("#lableNames").val());
	</script>
</body>
</html>