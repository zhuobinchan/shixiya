<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/business/layer/layui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/business/layer/layui.js"></script>
<script type="text/javascript">

function downLoadResume(sid){

	$("#sid").val(sid);
	$("#downFrom").attr("action","bussinessSubmit_downLoadResume.action");
	$("#downFrom").submit();
	
	
	
}

function downLoadPersonInfo(ids){

	$("#ids").val(ids);
	$("#downFrom").attr("action","bussinessSubmit_downLoadPersonInfo.action");
	$("#downFrom").submit();

}

function visit(sid){
	$.post("bussinessSubmit_visit.action",{sid:sid},
				function(result){
					
					
					var resume =  eval("("+result+")");
					if(resume.resume!=null){
						layui.use('layer', function(){
  							var layer = layui.layer;
  	
  							layer.open({
  								type: 2,
    							content: resume.resume+"",
								title:"简历",
								area: ['900px', '600px']
							});
  
						});         
					}else{
						alert("暂无简历！");
					}
				}
			); 
	
}


function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("bussinessSubmit_deleteRecruitResumeSubmit.action",{id:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
				}
			);
	}
}

function deletes(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push($(selectedSpan[i]).find("input").val());
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("bussinessSubmit_deleteRecruitResumeSubmits.action",{ids:ids},function(result){
			if(result.success){
				alert("数据已成功删除！");
				window.location.reload(true);
			}else{
				alert("数据删除失败！");
			}
		},"json");
	}else{
		return;
	}
}

function yashuo(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要压缩的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push($(selectedSpan[i]).find("input").val());
	}
	var ids=strIds.join(",");
	$("#ids").val(ids);
	$("#downFrom").attr("action","bussinessSubmit_downLoadResumes.action");
	$("#downFrom").submit();
}

function excles(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	
	if(selectedSpan.length==0){
		alert("请选择要导出的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push($(selectedSpan[i]).find("input:last").val());
	}
	var ids=strIds.join(",");
	$("#ids").val(ids);
	$("#downFrom").attr("action","bussinessSubmit_downLoadPersonInfo.action");
	$("#downFrom").submit();
}

</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deletes()">批量删除</a>
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:yashuo()">批量下载</a>
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:excles()">批量导出Excel</a>
			
			<form action="bussinessSubmit_findByCondition" method="post" class="form-search" style="display: inline;">
	          &nbsp;姓名：
			  <input name="submitName"  type="text" class="input-medium search-query" style="height:30px" placeholder="输入要查找的姓名..."/>
			  &nbsp;邮箱：
			 <input name="submitEmail"  type="text" class="input-medium search-query" style="height:30px" placeholder="输入要查找的邮箱..."/>
			  &nbsp;电话：
			 <input name="submitPhone"  type="text" class="input-medium search-query" style="height:30px" placeholder="输入要查找的电话..."/>
			  &nbsp;岗位：
			  <select name="businessId" class="input-medium search-query">
			  	<option value="" selected="selected">选择岗位..</option>
			  	<c:forEach items="${rilist}" var="ri">
			  		<option value="${ri.id}">${ ri.name }</option>
			  	</c:forEach>
			  </select>
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
						<h5>应聘列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check" ms-controller="infos">
							<thead >
								<tr>
									<th><i class=""></i></th>
									<th style="display:none"></th>
									<th>学生名</th>
									<th>邮箱</th>
									<th>电话</th>
									<th>应聘岗位</th>									
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${rrsList }" var="k">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;display:none"><input id="id" type="hidden" name="id" value="${k.id}"><input type="hidden" class="stuId" value="${k.student.id }"/></td>
										<td style="text-align: center;">${k.name }</td>	
										<td style="text-align: center;">${k.email }</td>	
										<td style="text-align: center;">${k.telephone }</td>	
										<td style="text-align: center;">${k.recruitmentInfo.name}</td>								
										<td style="text-align: center;">									
											<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${k.id})">删除</button>								
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:visit(${k.student.id})">预览简历</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:downLoadResume(${k.student.id})">简历下载</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:downLoadPersonInfo(${k.student.id})">导出Excel</button>
										</td>
									</tr>
								
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="pagination alternate">
					<ul class="clearfix">${pageCode }</ul>
				</div>
			</div>
		</div>
		<div>
			<form id="downFrom" action="bussinessSubmit_downLoadResume.action" method="post">
				<input type="hidden" id="sid" name="sid">
				<input type="hidden" id="ids" name="ids">
			</form>
		</div>
		
	</div>
</body>
</html>
