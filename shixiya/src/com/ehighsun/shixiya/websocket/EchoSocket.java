package com.ehighsun.shixiya.websocket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ehighsun.shixiya.util.EmojiFilterUtil;

@ServerEndpoint("/echo/{roomId}/{stuName}")
public class EchoSocket {
	
	private static Map<String, Room> rooms = new HashMap<>();
	private static int countFail=0;
	private String roomId;
	
	public EchoSocket (){
		System.out.println("");
	}
	
	/*一个session代表一个通信会话,代表一个管道，通过sessionId来区分
	 * roomId为页面传过来的直播间ID
	 */
	@OnOpen
	public void open(Session session,
					@PathParam("roomId") String roomId,
					@PathParam("stuName") String stuName) throws IOException{
		
		this.roomId = roomId;
		

		/* 若有房间则创建一个并把用户加进房间，通过roomId判断用户类型，若没有就把用户加到所进入的房间 */
		if (judgeHr(roomId).equals("hr")) {
			String role = "hr";
			this.addToRoom(this.roomId,session,stuName,role);
		}else if(judgeHr(roomId).equals("js")){
			String role = "js";
			this.addToRoom(this.roomId,session,stuName,role);
		}else if(judgeHr(roomId).equals("th")){
			String role = "th";
			this.addToRoom(this.roomId,session,stuName,role);			
		}else {
			String role = "student";
			this.addToRoom(this.roomId,session,stuName,role);
		}
		
		System.out.println("直播人数："+rooms.get(this.roomId).getUsers().size()+"\n");
//		FileOutputStream fs = new FileOutputStream(new File("C:\\text\\text.txt"),true);
//		PrintStream p = new PrintStream(fs);
//		p.println("直播人数："+rooms.get(this.roomId).getUsers().size()+"\n");
//		p.close();
		
	}
	
	@OnMessage
	public void message(Session session,String msg) throws IOException{
		
		System.out.println("测试：有信息进来:"+msg);

		/*封装用户发送的信息*/
		
		msg = EmojiFilterUtil.filterEmoji(msg);
		
		Message messageObj = toMessageJsonObject(msg);		
		User user = rooms.get(this.roomId).getUsers().get(session.getId());

		System.out.println("客户端"+session.getId()+",姓名:"+user.getName()+",信息:"+msg+"\n");
		/*因为用户的url链接太长不能通过param传送，所以在同过第一次发信息给用户设置头像*/
		if (messageObj.getRecordType().equals("3")) {
			user.setHeadUrl(messageObj.getContent());
		}else{
			user.setMessage(messageObj);
			/*广播给其他user*/
			broadcast(this.roomId,session.getId(),user);			
		}

		
	}
	
	
	@OnClose
	public void close(Session session) throws FileNotFoundException{
			countFail++;
			System.out.println("session:"+session.getId()+"roomId:"+this.roomId+"通道关闭。。。。。countFail:"+countFail);
			
			rooms.get(this.roomId).getUsers().remove(session.getId());
			
			System.out.println("直播间剩余："+rooms.get(this.roomId).getUsers().size()+"\n");
			if (rooms.get(this.roomId).getUsers().size()==0) rooms.remove(this.roomId);
			
//			FileOutputStream fs = new FileOutputStream(new File("C:\\text\\text.txt"),true);
//			PrintStream p = new PrintStream(fs);
//			p.println("session:"+session.getId()+"roomId:"+this.roomId+"通道关闭。。。。。countFail:"+countFail);
//			p.close();
			
	}
	
	@OnError
	public void error(Throwable t,Session session) throws FileNotFoundException{
		System.out.println("浏览器关闭了！sessionId:"+session);
		
//		FileOutputStream fs = new FileOutputStream(new File("C:\\text\\text.txt"),true);
//		PrintStream p = new PrintStream(fs);
//		p.println("浏览器关闭了！");
//		p.close();
	}
	

	
	/*判断进来的用户身份*/
	public String judgeHr(String roomId){
		
		try {
			String role = roomId.substring(0, 6);
			if (role.equals("Hr1994")) {
				roomId =roomId.substring(6, roomId.length());
				this.roomId = roomId;
				return "hr";
			}else if (role.equals("Js1994")) {
				roomId =roomId.substring(6, roomId.length());
				this.roomId = roomId;
				return "js";
			}else if (role.equals("Th1994")){
				roomId =roomId.substring(6, roomId.length());
				this.roomId = roomId;
				return "th";				
			}
		} catch (Exception e) {
			System.out.println("判断hr或js身份，字符串截取超出范围,表示为学生!");
		}

		return roomId;
	}
	
	/*把传过来Message的json字符串,变成Message对象*/
	public Message toMessageJsonObject(String msg){
		
		msg = msg.replace("<", "&lt;");
		msg = msg.replace(">", "&gt;");
		
		Message message = JSON.parseObject(msg, Message.class);
		
		return message;
	}
	
	/*将User转成JsonString传输*/
	public String toUserJsonObject(User user){
		
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class,
				"message","headUrl","name","role");
		String userJsonString = JSON.toJSONString(user,filter);
		
		return userJsonString;
	}
	
	/*创建一个新的房间room，并把用户加进房间*/
	public void addToRoom(String roomId,Session session,String stuName,String role) throws FileNotFoundException{
		
			/* 若有房间则创建一个并把用户加进房间，若没有就把用户加到所进入的房间 */
			if(rooms.get(roomId)==null) {
				User user = new User(session);
				user.setName(stuName);
				if (role.equals("hr")) {
					user.setRole("hr");
				}else if (role.equals("js")) {
					user.setRole("js");
				}else if (role.equals("th")){
					user.setRole("th");
				}
				
				System.out.println("用户角色:"+user.getRole());
				
				Room newRoom = new Room();/*创建新的房间room*/
				HashMap<String, User> users = new HashMap<>();/*创建新的用户列表*/
				users.put(user.getSession().getId(), user);/*该房间room中用户列表添加新用户,用户Id为sessionId*/
				newRoom.setUsers(users);
				this.rooms.put(roomId, newRoom);

				System.out.println("新创建了房间room:"+roomId);
				
			}
			else {
				User user = new User(session);
				user.setName(stuName);
				if (role.equals("hr")) {
					user.setRole("hr");
				}else if (role.equals("js")) {
					user.setRole("js");
				}else if (role.equals("th")) {
					user.setRole("th");
				}
				rooms.get(roomId).getUsers().put(session.getId(), user);
				
			}

			System.out.println("用户sesionId:"+session.getId()+",用户名:"+stuName+"：进入房间！");
			FileOutputStream fs = new FileOutputStream(new File("C:\\text\\text.txt"),true);
			PrintStream p = new PrintStream(fs);
			
			p.println("用户sesionId:"+session.getId()+",用户名:"+stuName+"：进入房间！");
			p.close();
			
	}
	
	/* 广播消息，除出者外*/
	public void broadcast (String roomId,String sessionId,
			User user) throws IOException{

		Iterator<Map.Entry<String, User>> userMaps = 
				rooms.get(roomId).getUsers().entrySet().iterator();	
		
		while (userMaps.hasNext()) {		
			/*获取了一个Map对象user,getValue后就是user*/
			Map.Entry<String, User> userMap = userMaps.next();
			String userId = userMap.getKey();
			if (!userId.equals(sessionId)){
				User otherUser = userMap.getValue();
				otherUser.getSession().getBasicRemote().
					sendText(toUserJsonObject(user));/*发送User对象的jsonString*/
				
			}
		}
	
	}
	
	
}
