package com.ehighsun.shixiya.pojo;

public class DicInfo implements java.io.Serializable {
	
	private Double id;
	private Double superiorId;
	private String code;
	private String name;
	

	public DicInfo() {
		
	}
	
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public Double getSuperiorId() {
		return superiorId;
	}
	public void setSuperiorId(Double superiorId) {
		this.superiorId = superiorId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
