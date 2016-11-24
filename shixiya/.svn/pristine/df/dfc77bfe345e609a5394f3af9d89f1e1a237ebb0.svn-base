<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	 <div class="main main1 model">
        <div class="choose-ability" id="chooseAbility">
            
        </div>
        <div class="choose-container choose1" id="choose1">
            提交能力&nbsp;Click me
        </div>
    </div>
    
    
    
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
	
		<jsp:include page="foot.jsp"></jsp:include>
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	
	
	<script src="<%=path%>/omg/js/abilityAjax/abilityAjax.js"></script>
</body>
</html>