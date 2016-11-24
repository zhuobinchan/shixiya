package com.ehighsun.shixiya.administer.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.CProductQuestionService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCProductQuestionAction extends ActionSupport {
	private List<CProductQuestionList> questionLists;
	private List<CProductQuestion> questions;
	private List<CProductBroadcast> cpBroadcasts;
	private String mainPage;
	private CProductQuestion question;
	private Integer questionId;
	private String questionIds;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;

	@Resource(name = "cProductQuestionService")
	private CProductQuestionService cProductQuestionService;

	@Resource(name = "baseDao")
	private BaseDao<CProductQuestion> baseDao;

	private String page;
	private HttpServletRequest request = ServletActionContext.getRequest();

	public List<CProductQuestionList> getQuestionLists() {
		return questionLists;
	}

	public void setQuestionLists(List<CProductQuestionList> questionLists) {
		this.questionLists = questionLists;
	}

	public List<CProductQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<CProductQuestion> questions) {
		this.questions = questions;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public CProductQuestion getQuestion() {
		return question;
	}

	public void setQuestion(CProductQuestion question) {
		this.question = question;
	}

	public List<CProductBroadcast> getCpBroadcasts() {
		return cpBroadcasts;
	}

	public void setCpBroadcasts(List<CProductBroadcast> cpBroadcasts) {
		this.cpBroadcasts = cpBroadcasts;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// 获取所有直播间的问题
	public String getAllQuestion() {
		mainPage = "CProductQuestion.jsp";

		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		cpBroadcasts = cpBroadcastService.getAllCPBroadcast();
		questions = cProductQuestionService.getAllCProductQuestions(pageBean);

		Long count = baseDao.count("select count(*) from CProductQuestion");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllQuestion_AdminCProductQuestionAction.action",
				count, Integer.parseInt(page), 10, null);

		ActionContext.getContext().getSession().put("pageCode", pageCode);
		return "getAllQuestion";
	}

	public String saveOrUpdateQuestion() {
		System.out.println(question.getId());
		System.out.println(question.getCProductBroadcast().getId());
		cpBroadcasts = cpBroadcastService.getAllCPBroadcast();
		cProductQuestionService.saveOrUpdateQuestion(question);
		return "saveOrUpdateQuestion";
	}

	// 删除问题
	public String deleteQuestion() throws Exception {
		System.out.println(questionId);

		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();

		cProductQuestionService.deleteQuestion(questionId);
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return "deleteQuestion";
	}

	public String deleteQuestionList() throws Exception {
		System.out.println(questionIds);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		cProductQuestionService.deleteQuestions(questionIds);
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return "deleteQuestionList";
	}

	public String findByCondition() {
		mainPage = "CProductQuestion.jsp";

		cpBroadcasts = cpBroadcastService.getAllCPBroadcast();
		questions = cProductQuestionService.findByCondition(question,request,page);

		return "findByCondition";
	}

}
