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
    <title>答疑</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/style.css">
    <link rel="stylesheet" href="<%=path%>/omg/css/person.css">
</head>
<body>
    <div class="container">
        <h4>问题标题：关于某课程的问题答疑</h4>
        <form>
            <ul class="answer-list">
                <li>
                    <p class="s-title">1、问题一：xxxxxxxx</p>
                    <textarea rows="4" required="required"></textarea>
                </li>
                <li>
                    <p class="s-title">2、问题二：xxxxxxxx</p>
                    <textarea rows="4" required="required"></textarea>
                </li>
                <li>
                    <p class="s-title">3、问题三：xxxxxxxx</p>
                    <textarea rows="4" required="required"></textarea>
                </li>
                <li>
                    <p class="s-title">4、问题四：xxxxxxxx</p>
                    <textarea rows="4" required="required"></textarea>
                </li>
                <li>
                    <p class="s-title">5、问题五：xxxxxxxx</p>
                    <textarea rows="4" required="required"></textarea>
                </li>
            </ul>
            <input type="submit" id="submit">
        </form>

    </div>
        <jsp:include page="../curriculum/foot.jsp"></jsp:include>
</body>
</html>
