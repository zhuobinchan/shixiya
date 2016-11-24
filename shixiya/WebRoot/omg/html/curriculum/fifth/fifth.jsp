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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  <!--IE8兼容-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" /><!--禁止缩放-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">  <!--手机满屏变大-->
    <meta name="format-detection" content="telephone=no"> <!--去掉手机强制某些数值识别成链接-->
    <meta name="keywords" content="OMG、实习吖、海印招聘">
    <title>堂外优选汇</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
    <style>
        .tb>div{width: 32%;}
        .main{margin: 0px 0 100px 0}
    </style>
</head>
<body>
<!-- <div class="tb"> -->
<!--     <div class="visited2">专业技能</div> <div>通识教育</div> <div>行业分享</div> -->
<!-- </div> -->
<div class="main">
    <div class="swiper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <ul class="tuwen-lis" style="border: none">

    </ul>
    
     <div style="text-align: center;width: 100%" onclick="javascript:refleshMessage()">
			<img src="<%=path%>/omg/images/getMore.gif" style="height: 70px;width:70px;"></div>
</div>
		<jsp:include page="../foot.jsp"></jsp:include>

<script src="<%=path%>/omg/js/swiper.min.js"></script>
<!-- swiper 设置 -->
<script>

</script>
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path %>/omg/js/swiper.min.js"></script>
<script src="<%=path%>/omg/js/preferenceSelectAjax/showPreferenceSelect.js"></script>
<script>
    $(function(){
        var tr=$(".tb div");
        tr.click(function(){
            $(this).addClass("visited2").siblings("div").removeClass("visited2");
        });
    })
</script>

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
</body>
</html>