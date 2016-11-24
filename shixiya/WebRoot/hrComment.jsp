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

<title>My JSP 'index.jsp' starting page</title>
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
<title>翻转课堂</title>
<link rel="stylesheet" href="<%=path%>/omg/css/chat.css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>

</head>

<body>
	<div class="video">
		<video width="320" height="240" controls="controls"> <source
			src="<%=path%>/media/1.mp4" type="video/mp4"></video>
	</div>

	<div id="id_video_container" style="width:100%;height:400px;"></div>
	<script
		src="http://qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js"></script>
	<script type="text/javascript">
		(function() {
			var option = {
				"auto_play" : "0",
				"file_id" : "14651978969265670218",
				"app_id" : "1251788371",
				"width" : 640,
				"remember" : 1,
				"height" : 400
			};
			/*调用播放器进行播放*/
			new qcVideo.Player(
			/*代码中的id_video_container将会作为播放器放置的容器使用,可自行替换*/
			"id_video_container", option);
		})()
	</script>
</body>

</html>
