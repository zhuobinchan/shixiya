<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'testImage.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	wx.ready
	(function() {

				// 5 图片接口
				// 5.1 拍照、本地选图
				var images = {
					localId : [],
					serverId : []
				};
				document.querySelector('#chooseImage').onclick = function() {
					alert("能选择吗？");
					wx.chooseImage({
						success : function(res) {
							images.localId = res.localIds;
							alert('已选择 ' + res.localIds.length + ' 张图片');
						}
					});
				};

				// 5.2 图片预览
				document.querySelector('#previewImage').onclick = function() {
					wx
							.previewImage({
								current : 'http://img5.douban.com/view/photo/photo/public/p1353993776.jpg',
								urls : [
										'http://img3.douban.com/view/photo/photo/public/p2152117150.jpg',
										'http://img5.douban.com/view/photo/photo/public/p1353993776.jpg',
										'http://img3.douban.com/view/photo/photo/public/p2152134700.jpg' ]
							});
				};

				// 5.3 上传图片
				document.querySelector('#uploadImage').onclick = function() {
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
								i++;
								alert('已上传：' + i + '/' + length);
								images.serverId.push(res.serverId);
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
				};

				// 5.4 下载图片
				document.querySelector('#downloadImage').onclick = function() {
					if (images.serverId.length === 0) {
						alert('请先使用 uploadImage 上传图片');
						return;
					}
					var i = 0, length = images.serverId.length;
					images.localId = [];
					function download() {
						wx.downloadImage({
							serverId : images.serverId[i],
							success : function(res) {
								i++;
								alert('已下载：' + i + '/' + length);
								images.localId.push(res.localId);
								if (i < length) {
									download();
								}
							}
						});
					}
					download();
				};

				// 3 智能接口
				var voice = {
					localId : '',
					serverId : ''
				};

				// 4 音频接口
				// 4.2 开始录音
				document.querySelector('#startRecord').onclick = function() {
					wx.startRecord({
						cancel : function() {
							alert('用户拒绝授权录音');
						}
					});
				};

				// 4.3 停止录音
				document.querySelector('#stopRecord').onclick = function() {
					wx.stopRecord({
						success : function(res) {
							voice.localId = res.localId;
						},
						fail : function(res) {
							alert(JSON.stringify(res));
						}
					});
				};

				// 4.4 监听录音自动停止
				wx.onVoiceRecordEnd({
					complete : function(res) {
						voice.localId = res.localId;
						alert('录音时间已超过一分钟');
					}
				});

				// 4.5 播放音频
				document.querySelector('#playVoice').onclick = function() {
					if (voice.localId == '') {
						alert('请先使用 startRecord 接口录制一段声音');
						return;
					}
					wx.playVoice({
						localId : voice.localId
					});
				};

				// 4.6 暂停播放音频
				document.querySelector('#pauseVoice').onclick = function() {
					wx.pauseVoice({
						localId : voice.localId
					});
				};

				// 4.7 停止播放音频
				document.querySelector('#stopVoice').onclick = function() {
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
				document.querySelector('#uploadVoice').onclick = function() {
					if (voice.localId == '') {
						alert('请先使用 startRecord 接口录制一段声音');
						return;
					}
					wx.uploadVoice({
						localId : voice.localId,
						success : function(res) {
							alert('上传语音成功，serverId 为' + res.serverId);
							voice.serverId = res.serverId;
						}
					});
				};

				// 4.9 下载语音
				document.querySelector('#downloadVoice').onclick = function() {
					if (voice.serverId == '') {
						alert('请先使用 uploadVoice 上传声音');
						return;
					}
					wx.downloadVoice({
						serverId : voice.serverId,
						success : function(res) {
							alert('下载语音成功，localId 为' + res.localId);
							voice.localId = res.localId;
						}
					});
				};

			});

	wx.error(function(res) {
		alert(res.errMsg);
	});
</script>
</head>

<body>

	<h3 id="menu-image">图像接口</h3>
	<span class="desc">拍照或从手机相册中选图接口</span>
	<button class="btn btn_primary" id="chooseImage">chooseImage</button>
	<span class="desc">预览图片接口</span>
	<button class="btn btn_primary" id="previewImage">previewImage</button>
	<span class="desc">上传图片接口</span>
	<button class="btn btn_primary" id="uploadImage">uploadImage</button>
	<span class="desc">下载图片接口</span>
	<button class="btn btn_primary" id="downloadImage">downloadImage</button>


	<br>
	
	
	<h3 id="menu-voice">音频接口</h3>
	<span class="desc">开始录音接口</span>
	<button class="btn btn_primary" id="startRecord">startRecord</button>
	<span class="desc">停止录音接口</span>
	<button class="btn btn_primary" id="stopRecord">stopRecord</button>
	<span class="desc">播放语音接口</span>
	<button class="btn btn_primary" id="playVoice">playVoice</button>
	<span class="desc">暂停播放接口</span>
	<button class="btn btn_primary" id="pauseVoice">pauseVoice</button>
	<span class="desc">停止播放接口</span>
	<button class="btn btn_primary" id="stopVoice">stopVoice</button>
	<span class="desc">上传语音接口</span>
	<button class="btn btn_primary" id="uploadVoice">uploadVoice</button>
	<span class="desc">下载语音接口</span>
	<button class="btn btn_primary" id="downloadVoice">downloadVoice</button>


	<script type="text/javascript" src="<%=path%>/js/weixin.js"></script>
</body>

</html>
