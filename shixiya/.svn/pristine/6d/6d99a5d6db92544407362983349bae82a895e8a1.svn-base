package com.ehighsun.shixiya.Login.action;

import javax.annotation.Resource;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.service.RIBussinessService;

public class CompanyLoginAction extends BaseAction<RIBussiness> {

	public String falsename;
	public String falsePassword;

	public String getFalsename() {
		return falsename;
	}

	public void setFalsename(String falsename) {
		this.falsename = falsename;
	}

	public String getFalsePassword() {
		return falsePassword;
	}

	public void setFalsePassword(String falsePassword) {
		this.falsePassword = falsePassword;
	}

	@Resource(name = "riBussinessServiceImpl")
	private RIBussinessService riBussinessService;

	public String showlogin() {

		return "showlogin";
	}

	public String login() {

		RIBussiness riBussiness = riBussinessService
				.findRiBussinessByEmail(model.getEmail());
		if (riBussiness != null) {
			if (riBussiness.getPassword().equals(model.getPassword())) {
				this.getSession().setAttribute("companyer", riBussiness);
				return "loginSuccess";
			} else {
				falsePassword = "密码错误";
				return "falselogin";
			}

		} else {
			falsename = "账号错误";
			return "falselogin";
		}

	}

}
