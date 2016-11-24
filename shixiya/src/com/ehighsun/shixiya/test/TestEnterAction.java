package com.ehighsun.shixiya.test;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;


public class TestEnterAction extends ActionSupport {
	
//	private String id;
//	@Resource(name="baseDao")
//	public BaseDao<PreferenceSelect> baseDao;
//	
//	public String testGetHtml()  {
//		System.out.println(id);
//		JSONObject object = new JSONObject();
//		object.put("message", "ok");
//		ResponseUtil.write(ServletActionContext.getResponse(), object.toString());
//		PreferenceSelect bProductCourse = baseDao.get(PreferenceSelect.class, 9);
//		bProductCourse.setWorkIntroduction(id);
//		baseDao.save(bProductCourse);
//		return null;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
	
	
}
