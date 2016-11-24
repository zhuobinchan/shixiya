
var voiceNum = 0 ;
var ws = null; // 一个ws对象就是一个通信管道,全局变量
var voice = {
	    localId: '',
	    serverId: ''
};
var messageString ={
		  recordType:'',
		  content:''
};

//var target = "ws://1095256592.tunnel.2bdata.com/shixiya/echo";
var target = "ws://10.3.51.87/shixiya/echo";
	
subOpen();	/*简历socket长连接*/

function subOpen() {

	if ('WebSocket' in window) {
		ws = new WebSocket(target);
	} else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(target);
	} else {
		alert('WebSocket is not supported by this browser.');
		return;
	}

	// open的时候就要注册这个事件
	/** weixin.js上传语音后,接收广播的serverId **/
	ws.onmessage = function(event) {


		var chat=$(".chat");
		var result = eval('('+event.data+')');
		
		/* result.recordType：0为语音，1为文字*/
		if (result.recordType == "0"){
			chat.append('<div class="chat-content"><img src="/shixiya/chat/images/head-img.png" class="head2"><div class="content2"><p class="ni-name2">1234</p> '
					+'<img src="/shixiya/chat/images/yy2.png" class="yy" onclick="playRecord(\''+result.content+'\')"> </div> </div>');
		}else if (result.recordType =="1"){
			chat.append(
			'<div class="chat-content" id="chat-l">'
	        +'<img src="/shixiya/chat/images/head-img.png" class="head2">'
	        +'<div class="content2">'
	        		+'<p class="ni-name2">1234</p>'
	            	+'<div class="word2">'+result.content+'</div>'
	            +'</div>'
	        +'</div>');
		}
		/* result.recordType：0为语音，1为文字，end*/
        
	}

}

function onSend() {
	var msg = document.getElementById("msg").value;
	ws.send(msg);
	document.getElementById("msg").value = "";
}


function playRecord(serverId){
	voice.serverId = serverId;
	downloadVioce();//下载服务器上的录音
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.playVoice({
      localId: voice.localId
    });
}