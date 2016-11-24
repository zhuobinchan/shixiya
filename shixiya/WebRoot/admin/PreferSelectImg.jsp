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
	
	$("#myModalLabel").html("增加图片");
	 $("#fm").attr("action","pImgs_addImgs.action");
	 resetValue();
}
 function saveZone(){
	
	/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 
 function modify(id,url,wname){

	$("#id").val(id);
	$("#targetUrl").val(url);
	$("#fm").attr("action","pImgs_updateImg.action");
	 var count = $("#pSelect option").length;
      
      for(var i=0;i<count;i++) 
 	  {
 		if($("#pSelect ").get(0).options[i].text == wname) 
    	 { 
         	$("#pSelect ").get(0).options[i].selected = true; 
        	break; 
       	 }  
 	 }

 }
 
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("pImgs_deleteImg",{id:id},
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
		$.post("pImgs_deleteImgs.action",{ids:ids},function(result){
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

function resetValue(){
	
	$("#targetUrl").val("");
	$("#pSelect").get(0).options[0].selected = true; 

}

</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加图片</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="pImgs_findByStatus.action" method="post" class="form-search" style="display: inline;">      
			   &nbsp;合作企业：
			  <select id="courseSelect" name="psId">
			  			<option value="">请选择..</option>
						<c:forEach items="${selectlist}" var="s">	
								<option value="${s.id }">${s.workName }</option>
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
						<h5>图片列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>图片</th>
									<th>堂外优选汇</th>													
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"  myids="${t.id }">${idx.index+1 }</td>								
										<td style="text-align: center;">
											<a href="${pageContext.request.contextPath}/${t.url}" target="_Blank">
												<img   height="70px" width="200px" src="${pageContext.request.contextPath}/${t.url}">
											</a>
										</td>
										<td style="text-align: center;">${t.preferenceSelect.workName }</td>
										<td style="text-align: center;">
											<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
											&nbsp;&nbsp;<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg" onclick="javascript:modify('${t.id}','${t.targetUrl }','${t.preferenceSelect.workName }')">修改</button>
										</td>
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
				<h3 id="myModalLabel">增加图片</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="pImgs_addImgs.action"  method="post" enctype="multipart/form-data">
					<table>
						<input type="hidden" id="id" name="id">
						<tr>
							<td>
								<label class="control-label" for="imgUrl">图片：</label>
							</td>
							<td>
								<input type="file" id="imgUrl" name="imgUrl">
								
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="courseSelect">合作企业：</label>
							</td>
							<td>
								 <select id="pSelect" name="preferenceSelect.id">
									 <option value="">请选择..</option>
									<c:forEach items="${selectlist}" var="s">	
										<option value="${s.id }">${s.workName }</option>
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