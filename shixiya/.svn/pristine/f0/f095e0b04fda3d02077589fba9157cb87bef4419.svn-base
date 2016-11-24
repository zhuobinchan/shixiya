<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

function shenhe(id){
	if(confirm("确定要改变审核状态吗?")){
			$.post("reInfo_changeCheck.action",{id:id},
					function(result){
						
							alert("状态改变成功！");
							window.location.reload(true);
					}
				);
		}
}

function changeTop(id){
	if(confirm("确定要改变置顶状态吗?")){
			$.post("reInfo_changeTop.action",{id:id},
					function(result){
						
							alert("状态改变成功！");
							window.location.reload(true);
					}
				);
		}
}


function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("reInfo_deleteRecruitmentInfo.action",{id:id},
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
		strIds.push(selectedSpan[i].innerHTML);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("reInfo_deleteRecruitmentInfos.action",{ids:ids},function(result){
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
			<form action="reInfo_findByCondition.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;岗位名：
			  <input name="infoName"  type="text" class="input-medium search-query" placeholder="输入岗位名..."/>
			  &nbsp;岗位类型：
			 <input name="infoType"  type="text" class="input-medium search-query" placeholder="输入岗位类型..."/>
			 &nbsp;合作企业：
			  <select id="pSelect" class="input-medium search-query" name="businessId">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${riList }">
										<option value="${t.id }" >${t.name }</option>
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
						<h5>企业发布岗位列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check" ms-controller="infos">
							<thead >
								<tr>
									<th><i class=""></i></th>
									<th>岗位Id</th>
									<th>岗位名字</th>
									<th>岗位类型</th>
									<th>岗位介绍</th>
									<th>企业名</th>
									<th>审核结果</th>
									<th>是否置顶</th>
									<th>是否暂停</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="k">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;">${k.id }</td>
										<td style="text-align: center;">${k.name }</td>	
										<td style="text-align: center;">${k.workType }</td>	
										<td style="text-align: center;">${k.introduction }</td>	
										<td style="text-align: center;">${k.RIBussiness.name}</td>	
										<td style="text-align: center;">
											<c:if test="${k.checkState == 0}">
												未审核通过
											</c:if>
											<c:if test="${k.checkState == 1}">
												审核通过
											</c:if>			
										</td>	
										<td style="text-align: center;">
											<c:if test="${k.topState == 0}">
													未置顶
											</c:if>
											<c:if test="${k.topState == 1}">
													以置顶
											</c:if>	
										</td>	
										<td style="text-align: center;">
											<c:if test="${k.stopState == 0}">
													未停止
											</c:if>
											<c:if test="${k.stopState == 1}">
													以停止
											</c:if>	
										</td>									
										<td style="text-align: center;">									
											<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${k.id})">删除</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:shenhe(${k.id})">审核</button>
											&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:changeTop(${k.id})">置顶</button>
										
										</td>
									</tr>
								</div>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="pagination alternate">
					<ul class="clearfix">${pageCode }</ul>
				</div>


			</div>
		</div>
		
		
	</div>
</body>
</html>
