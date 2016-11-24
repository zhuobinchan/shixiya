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
	if (session.getAttribute("bpTeacher") == null) {
		response.sendRedirect("guiderLogin.jsp");
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
<title>讲座介绍</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
</head>
<body>
	<div class="main-content">
		<div class="header">
			<img src="<%=path%>/${bpCourse.imageUrl}">
		</div>
		<div class="detail">
			<p class="title" style="margin-left: 0">${bpCourse.title}</p>
			<p class="txt3">${bpCourse.introduction}</p>
		</div>
		<div class="intro">
			<div class="img">
				<img src="<%=path%>/${bpCourse.BProductTeacher.headImgUrl}">
			</div>
			<p class="s-title">${bpCourse.BProductTeacher.name}</p>
			<p class="txt3">${bpCourse.BProductTeacher.introduction}</p>
		</div>
		<p class="title" style="text-align: center">

			开始时间：
			<fmt:parseDate value="${bpCourse.startTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
		<p class="title" style="text-align: center">
			开始时间：2017年7月7日 7:00<span id="show-all2">章节</span>
		</p>
		<ul id="lesson" style="display: none;">
			<c:forEach var="chapter" items="${bpCourse.BProductChapters}">
				<li><details> <summary>${chapter.title }</summary> <c:forEach
						var="videos" items="${chapter.BProductVideos}">
						<a href="enterBpChat_chat?roomId=Js1994bp${videos.id }&role=js"><p>${videos.title }</p></a>
					</c:forEach> </details></li>
			</c:forEach>

		</ul>

	</div>


	<script>
		document.getElementById("show-all2").onclick = function() {
			var lis = document.getElementById("lesson");
			if (lis.style.display == "none") {
				lis.style.display = "block";
			} else {
				lis.style.display = "none";
			}
		}
	</script>
</body>
</html>