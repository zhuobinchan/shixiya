var AjaxPage = 1;

$("#bossLi").click(function (){
	$.post("getBroadcastByAjax_cp.action",{
		AjaxPage : AjaxPage
	} ,
	function(result) {
		$(".tuwen-lis").html("");
		setBroadcastContent(result);
	}, "json");
	
	
});

function refleshMessage() {
	AjaxPage++;
	$.post("getBroadcastByAjax_cp.action", {
		AjaxPage : AjaxPage
	}, function(result) {
		setBroadcastContent(result);
	}, "json");
}


$("#hrLi").click(function (){
	$.post("getHrCommentListByAjax_stuhr.action", 
	function(result) {
		setBroadcastContent(result);
	}, "json");
	
	
});

function getLableFilterBylableName (lableName){
	$.post("lableFilter_cp.action",{
		lableName:lableName
	}, 
	function(result) {
		setBroadcastContent(result);
	}, "json");
	
	
}

/*通过aJax获取hr周点评信息*/
function getHrListByAjax(){
	$.post("getHrCommentListByAjax_stuhr.action", 
			function(result) {
				setHrListContent(result);
			}, "json");
}

/* 通过aJax获取BOSS直播间信息 */
function getBroadcastByAjax() {
	$.post("getBroadcastByAjax_cp.action", {
		AjaxPage : AjaxPage
	}, function(result) {
		$(".tuwen-lis").html("");
		setBroadcastContent(result);
	}, "json");
}



function setBossLable(lables){
	var tb = $(".tb");
	$(".tb").html("");
	var td = "";
	for(var i=0;i<=lables.length;i++){
		
		td = td+"<td>"+lables[i].lableName+"</td>";
		
		if((i+1)%4==0){
			tb.append("<tr>"+td+"</tr>");
			td = "";
			
		}if(i == lables.length-1) tb.append("<tr>"+td+"</tr>");
			
	}
	
}

function setBroadcastContent(result){
	var main = $(".tuwen-lis");
	for(var i=0;i<result.length;i++){
		
		main.append('<li><a href="judgeApplyStatus_cp.action?broadcastId='+result[i].id+'">'
                	+'<div class="img-show">'
                	+'<img src="/shixiya/'+result[i].imgUrl+'">'
                	+'</div>'
                	+'<div class="triangle">'
                	+'<i class="'+result[i].lablePaste.cssClassName+'"></i>'
                	+'</div>'
                	+'<div class="txt-show">'
                	+'<p class="s-title">'+result[i].name+'</p>'
                	+'<p class="txt2">'+result[i].introduction+'</p>'
                	+'<p class="txt2-footer">'
                	+'<i class="time-icon"></i><span>'+judgeApplyStatus(result[i].startTime,result[i].endTime)+'</span>'
                	+'<i class="num-icon"></i><span>'+result[i].visitnum+'</span>'
                	+'</p></div></a></li>');
		
	}
	
	
}

function setHrListContent(result){
	var main = $(".swiper-wrapper");
	$(".swiper-wrapper").html("");
	
	for(var i=0;i<result.length;i++){
		
		main.append('<div class="swiper-slide">'
                +'<img src="/shixiya/'+result[i].imageUrl+'" width="100%" height="100%" alt="面试干货">'
                +'<div class="cover-txt">'
                +'<h3>'+result[i].title+'</h3>'
                +'<p>'+result[i].introduction+'</p>'
                +'</div></div>');
		
	}
	
	
}

/*判断课程进行状态*/
function judgeApplyStatus(startTime,endTime){
	var now=new Date();
	
	var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	var end = new Date(endTime.replace("-", "/").replace("-", "/"));  
	
	if(now<start){
		return "直播间正在报名";
	}
	if (start<now && now<end) {
		return "直播间正在进行";
	}
	if(end<now){
		return "直播间已经结束";
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


