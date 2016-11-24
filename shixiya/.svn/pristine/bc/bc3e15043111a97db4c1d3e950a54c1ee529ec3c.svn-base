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
		<h1>讲师后台管理系统</h1>
	</div>

	<ul class="tuwen-model" style="margin-top: 15px">
		<c:forEach items="${bpCourses}" var="course">
			<li><a
				href="getCourseById_BPTeacherAction?courseId=${course.id }">
					<div class="img-show">
						<img src="<%=path%>/${course.imageUrl}">
					</div>
					<div class="txt-show">
						<p class="s-title">${course.title }</p>
						<p class="txt2">${course.introduction }</p>
						<p class="txt2-footer">
							<span><a href="findBpStuApply_CheckApplyAction?courseId=${course.id }" style="color: #2596c2;font-size: 8px" >审核管理</a></span>
							<span style="color: #2596c2;font-size: 8px" > <fmt:parseDate value="${course.publicTime}"
									var="date" pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
									value="${date}" type="both" pattern="yyyy/MM/dd" />
							</span> <span style="color: #2596c2;font-size: 8px" >参与人数:${course.participation }</span>
						</p>
					</div>
			</a></li>
		</c:forEach>
	</ul>
	<a href="javascript:void(0)" onclick="javascript:alert('服务未开通，敬请期待');" ><i class="wsq"></i></a>
</body>
</html>
