package com.ehighsun.shixiya.student.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.BProductStudentApplyId;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.StudentUpdateIntegralService;
import com.opensymphony.xwork2.ActionSupport;

public class BPStudentApplyAction extends ActionSupport {

	Map<String, Object> session = ServletActionContext.getContext()
			.getSession();
	private int courseId;
	private int studentId;
	private int status;

	@Resource(name = "baseDao")
	private BaseDao<BProductCourse> bpCourseDao;
	@Resource(name = "baseDao")
	private BaseDao<BProductStudentApply> bpApplyDao;
	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name = "studentUpdateIntegralService")
	private StudentUpdateIntegralService suiService;

	public String addBpApply() {

		BProductStudentApply bpStuApply = new BProductStudentApply();
		BProductStudentApplyId bpStuApplyId = new BProductStudentApplyId();

		BProductCourse course = bpCourseDao.get(BProductCourse.class, courseId);
		Student student = (Student) session.get("student");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		bpStuApplyId.setBProductCourse(course);
		bpStuApplyId.setTelephone(student.getTelephone());

		bpStuApply.setId(bpStuApplyId);
		bpStuApply.setStudent(student);
		bpStuApply.setStates(status);
		bpStuApply.setApplyTime(df.format(new Date()));

		System.out.println("" + courseId + "&&" + student.getTelephone()
				+ bpStuApplyId);

		try {
			bpApplyDao.save(bpStuApply);

		} catch (DataIntegrityViolationException e) {
			System.out.println("重复报名!");
		}
		course.setParticipation(course.getParticipation() + 1);
		suiService.ProductForIntegral(student.getId());

		return "addBpApply";
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
