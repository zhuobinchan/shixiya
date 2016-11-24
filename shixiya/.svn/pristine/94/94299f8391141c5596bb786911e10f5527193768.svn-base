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
<title>答疑</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/person.css">
</head>
<body onload="javascript:sendAnswerTimer()">
	<div class="container">
	<input type="hidden" value="${cpBroadcast.id}" id="broadcastId"/>
		<c:forEach items="${cpBroadcast.CProductQuestions }" var="question"
			begin="0" end="0">
			<h4 id="limittime" limittime="${question.limitTime }">问题标题：${question.title } 时长：${question.limitTime }分钟后自动提交</h4>
			<h4 id="limittime" style="color:red;">若提交空卷将会影响你是否被录用，请认真完整作答。</h4>
				<ul class="answer-list">
					<c:forEach items="${question.CProductQuestionLists }"
						var="questionList" varStatus="status">
						<li>
							<p class="s-title" style="color: white;">${status.index+1}、问题：${questionList.title }</p>
							<textarea rows="4" required="required" name="answer" titleId="${questionList.id }" class="answerList"></textarea>
							
						</li>
					</c:forEach>
				</ul>
				<input type="submit" id="submit" onclick="javascript:getanswer()">
		</c:forEach>
	</div>
		<jsp:include page="../foot.jsp"></jsp:include>
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	<script src="<%=path%>/omg/js/bossHr/applyState.js"></script>
</body>
</html>
