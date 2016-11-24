package com.ehighsun.shixiya.Interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.ehighsun.shixiya.pojo.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserLoginInterceptor extends MethodFilterInterceptor  {
	
	private String targetUrl;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		
		Student student = (Student) session.get("student");
		
		ActionContext actionContext = invocation.getInvocationContext(); 
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String reqPamrs = request.getQueryString();

		System.out.println("Student:"+student);
		System.out.println("request请求："+request.getRequestURI()+",reqPamrs:"+reqPamrs);
		
		
		targetUrl = request.getRequestURI()+(reqPamrs==null?"":"?"+reqPamrs);
		
		System.out.println("UserLoginInterceptor-targetUrl:"+targetUrl);
		
		if(student == null){
			return "usertNoLogin";
		}else{
			return invocation.invoke();
		}
	}

	
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
}
