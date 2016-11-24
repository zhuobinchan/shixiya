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
    <title>HR周点评</title>
    <link rel="stylesheet" href="<%=path%>/omg/css/chat.css"/>
    <script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
</head>
<body>
<div class="chat">
    <div class="ly" style="display: none">
        <img src="<%=path%>/omg/images/ly.png">
    </div>
    <p id="alert"></p>
    <!--左边聊天信息-->
    <div class="chat-content">
        <img src="<%=path%>/omg/images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">1234</p>
            <div class="word2">聊天内容</div>
        </div>
    </div>
    <div class="chat-content">
        <img src="<%=path%>/omg/images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">1234</p>
            <div class="word2">聊天内容</div>
        </div>
    </div>
    <div class="chat-content">
        <img src="<%=path%>/omg/images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">1234</p>
            <div class="word2">聊天内容</div>
        </div>
    </div>
</div>
<div class="send">
    <img src="<%=path%>/omg/images/sound.png">
    <div class="send-word">
        <input type="text" class="input-word"/>
        <button class="btn-send">发送</button>
    </div>
    <button class="send-sound" style="display: none">点击开始录音</button>
</div>
<script src="<%=path%>/omg/js/video/video.js"></script>
</body>
</html>