<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/unicorn.main.css" />
<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jeDate/jedate.js"></script> 
<script type="text/javascript">
$(function(){
	var sectionPage="section.jsp";var topicPage="topic.jsp";var userPage="user.jsp";var zonePage="zone.jsp";var addTeacher="addTeacher.jsp";var addCourse="addCourse.jsp";var addChapter="addchapter.jsp"
	var curPage='${mainPage}';var addAdv="addAdvertisement";var addAdmin="addAdmin.jsp";var addHR="addHR.jsp";var student="student.jsp";
	if(sectionPage.indexOf(curPage)>=0&&curPage!=""){
		$("#sectionLi").addClass("active");
	} else if(topicPage.indexOf(curPage)>=0&&curPage!=""){
		$("#topicLi").addClass("active");
	} else if(userPage.indexOf(curPage)>=0&&curPage!=""){
		$("#userLi").addClass("active");
	} else if(zonePage.indexOf(curPage)>=0&&curPage!=""){
		$("#zoneLi").addClass("active");
	}else if(addTeacher.indexOf(curPage)>=0&&curPage!=""){
		$("#addTeacherLi").addClass("active");
	}else if(addCourse.indexOf(curPage)>=0&&curPage!=""){
		$("#addCourseLi").addClass("active");
	}else if(addChapter.indexOf(curPage)>=0&&curPage!=""){
		$("#addChapterLi").addClass("active");
	}else if(addAdv.indexOf(curPage)>=0&&curPage!=""){
		$("#addAdvLi").addClass("active");
	}else if(addAdmin.indexOf(curPage)>=0&&curPage!=""){
		$("#addAdminLi").addClass("active");
	}else if(addHR.indexOf(curPage)>=0&&curPage!=""){
		$("#addHRLi").addClass("active");
	}else if(student.indexOf(curPage)>=0&&curPage!=""){
		$("#addStudentLi").addClass("active");
	}
	
});

</script>
</head>
<%-- <%  if(session.getAttribute("currentUser")==null)
{response.sendRedirect("login.jsp"); return; } 
%> --%>
<body>
	<div id="header">
		<h1 style="margin-left: 0px;padding-left: 0px;">
			<a href="#">实习吖</a>
		</h1>
		<!-- <h2 style="padding: 0px; margin-top: 10px; margin-bottom: 0px;">
			<a href="#"><font color="#cccccc">Java1234论坛</font></a>
		</h2>
		<h3 style="margin: 0px 0px 0px 40px;">
			<a href="#"><font color="#cccccc">后台管理</font></a>
		</h3> -->
	</div>

	<div id="sidebar">
		<ul>
					
			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>实习生招聘管理</span> <span class="label">1</span></a>
				<ul>
					
					<li id="addCourseLi"><a href="#"><i
						class="icon icon-home"></i>发布实习生招聘</a></li>
					
					
				</ul>
				</li>

		</ul>

	</div>

	<div id="style-switcher">
		<i class="icon-arrow-left icon-white"></i> <span>颜色:</span> <a
			href="#grey"
			style="background-color: #555555; border-color: #aaaaaa;"></a> <a
			href="#blue" style="background-color: #2D2F57;"></a> <a href="#red"
			style="background-color: #673232;"></a>
	</div>

	<div id="content">
		<div id="content-header">
			<h1>后台管理</h1>
			<!-- <div class="btn-group">
				<a class="btn btn-large tip-bottom" title="Manage Files"><i
					class="icon-file"></i></a> <a class="btn btn-large tip-bottom"
					title="Manage Users"><i class="icon-user"></i></a> <a
					class="btn btn-large tip-bottom" title="Manage Comments"><i
					class="icon-comment"></i><span class="label label-important">5</span></a>
				<a class="btn btn-large tip-bottom" title="Manage Orders"><i
					class="icon-shopping-cart"></i></a>
			</div> -->
		</div>
		<div id="breadcrumb">
			
			</a> <a href="#" class="current">${crumb1 }</a>
		</div>
		<jsp:include page="${mainPage }"></jsp:include>
		<div class="row-fluid">
			<div id="footer" class="span12">
				2016 &copy;&nbsp;&nbsp;&nbsp;&nbsp; 实习吖-合作企业端&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script>
	<!-- <script src="js/select2.min.js"></script> -->
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/unicorn.js"></script>
	<script src="js/unicorn.tables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
</body>
</html>