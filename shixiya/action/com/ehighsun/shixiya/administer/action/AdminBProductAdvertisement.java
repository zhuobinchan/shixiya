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
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductAdvertisementService;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductAdvertisement extends
		BaseAction<BProductAdvertisement> {

	private File adv;
	private String advContentType;
	private String advFileName;

	private String page;
	private String ids;
	private Integer courseId;

	@Resource(name = "bProductAdvertisementService")
	private BProductAdvertisementService bProductAdvertisementService;
	@Resource(name = "bProductCourseService")
	private BProductCourseService bProductCourseService;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public File getAdv() {
		return adv;
	}

	public void setAdv(File adv) {
		this.adv = adv;
	}

	public String getAdvContentType() {
		return advContentType;
	}

	public void setAdvContentType(String advContentType) {
		this.advContentType = advContentType;
	}

	public String getAdvFileName() {
		return advFileName;
	}

	public void setAdvFileName(String advFileName) {
		this.advFileName = advFileName;
	}

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

	public String showAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addAdvertisement.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductAdvertisement> list = bProductAdvertisementService
				.findAdv("from BProductAdvertisement", null, pageBean);
		Long count = bProductAdvertisementService
				.countAdvs("select count(*) from BProductAdvertisement");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminAdv_showAdv.action", count,
				Integer.parseInt(page), 6, null);
		List<BProductCourse> clist = bProductCourseService.findBCourse();

		this.getSession().setAttribute("courseList", clist);
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("adv", list);

		return "showAdv";
	}

	public String addAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();

		if (adv != null) {
			String houzhui = advFileName.substring(advFileName.indexOf('.'));
			String randomDir = this.generateRandomDir(UUID.randomUUID()
					.toString());
			String path = request.getSession().getServletContext()
					.getRealPath("/images/" + randomDir);
			String saveName = UUID.randomUUID().toString();
			String savePath = "images/" + randomDir + "/" + saveName + houzhui;
			File file = new File(path, saveName + houzhui);
			try {
				path = file.getCanonicalPath();
				FileUtils.copyFile(adv, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setImageUrl(savePath);
		} else {

			BProductAdvertisement adv = bProductAdvertisementService
					.findAdvertisementById(model.getId());
			if (adv != null && adv.getImageUrl() != null) {
				model.setImageUrl(adv.getImageUrl());
			}
		}
		if (courseId != null && !courseId.equals("")) {
			BProductCourse course = bProductCourseService
					.findBCourseById(courseId);

			model.setTargetUrl(course.getId() + "");
		}
		bProductAdvertisementService.addAdvertisement(model);

		return "addAdv";
	}

	public String deleteAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();

		BProductAdvertisement adv = bProductAdvertisementService
				.findAdvertisementById(model.getId());
		String path = request.getSession().getServletContext()
				.getRealPath("/" + adv.getImageUrl());
		File f = new File(path);
		f.delete();

		bProductAdvertisementService.deleteAdvertisement(adv);

		return "deleteAdv";
	}

	public String deleteAdvs() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {

			BProductAdvertisement adv = bProductAdvertisementService
					.findAdvertisementById(Integer.parseInt(str[i]));
			String path = request.getSession().getServletContext()
					.getRealPath("/" + adv.getImageUrl());
			File f = new File(path);
			f.delete();
			bProductAdvertisementService.deleteAdvertisement(adv);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deleteAdvs";
	}

	public String findByStatus() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addAdvertisement.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getStatus() != null && !"".equals(model.getStatus())) {

			map.put("status", model.getStatus());
		}

		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductAdvertisement> list = bProductAdvertisementService
				.findPageByMap("from BProductAdvertisement", map, pageBean);
		Long count = bProductAdvertisementService
				.countAdvs("select count(*) from BProductAdvertisement where status = "
						+ model.getStatus());
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminAdv_findByStatus.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("adv", list);

		return "findByStatus";
	}

	public String updateAdv() {

		BProductAdvertisement adv = bProductAdvertisementService
				.findAdvertisementById(model.getId());

		if (adv.getStatus() == 0) {

			adv.setStatus(1);
			bProductAdvertisementService.saveAdvs(adv);
			return "updateAdv";
		}
		if (adv.getStatus() == 1) {
			adv.setStatus(0);
			bProductAdvertisementService.saveAdvs(adv);
			return "updateAdv";
		}

		return "updateAdv";
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
