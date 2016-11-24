<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>


	<h3 id="menu-voice">音频接口</h3>
	<span class="desc">开始录音接口</span>
	<button class="btn btn_primary" id="startRecord">startRecord</button>
	<span class="desc">停止录音接口</span>
	<button class="btn btn_primary" id="stopRecord">stopRecord</button>
	<span class="desc">播放语音接口</span>
	<button class="btn btn_primary" id="playVoice">playVoice</button>
	<span class="desc">暂停播放接口</span>
	<button class="btn btn_primary" id="pauseVoice">pauseVoice</button>
	<span class="desc">停止播放接口</span>
	<button class="btn btn_primary" id="stopVoice">stopVoice</button>
	<span class="desc">上传语音接口</span>
	<button class="btn btn_primary" id="uploadVoice">uploadVoice</button>
	<span class="desc">下载语音接口</span>
	<button class="btn btn_primary" id="downloadVoice">downloadVoice</button>
	
	
	<div id="record" ></div>
	<button class="playVoiceBtn" onclick="playRecord('123')" id="playVoice">playVoice</button>
	<script type="text/javascript">

	</script>
	
	<script type="text/javascript" src="<%=path %>/js/websocket.js"></script>
	<script type="text/javascript" src="<%=path %>/js/simple.js"></script>
	<script type="text/javascript" src="<%=path %>/js/weixin.js"></script>

</body>
</html>
