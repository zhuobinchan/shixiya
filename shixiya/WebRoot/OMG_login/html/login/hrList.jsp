<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	if (session.getAttribute("hr") == null) {
		response.sendRedirect("hrLogin.jsp");
		return;
	}
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
<title>课程列表</title>
<link rel="stylesheet" href="<%=path%>/OMG_login/css/style.css">
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path%>/omg/js/b-js.js"></script>


</head>
<body>

	<div class="nav-name">
		<ul>
			<li class="visited boss" id="bossLi">boss直播间</li>
			<li class="hr" id="hrLi">HR帮帮堂</li>
		</ul>
	</div>

	<ul class="tuwen-model" style="margin-top: 15px">
		<c:forEach items="${cpHr.CProductBroadcasts}" var="broadcast">
			<li><a
				href="enterCpChat_chat.action?roomId=Hr1994cp${broadcast.id}&role=hr">
					<div class="img-show">
						<img src="<%=path%>/${broadcast.imgUrl}">
					</div>
					<div class="txt-show">
						<p class="s-title">${broadcast.name }</p>
						<p class="txt2">${broadcast.introduction }</p>
						<p class="txt2-footer">
						<span>
						<a style="color: #2596c2;font-size: 8px"
							href="showQuestion_CPHrCheckAnswerAction?broadcastId=${broadcast.id }">查看答案</a>
						</span>
						<span><a style="color: #2596c2;font-size: 8px" href="findStuApply_CheckApplyAction?broadcastId=${broadcast.id }">报名审核</a></span>
						<span style="color: #2596c2;font-size: 8px">参与人数：${broadcast.participation }</span>					
					</div>
			</a></li>
		</c:forEach>
	</ul>
	<a href="javascript:void(0)" onclick="javascript:alert('服务未开通，敬请期待');" ><i class="wsq"></i></a>
<script src="../../js/hrListOrBroadcast.js"></script>
</body>
</html>