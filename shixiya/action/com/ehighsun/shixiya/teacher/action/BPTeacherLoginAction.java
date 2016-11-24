package com.ehighsun.shixiya.teacher.action;

import java.util.Map;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.service.BProductTeacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BPTeacherLoginAction extends ActionSupport {
	private String password;
	private String telephone;
	private BProductTeacher bpTeacher;
	private String errorMessage;
	Map<String, Object> session = ActionContext.getContext().getSession();

	@Resource(name = "bProductTeacherService")
	private BProductTeacherService bProductTeacherService;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BProductTeacher getBpTeacher() {
		return bpTeacher;
	}

	public void setBpTeacher(BProductTeacher bpTeacher) {
		this.bpTeacher = bpTeacher;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String teacherLogin() {
		try {
			session.remove("hr");
			session.remove("bpTeacher");
			session.remove("trainer");
			session.remove("student");
		} catch (Exception e) {
			System.out.println("清除其他残余session");
		}
		
		if (bProductTeacherService.bpTeacherLogin(telephone, password)) {
			System.out.println("login:ok");
			bpTeacher = bProductTeacherService.findByTelephone(telephone);
			System.out.println(bpTeacher.getId());
			session.put("bpTeacher", bpTeacher);
			return "teacherLogin";
		}
		errorMessage = "密码错误或者账户不存在";
		return "error";
	}
}
