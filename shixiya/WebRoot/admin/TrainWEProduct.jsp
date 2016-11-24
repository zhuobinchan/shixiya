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
		$("#myModalLabel").html("培训WE课堂");
		$("#fm").attr("action","addTrainWEProduct_AdminTrainWEAction.action");
		resetValue();
	}
	function saveTrainWEProduct() {
		var v =  $(".filter-option").text();
		$("#lablename").attr("value",v);
		
		if ($("#trainWEName").val() == null
				|| $("#trainWEName").val() == '') {
			$("#error").html("请输入培训we名称！");
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
			$("#error").html("请选择授课老师");
			return false;
		}
		/* $.post("Section_save.action", $("#fm").serialize()); */
		$("#fm").submit();
		alert("保存成功！");
	}
	function modifyTrainWE(id, name,publisher, startTime,
			endTime, introduction,listViewContent, broadcastCProductHR,logo,lable,visitnum,stick,productLable) {
		resetValue();
		
		var introduction_removeEnter = introduction.replace(/\[enter\]/g,"\n");
		var listViewContent_removeEnter = listViewContent.replace(/\[enter\]/g,"\n");
		
		$("#fm").attr("action","updateTrainWEProduct_AdminTrainWEAction.action");
		$("#trainWEProductId").val(id);
		$("#trainWEName").val(name);
		$("#trainWEPublisher").val(publisher);
	    $("#startinfo").val(startTime);
   		$("#dateinfo").val(endTime);
		$("#trainWEIntroduction").val(introduction_removeEnter);
		$("#bcpHR").val(broadcastCProductHR);
		$("#visitnum").val(visitnum);
		$(".filter-option").text(lable);
		$("#listViewContent").val(listViewContent_removeEnter);
		$("#stick").val(stick);
		$("#productLable").val(productLable);
		 $("#ImgPr").attr("src", "${pageContext.request.contextPath}/" + logo);
	}
	function TrainWEDelete(trainWEId) {
		if (confirm("确定要删除这条数据吗?")) {
			$.post("deleteTrainWEProduct_AdminTrainWEAction.action", {
				trainWEId : trainWEId
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
	function deleteselectedSpans() {
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
			$.post("deleteTrainWEs_AdminTrainWEAction.action", {
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
	function resetValue() {
		$("#trainWEId").val("");
		$("#trainWEName").val("");
		$("#trainWEPublisher").val("");
	    $("#startinfo").val("");
   		$("#dateinfo").val("");
		$("#trainWEIntroduction").val("");
		$("#listViewContent").val("");
		$("#bcpHR").val("");
		$("#visitnum").val("");
		$("#lableId").val("");
		$(".filter-option").text("");
		$("#ImgPr").attr("src","");
		$("#productLable").val("");
		$("#stick").val(0);

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

			<form action="findByCondition_AdminTrainWEAction.action" method="post"
				class="form-search" style="display: inline;">
				&nbsp; 培训WE课堂名称： <input name="title" value="" type="text"
					class="input-medium search-query" placeholder="输入名称...">
				&nbsp; 所属公司： <select id="broadcastCProductHR"
					name="teacherId"><option value="">请选择...</option>
					<c:forEach var="teacher" items="${teachers }">
						<option value="${teacher.id }">${teacher.name }</option>
					</c:forEach>
				</select>&nbsp;
				<button type="submit" class="btn btn-primary" title="Search">
					查询&nbsp;<i class="icon  icon-search"></i>
				</button>
				<br> <br>
			</form>
			<button class="btn btn-primary" type="button" data-backdrop="static"
					data-toggle="modal" data-target="#dlg"
					onclick="return openAddDlg()">添加直播间</button>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" role="button"
					class="btn btn-danger" onclick="javascrip:deleteselectedSpans()">批量删除</a>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>培训WE课堂</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>培训we课堂名称</th>
									<th>发布人</th>
									<th>开播时间</th>
									<th>结束时间</th>
									<th>培训we课堂老师姓名</th>
									<th>培训we课堂图片链接</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${trainWEProducts }" var="train" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;" myids="${train.id }">${(page-1)*6+idx.index+1 }</td>
										<td style="text-align: center;vertical-align: middle;">${train.title }</td>

										<td style="text-align: center;vertical-align: middle;">${train.publisher }</td>
										<td style="text-align: center;vertical-align: middle;">${train.startTime }</td>
										<td style="text-align: center;vertical-align: middle;">${train.endTime }</td>
										<td style="text-align: center;vertical-align: middle;">${train.trainWETeacher.name }</td>
										<td
											style="text-align: center;vertical-align: middle;width: 110px;vertical-align: middle;">
											<img style="width: 100px;"
											src='${pageContext.request.contextPath}/${train.imgUrl }'></img>
										</td>
										<td style="text-align: center;vertical-align: middle;">
											<button class="btn btn-info" type="button"
												data-backdrop="static" data-toggle="modal"
												data-target="#dlg"
												<%
												    request.setAttribute("vEnter", "\r\n");   
												    request.setAttribute("nEnter", "[enter]");
												%> 
												onclick="return modifyTrainWE('${train.id }','${train.title }','${train.publisher}','${train.startTime }','${train.endTime }','${fn:replace(train.introduction, vEnter, nEnter) }','${fn:replace(train.listViewContent, vEnter, nEnter) }','${train.trainWETeacher.id }','${train.imgUrl }','${train.lable }','${train.visitnum }','${train.stick }','${train.productLable}')">修改
											</button>&nbsp;&nbsp;
											<button class="btn btn-danger" type="button"
												onclick="javascript:TrainWEDelete('${train.id }')">删除</button>
										
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
					action="addTrainWEProduct_AdminTrainWEAction.action"
					method="post" enctype="multipart/form-data">
					<table>
					
						<tr>
							 <input id="lablename" type="hidden" name="lablename">
							<td><label class="control-label" for="trainWEName">培训we课堂名称：</label>
							</td>
							<td><input id="trainWEName" type="text"
								name="trainWEProduct.title" placeholder="请输入…"></td>
						</tr>
						
						<tr>

							<td><label class="control-label" for="trainWEPublisher">发布人：</label>
							</td>
							<td><input id="trainWEPublisher" type="text"
								name="trainWEProduct.publisher" placeholder="请输入…"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="trainWEStartTime">开始时间：</label>
							</td>
							<td><input class="form_endtime" id="startinfo" type="text"
								placeholder="请选择" name="trainWEProduct.startTime"></td>
						</tr>
						
						<tr>
							<td><label class="control-label" for="trainWEEndTime">结束时间：</label>
							</td>
							<td><input class="form_endtime" id="dateinfo" type="text"
								placeholder="请选择" name="trainWEProduct.endTime"></td>
						</tr>
						
						<tr>
							<td>
								<label class="control-label" for="lableId">项目类型：</label>
							</td>
							<td>										
								 <select id="productLable" name="trainWEProduct.productLable">
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
								  <select id="lableId" name="trainWEProduct.lable" class="selectpicker show-tick form-control" multiple data-live-search="false">
									<c:forEach var="t" items="${lables }">
										<c:if test="${t.type==0 }">
											<option value="${t.lableName }" >${t.lableName }</option>
										</c:if>
									</c:forEach>	
			 					 </select>
							</td>
						</tr>

						<tr>
							<td><label class="control-label" for="bcpHR">选择老师：</label>
							</td>
							<td><select id="bcpHR"
								name="trainWEProduct.trainWETeacher.id"><option value="">请选择...</option>
									<c:forEach var="teacher" items="${teachers }">
										<option value="${teacher.id }">${teacher.name }</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label" for="stick">是否置顶：</label>
							</td>
							<td>
								<select id="stick" name="trainWEProduct.stick" >
									<option value="0" >正常</option>
									<option value="1" >置顶</option>
			 					</select>
							</td>
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
							<td><label class="control-label" for="sectionName">直播简介：</label>
							</td>
							<td>
							<textarea id="trainWEIntroduction" rows="5" cols="50" style="width: 405px;"  name="trainWEProduct.introduction"></textarea>
							</td>
						</tr>
						<tr>
							<td><label class="control-label" for="sectionName">直播列表内容：</label>
							</td>
							<td>
							<textarea id="listViewContent" rows="5" cols="50" style="width: 405px;"  name="trainWEProduct.listViewContent"></textarea>
							</td>
						</tr>						
						<tr>

							<td><label class="control-label" for="visitnum">浏览人数：</label>
							</td>
							<td><input id="visitnum" type="text"
								name="trainWEProduct.visitnum"></td>
						</tr>


					</table>
					<input id="trainWEProductId" type="hidden" name="trainWEProduct.id">
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveTrainWEProduct()">保存</button>
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