package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * BProductCourse entity. @author MyEclipse Persistence Tools
 */
@JSONType(orders = { "BProductTeacher", "BProductStudentApplies",
		"BProductChapters" })
public class BProductCourse implements java.io.Serializable {

	private Integer id;
	private BProductTeacher BProductTeacher;
	private String lable;
	private String productLable;
	private String title;
	private String publisher;
	private String publicTime;
	private String startTime;
	private String endTime;
	private String imageUrl;
	private String introduction;
	private Integer participation;
	private Integer visitnum;
	private String detailsURL;
	private String richtext;
	private Integer stick;
	private Set BProductStudentApplies = new HashSet(0);
	private Set BProductChapters = new HashSet(0);

	// Constructors

	/** default constructor */
	public BProductCourse() {
	}

	/** full constructor */
	public BProductCourse(BProductTeacher BProductTeacher, String lable,
			String title, String publisher, String publicTime,
			String startTime, String endTime, String imageUrl,
			String introduction, Set BProductStudentApplies,
			Set BProductChapters) {
		this.BProductTeacher = BProductTeacher;
		this.lable = lable;
		this.title = title;
		this.publisher = publisher;
		this.publicTime = publicTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.imageUrl = imageUrl;
		this.introduction = introduction;
		this.BProductStudentApplies = BProductStudentApplies;
		this.BProductChapters = BProductChapters;

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
	public BProductTeacher getBProductTeacher() {
		return this.BProductTeacher;
	}

	public void setBProductTeacher(BProductTeacher BProductTeacher) {
		this.BProductTeacher = BProductTeacher;
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@JSONField(serialize = false)
	public Set getBProductStudentApplies() {
		return this.BProductStudentApplies;
	}

	public void setBProductStudentApplies(Set BProductStudentApplies) {
		this.BProductStudentApplies = BProductStudentApplies;
	}

	@JSONField(serialize = false)
	public Set getBProductChapters() {
		return this.BProductChapters;
	}

	public void setBProductChapters(Set BProductChapters) {
		this.BProductChapters = BProductChapters;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Integer getParticipation() {
		return participation;
	}

	public void setParticipation(Integer participation) {
		this.participation = participation;
	}

	public String getDetailsURL() {
		return detailsURL;
	}

	public void setDetailsURL(String detailsURL) {
		this.detailsURL = detailsURL;
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

	public String getRichtext() {
		return richtext;
	}

	public void setRichtext(String richtext) {
		this.richtext = richtext;
	}

}