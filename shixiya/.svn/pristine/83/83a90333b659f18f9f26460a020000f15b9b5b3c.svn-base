package com.ehighsun.shixiya.administer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.service.PreferenceSelectEnteredService;
import com.ehighsun.shixiya.service.PreferenceSelectService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminPreferenceSelectEnteredAction extends
		BaseAction<PreferenceSelectEntered> {

	private String page;
	private String ids;
	private String pageCode;
	private String sname;
	private String stelephone;
	private String semail;
	private String sid;
	private String enterid;
	private List<PreferenceSelectEntered> list;
	private List<PreferenceSelect> list1;

	public String getEnterid() {
		return enterid;
	}

	public void setEnterid(String enterid) {
		this.enterid = enterid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getStelephone() {
		return stelephone;
	}

	public void setStelephone(String stelephone) {
		this.stelephone = stelephone;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public List<PreferenceSelectEntered> getList() {
		return list;
	}

	public void setList(List<PreferenceSelectEntered> list) {
		this.list = list;
	}

	public List<PreferenceSelect> getList1() {
		return list1;
	}

	public void setList1(List<PreferenceSelect> list1) {
		this.list1 = list1;
	}

	@Resource(name = "preferenceSelectEnteredServiceImpl")
	private PreferenceSelectEnteredService preferenceSelectEnteredService;
	@Resource(name = "preferenceSelectServiceImpl")
	private PreferenceSelectService preferenceSelectService;

	public String showpreferenceSelectEntered() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "PreferenceSelectEntered.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		list = preferenceSelectEnteredService.findPreferenceSelectEntereds(
				"from PreferenceSelectEntered", null, pageBean);
		Long count = preferenceSelectEnteredService
				.countPreferenceSelectEntered("select count(*) from PreferenceSelect");
		list1 = preferenceSelectService.findAllPreferenceSelect();
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/psEntered_showpreferenceSelectEntered.action", count,
				Integer.parseInt(page), 6, null);

		return "showpreferenceSelectEntered";
	}

	public String deletePreferenceSelectEntered() {

		preferenceSelectEnteredService.deletePreferenceSelectEntered(Integer
				.parseInt(enterid));

		return "deletePSEntered";
	}

	public String deletePreferenceSelectEntereds() {
		preferenceSelectEnteredService.deletePreferenceSelectEntereds(ids);
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
		request.setAttribute("mainPage", "PreferenceSelectEntered.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Map<String, Object> o = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();

		if (sname != null && !"".equals(sname)) {
			o.put("name", sname);
			sb.append("sname=" + sname);
		}

		if (stelephone != null && !"".equals(stelephone)) {
			o.put("telephone", stelephone);
			if (sb.toString() != null) {
				sb.append("&stelephone=" + stelephone);
			} else {
				sb.append("stelephone=" + stelephone);
			}
		}

		if (semail != null && !"".equals(semail)) {
			o.put("email", semail);
			if (sb.toString() != null) {
				sb.append("&semail=" + semail);
			} else {
				sb.append("semail=" + semail);
			}
		}

		if (sid != null && !"".equals(sid)) {
			PreferenceSelect ps = new PreferenceSelect();
			ps.setId(Integer.parseInt(sid));
			o.put("preferenceSelect", ps);
			if (sb.toString() != null) {
				sb.append("&sid=" + sid);
			} else {
				sb.append("sid=" + sid);
			}
		}

		list = preferenceSelectEnteredService.findByCondition(
				"from PreferenceSelectEntered", o, pageBean);
		Long count = preferenceSelectEnteredService.countByCondition(
				"select count(*) from PreferenceSelectEntered", o);

		list1 = preferenceSelectService.findAllPreferenceSelect();

		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/psEntered_findByCondition.action?" + sb.toString(),
				count, Integer.parseInt(page), 6, null);

		return "findByCondition";
	}

}
