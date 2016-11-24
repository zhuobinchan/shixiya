package com.ehighsun.shixiya.administer.action;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.dao.BProductApplyDao;

import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.opensymphony.xwork2.ActionSupport;

public class BProductStudentApplyAction extends ActionSupport{
	
	private BProductStudentApply bProductStudentApply;
	private Resume resume;
	
	
	
	public BProductStudentApply getbProductStudentApply() {
		return bProductStudentApply;
	}



	public void setbProductStudentApply(BProductStudentApply bProductStudentApply) {
		this.bProductStudentApply = bProductStudentApply;
	}



	public Resume getResume() {
		return resume;
	}



	public void setResume(Resume resume) {
		this.resume = resume;
	}



//	@Autowired
//	private ResumeDao resumeDao;
	@Autowired
	private BProductApplyDao bProductStudentApplyDao;
	
	public String addApply(){
		
		Student student = (Student) this.getSession().getAttribute("user");
		
//		resume.setStudent(student);
//		resumeDao.save(resume);
		
		bProductStudentApply.setStudent(student);
		bProductStudentApply.setStates(1);
		bProductStudentApplyDao.save(bProductStudentApply);
		
		return "addApplySuccess";
	}
	
	public String deleteApply(){
		
		bProductStudentApplyDao.deleteBProductApply(bProductStudentApply);
		return "deleteApplySuccess";
	}
	
	public String findApply(){
		
		List<BProductStudentApply> lists = bProductStudentApplyDao.findBProductApply();
		this.getSession().setAttribute("applys", lists);
		
		return "findApplySuccess";
	}
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	
//
//
////	@Autowired
////	private ResumeDao resumeDao;
//	@Autowired
//	private BProductApplyDao bProductStudentApplyDao;
//	
//	public String addApply(){
//		
//		Student student = (Student) this.getSession().getAttribute("user");
//		
//		resume.setStudent(student);
//		resumeDao.save(resume);
//		
//		bProductStudentApply.setStudent(student);
//		bProductStudentApply.setStates(1);
//		bProductStudentApplyDao.save(bProductStudentApply);
//		
//		return "addApplySuccess";
//	}
//	
//	public String deleteApply(){
//		
//		bProductStudentApplyDao.deleteBProductApply(bProductStudentApply);
//		return "deleteApplySuccess";
//	}
//	
//	public String findApply(){
//		
//		List<BProductStudentApply> lists = bProductStudentApplyDao.findBProductApply();
//		this.getSession().setAttribute("applys", lists);
//		
//		return "findApplySuccess";
//	}
//	
//	public HttpSession getSession(){
//		return ServletActionContext.getRequest().getSession();
//	}
//	
//	
}
