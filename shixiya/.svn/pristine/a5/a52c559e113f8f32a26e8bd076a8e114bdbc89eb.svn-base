package com.ehighsun.shixiya.Interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.RIBussiness;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class CompanyLoginIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();
		RIBussiness riBussiness = (RIBussiness) session
				.getAttribute("companyer");
		if (riBussiness != null) {
			return arg0.invoke();
		} else {
			return "bussinessNotLogin";
		}

	}
}
