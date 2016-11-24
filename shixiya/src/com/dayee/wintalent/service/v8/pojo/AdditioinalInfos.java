package com.dayee.wintalent.service.v8.pojo;

import javax.xml.bind.annotation.XmlElement;

public class AdditioinalInfos {

	private String Title;//信息名称
	private String Description;//信息内容

	
	
	
	public AdditioinalInfos() {
		super();
	}
	public AdditioinalInfos(String title, String description) {
		super();
		Title = title;
		Description = description;
	}
	@XmlElement(name="Title")
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "AdditioinalInfos [Title=" + Title + ", Description="
				+ Description + "]";
	}
	
	
	
}
