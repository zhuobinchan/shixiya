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
<title>${bpCourse.title}</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<style type="text/css">
body,html{
    background-color: white; !important;
}
</style>

</head>

<%
	request.setAttribute("vEnter", "\r\n");   
	request.setAttribute("nEnter", "<br>");
%> 
<body>
	<div class="main-content">
		<div class="header">
			<img src="<%=path%>/${bpCourse.imageUrl}">
		</div>
		<div class="detail">
			<p class="title" style="margin-left: 0">${bpCourse.title}</p>
			<p class="txt3">${fn:replace(bpCourse.introduction, vEnter, nEnter)}</p>
		</div>
		
		<div class="line"></div>
		<div class="intro">
			<div class="img">
				<img src="<%=path%>/${bpCourse.BProductTeacher.headImgUrl}">
			</div>
			<p class="s-title">${bpCourse.BProductTeacher.name}</p>
			<p class="txt3">${fn:replace(bpCourse.BProductTeacher.introduction, vEnter, nEnter)}</p>
			<div class="RichText">${bpCourse.richtext}</div>
		</div>
		
		<p class="s-title" style="text-align: center">
			开始时间：
			<fmt:parseDate value="${bpCourse.startTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>
		<p class="s-title" style="text-align: center">
			结束时间：
			<fmt:parseDate value="${bpCourse.endTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>		
    	<span id="show-all2">章节</span>

		<ul id="lesson" style="display: none;">
			<c:forEach var="chapter" items="${bpCourse.BProductChapters}" varStatus="chapterStatus">
				<li>
					<details> 
						<summary>${chapterStatus.index+1}、${chapter.title }</summary>
						<c:forEach
						var="videos" items="${chapter.BProductVideos}"  varStatus="videoStatus">
						<c:if test="${applyStatus == 'sccessApply'}">
							<a href="javascript:void(0)"
								onclick="javascript:enterBpCourse('${bpCourse.id}','${videos.id}')">
						</c:if>
						<p>${chapterStatus.index+1}.${videoStatus.index+1}、${videos.title }</p>
						<c:if test="${applyStatus == 'sccessApply'}">
							</a>
						</c:if>
					</c:forEach>
					</details>
				</li>
			</c:forEach>
		</ul>
		
		<input type="hidden" id="courseId" value="${bpCourse.id}">
		
		
    <c:if test="${isEnd == false}">		
		<c:if test="${applyStatus == 'noApply'}">
			<button id="enter" status="noApply"
				onclick="window.location.href='judgeUserBaseInfo_resume?courseId=${bpCourse.id}'"
				 class="blue-btn" >我要报名</button>
		</c:if>
		
		<c:if test="${applyStatus == 'applyWithoutResume'}">
			<button id="enter" status="applyWithoutResume"
				onclick="isExistResume('resumeBtn')"
				class="red-btn">请完善简历完成报名</button>
		</c:if>	

		<c:if test="${applyStatus == 'waitingApply'}">
			<button id="enter" class="green-btn" status="waitingApply"
				onclick="reloadNow(${bpCourse.id})">报名审核中,点击刷新</button>
		</c:if>
		<script type="text/javascript">
			function reloadNow(bpCourseId){
				if(confirm("刷新查看最新状态"));
				window.location.href = "judgeApplyStatus_bp?courseId="+bpCourseId;
			}
		</script>
		<c:if test="${applyStatus == 'sccessApply'}">
			<button id="enter" status="sccessApply" onclick="javascript:alert('请点击章节进入视频。')"
				 class="yellow-btn">开始上课</button>
		</c:if>

		<c:if test="${applyStatus == 'failApply'}">
			<button id="enter" status="failApply" onclick="javascript:alert('很抱歉,报名不通过。')"
			class="gray-btn">报名失败</button>
		</c:if>

    </c:if>	

	<c:if test="${isEnd == true}">
	
		 <c:choose>
			<c:when test="${applyStatus == 'sccessApply'}">
				<button id="enter" status="sccessApply" onclick="javascript:alert('请点击章节进入视频。')"
					 class="yellow-btn">观看回放</button>
			</c:when>		
			<c:otherwise>
				<button id="enter" status="overdue" onclick="javascript:alert('报名时间结束。')"
					 class="red-btn">报名结束</button>
			</c:otherwise> 
		 </c:choose>
	
	</c:if>	


		
	</div>
		<jsp:include page="../foot.jsp"></jsp:include>
</body>
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
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path%>/omg/js/bossHr/applyState.js"></script>
</html>