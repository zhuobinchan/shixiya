package com.ehighsun.shixiya.hr.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.format.Printer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dayee.wintalent.service.v8.pojo.PersonalInformations;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CPHrCheckApplyAction extends ActionSupport {
	
	private Set<CProductStudentApply> cpStuApplys;
	private CProductBroadcast cpBroadcast;
	private Set<BProductStudentApply> bpStuApplys;
	private BProductCourse bpCourse;
	
	private int broadcastId;
	private int courseId;
	private int[] studentId;
	
	private String applyCheck;
	private String applyUnCheck;
	
	private String stuName;
	private String stuSchool;
	private String stuMajor;
	private String stuGraduaDate;
	
	@Resource(name="baseDao")
	private BaseDao<CProductBroadcast> cpBroadcastDao;
	@Resource(name="baseDao")
	private BaseDao<BProductCourse> bpCourseDao;
	
	public String findStuApply(){
		
		cpBroadcast = cpBroadcastDao.get(CProductBroadcast.class, broadcastId);
		cpStuApplys = cpBroadcast.getCProductStudentApplies();
		
		Iterator<CProductStudentApply> cpStuApplys_It = cpStuApplys.iterator();  
		while (cpStuApplys_It.hasNext()) {  
			
			CProductStudentApply cpApply = cpStuApplys_It.next();  
			
			Set<Resume> resumes = cpApply.getStudent().getResumes();
			Resume resume = null;
			for (Resume resume1:resumes) {
				resume = resume1;
				break;
			}
			
			if (resume!=null) {
				/*用户个人信息jsonString编程object*/
	//			PersonalInformations personalInformations = JSONObject.parseObject(resume.getPersonalInformation(), PersonalInformations.class);
				/*获取用户毕业学校、专业、毕业时间*/
				String school = resume.getSchool(); /*personalInformations.getSchoolName();*/
				String major = resume.getMajor(); /*personalInformations.getMajor();*/
				String graduationDate = resume.getGraduatTime(); /*personalInformations.getGraduateDate();*/
				
				if(stuName!=null && !stuName.equals("") && cpApply.getStudent().getName().indexOf(stuName) == -1) {cpStuApplys_It.remove();continue;}
				if(stuSchool!=null && !stuSchool.equals("") && school.indexOf(stuSchool) == -1) {cpStuApplys_It.remove();continue;}
				if(stuMajor!=null && !stuMajor.equals("") && major.indexOf(stuMajor) == -1) {cpStuApplys_It.remove();continue;}
				
				/*判断用户毕业时间，通过除去‘-’作时间判断*/
				if(stuGraduaDate!=null && !stuGraduaDate.equals("")){
					System.out.println("graduationDate:"+graduationDate);
					String graduation_resume = graduationDate.replaceAll("-", "").substring(0,(graduationDate.length()-4));
					String graduation_jsp = stuGraduaDate.replaceAll("-", "").substring(0,(stuGraduaDate.length()-4));
					
					if(!graduation_jsp.equals(graduation_resume)) cpStuApplys_It.remove();
				}
			}
		}
		
		return "findStuApply";
	}
	
	
	/*筛选学生报名*/
	public String findBpStuApply() throws ParseException{
		bpCourse = bpCourseDao.get(BProductCourse.class,courseId);
		bpStuApplys = bpCourse.getBProductStudentApplies();
		
		Iterator<BProductStudentApply> bpStuApplys_It = bpStuApplys.iterator();  
		while (bpStuApplys_It.hasNext()) {  
			BProductStudentApply bpApply = bpStuApplys_It.next();  
			
			Set<Resume> resumes = bpApply.getStudent().getResumes();
			Resume resume = null;
			for (Resume resume1:resumes) {
				resume = resume1;
				break;
			}
			
			if (resume!=null) {
				/*用户个人信息jsonString编程object*/
	//			PersonalInformations personalInformations = JSONObject.parseObject(resume.getPersonalInformation(), PersonalInformations.class);
				/*获取用户毕业学校、专业、毕业时间*/
				String school = resume.getSchool(); /*personalInformations.getSchoolName();*/
				String major = resume.getMajor(); /*personalInformations.getMajor();*/
				String graduationDate = resume.getGraduatTime(); /*personalInformations.getGraduateDate();*/
				
				if(stuName!=null && !stuName.equals("") && bpApply.getStudent().getName().indexOf(stuName) == -1) {bpStuApplys_It.remove();continue;}
				if(stuSchool!=null && !stuSchool.equals("") && school.indexOf(stuSchool) == -1) {bpStuApplys_It.remove();continue;}
				if(stuMajor!=null && !stuMajor.equals("") && major.indexOf(stuMajor) == -1) {bpStuApplys_It.remove();continue;}
				
				/*判断用户毕业时间，通过除去‘-’作时间判断*/
				if(stuGraduaDate!=null && !stuGraduaDate.equals("")){
					String graduation_resume = graduationDate.replaceAll("-", "").substring(0,(graduationDate.length()-4));
					String graduation_jsp = stuGraduaDate.replaceAll("-", "").substring(0,(stuGraduaDate.length()-4));
					
					if(!graduation_jsp.equals(graduation_resume)) bpStuApplys_It.remove();
				}
			}
					
			
			
		}  

		
		return "findBpStuApply";
	}
	

	
	public String applySuccess(){
		

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		String[] checks = applyCheck.split(",");
		String[] unChecks = applyUnCheck.split(",");
		try {
		/*当未选上时，字符串分割还是会有1的长度，内容为""*/
		if( !(checks.length==1 && checks[0].equals("")) ){	
			for(int i=0;i < checks.length;i++){
				cpBroadcastDao.executeSql("update c组团报名表 set 审核状态=1 where 学生ID="+checks[i]+" && 直播ID="+broadcastId);
			}
		}
		
		if( !(unChecks.length==1 && unChecks[0].equals("")) ){	
			for(int i=0;i < unChecks.length;i++){
				cpBroadcastDao.executeSql("update c组团报名表 set 审核状态=2 where 学生ID="+unChecks[i]+" && 直播ID="+broadcastId);
			}	
		}
		
		
			out = response.getWriter();
			out.print("true");
		} catch (Exception e) {
			System.out.println("CPHrCheckApplyAction 中 update 直播间   报名表时发生错误！");
			e.printStackTrace();
		} 
		
		return null;
//		return "applySuccess";
	}
	
	public String applyBpSuccess(){
		

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		String[] checks = applyCheck.split(",");
		String[] unChecks = applyUnCheck.split(",");
		

		try {
		if( !(checks.length==1 && checks[0].equals("")) ){		
			for(int i=0;i < checks.length;i++){
				cpBroadcastDao.executeSql("update b体验式课程学生报名表 set 审核状态=1 where 学生ID="+checks[i]+" && 课程ID="+courseId);
			}
		}
		
		if( !(unChecks.length==1 && unChecks[0].equals("")) ){	
			for(int i=0;i < unChecks.length;i++){
				cpBroadcastDao.executeSql("update b体验式课程学生报名表 set 审核状态=2 where 学生ID="+unChecks[i]+" && 课程ID="+courseId);
			}	
		}
		
			out = response.getWriter();
			out.print("true");
		} catch (Exception e) {
			System.out.println("CPHrCheckApplyAction 中 update 课程   报名表时发生错误！");
			e.printStackTrace();
		} 
		
		return null;
//		return "applySuccess";
	}

	public int getBroadcastId() {
		return broadcastId;
	}


	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}


	public Set<CProductStudentApply> getCpStuApplys() {
		return cpStuApplys;
	}


	public void setCpStuApplys(Set<CProductStudentApply> cpStuApplys) {
		this.cpStuApplys = cpStuApplys;
	}

	public int[] getStudentId() {
		return studentId;
	}

	public void setStudentId(int[] studentId) {
		this.studentId = studentId;
	}

	public String getApplyCheck() {
		return applyCheck;
	}

	public void setApplyCheck(String applyCheck) {
		this.applyCheck = applyCheck;
	}

	public String getApplyUnCheck() {
		return applyUnCheck;
	}

	public void setApplyUnCheck(String applyUnCheck) {
		this.applyUnCheck = applyUnCheck;
	}

	public Set<BProductStudentApply> getBpStuApplys() {
		return bpStuApplys;
	}

	public void setBpStuApplys(Set<BProductStudentApply> bpStuApplys) {
		this.bpStuApplys = bpStuApplys;
	}

	public BProductCourse getBpCourse() {
		return bpCourse;
	}

	public void setBpCourse(BProductCourse bpCourse) {
		this.bpCourse = bpCourse;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public String getStuSchool() {
		return stuSchool;
	}


	public void setStuSchool(String stuSchool) {
		this.stuSchool = stuSchool;
	}


	public String getStuMajor() {
		return stuMajor;
	}


	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}


	public String getStuGraduaDate() {
		return stuGraduaDate;
	}


	public void setStuGraduaDate(String stuGraduaDate) {
		this.stuGraduaDate = stuGraduaDate;
	}

}