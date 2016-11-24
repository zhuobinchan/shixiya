package com.ehighsun.shixiya.pojo;

// default package

/**
 * Lable entity. @author MyEclipse Persistence Tools
 */

public class Lable implements java.io.Serializable {

	// Fields

	private Integer lableId;
	private String lableName;
	private Integer type; // 类型 0 代表 能力匹配标签类型 ；1 代表筛选标签类型

	// Constructors

	/** default constructor */
	public Lable() {
	}

	/** full constructor */
	public Lable(String lableName) {
		this.lableName = lableName;
	}

	// Property accessors

	public Integer getLableId() {
		return this.lableId;
	}

	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}

	public String getLableName() {
		return this.lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}