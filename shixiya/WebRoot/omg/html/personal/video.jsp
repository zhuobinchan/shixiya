<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  <!--IE8兼容-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" /><!--禁止缩放-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">  <!--手机满屏变大-->
    <meta name="format-detection" content="telephone=no"> <!--去掉手机强制某些数值识别成链接-->
    <title>讲座</title>
    <link rel="stylesheet" href="../../css/chat.css"/>
    <script src="../../js/jquery-1.11.2.js"></script>
</head>
<body>
<div class="speech-title">
    微积分专题讲座
</div>
<div class="video">
    <video width="100%" height="100%">
        <source src="" type=""/>
    </video>
</div>
<div class="chat">
    <div class="ly" style="display: none">
        <img src="../../images/ly.png">
    </div>
    <p id="alert"></p>
    <!--右边聊天信息-->
    <div class="chat-content">
        <img src="../../images/tuwen/tuwen-1.png" class="head1">
        <div class="content">
            <p class="ni-name">实习吖某某讲师<i class="hr"></i></p>
            <div class="word3">聊天内容聊天内容聊天内容聊天内容聊天内容聊天内容聊天内容</div>
            <i class="r-img13"></i>
        </div>
    </div>
    <!--左边聊天信息-->
    <div class="chat-content" id="chat-l">
        <img src="../../images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">12345<i class="hr"></i></p>
            <div class="word4">聊天内容</div>
            <i class="r-img14"></i>
        </div>
    </div>

    <div class="chat-content">
        <img src="../../images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">1234</p>
            <div class="send-img send-img-left"><img src="../../images/tuwen/tuwen-1.png"></div>
            <i class="r-img14"></i>
        </div>
    </div>

    <div class="chat-content">
        <img src="../../images/head-img.png" class="head1">
        <div class="content">
            <p class="ni-name">实习吖实习生</p>
            <div class="send-img send-img-right"><img src=""></div>
            <i class="r-img13"></i>
        </div>
    </div>

    <div class="chat-content">
        <img src="../../images/head-img.png" class="head1">
        <div class="content">
            <p class="ni-name">1234</p>
            <div class="yy"></div>
        </div>
    </div>

    <div class="chat-content">
        <img src="../../images/head-img.png" class="head2">
        <div class="content2">
            <p class="ni-name2">1234</p>
            <div class="yy2"><i class="weidu"></i></div>
        </div>
    </div>
</div>
<div class="send">
    <div class="send1">
        <div class="send-word">
            <input type="text" class="input-word"/>
            <!--<i class="ft"></i>-->
            <div class="btn-container">
                发送
                <button class="btn-send hide btn-std">发送</button>
            </div>
        </div>
        <button class="send-sound" style="display: none">点击开始录音</button>
    </div>
    <div class="send2">
        <div class="sound-btn"><div class="voice"></div></div>
        <div><div class="play"></div></div>
        <div><div class="quiet"></div></div>
        <div><div class="photo"></div></div>
    </div>
</div>
<script src="../../js/video/video.js"></script>
</body>
</html>
