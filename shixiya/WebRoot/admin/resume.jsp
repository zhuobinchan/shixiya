<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- I feel so tired in there -->
<script type="text/javascript">

function zoneDelete(id){
	if(confirm("确定要删除这条数据吗?")){
		$.post("adminResume_deleteResume.action",{id:id},
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
		$.post("adminResume_deleteResumes.action",{ids:ids},function(result){
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

function downloadResume(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要下载的简历！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push(selectedSpan[i].innerHTML);
	}
	var ids=strIds.join(",");
	window.location.href="downloadResumeList_DownloadResumeAction?resumeIds="+ids;
}

</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
		
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<a href="#" role="button" class="btn btn-primary"  onclick="javascrip:downloadResume()">批量下载简历</a>
			
			<form action="adminResume_findByName.action" method="post" class="form-search" style="display: inline;">      
			   &nbsp;姓名：
				<input type="text" name="name">
			  &nbsp;
			  <button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button>
			  &nbsp;
			  		
			</form>
	
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>簡歷列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>序号</th>
									<th>名字</th>
									<th>學校</th>													
									<th>專業</th>
									<th>應聘職位</th>
									<th>電話</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resumes }" var="t" varStatus="idx">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;" myids="${t.id }">${idx.index+1 }</td>								
										<td style="text-align: center;">${t.name }</td>
										<td style="text-align: center;">${t.school }</td>
										<td style="text-align: center;">${t.major }</td>
										<td style="text-align: center;">${t.job }</td>
										<td style="text-align: center;">${t.telephone }</td>
										<td style="text-align: center;">
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:zoneDelete(${t.id})">删除</button>
											<%-- </button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:void(${t.id})">查看詳情</button> --%>
											<button class="btn btn-danger" type="button"><a style="color: white;" href="downloadResume_DownloadResumeAction?resumeId=${t.id}">下载简历</a></button>${Exclmsg }
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