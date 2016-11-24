package com.dayee.wintalent.service.v8.pojo;

import javax.xml.bind.annotation.XmlElement;

public class ProjectExperiences {

	private String StartDate;//开始时间
	private String EndDate;//结束时间
	private String ProjectName;//项目名称
	private String ProjectDescription;//项目描述
	private String Responsibilities;//项目职责
	
	

	public ProjectExperiences() {
		super();
	}
	public ProjectExperiences(String startDate, String endDate,
			String projectName, String projectDescription,
			String responsibilities) {
		super();
		StartDate = startDate;
		EndDate = endDate;
		ProjectName = projectName;
		ProjectDescription = projectDescription;
		Responsibilities = responsibilities;
	}
	@XmlElement(name="StartDate")
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	@XmlElement(name="EndDate")
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	@XmlElement(name="ProjectName")
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	@XmlElement(name="ProjectDescription")
	public String getProjectDescription() {
		return ProjectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		ProjectDescription = projectDescription;
	}
	@XmlElement(name="Responsibilities")
	public String getResponsibilities() {
		return Responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		Responsibilities = responsibilities;
	}
	@Override
	public String toString() {
		return "[开始时间:" + StartDate + ", 结束时间:"
				+ EndDate + ", 项目名称:" + ProjectName
				+ ", 项目描述:" + ProjectDescription
				+ ", 项目职责:" + Responsibilities + "]";
	}
	

	
}
