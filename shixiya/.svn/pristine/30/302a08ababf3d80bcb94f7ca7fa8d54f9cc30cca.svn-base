<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title>${trainWEProductChild.title}</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
</head>
<%
	request.setAttribute("vEnter", "\r\n");   
	request.setAttribute("nEnter", "<br>");
%> 

<body>
	<div class="main-content">
		<div class="header">
			<img src="<%=path%>/${trainWEProductChild.imgUrl}">
		</div>
		<div class="detail">
			<p class="title" style="margin-left: 0">${trainWEProductChild.title}</p>
			<p class="txt3">${fn:replace(trainWEProductChild.introduction, vEnter, nEnter)}</p>
		</div>
		<div class="line"></div>
		<div class="intro">
			<div class="img">
				<img src="<%=path%>/${trainWEProductChild.trainWEProduct.trainWETeacher.headImgUrl}">
			</div>
			<p class="s-title">${trainWEProductChild.trainWEProduct.trainWETeacher.name}</p>
			<p class="txt3">${fn:replace(trainWEProductChild.trainWEProduct.trainWETeacher.introduction, vEnter, nEnter)}</p>
		</div>
		<p class="s-title" style="text-align: center">
			开始时间：
			<fmt:parseDate value="${trainWEProductChild.startTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>
		<p class="s-title" style="text-align: center">
			结束时间：
			<fmt:parseDate value="${trainWEProductChild.endTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>		
		<button id="enter" onclick="window.location.href='enterWeCourseChat_chat.action?roomId=th${trainWEProductChild.id}'" class="yellow-btn">进入培训</button>
		<a href="javaScript:void(0)">意见反馈 <img src="<%=path%>/omg/images/tuwen/next.png"></a>
		<!--报名成功弹窗-->
	</div>
		<jsp:include page="../foot.jsp"></jsp:include>

	
</body>
</html>