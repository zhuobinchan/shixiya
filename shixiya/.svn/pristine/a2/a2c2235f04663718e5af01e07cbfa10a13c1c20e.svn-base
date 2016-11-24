package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTrainWETeacherAction extends ActionSupport {
	private File image;
	private String imageContentType;
	private String imageFileName;
	private FileUploadUtil fileUploadUtil = new FileUploadUtil();

	private String page;
	private String ids;
	private Integer id;

	private String mainPage;

	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource(name = "trainWETeacherService")
	private TrainWETeacherService trainWETeacherService;

	private List<TrainWETeacher> trainWETeachers;

	private TrainWETeacher trainWETeacher;

	// 查询条件
	private String name;
	private String telephone;
	private String email;

	public String getAllTrainWETeacher() {
		mainPage = "addTrainWETeacher.jsp";
		trainWETeachers = trainWETeacherService.getAllTrainWETeachers(request,
				page);
		return "getAllTrainWETeacher";
	}

	// 添加
	public String add() {
		if (image != null && image.exists()) {

			String imgUrl = fileUploadUtil.updateFile(image, imageFileName,
					"images");
			trainWETeacher.setHeadImgUrl(imgUrl);
		}

		trainWETeacherService.addTrainWETeacher(trainWETeacher);
		return "toShowAllAction";
	}

	// 更新
	public String update() {
		TrainWETeacher b = trainWETeacherService
				.getTrainWETeacherById(trainWETeacher.getId());

		if (image != null && image.exists()) {

			String imgUrl = fileUploadUtil.updateFile(image, imageFileName,
					"images");
			FileUploadUtil.deleteFile(b.getHeadImgUrl());
			trainWETeacher.setHeadImgUrl(imgUrl);
		} else {
			if (b != null && b.getHeadImgUrl() != null) {
				trainWETeacher.setHeadImgUrl(b.getHeadImgUrl());
			}
		}
		String password = trainWETeacher.getPassword();
		if (StringUtil.isNotEmpty(password)
				&& !b.getPassword().equals(StringUtil.md5(password))) {
			trainWETeacher.setPassword(StringUtil.md5(password));
		} else {
			trainWETeacher.setPassword(b.getPassword());
		}
		trainWETeacherService.evitTrainWETeacher(b);
		trainWETeacherService.updateTrainWETeacher(trainWETeacher);
		return "toShowAllAction";
	}

	// 查询
	public String findByCondition() {
		mainPage = "addTrainWETeacher.jsp";
		trainWETeachers = trainWETeacherService.getTrainWETeacherByCondition(
				name, telephone, email, request, page);
		return "findByCondition";
	}

	public String delete() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		trainWETeacherService.deleteTrainWETeacherById(id);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	public String deleteList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		trainWETeacherService.deleteTrainWETeacherByIds(ids);
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

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

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<TrainWETeacher> getTrainWETeachers() {
		return trainWETeachers;
	}

	public void setTrainWETeachers(List<TrainWETeacher> trainWETeachers) {
		this.trainWETeachers = trainWETeachers;
	}

	public TrainWETeacher getTrainWETeacher() {
		return trainWETeacher;
	}

	public void setTrainWETeacher(TrainWETeacher trainWETeacher) {
		this.trainWETeacher = trainWETeacher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
