<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
input[type="text"] {
	height: 26px !important;
	line-height: 26px !important;
	padding: 0 6px !important;
}
</style>
<script type="text/javascript">
	function openAddDlg() {
		$("#myModalLabel").html("增加视频文件");
	}
	function saveZone() {
		if ($("#title").val() == null || $("#title").val() == '') {
			$("#error").html("请输入课程名称！");
			return false;
		}
	
		
		if ($("#mode").val() == null || $("#mode").val() == ''){
			$("#error").html("请输入选择模式！");
			return false;
		}
		
		if ($("#courseSelect").val() == null || $("#courseSelect").val() == ''){
			$("#error").html("请输入选择课程！");
			return false;
		}
		if ($("#chapterSelect").val() == null || $("#chapterSelect").val() == ''){
			$("#error").html("请输入选择课程章节！");
			return false;
		}
		
		/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
		alert("保存成功！");
		$("#fm").submit();
		resetValue();
		/*  location.reload(true); */
	}
	function modifyZone(id, title, courseId,chapterId,mode,startTime,endTime) {

		$("#myModalLabel").html("修改章节");
		$("#id").val(id);
		$("#title").val(title);
		$("#mode").val(mode);
		$("#courseSelect").val(courseId);
		$("#startinfo").val(startTime);
		$("#dateinfo").val(endTime);
		getChapterByCourseId(courseId,chapterId);

	}
	function zoneDelete(id) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("adminVideo_deleteVideo.action", {
				id : id
			}, function(result) {

				alert("删除成功！");
				window.location.reload(true);

			});
		}
	}
	function resetValue() {

		$("#id").val("");
		$("#title").val("");
		$("#mode").val("");
		$("#startinfo").val("");
		$("#dateinfo").val("");
		$("#courseSelect ").get(0).options[0].selected = true;
		$("#chapterSelect ").get(0).options[0].selected = true;
		
	}
	function deleteSections() {
		var selectedSpan = $(".checked").parent().parent().next("td");
		if (selectedSpan.length == 0) {
			alert("请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedSpan.length; i++) {
			strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
		}
		var ids = strIds.join(",");
		if (confirm("您确定要删除这" + selectedSpan.length + "条数据吗？")) {
			$.post("adminVideo_deleteVideos.action", {
				ids : ids
			}, function(result) {
				if (result.success) {
					alert("数据已成功删除！");
					location.reload(true);
				} else {
					alert("数据删除失败！");
				}
			}, "json");
		} else {
			return;
		}
	}
	function getChapterByCourseId(courseId,chapterId){
		$.post("adminVideo_getChapterAjax.action", {
				courseId : courseId
			}, function(result) {
				var chapterSelect = $("#chapterSelect");
				var chapterSelectFility = $("#teacherSelect");
				chapterSelect.html("");
				chapterSelectFility.html("");
				chapterSelect.append("<option value=\"\">请选择</option>");
				chapterSelectFility.append("<option value=\"\">请选择</option>");
				for(var i=0;i<result.length;i++){
					chapterSelect.append("<option value=\""+result[i].id+"\">"+result[i].title+"</option>");
					chapterSelectFility.append("<option value=\""+result[i].id+"\">"+result[i].title+"</option>");
				}
				
				$("#chapterSelect").val(chapterId);
				
			}, "json");
	}
	
	function getTrainWeChildByTrainWEId(trainWeId){
			$.post("getAllTrainWEChildAjax_TrainWEActio", {
				trainWeId : trainWeId
			}, function(result) {
				var trainweChildSelect = $("#trainweChildSelect");
				trainweChildSelect.html("");
				trainweChildSelect.append("<option value=''>请选择</option>");
				
				for(var i=0;i<result.length;i++){
					trainweChildSelect.append("<option value='"+(result[i].videoUrl==null?"":result[i].videoUrl)+"'>"+result[i].title+"</option>");
				}
				
				$("#chapterSelect").val(chapterId);
				
			}, "json");
	}
	
	
	
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static"
				data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加视频</button>
			&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" role="button"
				class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="adminVideo_findByMap.action" method="post"
				class="form-search" style="display: inline;">
				&nbsp;视频名： <input name="title" value="${s_section.name }"
					type="text" class="input-medium search-query"
					placeholder="输入视频名称..." /> 
					
					&nbsp;所属课程名：
					<select id="courseSelect1" onchange="javascript:getChapterByCourseId(this.value)">
						<option value="">请选择</option>
						<c:forEach var="t" items="${courseList }">
							<option value="${t.id }">${t.title }</option>
						</c:forEach>
					</select>
					
					&nbsp;所属章节名：
					<select id="teacherSelect"
					class="input-medium search-query" name="chapterid">
					<option value="">请选择</option>
					
				</select> &nbsp;
				<button type="submit" class="btn btn-primary" title="Search">
					查询&nbsp;<i class="icon  icon-search"></i>
				</button>
			</form>

		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>视频列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>视频名称</th>
									<th>所属章节</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${video }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"  myids="${t.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${t.title }</td>
										<td style="text-align: center;">${t.BProductChapter.title }</td>
										<td style="text-align: center;">
											<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg"
												onclick="return modifyZone('${t.id}','${t.title}','${t.BProductChapter.BProductCourse.id }','${t.BProductChapter.id }','${t.mode}','${t.startTime}','${t.endTime}')">修改
											</button>&nbsp;&nbsp;
											<button class="btn btn-danger" type="button"
												onclick="javascript:zoneDelete(${t.id})">删除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="pagination alternate">
					<ul class="clearfix">${pageCode }
					</ul>
				</div>


			</div>
		</div>
		<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">增加章节视频</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="adminVideo_addVideo.action" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<input id="id" type="hidden" name="id">
						</tr>
						<tr>
							<td><label class="control-label" for="title">视频名：</label></td>
							<td><input id="title" type="text" name="title"
								placeholder="请输入…"></td>
						</tr>
						<tr>
							<td><label class="control-label form_starttime"
								for="startTime">开始时间：</label></td>
							<td><input class="form_endtime" id="startinfo" type="text"
								placeholder="请选择" name="startTime"></td>
						</tr>
						<tr>
							<td><label class="" datainp" for="endTime">结束时间：</label></td>
							<td><input class="form_endtime" id="dateinfo" type="text"
								placeholder="请选择" name="endTime"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="courseId">所属课程：</label>
							</td>
							<td><select id="courseSelect" onchange="javascript:getChapterByCourseId(this.value)">
									<option value="">请选择</option>
									<c:forEach var="t" items="${courseList }">
										<option value="${t.id }">${t.title }</option>
									</c:forEach>
							</select></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="teacherId">所属章节：</label>
							</td>
							<td><select id="chapterSelect" name="chapterid" >
									<option value="">请选择</option>
									
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label" for="mode">选择模式：</label>
							</td>
							<td><select id="mode" name="mode">
									<option value="0">既播放视频、又可以QA</option>
									<option value="1">只播放视、 不可以QA</option>
									<option value="2">不可以播放视频、但可以QA</option>
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label" for="video">上传视频：</label></td>
							<td><input type="file" id="logo" name="video"></td>
						</tr>
						<tr>
							<td><label class="control-label" for="trainweList">转WE课堂：</label>
							</td>
							<td>
							
							<select id="trainweSelect" onchange="getTrainWeChildByTrainWEId(this.value)">
								<option value="">请选择</option>
								<c:forEach var="t" items="${trainweList }">
										<option value="${t.id}">${t.title}</option>
								</c:forEach>
							</select>
							<select id="trainweChildSelect" name="trainweChildSelect">
									<option value="">请选择</option>
							</select>							
							
							</td>
						</tr>

					</table>

				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveZone()">保存</button>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jedate/jedate.js"></script>
<script type="text/javascript">
	jeDate({
		dateCell : "#startinfo",
		isinitVal : false,
		isTime : true, //isClear:false,
		minDate : "2014-09-19 00:00:00",
		okfun : function(val) {
			alert(val)
		}
	})
	jeDate({
		dateCell : "#dateinfo",
		isinitVal : false,
		isTime : true, //isClear:false,
		minDate : "2014-09-19 00:00:00",
		okfun : function(val) {
			alert(val)
		}
	})

	$("#jedatebox").css("z-index", "99999");
</script>
