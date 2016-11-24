package com.ehighsun.shixiya.administer.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductQuestionListService;
import com.ehighsun.shixiya.service.CProductQuestionService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCProductQuestionListAction extends ActionSupport {
	private List<CProductQuestionList> questionLists;
	private List<CProductQuestion> questions;

	private CProductQuestionList cpQuestionList;
	private Integer cpQuestionId;
	private String questionListTitle;

	private Integer cpQustionListId;
	private String cpQustionListIds;

	private String mainPage;

	@Resource(name = "cProductQuestionListService")
	private CProductQuestionListService cpqlService;

	@Resource(name = "cProductQuestionService")
	private CProductQuestionService cpqService;
	@Resource(name = "baseDao")
	private BaseDao<CProductQuestionList> baseDao;

	private String page;

	private HttpServletRequest request = ServletActionContext.getRequest();

	public List<CProductQuestionList> getQuestionLists() {
		return questionLists;
	}

	public void setQuestionLists(List<CProductQuestionList> questionLists) {
		this.questionLists = questionLists;
	}

	public CProductQuestionList getCpQuestionList() {
		return cpQuestionList;
	}

	public void setCpQuestionList(CProductQuestionList cpQuestionList) {
		this.cpQuestionList = cpQuestionList;
	}

	public Integer getCpQustionListId() {
		return cpQustionListId;
	}

	public void setCpQustionListId(Integer cpQustionListId) {
		this.cpQustionListId = cpQustionListId;
	}

	public String getCpQustionListIds() {
		return cpQustionListIds;
	}

	public void setCpQustionListIds(String cpQustionListIds) {
		this.cpQustionListIds = cpQustionListIds;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<CProductQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<CProductQuestion> questions) {
		this.questions = questions;
	}

	public Integer getCpQuestionId() {
		return cpQuestionId;
	}

	public void setCpQuestionId(Integer cpQuestionId) {
		this.cpQuestionId = cpQuestionId;
	}

	public String getQuestionListTitle() {
		return questionListTitle;
	}

	public void setQuestionListTitle(String questionListTitle) {
		this.questionListTitle = questionListTitle;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// 通过直播间名称和评论类型进行筛选评论
	public String getCPQustionListByCondition() {
		mainPage = "CProductQuestionList.jsp";
		questions = cpqService.getAllCProductQuestions();
		questionLists = cpqlService.getQuestionListsByCondition(
				questionListTitle, cpQuestionId, request, page);

		return SUCCESS;
	}

	public String saveOrUpdateQuestionList() {

		System.out.println(cpQuestionList.getId());
		System.out.println(cpQuestionList.getTitle());

		cpqlService.saveOrUpdate(cpQuestionList);

		return "saveOrUpdateQuestionList";
	}

	// 获取所有问题详细数据
	public String getAllQuestionLists() {
		mainPage = "CProductQuestionList.jsp";

		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		questions = cpqService.getAllCProductQuestions();
		questionLists = cpqlService.getAllQuestionLists(pageBean);

		Long count = baseDao.count("select count(*) from CProductQuestionList");

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/getAllQuestionLists_AdminCProductQuestionListAction.action",
						count, Integer.parseInt(page), 10, null);

		ActionContext.getContext().getSession().put("pageCode", pageCode);
		return SUCCESS;
	}

	// 删除问题详细数据
	public String deleteQuestionList() throws Exception {
		System.out.println(cpQustionListId);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		cpqlService.deleteQuestionList(cpQustionListId);
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return SUCCESS;
	}

	// 批量删除问题详细数据
	public String deleteQuestionLists() throws Exception {
		System.out.println(cpQustionListIds);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();

		cpqlService.deleteQuestionLists(cpQustionListIds);

		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return SUCCESS;

	}

}
