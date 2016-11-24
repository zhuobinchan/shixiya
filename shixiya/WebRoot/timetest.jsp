<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.wxtip {
	background: rgba(0, 0, 0, 0.8);
	text-align: center;
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 998;
	display: none;
}
.wxtip-txt {
	margin-top: 107px;
	color: #fff;
	font-size: 16px;
	line-height: 1.5;
}
</style>
</head>

<body>



	<a href="media/1.mp4" id="JdownApp">点击下载APP</a>
	<div class="wxtip" id="JweixinTip">
		<p class="wxtip-txt">
			点击右上角<br />选择在浏览器中打开
		</p>
	</div>
<script type="text/javascript">

function weixinTip(ele){
	var ua = navigator.userAgent;
	var isWeixin = !!/MicroMessenger/i.test(ua);
	if(isWeixin){
		ele.onclick=function(e){
			window.event? window.event.returnValue = false : e.preventDefault();
			document.getElementById('JweixinTip').style.display='block';
		}
		document.getElementById('JweixinTip').onclick=function(){
			this.style.display='none';
		}
	}
}
var btn1 = document.getElementById('JdownApp');//下载一
weixinTip(btn1);
</script>
</body>
</html>

