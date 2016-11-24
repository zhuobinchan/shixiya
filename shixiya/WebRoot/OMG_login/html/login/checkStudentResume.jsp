<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>学生简历</title>
<link rel="stylesheet" href="<%=path %>/OMG_login/css/table.css">
</head>
<body>
	<div class="header">学生简历</div>
	<div class="main">
		<table>
		<tr>
		  <th>${resume.name }<th>
		  <th></th>
		</tr>
		</table>
		<table border="1" align="center" >
			<tr>
				<th  colspan="2"><b>个人信息</b></th>
			</tr>
			<c:forEach items="${personInfo}" var="pi"> 
			<tr>
				<td align="center"  bgcolor=#D0D0D0  >${pi.key}</td>
				<td align="center" >${pi.value}</td>
			</tr>
			</c:forEach>
			<tr>
				<th  colspan="2">教育背景</th>
			</tr>
			<c:forEach items="${education}" var="edu"> 
			<tr>
				<td align="center"  bgcolor=#D0D0D0  >${edu.key}</td>
				<td align="center" >${edu.value}</td>
			</tr>
			</c:forEach>
		
			<tr>
				<td align="center" bgcolor=#D0D0D0>自我评价：</td>
				<td align="center">${selfAssessment}</td>
			</tr>
		</table>
	</div>
</body>
</html>