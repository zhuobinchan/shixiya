<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
// 		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
// 					+"appid=wx3e2d9fc537885b56&"
// 					+"redirect_uri=http%3a%2f%2fwww.hotjob.cn%2fwt%2fhighsun%2fmobweb%2flogin&"
// 					+"response_type=code&scope=snsapi_base&state=listDelivery");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'resumeIframe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		abc();
		function abc(){
			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?"
					+"appid=wx3e2d9fc537885b56&"
					+"redirect_uri=http%3a%2f%2fwww.hotjob.cn%2fwt%2fhighsun%2fmobweb%2flogin&"
					+"response_type=code&scope=snsapi_base&state=listDelivery";
		}
		
	</script>
  </head>
  
  <body>
    
  </body>
</html>
