package com.ehighsun.shixiya.hr.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.CProductHRService;
import com.ehighsun.shixiya.service.HrAdminServer;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CPHrAction extends ActionSupport {
	private CProductHR cpHr;

	Map<String, Object> session = ActionContext.getContext().getSession();

	@Resource(name = "cProductHRService")
	private CProductHRService cProductHRService;
	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "HrAdminServer")
	private HrAdminServer hrCommentServer;

	public CProductHR getCpHr() {
		return cpHr;
	}

	public void setCpHr(CProductHR cpHr) {
		this.cpHr = cpHr;
	}

	public String getBroadcastsByHr() {
		try {
			cpHr = (CProductHR) session.get("hr");
			cpHr = cProductHRService.findHRById(cpHr.getHrId());
			System.out.println(cpHr.getCProductBroadcasts().size());
			return "getBroadcastsByHr";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String getBroadcastByAjax() {

		try {
			cpHr = (CProductHR) session.get("hr");
			List<CProductBroadcast> cpBroadcasts = cpBroadcastService
					.getCPBroadcastByHrId(cpHr.getHrId());
			HttpServletResponse response = ServletActionContext.getResponse();

//			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
//					CProductBroadcast.class, "id", "name", "publisher",
//					"publicTime", "startTime", "endTime", "introduction",
//					"status", "videoUrl", "imgUrl");

			String result = JSON.toJSONString(cpBroadcasts);

			try {
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String getHrListByAjax() {
		try {
			cpHr = (CProductHR) session.get("hr");
			List<HrCommentList> cpBroadcasts = hrCommentServer
					.getHrCommentListsByHrId(cpHr.getHrId());
			HttpServletResponse response = ServletActionContext.getResponse();

			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
					HrCommentList.class, "id", "title", "introduction",
					"publicTime", "startTime", "state", "videoUrl", "imageUrl");

			String result = JSON.toJSONString(cpBroadcasts, filter);

			try {
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
}
