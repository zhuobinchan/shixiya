<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>广州招聘资讯</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/swiper.min.css">
</head>
<body>
    <div class="tb">
        <div class="visited2">互联网/IT</div> <div>传媒设计</div> <div>工程技术</div> <div>市场商务</div>
        <div>销售客服</div> <div>销售客服</div> <div>财经法务</div> <div>教育/咨询</div>
        <div>生产/供应链</div> <div>外语外贸</div> <div>餐饮</div> <div>其他</div>
    </div>
    <div class="main">
        <div class="recruit">
            <div class="s-line"></div>
            <p>招聘信息</p>
            <ul>
                <li>
                    <a href="recruit.jsp">
                        <p class="s-title">软件工程师 <span class="day-num"><b>200</b>/天</span></p>
                        <p class="txt2-2">
                            <span>天河区</span>
                            <span>男女不限</span>
                            <span>java开发</span>
                        </p>
                        <p class="txt3 tr">2016-09-04 07:00</p>
                    </a>
                </li>
                <li>
                    <p class="s-title">软件工程师 <span class="day-num"><b>200</b>/天</span></p>
                    <p class="txt2-2">
                        <span>天河区</span>
                        <span>男女不限</span>
                        <span>java开发</span>
                    </p>
                    <p class="txt3 tr">2016-09-04 07:00</p>
                </li>
            </ul>
        </div>
    </div>
		<jsp:include page="foot.jsp"></jsp:include>
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
    <script>
        $(function(){
            var tr=$(".tb div");
            tr.click(function(){
                tr.removeClass("visited2");
                $(this).addClass("visited2");
            });
        })
    </script>
</body>
</html>