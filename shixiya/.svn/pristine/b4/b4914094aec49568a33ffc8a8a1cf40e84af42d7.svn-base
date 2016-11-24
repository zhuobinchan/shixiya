

$("#bossLi").click(function (){
	$.post("getBroadcastByAjax_CPHrAction.action", 
	function(result) {
		setBroadcastContent(result);
	}, "json");
	
	
});

$("#hrLi").click(function (){
	
	$.post("getHrListByAjax_CPHrAction.action", 
	function(result) {
		setHrWeekContent(result);
	}, "json");
	
	
});




function setBroadcastContent(result){
	var ul = $(".tuwen-model");
	$(".tuwen-model").html("");
	
	for(var i=0;i<result.length;i++){
		
		ul.append('<li><a href="enterCpChat_chat.action?roomId=Hr1994cp'+result[i].id+'&role=hr">'
				 +'<div class="img-show">'
				 +'<img src="/shixiya/'+result[i].imgUrl+'">'
				 +'</div>'
				 +'<div class="txt-show">'
				 +'<p class="s-title">'+result[i].name+'</p>'
				 +'<p class="txt2">'+result[i].introduction+'</p>'
				 +'<p class="txt2-footer">'
				 +'<span>'
				 +'<a style="color: #2596c2;font-size: 8px"'
				 +'href="showQuestion_CPHrCheckAnswerAction?broadcastId='+result[i].id+'">查看答案</a>'
				 +'</span>'
				 +'<span><a style="color: #2596c2;font-size: 8px" href="findStuApply_CheckApplyAction?broadcastId='+result[i].id+'">报名审核</a></span>'
				 +'</span> <span style="color: #2596c2;font-size: 8px">参与人数：'+result[i].participation+'</span>'					
				 +'</p></div></a></li>');
		
	}
}

function setHrWeekContent(result){
	var ul = $(".tuwen-model");
	$(".tuwen-model").html("");
	
	for(var i=0;i<result.length;i++){
		
		ul.append('<li><a href="enterStuHrChat_chat?roomId=Hr1994sh'+result[i].id+'&role=hr">'
				 +'<div class="img-show">'
				 +'<img src="/shixiya/'+result[i].imageUrl+'">'
				 +'</div>'
				 +'<div class="txt-show">'
				 +'<p class="s-title">'+result[i].title+'</p>'
				 +'<p class="txt2">'+result[i].introduction+'</p>'
				 +'<p class="txt2-footer">'
				 +'</p></div></a></li>');
		
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


