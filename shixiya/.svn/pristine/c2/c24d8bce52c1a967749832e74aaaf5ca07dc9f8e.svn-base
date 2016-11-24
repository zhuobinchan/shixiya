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
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductHRService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminCProductHRAction extends BaseAction<CProductHR> {

	private File image;
	private String imageContentType;
	private String imageFileName;

	private String page;
	private String ids;

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

	@Resource(name = "cProductHRService")
	private CProductHRService cProductHRService;

	public String showHR() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addHR.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<CProductHR> list = cProductHRService.findCProductHR(
				"from CProductHR", new Object[] {}, pageBean);
		Long count = cProductHRService
				.countCProductHR("select count(*) from CProductHR");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminHR_showHR.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("hr", list);

		return "showHR";
	}

	public String addHR() {

		HttpServletRequest request = ServletActionContext.getRequest();

		if (image != null) {
			
			FileUploadUtil fileUtil = new FileUploadUtil();
			String savePath = fileUtil.updateFile(image, imageFileName, "images");
			
//			String houzhui = imageFileName
//					.substring(imageFileName.indexOf('.'));
//			String randomDir = this.generateRandomDir(UUID.randomUUID()
//					.toString());
//			String path = request.getSession().getServletContext()
//					.getRealPath("/images/" + randomDir);
//			String saveName = UUID.randomUUID().toString();
//			String savePath = "images/" + randomDir + "/" + saveName + houzhui;
//			File file = new File(path, saveName + houzhui);
//			try {
//				path = file.getCanonicalPath();
//				FileUtils.copyFile(image, file);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			model.setHeadImgUrl(savePath);
		} else {
			CProductHR hr = cProductHRService.findHRById(model.getHrId());
			if (hr != null && hr.getHeadImgUrl() != null) {
				model.setHeadImgUrl(hr.getHeadImgUrl());
			}
		}

		model.setStatus(1);
		model.setPassword(StringUtil.md5(model.getPassword()));
		cProductHRService.addOrUpdate(model);

		return "addHR";
	}

	public String deleteHR() {

		HttpServletRequest request = ServletActionContext.getRequest();

		CProductHR hr = cProductHRService.findHRById(model.getHrId());
		if (hr != null) {

			String path = request.getSession().getServletContext()
					.getRealPath("/" + hr.getHeadImgUrl());
			if (path != null) {
				File f = new File(path);
				f.delete();
			}
			hr.setStatus(0);
			cProductHRService.deleteCProductHR(hr);
		}

		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String deleteHRs() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");
		String path = null;

		for (int i = 0; i < str.length; i++) {

			CProductHR hr = cProductHRService.findHRById(Integer
					.parseInt(str[i]));
			if (hr != null) {
				path = request.getSession().getServletContext()
						.getRealPath("/" + hr.getHeadImgUrl());
				if (path != null) {
					File f = new File(path);
					f.delete();
				}
				hr.setStatus(0);
				cProductHRService.deleteCProductHR(hr);
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

		return null;

	}

	public String findByMap() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addHR.jsp");

		Map<String, Object> map = this.getMap(model);

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<CProductHR> list = cProductHRService.findPageByMap(
				"from CProductHR", map, pageBean);
		Long count = cProductHRService.countByMap(
				"select count(*) from CProductHR", map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminHR_showHR.action", count,
				Integer.parseInt(page), 6, null);

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("hr", list);

		return "findByMap";
	}

	public Map<String, Object> getMap(CProductHR model) {

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
			if (model.getCompany() != null && !"".equals(model.getCompany())) {

				map.put("company", model.getCompany());
			}

		}

		return map;

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
