package com.ehighsun.shixiya.Interceptor;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.BProductApplyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ApplyInterceptor extends MethodFilterInterceptor{
	@Autowired
	private BProductApplyService bProductApplyService;
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		ServletRequest request = ServletActionContext.getRequest();
		 
		Student student = (Student) session.get("user");
		Integer cid = (Integer) request.getAttribute("BCourseID");
		
		BProductStudentApply apply = bProductApplyService.findBProductApplyByStudentId(student,cid);
		
		if(apply!=null){
			return "fail";
		}else{
			  return invocation.invoke();
		}
																													
		
	}

}
