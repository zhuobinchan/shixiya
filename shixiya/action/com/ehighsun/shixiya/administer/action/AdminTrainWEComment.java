package com.ehighsun.shixiya.administer.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.TrainWEComment;
import com.ehighsun.shixiya.service.TrainWECommentService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTrainWEComment extends ActionSupport {
	private String page;
	private String ids;
	private Integer id;

	private String mainPage;

	HttpServletRequest request = ServletActionContext.getRequest();

	// 筛选字段
	private String trainWEName;
	private String commentContent;
	private String commentType;

	@Resource(name = "trainWECommentService")
	private TrainWECommentService commentService;

	private List<TrainWEComment> comments;

	// 显示
	public String getAllTrainWEComment() {
		mainPage = "TrainWEComment.jsp";
		comments = commentService.getAllComments(request, page);
		return "getAllTrainWEComment";
	}

	// 删除
	public String delete() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		commentService.deleteCommentById(id);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 删除多条
	public String deleteList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		commentService.deleteComments(ids);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 查询
	public String getByCondition() {
		mainPage = "TrainWEComment.jsp";
		comments = commentService.getCommentsByCondition(trainWEName,
				commentContent, commentType, request, page);
		
		System.out.println("comments.size:"+comments.size());
		return "getByCondition";
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getTrainWEName() {
		return trainWEName;
	}

	public void setTrainWEName(String trainWEName) {
		this.trainWEName = trainWEName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public List<TrainWEComment> getComments() {
		return comments;
	}

	public void setComments(List<TrainWEComment> comments) {
		this.comments = comments;
	}

}
