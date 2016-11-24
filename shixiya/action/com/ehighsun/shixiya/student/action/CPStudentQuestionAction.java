package com.ehighsun.shixiya.student.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.CProductAnswerService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.websocket.Answer;
import com.opensymphony.xwork2.ActionSupport;

public class CPStudentQuestionAction extends ActionSupport {

	private Integer broadcastId;
	private CProductBroadcast cpBroadcast;
	private String answer;
	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "cProductAnswerService")
	private CProductAnswerService cProductAnswerService;

	Map<String, Object> session = ServletActionContext.getContext()
			.getSession();

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public CProductBroadcast getCpBroadcast() {
		return cpBroadcast;
	}

	public void setCpBroadcast(CProductBroadcast cpBroadcast) {
		this.cpBroadcast = cpBroadcast;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionsByBroadcast() {
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");

		cpBroadcast = cpBroadcastService.findById(broadcastId);
		return "getQuestionsByStuent";
	}

	public String getAnswerByStudent() throws Exception {
		// Integer studentId = (Integer) session.get("student");
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");
		HttpServletResponse response = ServletActionContext.getResponse();

		JSONObject json = new JSONObject();

		List<Answer> answers = JSON.parseArray(answer, Answer.class);
		if (answers.size() != 0) {
			if (!isAnswer(student.getId(), broadcastId)) {
				for (Answer answer : answers) {
					System.out.println(answer.getTitleId());
					System.out.println(answer.getAnswer());
					cProductAnswerService.addAnswer(student.getId(),
							Integer.parseInt(answer.getTitleId()),
							answer.getAnswer());
				}
			} else {
				json.put("message", "您已经答题,这次答题无效");
			}

		}

		json.put("success", "true");
		json.put("broadcastId", broadcastId);

		ResponseUtil.write(response, json.toString());
		return null;
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
