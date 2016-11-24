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
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.service.RIBussinessService;
import com.ehighsun.shixiya.service.RecruitmentInfoService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class RecruitmentInfoAction extends BaseAction<RecruitmentInfo> {

	private String page;
	private String ids;
	private String pageCode;
	public List<RecruitmentInfo> list;
	public List<RIBussiness> riList;
	public String infoName;
	public String infoType;
	public String businessId;

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public List<RIBussiness> getRiList() {
		return riList;
	}

	public void setRiList(List<RIBussiness> riList) {
		this.riList = riList;
	}

	public List<RecruitmentInfo> getList() {
		return list;
	}

	public void setList(List<RecruitmentInfo> list) {
		this.list = list;
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

	@Resource(name = "recruitmentInfoServiceImpl")
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name = "riBussinessServiceImpl")
	private RIBussinessService riBussinessService;

	public String showRecruitmentInfo() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RecruitmentInfo.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		list = recruitmentInfoService.findRecruitmentInfos(
				"from RecruitmentInfo", null, pageBean);
		Long count = recruitmentInfoService
				.countRecruitmentInfo("select count(*) from RecruitmentInfo");
		riList = riBussinessService.findRiBussinesses();
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/reInfo_showRecruitmentInfo.action", count,
				Integer.parseInt(page), 6, null);

		return "showRecruitmentInfo";
	}

	public String deleteRecruitmentInfo() {

		recruitmentInfoService.deleteRecruitmentInfo(model.getId());
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

	public String deleteRecruitmentInfos() {

		recruitmentInfoService.deleteRecruitmentInfos(ids.split(","));
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

	public String updateRecruitmentInfo() {

		recruitmentInfoService.updateRecruitmentInfo(model);

		return "updateRecruitmentInfo";
	}

	public String changeCheck() {

		RecruitmentInfo info = recruitmentInfoService
				.getRecruitmentInfoById(model.getId());
		if (info != null) {

			if (info.getCheckState() == 0) {
				info.setCheckState(1);
			} else {
				info.setCheckState(0);
			}
			recruitmentInfoService.updateRecruitmentInfo(info);
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

	public String changeTop() {

		RecruitmentInfo info = recruitmentInfoService
				.getRecruitmentInfoById(model.getId());
		if (info != null) {

			if (info.getTopState() == 0) {
				info.setTopState(1);
			} else {
				info.setTopState(0);
			}
			recruitmentInfoService.updateRecruitmentInfo(info);
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

	public String findByCondition() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RecruitmentInfo.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Map<String, Object> o = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();

		if (infoName != null && !"".equals(infoName)) {
			o.put("name", infoName);
			sb.append("infoName=" + infoName);
		}

		if (infoType != null && !"".equals(infoType)) {
			o.put("workType", infoType);
			if (sb.toString() != null) {
				sb.append("&infoType=" + infoType);
			} else {
				sb.append("infoType=" + infoType);
			}
		}

		if (businessId != null && !"".equals(businessId)) {
			RIBussiness riBussiness = new RIBussiness();
			riBussiness.setId(Integer.parseInt(businessId));
			o.put("RIBussiness", riBussiness);
			if (sb.toString() != null) {
				sb.append("&businessId=" + businessId);
			} else {
				sb.append("businessId=" + businessId);
			}
		}

		list = recruitmentInfoService.findByCondition("from RecruitmentInfo",
				o, pageBean);
		Long count = recruitmentInfoService.countByCondition(
				"select count(*) from RecruitmentInfo", o);

		riList = riBussinessService.findRiBussinesses();

		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/reInfo_findByCondition.action?" + sb.toString(),
				count, Integer.parseInt(page), 6, null);

		return "findByCondition";
	}

}
