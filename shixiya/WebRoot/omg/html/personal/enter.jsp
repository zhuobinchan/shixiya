<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  <!--IE8兼容-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" /><!--禁止缩放-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">  <!--手机满屏变大-->
    <meta name="format-detection" content="telephone=no"> <!--去掉手机强制某些数值识别成链接-->
    <title>我已报名</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/person.css">
</head>
<body>
<div class="container">
    <div class="per-message">
        <div class="head-img">
            <img src="${session.student.headUrl }">
        </div>
        <p>${session.student.nickname }</p>
        <p>印币：${session.student.integral }</p>
    </div>
    <div class="per-content">
    	<i class="xtb bm"></i>
        <p style="padding-left: 10px">我已报名</p>
			<ul class="tuwen-model A" id="ulAs"
				style="width: 100%;margin-top: 15px;padding: 0">
				<!-- 学生B组已报名课程start -->
				<c:forEach items="${bpStuApplys }" var="bpStuApply">
					<li><a
						href="judgeApplyStatus_bp?courseId=${bpStuApply.id.BProductCourse.id }">

							<div class="img-show">
								<img
									src="<%=path%>/${bpStuApply.id.BProductCourse.imageUrl }">
							</div>
							<div class="txt-show">
								<p class="s-title">${bpStuApply.id.BProductCourse.title }</p>
								<p class="txt2">${bpStuApply.id.BProductCourse.introduction }</p>
								<p class="txt2-footer">
									<span>报名状态： 
										<c:if test="${bpStuApply.states==0||bpStuApply.states==3}">报名审核中</c:if>
										<c:if test="${bpStuApply.states==1}">报名成功</c:if> 
										<c:if test="${bpStuApply.states==2}">报名失败</c:if>
									</span>
								</p>
							</div>

					</a></li>
				</c:forEach>
				<!-- 学生B组已报名课程end -->
				
				<!-- 学生C组报名直播start -->
				<c:forEach items="${cpStuApplys }" var="cpStuApply">
					<li><a
						href="judgeApplyStatus_cp?broadcastId=${cpStuApply.id.CProductBroadcast.id }">

							<div class="img-show">
								<img
									src="<%=path%>/${cpStuApply.id.CProductBroadcast.imgUrl }">
							</div>
							<div class="txt-show">
								<p class="s-title">${cpStuApply.id.CProductBroadcast.name }</p>
								<p class="txt2">${cpStuApply.id.CProductBroadcast.introduction }</p>
								<p class="txt2-footer">
									<span>报名状态： 
										<c:if test="${cpStuApply.status==0||cpStuApply.status==3}">报名审核中</c:if>
										<c:if test="${cpStuApply.status==1}">报名成功</c:if> 
										<c:if test="${cpStuApply.status==2}">报名失败</c:if>
									</span>
								</p>
							</div>

					</a></li>
				</c:forEach>	
				<!-- 学生C组报名直播end -->			
			</ul>

    </div>
</div>
	    <jsp:include page="../curriculum/foot.jsp"></jsp:include>
</body>
<script src="<%=path%>/omg/js/bossHr/applyList.js"></script>
</html>


<!-- 
									<span> <fmt:parseDate
											value="${cpStuApply.id.CProductBroadcast.publicTime}" var="date"
											pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
											value="${date}" type="both" pattern="yyyy/MM/dd" /></span> </span> <span>参与人数：${cpStuApply.id.CProductBroadcast.participation }</span>

 -->