package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.service.RIBussinessService;
import com.ehighsun.shixiya.service.SendEmailService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminRIBusinessAction extends BaseAction<RIBussiness> {

	private String page;
	private String ids;
	private String pageCode;
	private List<RIBussiness> list;
	private String businessName;
	private String linkName;

	private File fileUrl;
	private String fileUrlContentType;
	private String fileUrlFileName;

	public String fileName;
	public InputStream fileInputStream;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
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

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public List<RIBussiness> getList() {
		return list;
	}

	public void setList(List<RIBussiness> list) {
		this.list = list;
	}

	public File getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(File fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileUrlContentType() {
		return fileUrlContentType;
	}

	public void setFileUrlContentType(String fileUrlContentType) {
		this.fileUrlContentType = fileUrlContentType;
	}

	public String getFileUrlFileName() {
		return fileUrlFileName;
	}

	public void setFileUrlFileName(String fileUrlFileName) {
		this.fileUrlFileName = fileUrlFileName;
	}

	@Resource(name = "riBussinessServiceImpl")
	private RIBussinessService riBussinessService;
	@Resource(name = "sendEmailServiceImpl")
	private SendEmailService sendEmailService;

	public String showRIBussiness() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RIBusiness.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		list = riBussinessService.findRIBussinesss("from RIBussiness", null,
				pageBean);
		Long count = riBussinessService
				.countRIBussiness("select count(*) from RIBussiness");
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/ribusiness_showRIBussiness.action", count,
				Integer.parseInt(page), 6, null);

		return "showRIBussiness";
	}

	public String addRIBusiness() {

		HttpServletRequest request = ServletActionContext.getRequest();
		if (fileUrl != null) {
			String houzhui = fileUrlFileName.substring(fileUrlFileName
					.indexOf('.'));
			String randomDir = this.generateRandomDir(UUID.randomUUID()
					.toString());
			String path = request.getSession().getServletContext()
					.getRealPath("/files/" + randomDir);
			String saveName = UUID.randomUUID().toString();
			String savePath = "files/" + randomDir + "/" + saveName + houzhui;
			File file = new File(path, saveName + houzhui);
			try {
				path = file.getCanonicalPath();
				FileUtils.copyFile(fileUrl, file);
			} catch (IOException e) {

				e.printStackTrace();
			}
			model.setFileUrl(savePath);
		}

		riBussinessService.addRIBussiness(model);
		return "addRIBusiness";
	}

	public String deleteRIBusiness() {
		riBussinessService.deleteRIBussiness(model.getId());
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

	public String deleteRIBusinesses() {
		String[] strs = ids.split(",");
		riBussinessService.deleteRIBussinesses(strs);
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

	public String findByCondition() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RIBusiness.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Map<String, Object> o = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();

		if (businessName != null && !"".equals(businessName)) {
			o.put("name", businessName);
			sb.append("businessName=" + businessName);
		}

		if (linkName != null && !"".equals(linkName)) {
			o.put("linkman", linkName);
			if (sb.toString() != null) {
				sb.append("&linkName=" + linkName);
			} else {
				sb.append("linkName=" + linkName);
			}
		}

		list = riBussinessService.findByCondition("from RIBussiness", o,
				pageBean);
		Long count = riBussinessService.countByCondition(
				"select count(*) from RIBussiness", o);

		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/ribusiness_findByCondition.action?" + sb.toString(),
				count, Integer.parseInt(page), 6, null);

		return "findByCondition";
	}

	public String updateRIBusiness() {

		System.out.println("开始");
		RIBussiness bussiness = riBussinessService.getRIBussinessById(model
				.getId());
		if (bussiness != null) {
			bussiness.setState(1);
			riBussinessService.updateRIBussiness(bussiness);

			String html = "<html><head></head>" + "<body>"
					+ "<h1>hello!!chao.wang</h1></br>"
					+ "<a href=\"www.baidu.com\">连接</a>" + "</body></html>";
			sendEmailService.sendEmailByHtml("jkb549121@sina.com", null,
					"海印集团", html);
		}

		return "updateRIBusiness";
	}

	public String outRIBusiness() {
		RIBussiness bussiness = riBussinessService.getRIBussinessById(model
				.getId());
		if (bussiness != null) {
			bussiness.setState(0);
			riBussinessService.updateRIBussiness(bussiness);
		}
		return "outRIBusiness";
	}

	public String downLoadFile() {

		System.out.println("id:" + model.getId());
		RIBussiness bussiness = riBussinessService.getRIBussinessById(model
				.getId());

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");

		if (bussiness != null) {

			if (StringUtil.isNotEmpty(bussiness.getName())) {

				fileName = bussiness.getName() + ".zip";
				try {
					fileName = new String(fileName.getBytes(), "ISO8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (StringUtil.isNotEmpty(bussiness.getFileUrl())) {

				try {
					fileInputStream = new FileInputStream(new File(path,
							bussiness.getFileUrl()));
					return "downLoadFile";
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				this.getSession().setAttribute("fileNull", "资料文件不存在");
				return "fileNull";
			}

		} else {
			this.getSession().setAttribute("fileNull", "资料文件不存在");
			return "fileNull";
		}
		return "downLoadFile";
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
