<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

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
    <meta name="keywords" content="OMG、实习吖、海印招聘">
    <title>Boss直播间</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/tuwen.css">
    <style>
        .swiper1,.swiper1>.swiper-container{height: 90px;}
        .main{margin-top: 1px}
    </style>
</head>
<body>
   <%--  <div class="swiper swiper1">
        <div class="swiper-container swiper-container1">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="tb2">
                    	<c:forEach items="${lables }" var="lable" varStatus="status">
                        <div class="visited2" value="${lable.lableId }" onclick="javascript:getLableFilterBylableName('${lable.lableName }')">${lable.lableName }</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
<!--             <div class="swiper-pagination"></div> -->
        </div>
    </div> --%>
    
    <div class="swiper swiper2">
        <div class="swiper-container swiper-container2">
            <div class="swiper-wrapper">
				<c:forEach items="${cpAdvs }" var="cpAdv">
					<img class="swiper-slide" onclick="window.location.href='judgeApplyStatus_cp.action?&broadcastId=${cpAdv.targetUrl }'"
						src="<%=path%>/${cpAdv.imageUrl}">
				</c:forEach>            
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
    
    <div class="main">
   	 <ul class="tuwen-lis" >
   	 </ul>
   	 <div style="text-align: center;width: 100%" onclick="javascript:refleshMessage()">
			<img src="<%=path%>/omg/images/getMore.gif" style="height: 70px;width:70px;"></div>
    </div>
		<jsp:include page="../foot.jsp"></jsp:include>
    <script src="<%=path %>/omg/js/jquery-1.11.2.js"></script>
    <script>
        $(function(){
            var tr=$(".tb2 div");
            tr.click(function(){
                tr.removeClass("visited2");
                $(this).addClass("visited2");
            });
        })
    </script>

    <script src="<%=path %>/omg/js/swiper.min.js"></script>
    <!-- swiper 设置 -->
    <script>
        var swiper1 = new Swiper('.swiper-container1', {
            pagination: '.swiper-pagination',
            loop:true,
            speed:800      //切换速度
        });
        var swiper2 = new Swiper('.swiper-container2', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            autoplayDisableOnInteraction: false,//操作后可以继续轮播
            loop:true,
            speed:800,      //切换速度
            autoplay:3000   //自动切换时间
        });
    </script>
</body>
<script src="<%=path%>/omg/js/bossHr/bossHr.js"></script>
<script type="text/javascript">
	getBroadcastByAjax();
</script>
</html>