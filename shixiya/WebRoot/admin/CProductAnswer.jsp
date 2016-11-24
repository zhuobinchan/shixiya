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
		$.post("adminAnswer_deleteAnswer.action",{id:id},
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
		strIds.push(selectedSpan[i].attributes["myids"].nodeValue);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("adminAnswer_deleteAnswers.action",{ids:ids},function(result){
			if(result.success){
				alert("数据已成功删除！");
				location.reload(true);
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
			<form action="adminAnswer_findByMap.action" method="post" class="form-search" style="display: inline;">      
			  &nbsp;学生名：
			   <input name="sname" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入学生名..."/>
			   &nbsp;问题项：
			  <select id="teacherSelect" class="input-medium search-query" name="questionId">
									<option value="" >请选择</option>
									<c:forEach var="t" items="${questionList }">
										<option value="${t.id }" >${t.title }</option>
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
						<h5>视频列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>答案内容</th>
									<th>问题项名</th>															
									<th>学生名</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${answerList }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;"  myids="${t.id }">${idx.index+1 }</td>
										<td style="text-align: center;">${t.content }</td>
										<td style="text-align: center;">${t.CProductQuestionList.title }</td>
										<td style="text-align: center;">${t.student.name }</td>
										<td style="text-align: center;">
											
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
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