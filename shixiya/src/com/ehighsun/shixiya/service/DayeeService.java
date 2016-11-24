package com.ehighsun.shixiya.service;

import javax.servlet.http.HttpServletResponse;

public interface DayeeService {
	/*判断大易是否有该用户的简历*/
	public String judgeResumeIsExist(int studentId);
	
	/*用户基本信息和大易的简历匹配，为了找回对应的简历*/
	public String getSelectSqlString(int studentId);
}
