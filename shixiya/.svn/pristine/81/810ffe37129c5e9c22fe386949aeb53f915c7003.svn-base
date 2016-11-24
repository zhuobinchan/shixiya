<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript">

function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("psEntered_deletePreferenceSelectEntered.action",{enterid:id},
				function(result){
					
						alert("删除成功！");
						window.location.reload(true);
				}
			);
	}
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
		$.post("psEntered_deletePreferenceSelectEntereds.action",{ids:ids},function(result){
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
			<form action="psEntered_findByCondition.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;姓名：
			  <input name="sname"  type="text" class="input-medium search-query" placeholder="输入姓名..."/>
			  &nbsp;电话：
			 <input name="stelephone"  type="text" class="input-medium search-query" placeholder="输入电话..."/>
			 &nbsp;邮箱：
			 <input name="semail"  type="text" class="input-medium search-query" placeholder="输入邮箱..."/>
			 &nbsp;优选汇：
			  <select id="pSelect" class="input-medium search-query" name="sid">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${list1 }">
										<option value="${t.id }" >${t.perferenceName }</option>
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
						<h5>堂外优选汇列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>姓名</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>优选汇名</th>													
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="k" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${k.name }</td>
										<td style="text-align: center;">${k.telephone }</td>
										<td style="text-align: center;">${k.email }</td>
										<td style="text-align: center;">${k.preferenceSelect.perferenceName }</td>									
										<td style="text-align: center;">									
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${k.id})">删除</button>
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
		</div>
		
		
	</div>
</body>
</html>
