<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title style="text-align:center">${ weCourseChild.title}</title>
<link rel="stylesheet" href="<%=path%>/omg/css/chat.css" />
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

</head>
<body>

	<c:if test="${weCourseChild.mode == 0 || weCourseChild.mode == 1 }">
		<div class="video">
			<video id="video" width="100%" height="100%" ${ weCourseChild.status==1?'controls':''}
			poster="${weCourseChild.status==1?'/shixiya/omg/images/video/ready.png':'/shixiya/omg/images/video/wait.png' }"
			webkit-playsinline> 
				
				<c:if test="${(!empty session.hr.name) || (!empty session.trainer.name) }">
					<source id="videoSource" src="${ weCourseChild.videoUrl}" type="video/mp4" /> </video>
					<source id="videoSource" src="${ weCourseChild.videoUrl}" type="video/ogg" /> </video>
				</c:if>
				<c:if test="${empty session.hr.name && empty session.trainer.name }">
					<source id="videoSource" src="${ weCourseChild.status==1?weCourseChild.videoUrl:''}" type="video/mp4" /> </video>
					<source id="videoSource" src="${ weCourseChild.status==1?weCourseChild.videoUrl:''}" type="video/ogg" /> </video>
				</c:if>	
				
			</video>

		</div>
	</c:if>
	
	<c:if test="${weCourseChild.mode ==2 }">
	<style>
        .chatroom{
            height: 100%;
        }
	</style>
	</c:if>
	
<div class="chatroom">	
	<div class="chat">
		<div class="ly" style="display: none">
			<img src="<%=path%>/omg/images/ly.png">
		</div>
		<p id="alert"></p>
		<div class="history" id="getMore" value="1" onclick="getComment()">
		    <i class="history-icon"></i>
		    查看更多信息
		</div>
		
		<div class="chat-container">
		</div>
		
	</div>

	<c:if
		test="${(empty session.student) && (!empty session.hr.name) || (!empty session.trainer.name) }">
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
	        <div><div class="play" id="startVideoBtn"></div></div>
	        <div><div class="${weCourseChild.chatStatus==1?'quiet':'noise' }" id="stopTalkBtn" status="${weCourseChild.chatStatus }" ></div></div>
	        <div><div class="photo" onclick="chooseImage()"></div></div>
	    </div>
		<div class="ly" style="display: none">
	        <img src="<%=path %>/omg/images/ly.png">
	        <p>还可以说<span class="timing">59</span>秒</p>
	    </div>
	</div>
	</c:if>



	<c:if test="${weCourseChild.mode == 0 || weCourseChild.mode == 2}">
		<c:if
			test="${(!empty session.student) && (empty session.hr.name) && (empty session.bpTeacher.name) && (empty session.trainer.name) }">
			<div class="send" ${ weCourseChild.chatStatus==1?"":"style='display:none'" }>
			    <div class="send1">
			        <div class="send-word">
			            <input type="text" class="input-word"/>
			            <!--<i class="ft"></i>-->
			            <div class="btn-container">
			                发送
			                <button class="btn-send hide btn-std">发送</button>
			            </div>
			        </div>
			    </div>
			</div>
		</c:if>
	</c:if>
</div>

	<input id="roomId" type="text" value="${roomId }" style="display: none" />
	<input id="role" type="text" value="${role }" style="display: none" />
	<input id="stuName" type="hidden"
		value="${session.student == null?session.trainer.name:session.student.nickname }"/>
	<input id="headUrl" type="hidden"
		value="${session.student == null?'/shixiya/'.concat(session.trainer.headImgUrl):session.student.headUrl }"/>
	<input id="roleId" type="hidden"
		value="${session.student == null?'th1994'.concat(session.trainer.id):'stu'.concat(session.student.id) }"/>		

</body>
<script type="text/javascript" src="<%=path%>/omg/js/until.js"></script>
<script type="text/javascript" src="<%=path%>/omg/js/weixin.js"></script>
<script type="text/javascript" src="<%=path%>/omg/js/websocket.js"></script>
<script type="text/javascript" src="<%=path%>/omg/js/simple.js"></script>
<script src="<%=path%>/omg/js/video/video.js"></script>
<script type="text/javascript">
		getComment();
		
</script>
</html>
