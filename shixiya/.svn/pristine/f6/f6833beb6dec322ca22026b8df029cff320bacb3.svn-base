package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * CProductBroadcast entity. @author MyEclipse Persistence Tools
 */
@JSONType(orders = { "CProductHR", "CProductQuestions", "CProductComments",
		"CProductStudentApplies", "CProductEmploys" })
public class CProductBroadcast implements java.io.Serializable {

	// Fields

	private Integer id;
	private CProductHR CProductHR;
	private LablePaste lablePaste;
	private String lable;
	private String productLable;
	private String name;
	private String publisher;
	private String publicTime;
	private String startTime;
	private String endTime;
	private String introduction;
	private Integer status;
	private Integer chatStatus;
	private String videoUrl;
	private String imgUrl;
	private Integer participation;
	private Integer visitnum;
	private Integer stick;
	private Set CProductComments = new HashSet(0);
	private Set CProductStudentApplies = new HashSet(0);
	private Set CProductQuestions = new HashSet(0);
	private Set CProductEmploys = new HashSet(0);
	private Integer mode;

	// Constructors

	/** default constructor */
	public CProductBroadcast() {
	}

	/** full constructor */
	public CProductBroadcast(CProductHR CProductHR, String lable, String name,
			String publisher, String publicTime, String startTime,
			String endTime, String introduction, Integer status,
			String videoUrl, String imgUrl, Set CProductComments,
			Set CProductStudentApplies, Set CProductQuestions,LablePaste lablePaste) {
		this.CProductHR = CProductHR;
		this.lable = lable;
		this.name = name;
		this.publisher = publisher;
		this.publicTime = publicTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.introduction = introduction;
		this.status = status;
		this.videoUrl = videoUrl;
		this.imgUrl = imgUrl;
		this.CProductComments = CProductComments;
		this.CProductStudentApplies = CProductStudentApplies;
		this.CProductQuestions = CProductQuestions;
		this.lablePaste = lablePaste;
	}

	// Property accessors

	
	public Integer getId() {
		return this.id;
	}

	public Integer getVisitnum() {
		return visitnum;
	}

	public void setVisitnum(Integer visitnum) {
		this.visitnum = visitnum;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSONField(serialize = false)
	public CProductHR getCProductHR() {
		return this.CProductHR;
	}

	public void setCProductHR(CProductHR CProductHR) {
		this.CProductHR = CProductHR;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getParticipation() {
		return participation;
	}

	public void setParticipation(Integer participation) {
		this.participation = participation;
	}

	@JSONField(serialize = false)
	public Set getCProductComments() {
		return this.CProductComments;
	}

	public void setCProductComments(Set CProductComments) {
		this.CProductComments = CProductComments;
	}

	@JSONField(serialize = false)
	public Set getCProductStudentApplies() {
		return this.CProductStudentApplies;
	}

	public void setCProductStudentApplies(Set CProductStudentApplies) {
		this.CProductStudentApplies = CProductStudentApplies;
	}

	@JSONField(serialize = false)
	public Set getCProductQuestions() {
		return this.CProductQuestions;
	}

	public void setCProductQuestions(Set CProductQuestions) {
		this.CProductQuestions = CProductQuestions;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@JSONField(serialize = false)
	public Set getCProductEmploys() {
		return CProductEmploys;
	}

	public void setCProductEmploys(Set cProductEmploys) {
		CProductEmploys = cProductEmploys;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(Integer chatStatus) {
		this.chatStatus = chatStatus;
	}

	public LablePaste getLablePaste() {
		return lablePaste;
	}

	public void setLablePaste(LablePaste lablePaste) {
		this.lablePaste = lablePaste;
	}

	public Integer getStick() {
		return stick;
	}

	public void setStick(Integer stick) {
		this.stick = stick;
	}

	public String getProductLable() {
		return productLable;
	}

	public void setProductLable(String productLable) {
		this.productLable = productLable;
	}
	
}