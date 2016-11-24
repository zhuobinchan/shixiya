package com.ehighsun.shixiya.student.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport {

	private String code;
	private String state;

	private Student student;
	private String studentId;
	
	private Set<BProductStudentApply> bpStuApplys;
	private Set<CProductStudentApply> cpStuApplys;

	Map<String, Object> session = ActionContext.getContext().getSession();

	@Autowired
	private BaseDao<Student> baseDao;

	@Resource(name = "bProductCourseService")
	private BProductCourseService bpCourseService;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;

	List<CProductBroadcast> appliedBroadcasts;
	List<BProductCourse> appliedCourses;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<CProductBroadcast> getAppliedBroadcasts() {
		return appliedBroadcasts;
	}

	public void setAppliedBroadcasts(List<CProductBroadcast> appliedBroadcasts) {
		this.appliedBroadcasts = appliedBroadcasts;
	}

	public List<BProductCourse> getAppliedCourses() {
		return appliedCourses;
	}

	public void setAppliedCourses(List<BProductCourse> appliedCourses) {
		this.appliedCourses = appliedCourses;
	}

	public Set<BProductStudentApply> getBpStuApplys() {
		return bpStuApplys;
	}

	public void setBpStuApplys(Set<BProductStudentApply> bpStuApplys) {
		this.bpStuApplys = bpStuApplys;
	}

	public CProductBroadcastService getCpBroadcastService() {
		return cpBroadcastService;
	}

	public void setCpBroadcastService(CProductBroadcastService cpBroadcastService) {
		this.cpBroadcastService = cpBroadcastService;
	}


	// 通过session获取信息
	public String getStudentInfoBySession() {
		student = (Student) session.get("student");
		return "getStudentInfo";
	}

	public Set<CProductStudentApply> getCpStuApplys() {
		return cpStuApplys;
	}

	public void setCpStuApplys(Set<CProductStudentApply> cpStuApplys) {
		this.cpStuApplys = cpStuApplys;
	}

	// 查寻学生通过Id
	public String findStudentById() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		return "findStudentById";
	}
	
	// 已经报名的产品
	public String appliedAll() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		bpStuApplys = student.getBProductStudentApplies();

		cpStuApplys = student.getCProductStudentApplies();
		
		return "appliedAll";
	}
	
	// 参与过的产品，前端显示报名成功的产品
	public String participant(){
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		bpStuApplys = student.getBProductStudentApplies();

		cpStuApplys = student.getCProductStudentApplies();
		
		return "participant";
	}
	
	// 已经报名的B产品
	public String appliedBp() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		bpStuApplys = student.getBProductStudentApplies();
		
		return "appliedBp";
	}
	
	// 已经报名的C产品
	public String appliedCp() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		cpStuApplys = student.getCProductStudentApplies();
		
		return "appliedCp";
	}
	
	public String appliedCpByAjax() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		cpStuApplys = student.getCProductStudentApplies();
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(CProductStudentApply.class,
				"id");
		
		String result = JSON.toJSONString(cpStuApplys,filter);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public String appliedBpByAjax() {
		
		Student student1 = (Student) session.get("student");
		
		student = baseDao.get(Student.class,student1.getId());
		
		bpStuApplys = student.getBProductStudentApplies();
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(BProductStudentApply.class,
				"id");
		
		String result = JSON.toJSONString(bpStuApplys,filter);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}	
}
