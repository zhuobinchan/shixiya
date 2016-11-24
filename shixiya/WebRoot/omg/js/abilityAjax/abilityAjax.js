$(window).load(function() {
	showAbilityLableName();

});

function showAbilityLableName() {
	$.post("getLableAjax_AbilityAction.action", function(result) {
		var chooseAbility = $("#chooseAbility");
		for (var i = 0; i < result.length; i++) {
			chooseAbility.append("<div class=\"ca\">" + result[i].lableName
					+ "</div>");
		}
		chooseAbility.append("<div><i class=\"abl-xtb\"></i>不超过3项</div>");
		clickAbilityLableName();

	}, "json");
}

function clickAbilityLableName() {
	$(".ca").click(
			function() {
				$(this).hasClass("chosen") ? $(this).removeClass("chosen") : $(
						this).addClass("chosen");
				var tip3 = $(".choose3");
				var m1 = $(".main1"), m2 = $(".main2"), m3 = $(".main3");

				tip3.click(function() {
					m1.css("display", "block").siblings(".model").css(
							"display", "none");
					window.location.href = "/shixiya/omg/html/curriculum/ability.jsp";
				});
			});
}

$(document).ready(function(){
	var tip1 = $("#choose1");
	var tip3 = $(".choose3");
	var m1 = $(".main1"), m2 = $(".main2"), m3 = $(".main3");
	tip3.click(function() {
		m1.css("display", "block").siblings(".model").css(
				"display", "none");
		window.location.href = "/shixiya/omg/html/curriculum/ability.jsp";
	});
	
	tip1.click(function() {
		if ($(".chosen").length <= 3 && $(".chosen").length >= 1) {

			var members = $(".chosen").get();
			var lableNames = "";
			$.each(members, function(i, item) {
				lableNames += item.innerHTML + ", ";
			});
			
			
//			showCourseFromLableByAjax(lableNames);
//			showBroadcastFromLableByAjax(lableNames);
//			m2.css("display", "block").siblings(".model").css(
//					"display", "none");
//			var mm = setTimeout(function() {
//				m3.css("display", "block").siblings(".model").css(
//						"display", "none");
//			}, 3000);
			
			window.location.href = "/shixiya/omg/html/curriculum/abilityShow.jsp?lableNames="+lableNames;

		} else {
			return false;
		}
	});

})


function showCourseFromLableByAjax(lableNames) {
	$.post("getbpCourseByAjax_AbilityAction.action", 
	{
		lableNames:lableNames
	},
	function(result) {
		setCourseInfo(result);
	}, "json");
}
function showBroadcastFromLableByAjax(lableNames) {
	$.post("getCpBroadcastByAjax_AbilityAction.action", 
	{
		lableNames:lableNames
	},
	function(result) {
		setBroadcastInfo(result);
	}, "json");
}

function setCourseInfo(bpCourse){
	var tuwen_model = $(".tuwen-model");
	
	tuwen_model.html("");
	for(var i=0;i<bpCourse.length;i++){
		tuwen_model.append(
				"<li><a href=\"judgeApplyStatus_bp?courseId="+bpCourse[i].id+"\">"
                +"<div class=\"img-show\">"
                +"<img src=\"/shixiya/"+bpCourse[i].imageUrl+ "\">"
                +" </div>"
                +" <div class=\"txt-show\">"
                +"    <p class=\"s-title\">"+bpCourse[i].title+"</p>"
                +"    <p class=\"txt2\">"+bpCourse[i].introduction+"</p>"
                +"    <p class=\"txt2-footer\">"
                +"       <i class=\"txt2-xtb\"></i>"
                +"        <span>"+formatDate(bpCourse[i].publicTime)+"</span>"
                +"       <i class=\"txt2-xtb2\"></i>"
                +"        <span>"+bpCourse[i].participation+"</span>"
                +"   </p>"
                +"</div>"
                +"</a></li>"
		);
	}
	
}

function setBroadcastInfo(cpBroadcast){
	var tuwen_model = $(".tuwen-model");
	
	for(var i=0;i<cpBroadcast.length;i++){
		tuwen_model.append(
				"<li><a href=\"judgeApplyStatus_cp?broadcastId="+cpBroadcast[i].id+"\">"
                +"<div class=\"img-show\">"
                +"<img src=\"/shixiya/"+cpBroadcast[i].imgUrl+ "\">"
                +" </div>"
                +" <div class=\"txt-show\">"
                +"    <p class=\"s-title\">"+cpBroadcast[i].name+"</p>"
                +"    <p class=\"txt2\">"+cpBroadcast[i].introduction+"</p>"
                +"    <p class=\"txt2-footer\">"
                +"       <i class=\"txt2-xtb\"></i>"
                +"        <span>"+formatDate(cpBroadcast[i].publicTime)+"</span>"
                +"       <i class=\"txt2-xtb2\"></i>"
                +"        <span>"+cpBroadcast[i].participation+"</span>"
                +"   </p>"
                +"</div>"
                +"</a></li>"
		);
	}
	
}



function formatDate(date, format) {   
    if (!date) return;   
    if (!format) format = "yyyy-MM-dd";   
    switch(typeof date) {   
        case "string":   
            date = new Date(date.replace(/-/, "/"));   
            break;   
        case "number":   
            date = new Date(date);   
            break;   
    }    
    if (!date instanceof Date) return;   
    var dict = {   
        "yyyy": date.getFullYear(),   
        "M": date.getMonth() + 1,   
        "d": date.getDate(),   
        "H": date.getHours(),   
        "m": date.getMinutes(),   
        "s": date.getSeconds(),   
        "MM": ("" + (date.getMonth() + 101)).substr(1),   
        "dd": ("" + (date.getDate() + 100)).substr(1),   
        "HH": ("" + (date.getHours() + 100)).substr(1),   
        "mm": ("" + (date.getMinutes() + 100)).substr(1),   
        "ss": ("" + (date.getSeconds() + 100)).substr(1)   
    };       
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
        return dict[arguments[0]];   
    });                   
}

