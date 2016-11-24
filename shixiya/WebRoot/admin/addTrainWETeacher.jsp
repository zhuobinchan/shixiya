<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function openAddDlg(){
	$("#myModalLabel").html("增加we课堂老师");
}
 function saveZone(){
	 if ($("#name").val() == null || $("#name").val() == '') {
			$("#error").html("请输入姓名！");
			return false;
	}
	if ($("#telephone").val() == null || $("#telephone").val() == '') {
			$("#error").html("请输入姓名！");
			return false;
	}
	
	  alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
 }
 function modifyZone(id,name,telephone,email,introduction){
 	
 	var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
	 $("#myModalLabel").html("修改we课堂老师资料");
	 $("#fm").attr("action","update_AdminTrainWETeacherAction.action");
	 $("#id").val(id);
	 $("#name").val(name);
	 $("#email").val(email);
	 $("#introduction").val(introduction_removeEnter);
	 $("#telephone").val(telephone);
	   
	   
 }
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("delete_AdminTrainWETeacherAction.action",{id:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
					
				}
			);
	}
}
 function resetValue(){
	 $("#id").val("");
	 $("#name").val("");
	 $("#email").val("");
	 $("#introduction").val("");
	  $("#telephone").val("");
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
		$.post("deleteList_AdminTrainWETeacherAction.action",{ids:ids},function(result){
			if(result.success){
				alert("数据已成功删除！");
				location.reload(true);
			}else{
				alert("数据删除失败！");
			}
		},"json");
	}else{
		return;
	}
}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加讲师</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="findByCondition_AdminTrainWETeacherAction.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;老师名：
			  <input name="name"type="text" class="input-medium search-query" placeholder="输入讲师名称..."/>
			  &nbsp;老师电话：
			  <input name="telephone"  type="text" class="input-medium search-query" placeholder="输入讲师电话..."/>
			  &nbsp;老师邮箱：
			 <input name="email"  type="text" class="input-medium search-query" placeholder="输入讲师邮箱..."/>
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
						<h5>讲师列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>培训we教师姓名</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${trainWETeachers }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"  myids="${t.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${t.name }</td>
										<td style="text-align: center;">${t.telephone }</td>
										<td style="text-align: center;">${t.email }</td>
										<td style="text-align: center;">
												<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
												%> 
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyZone('${t.id }','${t.name }','${t.telephone }','${t.email }','${fn:replace(t.introduction, vEnter, nEnter) }')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
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
				<h3 id="myModalLabel">增加讲师</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="${pageContext.request.contextPath}/add_AdminTrainWETeacherAction.action"  method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<input id="id" type="hidden" name="trainWETeacher.id" >

						</tr>
						<tr>
							<td>
								<label class="control-label" for="name">讲师名：</label>
							</td>
							<td>
								 <input id="name" type="text" name="trainWETeacher.name" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="image">头像：</label>
							</td>
							<td>
								 <input id="image" type="file" name="image">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="password">密码：</label>
							</td>
							<td>
								 <input id="password" type="text" name="trainWETeacher.password" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="telephone">电话：</label>
							</td>
							<td>
								<input id="telephone" type="text" name="trainWETeacher.telephone" placeholder="请输入…">
						</tr>
						<tr>
							<td>
								<label class="control-label" for="email">邮箱：</label>
							</td>
							<td>
								  <input id="email" type="text" name="trainWETeacher.email" placeholder="请输入…">
							</td>
						</tr>
						
						
						<tr>
							<td>
								<label class="control-label" for="introduction">简介：</label>
							</td>
							<td>
								<textarea rows="5" cols="50" style="width: 405px;" id="introduction" name="trainWETeacher.introduction"></textarea>
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