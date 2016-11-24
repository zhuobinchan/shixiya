package com.ehighsun.shixiya.student.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.service.HrAdminServer;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CPstudentHrWeekAction extends ActionSupport {

	private List<HrCommentList> hrCommentLists;
	private HrCommentList hrCommentList;
	private List<HrComment> hrComments;
	
	
	private int id;
	private int hrId;
	private int lableId;
	
	@Autowired
	private HrAdminServer hrAdminServer;
	
	
	/*获取Hr周点评列表*/
	public String showHrCommentList(){
		
		hrCommentLists = hrAdminServer.getAll();
		return "showHrCommentList";
	}
	
	/*获取Hr周点评列表ByAjax*/
	public String getHrCommentListByAjax(){
		
		hrCommentLists = hrAdminServer.getAll();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(HrCommentList.class,
				"id","title","introduction","publicTime","startTime","state","videoUrl","imageUrl");
		
		String result = JSON.toJSONString(hrCommentLists,filter);
		
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*通过标签返还筛选Hr周点评列表*/
	public String lableFilter(){
		
		
		
		return "lableFilter";
	}
	
	public String showHrCommentHistory(){
		
		hrComments = hrAdminServer.getHrCommentsByHrid(hrId);
		
		return "showHrCommentHistory";
	}
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHrId() {
		return hrId;
	}
	public void setHrId(int hrId) {
		this.hrId = hrId;
	}

	public int getLableId() {
		return lableId;
	}

	public void setLableId(int lableId) {
		this.lableId = lableId;
	}

	public List<HrCommentList> getHrCommentLists() {
		return hrCommentLists;
	}

	public void setHrCommentLists(List<HrCommentList> hrCommentLists) {
		this.hrCommentLists = hrCommentLists;
	}

	public HrCommentList getHrCommentList() {
		return hrCommentList;
	}

	public void setHrCommentList(HrCommentList hrCommentList) {
		this.hrCommentList = hrCommentList;
	}

	public List<HrComment> getHrComments() {
		return hrComments;
	}

	public void setHrComments(List<HrComment> hrComments) {
		this.hrComments = hrComments;
	}
	
	
	
	
	
}
