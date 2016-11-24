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
<title>HR登录</title>
<link href="../../css/login.css" rel="stylesheet">
</head>
<body>
	<div class="header">HR登录</div>
	<div class="main">
		<form action="hrLogin_CPHrLoginAction" method="post">
			<input type="text" name="telephone" placeholder="请输入账号"><br>
			<input type="password" name="password" placeholder="6-16位数字或字母,区分大小写"><br>
			<input type="submit" value="登&nbsp;&nbsp;录" onclick="check()">
		</form>
	</div>
	<p style="color: red;">${errorMessage }</p>
	<script>
		function check() {
			var bb = document.getElementById("password").value;
			isalphanumber(bb);
		}
		function isalphanumber(str) {
			var result = str.match(/^[a-zA-Z0-9]{6,16}$/);
			if (result == null) {
				alert("密码格式错误！");
			} else {
				return true;
			}
		}
	</script>
</body>
</html>