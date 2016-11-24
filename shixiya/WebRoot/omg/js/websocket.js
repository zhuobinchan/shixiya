/*var target = "ws://1095256592.tunnel.2bdata.com//shixiya/echo/"
					+$("#roomId").val()+"/"+$("#stuName").val();*/
var target = "ws://sxy.000861.com//shixiya/echo/"
					+$("#roomId").val()+"/"+$("#stuName").val();
//var target = "ws://zhuobinchan.tunnel.2bdata.com/shixiya/echo/"
//	+$("#roomId").val()+"/"+$("#stuName").val();
subOpen();	/*建立socket长连接*/

function subOpen() {

	if ('WebSocket' in window) {
		ws = new WebSocket(target);
	} else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(target);
	} else {
		alert('WebSocket is not supported by this browser.');
		return;
	}

	ws.onopen = function(){
		
		/*因为头像链接太长不能通过get方法从url传到websocket,只好让用户进入房间时发送一次头像地址*/
		ws.send(SetUserMessage("3",$("#headUrl").val()));
	
	// open的时候就要注册这个事件
	/** weixin.js上传语音后,接收广播的serverId **/
	ws.onmessage = function(event) {

		var chat=$(".chat");
		var chatCtn=$(".chat-container");
		var result = eval('('+event.data+')');
		/* result.recordType：0为语音，1为文字,2为图片*/
		if (result.message.recordType == "0"){
			
			var content = result.message.content.split(",");
			var yw=parseInt(content[1])*2+80;
			
			chatCtn.append('<div class="chat-content"><img src="'+result.headUrl+'" class="head2"><div class="content2"><p class="ni-name2">'+result.name+'</p> '
					+' <div class="yy2" style="width: '+yw+'px" onclick="downloadVioce(\''+ content[0] + '\')">'
					+'<span class="scd scd2">'+content[1]+'"</span>'
					+' <i class="weidu"></i></div>'
					+' </div> </div>');
			chat.scrollTop(chat[0].scrollHeight);
		}else if (result.message.recordType =="1"){
			
			chatCtn.append(
			'<div class="chat-content" id="chat-l">'
	        +'<img src="'+result.headUrl+'" class="head2">'
	        +'<div class="content2">'
	        		+'<p class="ni-name2">'+result.name+'</p>'
	            	+'<div class="word4">'+result.message.content+'</div>'
	            	+'<i class="r-img14"></i>'
	            +'</div>'
	        +'</div>');
			
        	if(CheckUrl(result.message.content)){
        		$(".word4:last").html("<a href='"+result.message.content+"'>"+result.message.content+"</a>")
        	}
			
			chat.scrollTop(chat[0].scrollHeight);
			
		}else if(result.message.recordType =="2"){

			chatCtn.append('<div class="chat-content">'
					+'<img src="'+result.headUrl+'" class="head2">'
					+'<div class="content2">'
					+'<p class="ni-name2">'+result.name+'</p>'
					+'<div class="send-img send-img-left">'
					+'<img class="wsImage" src="" onclick="">'
					+'</div><i class="r-img14"></i></div></div>');
			
			/*获取图片对象,并且传给下载图片函数,为了防止websocket发送太快,图片设置不对应*/
			var wsImages = document.getElementsByClassName("wsImage");
			var wsImage = wsImages[wsImages.length-1];
			downloadImage(result.message.content,wsImage);
			
			chat.scrollTop(chat[0].scrollHeight);	
            
		}
		
		/*若评论的是是Hr或讲师js则，改变他们评论背景*/
		if((result.role=="hr") && 
				result.message.recordType!="startVideo" && result.message.recordType != "talking"){
			
			$(".ni-name2:last").append('<i class="hr"></i>');
			
		}else if((result.role=="js" || result.role=="th") && 
				result.message.recordType!="startVideo" && result.message.recordType != "talking"){
			
			$(".ni-name2:last").append('<i class="js"></i>');
			
		}
		
		/*后台控制视频的播放*/
		if((result.role=="hr" || result.role=="js" || result.role=="th") && result.message.recordType == "startVideo"){
			var videoSource = $("#videoSource");
			alert("视频可以开始播放");
			video.poster = "";
			video.poster = "/shixiya/omg/images/video/ready.png";
			video.controls = true;
			video.src = result.message.content;
		}
        
		/*后台控制禁言*/
		if((result.role=="hr" || result.role=="js" || result.role=="th") && result.message.recordType == "talking"){
			
			if(result.message.content=="false"){
				$(".send").css("display","none");
			}else if (result.message.content=="true"){
				$(".send").css("display","block");
			}

		}		

		/* result.recordType：0为语音，1为文字,2为图片end*/
		
	}
	
	}
	
	ws.onclose = function() {
	    console.log("连接关闭，定时重连");
		alert("出现错误，请检测网络！");
	    // 定时重连
//	    window.clearInterval(timeid);
//	    timeid = window.setInterval(init, 3000);
	};
//	ws.onerror = function() {
//	    console.log("出现错误");
//		alert("出错了！"error);
//	};

}

 //单位毫秒 
function delayDown(content) 
{ 
	downloadImage(content);
} 


