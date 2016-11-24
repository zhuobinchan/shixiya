package com.ehighsun.shixiya.pojo;

// default package

/**
 * CProductAdv entity. @author MyEclipse Persistence Tools
 */

public class CProductAdv implements java.io.Serializable {

	// Fields

	private Integer id;

	private String imageUrl;
	private String targetUrl;
	private Integer status;

	// Constructors

	/** default constructor */
	public CProductAdv() {
	}

	/** full constructor */
	public CProductAdv(String imageUrl, String targetUrl) {
		this.targetUrl = targetUrl;
		this.imageUrl = imageUrl;
	}

	// Property accessors

	
	
	public Integer getId() {
		return this.id;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}