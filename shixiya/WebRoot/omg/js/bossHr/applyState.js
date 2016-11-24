function enterCpBroadcast(id) {
	$
			.post(
					"broadcastApplyEnter_ApplyEnterAction.action",
					{
						broadcastId : id
					},
					function(result) {

						if (result.error) {
							alert(result.message);
						}
						if (result.success == "true") {
							if (result.isAnswer == "true") {
								alert("您已经回答问题,可以进入直播间");
								window.location.href = 'enterCpChat_chat?roomId=cp'
									+ result.broadcastId;
								
							} else {
								alert("进入直播间前,请先完成问题");
								window.location.href = 'getQuestionsByBroadcast_CPStudentQuestionAction.action?broadcastId='
										+ result.broadcastId + '';
							}
						}
						if (result.success == "false") {
							alert(result.message);
							window.location.href = 'enterCpChat_chat?roomId=cp'
								+ result.broadcastId;
						}
					}, "json");

}

function enterBpCourse(courseId, videoId) {
	$.post("courseApplyEnter_ApplyEnterAction.action", {
		courseId : courseId,
		videoId : videoId
	}, function(result) {

		if (result.error) {
			alert(result.message);
		}
		if (result.success == "true") {
			alert(result.message);
			window.location.href = 'enterBpChat_chat?roomId=bp'
					+ result.courseId;
		}
		if (result.success == "false") {
			alert(result.message);
			window.location.href = 'enterBpChat_chat?roomId=bp'
				+ result.courseId;
		}
	}, "json");

}

function getanswer() {

	var array = new Array();
	var answers = $(".answerList");

	for (var i = 0; i < answers.size(); i++) {
		var data = {
			answer : "",
			titleId : ""
		};
		data.answer = answers[i].value;
		data.titleId = answers[i].getAttribute("titleId");

		array[i] = data;
	}

	$.post("getAnswerByStudent_CPStudentQuestionAction.action", {
		answer : JSON.stringify(array),
		broadcastId : $("#broadcastId").val()
	}, function(result) {
		if(result.message!=null){
			alert(result.message);
		}
		window.location.href = 'enterCpChat_chat?roomId=cp'
				+ result.broadcastId;
	}, "json");

}

function sendAnswerTimer() {
	var time = $("#limittime").attr("limittime");
	window.setTimeout(getanswer, 60000 * time);
}

/*查找简历时loading界面*/
var loading=function(){
	$("body").append('<div class="loading"><span>正在为您查找简历中,loading...</span></div>');
//    var btnAttend=$(".blue-btn");
//    if(btnAttend){
//        $.each(btnAttend,function(){
//            $(this).click(function(){
//                $("body").append('<div class="loading"><span>正在为您查找简历中,loading...</span></div>');
////                 $(".loading").click(function(){
////                     $(this).remove();
////                 });
//            });
//        });
//    }
};
//loading();

var isExistResume = function(btn){
	var status = $("#enter").attr("status");
	var btn = btn;
	if(status=="applyWithoutResume"){
		if($("#broadcastId").val()==null){
			isExistResume_bp();
		}else {
			isExistResume_cp();
		}
	}
	
	
	function isExistResume_cp(){
		loading();
		
		$.post("judgeResumeForApply_cp.action"
		,{
			broadcastId : $("#broadcastId").val()
		},function(result) {
			var result = eval("("+result+")");
			$(".loading").remove();
			
			var isResume = result.isResume;
			var applyStatus = result.applyStatus;
			var broadcastId = result.broadcastId;
			
			if(isResume=="false" && btn=="resumeBtn"){
				window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e2d9fc537885b56&redirect_uri=http://www.hotjob.cn/wt/highsun/mobweb/login&response_type=code&scope=snsapi_base&state=interns;;;;0/1227/100901#wechat_redirect';
			}
			
			if(applyStatus=="waitingApply"){
				$("#enter").attr("status","waitingApply");
				$("#enter").attr("onclick","reloadNow("+broadcastId+")");
				$("#enter").attr("class","green-btn");
				$("#enter").html("报名审核中,点击刷新");
			}
		});
	}
	
	function isExistResume_bp(){
		loading();
		
		$.post("judgeResumeForApply_bp.action"
		,{
			courseId : $("#courseId").val()
		},function(result) {
			var result = eval("("+result+")");
			$(".loading").remove();
			
			var isResume = result.isResume;
			var applyStatus = result.applyStatus;
			var courseId = result.courseId;
			
			if(isResume=="false" && btn=="resumeBtn"){
				window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e2d9fc537885b56&redirect_uri=http://www.hotjob.cn/wt/highsun/mobweb/login&response_type=code&scope=snsapi_base&state=interns;;;;0/1227/100901#wechat_redirect';
			}
			
			if(applyStatus=="waitingApply"){
				$("#enter").attr("status","waitingApply");
				$("#enter").attr("onclick","reloadNow("+courseId+")");
				$("#enter").attr("class","green-btn");
				$("#enter").html("报名审核中,点击刷新");
			}
		});
	}
	
}
isExistResume();




//function judgeOurDataBaseResume(id,pType){
//	
//	$.post("judgeOurDataBaseResume_resume.action"
//	, function(result) {
//		var resultStr = result.toString() ;
//		
//		if(resultStr.trim()=="true"){
//			
//			alert()
//			window.location.href = addWhichApply+"?"+pTypeId+"="+id;
//			
//		}else if(resultStr.trim()=="false"){
//			
//			alert("您尚未有简历，请前往填写简历");
//			window.location.href = "judgeApplyStatus_cp";
//			
//		}else alert("error");
//		
//		
//	});
//}
//
//
//
//function judgeResume(id,pType){
//	
//	var pTypeId = null;
//	var addWhichApply = null;
//	if(pType=="cp"){
//		pTypeId="broadcastId"; 
//		addWhichApply="Cp";
//	}else if(pType=="bp"){
//		pTypeId="courseId";
//		addWhichApply="Bp";
//	}
//	$.post("judgeResumeIsExist.action"
//	, function(result) {
//		var resultStr = result.toString() ;
//		
//		if(resultStr.trim()=="true"){
//			
//	        $(".loading").remove();
//			window.location.href = addWhichApply+"?"+pTypeId+"="+id;
//			
//		}else if(resultStr.trim()=="false"){
//			
//			alert("您尚未有简历，请前往填写简历");
//	        $(".loading").remove();
//			window.location.href = "/shixiya/omg/html/personal/information.jsp?pType="+pType+"&"+pTypeId+"="+id;
//			
//		}else alert("error");
//		
//		
//	});
//}