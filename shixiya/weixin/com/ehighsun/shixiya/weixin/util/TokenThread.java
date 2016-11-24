package com.ehighsun.shixiya.weixin.util;

import com.ehighsun.shixiya.weixin.pojo.Token;

public class TokenThread implements Runnable {

//	 // 海印微办公
//	 String appid = "wxc7eee0d7cb088852";
//	 String appsecret = "3c77e1eacc078c66edbaa4e9026efced";

	// 实习吖版本
	// 第三方用户唯一凭证
	public static String appid = "wx2f5016e1682b8d9f";
	// 第三方用户唯一凭证密匙
	public static String appsecret = "c3b693e7a32b17503e9880f1b6f1e703";

	public static Token token = null;
	public static String jsapi_ticket = null;

	public void run() {
		while (true) {
			try {
				token = WeixinUtil.getToken(appid, appsecret);
				// 获取JSAPI_Ticket
				jsapi_ticket = WeixinUtil.JSApiTIcket(token.getAccessToken());

				if (null != token) {
					System.out.println("获取access_token成功，有效时长{}秒 token:{}"
							+ token.getExpiresIn() + "accessToken:"
							+ token.getAccessToken());
					System.out.println("获取jsapi_ticket成功， jsapi_ticket:{}"
							+ jsapi_ticket);
					// 休眠700秒
					Thread.sleep((token.getExpiresIn() - 200) * 1000);
				} else {
					// 如果access_token未null，60秒后在获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					System.out.println(e1.toString());
				}
				System.out.println(e.toString());
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(TokenThread.token.getAccessToken());
	}
}
