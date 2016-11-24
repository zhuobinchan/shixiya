<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function openAddDlg(){
	$("#myModalLabel").html("增加章节");
}
 function saveZone(){
 
    if ($("#title").val() == null || $("#title").val() == '') {
			$("#error").html("请输入章节名称！");
			return false;
		}
	
		

		if ($("#courseSelect").val() == null || $("#courseSelect").val() == ''){
			$("#error").html("请选择所属课程！");
			return false;
		}
	
	/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 function modifyZone(id,title,ctitle,introduction){
 
	 $("#myModalLabel").html("修改章节");
	 $("#id").val(id);
	 $("#title").val(title);
     $("#introduction").val(introduction);
      var count = $("#courseSelect option").length;
      
      for(var i=0;i<count;i++) 
 	  {
 		if($("#courseSelect ").get(0).options[i].text == ctitle) 
    	 { 
         	$("#courseSelect ").get(0).options[i].selected = true; 
        	break; 
       	 }  
 	 }
	
	   
	   
 }
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("adminChapter_deleteChapter.action",{id:id},
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
     $("#introduction").val("");
     $("#courseSelect ").get(0).options[0].selected = true;
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
		$.post("adminChapter_deleteChapters.action",{ids:ids},function(result){
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
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加章节</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="adminChapter_findChapterByMap.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;章节名：
			  <input name="title" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入章节名称..."/>
			  &nbsp;课程名：
			  <select id="teacherSelect" class="input-medium search-query" name="courseId">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${courseList }">
										<option value="${t.id }" >${t.title }</option>
									</c:forEach>
			  </select>
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
									<th>章节名称</th>
									<th>所属课程</th>															
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chapter }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${t.title }</td>
										<td style="text-align: center;">${t.BProductCourse.title }</td>
										<td style="text-align: center;">
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyZone('${t.id}','${t.title}','${t.BProductCourse.title }','${t.introduction }')">修改
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
				<h3 id="myModalLabel">增加章节</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="adminChapter_addChapter.action"  method="post">
					<table>
						<tr>
							<input id="id" type="hidden" name="id" >
						</tr>
						<tr>
							<td>
								<label class="control-label" for="title">章节名：</label>
							</td>
							<td>
								 <input id="title" type="text" name="title" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="teacherId">所属课程：</label>
							</td>
							<td>
								 <select id="courseSelect" name="BProductCourse.id">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${courseList }">
										<option value="${t.id }" >${t.title }</option>
									</c:forEach>
			 					 </select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label form_starttime" for="introduction">课程介绍：</label>
							</td>
							<td>
								
								<textarea id="introduction" rows="5" cols="50" style="width: 405px;" id="introduction" name="introduction" placeholder="请输入…"></textarea>
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