<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function openAddDlg() {
		$("#myModalLabel").html("增加标签");
	}
	function saveZone() {
	if ($("#lableName").val() == null || $("#lableName").val() == '') {
			$("#error").html("请输入标签名！");
			return false;
	}
	if ($("#lableType").val() == null || $("#lableType").val() == '') {
			$("#error").html("请输入标签类型！");
			return false;
	}
		
		/*  $.post("adminCourse_addCourse.action", $("#fm").serialize()); */
		alert("保存成功！");
		$("#fm").submit();
		resetValue();
		/*  location.reload(true); */
	}
	function modifyZone(lableId, lableName,type) {

		$("#myModalLabel").html("修改标签");
		$("#id").val(lableId);
		$("#lableName").val(lableName);
		$("#lableType").val(type);
		

	}
	function zoneDelete(lableId) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("adminLable_deleteLable.action", {
				lableId : lableId
			}, function(result) {

				alert("删除成功！");
				window.location.reload(true);

			});
		}
	}
	function resetValue() {

		$("#id").val("");
		$("#lableName").val("");
		$("#lableType").val("");

	}
	function deleteSections() {
		var selectedSpan = $(".checked").parent().parent().next("td");
		if (selectedSpan.length == 0) {
			alert("请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedSpan.length; i++) {
			strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
		}
		var ids = strIds.join(",");
		if (confirm("您确定要删除这" + selectedSpan.length + "条数据吗？")) {
			$.post("adminLable_deleteLables.action", {
				ids : ids
			}, function(result) {
				if (result.success) {
					alert("数据已成功删除！");
					location.reload(true);
				} else {
					alert("数据删除失败！");
				}
			}, "json");
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static"
				data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加能力匹配标签</button>
			&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" role="button"
				class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="adminLable_findByName.action" method="post"
				class="form-search" style="display: inline;">
				&nbsp;标签名： <input name="lableName" value="${s_section.name }"
					type="text" class="input-medium search-query"
					placeholder="输入标签名..." />

				<button type="submit" class="btn btn-primary" title="Search">
					查询&nbsp;<i class="icon  icon-search"></i>
				</button>
			</form>

		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>视频列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>标签名</th>
									<th>标签类型</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lables }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"  myids="${t.lableId }">${idx.index+1 }</td>
										<td style="text-align: center;">${t.lableName }</td>
										<td style="text-align: center;">${t.type==0?"能力匹配类型标签":"筛选类型标签" }</td>
										<td style="text-align: center;">
											<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg"
												onclick="return modifyZone('${t.lableId }','${t.lableName }','${t.type }')">修改
											</button>&nbsp;&nbsp;
											<button class="btn btn-danger" type="button"
												onclick="javascript:zoneDelete(${t.lableId })">删除</button>
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
		<div id="dlg" class="modal hide fade" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">增加标签</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="adminLable_addLable.action" method="post">
					<table>
						<tr>
							<input id="id" type="hidden" name="lableId">
						</tr>
						<tr>
							<td><label class="control-label" for="lableName">标签名：</label>
							</td>
							<td><input id="lableName" type="text" name="lableName"
								placeholder="请输入…"></td>
						</tr>
						<tr>
							<td><label class="control-label" for="lableType">标签类型：</label>
							</td>
							<td><select id="lableType" name="type">
									<option value="1">筛选项目类别标签</option>
									<option value="0">能力匹配类型标签</option>
									<option value="2">企业岗位类型标签</option>
									<option value="3">企业应聘类型标签</option>
							</select></td>
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
