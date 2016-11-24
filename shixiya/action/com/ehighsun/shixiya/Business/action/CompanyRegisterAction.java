package com.ehighsun.shixiya.Business.action;

import java.io.File;

import javax.annotation.Resource;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyRegisterAction extends ActionSupport {
	
	private RIBussiness bussiness;
	
	private File logo;
	private String logoFileName;
	
	private File fujian;
	private String fujianFileName;
	
	@Resource(name="baseDao")
	private BaseDao<RIBussiness> bussinessDao;
	
	
	public String SaveBussiness(){

		if(logo != null){
			
			FileUploadUtil fileTools = new FileUploadUtil();
			
			String logoUrl = fileTools.updateFile(logo, logoFileName, "images");
			
			bussiness.setLogoUrl(logoUrl);
			
			
		}
		
		
		if(fujian != null){
			
			FileUploadUtil fileTools = new FileUploadUtil();
			
			String fujianUrl = fileTools.updateFile(fujian, fujianFileName, "companyFile");
			
			bussiness.setFileUrl(fujianUrl);
			
			
		}
		
		
		bussiness.setState(0);
		bussinessDao.save(bussiness);
		
		return "SaveBussiness";
	}
	
	public String UpdateBussiness(){
		
		RIBussiness riBussiness_form_dataBase = bussinessDao.get(RIBussiness.class, bussiness.getId());
		
		if(logo != null){
			
			FileUploadUtil fileTools = new FileUploadUtil();
			
			String logoUrl = fileTools.updateFile(logo, logoFileName, "images");
			
			bussiness.setLogoUrl(logoUrl);
			
			
		}
		
		if(riBussiness_form_dataBase !=null){
			
			if (StringUtil.isEmpty(bussiness.getPassword())) {
				bussiness.setPassword(riBussiness_form_dataBase.getPassword());
			}			
			
			bussiness.setEmail(riBussiness_form_dataBase.getEmail());
			bussiness.setState(riBussiness_form_dataBase.getState());
			bussiness.setFileUrl(riBussiness_form_dataBase.getFileUrl());
		}
		
		bussinessDao.merge(bussiness);
		
		return "UpdateBussiness";
	}	
	

	public RIBussiness getBussiness() {
		return bussiness;
	}

	public void setBussiness(RIBussiness bussiness) {
		this.bussiness = bussiness;
	}


	public File getFujian() {
		return fujian;
	}


	public void setFujian(File fujian) {
		this.fujian = fujian;
	}


	public String getFujianFileName() {
		return fujianFileName;
	}


	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
	
	
}
