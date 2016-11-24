package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * CProduct entity. @author MyEclipse Persistence Tools
 */
// 这是HR信息表的实体 当初建实体时 把名字弄错了 请注意！
public class CProductHR implements java.io.Serializable {

	// Fields

	private Integer hrId;
	private String name;
	private String telephone;
	private String company;
	private String introduction;
	private String headImgUrl;
	private String password;
	private Integer status;
	private Set hrCommentLists = new HashSet(0);
	private Set CProductBroadcasts = new HashSet(0);

	// Constructors

	/** default constructor */
	public CProductHR() {
	}

	/** full constructor */
	public CProductHR(String name, String telephone, String company,
			String introduction, String headImgUrl, Set hrCommentLists,
			Set CProductBroadcasts) {
		this.name = name;
		this.telephone = telephone;
		this.company = company;
		this.introduction = introduction;
		this.headImgUrl = headImgUrl;
		this.hrCommentLists = hrCommentLists;
		this.CProductBroadcasts = CProductBroadcasts;
	}

	// Property accessors

	public Integer getHrId() {
		return this.hrId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setHrId(Integer hrId) {
		this.hrId = hrId;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Set getHrCommentLists() {
		return this.hrCommentLists;
	}

	public void setHrCommentLists(Set hrCommentLists) {
		this.hrCommentLists = hrCommentLists;
	}

	public Set getCProductBroadcasts() {
		return this.CProductBroadcasts;
	}

	public void setCProductBroadcasts(Set CProductBroadcasts) {
		this.CProductBroadcasts = CProductBroadcasts;
	}

}