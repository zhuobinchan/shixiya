package com.ehighsun.shixiya.student.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.CProductStudentApplyId;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.StudentUpdateIntegralService;
import com.opensymphony.xwork2.ActionSupport;

public class CPStudentApplyAction extends ActionSupport {

	Map<String, Object> session = ServletActionContext.getContext()
			.getSession();
	private int broadcastId;
	private int studentId;
	private int status;

	@Resource(name = "baseDao")
	private BaseDao<CProductBroadcast> cpBroadcastDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductStudentApply> cpApplyDao;
	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name = "studentUpdateIntegralService")
	private StudentUpdateIntegralService suiService;

	public String addCpApply() {

		CProductStudentApply cpStuApply = new CProductStudentApply();
		CProductStudentApplyId cpStuApplyId = new CProductStudentApplyId();

		CProductBroadcast broadcast = cpBroadcastDao.get(
				CProductBroadcast.class, broadcastId);
		Student student = (Student) session.get("student");

		cpStuApplyId.setCProductBroadcast(broadcast);
		cpStuApplyId.setTelephone(student.getTelephone());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		cpStuApply.setId(cpStuApplyId);
		cpStuApply.setApplyTime(df.format(new Date()));
		cpStuApply.setStudent(student);
		cpStuApply.setStudentName(student.getName());
		cpStuApply.setStatus(status);
		try {
			cpApplyDao.save(cpStuApply);
		} catch (DataIntegrityViolationException e) {
			System.out.println("重复报名!");
		}
		broadcast.setParticipation(broadcast.getParticipation() + 1);
		suiService.ProductForIntegral(student.getId());
		return "addCpApply";
	}

	public int getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
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
