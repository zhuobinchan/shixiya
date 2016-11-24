

  // 4 音频接口
  // 4.2 开始录音
  function startRecord(){
	    wx.startRecord({  
	        cancel: function () {
	          alert('用户拒绝授权录音');
	        }
	      });
  } 
  
  // 4.3 停止录音
  function stopRecord(){
	    wx.stopRecord({
	        success: function (res) {
	          voice.localId = res.localId;
	          uploadVioce();//停止录音后上传录音到服务器
	        },
	        fail: function (res) {
	          alert(JSON.stringify(res));
	        }
	      });
  }

  // 4.4 监听录音自动停止
  wx.onVoiceRecordEnd({
    complete: function (res) {
      voice.localId = res.localId;
      alert('录音时间已超过一分钟');
    }
  });


  
  // 4.5 播放音频
  function playVoice() {
	  
	downloadVioce();//下载服务器上的录音
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.playVoice({
      localId: voice.localId
    });
  };
  
  function playLocalVoice() {
	  
	    if (voice.localId == '') {
	      alert('请先使用 startRecord 接口录制一段声音');
	      return;
	    }
	    wx.playVoice({
	      localId: voice.localId
	    });
	  };

  // 4.6 暂停播放音频
  function pauseVoice() {
    wx.pauseVoice({
      localId: voice.localId
    });
  };

  // 4.7 停止播放音频
  function stopVoice() {
    wx.stopVoice({
      localId: voice.localId
    });
  };

  // 4.8 监听录音播放停止
  wx.onVoicePlayEnd({
    complete: function (res) {
      alert('录音（' + res.localId + '）播放结束');
    }
  });

  // 4.8 上传语音
  /* 上传语音,并广播serverId */
  function uploadVioce(){
	    if (voice.localId == '') {
	        alert('请先使用 startRecord 接口录制一段声音');
	        return;
	      }
	      wx.uploadVoice({
	        localId: voice.localId,
	        success: function (res) {
	          alert('上传语音成功，serverId 为' + res.serverId);
	          voice.serverId = res.serverId;
	          
	          ws.send(SetUserMessage('0',voice.serverId));/* 广播serverId */
	        }
	      });	  
  }

  /* 下载语音 */
  function downloadVioce(){
	    if (voice.serverId == '') {
	        alert('请先使用 uploadVoice 上传声音');
	        return;
	      }
	      wx.downloadVoice({
	        serverId: voice.serverId,
	        success: function (res) {
	          alert('下载语音成功!');
	          voice.localId = res.localId;
	        }
	      });
  }


  var shareData = {
    title: '微信JS-SDK Demo',
    desc: '微信JS-SDK,帮助第三方为用户提供更优质的移动web服务',
    link: 'http://demo.open.weixin.qq.com/jssdk/',
    imgUrl: 'http://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRt8Qia4lv7k3M9J1SKqKCImxJCt7j9rHYicKDI45jRPBxdzdyREWnk0ia0N5TMnMfth7SdxtzMvVgXg/0'
  };
 
  wx.onMenuShareAppMessage(shareData);
  wx.onMenuShareTimeline(shareData);
  

  



wx.error(function (res) {
  alert(res.errMsg);
});



