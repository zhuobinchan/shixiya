package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.service.PreferenceSelectEnteredService;
import com.ehighsun.shixiya.service.PreferenceSelectService;
import com.ehighsun.shixiya.service.SendEmailService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminPreferenceSelectAction extends BaseAction<PreferenceSelect> {

	private String page;
	private String ids;
	private String pageCode;
	private List<PreferenceSelect> list;
	private String wkName;

	private File workLogos;
	private String workLogosContentType;
	private String workLogosFileName;

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

	public List<PreferenceSelect> getList() {
		return list;
	}

	public void setList(List<PreferenceSelect> list) {
		this.list = list;
	}

	public String getWkName() {
		return wkName;
	}

	public void setWkName(String wkName) {
		this.wkName = wkName;
	}

	public File getWorkLogos() {
		return workLogos;
	}

	public void setWorkLogos(File workLogos) {
		this.workLogos = workLogos;
	}

	public String getWorkLogosContentType() {
		return workLogosContentType;
	}

	public void setWorkLogosContentType(String workLogosContentType) {
		this.workLogosContentType = workLogosContentType;
	}

	public String getWorkLogosFileName() {
		return workLogosFileName;
	}

	public void setWorkLogosFileName(String workLogosFileName) {
		this.workLogosFileName = workLogosFileName;
	}

	@Resource(name = "preferenceSelectServiceImpl")
	private PreferenceSelectService preferenceSelectService;
	@Resource(name = "preferenceSelectEnteredServiceImpl")
	private PreferenceSelectEnteredService preferenceSelectEnteredService;
	@Resource(name = "sendEmailServiceImpl")
	private SendEmailService sendEmailService;

	public String showPrefereneceSelect() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "PreferenceSelect.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		list = preferenceSelectService.findPreferenceSelects(
				"from PreferenceSelect", null, pageBean);
		Long count = preferenceSelectService.countPreferenceSelect(
				"select count(*) from PreferenceSelect", null);
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/pSelect_showPrefereneceSelect.action", count,
				Integer.parseInt(page), 6, null);

		return "showPrefereneceSelect";
	}

	public String deletePreferenceSelect() {

		preferenceSelectService.deletePreferenceSelect(model.getId());

		return "deletePreferenceSelect";
	}

	public String deletePreferenceSelects() {

		System.out.println(ids + "//////");
		String[] strs = ids.split(",");
		preferenceSelectService.deletePreferenceSelects(strs);
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

	public String addPreferenceSelect() {

		HttpServletRequest request = ServletActionContext.getRequest();

		if (workLogos != null) {
			String houzhui = workLogosFileName.substring(workLogosFileName
					.indexOf('.'));
			String randomDir = this.generateRandomDir(UUID.randomUUID()
					.toString());
			String path = request.getSession().getServletContext()
					.getRealPath("/images/" + randomDir);
			String saveName = UUID.randomUUID().toString();
			String savePath = "images/" + randomDir + "/" + saveName + houzhui;
			File file = new File(path, saveName + houzhui);
			try {
				path = file.getCanonicalPath();
				FileUtils.copyFile(workLogos, file);
			} catch (IOException e) {

				e.printStackTrace();
			}
			model.setWorkLogo(savePath);

			// 更新时删除文件
			if (model.getId() != null && !"".equals(model.getId())) {
				PreferenceSelect ps = preferenceSelectService
						.getPreferenceSelectById(model.getId());
				if (ps != null && ps.getWorkLogo() != null) {
					String oldPath = request.getSession().getServletContext()
							.getRealPath(ps.getWorkLogo());
					File f = new File(oldPath);
					f.delete();
				}
				preferenceSelectService.evitPreferenceSelect(ps);
			}
		} else {
			if (model.getId() != null && !"".equals(model.getId())) {
				PreferenceSelect ps = preferenceSelectService
						.getPreferenceSelectById(model.getId());
				if (ps != null && ps.getWorkLogo() != null) {
					model.setWorkLogo(ps.getWorkLogo());
				}
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		model.setPublishtime(time);

		preferenceSelectService.addPreferenceSelect(model);
		return "addPreferenceSelect";
	}

	public String findPrefereneceSelectByCondition() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "PreferenceSelect.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		List<Object> condition = new ArrayList<Object>();
		sb.append("from PreferenceSelect");

		if (wkName != null && !"".equals(wkName)) {
			if (sb.toString().equals("from PreferenceSelect")) {
				sb.append(" where workName=?");
				sb1.append("wkName=" + wkName);
				condition.add(wkName);
			} else {
				sb.append(" and workName=?");
				sb1.append("&wkName=" + wkName);
				condition.add(wkName);
			}
		}

		list = preferenceSelectService.findPreferenceSelects(sb.toString(),
				condition, pageBean);
		Long count = preferenceSelectService.countPreferenceSelect(
				"select count(*) " + sb.toString(), condition);
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/pSelect_findPrefereneceSelectByCondition.action"
				+ sb1.toString(), count, Integer.parseInt(page), 6, null);

		return "findCondition";
	}

	public String sendEmail() {

		PreferenceSelect p = preferenceSelectService
				.getPreferenceSelectById(model.getId());
		if (p != null) {
			List<PreferenceSelectEntered> list = preferenceSelectEnteredService
					.findByPreferenceSelectId(p.getId());
			StringBuilder sb = new StringBuilder();
			sb.append("<html><head></head><body><h1>html测试</h1></br><table border=\"1\"  bordercolor=\"#00CCCC\"  width=\"300\">");
			for (PreferenceSelectEntered pse : list) {
				sb.append("<tr>" + "<th>" + pse.getName() + "</th>" + "<th>"
						+ pse.getEmail() + "</th>" + "<th>"
						+ pse.getTelephone() + "</th>" + "</tr>");

			}
			sb.append("</table>");
			sb.append("<a href=\"https://www.baidu.com/\">连接</a>");
			sb.append("</body></html>");

			sendEmailService.sendEmailByHtml("jkb549121@sina.com",
					"ml_21233qq@163.com", "海印集团人力资源部", sb.toString());

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
