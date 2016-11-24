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
<title>${preferenceSelect.workName}</title>
	<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
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
	    <div class="swiper">
	        <div class="swiper-container">
	            <div class="swiper-wrapper">
	            	<c:forEach var="img" items="${preferenceSelectImgs}">
	            		<img class="swiper-slide" src="<%=path %>/${img.url}" />
	            	</c:forEach>
	            </div>
	            <div class="swiper-pagination"></div>
	        </div>
	    </div>
		
		<div class="intro">
			<div class="img">
				<img src="<%=path%>/${preferenceSelect.workLogo}">
			</div>
			<p class="s-title">${preferenceSelect.workName}</p>
			<c:if test="${applyStatus == 'sccessApply'}">
			<p class="txt3">企业联系邮箱：<b style="color: #38f">${preferenceSelect.workEmail}</b></p>
			</c:if>
			
			<p class="txt3">${fn:replace(preferenceSelect.workIntroduction, vEnter, nEnter)}</p>
			<div class="RichText">${preferenceSelect.richtext}</div>
		</div>
    	
		<c:if test="${applyStatus == 'noApply'}">
			<button id="enter" status="noApply"
				onclick="window.location.href='applyPreferenceSelect_PreferenceSelectAction?id=${preferenceSelect.id}'"
				class="blue-btn">我要报名</button>
		</c:if>		
		

		<c:if test="${applyStatus == 'sccessApply'}">
			<button id="enter" status="sccessApply"
				onclick="javascript:alert('你已报名，请留意短信')"
				class="yellow-btn">已报名</button>
		</c:if>

	</div>
		<jsp:include page="../foot.jsp"></jsp:include>

	
</body>
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path %>/omg/js/swiper.min.js"></script>
    <!-- swiper 设置 -->
    <script>
//         var swiper1 = new Swiper('.swiper-container', {
//             pagination: '.swiper-pagination',
//             loop:true,
//             speed:800      //切换速度
//         });
        var swiper2 = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            autoplayDisableOnInteraction: false,//操作后可以继续轮播
            loop:true,
            speed:800,      //切换速度
            autoplay:3000   //自动切换时间
        });
    </script>
</html>