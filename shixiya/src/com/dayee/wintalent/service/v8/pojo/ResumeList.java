package com.dayee.wintalent.service.v8.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ResumeList")
public class ResumeList {

	private List<Resumes> Resume;
	
	

	public ResumeList() {
		super();
	}

	public ResumeList(List<Resumes> resume) {
		
		this.Resume = resume;
	}
	
	@XmlElement(name="Resume")
	public List<Resumes> getResume() {
		return Resume;
	}

	public void setResume(List<Resumes> resume) {
		Resume = resume;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for (Resumes r : Resume) {
			sb.append(r.toString());
		}
		return "ResumeList [Resume=" + sb.toString()+ "]";
	} 
	
	
	
}
