package com.ehighsun.shixiya.hr.action;

import java.util.Map;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.service.CProductHRService;
import com.opensymphony.xwork2.ActionContext;

public class CPHrLoginAction {
	private String password;
	private String telephone;
	private CProductHR cpHr;
	private String errorMessage;
	Map<String, Object> session = ActionContext.getContext().getSession();

	@Resource(name = "cProductHRService")
	private CProductHRService cpHrService;

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

	public CProductHR getCpHr() {
		return cpHr;
	}

	public void setCpHr(CProductHR cpHr) {
		this.cpHr = cpHr;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String hrLogin() {
		
		try {
			session.remove("hr");
			session.remove("bpTeacher");
			session.remove("trainer");
			session.remove("student");
		} catch (Exception e) {
			System.out.println("清除其他残余session");
		}
		
		if (cpHrService.cpHrLogin(telephone, password)) {
			System.out.println("login:ok");
			cpHr = cpHrService.findByTelephone(telephone);
			System.out.println(cpHr.getHrId());
			session.put("hr", cpHr);
			return "hrLogin";
		}
		errorMessage = "密码错误或者账户不存在";
		return "error";
	}
}
