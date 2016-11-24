package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.service.ResumeService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class ResumeAction extends BaseAction<Resume>{

	
	private String page;
	private String ids;
	
	@Resource(name="resumeService")
	private ResumeService resumeService;

	
	
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
	
	public String showResume(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "resume.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Resume> list = resumeService.findResume("from Resume", null, pageBean);
		Long count = resumeService.countResume("select count(*) from Resume");
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminResume_showResume.action", count, Integer.parseInt(page),6,null);
	
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("resumes", list);
	
		
		return "showResume";
	}
	
	
	public String deleteResume(){
		
		Resume resume = resumeService.findResumeById(model.getId());
		resumeService.deleteResume(resume);
		
		
		return "deleteResume";
	}
	
	public String deleteResumes(){
		
			String[] str = ids.split(",");
			Resume resume = null;
			for (int i = 0; i < str.length; i++) {
				resume = resumeService.findResumeById(Integer.parseInt(str[i]));
				resumeService.deleteResume(resume);
			}
			JSONObject result=new JSONObject();
			result.put("success", true);
			try {
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return "deleteResumes";
	}
	
	
	public String findByName(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "resume.jsp");
		
		List<Resume> list = resumeService.findResumeByName(model.getName());
		this.getSession().setAttribute("resumes", list);
		
		return "findByName";
	}
	
}
