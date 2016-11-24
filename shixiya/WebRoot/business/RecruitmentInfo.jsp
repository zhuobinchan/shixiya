<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap-select.css">
<script src="${pageContext.request.contextPath}/admin/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/i18n/defaults-*.min.js"></script>

<style>
	input[type="text"]{
		height: 26px!important;
		line-height: 26px!important;
		padding: 0 6px!important;
	}
</style>
<script type="text/javascript">
function openAddDlg(){
 
	$("#myModalLabel").html("增加岗位");
	 resetValue();
}

function save(){
 
 	
 
 	 if ($("#name").val() == null
				|| $("#name").val() == '') {
			$("#error").html("请输入岗位名称！");
			$("#name").focus();
			return false;
		}
		
	if ($("#area").val() == null
				|| $("#area").val() == '') {
			$("#error").html("请输入工作地区！");
			$("#area").focus();
			return false;
	}
			
	if ($("#address").val() == null
				|| $("#address").val() == '') {
			$("#error").html("请输入工作地点！");
			$("#address").focus();
			return false;
	}
		
	if ($("#workingDay").val() == null
				|| $("#workingDay").val() == '') {
			$("#error").html("请输工作时长！");
			$("#workingDay").focus();
			return false;
	}

	if ($("#advantage").val() == null
				|| $("#advantage").val() == '') {
			$("#error").html("请输入岗位优势！");
			$("#advantage").focus();
			return false;
	}	
	
	if ($("#advantage").val().replace(/[^\x00-\xff]/g, "** ").length > 18) {
			$("#error").html("岗位优势请输入0~6个汉子或0~18个字符以内！");
			$("#advantage").focus();
			return false;
	}
		
	if ($("#positionType").val() == null
				|| $("#positionType").val() == '') {
			$("#error").html("请选择任职类型！");
			return false;
	}	
	
	if ($("#workType").val() == null
				|| $("#workType").val() == '') {
			$("#error").html("请输入工作类型！");
			$("#workType").focus();
			return false;
		}
		
		if ($("#salary").val() == null
				|| $("#salary").val() == '') {
			$("#error").html("请输入工资！");
			$("#salary").focus();
			return false;
		}
		if ($("#population").val() == null
				|| $("#population").val() == '') {
			$("#error").html("请输入招聘人数！");
			$("#population").focus();
			return false;
		}
			
		if(!(($("#population").val()*1)%1 === 0)){
			$("#error").html("招聘人数请输入数字！");
			$("#population").focus();
				return false;
		}
		
		
		if ($("#introduction").val() == null
				|| $("#introduction").val() == '') {
			$("#error").html("请输入工作描述！");
			$("#introduction").focus();
			return false;
		}
	
		if ($("#demand").val() == null
					|| $("#demand").val() == '') {
			$("#error").html("请输入任职要求！");
			$("#demand").focus();
			return false;
		}
		if ($("#remark").val() == null
				|| $("#remark").val() == '') {
			$("#error").html("请输入备注！");
			$("#remark").focus();
			return false;
		}

		
	 $("#fm").submit();
	 alert("保存成功！");
	 resetValue();
	 $("#fm").attr("action","bussinessReInfo_addRecruitmentInfo.action");

 }
 

 function modify(id,name,address,publishTime,workType,salary,population,introduction,demand,workTime,remark,area,positionType,workingDay,advantage){
 
 	var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
 	var remark_removeEnter = remark.replace(/\[enter\]/g,"\n");
 	var demand_removeEnter = demand.replace(/\[enter\]/g,"\n");
 	
 	$("#fm").attr("action","bussinessReInfo_updateRecruitmentInfo.action");
	 $("#myModalLabel").html("修改章节");
	 $("#bid").val(id);
	 $("#name").val(name);
	 $("#area").val(area);
	 $("#address").val(address);
     $(".filter-option").text(workType);
     $("#salary").val(salary);
     $("#population").val(population);
 	 $("#introduction").val(introduction_removeEnter);
 	 $("#demand").val(demand_removeEnter);
 	 $("#startinfo").val(publishTime);
 	 $("#workinfo").val(workTime);
 	 $("#remark").val(remark_removeEnter);
 	 $("#area").val(area);
 	 $("#positionType").val(positionType);
 	 $("#workingDay").val(workingDay);
 	 $("#advantage").val(advantage);
 	 
 }
 
 function resetValue(){
	
	$("#area").val("");
	 $("#id").val("");
	  $("#address").val("");
	 $("#name").val("");
     $("#startinfo").val("");
     $("#workType").val("");
     $("#salary").val("");
     $("#population").val("");
 	 $("#introduction").val("");
 	 $("#demand").val("");
 	 $("#workinfo").val("");
 	 $("#remark").val("");
	 $("#area").val("");
	 $("#positionType").val("");
	 $("#workingDay").val("");
	 $("#advantage").val("");
 	$(".filter-option").text("Nothing Selected");
 	 $("#error").html("");
}

function changeStop(id){
	if(confirm("确定要改变暂停状态吗?")){
			$.post("bussinessReInfo_changeStop.action",{id:id},
					function(result){
						
							alert("状态改变成功！");
							window.location.reload(true);
					}
				);
		}
}



function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("bussinessReInfo_deleteRecruitmentInfo.action",{id:id},
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
		$.post("bussinessReInfo_deleteRecruitmentInfos.action",{ids:ids},function(result){
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

</script>
</head>

<%
	request.setAttribute("vEnter", "\r\n");   
	request.setAttribute("nEnter", "[enter]");
%> 

<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加岗位</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deletes()">批量删除</a>
			<form action="bussinessReInfo_findByCondition.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;岗位名：
			  <input name="infoName"  type="text" class="input-medium search-query" placeholder="输入岗位名..."/>
			  &nbsp;岗位类型：
			 <input name="infoType"  type="text" class="input-medium search-query" placeholder="输入岗位类型..."/>
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
						<h5>企业发布岗位列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check" ms-controller="infos">
							<thead >
								<tr>
									<th><i class=""></i></th>
									<th style="display:none"></th>
									<th>岗位名字</th>
									<th>岗位类型</th>
									<th>岗位介绍</th>
									<th>企业名</th>
									<th>审核是否通过</th>
									<th>是否暂停</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="k">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;display:none"><input id="id" type="hidden" name="id" value="${k.id}"></td>
										<td style="text-align: center;">${k.name }</td>	
										<td style="text-align: center;">${k.workType }</td>	
										<td style="text-align: center;">${k.introduction}</td>	
										<td style="text-align: center;">${k.RIBussiness.name}</td>	
										<td style="text-align: center;">
											<c:if test="${k.checkState == 0}">
													审核未通过
											</c:if>
											<c:if test="${k.checkState == 1}">
													审核通过
											</c:if>	
										</td>
										<td style="text-align: center;">
											<c:if test="${k.stopState == 0}">
													未停止
											</c:if>
											<c:if test="${k.stopState == 1}">
													以停止
											</c:if>	
										</td>									
										<td style="text-align: center;">									
											<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${k.id})">删除</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button"  data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="javascript:modify('${k.id}','${k.name}','${k.address}','${k.publishTime}','${k.workType}','${k.salary}','${k.population}','${fn:replace(k.introduction, vEnter, nEnter) }','${fn:replace(k.demand, vEnter, nEnter) }','${k.workTime}','${fn:replace(k.remark, vEnter, nEnter) }','${k.area}','${k.positionType}','${k.workingDay}','${k.advantage}')">修改</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:changeStop(${k.id})">暂停</button>
										
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
		<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">增加岗位</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="bussinessReInfo_addRecruitmentInfo.action"  method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<input id="bid" type="text" name="id" style="display: none">
						</tr>
						<tr>
							<td>
								<label class="control-label" for="name">岗位名：</label>
							</td>
							<td>
								 <input id="name" type="text" name="name" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="area">工作地区：</label>
							</td>
							<td>
								 <input id="area" type="text" name="area" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="address">工作地点：</label>
							</td>
							<td>
								 <input id="address" type="text" name="address" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="workingDay">工作时长：</label>
							</td>
							<td>
								 <input id="workingDay" type="text" name="workingDay" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="advantage">岗位优势：</label>
							</td>
							<td>
								 <input id="advantage" type="text" name="advantage" placeholder="请输入…">
							</td>
						</tr>												
						<tr>
							<td>
								<label class="control-label" for="positionType">任职类型：</label>
							</td>
							<td>										
								 <select id="positionType" name="positionType" >
								  <option value="">请选择...</option>
									<c:forEach var="t" items="${typeLableList }">
										<option value="${t.lableName }" >${t.lableName }</option>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>
												
						<tr>
							<td>
								<label class="control-label" for="workType">工作类型：</label>
							</td>
							<td>										
								  <select id="workType" name="workType" class="selectpicker show-tick form-control" multiple data-live-search="false">
									<c:forEach var="t" items="${lableList }">
										<option value="${t.lableName }" >${t.lableName }</option>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="salary">工资薪酬：</label>
							</td>
							<td>
								 <input id="salary" type="text" name="salary" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="population">招聘人数：</label>
							</td>
							<td>
								 <input id="population" type="text" name="population" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label form_starttime" for="workinfo">工作时间：</label>
							</td>
							<td>
								<input class="form_endtime" id="workinfo" type="text" placeholder="请选择" name="workTime" >
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label form_starttime" for="introduction">工作描述：</label>
							</td>
							<td>
								
								<textarea  rows="5" cols="50" style="width: 405px;" id="introduction" name="introduction" placeholder="请输入…"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label form_starttime" for="demand">任职要求：</label>
							</td>
							<td>
								
								<textarea  rows="5" cols="50" style="width: 405px;" id="demand" name="demand" placeholder="请输入…"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label form_starttime" for="remark">备注：</label>
							</td>
							<td>
								
								<textarea  rows="5" cols="50" style="width: 405px;" id="remark" name="remark" placeholder="请输入…"></textarea>
							</td>
						</tr>
						
		
					</table>
					
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:save()">保存</button>
			</div>
		</div>
		
	</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/jedate/jedate.js"></script>
<script type="text/javascript">
 
	jeDate({
		dateCell:"#workinfo",
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
