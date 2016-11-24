package com.ehighsun.shixiya.Login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport {

	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Autowired
	private AdminService adminService;

	public String showlogin() {
		return "showlogin";
	}

	public String adminlogin() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		Administer admin = adminService.findAdminByName(name);

		if (admin != null) {

			if (admin.getTelephone().equals(password)) {

				session.setAttribute("user", admin);
				return "adminloginSuccess";
			}

		} else {

			request.setAttribute("falsename", "账号错误");

			return "adminloginFalse";
		}

		request.setAttribute("falsePassword", "密码错误");
		return "adminloginFalse";
	}

}
