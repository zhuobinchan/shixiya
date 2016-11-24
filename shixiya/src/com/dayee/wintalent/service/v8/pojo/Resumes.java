package com.dayee.wintalent.service.v8.pojo;

import javax.xml.bind.annotation.XmlElement;

public class Resumes {

	private BasicInfos BasicInfo;
	private ResumeContents ResumeContent;
	
	
	
	public Resumes() {
		super();
	}

	public Resumes(BasicInfos basicInfo, ResumeContents resumeContent) {
		super();
		this.BasicInfo = basicInfo;
		this.ResumeContent = resumeContent;
	}

	@XmlElement(name="BasicInfo")
	public BasicInfos getBasicInfo() {
		return BasicInfo;
	}

	public void setBasicInfo(BasicInfos basicInfo) {
		BasicInfo = basicInfo;
	}

	@XmlElement(name="ResumeContent")
	public ResumeContents getResumeContent() {
		return ResumeContent;
	}

	public void setResumeContent(ResumeContents resumeContent) {
		ResumeContent = resumeContent;
	}

	@Override
	public String toString() {
		return "Resumes [BasicInfo=" + BasicInfo.toString() + ", ResumeContent="
				+ ResumeContent + "]";
	}
	
	
	
}
