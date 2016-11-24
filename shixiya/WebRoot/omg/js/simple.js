// 4 音频接口
// 4.2 开始录音
function startRecord() {
	wx.startRecord({
		cancel : function() {
			alert('录音失败,用户拒绝授权录音');
		}
	});
}

// 4.3 停止录音
function stopRecord(sec) {
	wx.stopRecord({
		success : function(res) {
			voice.localId = res.localId;
			
    	    var yw=parseInt(sec)*2+80;
            $(".ly").css("display","none");
            
            var chat=$(".chat");
			var chatCtn=$(".chat-container");
			chatCtn.append('<div class="chat-content">' +
                    '<img src="'+$("#headUrl").val()+'" class="head1">' +
                    '<div class="content"><p class="ni-name">'+$("#stuName").val()+'</p>' +
                    ' <div class="yy" style="width: '+yw+'px" onclick="playLocalVoice(\''+voice.localId+'\')">'
                    +'<span class="scd scd1">'+sec+'"</span>'
                    +'</div><i class="r-img13"></i></div> </div>');
                    
            if($("#role").val()=="hr"){
    			
    			$(".ni-name:last").prepend('<i class="hr"></i>');
    			
            }else if($("#role").val()=="js" || $("#role").val()=="th"){
    			
    			$(".ni-name:last").prepend('<i class="js"></i>');
    			
            }
            chat.scrollTop(chat[0].scrollHeight);
			uploadVioce(sec);// 停止录音后上传录音到服务器
		},
		fail : function(res) {
			alert(JSON.stringify(res));
		}
	});
}

// 4.4 监听录音自动停止
wx.onVoiceRecordEnd({
	complete : function(res) {
		voice.localId = res.localId;
		alert('录音时间已超过一分钟');
	}
});

// 4.5 播放音频
function playVoice() {

	if (voice.localId == '') {
		alert('请先使用 startRecord 接口录制一段声音');
		return;
	}
	wx.playVoice({
		localId : voice.localId
	});
};

function playLocalVoice(currentLocalId) {

	if (voice.localId == '') {
		alert('请先使用 startRecord 接口录制一段声音');
		return;
	}
	wx.playVoice({
		localId : currentLocalId
	});
};

// 4.6 暂停播放音频
function pauseVoice() {
	wx.pauseVoice({
		localId : voice.localId
	});
};

// 4.7 停止播放音频
function stopVoice() {
	wx.stopVoice({
		localId : voice.localId
	});
};

// 4.8 监听录音播放停止
wx.onVoicePlayEnd({
	complete : function(res) {
		alert('录音（' + res.localId + '）播放结束');
	}
});

// 4.8 上传语音
/* 上传语音,并广播serverId */
function uploadVioce(sec) {
	if (voice.localId == '') {
		return;
	}
	wx.uploadVoice({
		localId : voice.localId,
		success : function(res) {
			voice.serverId = res.serverId;
			saveComment("0", voice.serverId,sec);/* 用aJax，发送给Action后台添加评论 */
			ws.send(SetUserMessage('0', voice.serverId+","+sec));/* 广播serverId */
		}
	});
}

/* 下载语音 */
function downloadVioce(serverId) {
	voice.serverId = serverId;
	if (voice.serverId == '') {
		return;
	}
	wx.downloadVoice({
		serverId : voice.serverId,
		success : function(res) {
			voice.localId = res.localId;
			playVoice();/* 下载成功后播发音频 */
		}
	});
}

var shareData = {
	title : '微信JS-SDK Demo',
	desc : '微信JS-SDK,帮助第三方为用户提供更优质的移动web服务',
	link : 'http://demo.open.weixin.qq.com/jssdk/',
	imgUrl : 'http://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRt8Qia4lv7k3M9J1SKqKCImxJCt7j9rHYicKDI45jRPBxdzdyREWnk0ia0N5TMnMfth7SdxtzMvVgXg/0'
};

wx.onMenuShareAppMessage(shareData);
wx.onMenuShareTimeline(shareData);

function chooseImage() {
	wx.chooseImage({
		success : function(res) {
			images.localId = res.localIds;
			/* 选择完图片后上传图片到微信服务器 */
			uploadImage();
		}
	});
}

function previewImage(imageLocalId) {
	wx.previewImage({
				current : imageLocalId,
				urls : [imageLocalId]
			
			});
}

function uploadImage(){
	if (images.localId.length == 0) {
		alert('请先使用 chooseImage 接口选择图片');
		return;
	}
	var i = 0, length = images.localId.length;
	images.serverId = [];
	function upload() {
		wx.uploadImage({
			localId : images.localId[i],
			success : function(res) {
				
				images.serverId.push(res.serverId);

				/* 1、选择完图片后为当前用户显示发送的图片消息,2、保存聊天记录，3、广播图片 */
				setSendinglocalImage(images.localId[i]);
				saveComment("2",res.serverId);
            	ws.send(SetUserMessage("2",res.serverId));

				i++;
				if (i < length) {
					upload();
				}
			},
			fail : function(res) {
				alert(JSON.stringify(res));
			}
		});
	}
	upload();
}

function downloadImage(wsServerId,wsImage){
	if (wsServerId == null) {
		return;
	}
	var i = 0, length = images.serverId.length;
	images.localId = [];
	function download() {
		wx.downloadImage({
			serverId : wsServerId,
			success : function(res) {
				images.localId.push(res.localId);

				wsImage.setAttribute("src",res.localId);
				wsImage.setAttribute("onclick",'previewImage(\''+res.localId+'\')');

				i++;
				if (i < length) {
					download();
				}
			},
			fail : function(res) {
				alert(JSON.stringify(res));
			}
		});
	}
	download();
}

wx.error(function(res) {
	alert(res.errMsg);
});


