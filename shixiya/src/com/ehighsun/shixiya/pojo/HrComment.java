package com.ehighsun.shixiya.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

// default package

/**
 * HrComment entity. @author MyEclipse Persistence Tools
 */
@JSONType(orders = { "hrCommentList" })
public class HrComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private HrCommentList hrCommentList;
	private String content;
	private Integer type;
	private Integer hrId;
	private String commentTime;
	private String headUrl;
	private String name;
	private String role;
	private String roleId;
	private String localUrl;
	private int duration;
	

	// Constructors
	
	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	/** default constructor */
	public HrComment() {
	}

	/** minimal constructor */
	public HrComment(Integer type) {
		this.type = type;
	}

	/** full constructor */
	public HrComment(HrCommentList hrCommentList, String context, Integer type,
			Integer hrId, String commentTime) {
		this.hrCommentList = hrCommentList;
		this.content = context;
		this.type = type;
		this.hrId = hrId;
		this.commentTime = commentTime;
	}

	// Property accessors

	public HrComment(Integer id, String content, Integer type, Integer hrId,
			String commentTime, String headUrl, String name, String role,
			String localUrl) {
		super();
		this.id = id;
		this.content = content;
		this.type = type;
		this.hrId = hrId;
		this.commentTime = commentTime;
		this.headUrl = headUrl;
		this.name = name;
		this.role = role;
		this.localUrl = localUrl;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSONField(serialize = false)
	public HrCommentList getHrCommentList() {
		return this.hrCommentList;
	}

	public void setHrCommentList(HrCommentList hrCommentList) {
		this.hrCommentList = hrCommentList;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHrId() {
		return this.hrId;
	}

	public void setHrId(Integer hrId) {
		this.hrId = hrId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}