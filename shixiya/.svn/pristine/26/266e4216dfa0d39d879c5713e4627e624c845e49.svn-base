package com.ehighsun.shixiya.student.action;

import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StudentInfomationAction extends ActionSupport {
	private String name;
	private String telephone;
	private String email;
	private String message;
	
	private String addWhichApply;
	private int broadcastId;
	private int courseId;
	private Integer recruitId;
	private int fifthId;
	
	private String targetUrl;
	
	private String isFromPersonResume;

	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	@Resource(name = "adminStudentService")
	private AdminStudentService studentService;

	private static final String email_regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	private static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

	public String writeStudentInfo() {

		Student student = (Student) sessionMap.get("student");
		
		if (broadcastId!=0) {
			addWhichApply = "addCpApply";
		}else if(courseId != 0) addWhichApply = "addBpApply";

		if (StringUtil.isNotEmpty(name)) {
			student.setName(name);
		} else {
			message = "姓名不能为空 或者姓名格式不对";
			return "error";
		}
		if (StringUtil.isNotEmpty(email)
				&& Pattern.matches(email_regexp, email)) {
			student.setEmail(email);
		} else {
			message = "电子邮箱不能为空 或者电子邮箱格式不对";
			return "error";
		}
		if (StringUtil.isNotEmpty(telephone)
				&& Pattern.matches(phone_regexp, telephone)) {
			student.setTelephone(telephone);
		} else {
			message = "电话号码不能为空 或者电话号码格式不对";
			return "error";
		}

		studentService.updateStudent(student);
		sessionMap.remove("student");
		sessionMap.put("student", student);
		
		System.out.println("isFromPersonResume:"+isFromPersonResume+",recruitId:"+recruitId);
		
		if (recruitId!=null){
			
			targetUrl = "applyRecruit_RecruitAction?recruitId="+recruitId;
			
			return "gotoTargetUrl";
		}
		
		
		if (isFromPersonResume.equals("true")) {
			System.out.println("我是从个人中心填写的简历。");
			return "onlyWriteInfo";
		}else if (isFromPersonResume.equals("from_fifth")) {
			return "from_fifth";
		}
		
		return "writeStudentInfo";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getAddWhichApply() {
		return addWhichApply;
	}

	public void setAddWhichApply(String addWhichApply) {
		this.addWhichApply = addWhichApply;
	}

	public String getIsFromPersonResume() {
		return isFromPersonResume;
	}

	public void setIsFromPersonResume(String isFromPersonResume) {
		this.isFromPersonResume = isFromPersonResume;
	}

	public int getFifthId() {
		return fifthId;
	}

	public void setFifthId(int fifthId) {
		this.fifthId = fifthId;
	}

	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
}
