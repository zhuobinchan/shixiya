package com.ehighsun.shixiya.student.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.ThirdPartyUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JudgeResumeAction extends ActionSupport {
	
	@Resource(name="baseDao")
	private BaseDao<Resume> resumedDao;
	private String isResume;
	private int broadcastId;
	private int courseId;
	private Integer recruitId;
	private Integer companyId;
	
	private int studentId;
	
	private String addWhichApply;
	
	@Resource(name="baseDao")
	private BaseDao<Student> studentDao;
	
	Map<String, Object> session = ActionContext.getContext().getSession();
	
	/*判断用户是否填写了基本信息*/
	public String judgeUserBaseInfo(){
		
		Student student = (Student) session.get("student");
		
		Student student2 = studentDao.get(Student.class, student.getId());
		session.remove("student");
		session.put("student",student2);
		
		if (broadcastId!=0) {
			addWhichApply = "addCpApply";
		}else if(courseId != 0) {
			addWhichApply = "addBpApply";
		}
		

		if(student2.getTelephone()==null || student2.getTelephone().equals(""))
			return "no_info";
		else {
			
			/*招聘报名环节如有基本信息，则直接跳转去报名*/
			if(recruitId !=null){
				addWhichApply = "applyRecruit_RecruitAction";
				return "exist_info";
			}
			
			List resumes = resumedDao.executeOurSql(getSelectSqlString());
			if (!(resumes == null || resumes.size() == 0)) {

				try {
					/*查询到有简历后，对简历和用户信息的ID做匹配*/
					String sql = "update 简历管理  set 学生ID="+student2.getId()+" where 简历ID="+resumes.get(0);
					studentDao.executeSql(sql);
				} catch (Exception e) {
					System.out.println("com.ehighsun.shixiya.student.action.JudgeResumeAction里judgeUserBaseInfo：exist_info_and_resume");
					e.printStackTrace();
				}
				
				return "exist_info_and_resume";
			}else return "exist_info";
		}
		
		
	}
	
	/*判断实习吖数据库是否存在该用户的简历*/
	public String judgeOurDataBaseResume(){
		
		HttpServletResponse response = ServletActionContext.getResponse();

		
		List resumes = resumedDao.executeOurSql(getSelectSqlString());
		
		if (resumes == null || resumes.size() == 0) {
			isResume = "false";
//			return "no_resume_in_shixiya";
		}else isResume = "true";
		
		try {
			ResponseUtil.write(response, isResume);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return "exist_resume_in_shixiya";
		return null;
	}
	
	/*判断大易是否有该用户的简历*/
	public String judgeResumeIsExist(){
		
		
		List resumes = resumedDao.executeOurSql(getSelectSqlString());
		if (resumes == null || resumes.size() == 0) {

			try {
				/*实习吖数据库里没有简历*/
				isResume = "false";
				/*若实习吖数据库没有该简历，则去大易系统更新简历,每次获取3简历,直到获取到用户的简历或获取完接口里的简历时停止*/
//				ThirdPartyUtil.getResume("20");
				while (!ThirdPartyUtil.getResume("1").equals("999")) {
					/*上面经过大易获取简历后，再次判断实习吖数据库中是否存在该学生的简历数据*/
					resumes = resumedDao
							.executeOurSql(getSelectSqlString());
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
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ResponseUtil.write(response, isResume);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	/*用户基本信息和大易的简历匹配，为了找回对应的简历*/
	public String getSelectSqlString(){
		
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");
		studentId = student.getId();
		
		String sql1 = "(姓名='" + student.getName()	+ "' and 邮箱='"+student.getEmail()+"')";
		String sql2 = "(姓名='" + student.getName()	+ "' and 手机号码='"+student.getTelephone()+"')";
		String sql3 = "(手机号码='"+student.getTelephone()+"' and 邮箱='"+student.getEmail()+"')";
		
		String sql = "select 简历ID from 简历管理 where "+sql1+" "+" or "+sql2+" or "+sql3;
		
		return sql;
	}
	
	
	public String getIsResume() {
		return isResume;
	}

	public void setIsResume(String isResume) {
		this.isResume = isResume;
	}



	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getAddWhichApply() {
		return addWhichApply;
	}

	public void setAddWhichApply(String addWhichApply) {
		this.addWhichApply = addWhichApply;
	}

	public Integer getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(Integer recruitId) {
		this.recruitId = recruitId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
