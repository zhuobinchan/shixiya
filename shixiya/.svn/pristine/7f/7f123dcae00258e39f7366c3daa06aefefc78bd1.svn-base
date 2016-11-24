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
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectAdv;
import com.ehighsun.shixiya.service.PreferenceSelectAdvService;
import com.ehighsun.shixiya.service.PreferenceSelectService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminPreferenceSelectAdvAction extends
		BaseAction<PreferenceSelectAdv> {
	private File adv;
	private String advContentType;
	private String advFileName;

	private String page;
	private String ids;

	private Integer preferenceSelectId;

	@Resource(name = "preferenceSelectAdvService")
	private PreferenceSelectAdvService preferenceSelectAdvService;

	@Resource(name = "preferenceSelectServiceImpl")
	private PreferenceSelectService preferenceSelectService;

	public String showAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addPreferenceSelectAdv.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<PreferenceSelectAdv> list = preferenceSelectAdvService.findAdv(
				"from PreferenceSelectAdv", null, pageBean);
		Long count = preferenceSelectAdvService
				.countAdvs("select count(*) from PreferenceSelectAdv");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/showAdv_AdminPreferenceSelectAdvAction.action",
				count, Integer.parseInt(page), 6, null);
		List<PreferenceSelect> blist = preferenceSelectService
				.findAllPreferenceSelect();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("preferenceSelect", blist);
		this.getSession().setAttribute("adv", list);

		return "showAdv";
	}

	public String addAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String houzhui = advFileName.substring(advFileName.indexOf('.'));
		String randomDir = this.generateRandomDir(UUID.randomUUID().toString());
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
		System.out.println(preferenceSelectId);
		PreferenceSelect b = preferenceSelectService
				.getPreferenceSelectById(preferenceSelectId);
		preferenceSelectService.evitPreferenceSelect(b);

		model.setImageUrl(savePath);
		model.setTargetUrl("" + b.getId());

		preferenceSelectAdvService.addAdvertisement(model);

		return "addAdv";
	}

	public String deleteAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();

		PreferenceSelectAdv adv = preferenceSelectAdvService
				.findAdvertisementById(model.getId());
		String path = request.getSession().getServletContext()
				.getRealPath("/" + adv.getImageUrl());
		File f = new File(path);
		f.delete();

		preferenceSelectAdvService.deleteAdvertisement(adv);

		return "deleteAdv";
	}

	public String deleteAdvs() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");
		System.out.println("//////////////kasndlknsda" + ids);
		for (int i = 0; i < str.length; i++) {

			PreferenceSelectAdv adv = preferenceSelectAdvService
					.findAdvertisementById(Integer.parseInt(str[i]));
			String path = request.getSession().getServletContext()
					.getRealPath("/" + adv.getImageUrl());
			File f = new File(path);
			f.delete();
			preferenceSelectAdvService.deleteAdvertisement(adv);
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

		System.out.println("asdjasfha///////////");

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addPreferenceSelectAdv.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getStatus() != null && !"".equals(model.getStatus())) {

			map.put("status", model.getStatus());
		}

		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<PreferenceSelectAdv> list = preferenceSelectAdvService
				.findPageByMap("from PreferenceSelectAdv", map, pageBean);
		Long count = preferenceSelectAdvService
				.countAdvs("select count(*) from PreferenceSelectAdv where status = "
						+ model.getStatus());
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/findByStatus_AdminPreferenceSelectAdvAction.action",
				count, Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("adv", list);

		return "findByStatus";
	}

	public String updateAdv() {

		PreferenceSelectAdv adv = preferenceSelectAdvService
				.findAdvertisementById(model.getId());
		if (adv.getStatus() == 0) {

			adv.setStatus(1);
			preferenceSelectAdvService.saveAdvs(adv);
			return "updateAdv";
		}
		if (adv.getStatus() == 1) {
			adv.setStatus(0);
			preferenceSelectAdvService.saveAdvs(adv);
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

	public Integer getPreferenceSelectId() {
		return preferenceSelectId;
	}

	public void setPreferenceSelectId(Integer preferenceSelectId) {
		this.preferenceSelectId = preferenceSelectId;
	}

}
