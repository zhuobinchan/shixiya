<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("hr") == null) {
		response.sendRedirect("hrLogin.jsp");
		return;
	}
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
<title>查看答案</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/person.css">
   <style>
body,html{width: 100%;height: 100%;background-color: #e8e8e8}
.tb{position: fixed;z-index: 102;top: 0;margin: 0;padding: 8px 0;border-bottom: 1px solid #cdcdcd;background-color: #ffffff;}
.tb>div{height: 24px;}
.tb>div>input,.tb>div>select,.submit{padding-left: 2px;width: 96%!important;height: 100%!important;border-radius: 2px!important;border: 1px solid #DCB57E!important;font-size: 12px!important}
.submit{background-color: #dcae62!important;line-height: 24px!important;}
form{margin-top: 50px}
table{z-index: 100;width: 100%;margin-top: 15px;margin-bottom: 45px}
table tr{width: 100%;display: block;background-color: snow;box-shadow: 2px 2px 4px #9f9f9f;margin-bottom: 20px}
table td{padding:5px}
table td:first-child{width: 10%}
table td:last-child{width: 90%;position: relative}
.img-show{width: 50px;height: 50px;border-radius: 25px;overflow: hidden;background-color: #c3c4c4;margin: 0 auto;float: none}
.txt-show{float: left;width: 71%;height: 65px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-box-orient: vertical;}
.s-title{color: #3879D9;font-weight: bold}
.xs-title{font-size: 0.7em;margin:7px 0 4px 0;line-height: 1.3em}
input[type="checkbox"]{width: 20px;height: 20px;}
.txt-n{line-height: 1.3em;font-size: 0.7em; letter-spacing: 0.08em; margin-top: 1px; color: #808080;}
.btns{position: fixed;bottom: 0;height: 40px;width: 100%;padding-top: 10px;border-top: 1px solid #cdcdcd;background-color: #ffffff}
input[type="submit"],input[type="button"]{width:32%;background-color: #3879D9;font-size: 13px;line-height: 30px;border-radius: 18px;border: none;color: #ffffff}
.nickname{font-size: 0.7em;}
.img{width: 26%;height: auto;word-wrap:break-word;float: right;margin-right: 1%;text-align: center;border-radius:0;}
.dn{position: absolute;bottom: -16px;right: 25%;height: 20px;width: 20px}
    </style>



</head>
<body>
	<form action="saveOrUpdateEmploy_CPHrEmployStudent" method="post">
		<table cellspacing="0">

			<c:forEach items="${hrCheckAnswers }" var="hrCheckAnswer">
				<tr>
					<td><input type="checkbox" name="choose"
						value="${hrCheckAnswer.studentId }"></td>
					<td>
						<div class="txt-show">
							<p class="s-title">问题：${hrCheckAnswer.questionTitle }</p>
							<c:forEach items="${hrCheckAnswer.answerMap }" var="map">
								<c:forEach items="${map.key }" var="mapkey" begin="0" end="0"
									varStatus="status">
									<p class="xs-title">${status.index+1 }、${mapkey.value }</p>
								</c:forEach>
								<p class="txt-n">${map.value }</p>
							</c:forEach>
						</div>
						<div class="img">
							<div class="img-show">
								<a
									href="getResumeByStudentId_CPHrCheckStudentResumeAction.action?studentId=${hrCheckAnswer.studentId }"><img
									src="${hrCheckAnswer.headImg }"></a>
							</div>
							<p class="nickname">${hrCheckAnswer.name }</p>
						</div> <img src="../../images/tuwen/dn.png" class="dn">
					</td>
				</tr>
			</c:forEach>
		</table>
		<input id="broadcastId" type="hidden" name="broadcastId"
			value="${broadcastId }"> 
		<div class="btns">
            <input type="button" value="一键全选" onclick="chooseAll()">
            <input type="button" value="一键反选" onclick="chooseNull()">
            <input type="submit" id="submits" style="background-color: #d96a38" value="提&nbsp;&nbsp;交">
        </div>	
	</form>
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
	 <script>
        $(window).load(function(){
            showCtn();
        });
        function showCtn(){
            var dn=$(".dn");
            var txt=$(".txt-show");
            for(var i=0;i<dn.length;i++){
                $(dn[i]).click(function(){
                    var j=i;
                    return function(){
                        if($(txt[j]).css("height")=="65px"){
                            $(txt[j]).css("height","auto");
                        }else{
                            $(txt[j]).css("height","65px");}}
                }())
            }
        }
        function chooseAll(){
            $("input[name='choose']").prop("checked","true");
        }
        function chooseNull(){
            $("input[name='choose']").each(function(){
                if($(this).prop("checked"))
                {
                    $(this).removeAttr("checked");
                }
                else
                {
                    $(this).prop("checked","true");
                }
            })
        }
    </script>
</body>
</html>