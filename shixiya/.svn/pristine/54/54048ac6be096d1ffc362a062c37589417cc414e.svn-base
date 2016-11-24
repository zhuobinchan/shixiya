package com.ehighsun.shixiya.administer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jiabin.entity.User;
import jiabin.service.UserService;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RoleInfoTransform;
import com.ehighsun.shixiya.service.AdminService;
import com.ehighsun.shixiya.service.RoleService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminAction extends BaseAction<Administer> {

	private String page;
	private String ids;
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
	
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="UserService")
	private UserService userService;
	
	
	public String showAdmin(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addAdmin.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Administer> list = adminService.findAdminister("from Administer", null, pageBean);
		Long count = adminService.countAdminister("select count(*) from Administer");
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/admin_showAdmin.action", count, Integer.parseInt(page),6,null);
		
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("admin", list);
		
		
		
		return "showAdmin";
	}
	
	public String addAdmin(){
		
		
		adminService.addOrUpdate(model);
		
		RoleInfoTransform r = new RoleInfoTransform();
		String uuid = UUID.randomUUID().toString();
		r.setRoleId(model.getId()+"");
		r.setRoleType(4);
		r.setUserId(uuid);
		roleService.saveRoleInfo(r);
		
		User u = new User();
		u.setId(r.getUserId()+"");
		u.setType(2);
		u.setTrueName(model.getName());
		u.setNickName("管理员");
		if(model.getTelephone()!=null){
			u.setMobile(model.getTelephone()+"");
		}
		u.setRegTime(new Date());
		System.out.println(u.getId()+"snd]]]]]]]]");
		userService.saveUser(u);
		
		return "addAdmin";
	}
	
	public String deleteAdmin(){
		
		Administer admin = adminService.findAdminById(model.getId());
		if(admin!=null){
			adminService.deleteAdminister(admin);
		}
		return "deleteAdmin";
	}
	
	public String deleteAdmins(){
		
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			Administer admin = adminService.findAdminById(Integer.parseInt(str[i]));
			if(admin!=null){
				adminService.deleteAdminister(admin);
			}
		}
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "deleteAdmins";
	}
	
	public String findAdminByMap(){
	
		HttpServletRequest request = ServletActionContext.getRequest();
	
		request.setAttribute("mainPage", "addAdmin.jsp");
		
		Map<String,Object> map = this.getMap(model);
		

		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<Administer> list = adminService.findPageByMap("from Administer", map, pageBean);
		Long count = adminService.countByMap("select count(*) from Administer",map);
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/admin_showAdmin.action", count, Integer.parseInt(page),6,null);
		
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("admin", list);
		
		
		return "findAdminByMap";
	}
	
	public Map<String,Object> getMap(Administer model){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(model!=null){
			
			if(model.getName()!=null && !"".equals(model.getName())){
				System.out.println(model.getName()+"//////////");
				map.put("name", model.getName());
			}
			if(model.getTelephone()!=null && !"".equals(model.getTelephone())){
				System.out.println(model.getTelephone()+"//////////");
				map.put("telephone", model.getTelephone());
			}
			
		}
		
		return map;
		
	}
	

}
