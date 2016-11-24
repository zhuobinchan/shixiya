package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * TrainWEProduct entity. @author MyEclipse Persistence Tools
 */

public class TrainWEProductChild implements java.io.Serializable {

	// Fields

	private Integer id;
	private TrainWEProduct trainWEProduct;
	private String title;
	private String publisher;
	private String publicTime;
	private String startTime;
	private String endTime;
	private String introduction;
	private String listViewContent;
	private Integer status;
	private String videoUrl;
	private String imgUrl;
	private Integer participation;
	private Integer mode;
	private Integer chatStatus;
	private Integer visitnum;
	private Set trainWEComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public TrainWEProductChild() {
	}

	public TrainWEProductChild(Integer id, String title, String publisher,
			String publicTime, String startTime, String endTime,
			String introduction, Integer status, String videoUrl,
			String imgUrl, Integer participation, Integer mode,
			Integer chatStatus, Integer visitnum,String listViewContent) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.publicTime = publicTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.introduction = introduction;
		this.status = status;
		this.videoUrl = videoUrl;
		this.imgUrl = imgUrl;
		this.participation = participation;
		this.mode = mode;
		this.chatStatus = chatStatus;
		this.visitnum = visitnum;
		this.listViewContent = listViewContent;
	}

	/** full constructor */
	public TrainWEProductChild(String title,
			String publisher, String publicTime, String startTime,
			String endTime, String introduction, Integer status,
			String videoUrl, String imgUrl, Integer participation,
			Integer mode, Integer chatStatus, Integer visitnum,
			Set trainWEComments) {
		this.title = title;
		this.publisher = publisher;
		this.publicTime = publicTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.introduction = introduction;
		this.status = status;
		this.videoUrl = videoUrl;
		this.imgUrl = imgUrl;
		this.participation = participation;
		this.mode = mode;
		this.chatStatus = chatStatus;
		this.visitnum = visitnum;
		this.trainWEComments = trainWEComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getParticipation() {
		return this.participation;
	}

	public void setParticipation(Integer participation) {
		this.participation = participation;
	}

	public Integer getMode() {
		return this.mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getChatStatus() {
		return this.chatStatus;
	}

	public void setChatStatus(Integer chatStatus) {
		this.chatStatus = chatStatus;
	}

	public Integer getVisitnum() {
		return this.visitnum;
	}

	public void setVisitnum(Integer visitnum) {
		this.visitnum = visitnum;
	}

	public Set getTrainWEComments() {
		return this.trainWEComments;
	}

	public void setTrainWEComments(Set trainWEComments) {
		this.trainWEComments = trainWEComments;
	}

	public String getListViewContent() {
		return listViewContent;
	}

	public void setListViewContent(String listViewContent) {
		this.listViewContent = listViewContent;
	}

	public TrainWEProduct getTrainWEProduct() {
		return trainWEProduct;
	}

	public void setTrainWEProduct(TrainWEProduct trainWEProduct) {
		this.trainWEProduct = trainWEProduct;
	}

}