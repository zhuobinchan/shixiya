package com.ehighsun.shixiya.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.DayeeService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.ThirdPartyUtil;

@Service("dayeeService")
public class DayeeServiceImpl implements DayeeService {
	
	@Resource(name="baseDao")
	private BaseDao<CProductStudentApply> applyDao;

	
	/*判断大易是否有该用户的简历*/
	@Override
	public String judgeResumeIsExist(int studentId){
		
		String isResume = null;
		
		List resumes = applyDao.executeOurSql(getSelectSqlString(studentId));
		if (resumes == null || resumes.size() == 0) {

			try {
				/*实习吖数据库里没有简历*/
				isResume = "false";
				/*若实习吖数据库没有该简历，则去大易系统更新简历,每次获取3简历,直到获取到用户的简历或获取完接口里的简历时停止*/
//				ThirdPartyUtil.getResume("20");
				while (!ThirdPartyUtil.getResume("1").equals("999")) {
					/*上面经过大易获取简历后，再次判断实习吖数据库中是否存在该学生的简历数据*/
					resumes = applyDao
							.executeOurSql(getSelectSqlString(studentId));
					if (!(resumes == null || resumes.size() == 0)){
						/*大易接口里查询到简历*/
						isResume = "true";
						break;
					}
				}

				
//				if (resumes == null || resumes.size() == 0){
//					isResume = "false";
//				}else isResume = "true";
				
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			
		} else
			isResume = "true";
		
		return isResume;
	}
	
	@Override
	/*用户基本信息和大易的简历匹配，为了找回对应的简历*/
	public String getSelectSqlString(int studentId){
		
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");
		studentId = student.getId();
		
		String sql1 = "(姓名='" + student.getName()	+ "' and 邮箱='"+student.getEmail()+"')";
		String sql2 = "(姓名='" + student.getName()	+ "' and 手机号码='"+student.getTelephone()+"')";
		String sql3 = "(手机号码='"+student.getTelephone()+"' and 邮箱='"+student.getEmail()+"')";
		
		String sql = "select 简历ID from 简历管理 where "+sql1+" "+" or "+sql2+" or "+sql3;
		
		return sql;
	}
}
