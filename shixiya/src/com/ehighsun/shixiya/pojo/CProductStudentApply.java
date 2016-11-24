package com.ehighsun.shixiya.pojo;

// default package

/**
 * CProductStudentApply entity. @author MyEclipse Persistence Tools
 */

public class CProductStudentApply implements java.io.Serializable {

	// Fields

	private CProductStudentApplyId id;
	private Student student;
	private String studentName;
	private Integer status;
	private String applyTime;

	// Constructors

	/** default constructor */
	public CProductStudentApply() {
	}

	/** minimal constructor */
	public CProductStudentApply(CProductStudentApplyId id, String studentName,
			Integer status) {
		this.id = id;
		this.studentName = studentName;
		this.status = status;
	}

	/** full constructor */
	public CProductStudentApply(CProductStudentApplyId id, Student student,
			String studentName, Integer status) {
		this.id = id;
		this.student = student;
		this.studentName = studentName;
		this.status = status;
	}

	// Property accessors

	public CProductStudentApplyId getId() {
		return this.id;
	}

	public void setId(CProductStudentApplyId id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

}