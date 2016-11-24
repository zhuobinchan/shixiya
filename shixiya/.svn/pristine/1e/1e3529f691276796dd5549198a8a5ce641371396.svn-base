


$(document).ready(function(){
	var AjaxPage = 1;
	$.post("getRecruitmentLableByAjax_RecruitAction", 
			function(result) {
		
				setRecruitMentLables(result);
				
		        $(function(){
		            var tr=$(".tb div");
		            tr.click(function(){
		                tr.removeClass("visited2");
		                $(this).addClass("visited2");
		                AjaxPage = 1;
		            });
		        })
		        
		        findRecruitByLable(AjaxPage);
		        
	}, "json");
	
	
	$.post("getAllRecruitmentByAjax_RecruitAction",{
		AjaxPage:AjaxPage
	} ,
	function(result) {
		$(".recruit_ul").html("");
		setRecruitMents(result);
	}, "json");
	
	/*添加招聘岗位*/
	function setRecruitMents(recruitMents){
		var recruit_ul = $(".recruit_ul");
		
		
		for(var i=0;i<recruitMents.length;i++){
			recruit_ul.append('<li><img src="/shixiya/'+recruitMents[i].logoUrl+'" width="65px" height="65px" style="float:left;margin-right:10px;border-radius:10px;"/><a href="getRecruitmentsById_RecruitAction?recruitId='+recruitMents[i].id+'">'
			        +'<p class="s-title">'+recruitMents[i].name+' <span class="day-num"><b>'+recruitMents[i].salary+'</b></span></p>'
			        +'<p class="txt3">'+recruitMents[i].area+'&nbsp;/&nbsp;'+recruitMents[i].workingDay+'</p>'
			        +'<p class="txt2-2">'
			        +'<span>'+recruitMents[i].positionType+'</span>'
			        +'<span style="width:50px">'+recruitMents[i].advantage+'</span></p>'
			        +'<p class="txt3 tr">'+recruitMents[i].publishTime+'</p></a></li>');
		}
		
	}
	
	
	/*添加分类标签*/
	function setRecruitMentLables(lables){
		
		$(".tb").html();
		var div = $(".tb");

		div.append('<div><a href="javascript:void(0)" class="lableName_class" value="">全部行业</a></div>');
		for(var i =0;i < lables.length ;i++){
			div.append('<div><a href="javascript:void(0)" class="lableName_class" value="'+lables[i].lableName+'">'+lables[i].lableName+'</a></div>');
		}
		
	}
	
	function findRecruitByLable(AjaxPageIndex){
		$(".lableName_class").click(function(){
			
			var lableName = $(this).attr("value");
			
			$.post("getRecruitmentsByLable_RecruitAction",{
				lableName:lableName,
				AjaxPage : AjaxPageIndex
			},function(result) {
				$(".recruit_ul").html("");
				setRecruitMents(result);
			}, "json");
			
		});

	}
	
	$("#refleshMessage").click(function(){
		AjaxPage++;
		var lableName = $(".visited2 a").attr("value");
		$.post("getRecruitmentsByLable_RecruitAction",{
			lableName:lableName,
			AjaxPage : AjaxPage
		},function(result) {
			setRecruitMents(result);
		}, "json");
	});
	
})