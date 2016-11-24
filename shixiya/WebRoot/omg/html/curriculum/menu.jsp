<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
// 		System.out.println(session.getAttribute("student"));

	if (session.getAttribute("student") == null) {
//		response.sendRedirect("http://zhuobinchan.tunnel.2bdata.com/shixiya/GetUserInfoAction");
 		response.sendRedirect("http://sxy.000861.com/shixiya/GetUserInfoAction");
		return;
	}


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
    <title>实习吖</title>
    <link rel="stylesheet" href="/shixiya/omg/css/style.css">
    <link rel="stylesheet" href="/shixiya/omg/css/index.css">
    <link rel="stylesheet" href="/shixiya/omg/css/swiper.min.css">
    <script src="/shixiya/omg/js/jquery-1.11.2.js"></script>
    <style>
        body{background: #c9c9c9}
        .swiper-slide{height: 100%!important;}
    </style>
</head>
<body>
<div class="container">
    <div class="container-bg"></div>
    <div class="container-slide">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu1.png">
                    </div>
                    <div class="line-box">
                        <div class="hx2"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">OMG实习生计划</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">这里有实战性最强、方案最落地、形式最好玩的全省性大赛招募及选拔大一到大三的在校大学生，您将获得【OMG’er学堂】提供的免费暑期培训营培训机会及名企岗位实训机会。</p>
                        <a href="http://mp.weixin.qq.com/mp/homepage?__biz=MzAwMTAyMjkzOA==&hid=2&sn=4a9437e4a24630b33d675a00e93cba8f#wechat_redirect" class="menu-more">more</a>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu2.png">
                    </div>
                    <div class="line-box">
                        <div class="hx"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">实习体验馆</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">实习生们以“项目制”的方式，参与企业实际运营项目，开启“15-60天制”的岗位体验计划。以培训+实践的形式，实现线上导师的培训指导，线下项目实践的FUNNY  SHOW。</p>
                        <a href="showAllCourses_bp.action" class="menu-more">more</a>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu3.png">
                    </div>
                    <div class="line-box">
                        <div class="hx"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">Boss直播间</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">以“10min宣讲会”为中心，旨在集中、快速、有针对性地了解有校招需求的目标企业招聘资讯，开辟通往面试offer的快速通道。</p>
                        <a href="showAllBroadcast_cp" class="menu-more">more</a>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu4.png">
                    </div>
                    <div class="line-box">
                        <div class="hx"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">HR帮帮堂</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">以栏目的形式，听取各行业深具影响力的企业HR给你面对面分享，从学生简历的编写、求职技巧等直击企业需求，成为职场老司机。</p>
                        <a href="showHrCommentList_stuhr.action" class="menu-more">more</a>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu5.png">
                    </div>
                    <div class="line-box">
                        <div class="hx"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">培训WE课堂</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">WE课堂专属定向为90后新生代开发职场认知所需要的技能类、职场素质通用类等方向的培训线上课程，让你全免费预知职场未来。</p>
                        <a href="<%=path%>/omg/html/curriculum/fourth/fourth.jsp" class="menu-more">more</a>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu6.png">
                    </div>
                    <div class="line-box">
                        <div class="hx"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">堂外优选汇</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">平台提供各类岗位职场上所需要的学习成长资源，定向给大学生提出自我学习的建议，“授人以鱼，不如授人以渔”，这是精心为你们挑选的学习平台。</p>
                          <a href="<%=path%>/omg/html/curriculum/fifth/fifth.jsp" class="menu-more">more</a> 
<!--                      	 <a href="javascript:void(0)" onclick="javascript:alert('服务未开通，敬请期待');" class="menu-more">more</a> -->
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="menu1">
                        <img src="/shixiya/omg/images/menu/menu7.png">
                    </div>
                    <div class="line-box">
                        <div class="hx3"></div>
                        <div class="sx"></div>
                        <div class="rec"></div>
                    </div>
                    <div class="introduction">
                        <p class="menu-title">实习生招聘资讯</p>
                        <div class="line-dot" style="width: 140%;margin-left: -20%;margin-bottom: 10px"></div>
                        <p class="menu-detail">校招岗位、实习岗位、兼职需求等广东地区招聘资讯在这里一网打尽，我们相信：实践，是自我能力提升的启蒙老师。</p>
                        <a href="<%=path%>/omg/html/curriculum/sixth/sixth.jsp" class="menu-more">more</a> 
<!--                         <a href="javascript:void(0)" onclick="javascript:alert('服务未开通，敬请期待');" class="menu-more">more</a> -->
                    </div>
                </div>
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
</div>
    <jsp:include page="foot.jsp"></jsp:include>

<script>
    $(function(){
        $(window).load(function () {
            
        });
    })
</script>
<script src="/shixiya/omg/js/swiper.min.js"></script>
<!-- swiper 设置 -->
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationType: 'progress',
        slidesPerView : 2,
        centeredSlides : true,
        paginationClickable: true,
        speed:800,     //切换速度
        loop:true,
        onTransitionStart: function(swiper){
            var index=parseInt($(".swiper-slide-active").attr("data-swiper-slide-index"));
            $(".container").css("background-position",16.666*index+"% 0%");
        }
    });
</script>
</body>
</html>