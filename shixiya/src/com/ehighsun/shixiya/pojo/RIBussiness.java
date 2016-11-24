package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * RIBussiness entity. @author MyEclipse Persistence Tools
 */

public class RIBussiness implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String address;
	private String linkman;
	private String telephone;
	private String email;
	private String password;
	private String fileUrl;
	private String logoUrl;
	private Integer state;
	private Set recruitmentInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public RIBussiness() {
	}

	public RIBussiness(String name, String address, String linkman,
			String telephone, String email, String password, String fileUrl,
			Integer state, Set recruitmentInfos,String logoUrl) {
		super();
		this.name = name;
		this.address = address;
		this.linkman = linkman;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.fileUrl = fileUrl;
		this.state = state;
		this.recruitmentInfos = recruitmentInfos;
		this.logoUrl = logoUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Set getRecruitmentInfos() {
		return this.recruitmentInfos;
	}

	public void setRecruitmentInfos(Set recruitmentInfos) {
		this.recruitmentInfos = recruitmentInfos;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}