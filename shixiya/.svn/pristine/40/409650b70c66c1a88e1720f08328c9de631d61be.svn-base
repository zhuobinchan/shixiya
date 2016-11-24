package com.ehighsun.shixiya.administer.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductCommentService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCProductCommentAction extends ActionSupport {
	private List<CProductComment> comments;
	private Integer commentId;
	private String commentIds;
	private String broadcastName;
	private Integer commentType;
	private String commentContent;

	@Resource(name = "cProductCommentService")
	private CProductCommentService cProductCommentService;

	private String mainPage;
	private String page;
	private HttpServletRequest request = ServletActionContext.getRequest();

	public List<CProductComment> getComments() {
		return comments;
	}

	public void setComments(List<CProductComment> comments) {
		this.comments = comments;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(String commentIds) {
		this.commentIds = commentIds;
	}

	public String getBroadcastName() {
		return broadcastName;
	}

	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	// 通过直播间名称和评论类型进行筛选评论
	public String getCommentsByCondition() {
		mainPage = "CProductComment.jsp";

		comments = cProductCommentService.getCommentsByCondition(
				commentContent, broadcastName, commentType, request, page);

		return SUCCESS;
	}

	// 判断对象是否为空或者不错在值
	public boolean isNullOrEmpty(Object object) {
		return object == null || object.equals("");
	}

	// 获取所有评论
	public String getAllComments() {
		mainPage = "CProductComment.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		comments = cProductCommentService.getAllComments(pageBean);

		Long count = cProductCommentService
				.countComment("select count(*) from CProductComment");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllComments_AdminCProductCommentAction.action",
				count, Integer.parseInt(page), 10, null);

		ActionContext.getContext().getSession().put("pageCode", pageCode);

		return SUCCESS;
	}

	// 删除评论
	public String deleteComment() throws Exception {
		System.out.println(commentId);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		cProductCommentService.deleteComment(commentId);
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return SUCCESS;
	}

	// 批量删除评论
	public String deleteCommentList() throws Exception {
		System.out.println(commentIds);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();

		cProductCommentService.deleteCommentList(commentIds);

		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return SUCCESS;

	}

}
