var AjaxPage = 1;
$(window).load(function() {
	showPreferenceSelect();
});
function refleshMessage() {
	AjaxPage++;
	$.post("getPSByPageBean_PreferenceSelectAction.action", {
		AjaxPage : AjaxPage
	}, function(result) {
		var ul = $(".tuwen-lis");
		var contents = result.contents;
		for (var i = 0; i < contents.length; i++) {
			
			ul.append('<li><a href="showDetail_PreferenceSelectAction?id='+contents[i].id+'">'
		            +'<div class="img-show">'
		            +'<img src="/shixiya/'+contents[i].workLogo+'">'
		            +'</div>'
		            +'<div class="txt-show">'
		            +'<p class="s-title">'+contents[i].workName+'</p>'
		            +'<p class="txt2">'+contents[i].workIntroduction+'</p>'
		            +'<p class="txt2-footer">'
		            +'<i class="time-icon"></i><span>'+contents[i].publishtime+'</span>'
		            +'<i class="num-icon"></i><span>'+contents[i].visitNum+'</span>'
		            +'</p>'
		            +'</div>'
		            +'</a></li>');
		}
	}, "json");
}
function showPreferenceSelect(){
	
	
	$.post("showAll_PreferenceSelectAction.action", {
		AjaxPage : AjaxPage
	}, function(result) {
		
		var ul = $(".tuwen-lis");
		var div_adv =$(".swiper-wrapper");
		
		var contents = result.contents;
		var advs = result.advs;
		
		for(var i = 0;i < advs.length;i++){
			div_adv.append('<img class="swiper-slide" src="/shixiya/'+advs[i].imageUrl+'" '
					+'onclick="javascript:window.location.href=\'showDetail_PreferenceSelectAction?id='+advs[i].targetUrl+'\'">');
			
		}
		
		for (var i = 0; i < contents.length; i++) {
			
			ul.append('<li><a href="showDetail_PreferenceSelectAction?id='+contents[i].id+'">'
		            +'<div class="img-show">'
		            +'<img src="/shixiya/'+contents[i].workLogo+'">'
		            +'</div>'
		            +'<div class="txt-show">'
		            +'<p class="s-title">'+contents[i].workName+'</p>'
		            +'<p class="txt2">'+contents[i].workIntroduction+'</p>'
		            +'<p class="txt2-footer">'
		            +'<i class="time-icon"></i><span>'+contents[i].publishtime+'</span>'
		            +'<i class="num-icon"></i><span>'+contents[i].visitNum+'</span>'
		            +'</p>'
		            +'</div>'
		            +'</a></li>');
			
		}
	    var swiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        paginationClickable: true,
	        autoplayDisableOnInteraction: false,//操作后可以继续轮播
	        loop:true,
	        speed:800,      //切换速度
	        autoplay:3000   //自动切换时间
	    });
		
	}, "json");

	
}