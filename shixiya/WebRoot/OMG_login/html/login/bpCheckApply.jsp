<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("bpTeacher") == null) {
		response.sendRedirect("guiderLogin.jsp");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--IE8兼容-->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<!--禁止缩放-->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!--手机满屏变大-->
<meta name="format-detection" content="telephone=no">
<!--去掉手机强制某些数值识别成链接-->
<title>报名审核</title>
<link rel="stylesheet" href="<%=path%>/omg/css/style.css">
<link rel="stylesheet" href="<%=path%>/omg/css/person.css">
<link rel="stylesheet" href="../../css/checkAnswer.css">
</head>
<body>
	<button style="width: 100%;height: 40px" onclick="backPage()">返回</button>
    <form style="margin-top: 20px" class="sx" action="findBpStuApply_CheckApplyAction?courseId=${courseId }" method="post">
	        姓名:<input type="text" name="stuName">
	        学校:<input type="text" name="stuSchool">
	        专业:<input type="text" name="stuMajor"><br>
	        毕业时间:<input type="date" name="stuGraduaDate">
	        <input type="submit" value="确认筛选">
    </form>
		<table cellspacing="0">

			<c:forEach items="${bpStuApplys }" var="bpStuApply">
			<c:if test="${bpStuApply.states!=1 }">
				<tr>
					<td><input type="checkbox" name="studentId"
						value="${bpStuApply.student.id }"></td>
					<td>
						<div class="txt-show">
							<p class="s-title">报名状态：</p>
							<font color="${bpStuApply.states==0?'green':'red' }">${bpStuApply.states==0?'等待审核中':'审核不通过' }</font>
						</div>
						<div class="img">
							<div class="img-show">
							<a href="getResumeByStudentId_CPHrCheckStudentResumeAction.action?studentId=${bpStuApply.student.id }">
								<img src="${bpStuApply.student.headUrl }">
								</a>
							</div>
							<p class="nickname">${bpStuApply.student.name }</p>
						</div> 
					</td>
				</tr>
			</c:if>
			</c:forEach>
			
			
		</table>
        <div class="btns">
            <input type="button" value="一键全选" onclick="chooseAll()">
            <input type="button" value="一键反选" onclick="chooseNull()">
            <input type="submit" id="submits" style="background-color: #d96a38" value="提&nbsp;&nbsp;交">
        </div>		
		<input id="courseId" type="hidden" name="courseId"
			value="${courseId }">
	<script src="<%=path%>/omg/js/jquery-1.11.2.js"></script>
    <script>
        $(window).load(function(){
            showCtn();
        });
        function showCtn(){
            var dn=$(".dn");
            var txt=$(".txt-show");
            for(var i=0;i<dn.length;i++){
                $(dn[i]).click(function(){
                    var j=i;
                    return function(){
                        if($(txt[j]).css("height")=="65px"){
                            $(txt[j]).css("height","auto");
                        }else{
                            $(txt[j]).css("height","65px");}}
                }())
            }
        }
        function chooseAll(){
            $("input[name='studentId']").prop("checked","true");
        }
        function chooseNull(){
            $("input[name='studentId']").each(function(){
                if($(this).prop("checked"))
                {
                    $(this).removeAttr("checked");
                }
                else
                {
                    $(this).prop("checked","true");
                }
            })
        }
    </script>
	<script type="text/javascript">
		function backPage(){
			 if(confirm("是否返回主页列表？"))
				 {
				 	window.location.href = 'getCourseByTeacher_BPTeacherAction';
				 }
		}
		
		$(document).ready(function (){
		
			$("#submits").click(function(){
			
				var applyCheck = "";
				var applyUnCheck = "";
				var i = 1;
				$("input[name='studentId']:checked").each(function () {
				    applyCheck +=$(this).val();
				    if (i!=$("input[name='studentId']:checked").length)  applyCheck +=",";
				    i++;
				  });
				i = 1;
				$("input[name='studentId']").not("input:checked").each(function () {
					applyUnCheck += $(this).val();
					if (i!=$("input[name='studentId']").not("input:checked").length)  applyUnCheck +=",";
				    i++;
				  });
					
					
				$.post("applyBpSuccess_CheckApplyAction",{
					applyCheck:applyCheck,
					applyUnCheck:applyUnCheck,
					courseId:$("#courseId").val()
				},function(result) {
					
					if(result=="true") {
						alert("报名修改成功!");
						window.location.reload(true);
					}
				});
				
			});
		
		});
		
	</script>
</body>
</html>