package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * BProductTeacher entity. @author MyEclipse Persistence Tools
 */

public class BProductTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String telephone;
	private String job;
	private String background;
	private String email;
	private String introduction;
	private String headImgUrl;
	private String password;
	private Set BProductCourses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BProductTeacher() {
	}

	/** full constructor */
	public BProductTeacher(String name, String telephone, String job,
			String background, String email, String introduction,
			String headImgUrl, String password, Set BProductCourses) {
		this.name = name;
		this.telephone = telephone;
		this.job = job;
		this.background = background;
		this.email = email;
		this.introduction = introduction;
		this.headImgUrl = headImgUrl;
		this.password = password;
		this.BProductCourses = BProductCourses;
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

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getBackground() {
		return this.background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getBProductCourses() {
		return this.BProductCourses;
	}

	public void setBProductCourses(Set BProductCourses) {
		this.BProductCourses = BProductCourses;
	}

}