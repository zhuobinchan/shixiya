<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/business/bootstrap/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/business/bootstrap/bootstrap.min.css">
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/business/bootstrap/jquery-1.10.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/business/bootstrap/bootstrap.min.js"></script>
	
    
</head>
<body class="container-fluid">
	<nav class="navbar navbar-default" role="navigation" style="height:100px">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img alt="实习吖" src="images/shixiya.jpg" class="img-circle" style="width: 85px;height: 85px">
                </a>
                <p class="navbar-text" style=" font-family:'微软雅黑'; font-size:36px; font-style:italic;margin-top: 25px"> 实  习  吖 </p>
            </div>
        </div>
    </nav>
    <form id="resumeFrom" role="form" action="${pageContext.request.contextPath}/submitResume_updateSubmitResume.action" method="post" enctype="multipart/form-data"  class="form-horizontal" style="margin-top:100px">

                <div class="form-group" >
                    <span class="help-block" style="margin-left: 100px;font-family:'微软雅黑'; font-size:25px;">核实应聘信息</span>
                </div>
                <div class="form-group" >
                    <label for="submitName" class="col-md-2 col-md-offset-2 control-label" style="font-size:18px;">姓名：</label>
                    <input type="hidden"  name="sid" value="${submit.student.id}">
                    <input type="hidden"  name="id" value="${submit.id}">
                    <div class="col-md-4">
                        <input type="text" class="col-md-2 form-control" id="submitName" name="submitName" placeholder="输入姓名..." value="${submit.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="submitEmail" class="col-md-2 col-md-offset-2 control-label" style="font-size:18px;">邮箱：</label>
                    <div class="col-md-4">
                        <input type="email" class="col-md-2 form-control" id="submitEmail" name="submitEmail" placeholder="输入邮箱..." value="${submit.email}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="submitPhone" class="col-md-2  col-md-offset-2 control-label" style="font-size:18px;">电话：</label>
                    <div class="col-md-4">
                        <input type="text" class=" col-md-2 form-control"  id="submitPhone" name="submitPhone" placeholder="输入电话..." value="${submit.telephone}">
                    </div>
                </div>
                <div class="form-group" >
                    <span class="help-block" style="margin-left: 100px;font-family:'微软雅黑'; font-size:25px;">完善简历</span>
                </div>
                <div class="form-group" >
                    <label class="col-md-2  col-md-offset-2 control-label" style="font-size:18px;">简历上传：</label>
                    <input id="upFile" name="word" type="file" style="display:none" accept=".doc"   >
                    <div class="input-append" >
                        <a id="btn" class="btn btn-primary" style="color: #5e5e5e" >Browse</a>
                        <input  id="show" type="text" style="width: 200px;height:32px; border: 1px solid #ccc;border-radius: 4px;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;color: #555;">
                    </div>
                </div>
                <div class="form-group" >
                    <span class="help-block col-md-2  col-md-offset-4">请上传word文档的简历</span>
                </div>
                <div class="form-group" >
                    <div class="col-md-4  col-md-offset-4">
                         <button  id="submitBtn" type="button" class="btn btn-primary btn-lg btn-block">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
                    </div>
                </div>
    </form>
	
</body>
<script>

    $(function() {

        setTimeout(function(){
            $("#submitBtn").removeAttr("disabled");
            $("#show").val("");
        },100);
    });
    $("#btn").click(function(){
        $("input[id=upFile]").click();
    });

    $("#upFile").change(function(){
        var name = $(this).val();
        var index = name.indexOf(".");
        var houzhi =name.substring(index);
        if(houzhi==".doc"){
         	$("#submitBtn").removeAttr("disabled");
        	$("#show").val($(this).val());
        }else{
        	alert("请输入.doc后缀的word文档");
        	$("#show").val($(this).val());
        	$("#submitBtn").attr("disabled","disabled");
        }
        
    });

    $("#submitBtn").click(function(){
    	
    	$("#resumeFrom").submit();
        $("#submitBtn").attr("disabled","disabled");
    })

</script>
</html>
