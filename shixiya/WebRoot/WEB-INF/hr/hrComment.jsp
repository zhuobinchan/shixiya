<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<style type="text/css">
		.hrImg{
			margin:0;
			border-radius:20px;
			box-shadow: 0 0 3px #000;
			width:100%;
			height: 100%;

		}
		.imgDiv{
			float: left;
			margin:0;
			width: 40%;
			height: 15%;
		}
		.hrTitle{
			font-size: 30pt;
			font-weight: bold;
		}
		.hrContent{
			font-size: 20pt;
			padding-top: 10px;
			width:97%;
		}
	</style>
  </head>
  
  <body>
  	<div>
    	<img alt="" src="<%=path %>/images/1.jpg" width="100%" height="20%" style="border-radius:10px">
    </div>
    <div style="margin:20px 0 0 0;">
    	<div class="imgDiv"><img class="hrImg" alt="" src="<%=path %>/images/2.jpg"></div>
    	<div>
    		<div class="hrTitle">Hr周点评第一期</div>
    		<div class="hrContent">Hr周点评内容，我是内容啊啊哈哈哈哈哈!Hr周点评内容，我是内容啊啊哈哈哈哈哈!Hr周点评内容，我是内容啊啊哈哈哈哈哈!</div>
    	</div>
    </div>
    
    <s:debug></s:debug>
    <s:iterator value="hrCommentLists" id="abc">
    	<s:property value="#abc.introduction"/></br>
    </s:iterator>
    
  </body>
</html>
