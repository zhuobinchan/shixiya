package com.ehighsun.shixiya.student.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.StudentUpdateIntegralService;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResumeStudentAction extends ActionSupport {

	private static final String email_regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	private static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
	private static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

	private int studentId;
	private String name;
	private int courseId;
	private int broadcastId;
	private String title;
	private String birthday;
	private String school;
	private String major;
	private String grade;
	private String introduction;
	private String job;
	private String address;
	private String email;
	private String telephone;
	private String identityCar;

	private Resume resume;
	private String productType;

	private String message;

	@Resource(name = "baseDao")
	private BaseDao<Resume> resumeDao;
	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name = "studentUpdateIntegralService")
	private StudentUpdateIntegralService suiService;

	public String addStudentResume() {
		resume = new Resume();

		Student student = (Student) ActionContext.getContext().getSession().get("student");

		// Student student = studentDao.get(Student.class, studentId);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		resume.setUploadTime(df.format(new Date()));
		resume.setStudent(student);
		if (StringUtil.isEmpty(title)) {
			message = "标题不能为空";
		} else if (StringUtil.isEmpty(birthday)) {
			message = "生日不能为空";
		} else if (StringUtil.isEmpty(school)) {
			message = "学校不能为空";
		} else if (StringUtil.isEmpty(major)) {
			message = "专业不能为空";
		} else if (StringUtil.isEmpty(grade)) {
			message = "年纪不能为空";
		} else if (StringUtil.isEmpty(introduction)) {
			message = "介绍不能为空";
		} else if (StringUtil.isEmpty(job)) {
			message = "职业不能为空";
		} else if (StringUtil.isEmpty(address)) {
			message = "地址不能为空";
		} else if (StringUtil.isEmpty(email)
				&& Pattern.matches(email_regexp, email)) {
			message = "邮箱不能为空或者邮箱信息不正确";
		} else if (StringUtil.isEmpty(telephone)
				&& Pattern.matches(phone_regexp, telephone)) {
			message = "电话号码不能为空或者电话号码不正确";
		} else if (StringUtil.isEmpty(identityCar)
				&& Pattern.matches(ID_card_regexp, identityCar)) {
			message = "身份证不能为空或者身份证号码不争取";
		} else {
			resume.setTitle(title);
			resume.setBirthday(birthday);
			resume.setSchool(school);
			resume.setMajor(major);
			resume.setName(name);
			resume.setGrade(grade);
			resume.setIntroduction(introduction);
			resume.setJob(job);
			resume.setAddress(address);
			resume.setEmail(email);
			resume.setTelephone(telephone);
			resume.setIdentityCar(identityCar);
			resumeDao.saveOrUpdate(resume);
		}
		
		if(student.getTelephone()==null || student.getTelephone().equals("")){
			student.setTelephone(telephone);
			student.setEmail(email);
			student.setIdentityCar(identityCar);
			student.setName(name);
			System.out.println("studentDao.saveOrUpdate(student):执行了");
			studentDao.saveOrUpdate(student);
			ActionContext.getContext().getSession().remove("student");
			ActionContext.getContext().getSession().put("student", student);
		}
		suiService.ResumesForIntegral(student.getId());
		if (productType.equals("cp")) {

			return "goToCpApply";
		} else if (productType.equals("bp")){
			return "goToBpApply";
		}
		return null;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIntroduction() {
		return introduction;
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

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityCar() {
		return identityCar;
	}

	public void setIdentityCar(String identityCar) {
		this.identityCar = identityCar;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
