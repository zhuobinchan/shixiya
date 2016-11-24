<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	function save() {
		if ($("#questionTitle").val() == null
				|| $("#questionTitle").val() == '') {
			$("#error").html("请输入问题标题！");
			return false;
		}
		if ($("#questionLimitTime").val() == null
				|| $("#questionLimitTime").val() == '') {
			$("#error").html("请填写时间长度！");
			return false;
		}
		if ($("#broadcastId").val() == null
				|| $("#broadcastId").val() == '') {
			$("#error").html("请填写时间长度！");
			return false;
		}

		/* $.post("Section_save.action", $("#fm").serialize()); */
		$("#fm").submit();
		alert("保存成功！");
		resetValue();
	}
	function modifyQuestion(questionId, questionTitle, broadcastId,
			questionLimitTime) {
		$("#questionId").val(questionId);
		$("#questionTitle").val(questionTitle);
		$("#broadcastId").val(broadcastId);
		$("#questionLimitTime").val(questionLimitTime);
	}
	function questionDelete(questionId) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("deleteQuestion_AdminCProductQuestionAction.action", {
				questionId : questionId
			}, function(result) {
				var result = eval('(' + result + ')');
				if (result.error) {
					alert(result.error);
				} else {
					alert("删除成功！");
					window.location.reload(true);
				}
			});
		}
	}
	function deleteQuestions() {
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
			$.post("deleteQuestionList_AdminCProductQuestionAction.action", {
				questionIds : ids
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
	function resetValue() {
		$("#questionId").val("");
		$("#questionTitle").val("");
		$("#broadcastId").val("");
		$("#questionLimitTime").val("");
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			
			<button class="btn btn-primary" type="button" data-backdrop="static"
					data-toggle="modal" data-target="#dlg"
					onclick="return openAddDlg()">添加问卷</button>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" role="button" class="btn btn-danger"
				onclick="javascrip:deleteQuestions()">批量删除</a>
			<form action="findByCondition_AdminCProductQuestionAction.action" method="post" class="form-search"
				style="display: inline;">
				&nbsp;问卷标题： <input name="question.title" value="${question.title }"
					type="text" class="input-medium search-query"
					/>
					&nbsp;
					直播间： <select name="question.CProductBroadcast.id"><option
						value="">请选择...</option>
					<c:forEach var="broadcast" items="${cpBroadcasts }">
						<option value="${broadcast.id }">${broadcast.name }</option>
					</c:forEach>
				</select> &nbsp;
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
						<h5>小板块列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>问题标题</th>
									<th class="th">所属直播间</th>
									<th>问题的时间长度</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${questions }" var="question" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;" myids="${question.id }">${idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${question.title }</td>

										<td style="text-align: center;vertical-align: middle;">${question.CProductBroadcast.name }</td>
										<td style="text-align: center;vertical-align: middle;">${question.limitTime }</td>
										<td style="text-align: center;vertical-align: middle;">
											<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg"
												onclick="return modifyQuestion('${question.id }','${question.title }','${question.CProductBroadcast.name }','${question.limitTime }')">修改
											</button>&nbsp;&nbsp;
											<button class="btn btn-danger" type="button"
												onclick="javascript:questionDelete('${question.id }')">删除</button>
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
				<h3 id="myModalLabel">增加小板块</h3>
			</div>
			<div class="modal-body">
				<form id="fm"
					action="saveOrUpdateQuestion_AdminCProductQuestionAction.action"
					method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td><label class="control-label" for="questionTitle">问题标题：</label>
							</td>
							<td><input id="questionTitle" type="text"
								name="question.title" placeholder="请输入…"></td>
						</tr>



						<tr>
							<td><label class="control-label" for="zone">所属直播间：</label></td>
							<td><select id="broadcastId"
								name="question.CProductBroadcast.id"><option value="">请选择...</option>
									<c:forEach var="broadcast" items="${cpBroadcasts }">
										<option value="${broadcast.id }">${broadcast.name }</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td><label class="control-label" for="questionLimitTime">问题的
									时间长度：</label></td>
							<td><input id="questionLimitTime" type="text"
								name="question.limitTime" placeholder="请输入…"></td>
						</tr>



					</table>
					<input id="questionId" type="hidden" name="question.id">
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:save()">保存</button>
				<!-- <button class="btn btn-primary" type="submit">保存</button> -->
			</div>
		</div>
	</div>
</body>
</html>