package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * HrCommentList entity. @author MyEclipse Persistence Tools
 */

public class HrCommentList implements java.io.Serializable {

	// Fields

	private Integer id;
	private CProductHR CProductHR;
	private String title;
	private String introduction;
	private String publicTime;
	private String startTime;
	private Integer state;// 直播状态
	private Integer mode;// 三种模式
	private String videoUrl;
	private String imageUrl;
	private Integer visitnum;
	private Integer stick;

	public Integer getVisitnum() {
		return visitnum;
	}

	public void setVisitnum(Integer visitnum) {
		this.visitnum = visitnum;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private Set hrComments = new HashSet(0);

	// Constructors
	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	/** default constructor */
	public HrCommentList() {
	}

	/** full constructor */
	public HrCommentList(CProductHR CProductHR, String title,
			String introduction, String publicTime, String startTime,
			Integer state, Set hrComments) {
		this.CProductHR = CProductHR;
		this.title = title;
		this.introduction = introduction;
		this.publicTime = publicTime;
		this.startTime = startTime;
		this.state = state;
		this.hrComments = hrComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CProductHR getCProductHR() {
		return this.CProductHR;
	}

	public void setCProductHR(CProductHR CProductHR) {
		this.CProductHR = CProductHR;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPublicTime() {
		return this.publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set getHrComments() {
		return this.hrComments;
	}

	public void setHrComments(Set hrComments) {
		this.hrComments = hrComments;
	}

	public Integer getStick() {
		return stick;
	}

	public void setStick(Integer stick) {
		this.stick = stick;
	}

}