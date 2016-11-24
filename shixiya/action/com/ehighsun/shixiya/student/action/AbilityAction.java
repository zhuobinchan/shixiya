package com.ehighsun.shixiya.student.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.TrainWEAdvService;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.util.ResponseUtil;

public class AbilityAction {
	private String lableNames;

	private List<CProductBroadcast> broadcasts;
	private List<BProductCourse> courses;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "bProductCourseService")
	private BProductCourseService bpCourseService;
	@Resource(name="trainWEService")
	private TrainWEService trainWEService;
	@Resource(name = "baseDao")
	private BaseDao<Lable> lableDao;

	// 通过标签 、获取个产品的信息
	public String getProductFromLableName() {
		System.out.println(lableNames);

		return "getProductFromLableName";
	}

	// 通过标签 、获取B产品的信息
	public String getbpCourseByAjax() {
		System.out.println(lableNames);
		courses = bpCourseService.getBProductCoursesByLableName(lableNames);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ResponseUtil.write(response, JSON.toJSONString(courses));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 通过标签 、获取C产品的信息
	public String getCpBroadcastByAjax() {
		System.out.println(lableNames);
		broadcasts = cpBroadcastService.getBroadcastByLableNames(lableNames);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ResponseUtil.write(response, JSON.toJSONString(broadcasts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	// 通过标签 、获取培训we课堂的信息
//		public String getTrainWEByAjax() {
//			System.out.println(lableNames);
//			List<TrainWEProduct> trainWEProducts = trainWEService.get
//			HttpServletResponse response = ServletActionContext.getResponse();
//			try {
//				ResponseUtil.write(response, JSON.toJSONString(trainWEProducts));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}

	// 获取所有标签信息
	public String getLableAjax() {
		List<Lable> lables = lableDao
				.find("select new Lable(lableName) from Lable where type = 0");
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ResponseUtil.write(response, JSON.toJSONString(lables));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getLableNames() {
		return lableNames;
	}

	public void setLableNames(String lableNames) {
		this.lableNames = lableNames;
	}

	public List<CProductBroadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(List<CProductBroadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

	public List<BProductCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<BProductCourse> courses) {
		this.courses = courses;
	}

}
