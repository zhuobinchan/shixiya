<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	if (session.getAttribute("trainer") == null) {
		response.sendRedirect("trainerLogin.jsp");
		return;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<title>培训WE课堂列表</title>
<link rel="stylesheet" href="<%=path%>/OMG_login/css/style.css">
<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
<script src="<%=path%>/omg/js/b-js.js"></script>

    <style>
        .tb>div{width: 32%;}
        .main{margin-top: 1px}
        #overChild{
        	border:5px;
        	position:fixed;
        	background-color: white;
        	box-shadow: 2px 4px 6px #000;
        	display:none;
        	
			left:10%; 
			bottom: 165px;
			width:80%; /* 宽为400,那么margin-top为-200px  */
			height:350px; /* 高为200那么margin-left为-100px;  */
			border:1px solid rgb(0, 184, 255); 
			line-height:200px; 
			font-size:16px; 
			text-align:left; 
			overflow:auto;
			z-index:101; /* 浮动在最上层  */
			
        }
        #pew
        {
            width: 100%;
            height: 10%;
            text-align: center;
            line-height: 30px;
            color: red;
            background: rgba(24, 203, 227, 0.19);
            cursor: default;
            z-index: 105
        }
        .container
        {
        	width: 100%;
            height: 90%;
			overflow:auto; 
        }
    </style>
</head>
<body>

	<div class="nav-name">
		<h1>培训WE课堂讲师端</h1>
	</div>
	
    <div id="overChild">

        <div class="container">
           <ul class="tuwen-lis" style="border: none" id="overChild-ul">
   
  			</ul>
        </div>
        
       <div id="pew">
            close
        </div>		
        
    </div>
    
	<ul class="tuwen-model" style="margin-top: 15px">
		<c:forEach items="${trainWeProducts}" var="trainWe">
			<li><a
				href="javascript:void(0)" onclick="showChild(${trainWe.id})">
					<div class="img-show">
						<img src="<%=path%>/${trainWe.imgUrl}">
					</div>
					<div class="txt-show">
						<p class="s-title">${trainWe.title }</p>
						<p class="txt2">${trainWe.introduction }</p>
						<p class="txt2-footer">
							<span style="color: #2596c2;font-size: 8px" > <fmt:parseDate value="${trainWe.publicTime}"
									var="date" pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
									value="${date}" type="both" pattern="yyyy/MM/dd" />
							</span>
						</p>
					</div>
			</a></li>
		</c:forEach>
	</ul>
	<a href="javascript:void(0)" onclick="javascript:alert('服务未开通，敬请期待');" ><i class="wsq"></i></a>
</body>
<script>

	function showChild(trainWeId){
		$.post("getAllTrainWEChildAjax_TrainWEAction.action",{
			trainWeId:trainWeId
		}, function(result) {
			setTrainWEChildInfo(result);
			
			if(result.length==null || result.length==0){
				var tuwen_lis = $("#overChild-ul");
				tuwen_lis.append("<img src='/shixiya/omg/images/nodata.jpg' style='height:100%;width:100%'/>");
			}
			
			/*点击div外的隐藏掉该div*/
			$("body").unbind("click").click(function (e) {
				if (!$(e.target).closest("#overChild").length) {
					$("#overChild").hide();
				}
			});
			$("#pew").unbind("click").click(function(){
				$("#overChild").hide();
			})
			
		}, "json");
		
		
		
		function setTrainWEChildInfo(trainWEProduct) {
			$("#overChild").css("display","block");
			var tuwen_lis = $("#overChild-ul");
			tuwen_lis.html("");

			for (var i = 0; i < trainWEProduct.length; i++) {
				
				var listViewContent = trainWEProduct[i].listViewContent;
				if(listViewContent==null) listViewContent="";
				
//				var listViewContent_removeEnter = listViewContent.replace(/[\r\n]/g,"</br>");
//				.append('<li><a href="getTrainWEById_TrainWEActio?trainWeId='
//						+ trainWEProduct[i].id
				tuwen_lis
						.append('<li><a href="enterWeCourseChat_chat.action?roomId=Th1994th'+trainWEProduct[i].id+'&role=th'
						+ trainWEProduct[i].id+'">'
						+ '<div class="img-show">'
								+ ' <img src="/shixiya/'
								+ trainWEProduct[i].imgUrl
								+ '">'
								+ '</div>'
								+ ' <div class="txt-show" style="text-align:left">'
								+ '     	<p class="s-title">'
								+ trainWEProduct[i].title
								+ '</p>'
								+ '   	<p class="txt2">'
								+ listViewContent
								+ '</p>'
								+ '   	<p class="txt2-footer">'
								+ '      	<i class="time-icon"></i><span>'
								+ judgeApplyStatus(
										trainWEProduct[i].startTime,
										trainWEProduct[i].endTime)
								+ '</span>'
								+ '      	<i class="num-icon"></i><span>'
								+ trainWEProduct[i].visitnum
								+ '</span>'
								+ '   	</p>'
								+ ' </div>' + '</a></li>');

			}

		}
	}
	
	/* 判断课程进行状态 */
	function judgeApplyStatus(startTime, endTime) {
		var now = new Date();

		var start = new Date(startTime.replace("-", "/")
				.replace("-", "/"));
		var end = new Date(endTime.replace("-", "/").replace(
				"-", "/"));

		if (now < start) {
			return "培训没有开始";
		}
		if (start < now && now < end) {
			return "培训正在进行";
		}
		if (end < now) {
			return "培训已经结束";
		}
	}

</script>
</html>
