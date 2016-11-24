<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'testGetUserInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

<!-- 	<a style="" -->
<!-- 		href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2f5016e1682b8d9f&redirect_uri=http%3a%2f%2fsxy.000861.com%2fshixiya%2fGetUserInfoAction&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"> -->
<!-- 		<h1 style="font-size: 180">请先登录！</h1> -->
<!-- 	</a>  -->
	
	 <a style=""
		href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc7eee0d7cb088852&redirect_uri=http%3a%2f%2fzhuobinchan.tunnel.2bdata.com%2fshixiya%2fGetUserInfoAction&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect">
		<h1 style="font-size: 180">请先登录！</h1>
	</a>

</body>
</html>
