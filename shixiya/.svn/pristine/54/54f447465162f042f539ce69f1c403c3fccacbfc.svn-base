package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminStudentAction extends BaseAction<Student> {

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

	@Resource(name = "adminStudentService")
	private AdminStudentService adminStudentService;

	public String showStudent() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "student.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<Student> list = adminStudentService.findStudent("from Student",
				new Object[] {}, pageBean);
		Long count = adminStudentService
				.countStudent("select count(*) from Student");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminStudent_showStudent.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("students", list);

		return "showStudent";
	}

	public String deleteStudent() {

		HttpServletRequest request = ServletActionContext.getRequest();

		Student student = adminStudentService.findStudentById(model.getId());
		if (student != null) {

			String path = request.getSession().getServletContext()
					.getRealPath(student.getHeadUrl());
			if (path != null) {
				File f = new File(path);
				f.delete();
			}
			student.setStatus(0);
			student.setHeadUrl("");
			adminStudentService.deleteStudent(student);
		}

		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deleteStudent";
	}

	public String deleteStudents() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");

		Student student = null;

		for (int i = 0; i < str.length; i++) {

			student = adminStudentService.findStudentById(Integer
					.parseInt(str[i]));

			if (student != null) {

				String path = request.getSession().getServletContext()
						.getRealPath(student.getHeadUrl());
				if (path != null) {
					File f = new File(path);
					f.delete();
				}
				student.setStatus(0);
				student.setHeadUrl("");
				adminStudentService.deleteStudent(student);
			}
		}

		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deleteStudents";
	}

	public String findByMap() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "student.jsp");

		Map<String, Object> map = this.getMap(model);

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<Student> list = adminStudentService.findPageByMap("from Student ",
				map, pageBean);
		Long count = adminStudentService.countByMap(
				"select count(*) from Student", map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminStudent_findByMap.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("students", list);

		return "findByMap";
	}

	public Map<String, Object> getMap(Student model) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", 1);

		if (model != null) {

			if (model.getName() != null && !"".equals(model.getName())) {

				map.put("name", model.getName());
			}
			if (model.getTelephone() != null
					&& !"".equals(model.getTelephone())) {

				map.put("telephone", model.getTelephone());
			}

		}

		return map;

	}

}
