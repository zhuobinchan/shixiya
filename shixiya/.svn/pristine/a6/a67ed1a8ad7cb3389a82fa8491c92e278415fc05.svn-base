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
    <title>堂外优选汇</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
    <style>
        .tb>div{width: 32%;}
    </style>
</head>
<body>
<div class="tb">
    <div class="visited2">专业技能</div> <div>通识教育</div> <div>行业分享</div>
</div>
<div class="main">
    <div class="swiper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <img class="swiper-slide" src="<%=path%>/omg/images/tuwen/tuwen-intro.png">
                <img class="swiper-slide" src="<%=path%>/omg/images/tuwen/tuwen-intro.png">
                <img class="swiper-slide" src="<%=path%>/omg/images/tuwen/tuwen-intro.png">
                <img class="swiper-slide" src="<%=path%>/omg/images/tuwen/tuwen-intro.png">
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <ul class="tuwen-lis" style="border: none">
        <li><a href="">
            <div class="img-show">
                <img src="<%=path%>/omg/images/tuwen/tuwen-1.png">
            </div>
            <div class="txt-show">
                <p class="s-title">微积分</p>
                <p class="txt2">讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容</p>
                <p class="txt2-footer">
                    <i class="time-icon"></i><span>2017.07.07  20:30</span>
                    <i class="num-icon"></i><span>12</span>
                </p>
            </div>
        </a></li>
        <li><a href="">
            <div class="img-show"><img src="<%=path%>/omg/images/tuwen/tuwen-1.png"></div>
            <div class="txt-show">
                <p class="s-title">微积分</p>
                <p class="txt2">讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容讲座内容</p>
                <p class="txt2-footer">
                    <i class="time-icon"></i><span>2017.07.07  20:30</span>
                    <i class="num-icon"></i><span>12</span>
                </p>
            </div>
        </a></li>
    </ul>
</div>
		<jsp:include page="foot.jsp"></jsp:include>

<script src="<%=path%>/omg/js/swiper.min.js"></script>
<!-- swiper 设置 -->
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        autoplayDisableOnInteraction: false,//操作后可以继续轮播
        loop:true,
        speed:800,      //切换速度
        autoplay:3000   //自动切换时间
    });
</script>
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script>
    $(function(){
        var tr=$(".tb div");
        tr.click(function(){
            $(this).addClass("visited2").siblings("div").removeClass("visited2");
        });
    })
</script>
</body>
</html>