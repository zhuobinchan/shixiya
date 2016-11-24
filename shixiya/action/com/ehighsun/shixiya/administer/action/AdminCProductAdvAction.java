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
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductAdvService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminCProductAdvAction extends BaseAction<CProductAdv> {

	private File adv;
	private String advContentType;
	private String advFileName;

	private String page;
	private String ids;

	private Integer broadcastId;

	@Resource(name = "cProductAdvService")
	private CProductAdvService cProductAdvService;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cProductBroadcastService;

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
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
		request.setAttribute("mainPage", "addCAdv.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<CProductAdv> list = cProductAdvService.findAdv("from CProductAdv",
				null, pageBean);
		Long count = cProductAdvService
				.countAdvs("select count(*) from CProductAdv");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminCAdv_showAdv.action", count,
				Integer.parseInt(page), 6, null);
		List<CProductBroadcast> blist = cProductBroadcastService
				.getAllCPBroadcast();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("broadcastList", blist);
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

		if (broadcastId != null && !broadcastId.equals("")) {

			CProductBroadcast b = cProductBroadcastService
					.findById(broadcastId);

			model.setImageUrl(savePath);
			model.setTargetUrl("" + b.getId());
		}
		cProductAdvService.addAdvertisement(model);

		return "addAdv";
	}

	public String deleteAdv() {

		HttpServletRequest request = ServletActionContext.getRequest();

		CProductAdv adv = cProductAdvService.findAdvertisementById(model
				.getId());
		String path = request.getSession().getServletContext()
				.getRealPath("/" + adv.getImageUrl());
		File f = new File(path);
		f.delete();

		cProductAdvService.deleteAdvertisement(adv);

		return "deleteAdv";
	}

	public String deleteAdvs() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");
		System.out.println("//////////////kasndlknsda" + ids);
		for (int i = 0; i < str.length; i++) {

			CProductAdv adv = cProductAdvService.findAdvertisementById(Integer
					.parseInt(str[i]));
			String path = request.getSession().getServletContext()
					.getRealPath("/" + adv.getImageUrl());
			File f = new File(path);
			f.delete();
			cProductAdvService.deleteAdvertisement(adv);
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
		request.setAttribute("mainPage", "addCAdv.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getStatus() != null && !"".equals(model.getStatus())) {

			map.put("status", model.getStatus());
		}

		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<CProductAdv> list = cProductAdvService.findPageByMap(
				"from CProductAdv", map, pageBean);
		Long count = cProductAdvService
				.countAdvs("select count(*) from CProductAdv where status = "
						+ model.getStatus());
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminCAdv_findByStatus.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("adv", list);

		return "findByStatus";
	}

	public String updateAdv() {

		CProductAdv adv = cProductAdvService.findAdvertisementById(model
				.getId());

		if (adv.getStatus() == 0) {

			adv.setStatus(1);
			cProductAdvService.saveAdvs(adv);
			return "updateAdv";
		}
		if (adv.getStatus() == 1) {
			adv.setStatus(0);
			cProductAdvService.saveAdvs(adv);
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
