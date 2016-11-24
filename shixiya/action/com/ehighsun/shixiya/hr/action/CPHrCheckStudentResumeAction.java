package com.ehighsun.shixiya.hr.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.dayee.wintalent.service.v8.pojo.PersonalInformations;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.DicInfo;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.DayeeService;
import com.ehighsun.shixiya.service.DicInfoService;
import com.ehighsun.shixiya.service.ResumeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CPHrCheckStudentResumeAction extends ActionSupport {
	private Integer studentId;
	private Resume resume;
	private String selfAssessment;
	Map<String, String> education = new HashMap<String, String>();
	Map<String, String> personInfo = new HashMap<String, String>();
	
	private String isFromPersonResume;

	@Resource(name = "resumeService")
	private ResumeService resumeService;
	@Resource(name = "dicInfoService")
	private DicInfoService dicInfoService;
	@Resource(name="baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name="dayeeService")
	private DayeeService dayeeService;
	
	Map<String, Object> session = ActionContext.getContext().getSession();
	
	public String judgeResumeIsExist(){
		
		System.out.println("judgeResumeIsExist----isFromPersonResume:"+isFromPersonResume);
	
		Student student = (Student) session.get("student");
		System.out.println("student.getId():"+student.getId());
		Student student2 = studentDao.get(Student.class, student.getId());
		session.remove("student");
		session.put("student",student2);

		if(student2.getTelephone()==null || student2.getTelephone().equals(""))
			return "no_info";
		else {
			System.out.println("exist_info------------------only");
			List resumes = studentDao.executeOurSql(getSelectSqlString());
			if (!(resumes == null || resumes.size() == 0)) {
				try {
					/*查询到有简历后，对简历和用户信息的ID做匹配*/
					String sql = "update 简历管理 set 学生ID="+student2.getId()+" where 简历ID="+resumes.get(0);
					studentDao.executeSql(sql);
				} catch (Exception e) {
					System.out.println("com.ehighsun.shixiya.hr.action.CPHrCheckStudentResumeAction里judgeResumeIsExist：exist_info_and_resume");
					e.printStackTrace();
				}
				return "exist_info_and_resume";
			}else return "exist_info";
		}
		
	}
	
	public String searchResumeFromDayee(){
		
		Student student = (Student) session.get("student");
		
		String isResume = dayeeService.judgeResumeIsExist(student.getId());
		
		if (isResume.equals("true")) {
			return "exist_info_and_resume";
		}

		return "exist_info_no_resume";
	}


	public String getResumeByStudentId() {
		
		Resume r = resumeService.getResumeByStudentId(studentId);

		JSONObject obj1 = new JSONObject().fromObject(r
				.getPersonalInformation());
		PersonalInformations pi = (PersonalInformations) JSONObject.toBean(
				obj1, PersonalInformations.class);
		personInfo = new HashMap<String, String>();
		personInfo.put("手机号码:", pi.getMobilePhone());
		personInfo.put("邮箱", pi.getEmail());
		System.out.println(pi.getCurrentCity()+"////");
		if (pi.getCurrentCity() != null && !"".equals(pi.getCurrentCity())) {
			DicInfo dicInfo = dicInfoService.findDicInfoByCode(pi
					.getCurrentCity());
			if (dicInfo != null) {
				personInfo.put("现居住地:", dicInfo.getName());

			}else{
				personInfo.put("现居住地:","");
			}
		}else{
			personInfo.put("现居住地:","");
		}
		
		if (pi.getNation() != null && !"".equals(pi.getNation())) {
			DicInfo dicInfo = dicInfoService.findDicInfoByCode(pi.getNation());
			if (dicInfo != null) {
				personInfo.put("民族:", dicInfo.getName());
			}else{
				personInfo.put("民族:","");
			}
		}else{
			personInfo.put("民族:","");
		}
		
		if (pi.getPoliticalStatus() != null
				&& !"".equals(pi.getPoliticalStatus())) {
			DicInfo dicInfo = dicInfoService.findDicInfoByCode(pi
					.getPoliticalStatus());
			if (dicInfo != null) {
				personInfo.put("政治面貌:", dicInfo.getName());
			}else{
				personInfo.put("政治面貌:","");
			}
		}else{
			personInfo.put("政治面貌:","");
		}

		education = new HashMap<String, String>();
		education.put("学校:", pi.getSchoolName());
		if (pi.getDiploma() != null && !"".equals(pi.getDiploma())) {
			DicInfo dicInfo = dicInfoService.findDicInfoByCode(pi.getDiploma());
			if (dicInfo != null) {
				education.put("学历:", dicInfo.getName());
			}else{
				education.put("学历:", "");
			}
		}else{
			education.put("学历:", "");
		}
		String major = pi.getMajor();
		if (major != null && !"".equals(major)) {

			if (major.startsWith("0/")) {

				DicInfo dicInfo = dicInfoService.findDicInfoByCode(major);
				if (dicInfo != null) {
					education.put("专业:", dicInfo.getName());
				}

			} else if (major.startsWith("N/")) {

				education.put("专业:", major.substring(2));

			} else {

				education.put("专业:", major);
			}

		}else{
			education.put("专业:", "");
		}
		
		education.put("毕业时间:", pi.getGraduateDate());

		selfAssessment = r.getSelfAssessment();

		return "getResumeByStudentId";
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
	

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}

	public Map<String, String> getEducation() {
		return education;
	}

	public void setEducation(Map<String, String> education) {
		this.education = education;
	}

	public Map<String, String> getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(Map<String, String> personInfo) {
		this.personInfo = personInfo;
	}

	public String as() {

		this.resume = resumeService.getResumeByStudentId(studentId);

		return "as";
	}
}
