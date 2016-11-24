<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
input[type="text"] {
	height: 26px !important;
	line-height: 26px !important;
	padding: 0 6px !important;
}
</style>
<link rel="stylesheet" href="../admin/css/bootstrap-select.css">
<script src="../admin/js/bootstrap-select.min.js"></script>
<script src="../admin/js/i18n/defaults-*.min.js"></script>
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
		resetValue();
	}
	function saveBroadcast() {
		var v =  $(".filter-option").text();
		$("#lablename").attr("value",v);
		
		if ($("#broadcastName").val() == null
				|| $("#broadcastName").val() == '') {
			$("#error").html("请输入直播间名称！");
			return false;
		}
		if ($("#startinfo").val() == null || $("#startinfo").val() == '') {
			$("#error").html("请输入开始时间！");
			return false;
		}
		if ($("#dateinfo").val() == null || $("#dateinfo").val() == '') {
			$("#error").html("请输入结束时间！");
			return false;
		}
		if ($("#bcpHR").val() == null || $("#bcpHR").val() == ''){
			$("#error").html("请选择所属公司");
			return false;
		}
		/* $.post("Section_save.action", $("#fm").serialize()); */
		$("#fm").submit();
		alert("保存成功！");
	}
	
	function modifyBroadcast(id, name,publisher, startTime,
			endTime, introduction, status, broadcastCProductHR, broadcastMode,broadcastParticipation,logo,lable,bcpLablePaste,visitnum,stick,productLable) {

		var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
		
		$("#fm").attr("action","saveOrUpdate_AdminCPBroadcastAction.action");
		$("#broadcastId").val(id);
		$("#broadcastName").val(name);
		$("#broadcastPublisher").val(publisher);
	    $("#startinfo").val(startTime);
   		$("#dateinfo").val(endTime);
		$("#broadcastIntroduction").val(introduction_removeEnter);
		$("#broadcastStatus").val(status);
		$("#bcpHR").val(broadcastCProductHR);
		$("#bcpLablePaste").val(bcpLablePaste);
		$("#visitnum").val(visitnum);
		$(".filter-option").text(lable);
		$("#broadcastMode").val(broadcastMode);
		$("#broadcastParticipation").val(broadcastParticipation);
		$("#stick").val(stick);
		$("#productLable").val(productLable);
		 $("#ImgPr").attr("src", "${pageContext.request.contextPath}/" + logo);
	}
	function broadcastDelete(broadcastId) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("delete_AdminCPBroadcastAction.action", {
				broadcastId : broadcastId
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
	function deleteBroadcasts() {
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
			$.post("deleteBroadcasts_AdminCPBroadcastAction.action", {
				broadcastIds : ids
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
		$("#broadcastId").val("");
		$("#broadcastName").val("");
		$("#broadcastPublisher").val("");
		$("#broadcastStartTime").val("");
		$("#broadcastEndTime").val("");
		$("#broadcastIntroduction").val("");
		$("#broadcastStatus").val("");
		$("#broadcastCProductHR").val("");
		$("#bcpHR").val("");
		$("#broadcastMode").val("");
		$("#broadcastParticipation").val("");
		$("#bcpLablePaste").val("");
		$("#visitnum").val("");
		$("#stick").val(0);
				
		$("#startinfo").val("");
		$("#dateinfo").val("");
		$(".filter-option").text("");
		$("#productLable").val("");
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

			<form action="findByName_AdminCPBroadcastAction.action" method="post"
				class="form-search" style="display: inline;">
				&nbsp; 直播间名称： <input name="broadcast.name" value="" type="text"
					class="input-medium search-query" placeholder="输入直播间名称...">
				&nbsp; 所属公司： <select id="broadcastCProductHR"
					name="broadcast.CProductHR.hrId"><option value="">请选择...</option>
					<c:forEach var="CProductHR" items="${cpHr }">
						<option value="${CProductHR.hrId }">${CProductHR.name }</option>
					</c:forEach>
				</select>&nbsp;
				<button type="submit" class="btn btn-primary" title="Search">
					查询&nbsp;<i class="icon  icon-search"></i>
				</button>
				<br> <br>
				<button class="btn btn-primary" type="button" data-backdrop="static"
					data-toggle="modal" data-target="#dlg"
					onclick="return openAddDlg()">添加直播间</button>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" role="button"
					class="btn btn-danger" onclick="javascrip:deleteBroadcasts()">批量删除</a>
			</form>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>boss直播间管理</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>直播间名称</th>
									<th>发布人</th>
									<th>开播时间</th>
									<th>结束时间</th>
									<th>HR姓名</th>
									<th>直播图片链接</th>
									<th>标签贴</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${broadcasts }" var="broadcast" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;"  myids="${broadcast.id }">${(page-1)*6+idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${broadcast.name }</td>

										<td style="text-align: center;vertical-align: middle;">${broadcast.publisher }</td>
										<td style="text-align: center;vertical-align: middle;">${broadcast.startTime }</td>
										<td style="text-align: center;vertical-align: middle;">${broadcast.endTime }</td>
										<td style="text-align: center;vertical-align: middle;">${broadcast.CProductHR.name }</td>
										<td
											style="text-align: center;vertical-align: middle;width: 110px;vertical-align: middle;">
											<img style="width: 100px;"
											src='${pageContext.request.contextPath}/${broadcast.imgUrl }'></img>
										</td>
										<td style="text-align: center;vertical-align: middle;"><img
											src='${pageContext.request.contextPath}/${broadcast.lablePaste.imageUrl }'></img></td>
										<td style="text-align: center;vertical-align: middle;">
											<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg"
												<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
												%> 
												onclick="return modifyBroadcast('${broadcast.id }','${broadcast.name }','${broadcast.publisher}','${broadcast.startTime }','${broadcast.endTime }','${fn:replace(broadcast.introduction, vEnter, nEnter) }','${broadcast.status }','${broadcast.CProductHR.hrId }','${broadcast.mode }','${broadcast.participation==null?0:broadcast.participation }','${broadcast.imgUrl }','${broadcast.lable }','${broadcast.lablePaste.id }','${broadcast.visitnum }','${broadcast.stick }','${broadcast.productLable }')">修改
											</button>&nbsp;&nbsp;
											<button class="btn btn-danger" type="button"
												onclick="javascript:broadcastDelete('${broadcast.id }')">删除</button>
											<br>	
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByBroadcast_DownloadResumeAction?broadcastId=${broadcast.id}">报了名-有简历</a></button>${Exclmsg }
											<br>
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByBroadcast_DownloadResumeAction?broadcastId=${broadcast.id}&status=1">报名成功的简历</a></button>${Exclmsg }
											<br>
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResumeByBroadcast_DownloadResumeAction?broadcastId=${broadcast.id}&status=3">报了名-没简历</a></button>${Exclmsg }
											<br>
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadEmployByBroadcast_DownloadResumeAction?broadcastId=${broadcast.id}">下载该直播间龙虎榜(最终进入面试环节)</a></button>${Exclmsg }
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
				<h3 id="myModalLabel">直播间信息添加或修改</h3>
			</div>
			<div class="modal-body">
				<form id="fm"
					action="addBroadcast_AdminCPBroadcastAction.action"
					method="post" enctype="multipart/form-data">
					<table>
					
						<tr>
							 <input id="lablename" type="hidden" name="lablename">
							<td><label class="control-label" for="broadcastName">直播间名称：</label>
							</td>
							<td><input id="broadcastName" type="text"
								name="broadcast.name" placeholder="请输入…"></td>
						</tr>
						<tr>

							<td><label class="control-label" for="broadcastPublisher">发布人：</label>
							</td>
							<td><input id="broadcastPublisher" type="text"
								name="broadcast.publisher" placeholder="请输入…"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="broadcastStartTime">开播时间：</label>
							</td>
							<td><input class="form_endtime" id="startinfo" type="text"
								placeholder="请选择" name="broadcast.startTime"></td>
						</tr>
						<tr>
							<td><label class="control-label" for="broadcastEndTime">结束时间：</label>
							</td>
							<td><input class="form_endtime" id="dateinfo" type="text"
								placeholder="请选择" name="broadcast.endTime"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="sectionName">直播状态：</label>
							</td>
							<td>
							<select id="state" name="broadcast.state">
										<option value="0">禁播</option>
										<option value="1">播放</option>
							</select></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="stick">是否置顶：</label>
							</td>
							<td>
							<select id="stick" name="broadcast.stick">
										<option value="0" checked="checked">正常</option>
										<option value="1">置顶</option>
							</select></td>
						</tr>	
						
						<tr>
							<td>
								<label class="control-label" for="lableId">项目类型：</label>
							</td>
							<td>										
								  <select id="productLable" name="broadcast.productLable">
								  	<option value="" >请选择</option>
									<c:forEach var="t" items="${lableList }">
										<c:if test="${t.type==1 }">
											<option value="${t.lableName }" >${t.lableName }</option>
										</c:if>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>					
						
						<tr>
							<td>
								<label class="control-label" for="lableId">能力匹配：</label>
							</td>
							<td>										
								  <select id="lableId" name="usertype" class="selectpicker show-tick form-control" multiple data-live-search="false">
									<c:forEach var="t" items="${lableList }">
										<c:if test="${t.type==0 }">
											<option value="${t.lableName }" >${t.lableName }</option>
										</c:if>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>

						<tr>
							<td><label class="control-label" for="bcpHR">所属公司：</label>
							</td>
							<td><select id="bcpHR"
								name="broadcast.CProductHR.hrId"><option value="">请选择...</option>
									<c:forEach var="CProductHR" items="${cpHr }">
										<option value="${CProductHR.hrId }">${CProductHR.name }</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td><label class="control-label" for="lablePaste">标签贴：</label>
							</td>
							<td><select id="bcpLablePaste"
								name="broadcast.lablePaste.id">
									<c:forEach var="lablePaste" items="${lablePastes }">
										<option value="${lablePaste.id }">${lablePaste.name }</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td><label class="control-label" for="broadcastMode">选择模式：</label>
							</td>
							<td><select id="broadcastMode" name="broadcast.mode">
									<option value="0">既播放视频、又可以QA</option>
									<option value="1">只播放视、 不可以QA</option>
									<option value="2">不可以播放视频、但可以QA</option>
							</select></td>
						</tr>




						<tr>
							<td><img id="ImgPr" class="pull-left"
								style="width: 120px; height: 120px;"
								src="${pageContext.request.contextPath}/${section.logo }" /></td>
							<td></td>
						</tr>

						<tr>
							<td><label class="control-label" for="img">上传图片：</label></td>
							<td><input type="file" id="logo" name="img"></td>
						</tr>
						<tr>
							<td><label class="control-label" for="video">上传视频：</label></td>
							<td><input type="file" id="video" name="video"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="sectionName">直播简介：</label>
							</td>
							<td>
							<textarea id="broadcastIntroduction" rows="5" cols="50" style="width: 405px;"  name="broadcast.introduction"></textarea>
							</td>
						</tr>
						<tr>

							<td><label class="control-label" for="visitnum">浏览人数：</label>
							</td>
							<td><input id="visitnum" type="text"
								name="broadcast.visitnum"></td>
						</tr>


					</table>
					<input id="broadcastId" type="hidden" name="broadcast.id">
					<input id="broadcastParticipation" type="hidden" name="broadcast.participation" value="0">
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveBroadcast()">保存</button>
				<!-- <button class="btn btn-primary" type="submit">保存</button> -->
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jedate/jedate.js"></script>
<script type="text/javascript">
	jeDate({
		dateCell : "#dateinfo",
		isinitVal : true,
		isTime : true, //isClear:false,
		minDate : "2014-09-19 00:00:00",
		okfun : function(val) {
			alert(val)
		}
	})

	jeDate({
		dateCell : "#startinfo",
		isinitVal : true,
		isTime : true, //isClear:false,
		minDate : "2014-09-19 00:00:00",
		okfun : function(val) {
			alert(val)
		}
	})

	$("#jedatebox").css("z-index", "99999");
</script>