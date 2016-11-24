<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../admin/css/bootstrap-select.css">
<title>Insert title here</title>

<script type="text/javascript">

 function modify(id,name,address,linkman,telephone){
 
 	 $("#id").val("");
	 $("#name").val("");
	 $("#address").val("");
     $("#linkman").val("");
     $("#password").val("");
     $("#telephone").val("");
 
	 $("#id").val(id);
	 $("#name").val(name);
	 $("#address").val(address);
     $("#linkman").val(linkman);
     $("#telephone").val(telephone);
    
    	 
 }
 
  function saveZone(){
     
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }

function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("ribusiness_deleteRIBusiness.action",{id:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
				}
			);
	}
}

function shenhe(id){
	if(confirm("确定要通过审核吗?")){
		$.post("ribusiness_updateRIBusiness.action",{id:id},
				function(result){
					
						alert("修改成功！");
						window.location.reload(true);
				}
			);
	}
}


function butongguo(id){
	if(confirm("确定要不通过审核吗?")){
		$.post("ribusiness_outRIBusiness.action",{id:id},
				function(result){
					
						alert("修改成功！");
						window.location.reload(true);
				}
			);
	}
}

function downLoad(id){

	$("#item").val(id);
	$("#downFrom").submit();

}

 function resetValue(){
	
	 $("#id").val("");
	 $("#name").val("");
	 $("#startinfo").val("");
     $("#dateinfo").val("");
     $("#introduction").val("");
     $("#wname").val("");
     $("#wintroduction").val("");
     $("#wemail").val("");
     $("#visitNum").val("");    
    $("#logo").val("");
    $("#image").val("");

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
		$.post("ribusiness_deleteRIBusinesses.action",{ids:ids},function(result){
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
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="ribusiness_findByCondition.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;合作企业名：
			  <input name="businessName"  type="text" class="input-medium search-query" placeholder="输入企业名..."/>
			  &nbsp;联系人姓名：
			 <input name="linkName"  type="text" class="input-medium search-query" placeholder="输入联系人姓名..."/>
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
						<h5>合作企业列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>公司名称</th>
									<th>公司地址</th>
									<th>联系人</th>
									<th>联系方式</th>
									<th>邮箱</th>
									<th>是否审核通过</th>															
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="k" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"myids="${k.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${k.name }</td>
										<td style="text-align: center;">${k.address }</td>
										<td style="text-align: center;">${k.linkman }</td>
										<td style="text-align: center;">${k.telephone}</td>	
										<td style="text-align: center;">${k.email}</td>	
										<td style="text-align: center;">
											<c:if test="${k.state==0}">
												否
											</c:if>
											<c:if test="${k.state==1}">
												是
											</c:if>
										</td>									
										<td style="text-align: center;">		
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="javascript:modify(${k.id},'${k.name}','${k.address}','${k.linkman}','${k.telephone}')">修改</button>							
											<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${k.id})">删除</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:shenhe(${k.id})">通过</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:butongguo(${k.id})">不通过</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:downLoad(${k.id})">资料下载</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="pagination alternate">
					<ul class="clearfix">${pageCode }</ul>
				</div>


			</div>
	
	
			<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">修改企业信息</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="UpdateBussiness_companyRegister.action" enctype="multipart/form-data" method="post">
					<table>
						<tr>
							<td>
								<input id="id" type="hidden" name="bussiness.id">
							</td>
							<td>
								 <input id="lablename" type="hidden" name="lablename">
							</td>
								 <input id="participation" type="hidden" name="participation"> 
						</tr>
						<tr>
							<td>
								<label class="control-label" for="name">企业名：</label>
							</td>
							<td>
								 <input id="name" type="text" name="bussiness.name" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class=""datainp" for="linkman">联系人：</label>
							</td>
							<td>
								<input class="form_endtime" id="linkman" type="text" placeholder="请选择" name="bussiness.linkman" >
							</td>				
						</tr>
						<tr>
							<td>
								<label class="control-label" for="telephone">联系方式：</label>
							</td>
							<td>
								<input class="form_endtime" id="telephone" type="text" placeholder="请选择" name="bussiness.telephone" >
							</td>	
						</tr>
						<tr>
							<td>
								<label class="control-label" for="telephone">密码：</label>
							</td>
							<td>
								<input class="form_endtime" id="password" type="text" placeholder="请选择" name="bussiness.password" >
							</td>	
						</tr>	
						<tr>
							<td>
								<label class="control-label" for="telephone">logo：</label>
							</td>
							<td>
								<input class="form_endtime" id="logo" type="file" name="logo" >
							</td>	
						</tr>											
						<tr>
							<td>
								<label class="control-label form_starttime" for="address">地址：</label>
							</td>
							<td>
								<input class="form_endtime" id="address" type="text" style="width: 400px" placeholder="请选择" name="bussiness.address" >
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
	
	
			
			<div>
					
					<form id="downFrom" action="ribusiness_downLoadFile.action" method="post" >
						<input id="item" type="hidden" name="id">
					</form>
				
			</div>
		</div>
		
		
	</div>
</body>
</html>
