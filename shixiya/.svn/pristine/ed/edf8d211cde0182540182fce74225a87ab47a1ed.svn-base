<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  <!--IE8兼容-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" /><!--禁止缩放-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">  <!--手机满屏变大-->
    <meta name="format-detection" content="telephone=no"> <!--去掉手机强制某些数值识别成链接-->
    <title>${cpBroadcast.name }</title>
    <link rel="stylesheet" href="/shixiya/omg/css/style.css">
</head>

<%
	request.setAttribute("vEnter", "\r\n");   
	request.setAttribute("nEnter", "<br>");
%> 
<body>
<div class="main-content">
    <div class="header">
        <img src="<%=path%>/${cpBroadcast.imgUrl }">
    </div>
    <div class="detail">
        <p class="title" style="margin-left: 0">${cpBroadcast.name }</p>
        <p class="txt3">${fn:replace(cpBroadcast.introduction, vEnter, nEnter) }</p>
    </div>
    <div class="line"></div>
    <div class="intro">
        <div class="img">
            <img src="<%=path%>/${cpBroadcast.CProductHR.headImgUrl }">
        </div>
        <p class="s-title">${cpBroadcast.CProductHR.name }</p>
        <p class="txt3">${fn:replace(cpBroadcast.CProductHR.introduction, vEnter, nEnter) }</p>
    </div>
    <input id="broadcastId" type="hidden" value="${cpBroadcast.id}">
  	  <p class="s-title" style="text-align: center">
    	开始时间：
			<fmt:parseDate value="${cpBroadcast.startTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>
   	 <p class="s-title" style="text-align: center">
    	结束时间：
			<fmt:parseDate value="${cpBroadcast.endTime}" var="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"
				pattern="yyyy-MM-dd HH:mm" />
    	</p>    
    	
     <c:if test="${isEnd == false}">	   	
    	<c:if test="${applyStatus == 'noApply'}">
			<button id="enter" status="noApply"
				onclick="window.location.href='judgeUserBaseInfo_resume?broadcastId=${cpBroadcast.id}'"
				class="blue-btn">我要报名</button>
		</c:if>		
		
    	<c:if test="${applyStatus == 'applyWithoutResume'}">
			<button id="enter" class="red-btn" status="applyWithoutResume"
					onclick="isExistResume('resumeBtn')"
				>请完善简历完成报名</button>
		</c:if>	
		
		<c:if test="${applyStatus == 'waitingApply'}">
			<button id="enter" class="green-btn" status="waitingApply"
				onclick="reloadNow(${cpBroadcast.id})">报名审核中,点击刷新</button>
		</c:if>
		<script type="text/javascript">
			function reloadNow(broadcastId){
				if(confirm("刷新查看最新状态"))
				window.location.href = "judgeApplyStatus_cp?broadcastId="+broadcastId;
			}
		</script>

		<c:if test="${applyStatus == 'sccessApply'}">
			<button id="enter" status="sccessApply"
				onclick="javascript:enterCpBroadcast('${cpBroadcast.id}')"
				class="yellow-btn">进入直播</button>
		</c:if>

		<c:if test="${applyStatus == 'failApply'}">
			<button id="enter"  status="failApply" onclick="javascript:alert('很抱歉,报名不通过')" class="gray-btn">报名失败</button>
		</c:if>
 	</c:if>   
 	
	<c:if test="${isEnd == true}">
	
		 <c:choose>
			<c:when test="${applyStatus == 'sccessApply'}">
			<button id="enter" status="sccessApply"
				onclick="javascript:enterCpBroadcast('${cpBroadcast.id}')"
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
	
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	<script src="<%=path%>/omg/js/bossHr/applyState.js"></script>

</body>
</html>
