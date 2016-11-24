<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
	input[type="text"]{
		height: 26px!important;
		line-height: 26px!important;
		padding: 0 6px!important;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(function () {
	$("#logo").uploadPreview({ Img: "ImgPr", Width: 220, Height: 220 });
});
function openAddDlg(){
	$("#myModalLabel").html("增加小板块");
	$("#fm").attr("action","addHrComment_hr.action");
}
function saveSection(){
	 if ($("#title").val()==null||$("#title").val()=='') {
		 $("#error").html("请输入周点评标题！");
		 return false;
	 }
	 if ($("#hrId").val()==null||$("#hrId").val()=='') {
		 $("#error").html("请选择所属公司！");
		 return false;
	 }
	 if ($("#introduction").val()==null||$("#introduction").val()=='') {
		 $("#error").html("请输入简介！");
		 return false;
	 }
	 /* $.post("Section_save.action", $("#fm").serialize()); */
	 $("#fm").submit();
	 alert("保存成功！");
	 resetValue();
 }
 function modifyHrComment(id,name,logo,introduction,hrId,imgUrl,startTime,visitnum,state,mode){
 
 	var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
	 $("#myModalLabel").html("修改Hr周点评");
	 $("#fm").attr("action","updateHrComment_hr.action");
	 $("#id").val(id);
	 $("#title").val(name);
	  $("#startinfo").val(startTime);
	 $("#ImgPr").attr("src","${pageContext.request.contextPath}/"+imgUrl);
	 $("#introduction").val(introduction_removeEnter);
	  $("#visitnum").val(visitnum);
	  $("#states").val(state);
	  $("#Mode").val(mode);
	 $("#hrId").find("option[value='"+hrId+"']").attr("selected",true);
	 
 }
function sectionDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("delHrComment_hr.action",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.reload(true);
					}
				}
			);
	}

}
function deleteSections(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("delHrComment_hr.action",{ids:ids},function(result){
			var result=eval('('+result+')');
			if(result.success){
				alert("数据已成功删除！");
				window.location.reload(true);
			}else{
				alert("数据删除失败！");
			}
		});
	}else{
		return;
	}

}
function resetValue(){
	 $("#id").val("");
	 $("#title").val("");
	 $("#introduction").val("");
	 $("#hrId").find("option:selected").attr("selected",false);
	 $("#startinfo").val("");
	  $("#visitnum").val("");
}
function searchUserByNickName(userNickName){
	$.post("Section_getUserByNickName.action",{nickName:userNickName},function(result){
		var result=eval('('+result+')');
		$("#info").html(result.info);
		$("#masterId").val(result.masterId);
	});
}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">

			<form action="hrCommentFilter_hr.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;周点评标题：
			  <input name="title" value="" type="text" class="input-medium search-query" placeholder="输入小板块名称..."/>
			  &nbsp;所属公司：
			  <select name="hrId"><option value="">请选择...</option>
				<c:forEach var="hr" items="${hrs }">
					<option value="${hr.hrId}">${hr.company }</option>
				</c:forEach>
			  </select>
			  &nbsp;版主：
			  <select name="s_section.master.id"><option value="">请选择...</option>
				<c:forEach var="master" items="${masterList }">
					<option value="${master.id }" ${s_section.master.id==master.id?'selected':'' }>${master.nickName }</option>
				</c:forEach>
			  </select>
			  &nbsp;
			  <button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button><br/></br>
			  			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加周点评</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			</form>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>Hr周点评列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>标题</th>
									<th>发布时间</th>
									<th>封面</th>
									<th class="th">Hr姓名</th>
									<th>Hr所属公司</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hrCommentLists }" var="hrComment" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;" myids="${hrComment.id }" >${idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.title }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.publicTime }</td>
										
										<td style="text-align: center;">
											<a href="${pageContext.request.contextPath}/${hrComment.imageUrl }" target="_Blank">
												<img   height="100" width="200" src="${pageContext.request.contextPath}/${hrComment.imageUrl }">
											</a>
										</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.CProductHR.name }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.CProductHR.company }</td>
										<td style="text-align: center;vertical-align: middle;">
												<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
												%> 
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyHrComment(${hrComment.id},'${hrComment.title }','${hrComment.title }','${fn:replace(hrComment.introduction, vEnter, nEnter) }',${hrComment.CProductHR.hrId},'${hrComment.imageUrl }','${hrComment.startTime }','${hrComment.visitnum }','${hrComment.state }','${hrComment.mode }')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:sectionDelete(${hrComment.id})">删除</button>
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
		<div id="dlg" class="modal hide fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">添加周点评</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="addHrComment_hr.action" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td>
								<label class="control-label" for="sectionName">周点评标题：</label>
							</td>
							<td>
								<input id="title" type="text" name="title" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<img id="ImgPr" class="pull-left" style="width: 120px; height: 120px;" src="" />
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="logo">上传封面：</label>
							</td>
							<td>
								<input type="file" id="logo" name="img">
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="logo">上传视频：</label>
							</td>
							<td>
								<input type="file" id="video" name="video">
							</td>
						</tr>
						
												
						<tr>
							<td>
								<label class="control-label" for="zone">请选择公司：</label>
							</td>
							<td>
								<select id="hrId" name="hrId"><option value="">请选择...</option>
									<c:forEach var="hr" items="${hrs }">
										<option value="${hr.hrId }">${hr.company }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="sectionName">直播状态：</label>
							</td>
							<td>
							<select id="states" name="state">
										<option value="0">禁播</option>
										<option value="1">播放</option>
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label" for="mode">选择模式：</label>
							</td>
							<td><select id="Mode" name="mode">
									<option value="0">没有视频、 可以QA</option>
									<option value="1">有视频、可以QA</option>
							</select></td>
						</tr>
						
						
						<tr>
							<td>
								<label class="control-label" for="masterNickName">简介：</label>
							</td>
							<td>
								
								<font id="info" style="color: red;"></font>
								<textarea rows="5" cols="50" style="width: 305px;" id="introduction" name="introduction" ></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="sectionId">论坛版块：</label>
							</td>
							<td>
								 <select id="lableSelect" name="sectionId">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${zonelist }">
										<option value="${t.id }" >${t.name }</option>
									</c:forEach>
			 					 </select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="visitnum">浏览人数：</label>
							</td>
							<td>
								<input id="visitnum" type="text" name="visitnum" >
							</td>
						</tr>
					</table>
					<input id="id" type="hidden" name="id">
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveSection()">保存</button>
				<!-- <button class="btn btn-primary" type="submit">保存</button> -->
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/jedate/jedate.js"></script>
<script type="text/javascript">


	jeDate({
		dateCell:"#startinfo",
		isinitVal:true,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00:00",
		okfun:function(val){alert(val)}
	})
	
	$("#jedatebox").css("z-index","99999");
</script>

