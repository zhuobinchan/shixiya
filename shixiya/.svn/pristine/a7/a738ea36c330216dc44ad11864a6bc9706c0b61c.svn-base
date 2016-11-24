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
<title>职场情报站</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path%>/omg/js/b-js.js"></script>
</head>
<body>
	<div class="main">
		<div class="swiper">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${cpAdvs }" var="cpAdv">
						<img class="swiper-slide" onclick="window.location.href='judgeApplyStatus_cp.action?&broadcastId=${cpAdv.targetUrl }'"
							src="<%=path%>/${cpAdv.imageUrl}">
					</c:forEach>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</div>
		<div class="nav-name">
			<ul>
				<li class="visited boss" id="bossLi">boss直播间</li>
				<li class="hr" id="hrLi">HR周点评</li>
			</ul>
		</div>
		<div class="second-web boss-1">
			<table class="tb">
				<c:forEach items="${lables }" var="lable" varStatus="status">


					<!-- <td class="tb_td" value="${lable.lableId }">${lable.lableName }</td> -->
					<c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">
						<tr>
					</c:if>
					<td class="tb_td" value="${lable.lableId }" lableName="${lable.lableName }">${lable.lableName }
						<!--url为obj的一个属性-->
					</td>
					<c:if test="${status.count % 4 eq 0 || status.count eq 4}">
						</tr>
					</c:if>

				</c:forEach>


			</table>
			<div class="main" style="margin: 0 1% 100px 1%;">
				<ul class="tuwen-model" style="margin-top: 15px">
					<c:forEach items="${cpBroadcasts}" var="cpBroadcast">


						<li><a
							href="judgeApplyStatus_cp.action?broadcastId=${cpBroadcast.id }">
								<div class="img-show">
									<img src="<%=path%>/${cpBroadcast.imgUrl}">
								</div>
								<div class="txt-show">
									<p class="s-title">${cpBroadcast.name }</p>
									<p class="txt2">${cpBroadcast.introduction }</p>
									<p class="txt2-footer">
										<span startTime="${cpBroadcast.startTime }" endTime="${cpBroadcast.endTime }">
										
										
									<script type="text/javascript">
									var now=new Date();
								
									var startTime = $(".txt2-footer:last").find("span:first-child").attr("startTime");
									var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
									var endTime =$(".txt2-footer:last").find("span:first-child").attr("endTime");
									var end = new Date(endTime.replace("-", "/").replace("-", "/"));  
									
									if(now<start){
										$(".txt2-footer:last").find("span:first-child").append("直播间正在报名");
									}
									if (start<now && now<end) {
										$(".txt2-footer:last").find("span:first-child").append("直播间正在进行");
									}
									if(end<now){
										$(".txt2-footer:last").find("span:first-child").append("直播间已经结束");
									}
								</script>
										
										
										
										 <%-- <fmt:parseDate value="${cpBroadcast.publicTime}"
												var="date" pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
												value="${date}" type="both" pattern="yyyy/MM/dd" /> --%>
										</span> <span>参与人数 : ${cpBroadcast.participation }</span>
									</p>
								</div>
						</a></li>
					</c:forEach>

				</ul>
			</div>
		</div>

	</div>
		<jsp:include page="../foot.jsp"></jsp:include>

	<script src="<%=path%>/omg/js/swiper.min.js"></script>
	<!-- swiper 设置 -->
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			paginationClickable : true,
			autoplayDisableOnInteraction : false,//操作后可以继续轮播
			loop : true,
			speed : 800, //切换速度
			autoplay : 3000
		//自动切换时间
		});
	</script>
</body>
<script src="<%=path%>/omg/js/bossHr/bossHr.js"></script>
</html>