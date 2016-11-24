package com.ehighsun.shixiya.commonality.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.pojo.TrainWEComment;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWEProductChild;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.BProductApplyService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.AmrToMP3Util;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.weixin.util.WeixinUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ChatAction extends ActionSupport {

	private String hrCommentListId;
	private String productName;
	private String studentId;
	private Student student;
	
	private CProductBroadcast cpBroadcast;
	private BProductVideo bpVideo;
	private HrCommentList hrList;
//	private TrainWEProduct weCourse;
	private TrainWEProductChild weCourseChild;
	
	private int chatStatus;
	private int videoStatus;
	
	/*保存评论内容*/
	private String roomId;
	private String inputWord;
	private String stuName;
	private String recordType;
	private String headUrl;
	private String commentRole;/*用于保存room的评论时区分角色，方便取出来后判断评论属于什么角色*/
	private String role;/*用于room的角色判断，当用户发送信息时，取该值，判断身份，改变当前发送框的颜色*/
	private String roleId;/*加载历史消息时识别是自己所发的评论，显示在右边*/
	private int duration;/*聊天时长*/
	
	private String targetUrl;
	
	/*评论内容分页*/
	private int page;
	private int pageSize = 5;

	
	private String isTime;
	private String videoUrl;
	private String diffT;
	
	@Resource(name="baseDao")
	private BaseDao<HrCommentList> hrcommentListDao;
	@Resource(name="baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name="baseDao")
	private BaseDao<BProductComment> bpCommentDao;
	@Resource(name="baseDao")
	private BaseDao<BProductVideo> bpVideoDao;
	@Resource(name="baseDao")
	private BaseDao<CProductBroadcast> cpBroadcasrDao;
	@Resource(name="baseDao")
	private BaseDao<CProductComment> cpCommentDao;	
	@Resource(name="baseDao")
	private BaseDao<HrComment> hrCommentDao;	
	@Resource(name="baseDao")
	private BaseDao<TrainWEProductChild> weCourseChildDao;	
	@Resource(name="baseDao")
	private BaseDao<TrainWEComment> weCommentDao;	
	
	@Autowired
	private CProductBroadcastService cpBroadcastService;
	@Autowired
	private BProductApplyService bpApplyService;
	
	/*跳转到C产品直播间聊天室*/
	public String enterCpChat(){
		

		
		/*因为设计关系，传出去的roomId必须是刚才传进来未处理的roomId*/
		String sub_before_roomId = roomId;
		subStringId();
		
		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		CProductHR hrSession = (CProductHR) ServletActionContext.getContext().getSession().get("hr");
		
		
		if(hrSession==null){
			if(student==null) {
				
				targetUrl ="enterCpChat_chat?roomId=cp"+roomId;
				return "nologin";
			
			}
			
			String applyStatus = cpBroadcastService.judgeApplyStatus(student.getId(),Integer.parseInt(roomId) );
			if(applyStatus!="sccessApply") {
				targetUrl = "judgeApplyStatus_cp?broadcastId="+roomId;
				return "unlawfulEnter";
			}
		}
		
		cpBroadcast = cpBroadcasrDao.get(CProductBroadcast.class, Integer.parseInt(roomId));
		roomId = sub_before_roomId;
		return "enterCpChat";
	}
	
	/*跳转到B产品直播间聊天室*/
	public String enterBpChat(){
		
	
		
		/*因为设计关系，传出去的roomId必须是刚才传进来未处理的roomId*/
		String sub_before_roomId = roomId;
		subStringId();
		
		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		BProductTeacher bpTeacherSession = (BProductTeacher) ServletActionContext.getContext().getSession().get("bpTeacher");
		
		if(bpTeacherSession==null){
			if(student==null) {
				
				targetUrl ="enterBpChat_chat?roomId=cp"+roomId;
				return "nologin";
			
			}		
			
			String applyStatus = bpApplyService.judgeApplyStatus(student.getId(), Integer.parseInt(roomId));
			if(applyStatus!="sccessApply") {
				targetUrl = "judgeApplyStatus_bp?courseId="+roomId;
				return "unlawfulEnter";
			}
		}
		
		bpVideo = bpVideoDao.get(BProductVideo.class,Integer.parseInt(roomId) );		
		roomId = sub_before_roomId;
		return "enterBpChat";
	}	
	
	/*跳转到Hr帮帮堂聊天室*/
	public String enterStuHrChat(){	
		
		
		/*因为设计关系，传出去的roomId必须是刚才传进来未处理的roomId*/
		String sub_before_roomId = roomId;
		subStringId();

		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		CProductHR hrSession = (CProductHR) ServletActionContext.getContext().getSession().get("hr");
		if(hrSession==null){
			if(student==null) {
				targetUrl ="enterStuHrChat_chat?roomId=sh"+roomId;
				return "nologin";
			}
		}
		hrList = hrcommentListDao.get(HrCommentList.class, Integer.parseInt(roomId));	
		roomId = sub_before_roomId;
		return "enterStuHrChat";
	}	
	
	/*跳转到培训we课堂聊天室*/
	public String enterWeCourseChat(){

		/*因为设计关系，传出去的roomId必须是刚才传进来未处理的roomId*/
		String sub_before_roomId = roomId;
		subStringId();
		
		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		TrainWETeacher trainWETeacher = (TrainWETeacher) ServletActionContext.getContext().getSession().get("trainer");
		if(trainWETeacher==null){
			if(student==null) {
				
				targetUrl ="enterWeCourseChat_chat.action?roomId=th"+roomId;
				return "nologin";
			
			}
		}
		weCourseChild = weCourseChildDao.get(TrainWEProductChild.class, Integer.parseInt(roomId));
		roomId = sub_before_roomId;
		
		
		return "enterWeCourseChat";
	}
	
	/*修改学生端聊天室是否可以聊天*/
	public String canChat(){
		
		subStringId();
		
		if(productName.equals("bp")){
			
			bpVideo = bpVideoDao.get(BProductVideo.class, Integer.parseInt(roomId));
			bpVideo.setChatStatus(chatStatus);
			bpVideoDao.saveOrUpdate(bpVideo);

		}else if(productName.equals("cp")) {
			
			cpBroadcast = cpBroadcasrDao.get(CProductBroadcast.class, Integer.parseInt(roomId));
			cpBroadcast.setChatStatus(chatStatus);
			cpBroadcasrDao.saveOrUpdate(cpBroadcast);

		}else if(productName.equals("th")){
			
			weCourseChild = weCourseChildDao.get(TrainWEProductChild.class, Integer.parseInt(roomId));
			weCourseChild.setChatStatus(chatStatus);
			weCourseChildDao.saveOrUpdate(weCourseChild);

		}else if(productName.equals("sh")){

		}
		
		return null;
	}
	
	/*修改学生端聊天室视频是否可以播放*/
	public String videoCanPlay(){
		
		subStringId();
		
		if(productName.equals("bp")){
			bpVideo = bpVideoDao.get(BProductVideo.class, Integer.parseInt(roomId));
			bpVideo.setStatus(videoStatus);
			bpVideoDao.saveOrUpdate(bpVideo);

		}else if(productName.equals("cp")) {
			
			cpBroadcast = cpBroadcasrDao.get(CProductBroadcast.class, Integer.parseInt(roomId));
			cpBroadcast.setStatus(videoStatus);
			cpBroadcasrDao.saveOrUpdate(cpBroadcast);

		}else if(productName.equals("sh")){
			hrList = hrcommentListDao.get(HrCommentList.class, Integer.parseInt(roomId));
			hrList.setState(videoStatus);
			hrcommentListDao.saveOrUpdate(hrList);
		}else if(productName.equals("th")){
			weCourseChild = weCourseChildDao.get(TrainWEProductChild.class, Integer.parseInt(roomId));
			weCourseChild.setStatus(videoStatus);
			weCourseChildDao.saveOrUpdate(weCourseChild);
		}
		
		return null;
	}
	
	
	/*保存评论到评论表*/
	public String saveCommentByAjax(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendTime = df.format(new Date()) ;
		
		/*取出所在房间产品类型*/
		subStringId();
		
		inputWord = inputWord.replace("<", "&lt");
		inputWord = inputWord.replace(">", "&gt");

		System.out.println("productName:"+productName+",roomId:"+roomId);
	
		/* 
		 * localUrl:素材保存到数据的本地的路径
		 * sourceName:从微信服务器下载后的素材名
		 * 1、判断素材是语音还是图片
		 * 2、下载微信服务器上的素材到本地服务器
		 *  */
		String localUrl = null;
		String sourceName = null;
		try {
		if(recordType.equals("0")){
			
			sourceName =WeixinUtil.downloadMediaFromWx(inputWord, "/omg/message/record");
			localUrl = "omg/message/record/"+ sourceName;
			String sourcePath = ServletActionContext.getServletContext().getRealPath("/")+"/"+localUrl;
			
			localUrl = localUrl.replace(".amr", ".mp3");
			String targetPath = ServletActionContext.getServletContext().getRealPath("/")+"/"+localUrl;
			
			AmrToMP3Util.changeToMp3(sourcePath, targetPath);
			FileUploadUtil.deleteFile(sourcePath);

		}else if(recordType.equals("2")){
			sourceName = WeixinUtil.downloadMediaFromWx(inputWord, "/omg/message/images");
			localUrl = "omg/message/images/"+ sourceName;
		}
		} catch (Exception e) {
			System.out.println("download素材时出错，在ChatAction---saveCommentByAjax。");
			e.printStackTrace();
		}
		
		
		/* 判断产品类型，保存评论信息到数据库 */
		if (productName.equals("cp")) {
			
			studentDao.executeSql("insert into C组评论  "
					+ "(评论内容,学生姓名,评论类型,直播间ID,评论时间,头像链接,角色,本地存储地址,自我识别,聊天时长) "
					+ "values('"+inputWord+"','"+stuName+"',"
							+ "'"+recordType+"','"+roomId+"','"+sendTime+"','"+headUrl+"','"+commentRole+"','"+localUrl
							+"','"+roleId+"','"+duration+"')");
		}else if(productName.equals("bp")) {
			studentDao.executeSql("insert into B组评论  "
					+ "(评论内容,学生姓名,评论类型,B体验式课程视频ID,评论时间,头像链接,角色,本地存储地址,自我识别,聊天时长) "
					+ "values('"+inputWord+"','"+stuName+"',"
							+ "'"+recordType+"','"+roomId+"','"+sendTime+"','"+headUrl+"','"+commentRole+"','"+localUrl
							+"','"+roleId+"','"+duration+"')");
		}
		else if(productName.equals("sh")){
			studentDao.executeSql("insert into hr点评表  "
					+ "(评论内容,HR姓名,评论类型,点评列表ID,评论时间,头像链接,角色,本地存储地址,自我识别,聊天时长) "
					+ "values('"+inputWord+"','"+stuName+"',"
							+ "'"+recordType+"','"+roomId+"','"+sendTime+"','"+headUrl+"','"+commentRole+"','"+localUrl
							+"','"+roleId+"','"+duration+"')");
		}else if(productName.equals("th")){
			studentDao.executeSql("insert into 培训we课堂评论表  "
					+ "(评论内容,姓名,评论类型,章节ID,评论时间,头像链接,角色,本地存储地址,自我识别,聊天时长) "
					+ "values('"+inputWord+"','"+stuName+"',"
							+ "'"+recordType+"','"+roomId+"','"+sendTime+"','"+headUrl+"','"+commentRole+"','"+localUrl
							+"','"+roleId+"','"+duration+"')");
		}
		

		
		return null;
	}
	
	/*返还历史评论数据*/
	public String getChatHistory(){
		
		/*取出所在房间产品类型*/
		subStringId();
		
	
		PageBean pageBean = new PageBean(page, pageSize);
		
		System.out.println("取出历史评论函数,productName:"+productName);
		
		if(productName.equals("bp")){
			returnBHistroy(pageBean);
		}else if(productName.equals("cp")) {
			returnCHistroy(pageBean);
		}else if(productName.equals("sh")){
			returnHrHistroy(pageBean);
		}else if(productName.equals("th")){
			returnThHistroy(pageBean);
		}

		return null;
	}
	

	
	/*截取roomId，识别hr、学生、老师身份，并取出房间id*/
	public void subStringId(){
		/*取出所在房间产品类型*/
		try {
			if(roomId.substring(0, 6).equals("Hr1994")){
				productName = roomId.substring(6, 8);
				/*取出所在房间id，相当于该课程ID*/
				roomId = roomId.substring(8,roomId.length());
				commentRole = "hr";
			}else if (roomId.substring(0, 6).equals("Js1994")){
				productName = roomId.substring(6, 8);
				/*取出所在房间id，相当于该课程ID*/
				roomId = roomId.substring(8,roomId.length());
				commentRole = "js";
			}else if(roomId.substring(0, 6).equals("Th1994")){
				productName = roomId.substring(6, 8);
				/*取出所在房间id，相当于该课程ID*/
				roomId = roomId.substring(8,roomId.length());
				commentRole = "th";
			}
		} catch (Exception e) {
			productName = roomId.substring(0, 2);
			/*取出所在房间id，相当于该课程ID*/
			roomId = roomId.substring(2, roomId.length());
		}
	}
	
	public void returnBHistroy(PageBean pageBean){
		
		BProductVideo bpVideo = bpVideoDao.get(BProductVideo.class, Integer.parseInt(roomId));
		String hql = "From BProductComment where BProductVideo=? Order By commentTime desc";
		List<BProductComment> bpComments = bpCommentDao.
				find(hql, new Object[]{bpVideo}, pageBean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = JSON.toJSONString(bpComments);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnCHistroy(PageBean pageBean){
		CProductBroadcast cpBroadcast = cpBroadcasrDao.get(CProductBroadcast.class, Integer.parseInt(roomId));

		String hql = "From CProductComment where CProductBroadcast=? Order By commentTime desc";
		
		List<CProductComment> cpComments =  cpCommentDao.
				find(hql, new Object[]{cpBroadcast},pageBean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = JSON.toJSONString(cpComments);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void returnHrHistroy(PageBean pageBean){
		
		HrCommentList hrCommentList = hrcommentListDao.get(HrCommentList.class, Integer.parseInt(roomId));

		String hql = "From HrComment where hrCommentList=? Order By commentTime desc";
		
		List<HrComment> hrComments = hrCommentDao.find(hql, new Object[]{hrCommentList},pageBean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = JSON.toJSONString(hrComments);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public void returnThHistroy(PageBean pageBean){
		
		TrainWEProductChild weCourseChild = weCourseChildDao.get(TrainWEProductChild.class, Integer.parseInt(roomId));
		
		String hql = "From TrainWEComment where trainWEProductChild=? Order By commentTime desc";
		
		List<TrainWEComment> weComments = weCommentDao.find(hql, new Object[]{weCourseChild},pageBean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = JSON.toJSONString(weComments);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	
	
	/*矫正视频时间，同步播放，。。。。。。。。。。。。。。技术问题目前只是摆放着，可以删掉*/
	public String timeCheckChat() throws ParseException{
		/*取出所在房间产品类型*/
		try {
			if(roomId.substring(0, 6).equals("Hr1994")){
				productName = roomId.substring(6, 8);
				/*取出所在房间id，相当于该课程ID*/
				roomId = roomId.substring(8,roomId.length());
				commentRole = "hr";
			}else if (roomId.substring(0, 6).equals("Js1994")){
				productName = roomId.substring(6, 8);
				/*取出所在房间id，相当于该课程ID*/
				roomId = roomId.substring(8,roomId.length());
				commentRole = "js";
			}
		} catch (Exception e) {
			productName = roomId.substring(0, 2);
			/*取出所在房间id，相当于该课程ID*/
			roomId = roomId.substring(2, roomId.length());
		}

		
		hrCommentListId = roomId;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = df.parse(df.format(new Date()));/*服务器上的当前时间*/
		
		HrCommentList hrCommentList = hrcommentListDao
				.get(HrCommentList.class, Integer.parseInt(hrCommentListId));
		Date startDate = df.parse(hrCommentList.getStartTime());/*数据库上的开始时间*/
		
		long diff = nowDate.getTime() - startDate.getTime();/*与数据对比开始时间差*/

		JSONObject judgeTime = new JSONObject();
		
		if(diff<=0){
			isTime = "false";
			judgeTime.put("isTime", isTime);
		}else {
			isTime = "true";
			diffT = String.valueOf(diff/1000);
			videoUrl = hrCommentList.getVideoUrl();
			judgeTime.put("isTime", isTime);
			judgeTime.put("diff", diffT);
			judgeTime.put("videoUrl", videoUrl);
			
		}
		
		System.out.println("judgeTime"+judgeTime.get("isTime"));
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = JSON.toJSONString(judgeTime);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	
	public BProductVideo getBpVideo() {
		return bpVideo;
	}

	public void setBpVideo(BProductVideo bpVideo) {
		this.bpVideo = bpVideo;
	}
	
	public String getProductName() {
		return productName;
	}

	public CProductBroadcast getCpBroadcast() {
		return cpBroadcast;
	}

	public void setCpBroadcast(CProductBroadcast cpBroadcast) {
		this.cpBroadcast = cpBroadcast;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public HrCommentList getHrList() {
		return hrList;
	}

	public void setHrList(HrCommentList hrList) {
		this.hrList = hrList;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getIsTime() {
		return isTime;
	}

	public void setIsTime(String isTime) {
		this.isTime = isTime;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getHrCommentListId() {
		return hrCommentListId;
	}



	public void setHrCommentListId(String hrCommentListId) {
		this.hrCommentListId = hrCommentListId;
	}



	public String getVideoUrl() {
		return videoUrl;
	}



	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}



	public String getDiffT() {
		return diffT;
	}



	public void setDiffT(String diffT) {
		this.diffT = diffT;
	}

	public String getRoomId() {
		return roomId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getInputWord() {
		return inputWord;
	}

	public void setInputWord(String inputWord) {
		this.inputWord = inputWord;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getCommentRole() {
		return commentRole;
	}

	public void setCommentRole(String commentRole) {
		this.commentRole = commentRole;
	}

	public int getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(int chatStatus) {
		this.chatStatus = chatStatus;
	}

	public int getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(int videoStatus) {
		this.videoStatus = videoStatus;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public TrainWEProductChild getWeCourseChild() {
		return weCourseChild;
	}

	public void setWeCourseChild(TrainWEProductChild weCourseChild) {
		this.weCourseChild = weCourseChild;
	}



}
