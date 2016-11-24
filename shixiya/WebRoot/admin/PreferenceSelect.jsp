<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../admin/css/bootstrap-select.css">
<script src="../admin/js/bootstrap-select.min.js"></script>
<script src="../admin/js/i18n/defaults-*.min.js"></script>
<script type="text/javascript">
function openAddDlg(){
	$("#myModalLabel").html("增加堂外优选汇");
}
 function saveZone(){
 
 	var str = $("#editor").html();
 	str = str.replace(/&lt;/g,"<");
 	str = str.replace(/&gt;/g,">");
    
	$("#editorInput").val(str);
	 alert("保存成功！");
	 $("#fm").submit();
	 resetValue();
	/*  location.reload(true); */
 }
 function modifyZone(id,workName,workEmail,workIntroduction,visitNum,stick){

	
 	 var introduction_removeEnter = workIntroduction.replace(/\[enter\]/g,"\n");
 	
	 $("#myModalLabel").html("修改堂外优选汇");
	 $("#id").val(id);
     $("#wname").val(workName);
     $("#wintroduction").val(introduction_removeEnter);
     $("#wemail").val(workEmail);
     $("#visitNum").val(visitNum); 
     $("#stick").val(stick);
	
	   
	   
 }
function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("pSelect_deletePreferenceSelect.action",{id:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
				}
			);
	}
}

 function sendEmail(id){
 	if(confirm("确定要发送邮件吗?")){
		$.post("pSelect_sendEmail.action",{id:id},
				function(result){
					
						alert("发送成功！");
						
				}
			);
	}
 }
 function resetValue(){
	
	 $("#id").val("");
     $("#wname").val("");
     $("#wintroduction").val("");
     $("#wemail").val("");
     $("#visitNum").val("");    
    $("#logo").val("");
    $("#stick").val(0);
     $("#editor").html("");
     $("#editorInput").val("");
    
    
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
		$.post("pSelect_deletePreferenceSelects.action",{ids:ids},function(result){
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
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加优选汇</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="pSelect_findPrefereneceSelectByCondition" method="post" class="form-search" style="display: inline;">
			  &nbsp;合作机构名：
			 <input name="wkName"  type="text" class="input-medium search-query" placeholder="输入合作机构名称..."/>
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
						<h5>堂外优选汇列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>合作机构名称</th>
									<th>合作邮箱</th>														
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.id }">${idx.index+1 }</td>										
										<td style="text-align: center;">${t.workName }</td>
										<td style="text-align: center;">${t.workEmail }</td>
												<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
												%> 										
										<td style="text-align: center;">
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifyZone('${t.id}','${t.workName}','${t.workEmail}','${fn:replace(t.workIntroduction, vEnter, nEnter) }','${t.visitNum}','${t.stick}')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:sendEmail(${t.id})">发送邮件</button>
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
				<h3 id="myModalLabel">增加堂外优选汇</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="pSelect_addPreferenceSelect.action" enctype="multipart/form-data" method="post">
					<table>
						<tr>
							<input id="id" type="hidden" name="id" >
						</tr>											
						<tr>
							<td><label class="control-label" for="stick">是否置顶：</label>
							</td>
							<td>
								<select id="stick" name="stick" >
									<option value="0" >正常</option>
									<option value="1" >置顶</option>
			 					</select>
							</td>
						</tr>		
						<tr>
							<td>
								<label class="control-label" for="wname">合作企业名称：</label>
							</td>
							<td>
								 <input id="wname" type="text" name="workName" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="wintroduction">合作企业介绍：</label>
							</td>
							<td>
									<textarea id="wintroduction" rows="5" cols="50" style="width: 405px;" name="workIntroduction" placeholder="请输入…"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="wemail">合作企业邮箱：</label>
							</td>
							<td>
								 <input id="wemail" type="text" name="workEmail" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="wimage">合作企业logo：</label>
							</td>
							<td>
								 <input id="logo" type="file" name="workLogos" placeholder="请选择…">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="visitNum">浏览人数：</label>
							</td>
							<td>
								 <input  id="visitNum" type="text" name="visitNum" placeholder="请输入…">
							</td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="visitNum">图文排版：</label>
							</td>
							<td  style="width: 300px;height: 350px;">
								<div id="editor" style="width: 500px;height: 300px;background-color: rgba(255, 192, 192, 0.28); overflow: scroll;">
								</div>
								<input  id="editorInput" type="text" name="richtext" style="display: none">
							</td>
						</tr>
					</table>
					
				</form>
				
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<input type="button" class="btn btn-danger" onclick="javascript:resetRichText()" value="清空图文编辑区"></input>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveZone()">保存</button>
			</div>
			
		</div>
	</div>
</body>
<!-- 富文本框 -->
<script src="../admin/js/bootstrap-wysiwyg.js"></script>
<script src="../admin/js/prefixfree.min.js"></script>
<script src="../admin/js/jquery.hotkeys.js"></script>
<script type="text/javascript">
$('#editor').wysiwyg();
function resetRichText(){
	if(confirm("确定要清除编辑区的内容吗?")){
		$("#editor").html("");
    	$("#editorInput").val("");
	}
	 
}

</script>
</html>
