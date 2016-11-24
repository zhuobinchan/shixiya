<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("hr") == null) {
		response.sendRedirect("hrLogin.jsp");
		return;
	}
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
<title>龙虎榜</title>
<link rel="stylesheet" href="../../css/table.css">
<style type="text/css">
input[type="submit"],input[type="button"] {
	width: 32%;
	background-color: #3879D9;
	font-size: 13px;
	line-height: 30px;
	border-radius: 18px;
	border: none;
	color: #ffffff
}
</style>
</head>
<body>
	<div class="header">龙虎榜</div>
	<div class="main">
		<h3>${broadcast.name }直播间的进入龙虎榜名单</h3>
		<table border="1">
			<tr>
				<td>学生姓名</td>
				<td>学生手机号码</td>
				<td>学生邮箱</td>
			</tr>
			<c:forEach items="${employs }" var="employ">
				<tr>
					<td style="display: none" class="myEmployIds">${employ.id }</td>
					<td>${employ.name }</td>
					<td>${employ.telephone }</td>
					<td>${employ.email }</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<div class="btns">
		<input type="button" value="修改龙虎榜" id="deleteEmployMessage">
	</div>
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var strIds = [];
			$(".myEmployIds").each(function() {
				strIds.push($(this).text());
			});
			$("#deleteEmployMessage").click(function deleteEmployMessage() {
				if(confirm("是否修改，确定后就重修选择学生，原来的龙虎榜信息将会删除")){
					alert(strIds.toString());
					$.post("deleteEmploys_CPHrEmployStudent.action", {
						employIds : strIds.toString()
					}, function(result) {
						if(result.message=='true'){
							alert("成功修改");
							window.location.href=document.referrer;
						}
					}, "json");
				}
			});
		});
	</script>
</body>
</html>