<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'hrChating.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="http://res.wx.qq.com/o pen/js/jweixin-1.0.0.js"></script>
</head>

<body>
	<br>
	<div>
	<video id="myVideo" controls preload width="100%" height="20%">
	  <source src="<%=path %>/media/American.mp4" type="video/mp4">
	  <p>Your browser does not support the video tag.</p>
	</video>
	</div>
	<div id="record"></div>
	<div style="width: 100%;height: 5%;position:fixed;bottom:0;">
		<span class="desc">开始录音</span>
		<button class="btn btn_primary" id="startRecord">startRecord</button>
		<span class= "desc">停止录音</span>
		<button class="btn btn_primary" id="stopRecord">stopRecord</button>
	</div>
</body>
<script type="text/javascript" src="<%=path%>/js/websocket.js"></script>
<script type="text/javascript" src="<%=path%>/js/simple.js"></script>
<script type="text/javascript" src="<%=path%>/js/weixin.js"></script>
</html>
