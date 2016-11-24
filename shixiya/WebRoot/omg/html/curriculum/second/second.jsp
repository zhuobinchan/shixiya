<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<meta name="keywords" content="OMG、实习吖、海印招聘">
<title>实习体验馆</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path%>/omg/js/b-js.js"></script>
<script src="<%=path%>/omg/js/secondAjax/showAjax.js"></script>

</head>

<body>
	<div class="main" style="margin-top: 0">

		<select class="select" id="TimeQuantumFilter"
			onchange="javascript:getCourseByTimeQuantumFilter(this.value)">
			<option value="3650" selected>项目时长</option>
			<option value="1">1个月内</option>
			<option value="90">3个月内</option>
			<option value="180">半年内</option>
			<option value="365">1年内</option>
			<option value="3650">超过1年</option>
		</select> <select class="select" id="LableFilter" onchange="javascript:getCourseBylableFilter(this.value)">
			<option value="" selected>项目类别</option>
			<c:forEach items="${lables }" var="lable">
				<option value="${lable.lableName }">${lable.lableName }</option>
			</c:forEach>
		</select>

		<div class="swiper">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${bpAdvertisement }" var="bpAdv">
						<img class="swiper-slide"
							 onclick="window.location.href='judgeApplyStatus_bp?courseId=${bpAdv.targetUrl }'"
								src="<%=path%>/${bpAdv.imageUrl}">
					</c:forEach>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</div>

		<!-- 改到这里 -->
		<ul class="tuwen-container" id="tuwen-container">

		</ul>
		
		<div style="text-align: center;width: 100%" onclick="javascript:refleshMessage()">
			<img src="<%=path%>/omg/images/getMore.gif" style="height: 70px;width:70px;"></div>
	</div>
	
	<jsp:include page="../foot.jsp"></jsp:include>

	<script src="<%=path%>/omg/js/swiper.min.js"></script>
	<!-- swiper 设置 -->
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			paginationClickable : true,
			autoplayDisableOnInteraction : false, //操作后可以继续轮播
			loop : true,
			speed : 800, //切换速度
			autoplay : 3000
		//自动切换时间
		});
	</script>
</body>

</html>