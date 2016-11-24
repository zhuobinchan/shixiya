package com.ehighsun.shixiya.Interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.Administer;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AdminLoginIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();
		Administer administer = (Administer) session.getAttribute("user");
		if (administer != null) {
			return arg0.invoke();
		} else {
			return "adminNotLogin";
		}

	}
}
