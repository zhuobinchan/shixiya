$(function(){
    var init=function(){
        var btnSound=$(".sound-btn");
        var btnSend=$(".btn-std");
        var sendSound=$(".send-sound");
        var inputTxt=$(".input-word");
        var sendImg=$(".ft");
        var chat=$(".chat");
        var chatCtn=$(".chat-container");
        var yuYin=$(".yy2");
        var send=$(".send");

        chatCtn.css("padding-bottom",send.height());    
        
        btnSound.click(function(){
            var sendWord=$(".send-word");
            var sd=sendSound.css("display");
            if(sd=="none"){
                sendSound.css("display","block");
                sendWord.css("display","none");
            }
            else if(sd=="block"){
                sendSound.css("display","none");
                sendWord.css("display","block");
            }
        });

        yuYin.click(function () {
            $(this).find("i").css("display","none");
        });

        var ly=$(".ly");
        sendSound.click(function() {
            if(ly.css("display")=="none"){
                sending();
            }
            else{
                var i=$(".timing").text();
                sended(60-i);
            }
        });
        
        function sending(){
            $(".ly").css("display","block");
            sendSound.text("点击结束录音");
            var n=59;
            var t1=setInterval(function(){
                n--;
                if (n ==0) {
                    sended(60);
                    clearInterval(t1);
                }
                $(".timing").text(n);
            },1000);
            $(".timing").text(n);
            sendSound.click(function () {
                clearInterval(t1);
            });
            
            startRecord(); /* 开始录音  */
        }
        
        function sended(sec){
        	
            stopRecord(sec); /* 停止录音  */
            sendSound.text("点击开始录音");
        	
        	

        }
        
        inputTxt.bind('input propertychange',function () {
            sendImg.css("display","none");
            btnSend.css("display","block");
        });
        $(".btn-send").click(function () {
            sendImg.css("display","block");
            btnSend.css("display","none");
        });

        btnSend.click(function(){
            var inputWord=inputTxt.val();
            if(inputWord==""){
                alert("输入内容不能为空");
                return false;
            }
            else{
            	
            	var message = SetUserMessage("1",inputWord);
            	inputWord = inputWord.replace("<","&lt;");
            	inputWord = inputWord.replace(">","&gt;");
            	ws.send(message);/*打包信息，发送给websocket端*/
            	
            	chatCtn.append('<div class="chat-content">' +
                '<img src="'+$("#headUrl").val()+'" class="head1">' +
                '<div class="content"><p class="ni-name">'+$("#stuName").val()+'</p>' +
                ' <div class="word3">'+inputWord+'</div>' +
                '<i class="r-img13"></i></div> </div>');

            	if(CheckUrl(inputWord)){
            		$(".word3:last").html("<a href='"+inputWord+"'>"+inputWord+"</a>")
            	}
            	
                inputTxt.val("");
                chat.scrollTop(chat[0].scrollHeight);
                
                
                if($("#role").val()=="hr"){
        			
        			$(".ni-name:last").prepend('<i class="hr"></i>');
        			
                }else if($("#role").val()=="js" || $("#role").val()=="th"){
        			
        			$(".ni-name:last").prepend('<i class="js"></i>');
        			
                }
                
                saveComment("1",inputWord);/*用aJax，发送给Action后台添加评论*/
                
                
                
                
            }
        });
    };

    //左边聊天内容
    var sendChat=function(){
        var inputWord="";  //聊天内容
        $(".chatCtn").append('<div class="chat-content">' +
        '<img src="../../images/head-img.png" class="head2">' +
        '<div class="content2"><p class="ni-name2">1234</p>' +
        ' <div class="word4">'+inputWord+ '</div>' +
        '<i class="r-img14"></i></div> </div>')
    };
    
    

    

    $(window).load(function(){
        wx.ready(function(){

            init();
        	
        })
    });
});

	/*点击后学生端聊天室可以播放视频*/
	$("#startVideoBtn").click(function(){
		
		if(confirm("是否允许播放视频？")){
			var videoSrc = $("#videoSource").attr("src");
			ws.send(SetUserMessage('startVideo', videoSrc));
			videoCanPlay(1);
		}
		
	})
	
	/*点击后学生端聊天室可以聊天或禁言*/
	$("#stopTalkBtn").click(
	function(){
		
        if($(this).hasClass("noise")) {
        	$(this).removeClass("noise").addClass("quiet");
        }
        else if($(this).hasClass("quiet")) $(this).removeClass("quiet").addClass("noise");
		
			if( $(this).attr("status")==0 ){
				ws.send(SetUserMessage('talking', "true"));
				$(this).attr("status","1");
				canChat(1);
			}else {
				ws.send(SetUserMessage('talking', "false"));
				$(this).attr("status","0");
				canChat(0);
			};
	})

	
	/*修改聊天室学生是否可以聊天，主要是为了学生退出后还是当前的言论状态*/
	function canChat(chatStatus){
		$.post("canChat_chat.action", {
			roomId : $("#roomId").val(),
			chatStatus : chatStatus
		}, function(result) {
	
		});
	}
	/*修改聊天室学生是否可以播放视频，主要是为了学生退出后还是当前的视频状态*/
	function videoCanPlay(videoStatus){
		$.post("videoCanPlay_chat.action", {
			roomId : $("#roomId").val(),
			videoStatus : videoStatus
		}, function(result) {
	
		});
	}