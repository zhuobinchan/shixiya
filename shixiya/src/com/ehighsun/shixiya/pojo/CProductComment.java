package com.ehighsun.shixiya.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

// default package

/**
 * CProductComment entity. @author MyEclipse Persistence Tools
 */
@JSONType(orders = { "CProductBroadcast" })
public class CProductComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private CProductBroadcast CProductBroadcast;
	private String content;
	private String name;
	private Integer type;
	private String commentTime;
	private String headUrl;
	private String role;
	private String roleId;
	private String localUrl;
	private int duration;

	// Constructors

	/** default constructor */
	public CProductComment() {
	}

	/** minimal constructor */
	public CProductComment(Integer type) {
		this.type = type;
	}

	/** full constructor */
	public CProductComment(CProductBroadcast CProductBroadcast, String content,
			String name, Integer type, String commentTime) {
		this.CProductBroadcast = CProductBroadcast;
		this.content = content;
		this.name = name;
		this.type = type;
		this.commentTime = commentTime;
	}

	// Property accessors

	public CProductComment(Integer id, String content, String name,
			Integer type, String commentTime, String headUrl, String role,
			String localUrl) {
		super();
		this.id = id;
		this.content = content;
		this.name = name;
		this.type = type;
		this.commentTime = commentTime;
		this.headUrl = headUrl;
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
	public CProductBroadcast getCProductBroadcast() {
		return this.CProductBroadcast;
	}

	public void setCProductBroadcast(CProductBroadcast CProductBroadcast) {
		this.CProductBroadcast = CProductBroadcast;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
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