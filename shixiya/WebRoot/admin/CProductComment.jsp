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
	$(function() {
		$("#logo").uploadPreview({
			Img : "ImgPr",
			Width : 220,
			Height : 220
		});
	});
	function openAddDlg() {
		$("#myModalLabel").html("增加小板块");
	}

	function commentDelete(commentId) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("deleteComment_AdminCProductCommentAction.action", {
				commentId : commentId
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
	function deleteComments() {
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
			$.post("deleteCommentList_AdminCProductCommentAction.action", {
				commentIds : ids
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

	function searchUserByNickName(userNickName) {
		$.post("Section_getUserByNickName.action", {
			nickName : userNickName
		}, function(result) {
			var result = eval('(' + result + ')');
			$("#info").html(result.info);
			$("#masterId").val(result.masterId);
		});
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">

			<a href="#" role="button" class="btn btn-danger"
				onclick="javascrip:deleteComments()">批量删除</a>
			<form action="getCommentsByCondition_AdminCProductCommentAction.action" method="post" class="form-search"
				style="display: inline;">
				&nbsp;
				评论内容： <input name="commentContent" 
					type="text" class="input-medium search-query"
					/> 
				&nbsp;
				直播间名称： <input name="broadcastName" 
					type="text" class="input-medium search-query"
					/> 
					
					&nbsp; 评论类型：
				<select name="commentType">
					<option value="">请选择...</option>
					<option value="0">语音</option>
					<option value="1">文字</option>
					<option value="2">图片</option>
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
									<th>用户姓名</th>
									<th>评论类型</th>
									<th class="th">所属直播间</th>
									<th>评论时间</th>
									<th>评论内容</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${comments }" var="comment" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;" myids="${comment.id }">${idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${comment.name }</td>
										<td style="text-align: center;vertical-align: middle;">${comment.type==0?"语音":comment.type==1?"文字":"图片" }</td>
										<td style="text-align: center;vertical-align: middle;">${comment.CProductBroadcast.name }</td>
										<td style="text-align: center;vertical-align: middle;">${comment.commentTime }</td>
										<c:if test="${comment.type!=2 }">
											<td style="text-align: center;vertical-align: middle;">
											<c:if test="${comment.type==0}">
												<audio src="${pageContext.request.contextPath }/${comment.localUrl }" controls="controls">
											</c:if>
											${comment.type==0?"":comment.content }</td>
										</c:if>
										
										<c:if test="${comment.type==2 }">
										<td
											style="text-align: center;vertical-align: middle;width: 110px;vertical-align: middle;">
											<a href="${pageContext.request.contextPath}/${comment.localUrl }" target="_Blank">
											<img style="width: 100px;"
											src='${pageContext.request.contextPath}/${comment.localUrl }'></img>
											</a>
										</td>
										</c:if>
										
										<td style="text-align: center;vertical-align: middle;">

											<button class="btn btn-danger" type="button"
												onclick="javascript:commentDelete(${comment.id})">删除</button>
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

	</div>
</body>
</html>