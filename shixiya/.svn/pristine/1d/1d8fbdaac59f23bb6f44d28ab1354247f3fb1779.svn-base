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
	$("#myModalLabel").html("增加HR");
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
	if ($("#company").val() == null || $("#company").val() == '') {
			$("#error").html("请输入姓名！");
			return false;
	}
	/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 
 function modifyZone(id,name,telephone,company,introduction){
 
 var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
 
	 $("#myModalLabel").html("修改HR");
	 $("#id").val(id);
	 $("#name").val(name);
     $("#telephone").val(telephone);
     $("#company").val(company);
     $("#introduction").val(introduction_removeEnter);
     
	   
	   
 }
 
 function resetValue(){
	
	 $("#id").val("");
	 $("#name").val("");
     $("#telephone").val("");
     $("#company").val("");
     $("#introduction").val("");
     
     
}
 
function zoneDelete(id){
	if(confirm("确定要删除吗?")){
		$.post("adminHR_deleteHR.action",{hrId:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
					
				}
			);
	}
}

function deleteSections(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要删除的HR！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"个HR吗？")){
		$.post("adminHR_deleteHRs.action",{ids:ids},function(result){
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
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加HR</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="adminHR_findByMap.action" method="post" class="form-search" style="display: inline;">      
			   &nbsp;HR姓名：
			   <input name="name" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入HR名称..."/>
			    &nbsp;手机：
			   <input name="telephone" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入手机..."/>
			    &nbsp;所属公司：
			   <input name="company" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入公司..."/>
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
						<h5>管理员列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>姓名</th>
									<th>电话</th>
									<th>公司</th>													
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hr }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.hrId }">${idx.index+1 }</td>								
										<td style="text-align: center;">${t.name }</td>
										<td style="text-align: center;">${t.telephone }</td>
										<td style="text-align: center;">${t.company }</td>
										<td style="text-align: center;">
											<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
											%> 
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyZone('${t.hrId}','${t.name}','${t.telephone }','${t.company }','${fn:replace(t.introduction, vEnter, nEnter)}')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.hrId})">删除</button>
											
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
				<h3 id="myModalLabel">增加HR</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="${pageContext.request.contextPath}/admin/adminHR_addHR.action"  method="post" enctype="multipart/form-data" >
					<table>
							<input type="hidden" id="id" name="hrId">
						<tr>
							<td>
								<label class="control-label" for="name">HR姓名：</label>
							</td>
							<td>
								 <input id="name" type="text" name="name" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="image">头像：</label>
							</td>
							<td>
								 <input id="image" type="file" name="image" >
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="password">登陆密码：</label>
							</td>
							<td>
								 <input id="password" type="password" name="password" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="telephone">电话：</label>
							</td>
							<td>
								 <input id="telephone" type="text" name="telephone" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="company">公司：</label>
							</td>
							<td>
								 <input id="company" type="text" name="company" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="introduction">简介：</label>
							</td>
							<td>
								 <textarea id="introduction" rows="5" cols="50" style="width: 405px;" id="introduction" name="introduction"></textarea>
								
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