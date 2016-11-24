package com.ehighsun.shixiya.trainer.action;

import java.util.Map;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrainerLoginAction extends ActionSupport {
	private String password;
	private String telephone;
	private TrainWETeacher trainer;
	private String errorMessage;
	Map<String, Object> session = ActionContext.getContext().getSession();
	@Resource(name="trainWETeacherService")
	private TrainWETeacherService trainWETeacherService;
	
	public String trainerLogin() {
		try {
			session.remove("hr");
			session.remove("bpTeacher");
			session.remove("trainer");
			session.remove("student");
		} catch (Exception e) {
			System.out.println("清除其他残余session");
		}
		
		if (trainWETeacherService.trainTeacherLogin(telephone, password)) {
			trainer = trainWETeacherService.findByTelephone(telephone);
			
			System.out.println("trainTeacher_login:ok,id="+trainer.getId());
			
			session.put("trainer", trainer);
			return "trainerLogin";
		}
		errorMessage = "密码错误或者账户不存在";
		return "error";
	}
	
	
	
	
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
