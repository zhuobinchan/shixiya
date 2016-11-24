	

	var voiceNum = 0;
	var ws = null; // 一个ws对象就是一个通信管道
	var voice = {
		localId : '',
		serverId : ''
	};

	var images = {
		localId : [],
		serverId : []
	};

	var messageString = {
		recordType : '',
		content : ''
	};

	function playRecord(serverId) {
		voice.serverId = serverId;
		downloadVioce();// 下载服务器上的录音
		if (voice.localId == '') {
			// alert('请先使用 startRecord 接口录制一段声音');
			return;
		}
		wx.playVoice({
			localId : voice.localId
		});
	}

	/* 封装Message成JSON传给后台 */
	function SetUserMessage(recordType, content) {

		messageString.recordType = recordType;
		messageString.content = content;

		return JSON.stringify(messageString);
	}
	
	/*判断字符创是否为url链接*/
	function CheckUrl(str) {
	    var RegUrl = new RegExp();
	    RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
	    if (!RegUrl.test(str)) {
	        return false;
	    }
	    return true;
	}

    //点击放大缩小图片
    var imgSc=function(){
        var imgsObj = $('.send-img img');//需要放大的图像

        if(imgsObj){
            $.each(imgsObj,function(){
                $(this).unbind("click").click(function(){
                    var currImg = $(this);
                    var tempContainer = $('<div class=tempContainer></div>');//图片容器
                    with(tempContainer){//width方法等同于$(this)
                        appendTo("body");
                        var windowWidth=$(window).width();
                        var windowHeight=$(window).height();
                        //获取图片原始宽度、高度
                        var orignImg = new Image();
                        orignImg.src =currImg.attr("src") ;
                        var currImgWidth= orignImg.width;
                        var currImgHeight = orignImg.height;
                    	if(currImgWidth<windowWidth){//为了让图片不失真，当图片宽度较小的时候，保留原图
                            if(currImgHeight<windowHeight){
                                html('<img border=0 src=' + currImg.attr('src') + '>');
                                css("padding-top",(windowHeight-currImgHeight)/2);         //增加这一句
                            }else{
                                css('top',0);
                                html('<img border=0 src=' + currImg.attr('src') + ' height='+windowHeight+'>');
                            }
                        }else{
                            var currImgChangeHeight=(currImgHeight*windowWidth)/currImgWidth;
                            if(currImgChangeHeight<windowHeight){
                                var topHeight=(windowHeight-currImgChangeHeight)/2;
                                if(topHeight>35){
                                    topHeight=topHeight-35;
                                    css('top',topHeight);
                                }else{
                                    css('top',0);
                                }
                                html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+';>');
                            }else{
                                css('top',0);
                                html('<img border=0 src=' + currImg.attr('src') + ' width='+windowWidth+'; height='+windowHeight+'>');
                            }
                        }
                    }
                    tempContainer.click(function(){
                        $(this).remove();
                    });
                });
            });
        }
        else{
            return false;
        }
    };
	
	/* 上传评论 */
	function saveComment(recordType, inputWord,sec) {
		$.post("saveCommentByAjax_chat.action", {
			inputWord : inputWord,
			roomId : $("#roomId").val(),
			stuName : $("#stuName").val(),
			recordType : recordType,
			headUrl : $("#headUrl").val(),
			roleId : $("#roleId").val(),
			duration : sec
		}, function(result) {

		});
	}

	/* 加载历史评论 */
	function getComment() {

		/* 获取当前页数 */
		var page = Number($("#getMore").attr("value"));

		$.post("getChatHistory_chat.action", {
			roomId : $("#roomId").val(),
			page : page
		}, function(result) {

			comments = eval("(" + result + ")");
			for (var i = 0; i < comments.length; i++) {

				addHistoryComment(comments[i]);

			}
			

		});
		/* 加载完数据后，设置当前页数加1 */
		page = page + 1;
		$("#getMore").attr("value", page);
	}

	function addHistoryComment(comment) {

		var chat = $(".chat-container");
		var getMore = $(".chat-container");

		var comment = comment;
		/* result.recordType：0为语音，1为文字 ,2为图片*/
		if (comment.type == "0") {
			
			var yw=parseInt(comment.duration)*2+80;
			
			getMore
					.prepend('<div class="chat-content"><img src="'
							+ comment.headUrl
							+ '" class="head2"><div class="content2"><p class="ni-name2">'
							+ comment.name
							+ '</p> '
							+' <div class="yy2"  style="width: '+yw+'px" >'
							+'<span class="scd scd2">'+comment.duration+'"</span>'
							+'<audio class="historyAudio" style="display:none" src="/shixiya/'+comment.localUrl+'"></audio>'
							+' <i class="weidu"></i></div>'
							+' <i class="r-img14"></i></div> </div>');
			
						/* 为历史聊天中非自己说的audio添加播放事件 */
						if(comment.roleId!=$("#roleId").val()){
									$(".yy2:first").click(function(){
										var audio = $(this).children("audio");
										var audio = audio[0];
			//							alert(audio.getAttribute("src"));
										if(audio.paused){
											/*播放当前的语音时暂停其他的语音播放*/
											var audios = $("audio");
											for(var i=0;i<audios.length;i++){
												var currentAudio = audios[i];
												currentAudio.pause();
												currentAudio.currentTime = 0;
											}
											audio.play();
										}else{
											audio.pause();
											audio.currentTime = 0;
										}
									})
						}
						
			// chat.scrollTop(chat[0].scrollHeight);
		} else if (comment.type == "1") {
			
			getMore.prepend('<div class="chat-content" id="chat-l">' + '<img src="'
					+ comment.headUrl + '" class="head2">'
					+ '<div class="content2">' + '<p class="ni-name2">'
					+ comment.name + '</p>' + '<div class="word4">'
					+ comment.content + '</div>' + '<i class="r-img14"></i>'
					+ '</div>' + '</div>');
			
        	if(CheckUrl(comment.content)){
        		$(".word4:first").html("<a href='"+comment.content+"'>"+comment.content+"</a>")
        	}
			
			
			// chat.scrollTop(chat[0].scrollHeight);
		}else if(comment.type == "2"){
			
			getMore.prepend('<div class="chat-content">'
					+ '<img src="'+comment.headUrl+'" class="head2">'
					+ '<div class="content2">'
					+ '<p class="ni-name2">'+comment.name+'</p>'
					+ '<div class="send-img send-img-left">'
					+ '<img class="wsImage" src="/shixiya/'+comment.localUrl+'"></div>'
					+ '<i class="r-img14"></i></div></div>');
			imgSc();		
		}
		/* 若评论的是是Hr或讲师js则，改变他们评论背景 */
		if (comment.role == "hr") {

			$(".ni-name2:first").append('<i class="hr"></i>');

		}else if (comment.role == "js" || comment.role == "th") {

			$(".ni-name2:first").append('<i class="js"></i>');

		}

		if(comment.roleId==$("#roleId").val()){
			$(".head2:first").removeClass("head2").addClass("head1");
			$(".content2:first").removeClass("content2").addClass("content");
			$(".ni-name2:first").removeClass("ni-name2").addClass("ni-name");

			if(comment.type == "1") {
				$(".word4:first").removeClass("word4").addClass("word3");
				$(".r-img14:first").removeClass("r-img14").addClass("r-img13");
			}
			
			if (comment.role == "hr" || comment.role == "js" || comment.role == "th") {
				
				if (comment.role == "hr") {
					$(".hr:first").remove();
					$(".ni-name:first").prepend('<i class="hr"></i>');
				}else {
					$(".js:first").remove();
					$(".ni-name:first").prepend('<i class="js"></i>');
				}
				
				if(comment.type == "0") {
					$(".yy2:first").removeClass("yy2").addClass("yy");
					$(".weidu:first").remove();
					$(".scd:first").removeClass("scd2").addClass("scd1");
					$(".r-img14:first").removeClass("r-img14").addClass("r-img13");
					/* 为历史聊天中自己说的audio添加播放事件 */
					$(".yy:first").click(function(){
						var audio = $(this).children("audio");
						var audio = audio[0];
//						alert(audio.getAttribute("src"));
						if(audio.paused){
							/*播放当前的语音时暂停其他的语音播放*/
							var audios = $("audio");
							for(var i=0;i<audios.length;i++){
								if(audios[i].getAttribute("src")!="/shixiya/null"){
									var currentAudio = audios[i];
									currentAudio.pause();
									currentAudio.currentTime = 0;									
								}
							} 
							audio.play();
						}else{
							audio.pause();
							audio.currentTime = 0;
						}
					})
					
				}
				
				if(comment.type == "2"){
					$(".send-img:first").removeClass("send-img-left").addClass("send-img-right");
					$(".r-img14:first").removeClass("r-img14").addClass("r-img13");
				}
			}	
			
		}
		

			/* result.recordType：0为语音，1为文字，end */
	}





	function sleep(n) {
	    var start = new Date().getTime();
	    while(true)  if(new Date().getTime()-start > n) break;
	}



	/*选择完图片后,右边图片聊天信息*/
	function setSendinglocalImage(imageLocalId){
		var chat=$(".chat");
        var chatCtn=$(".chat-container");
        chatCtn.append('<div class="chat-content">'
				+'<img src="'+$("#headUrl").val()+'" class="head1">'
				+'<div class="content">'
				+'<p class="ni-name">'+$("#stuName").val()+'</p>'
				+'<div class="send-img send-img-right"><img src="'+imageLocalId+'" onclick="previewImage(\''+imageLocalId+'\')"></div>'
				+'<i class="r-img13"></i>'
				+'</div></div>');
        if($("#role").val()=="hr"){
			
			$(".ni-name:last").prepend('<i class="hr"></i>');
			
        }else if( $("#role").val()=="js"){
        	
			$(".ni-name:last").prepend('<i class="js"></i>');
        	
        }
		chat.scrollTop(chat[0].scrollHeight);
	}



	/* 判断视频开播时间，并矫正 */
	function checkTime() {

		$.post("timeCheckChat_chat.action", {
			roomId : $("#roomId").val(),
		}, function(result) {

			comments = eval("(" + result + ")");

			var video = document.getElementById("video");

			if (comments.isTime == "true" && comments.diff != null) {

				alert(comments.isTime + '1,' + comments.diff);

				video.currentTime = comments.diff;
			}

			// video.addEventListener('canplaythrough',function(){
			//
			//
			// video.play();
			//
			//		
			// });
			// video.on('timeupdate', function() {
			// video.play();
			// });
			// video.on('click', function() {
			// alert(123);
			// video.pause();
			//				
			// } );

		});
	}
