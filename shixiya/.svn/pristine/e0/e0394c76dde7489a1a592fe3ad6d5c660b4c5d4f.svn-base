<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>编辑个人信息</title>
    
    <style>
        *{
            padding: 0;
            margin: 0;
            font-family: 微软雅黑;
        }
        body,html{
            width: 100%;
            height: 100%;
            background: #f5f5f5;
        }
        .information{
            padding: 10px 20px;
        }
        .information>p{
            margin-bottom: 20px;
            border-bottom: 1px solid #e6e6e6;
            line-height: 50px;
            font-size: 16px;
        }
        form>div{
            margin: 10px 0;
            padding: 0 5px;
            border-radius: 6px;
            line-height: 40px;
            color: #b2b2b2;
            border: 1px solid #e6e6e6;
            background-color: #ffffff;
        }
        form>div>input{
            border: none;
            outline:none;
            font-size: 15px;
        }
        .submit{
            display: block;
            margin-top: 20px;
            width: 100%;
            border-radius: 6px;
            border: 1px solid #4b6d93;
            font-size: 16px;
            line-height: 40px;
            color: #ffffff;
            background: #4f7baa;
        }
        .loading{position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 199;text-align: center;font-size: 16px;color: #ffffff;background-color: rgba(0,0,0,.5)}
		.loading>span{position: fixed;top: 43%;left: 0;width: 100%;}
    </style>
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
</head>
<body>
    <div class="information">
    	<p style="color: red;">请认真填写该信息和下一步的简历</p>
    	<br>
        <p>请填写基本信息：</p>
        
        <form method="get" action="writeStudentInfo_writeStudentInfo.action">
            <div>姓名：<input type="text"  name="name" value="${name }" required ></div>
            <div>电话：<input type="tel" name="telephone" value="${telephone }" required></div>
            <div>邮箱：<input type="email" name="email" value="${email }" required></div>
            <input type="hidden" name="broadcastId" value="${broadcastId }">
            <input type="hidden" name="courseId" value="${courseId }">
            <input type="hidden" name="recruitId" value="${recruitId }">
            <input type="hidden" name="companyId" value="${companyId }">
            <input type="hidden" id="fifthId" name="fifthId" value="${param.fifthId }">
            <input type="hidden" id="isFromPersonResume" name="isFromPersonResume" value="${param.isFromPersonResume }">
            <input class="submit" type="submit" value="提&nbsp;&nbsp;交" ${param.isFromPersonResume=="from_fifth"?"":'onclick="loading()"' } >
            <p style="color: red;">${message }</p>
        </form>
        
    </div>
</body>
<script type="text/javascript">
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
</script>
</html>