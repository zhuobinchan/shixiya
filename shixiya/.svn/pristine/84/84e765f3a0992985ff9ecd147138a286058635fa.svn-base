var AjaxPage = 1;

$.post("showAllCourseAjax_bp.action", {
	AjaxPage : AjaxPage
}, function(result) {
	var tuwen_container = $("#tuwen-container");
	tuwen_container.html("");
	setCourseInfo(result);
}, "json");

function refleshMessage(){
	AjaxPage++;
	if($("#TimeQuantumFilter").val()!=3650){
		$.post("timeQuantumFilter_bp.action", {
			timeQuantum : $("#TimeQuantumFilter").val(),
			AjaxPage : AjaxPage
		}, function(result) {
			var tuwen_container = $("#tuwen-container");
			setCourseInfo(result);
		}, "json");
	}
	else if($("#LableFilter").val()!=""){
		$.post("lableFilter_bp.action", {
			lableName : $("#LableFilter").val(),
			AjaxPage : AjaxPage
		}, function(result) {
			var tuwen_container = $("#tuwen-container");
			setCourseInfo(result);
		}, "json");
	}
	else {
		$.post("showAllCourseAjax_bp.action", {
			AjaxPage : AjaxPage
		}, function(result) {
			var tuwen_container = $("#tuwen-container");
			setCourseInfo(result);
		}, "json");
	}
}

function getCourseBylableFilter(lableName) {
	AjaxPage=1;
	$.post("lableFilter_bp.action", {
		lableName : lableName,
		AjaxPage : AjaxPage
	}, function(result) {
		var tuwen_container = $("#tuwen-container");
		tuwen_container.html("");
		setCourseInfo(result);
	}, "json");
}

function getCourseByTimeQuantumFilter(timeQuantum) {
	AjaxPage=1;
	$.post("timeQuantumFilter_bp.action", {
		timeQuantum : timeQuantum,
		AjaxPage : AjaxPage
	}, function(result) {
		var tuwen_container = $("#tuwen-container");
		tuwen_container.html("");
		setCourseInfo(result);
	}, "json");
}


function setCourseInfo(bpCourse){
	var tuwen_container = $("#tuwen-container");
	for(var i=0;i<bpCourse.length;i++){
		tuwen_container.append("<li class=\"tuwen-model add-bd\">"
						+"<a href=\"judgeApplyStatus_bp?courseId="+bpCourse[i].id+"\">"
						+"	<img src='/shixiya/"+bpCourse[i].imageUrl+"'>"
						+"	<p class=\"title\">"+bpCourse[i].title+"</p>"
						+"	<p class=\"txt\">"+bpCourse[i].introduction+"</p>"
						+"	<p class=\"txt-footer\">"
						+"		<span class=\"fl\">"
						+"<img src=\"/shixiya/omg/images/tuwen/technology.png\">"+bpCourse[i].lable+"</span>"
						+"<span class=\"fr\">"
						+"<img src=\"/shixiya/omg/images/tuwen/date.png\">"
					
						+ 	judgeApplyStatus(bpCourse[i].startTime,bpCourse[i].endTime)		
						
						+"	</span>"
						+"	<span class=\"fr\">"
						+"	<img src=\"/shixiya/omg/images/tuwen/view.png\">"+bpCourse[i].visitnum
						+"	</span>"
						+"	</p>"
						+"</a>"
						+"</li>");

	}
}


/* 判断课程进行状态 */
function judgeApplyStatus(startTime,endTime){
	var now=new Date();
	
	var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	var end = new Date(endTime.replace("-", "/").replace("-", "/"));  
	
	if(now<start){
		return "课程正在报名";
	}
	if (start<now && now<end) {
		return "课程正在进行";
	}
	if(end<now){
		return "课程已经结束";
	}
}





$(".tuwen-container").scroll(function() {
	alert("到达底部！");
	var $this = $(this), viewH = $(this).height(), // 可见高度
	contentH = $(this).get(0).scrollHeight, // 内容高度
	scrollTop = $(this).scrollTop();// 滚动高度
	// if(contentH - viewH - scrollTop <= 100) { //到达底部100px时,加载新内容
	if (scrollTop / (contentH - viewH) >= 0.95) { // 到达底部100px时,加载新内容
		alert("到达底部！");
		// 这里加载数据..
	}
	alert("到达底部！");
});


