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
	$("#myModalLabel").html("增加视频文件");
}
 function saveZone(){
	 if ($("#video").val() == null || $("#video").val() == '') {
			$("#error").html("请选择图片！");
			return false;
	}
// 	if ($("#broadcastList").val() == null || $("#broadcastList").val() == '') {
// 			$("#error").html("请选择we课堂");
// 			return false;
// 	}
	if ($("#courseSelect").val() == null || $("#courseSelect").val() == '') {
			$("#error").html("请选择，选定状态");
			return false;
	}
	/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 
 function chooiseAdv(id){

		$.post("updateAdv_AdminTrainWEAdvAction.action",{id:id},
			function(result){
					
					alert("修改成功！");
					window.location.reload(true);
					
			}
		);

 }
 
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("deleteAdv_AdminTrainWEAdvAction.action",{id:id},
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
		alert("请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("deleteAdvs_AdminTrainWEAdvAction.action",{ids:ids},function(result){
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
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加广告</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="findByStatus_AdminTrainWEAdvAction.action" method="post" class="form-search" style="display: inline;">      
			   &nbsp;广告状态：
			  <select id="teacherSelect" class="input-medium search-query" name="status">
									<option value="" >请选择</option>
									<option value="1" >选定</option>
									<option value="0" >未选定</option>	
								
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
						<h5>广告列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>广告</th>
									<th>状态</th>													
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${adv }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;">${idx.index+1 }</td>								
										<td style="text-align: center;">
											<a href="${pageContext.request.contextPath}/${t.imageUrl }" target="_Blank">
												<img   height="100" width="200" src="${pageContext.request.contextPath}/${t.imageUrl }">
											</a>
										</td>
										<td style="text-align: center;">
										<c:if test="${t.status ==1}">
											选定
										</c:if>
										<c:if test="${t.status ==0}">
											未选定
										</c:if>
										
										</td>
										<td style="text-align: center;">
				
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:chooiseAdv(${t.id})">上架下架</button>
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
				<h3 id="myModalLabel">增加广告</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="addAdv_AdminTrainWEAdvAction.action"  method="post" enctype="multipart/form-data">
					<table>
						<input type="hidden" id="logo" name="id">
						<tr>
							<td>
								<label class="control-label" for="video">上传广告：</label>
							</td>
							<td>
								<input type="file" id="video" name="adv">
								
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="courseSelect">状态：</label>
							</td>
							<td>
								 <select id="courseSelect" name="status">
									<option value="" >请选择</option>
									<option value="0" >未选定</option>
									<option value="1" >选定</option>
			 					 </select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="broadcastList">选择培训we课堂：</label>
							</td>
							<td>
								 <select id="broadcastList" name="trainWEId">
									<option value="" >请选择</option>
									<c:forEach var="b" items="${trainWEProduct}">
										<option value="${b.id }" >${b.title }</option>
									</c:forEach>
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