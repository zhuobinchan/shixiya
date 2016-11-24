package com.ehighsun.shixiya.toBBS.action;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import jiabin.entity.User;
import jiabin.service.UserService;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.RoleInfoTransform;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

public class ShixiyaToBBSAction extends ActionSupport {
	
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="UserService")
	private UserService userService;
	

	public String toBBS(){
	
		
		Object o = this.getSession().getAttribute("user");
		
		switch (judgeUser(o)) {
		case 1:{
			
			Student s = (Student)o;
			RoleInfoTransform roleinfo = roleService.findByRoseId(s.getId()+"", 1);
			
			if(roleinfo!=null){
				User user = userService.getUserById(roleinfo.getUserId());
				if(!s.getNickname().equals(user.getNickName())){
					user.setNickName(s.getNickname());
					userService.saveUser(user);
				
				}
				this.getSession().setAttribute("currentUser", user);
			}else{
				
				User u = new User();
				RoleInfoTransform r = new RoleInfoTransform();
				
				String uuid = UUID.randomUUID().toString();
				
				r.setRoleId(s.getId()+"");
				r.setRoleType(1);
				r.setUserId(uuid);
				roleService.saveRoleInfo(r);
				
				u.setId(uuid);
				u.setType(1);
				u.setNickName(s.getNickname());
				u.setTrueName(s.getName());
				u.setFace(s.getHeadUrl());
				if(s.getTelephone()!=null){
					u.setMobile(s.getTelephone());
				}
				u.setRegTime(new Date());
				
				userService.saveUser(u);
				
				this.getSession().setAttribute("currentUser", u);
			}
			
			
			break;
		}	
		case 2:
		{
			
			BProductTeacher t = (BProductTeacher)o;
			RoleInfoTransform roleinfo = roleService.findByRoseId(t.getId()+"", 2);
			User u = null;
			
			if(roleinfo==null){
				
				u = new User();
				RoleInfoTransform r = new RoleInfoTransform();
				
				String uuid = UUID.randomUUID().toString();
				
				r.setRoleId(t.getId()+"");
				r.setRoleType(2);
				r.setUserId(uuid);
				roleService.saveRoleInfo(r);
				
				u.setId(uuid);
				u.setType(1);
				u.setTrueName(t.getName());
				u.setNickName(t.getName()+"老师");
				u.setFace(t.getHeadImgUrl());
				if(t.getTelephone()!=null){
					u.setMobile(t.getTelephone());
				}
				u.setRegTime(new Date());
				
				userService.saveUser(u);
				
				
			}else{
				
				u = userService.getUserById(roleinfo.getUserId());
				
			}
			
			this.getSession().setAttribute("currentUser", u);
			
			break;
		}
		case 3:
		{
			
			CProductHR hr = (CProductHR)o;
			RoleInfoTransform roleinfo = roleService.findByRoseId(hr.getHrId()+"",3);
			User u = null;
			
			if(roleinfo==null){
				
				u = new User();
				RoleInfoTransform r = new RoleInfoTransform();
				
				String uuid = UUID.randomUUID().toString();
				
				r.setRoleId(hr.getHrId()+"");
				r.setRoleType(3);
				r.setUserId(uuid);
				roleService.saveRoleInfo(r);
				
				u.setId(uuid);
				u.setType(1);
				u.setTrueName(hr.getName());
				u.setNickName(hr.getCompany()+":"+hr.getName());
				u.setFace(hr.getHeadImgUrl());
				if(hr.getTelephone()!=null){
					u.setMobile(hr.getTelephone());
				}
				u.setRegTime(new Date());
				
				userService.saveUser(u);
				
				
			}else{
				
				u = userService.getUserById(roleinfo.getUserId());
				
			}
			
			this.getSession().setAttribute("currentUser", u);
			
			break;
		}
		case 4:
		{
			
			Administer admin = (Administer)o;
			RoleInfoTransform roleinfo = roleService.findByRoseId(admin.getId()+"",4);
			User u = null;
			

			if(roleinfo==null){
				
				u = new User();
				RoleInfoTransform r = new RoleInfoTransform();
				
				String uuid = UUID.randomUUID().toString();
				
				r.setRoleId(admin.getId()+"");
				r.setRoleType(4);
				r.setUserId(uuid);
				roleService.saveRoleInfo(r);
				
				u.setId(uuid);
				u.setType(2);
				u.setTrueName(admin.getName());
				u.setNickName("管理员");
				if(admin.getTelephone()!=null){
					u.setMobile(admin.getTelephone()+"");
				}
				u.setRegTime(new Date());
				
				userService.saveUser(u);
				
				
			}else{
				
				u = userService.getUserById(roleinfo.getUserId());
				
			}
			
			this.getSession().setAttribute("currentUser", u);
			
			break;
		}

		default:
			break;
		}
		
		return "toBBS";
	}
	
	public HttpSession getSession(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		return session;
	}
	
	public Integer judgeUser(Object o){
		
		if(o instanceof Student){
			return 1;
		}
		if(o instanceof BProductTeacher){
			return 2;
		}
		if(o instanceof CProductHR){
			return 3;
		}
		if(o instanceof Administer){
			return 4;
		}
		return 0;
	}
}
