$("#ulC").click(function (){
	$.post("appliedCpByAjax_student.action", 
	function(result) {
		setContent(result);
	}, "json");
	
	
});

$("#ulB").click(function (){
	$.post("appliedBpByAjax_student.action", 
	function(result) {
		setContentBP(result);
	}, "json");
	
	
});





function setContent(result){
	var ul = $("#ulAs");
	$("#ulAs").html("");
	for(var i=0;i<result.length;i++){
		ul.append('<li id="li'+i+'"></li>');
		var li = $("#li"+i);
//		li.append('<a id="liA'+i+'" href="enterCpChat_chat?roomId=cp'+result[i].id.cProductBroadcast.id+'"></a>');
		li.append('<a id="liA'+i+'" href="judgeApplyStatus_cp?broadcastId='+result[i].id.cProductBroadcast.id+'"></a>');
		
		var a = $("#liA"+i);
		a.append('<div class="img-show">'
                +'<img src="/shixiya/images/'+result[i].id.cProductBroadcast.imgUrl+'">'
                +'</div>');
		a.append('<div class="txt-show">'
	            +'<p class="s-title">'+result[i].id.cProductBroadcast.name+'</p>'
	            +'<p class="txt2">'+result[i].id.cProductBroadcast.introduction+'</p>'
	            +'<p class="txt2-footer">'
	            +'<span>'+formatDate(result[i].id.cProductBroadcast.startTime, "yyyy/MM/dd")+'</span>'
	            +'&nbsp;<span>参与人数</span>'
	            +'</p>'
	            +'</div>');
		
	}
	
	
}

function setContentBP(result){
	var ul = $("#ulAs");
	$("#ulAs").html("");
	for(var i=0;i<result.length;i++){
		ul.append('<li id="li'+i+'"></li>');
		var li = $("#li"+i);
//		li.append('<a id="liA'+i+'" href="enterBpChat_chat?roomId=bp'+result[i].id.bProductCourse.id+'"></a>');
		li.append('<a id="liA'+i+'" href="judgeApplyStatus_bp?courseId='+result[i].id.bProductCourse.id+'"></a>');		
		var a = $("#liA"+i);
		a.append('<div class="img-show">'
                +'<img src="/shixiya/images/'+result[i].id.bProductCourse.imageUrl+'">'
                +'</div>');
		a.append('<div class="txt-show">'
	            +'<p class="s-title">'+result[i].id.bProductCourse.title+'</p>'
	            +'<p class="txt2">'+result[i].id.bProductCourse.introduction+'</p>'
	            +'<p class="txt2-footer">'
	            +'<span>'+formatDate(result[i].id.bProductCourse.startTime, "yyyy/MM/dd")+'</span>'
	            +'&nbsp;<span>参与人数</span>'
	            +'</p>'
	            +'</div>');
		
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

