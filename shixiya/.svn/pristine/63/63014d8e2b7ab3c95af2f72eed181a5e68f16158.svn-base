package com.ehighsun.shixiya.administer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.CProductAnswer;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.service.CProductAnswerService;
import com.ehighsun.shixiya.service.CProductQuestionListService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminCProductAnswerAction extends BaseAction<CProductAnswer> {


	private String sname;
	private Integer questionId;
	
	private String page;
	private String ids;
	
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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
	
	@Resource(name="cProductAnswerService")
	private CProductAnswerService cProductAnswerService;
	@Resource(name="cProductQuestionListService")
	private CProductQuestionListService cProductQuestionListService;
	@Resource(name="adminStudentService")
	private AdminStudentService adminStudentService;
	
	public String showAnswer(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "CProductAnswer.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<CProductAnswer> list = cProductAnswerService.findAnswers("from CProductAnswer", null, pageBean);
		Long count = cProductAnswerService.countAnwer("select count(*) from CProductAnswer");
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminAnswer_showAnswer.action", count, Integer.parseInt(page),6,null);
		
		List<CProductQuestionList> qlist = cProductQuestionListService.getAllQuestionLists();
		
		this.getSession().setAttribute("answerList", list);
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("questionList", qlist);
		
		return "showAnswer";
	}
	
	public String deleteAnswer(){

		cProductAnswerService.deleteAnswer(model);
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deleteAnswer";
	}
	
	public String deleteAnswers(){
		
		String[] str = ids.split(",");

		for (int i = 0; i < str.length; i++) {	
			CProductAnswer answer = new CProductAnswer();
			answer.setId(Integer.parseInt(str[i]));
			cProductAnswerService.deleteAnswer(answer);
		}
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "deleteAnswers";
	}
	
	public String findByMap(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "CProductAnswer.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		
		Map<String, Object> o = new HashMap<String, Object>();
		
		StringBuilder sb1 = new StringBuilder();
		
		if(sname!=null && !"".equals(sname)){
		
			Student s =  adminStudentService.findStudentByName(sname);
			o.put("student", s);
			sb1.append("sname="+sname);
			
		}

		if(questionId!=null){
			CProductQuestionList ql = new CProductQuestionList();
			ql.setId(questionId);
			o.put("CProductQuestionList", ql);
			if(sb1.toString()!=null){
				sb1.append("&questionId="+questionId);
			}else{
				sb1.append("questionId="+questionId);
			}
		}
		List<CProductAnswer> alist = cProductAnswerService.findAnswer("from CProductAnswer", o, pageBean);
		
		Long count = cProductAnswerService.countAnwer("select count(*) from CProductAnswer", o);
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminAnswer_findByMap.action?"+sb1.toString(), count, Integer.parseInt(page),6,null);
		
		List<CProductQuestionList> qlist = cProductQuestionListService.getAllQuestionLists();
		
		this.getSession().setAttribute("answerList", alist);
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("questionList", qlist);
		
		return "findByMap";
	}
	
	
}
