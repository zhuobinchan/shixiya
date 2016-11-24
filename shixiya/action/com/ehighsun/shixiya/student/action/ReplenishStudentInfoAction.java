package com.ehighsun.shixiya.student.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Student;
import com.opensymphony.xwork2.ActionSupport;

public class ReplenishStudentInfoAction extends ActionSupport {

	private static final String email_regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	private static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

	private String email;
	private String telephone;
	private String name;

	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;

	public String ReplenishStudentInfo() {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("student");
		student.setName(name);
		student.setTelephone(telephone);
		student.setEmail(email);

		studentDao.saveOrUpdate(student);
		return "ReplenishStudentInfo";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
