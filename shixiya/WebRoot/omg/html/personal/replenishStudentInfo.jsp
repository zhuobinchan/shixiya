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
<title>我的简历</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/person.css">
<style>
input {
	border: none;
	width: 100%;
	height: 100%
}

input[type="submit"] {
	display: block;
	width: 60%;
	height: 10%;
	margin: 0 auto;
	border-radius: 8px;
	line-height: 30px;
	font-size: 18px;
	background-color: #83beff;
	color: #ffffff
}
</style>
</head>
<body>
	<form action="ReplenishStudentInfo_rsInfoAction.action" method="post">
		<table class="intro-tb" cellspacing="0">
		

			
			<tr>
				<td>姓名</td>
				<td><input type="email" name="name" required="required"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="email" name="email" required="required"></td>
			</tr>
			<tr>
				<td>手机号码</td>
				<td><input type="tel" name="telephone" required="required"></td>
			</tr>
			
		</table>
		<input type="text" style="display: none"
			value="<%=request.getParameter("pType")%>" name="productType" /> <input
			type="text" style="display: none" value="${param.courseId }"
			name="courseId" /> <input type="text" style="display: none"
			value="${param.broadcastId }" name="broadcastId" /> <input
			type="submit" value="提&nbsp;&nbsp;交">
	</form>
	<p style="color: red;">${message }</p>
		<jsp:include page="../curriculum/foot.jsp"></jsp:include>
</body>
</html>