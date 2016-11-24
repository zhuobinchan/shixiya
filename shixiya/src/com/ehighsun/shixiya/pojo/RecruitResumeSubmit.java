package com.ehighsun.shixiya.pojo;

import java.sql.Timestamp;

// default package

/**
 * RecruitResumeSubmit entity. @author MyEclipse Persistence Tools
 */

public class RecruitResumeSubmit implements java.io.Serializable {

	// Fields
	private Integer id;
	private Student student;
	private RecruitmentInfo recruitmentInfo;
	private String openId;
	private String name;
	private String email;
	private String telephone;
	private Timestamp time;
	private Integer businessId;

	// Constructors

	/** default constructor */
	public RecruitResumeSubmit() {
	}

	/** full constructor */
	public RecruitResumeSubmit(Student student,
			RecruitmentInfo recruitmentInfo, String openId, String name,
			String email, String telephone) {
		this.student = student;
		this.recruitmentInfo = recruitmentInfo;
		this.openId = openId;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public RecruitmentInfo getRecruitmentInfo() {
		return this.recruitmentInfo;
	}

	public void setRecruitmentInfo(RecruitmentInfo recruitmentInfo) {
		this.recruitmentInfo = recruitmentInfo;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}