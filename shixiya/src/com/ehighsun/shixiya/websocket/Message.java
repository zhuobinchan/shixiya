package com.ehighsun.shixiya.websocket;

public class Message {
	private String recordType;
	private String content;
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String string) {
		this.recordType = string;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toJson(){
		return "{'recordType':'"+recordType+"','content':'"+content+"'}";
	}
}
