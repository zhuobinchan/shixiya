package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductTeacherService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductTeacherAction extends BaseAction<BProductTeacher> {

	private String page;
	private String ids;

	private File image;
	private String imageContentType;
	private String imageFileName;

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Autowired
	private BProductTeacherService bProductTeacherService;

	public String showAddTeacher() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addTeacher.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductTeacher> list = bProductTeacherService.findBTeacher(
				"from BProductTeacher", null, pageBean);
		Long count = bProductTeacherService
				.countTeacher("select count(*) from BProductTeacher");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminTeacher_showAddTeacher.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("teachers", list);

		return "showSuccess";
	}

	public String loginBTeacher() {

		BProductTeacher bt = bProductTeacherService.findBTeacherById(model
				.getId());

		if (bt == null) {
			this.getSession().setAttribute("login_error", "账号不存在");
		} else {
			if (bt.getTelephone().equals(model.getTelephone())) {

				this.getSession().setAttribute("user", bt);
			} else {

				this.getSession().setAttribute("login_error", "密码不对");

			}
		}

		return "loginSuccess";
	}

	public String addBTeacher() {

		HttpServletRequest request = ServletActionContext.getRequest();

		if (imageFileName != null) {
			String houzhui = imageFileName
					.substring(imageFileName.indexOf('.'));
			String randomDir = this.generateRandomDir(UUID.randomUUID()
					.toString());
			String path = request.getSession().getServletContext()
					.getRealPath("/images/" + randomDir);
			String saveName = UUID.randomUUID().toString();
			String savePath = "images/" + randomDir + "/" + saveName + houzhui;
			File file = new File(path, saveName + houzhui);
			try {
				path = file.getCanonicalPath();
				FileUtils.copyFile(image, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setHeadImgUrl(savePath);
		} else {

			BProductTeacher t = bProductTeacherService.findBTeacherById(model
					.getId());
			if (t != null && t.getHeadImgUrl() != null) {
				model.setHeadImgUrl(t.getHeadImgUrl());
			}
		}
		model.setPassword(StringUtil.md5(model.getPassword()));
		bProductTeacherService.saveOrAdd(model);

		return "addBTeacher";
	}

	public String deleteBTeacher() {

		HttpServletRequest request = ServletActionContext.getRequest();
		BProductTeacher t = bProductTeacherService.findBTeacherById(model
				.getId());
		String path = request.getSession().getServletContext()
				.getRealPath("/" + t.getHeadImgUrl());
		File f = new File(path);
		f.delete();

		bProductTeacherService.deleteBTeacher(model);

		return "deleteBTeacher";
	}

	public String findBTeacher() {

		List<BProductTeacher> lists = bProductTeacherService.findBTeacher();

		this.getSession().setAttribute("BProductTeacherLists", lists);

		return "findBTeacher";

	}

	public String findByMap() {

		// 条件的封装
		Map<String, Object> map = this.getMap(model);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addTeacher.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductTeacher> list = bProductTeacherService.findPageByMap(
				"from BProductTeacher", map, pageBean);
		Long count = bProductTeacherService.countByMap(
				"select count(*) from BProductTeacher", map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminTeacher_findByMap.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("teachers", list);

		return "findByMap";

	}

	public Map<String, Object> getMap(BProductTeacher model) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (model != null) {

			if (model.getName() != null && !"".equals(model.getName())) {
				map.put("name", model.getName());
			}
			if (model.getTelephone() != null
					&& !"".equals(model.getTelephone())) {

				map.put("telephone", model.getTelephone());
			}
			if (model.getEmail() != null && !"".equals(model.getEmail())) {

				map.put("email", model.getEmail());
			}

		}

		return map;

	}

	public String deleteTeachers() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String[] str = ids.split(",");

		for (int i = 0; i < str.length; i++) {

			BProductTeacher t = bProductTeacherService.findBTeacherById(Integer
					.parseInt(str[i]));
			String path = request.getSession().getServletContext()
					.getRealPath("/" + t.getHeadImgUrl());
			File f = new File(path);
			f.delete();
			bProductTeacherService.deleteBTeacher(t);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleteTeachers";
	}

	public String generateRandomDir(String uuidFileName) {
		// 获得唯一文件名的hashcode
		int hashcode = uuidFileName.hashCode();
		// 获得一级目录
		int d1 = hashcode & 0xf;
		// 获得二级目录
		int d2 = (hashcode >>> 4) & 0xf;

		return d2 + "/" + d1;// 共有256目录l
	}

}
