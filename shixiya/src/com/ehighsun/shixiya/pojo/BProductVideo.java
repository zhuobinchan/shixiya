package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * BProductVideo entity. @author MyEclipse Persistence Tools
 */

public class BProductVideo implements java.io.Serializable {

	// Fields

	private Integer id;
	private BProductChapter BProductChapter;
	private String title;
	private String videoUrl;
	private Set BProductComments = new HashSet(0);
	private String publicTime;
	private String startTime;
	private String endTime;
	private Integer status;
	private Integer chatStatus;
	private Integer mode;

	// Constructors

	/** default constructor */
	public BProductVideo() {
	}

	/** minimal constructor */
	public BProductVideo(BProductChapter BProductChapter) {
		this.BProductChapter = BProductChapter;
	}

	/** full constructor */
	public BProductVideo(BProductChapter BProductChapter, String title,
			String videoUrl, Set BProductComments) {
		this.BProductChapter = BProductChapter;
		this.title = title;
		this.videoUrl = videoUrl;
		this.BProductComments = BProductComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BProductChapter getBProductChapter() {
		return this.BProductChapter;
	}

	public void setBProductChapter(BProductChapter BProductChapter) {
		this.BProductChapter = BProductChapter;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Set getBProductComments() {
		return this.BProductComments;
	}

	public void setBProductComments(Set BProductComments) {
		this.BProductComments = BProductComments;
	}

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(Integer chatStatus) {
		this.chatStatus = chatStatus;
	}

}