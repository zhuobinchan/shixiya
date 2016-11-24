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
			<span>微社区管理</span> <span class="label">4</span></a>
				<ul>
					<li id="zoneLi"><a href="Zone_list.action"><i
						class="icon icon-home"></i> <span>大板块管理</span></a></li>
					<li id="sectionLi"><a href="Section_list.action"><i
						class="icon icon-home"></i> <span>小板块管理</span></a></li>
					<li id="topicLi"><a href="Topic_listAdmin.action"><i
						class="icon icon-home"></i> <span>帖子管理</span></a></li>
					<!-- <li><a href="#"><i class="icon icon-home"></i> <span>回复管理</span></a></li> -->
					<li id="userLi"><a href="User_list.action"><i
						class="icon icon-home"></i> <span>用户管理</span></a></li>
				</ul>
			</li>		
			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>实习体验馆管理</span> <span class="label">4</span></a>
				<ul>
					
					<li id="addCourseLi"><a href="${pageContext.request.contextPath}/admin/adminCourse_showCourse"><i
						class="icon icon-home"></i>课程管理</a></li>
					<li id="addChapterLi"><a href="${pageContext.request.contextPath}/admin/adminChapter_showChapter"><i
						class="icon icon-home"></i>章节管理</a></li>
					<li id="addVideoLi"><a href="${pageContext.request.contextPath}/admin/adminVideo_showVideo"><i
						class="icon icon-home"></i>视频管理</a></li>
					<li id="addAdvLi"><a href="${pageContext.request.contextPath}/admin/adminAdv_showAdv"><i
						class="icon icon-home"></i>广告管理</a></li>
					<li id="addAdvLi"><a href="${pageContext.request.contextPath}/admin/bComment_showComment"><i
						class="icon icon-home"></i>聊天记录</a></li>	
				</ul>
				</li>

			<li class="submenu"><a href="#"><i
							class="icon icon-th-list"></i> <span>BOSS直播间管理</span><span class="label">6</span></a>
						<ul>
							<li><a href="getAllCPBroadcast_AdminCPBroadcastAction.action"><i
						class="icon icon-home"></i>直播间</a></li>
							<li><a href="getAllQuestion_AdminCProductQuestionAction"><i
						class="icon icon-home"></i>问卷管理</a></li>
							<li><a href="getAllQuestionLists_AdminCProductQuestionListAction.action"><i
						class="icon icon-home"></i>问题列表</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/adminAnswer_showAnswer.action"><i
						class="icon icon-home"></i>答案管理</a></li>
							<li><a href="getAllComments_AdminCProductCommentAction.action"><i
						class="icon icon-home"></i>聊天记录</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/adminCAdv_showAdv.action"><i
						class="icon icon-home"></i>广告管理</a></li>
						</ul>
						
			</li>
			<li class="submenu"><a href="#"><i
							class="icon icon-th-list"></i> <span>HR帮帮堂</span><span class="label">2</span> </a>
						<ul>
							<li><a href="hrAdminIndex_hr.action"><i
						class="icon icon-home"></i>HR帮帮堂</a></li>
							<li><a href="getAllHrComment_hrComment.action"><i
						class="icon icon-home"></i>聊天记录</a></li>
						</ul>
			</li>
			<li class="submenu"><a href="#"><i
							class="icon icon-th-list"></i> <span>培训we课堂</span><span class="label">2</span> </a>
						<ul>
							<li><a href="getAllTrainWEProduct_AdminTrainWEAction.action"><i
						class="icon icon-home"></i>培训we课堂</a></li>
							<li><a href="getAllTrainWEProductChild_AdminTrainWEChildAction.action"><i
						class="icon icon-home"></i>章节管理</a></li>						
							<li><a href="getAllTrainWEComment_AdminTrainWEComment.action"><i
						class="icon icon-home"></i>聊天记录</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/showAdv_AdminTrainWEAdvAction.action"><i
						class="icon icon-home"></i>广告管理</a></li>
						</ul>
			</li>
			<li class="submenu"><a href="#"><i
							class="icon icon-th-list"></i> <span>堂外优选汇</span><span class="label">4</span> </a>
						<ul>
							<li><a href="pSelect_showPrefereneceSelect.action"><i
						class="icon icon-home"></i>堂外优选汇</a></li>
						<li><a href="psEntered_showpreferenceSelectEntered.action"><i
						class="icon icon-home"></i>优选汇报名管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/showAdv_AdminPreferenceSelectAdvAction.action"><i
						class="icon icon-home"></i>广告管理</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/pImgs_showImgs.action"><i
						class="icon icon-home"></i>图片管理</a></li>
							
						</ul>
			</li>
			<li class="submenu"><a href="#"><i
							class="icon icon-th-list"></i> <span>合作企业管理</span><span class="label">1</span> </a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/admin/ribusiness_showRIBussiness.action"><i
						class="icon icon-home"></i>合作企业管理</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/reInfo_showRecruitmentInfo.action"><i
						class="icon icon-home"></i>	企业岗位发布管理</a></li>	
						</ul>
			</li>
			
		
			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>用户管理</span> <span class="label">4</span></a>
				<ul>
					<li id="addTeacherLi"><a href="${pageContext.request.contextPath}/admin/adminTeacher_showAddTeacher"><i
						class="icon icon-home"></i>讲师管理</a></li>
					<li id="addAdminLi"><a href="${pageContext.request.contextPath}/admin/admin_showAdmin"><i
						class="icon icon-home"></i>管理员管理</a></li>
					<li id="addHRLi"><a href="${pageContext.request.contextPath}/admin/adminHR_showHR"><i
						class="icon icon-home"></i>HR管理</a></li>
					<li id="addStudentLi"><a href="${pageContext.request.contextPath}/admin/adminStudent_showStudent"><i
						class="icon icon-home"></i>学生管理</a></li>
					<li id="addStudentLi"><a href="${pageContext.request.contextPath}/admin/getAllTrainWETeacher_AdminTrainWETeacherAction"><i
						class="icon icon-home"></i>培训we课堂老师管理</a></li>
					
				</ul>
			</li>
			
			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>标签管理</span> <span class="label">1</span></a>
				<ul>	
					<li id="addLable"><a href="${pageContext.request.contextPath}/admin/adminLable_showLable"><i
								class="icon icon-home"></i>管理能力匹配标签</a></li>
				</ul>
			</li>
			
			
			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>简历管理</span> <span class="label">1</span></a>
				<ul>	
					<li id="addLable"><a href="${pageContext.request.contextPath}/admin/adminResume_showResume"><i
								class="icon icon-home"></i>简历管理</a></li>
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
				2016 &copy;&nbsp;&nbsp;&nbsp;&nbsp; 实习吖&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="#">实习吖前端连接</a>
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