package com.ehighsun.shixiya.hr.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductEmploy;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.CProductEmployService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CPHrEmployStudent extends ActionSupport {
	private List<Integer> choose;// 存放学生ID
	private Integer broadcastId;
	private String employIds;
	private List<CProductEmploy> employs;
	private CProductBroadcast broadcast;
	@Resource(name = "cProductEmployService")
	private CProductEmployService cpEmployService;
	@Resource(name = "adminStudentService")
	private AdminStudentService studentService;
	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;

	public List<Integer> getChoose() {
		return choose;
	}

	public void setChoose(List<Integer> choose) {
		this.choose = choose;
	}

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public List<CProductEmploy> getEmploys() {
		return employs;
	}

	public void setEmploys(List<CProductEmploy> employs) {
		this.employs = employs;
	}

	public CProductBroadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(CProductBroadcast broadcast) {
		this.broadcast = broadcast;
	}

	public String getEmployIds() {
		return employIds;
	}

	public void setEmployIds(String employIds) {
		this.employIds = employIds;
	}

	public String saveOrUpdateEmploy() {
		try {
			broadcast = cpBroadcastService.findById(broadcastId);

			if (choose!=null&&choose.size() != 0) {
				for (Integer index : choose) {

					Student student = studentService.findStudentById(index);
					CProductEmploy employ = new CProductEmploy(broadcast,
							student.getHeadUrl(), student.getName(),
							student.getTelephone(), student.getEmail());

					cpEmployService.saveOrUpdateEmploy(employ);
				}
			}
			employs = cpEmployService.getCProductEmploysByBroadcast(broadcast);
			return "saveOrUpdateEmploy";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String deleteEmploys(){
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		json.put("message", "true");
		if (StringUtil.isNotEmpty(employIds)) {
			System.out.println(employIds);
			cpEmployService.deleteEmploys(employIds);
		}
		ResponseUtil.write(response, json.toString());
		return null;
	}
}
