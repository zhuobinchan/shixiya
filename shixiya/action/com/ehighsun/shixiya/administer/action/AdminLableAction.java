package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductAdvService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminLableAction extends BaseAction<Lable>{

	private String page;
	private String ids;
	
	@Resource(name="lableService")
	private LableService  lableService;
	
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String showLable(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addLable.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Lable> list = lableService.findLable("from Lable", null, pageBean);
		Long count = lableService.countLable("select count(*) from Lable",null);
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminLable_showLable.action", count, Integer.parseInt(page),6,null);
	
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("lables", list);
	
		
		return "showLable";
	}
	
	public String addLable(){

		lableService.addLable(model);
		
		return "addLable";
	}
	
	public String deleteLable(){
		
		
		lableService.deleteLable(model);
		return "deleteLable";
	}
	
	public String deleteLables(){

			String[] str = ids.split(",");
			Lable lable = new Lable();
		
			for (int i = 0; i < str.length; i++) {
				
				lable.setLableId(Integer.parseInt(str[i]));
				lableService.deleteLable(lable);
			}
			JSONObject result=new JSONObject();
			result.put("success", true);
			try {
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return "deleteLables";
	}
	
	public String findByName(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addLable.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(model.getLableName()!=null && !"".equals(model.getLableName())){
			
			map.put("lableName", model.getLableName());
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Lable> list = lableService.findPageByMap("from Lable", map, pageBean);
		Long count = null;
		if(model.getLableName()!=null && !"".equals(model.getLableName())){
			count = lableService.countLable("select count(*) from Lable where lableName like ?",new Object[]{model.getLableName()});
		}else{
			 count = lableService.countLable("select count(*) from Lable",null);
		}
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminLable_findByName.action", count, Integer.parseInt(page),6,null);
		

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("lables", list);

		return "findByName";
	}
	
	
	
		
}
