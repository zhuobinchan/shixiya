function showChild(trainWeId){
	$.post("getAllTrainWEChildAjax_TrainWEAction.action",{
		trainWeId:trainWeId
	}, function(result) {
		setTrainWEChildInfo(result);

		$("#overChild").slideDown(500);
		if(result.length==null || result.length==0){
			var tuwen_lis = $("#overChild-ul");
			tuwen_lis.append("<img src='/shixiya/omg/images/nodata.jpg' style='height:100%;width:100%'/>");
		}
		
	}, "json");
	
	
	
	function setTrainWEChildInfo(trainWEProduct) {
		var tuwen_lis = $("#overChild-ul");
		tuwen_lis.html("");

		for (var i = 0; i < trainWEProduct.length; i++) {
			
			var listViewContent = trainWEProduct[i].listViewContent;
			if(listViewContent==null) listViewContent="";
			
//			var listViewContent_removeEnter = listViewContent.replace(/[\r\n]/g,"</br>");
//			.append('<li><a href="getTrainWEById_TrainWEAction?trainWeId='
//					+ trainWEProduct[i].id
			tuwen_lis
					.append('<li><a href="getTrainWEChildById_TrainWEAction?trainWeChildId='
					+ trainWEProduct[i].id+'">'
					+ '<div class="img-show">'
							+ ' <img src="/shixiya/'
							+ trainWEProduct[i].imgUrl
							+ '">'
							+ '</div>'
							+ ' <div class="txt-show" style="text-align:left">'
							+ '     	<p class="s-title">'
							+ trainWEProduct[i].title
							+ '</p>'
							+ '   	<p class="txt2">'
							+ listViewContent
							+ '</p>'
							+ '   	<p class="txt2-footer">'
							+ '      	<i class="time-icon"></i><span>'
							+ judgeApplyStatus(
									trainWEProduct[i].startTime,
									trainWEProduct[i].endTime)
							+ '</span>'
							+ '      	<i class="num-icon"></i><span>'
							+ trainWEProduct[i].visitnum
							+ '</span>'
							+ '   	</p>'
							+ ' </div>' + '</a></li>');

		}

	}
}


$(document).ready(function() {
					var AjaxPage = 1;
					/*点击div外的隐藏掉该div*/
					$("body").click(function (e) {
						if (!$(e.target).closest("#overChild").length) {
							$("#overChild").slideUp(500);
						}
					});
					$("#close_btn").click(function(){
						$("#overChild").slideUp(500);
					})
					
					
					/*培训we课堂初始数据渲染*/
					$.post("getAllTrainWEAjax_TrainWEAction.action",{
						AjaxPage:AjaxPage
					}, function(
							result) {
						var tuwen_lis = $("#tuwen-lis");
						tuwen_lis.html("");
						setTrainWEInfo(result);
					}, "json");
					
					$.post("getLableAjax_TrainWEAction.action", function(
							result) {
						var selectLableFilter = $("#selectLableFilter");
						for(var i = 0;i<result.length;i++){
							selectLableFilter.append("<option value='"+result[i].lableName+"'>"+result[i].lableName+"</option>");
						}
					}, "json");
					
					/*培训we课堂广告初始数据渲染*/
					$.post("getTrainWEAdvAjax_TrainWEAction.action", function(
							result) {
						setTrainWEAdvInfo(result);
						var swiper = new Swiper('.swiper-container', {
							pagination : '.swiper-pagination',
							paginationClickable : true,
							autoplayDisableOnInteraction : false,// 操作后可以继续轮播
							loop : true,
							speed : 800, // 切换速度
							autoplay : 3000
						// 自动切换时间
						});
					}, "json");
					
					
					//根据标签查找培训we课堂
					$("#selectLableFilter").change(
						function getTrainWEBylableFilter(){
							var lableName = $("#selectLableFilter").val();
							AjaxPage=1;
							$.post("lableFilter_TrainWEAction.action",
									{
										lableName : lableName,
										AjaxPage:AjaxPage
									},
									 function(result) {
										var tuwen_lis = $("#tuwen-lis");
										tuwen_lis.html("");
										setTrainWEInfo(result);
							}, "json");
					});
					//根据时间查找培训we课堂
					$("#selectTimeQuantumFilter").change(
						function getTrainWeByTimeQuantumFilter(){
							var timeQuantum = $("#selectTimeQuantumFilter").val();
							AjaxPage=1;
							$.post("timeQuantumFilter_TrainWEAction.action",
									{
										timeQuantum : timeQuantum,
										AjaxPage:AjaxPage
									},
									 function(result) {
										var tuwen_lis = $("#tuwen-lis");
										tuwen_lis.html("");
										setTrainWEInfo(result);
							}, "json");
					});
					
					

					/* 分页加载数据 */
					$("#refleshMessage").click(function refleshMessage() {
						AjaxPage++;
						if ($("#selectTimeQuantumFilter").val() != 3650) {
							$.post("timeQuantumFilter_TrainWEAction.action", {
								timeQuantum : $("#selectTimeQuantumFilter").val(),
								AjaxPage : AjaxPage
							}, function(result) {
								var tuwen_container = $("#tuwen-lis");
								setTrainWEInfo(result);
							}, "json");
						} else if ($("#selectLableFilter").val() != "") {
							$.post("lableFilter_TrainWEAction.action", {
								lableName : $("#selectLableFilter").val(),
								AjaxPage : AjaxPage
							}, function(result) {
								var tuwen_container = $("#tuwen-lis");
								setTrainWEInfo(result);
							}, "json");
						} else {
							$.post("getAllTrainWEAjax_TrainWEAction.action", {
								AjaxPage : AjaxPage
							}, function(result) {
								var tuwen_container = $("#tuwen-lis");
								setTrainWEInfo(result);
							}, "json");
						}
					});
					

					function setTrainWEInfo(trainWEProduct) {
						var tuwen_lis = $("#tuwen-lis");

						for (var i = 0; i < trainWEProduct.length; i++) {
							
							var listViewContent = trainWEProduct[i].listViewContent;
							if(listViewContent==null) listViewContent="";
							
//							var listViewContent_removeEnter = listViewContent.replace(/[\r\n]/g,"</br>");
//							.append('<li><a href="getTrainWEById_TrainWEAction?trainWeId='
//									+ trainWEProduct[i].id
							tuwen_lis
									.append('<li><a href="javascript:void(0)" onclick="showChild('+trainWEProduct[i].id+')">'
											+ '<div class="img-show">'
											+ ' <img src="/shixiya/'
											+ trainWEProduct[i].imgUrl
											+ '">'
											+ '</div>'
											+ ' <div class="txt-show">'
											+ '     	<p class="s-title">'
											+ trainWEProduct[i].title
											+ '</p>'
											+ '   	<p class="txt2">'
											+ listViewContent
											+ '</p>'
											+ '   	<p class="txt2-footer">'
											+ '      	<i class="time-icon"></i><span>'
											+ judgeApplyStatus(
													trainWEProduct[i].startTime,
													trainWEProduct[i].endTime)
											+ '</span>'
											+ '      	<i class="num-icon"></i><span>'
											+ trainWEProduct[i].visitnum
											+ '</span>'
											+ '   	</p>'
											+ ' </div>' + '</a></li>');

						}

					}

					function setTrainWEAdvInfo(trainWEAdvs) {
						var swiper_wrapper = $(".swiper-wrapper");
						swiper_wrapper.html("");
						for (var i = 0; i < trainWEAdvs.length; i++) {
							swiper_wrapper
									.append('<img class="swiper-slide" src="/shixiya/'
											+ trainWEAdvs[i].imageUrl + '">');
						}
					}
})

$(document).ready(function() 
{ 
	
//	var clicked = "Nope.";
//    var mausx = "0";
//    var mausy = "0";
//    var winx = "0";
//    var winy = "0";
//    var difx = mausx - winx;
//    var dify = mausy - winy;
//
//    $("html").mousemove(function (event) {
//        mausx = event.pageX;
//        mausy = event.pageY;
//        winx = $("#overChild").offset().left;
//        winy = $("#overChild").offset().top;
//        if (clicked == "Nope.") {
//            difx = mausx - winx;
//            dify = mausy - winy;
//        }
//
//        var newx = event.pageX - difx - $("#overChild").css("marginLeft").replace('px', '');
//        var newy = event.pageY - dify - $("#overChild").css("marginTop").replace('px', '');
//        $("#overChild").css({ top: newy, left: newx });
//
//        $(".container").html("Mouse Cords: " + mausx + " , " + mausy + "<br />" + "Window Cords:" + winx + " , " + winy + "<br />Draggin'?: " + clicked + "<br />Difference: " + difx + " , " + dify + "");
//    });
//
//    $(".pew").mousedown(function (event) {
//        clicked = "Yeah.";
//    });
//
//    $("html").mouseup(function (event) {
//
//        clicked = "Nope.";
//    });
	
	
//	function getOffsetSum(ele){
//		   var top= 0,left=0;
//		   while(ele){
//		       top+=ele.offsetTop;
//		       left+=ele.offsetLeft;
//		       ele=ele.offsetParent;
//		   }
//		 /*  alert(left+" : "+top);*/
//		   return { top:top, left:left }
//		}
//		        var maindiv=document.getElementById("pew");
//		        var movediv = document.getElementById("overChild");
//		maindiv.addEventListener("touchmove",touch,false);
//		maindiv.addEventListener("touchstart",touch,false);
//		maindiv.addEventListener("touchend",touch,false);
//		var firstVal_x = 0;
//		var firstVal_y = 0;
//		function touch(e)
//		{
//		switch(e.type)
//		{
//		case "touchmove":
//		var ele=getOffsetSum(e.target);
//		var left=ele.left;
//		var top=ele.top;
//		
//		var xx= $("#overChild").css("marginLeft").replace('px', '');
//		var yy= $("#overChild").css("marginTop").replace('px', '');
//		
//		var x=e.touches[0].pageX - firstVal_x;
//		var y=e.touches[0].pageY - firstVal_y;
//		
//		var newX = Number(xx)+Number(x);
//		var newY = Number(yy)+Number(y);
//		
//		e.preventDefault();
//		// alert(e.target.id);
//		movediv.style.marginLeft=Number(xx)+Number(x)+"px";
//		movediv.style.marginTop=Number(yy)+Number(y)+"px";
//
//		/* $(e.target).css("left","50px");
//		 $(e.target).css("top","50px");*/
//		case "touchstart":		
//
//			firstVal_x = e.touches[0].pageX;
//			firstVal_y = e.touches[0].pageY;
//		case "touchend":		
//
//			firstVal_x = 0;
//			firstVal_y = 0;
//			
//		}
//		
//		}	
	
	
	
	
	
}) 



					/* 判断课程进行状态 */
					function judgeApplyStatus(startTime, endTime) {
						var now = new Date();

						var start = new Date(startTime.replace("-", "/")
								.replace("-", "/"));
						var end = new Date(endTime.replace("-", "/").replace(
								"-", "/"));

						if (now < start) {
							return "培训没有开始";
						}
						if (start < now && now < end) {
							return "培训正在进行";
						}
						if (end < now) {
							return "培训已经结束";
						}
					}