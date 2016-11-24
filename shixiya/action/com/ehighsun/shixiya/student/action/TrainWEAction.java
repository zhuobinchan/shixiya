package com.ehighsun.shixiya.student.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEAdv;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWEProductChild;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.TrainWEAdvService;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class TrainWEAction extends ActionSupport {
	private List<TrainWEProduct> trainWEProducts;
	private Integer trainWeId;
	private Integer trainWeChildId;
	private TrainWEProduct trainWEProduct;
	private TrainWEProductChild trainWEProductChild;
	private List<TrainWEProductChild> trainWEProductChilds;
	private String timeQuantum;
	private String lableName;

	@Resource(name = "trainWEService")
	private TrainWEService trainWEService;
	@Resource(name = "trainWEAdvService")
	private TrainWEAdvService trainWEAdvService;
	@Resource(name = "baseDao")
	private BaseDao<TrainWEProductChild> trainWeChildDao;
	@Resource(name = "lableService")
	private LableService lableService;
	
	private Integer AjaxPage;
	
	
	public String getAllTrainWEAjax() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PageBean pageBean = new PageBean(AjaxPage, 5);
			trainWEProducts = trainWEService.getAllTrainWEProducts(pageBean);

			ResponseUtil.write(response, JSON.toJSONString(trainWEProducts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTrainWEAdvAjax() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			List<TrainWEAdv> trainWEAdvs = trainWEAdvService.getAllAdvs();

			ResponseUtil.write(response, JSON.toJSONString(trainWEAdvs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*显示该课堂的章节*/
	public void getAllTrainWEChildAjax(){ 

		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			trainWEProductChilds = trainWeChildDao.find("From TrainWEProductChild where trainWEProduct.id = ?",new Object[] { trainWeId });
			ResponseUtil.write(response, JSON.toJSONString(trainWEProductChilds));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public String getTrainWEChildById() {
		trainWEProductChild = trainWeChildDao.get(TrainWEProductChild.class,trainWeChildId);
		trainWEProductChild.setVisitnum(trainWEProductChild.getVisitnum()+1);
		return "getTrainWEChildById";
	}
	
	
//	获取标签信息
	public void getLableAjax() {
		List<Lable> labels = lableService.findLablesByType(1);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			ResponseUtil.write(response, JSON.toJSONString(labels));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//时间段获取培训we课堂
	public String timeQuantumFilter() {

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PageBean pageBean = new PageBean(AjaxPage, 5);
			List<TrainWEProduct> trainWEProducts = trainWEService
					.getTrainWEByTimeQuantum(timeQuantum,pageBean);

			ResponseUtil.write(response, JSON.toJSONString(trainWEProducts));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//根据标签获取培训we课堂
	public String lableFilter() {

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PageBean pageBean = new PageBean(AjaxPage, 5);
			List<TrainWEProduct> trainWEProducts = trainWEService.getTrainWEByLable(lableName,pageBean);

			ResponseUtil.write(response, JSON.toJSONString(trainWEProducts));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	

	public List<TrainWEProduct> getTrainWEProducts() {
		return trainWEProducts;
	}

	public void setTrainWEProducts(List<TrainWEProduct> trainWEProducts) {
		this.trainWEProducts = trainWEProducts;
	}

	public Integer getTrainWeId() {
		return trainWeId;
	}

	public void setTrainWeId(Integer trainWeId) {
		this.trainWeId = trainWeId;
	}

	public TrainWEProduct getTrainWEProduct() {
		return trainWEProduct;
	}

	public void setTrainWEProduct(TrainWEProduct trainWEProduct) {
		this.trainWEProduct = trainWEProduct;
	}

	public Integer getTrainWeChildId() {
		return trainWeChildId;
	}

	public void setTrainWeChildId(Integer trainWeChildId) {
		this.trainWeChildId = trainWeChildId;
	}

	public TrainWEProductChild getTrainWEProductChild() {
		return trainWEProductChild;
	}

	public void setTrainWEProductChild(TrainWEProductChild trainWEProductChild) {
		this.trainWEProductChild = trainWEProductChild;
	}

	public List<TrainWEProductChild> getTrainWEProductChilds() {
		return trainWEProductChilds;
	}

	public void setTrainWEProductChilds(
			List<TrainWEProductChild> trainWEProductChilds) {
		this.trainWEProductChilds = trainWEProductChilds;
	}

	public String getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public Integer getAjaxPage() {
		return AjaxPage;
	}

	public void setAjaxPage(Integer ajaxPage) {
		AjaxPage = ajaxPage;
	}
	
}
