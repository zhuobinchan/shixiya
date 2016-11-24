package com.ehighsun.shixiya.BBS.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import jiabin.entity.Section;
import jiabin.entity.Zone;
import jiabin.service.SectionService;
import jiabin.service.ZoneService;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductAdvertisementService;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BBSAction extends ActionSupport{
	
	@Resource(name="zoneService")
	private ZoneService zoneService;
	@Resource(name="bProductAdvertisementService")
	private BProductAdvertisementService bProductAdvertisementService;
	@Resource(name="sectionService")
 	private SectionService sectionService;
	
	
	private Integer zoneId;
	private Integer sectionId;
	private String page;
	
	
	
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String enterBBS(){
		
		List<Zone> zoneList = zoneService.findAllZoneList();
		
		List<BProductAdvertisement> advList =
				bProductAdvertisementService.findAllAdvertisementByState();
		
		this.getSession().setAttribute("advList", advList);
		this.getSession().setAttribute("zoneList", zoneList);

		return "enterBBS";
	}
	
	public String showSections(){
		
	
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Section> sectionList = sectionService.findSection(pageBean, zoneId);
		
		this.getSession().setAttribute("sectionList", sectionList);
		
		return "showSections";
	}
	
	public String enteSection(){
		
		
		
		return "enteSection";
	}

	public HttpSession getSession(){
		
		return ServletActionContext.getRequest().getSession();
	} 
	
}
