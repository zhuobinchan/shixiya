package com.ehighsun.shixiya.pojo;

public class PreferenceSelectEntered {

	private Integer id;
	private Integer studentId;
	private String name;
	private String telephone;
	private String email;
	private Integer emailState;// 1以发0未发
	private PreferenceSelect preferenceSelect;

	public Integer getEmailState() {
		return emailState;
	}

	public void setEmailState(Integer emailState) {
		this.emailState = emailState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PreferenceSelect getPreferenceSelect() {
		return preferenceSelect;
	}

	public void setPreferenceSelect(PreferenceSelect preferenceSelect) {
		this.preferenceSelect = preferenceSelect;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
