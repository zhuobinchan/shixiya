<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
 function modifyHrComment(id,name,logo,introduction,hrId){
	 $("#myModalLabel").html("修改Hr周点评");
	 $("#id").val(id);
	 $("#title").val(name);
	 $("#ImgPr").attr("src","${pageContext.request.contextPath}/"+logo);
	 $("#introduction").val(introduction);
	 $("#hrId").find("option[value='"+hrId+"']").attr("selected",true);
	 
 }
function commentDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("delHrComment_hrComment.action",{id:id},
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
		$.post("delHrComment_hrComment.action",{ids:ids},function(result){
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

			<form action="hrCommentFilter_hrComment.action" method="post" class="form-search" style="display: inline;">
<!-- 	          &nbsp;周点评标题： -->
<!-- 			  <input name="title" value="" type="text" class="input-medium search-query" placeholder="输入评论内容关键字..."/> -->
	          &nbsp;评论内容：
			  <input name="content" value="" type="text" class="input-medium search-query" placeholder="输入评论内容..."/>			  
			  &nbsp;Hr姓名：
			  <select name="hrId" style="width: 100px"><option value="">请选择...</option>
				<c:forEach var="hr" items="${hrs }">
					<option value="${hr.hrId}">${hr.name }</option>
				</c:forEach>
			  </select>
			  &nbsp;评论类型：
			  <select name="recordType" style="width: 80px"><option value="">请选择...</option>
					<option value="0" >语音</option>
					<option value="1" >文字</option>
					<option value="2" >图片</option>
			  </select>
			  &nbsp;
			  <button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button><a href="#" role="button" style="margin-left:30px" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a><br/></br>
			  			
			
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
									<th>周点评标题</th>
									<th>Hr姓名</th>
									<th>评论时间</th>
									<th class="th">评论类型</th>
									<th>评论内容</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hrComments }" var="hrComment" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;" myids="${hrComment.id }" >${(page-1)*6+idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.hrCommentList.title }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.hrCommentList.CProductHR.name }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.commentTime }</td>
										<td style="text-align: center;vertical-align: middle;">${hrComment.type==0?"录音":hrComment.type==1?"文字":"图片" }</td>
										
										<c:if test="${hrComment.type!=2 }">
											<td style="text-align: center;vertical-align: middle;">
											<c:if test="${hrComment.type==0}">
												<audio src="${pageContext.request.contextPath }/${hrComment.localUrl }" controls="controls">
											</c:if>
											${hrComment.type==0?"":hrComment.content }</td>
										</c:if>
										
										<c:if test="${hrComment.type==2 }">
										<td
											style="text-align: center;vertical-align: middle;width: 110px;vertical-align: middle;">
											<a href="${pageContext.request.contextPath}/${hrComment.localUrl }" target="_Blank">
											<img style="width: 100px;"
											src='${pageContext.request.contextPath}/${hrComment.localUrl }'></img>
											</a>
										</td>
										</c:if>										

										<td style="text-align: center;vertical-align: middle;">
					
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:commentDelete(${hrComment.id})">删除</button>
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
				<h3 id="myModalLabel">添加hr棒棒堂</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="addHrComment_hr.action" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td>
								<label class="control-label" for="sectionName">hr棒棒堂标题：</label>
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
								<label class="control-label" for="logo">上传logo：</label>
							</td>
							<td>
								<input type="file" id="logo" name="logo">
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
							<td>
								<label class="control-label" for="masterNickName">简介：</label>
							</td>
							<td>
								
								<font id="info" style="color: red;"></font>
								<textarea rows="5" cols="50" style="width: 305px;" id="introduction" name="introduction" ></textarea>
							</td>
						</tr>
						
					</table>
					<input id="id" type="hidden" name="hrCommentId">
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