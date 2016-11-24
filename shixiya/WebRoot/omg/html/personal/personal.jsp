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
    <title>个人中心</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/person.css">
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
    <style>
        html,body{width: 100%;height: 100%;overflow: hidden}
    </style>
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
            <div>
                <i class="jl xtb"></i>
                <a href="judgeResumeIsExist_CPHrCheckStudentResumeAction.action?isFromPersonResume=true&studentId=${session.student.id }" class="btn-bd" onclick="loading()">我的简历<i class="jt"></i></a>
            </div>
            <div onclick="isNull()" style="display: none">
                <i class="sc xtb"></i>
                <a>我收藏的<i class="jt"></i></a>
            </div>
        </div>
        <div class="per-content">
            <div>
                <i class="bm xtb"></i>
                <a href="appliedAll_student.action" class="btn-bd">我已报名<i class="jt"></i></a>
            </div>
            <div>
                <i class="cj xtb"></i>
                <a href="participant_student.action" class="btn-bd">我参加过<i class="jt"></i></a>
            </div>
            <div onclick="isNull()" style="display: none">
                <i class="fb xtb"></i>
                <a>我发表过<i class="jt"></i></a>
            </div>
        </div>
        <div class="per-content" style="display: none">
            <div onclick="isNull()">
                <i class="jf xtb"></i>
                <a>我的积分<i class="jt"></i></a>
            </div>
        </div>
    </div>
	    <jsp:include page="../curriculum/foot.jsp"></jsp:include>
    <script>
        function isNull(){
            alert("抱歉，暂时还没有此功能 T_T");
        }
        
        var loading=function(){
			$("body").append('<div class="loading"><span>正在为您查找简历中,loading...</span></div>');
		//    var btnAttend=$(".blue-btn");
		//    if(btnAttend){
		//        $.each(btnAttend,function(){
		//            $(this).click(function(){
		//                $("body").append('<div class="loading"><span>正在为您查找简历中,loading...</span></div>');
		////                 $(".loading").click(function(){
		////                     $(this).remove();
		////                 });
		//            });
		//        });
		//    }
		};
//loading();
    </script>
</body>
</html>