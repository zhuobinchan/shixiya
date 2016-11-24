package com.ehighsun.shixiya.pojo;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * BProductChapter entity. @author MyEclipse Persistence Tools
 */

public class BProductChapter implements java.io.Serializable {

	// Fields

	private Integer id;
	private BProductCourse BProductCourse;
	private String title;
	private String introduction;
	private Set BProductVideos = new HashSet(0);

	// Constructors

	/** default constructor */
	public BProductChapter() {
	}

	public BProductChapter(Integer id, String title, String introduction) {
		super();
		this.id = id;
		this.title = title;
		this.introduction = introduction;
	}

	/** full constructor */
	public BProductChapter(BProductCourse BProductCourse, String title,
			String introduction, Set BProductVideos) {
		this.BProductCourse = BProductCourse;
		this.title = title;
		this.introduction = introduction;
		this.BProductVideos = BProductVideos;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BProductCourse getBProductCourse() {
		return this.BProductCourse;
	}

	public void setBProductCourse(BProductCourse BProductCourse) {
		this.BProductCourse = BProductCourse;
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

	public Set getBProductVideos() {
		return this.BProductVideos;
	}

	public void setBProductVideos(Set BProductVideos) {
		this.BProductVideos = BProductVideos;
	}

}