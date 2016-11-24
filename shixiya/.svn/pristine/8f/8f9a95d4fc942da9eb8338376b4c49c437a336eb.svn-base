package com.ehighsun.shixiya.student.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.CProductAnswerService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.ResponseUtil;

public class ApplyEnterAction {
	private Integer broadcastId;
	private String courseId;
	private String videoId;
	private CProductBroadcast broadcast;
	private BProductCourse course;
	private BProductVideo video;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "bProductCourseService")
	private BProductCourseService bpCourseService;
	@Resource(name = "baseDao")
	private BaseDao<BProductVideo> videoDao;
	@Resource(name = "cProductAnswerService")
	private CProductAnswerService cProductAnswerService;

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public BProductCourse getCourse() {
		return course;
	}

	public void setCourse(BProductCourse course) {
		this.course = course;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public CProductBroadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(CProductBroadcast broadcast) {
		this.broadcast = broadcast;
	}

	public BProductVideo getVideo() {
		return video;
	}

	public void setVideo(BProductVideo video) {
		this.video = video;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String broadcastApplyEnter() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		JSONObject json = new JSONObject();
		broadcast = cpBroadcastService.findById(broadcastId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = df.parse(df.format(new Date()));/* 服务器上的当前时间 */
		Date startDate = df.parse(broadcast.getStartTime());
		Date endDate = df.parse(broadcast.getEndTime());

		if (startDate.getTime() - 600000 > nowDate.getTime()) {// 答题前
			json.put("error", "true");
			json.put("message", "时间没有到,请提前10分钟进入内进入直播间");
		}
		if (startDate.getTime() - 600000 < nowDate.getTime()
				&& nowDate.getTime() < startDate.getTime()) {// 在10分钟内 可以进入 答题中
			json.put("success", "true");
			json.put("broadcastId", broadcastId);
			Student student = (Student) ServletActionContext.getContext()
					.getSession().get("student");
			System.out.println(isAnswer(student.getId(), broadcastId));
			if (isAnswer(student.getId(), broadcastId)) {
				json.put("isAnswer", "true");
			}
		}
		if (startDate.getTime() < nowDate.getTime()
				&& nowDate.getTime() < endDate.getTime()) {// 正在直播进入
			json.put("broadcastId", broadcastId);
			json.put("success", "false");
			json.put("message", "直播间正在开始,欢迎光临");
		}
		if (endDate.getTime() < nowDate.getTime()) {// 直播间已经结束 可以进入
			json.put("broadcastId", broadcastId);
			json.put("success", "false");
			json.put("message", "直播间已经结束，播的是回放");
		}

		ResponseUtil.write(response, json.toString());

		return null;
	}

	public String courseApplyEnter() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		JSONObject json = new JSONObject();
		System.out.println("courseApplyEnter:"+courseId);
		course = bpCourseService.findBCourseById(Integer.parseInt(courseId));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date nowDate = df.parse(df.format(new Date()));/* 服务器上的当前时间 */
		Date startDate = df.parse(course.getStartTime());
		Date endDate = df.parse(course.getEndTime());

		/* 判断课程时候到时间观看 */
		json = judgeStartTime(nowDate, startDate, endDate);

		/* 若课程观看时间到了，则判断视频是否到观看时间 */
		if (json.get("error") == null && videoId != null) {

			json.remove("success");
			json.remove("courseId");
			json.remove("message");

			video = videoDao
					.get(BProductVideo.class, Integer.parseInt(videoId));

			nowDate = df.parse(df.format(new Date()));/* 服务器上的当前时间 */
			startDate = df.parse(video.getStartTime());
			endDate = df.parse(video.getEndTime());

			json = judgeVideoStartTime(nowDate, startDate, endDate, courseId);

		}

		ResponseUtil.write(response, json.toString());
		return null;
	}

	public JSONObject judgeStartTime(Date nowDate, Date startDate, Date endDate) {
		JSONObject json = new JSONObject();
		if (startDate.getTime() > nowDate.getTime()) {// 课程前
			json.put("error", "true");
			json.put("message", "课程还没有开始");
		}

		if (startDate.getTime() < nowDate.getTime()
				&& nowDate.getTime() < endDate.getTime()) {// 课程中
			json.put("success", "true");
			json.put("message", "课程已经开始");
		}
		if (endDate.getTime() < nowDate.getTime()) {// 课程接受后
			json.put("success", "false");
			json.put("message", "课程已经结束，播的是回放");
		}

		return json;
	}

	public JSONObject judgeVideoStartTime(Date nowDate, Date startDate,
			Date endDate, String id) {
		JSONObject json = new JSONObject();
		
		if (startDate.getTime() < nowDate.getTime()
				&& nowDate.getTime() < endDate.getTime()) {// 正在直播能进入
			json.put("success", "true");
			json.put("message", "课堂正在开始，欢迎参与");
			json.put("courseId", id);
		}
		if (endDate.getTime() < nowDate.getTime()) {// 直播间已经结束 可以进入
			json.put("success", "false");
			json.put("message", "课堂已经结束，播的是回放");
			json.put("courseId", id);
		}

		return json;
	}

	private boolean isAnswer(Integer studentId, Integer broadcastId) {

		List<Integer> studentAnswers = cProductAnswerService
				.getAnswersByStudentId(studentId);
		List<Integer> broadcastAnswers = cProductAnswerService
				.getAnswersByBroadcastId(broadcastId);
		return !Collections.disjoint(studentAnswers, broadcastAnswers);// disjoint函数是
																		// true代表没有交集；false代表有交集

	}

}
