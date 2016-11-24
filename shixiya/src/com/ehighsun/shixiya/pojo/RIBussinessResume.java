package com.ehighsun.shixiya.pojo;

public class RIBussinessResume {

	private Integer id;
	private String worldUrl;
	private String htmlUrl;
	private Student student;
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorldUrl() {
		return worldUrl;
	}

	public void setWorldUrl(String worldUrl) {
		this.worldUrl = worldUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
