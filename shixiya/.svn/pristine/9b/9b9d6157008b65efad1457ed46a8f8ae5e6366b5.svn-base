package com.ehighsun.shixiya.hr.action;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductEmploy;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.CProductEmployService;
import com.ehighsun.shixiya.service.HrCheckAnswerService;
import com.ehighsun.shixiya.websocket.HrCheckAnswer;
import com.opensymphony.xwork2.ActionSupport;

public class CPHrCheckAnswerAction extends ActionSupport {
	private Integer broadcastId;
	private List<HrCheckAnswer> hrCheckAnswers;
	private List<CProductEmploy> employs;
	private CProductBroadcast broadcast;
	@Resource(name = "hrCheckAnswerService")
	private HrCheckAnswerService hrCheckAnswerService;
	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "cProductEmployService")
	private CProductEmployService cpEmployService;

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public List<HrCheckAnswer> getHrCheckAnswers() {
		return hrCheckAnswers;
	}

	public void setHrCheckAnswers(List<HrCheckAnswer> hrCheckAnswers) {
		this.hrCheckAnswers = hrCheckAnswers;
	}

	public List<CProductEmploy> getEmploys() {
		return employs;
	}

	public void setEmploys(List<CProductEmploy> employs) {
		this.employs = employs;
	}

	public CProductBroadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(CProductBroadcast broadcast) {
		this.broadcast = broadcast;
	}

	// hr获取根据直播间获取学生答案
	public String showQuestion() {
		try {
			broadcast = cpBroadcastService.findById(broadcastId);
			if (cpEmployService.countEmployByBroadcast(broadcast) == 0) {
				hrCheckAnswers = hrCheckAnswerService
						.getHrCheckAnswer(broadcastId);
				return "showQuestion";
			}
			employs = cpEmployService.getCProductEmploysByBroadcast(broadcast);
			return "employMessage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
