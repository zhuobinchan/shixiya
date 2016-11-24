<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dayeeResume.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.resumeIframe{
			width: 80%;
			height: 70%;
			border: none;
		}
	</style>

  </head>
  
  <body>
  
    <iframe class="resumeIframe" target="_top" src="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e2d9fc537885b56&redirect_uri=http%3a%2f%2fwww.hotjob.cn%2fwt%2fhighsun%2fmobweb%2flogin&response_type=code&scope=snsapi_base&state=listDelivery" name="if1">
    </iframe>
    
  	<script type="text/javascript">
//   		abc();
//   		function abc(){
//   			windw.open(' http://www.baidu.com','if1');
//   		}
  	</script>
  </body>
</html>
