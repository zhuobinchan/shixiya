<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../admin/css/bootstrap-select.css">
<script src="../admin/js/bootstrap-select.min.js"></script>
<script src="../admin/js/i18n/defaults-*.min.js"></script>
<style>
	input[type="text"]{
		height: 26px!important;
		line-height: 26px!important;
		padding: 0 6px!important;
	}
</style>
<script type="text/javascript">
function openAddDlg(){
	$("#myModalLabel").html("增加课程");
	
	resetValue();
	

}
 function saveZone(){
     
     if ($("#title").val() == null || $("#title").val() == '') {
			$("#error").html("请输入课程名称！");
			return false;
		}
	
		
		if ($("#teacherSelect1").val() == null || $("#teacherSelect1").val() == ''){
			$("#error").html("请输入选择导师！");
			return false;
		}
		
     
     var str = $("#editor").html();
 	str = str.replace(/&lt;/g,"<");
 	str = str.replace(/&gt;/g,">");
    
	$("#editorInput").val(str);
	
	var v =  $(".filter-option").text();
	$("#lablename").attr("value",v);
	
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 function modifyZone(id,title,startTime,endTime,introduction,name,participation,lname,visitnum,detailsURL,stick,productLable){
 	var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
	 $("#myModalLabel").html("修改课程");
	 $("#id").val(id);
	 $("#title").val(title);
	 $("#startinfo").val(startTime);
     $("#dateinfo").val(endTime);
     $("#introduction").val(introduction_removeEnter);
 	$("#visitnum").val(visitnum);
     $("#participation").val(participation);
     $("#stick").val(stick);
      var count = $("#teacherSelect1 option").length;


      for(var i=0;i<count;i++) 
 	  {
 		if($("#teacherSelect1 ").get(0).options[i].text == name) 
    	 { 
         	$("#teacherSelect1 ").get(0).options[i].selected = true; 
        	break; 
       	 }  
 	 }
	   $(".filter-option").text(lname);
	  $("#detailsURL").val(detailsURL);
	  $("#productLable").val(productLable);
 }
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("adminCourse_deleteCourse.action",{id:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
					
				}
			);
	}
}
 function resetValue(){
	
		$("#id").val("");
		$("#title").val("");
		$("#startTime").val("");
		$("#startinfo").val("");
		$("#dateinfo").val("");
		$("#endTime").val("");
		$("#introduction").val("");
		$("#visitnum").val("");
		$("#participation").val("");
		$("#teacherSelect1 ").get(0).options[0].selected = true;
		$(".filter-option").text("");
		$("#detailsURL").val("");
		$("#productLable").val("");
		$("#stick").val(0);
		$("#editor").html("");
		$("#editorInput").val("");

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
			$.post("adminCourse_deleteCourses.action", {
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
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="openAddDlg()">添加课程</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="adminCourse_findByMap.action" method="post" class="form-search" style="display: inline;">
	          <br> &nbsp;课程名：
			  <input name="title" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入课程名称..."/>
			  &nbsp;讲师名：
			  <select id="teacherSelect" class="input-medium search-query" name="teacherId">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${teacherList }">
										<option value="${t.id }" >${t.name }</option>
									</c:forEach>
			  </select>
			  &nbsp;开始时间：
			  <input class="form_endtime" id="dateinfo1" type="text" placeholder="请选择" name="startTime" >
			 &nbsp;结束时间：
			  <input class="form_endtime" id="dateinfo2" type="text" placeholder="请选择" name="endTime" >
			  &nbsp;
			  <button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button>
			</form>
	
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>课程列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>课程名称</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>主讲老师</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${course }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.id }">${(page-1)*6+idx.index+1 }</td>
										<td style="text-align: center;">${t.title }</td>
										<td style="text-align: center;">${t.startTime }</td>
										<td style="text-align: center;">${t.endTime }</td>
										<td style="text-align: center;">${t.BProductTeacher.name }</td>
										<td style="text-align: center;">
											<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
											%> 
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyZone('${t.id}','${t.title}','${t.startTime}','${t.endTime}','${fn:replace(t.introduction, vEnter, nEnter)}','${t.BProductTeacher.name }','${t.participation==null?0:t.participation}','${t.lable }','${t.visitnum }','${t.detailsURL }','${t.stick }','${t.productLable }')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
											<br>
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByCourse_DownloadResumeAction?courseId=${t.id}">报了名-有简历</a></button>${Exclmsg }
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByCourse_DownloadResumeAction?courseId=${t.id}&status=1">报名成功简历</a></button>${Exclmsg }
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByCourse_DownloadResumeAction?courseId=${t.id}&status=3">报了名-没简历</a></button>${Exclmsg }	
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
		<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">增加课程</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="adminCourse_addCourse.action" enctype="multipart/form-data" method="post">
					<table>
						<tr>
							<td>
								<input id="id" type="hidden" name="id">
							</td>
							<td>
								 <input id="lablename" type="hidden" name="lablename">
							</td>
								 <input id="participation" type="hidden" name="participation"> 
						</tr>
						<tr>
							<td>
								<label class="control-label" for="title">课程名：</label>
							</td>
							<td>
								 <input id="title" type="text" name="title" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label form_starttime" for="startTime">开始时间：</label>
							</td>
							<td>
								<input class="form_endtime" id="startinfo" type="text" placeholder="请选择" name="startTime" >
							</td>
						</tr>
						<tr>
							<td>
								<label class=""datainp" for="endTime">结束时间：</label>
							</td>
							<td>
								<input class="form_endtime" id="dateinfo" type="text" placeholder="请选择" name="endTime" >
							</td>				
						</tr>
						<tr>
							<td>
								<label class="control-label" for="teacherId">课程讲师：</label>
							</td>
							<td>
								 <select id="teacherSelect1" name="teacherId" style="width:auto;">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${teacherList }">
										<option value="${t.id }" >${t.name }</option>
									</c:forEach>
			 					 </select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="lableId">项目类型：</label>
							</td>
							<td>										
								 <select id="productLable" name="productLable">
								 	<option value="" >请选择</option>
									<c:forEach var="t" items="${lableList }">
										<c:if test="${t.type==1 }">
											<option value="${t.lableName }" >${t.lableName }</option>
										</c:if>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="lableId">能力匹配：</label>
							</td>
							<td>										
								 <select id="lableId" name="usertype" class="selectpicker show-tick form-control" multiple data-live-search="false">
									<c:forEach var="t" items="${lableList }">
										<c:if test="${t.type==0 }">
											<option value="${t.lableName }" >${t.lableName }</option>
										</c:if>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="image">上传图片：</label>
							</td>
							<td>
								<input type="file" id="logo" name="image">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="introduction">课程介绍：</label>
							</td>
							<td>
								 <textarea id="introduction" rows="5" cols="50" style="width: 405px;" id="introduction" name="introduction"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="visitnum">浏览人数：</label>
							</td>
							<td>
								<input class="visitnum" id="visitnum" type="text"  name="visitnum" >
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="stick">是否置顶：</label>
							</td>
							<td>
								 <select id="stick" name="stick" style="width:auto;">
									<option value="0" checked="checked">正常</option>
									<option value="1" >置顶</option>
			 					 </select>
							</td>
						</tr>						
						<tr>
							<td>
								<label class="control-label" for="detailsURL">详情链接：</label>
							</td>
							<td>
								<input class="detailsURL" id="detailsURL" type="text"  name="detailsURL" >
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="visitNum">图文排版：</label>
							</td>
							<td  style="width: 300px;height: 350px;">
								<div id="editor" style="width: 500px;height: 300px;background-color: rgba(255, 192, 192, 0.28); overflow: scroll;">
								</div>
								<input  id="editorInput" type="text" name="richtext" style="display: none">
							</td>
						</tr>
					</table>	
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<input type="button" class="btn btn-danger" onclick="javascript:resetRichText()" value="清空图文编辑区"></input>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveZone()">保存</button>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/jedate/jedate.js"></script>
<script type="text/javascript">
 jeDate({
		dateCell:"#dateinfo",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00:00",
		okfun:function(val){alert(val)}
	})
	
	jeDate({
		dateCell:"#dateinfo1",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00:00",
		okfun:function(val){alert(val)}
	})
	jeDate({
		dateCell:"#dateinfo2",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00:00",
		okfun:function(val){alert(val)}
	})

	jeDate({
		dateCell:"#startinfo",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00:00",
		okfun:function(val){alert(val)}
	})
	
	$("#jedatebox").css("z-index","99999");
</script>
<!-- 富文本框 -->
<script src="../admin/js/bootstrap-wysiwyg.js"></script>
<script src="../admin/js/prefixfree.min.js"></script>
<script src="../admin/js/jquery.hotkeys.js"></script>
<script type="text/javascript">
$('#editor').wysiwyg();
function resetRichText(){
	if(confirm("确定要清除编辑区的内容吗?")){
		$("#editor").html("");
    	$("#editorInput").val("");
	}
	 
}

</script>