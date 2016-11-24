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
    <title>HR帮帮堂</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/tuwen.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/rotate.css">
</head>
<body>
    <div class="swiper" style="position: relative;height: 32vh;">
        <div class="up-intro">
            <h3>HR帮帮堂</h3>
            <p>以栏目的形式，听取各行业深具影响力的企业HR给你面对面分享，从学生简历的编写、求职技巧等直击企业需求，成为职场老司机。</p>
        </div>
        <div style="z-index: 100;width: 100%;height: 100%">
            <img src="<%=path%>/omg/images/tuwen/tuwen-intro.png" width="100%" height="100%">
        </div>
    </div>
    <div class="swiper-flow">
        <div class="swiper-container swiper-container2">
            <div class="swiper-wrapper">
            
               <c:forEach items="${hrCommentLists }" var="hrs"> 
                <div class="swiper-slide">
	                    <img src="<%=path%>/${hrs.imageUrl}" width="100%" height="100%" alt="${hrs.title }"
	                    	onclick="window.location.href='enterStuHrChat_chat?roomId=sh${hrs.id }' ">
	                    <div class="cover-txt">
	                        <h3>${hrs.title}</h3>
	                        <p>${hrs.introduction}</p>
	                    </div>
                </div>
                </c:forEach>
                
            </div>
            <div class="swiper-pagination"></div>

        </div>
    </div>

		<jsp:include page="../foot.jsp"></jsp:include>
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
        <script>
            $(function(){
                var tr=$(".tb div");
                tr.click(function(){
                    $(this).addClass("visited2").siblings("div").removeClass("visited2");
                });
            })
    </script>
   
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
    <script>
            $(function(){
                $(window).load(function () {
                    var Imgs=["msgh","jlbx","zcsy","zydy","zcsy","zydy"];
                    var Txts=["面试干货","简历编写","职场素养","职涯答疑","性格匹配","职业规划"];
                    var swiperBtn=$(".swiper-pagination-bullet");
                    for(var i=0;i<swiperBtn.length;i++){
                        $(swiperBtn[i]).css("background-image","url('/shixiya/omg/images/tuwen/"+Imgs[i]+".png')").append("<p>"+Txts[i]+"</p>");
                    }
                });
            })
    </script>
    <script src="<%=path%>/omg/js/swiper.min.js"></script>
    <!-- swiper 设置 -->
    <script>
        var swiper2 = new Swiper('.swiper-container2', {
            pagination: '.swiper-pagination',
            effect: 'coverflow',
            grabCursor: true,
            centeredSlides: true,
            slidesPerView: 'auto',
            coverflow: {
                rotate: 50,
                stretch: 0,
                depth: 100,
                modifier: 1,
                slideShadows : true
            },
            loop:true,
            onTransitionStart: function(swiper){
                var active=$(".swiper-pagination-bullet-active");
                if(active.css("bottom")=="0px"){
                    $(".swiper-pagination").css("transform","rotate(0deg)");
                }
                else if(active.css("left")=="0px"){
                    $(".swiper-pagination").css("transform","rotate(300deg)");
                }
                else if(parseInt(active.css("left"))<0){
                    $(".swiper-pagination").css("transform","rotate(240deg)");
                }
                else if(active.css("top")=="0px"){
                    $(".swiper-pagination").css("transform","rotate(180deg)");
                }
                else if(parseInt(active.css("right"))<0){
                    $(".swiper-pagination").css("transform","rotate(120deg)");
                }
                else if(active.css("right")=="0px"){
                    $(".swiper-pagination").css("transform","rotate(60deg)");
                }
            }
        });

    </script>
</body>
</html>