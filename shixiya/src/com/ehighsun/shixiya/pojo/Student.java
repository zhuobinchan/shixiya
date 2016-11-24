package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String nickname;

	private Integer integral;

	private String telephone;
	private String identityCar;
	private Set CProductStudentApplies = new HashSet(0);
	private Set CProductAnswers = new HashSet(0);
	private Set BProductStudentApplies = new HashSet(0);
	private Set resumes = new HashSet(0);
	private String headUrl;
	private String openId;
	private Integer status;
	private String email;

	// Constructors

	/** default constructor */
	public Student() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/** minimal constructor */
	public Student(String name) {
		this.name = name;

	}

	/** full constructor */
	public Student(String name, String telephone, String identityCar,
			Integer integral, Set CProductStudentApplies, Set CProductAnswers,
			Set BProductStudentApplies, Set resumes) {
		this.name = name;
		this.integral = integral;
		this.telephone = telephone;
		this.identityCar = identityCar;
		this.CProductStudentApplies = CProductStudentApplies;
		this.CProductAnswers = CProductAnswers;
		this.BProductStudentApplies = BProductStudentApplies;
		this.resumes = resumes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIntegral() {
		return this.integral;
	}

	public String getTelephone() {
		return telephone;
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

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set getCProductStudentApplies() {
		return this.CProductStudentApplies;
	}

	public void setCProductStudentApplies(Set CProductStudentApplies) {
		this.CProductStudentApplies = CProductStudentApplies;
	}

	public Set getCProductAnswers() {
		return this.CProductAnswers;
	}

	public void setCProductAnswers(Set CProductAnswers) {
		this.CProductAnswers = CProductAnswers;
	}

	public Set getBProductStudentApplies() {
		return this.BProductStudentApplies;
	}

	public void setBProductStudentApplies(Set BProductStudentApplies) {
		this.BProductStudentApplies = BProductStudentApplies;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Set getResumes() {
		return this.resumes;
	}

	public void setResumes(Set resumes) {
		this.resumes = resumes;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}