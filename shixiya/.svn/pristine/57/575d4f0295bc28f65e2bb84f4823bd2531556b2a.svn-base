package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTrainWEAction extends ActionSupport {
	private Integer trainWEId;
	private List<TrainWEProduct> trainWEProducts;
	private List<TrainWETeacher> teachers;
	private List<Lable> lables;
	private TrainWEProduct trainWEProduct;
	private String ids;

	private String mainPage;

	@Resource(name = "trainWEService")
	private TrainWEService trainWEService;

	@Resource(name = "trainWETeacherService")
	private TrainWETeacherService teacherService;

	@Resource(name = "lableService")
	private LableService lableService;

	// 上传视频
	private File video;
	private String videoContentType;
	private String videoFileName;
	// 上传图片
	private File img;
	private String imgContentType;
	private String imgFileName;

	// 分页
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String page;

	// 文件上传
	private FileUploadUtil fileUploadUtil = new FileUploadUtil();

	// 搜索条件
	private String title;
	private Integer teacherId;

	// 显示
	public String getAllTrainWEProduct() {
		mainPage = "TrainWEProduct.jsp";

		trainWEProducts = trainWEService.getAllTrainWEProducts(request, page);
		teachers = teacherService.getAllTrainWETeachers();
		lables = lableService.findLable();

		return "getAllTrainWEProduct";
	}

	// 添加培训we课堂
	public String addTrainWEProduct() {
		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			trainWEProduct.setImgUrl(imgUrl);
		}

		trainWEService.addTrainWEProduct(trainWEProduct);
		return "addTrainWEProduct";
	}

	// 保存
	public String updateTrainWEProduct() {
		TrainWEProduct b = trainWEService
				.getTrainWeById(trainWEProduct.getId());
		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			FileUploadUtil.deleteFile(b.getImgUrl());
			trainWEProduct.setImgUrl(imgUrl);
		} else {
			if (b != null && b.getImgUrl() != null) {
				trainWEProduct.setImgUrl(b.getImgUrl());
			}
		}

		trainWEService.updateTrainWEProduct(trainWEProduct);
		return "updateTrainWEProduct";
	}

	// 删除一条
	public String deleteTrainWEProduct() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		trainWEService.deleteTrainWEProductById(trainWEId);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 删除多条
	public String deleteTrainWEs() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		trainWEService.deleteTrainWEProducts(ids);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 通过信息查询
	public String findByCondition() {
		mainPage = "TrainWEProduct.jsp";
		teachers = teacherService.getAllTrainWETeachers();
		lables = lableService.findLable();
		trainWEProducts = trainWEService.findTrainWEProductsByCondition(title,
				teacherId, request, page);
		return "findByCondition";
	}

	public Integer getTrainWEId() {
		return trainWEId;
	}

	public void setTrainWEId(Integer trainWEId) {
		this.trainWEId = trainWEId;
	}

	public List<TrainWEProduct> getTrainWEProducts() {
		return trainWEProducts;
	}

	public void setTrainWEProducts(List<TrainWEProduct> trainWEProducts) {
		this.trainWEProducts = trainWEProducts;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<TrainWETeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TrainWETeacher> teachers) {
		this.teachers = teachers;
	}

	public List<Lable> getLables() {
		return lables;
	}

	public void setLables(List<Lable> lables) {
		this.lables = lables;
	}

	public File getVideo() {
		return video;
	}

	public void setVideo(File video) {
		this.video = video;
	}

	public String getVideoContentType() {
		return videoContentType;
	}

	public TrainWEProduct getTrainWEProduct() {
		return trainWEProduct;
	}

	public void setTrainWEProduct(TrainWEProduct trainWEProduct) {
		this.trainWEProduct = trainWEProduct;
	}

	public void setVideoContentType(String videoContentType) {
		this.videoContentType = videoContentType;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
