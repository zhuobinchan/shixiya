package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWEProductChild;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTrainWEChildAction extends ActionSupport {
	private Integer trainWEChildId;
	private List<TrainWEProductChild> trainWEProductChilds;
	private List<TrainWEProduct> trainWEProducts;
	private TrainWEProductChild trainWEProductChild;
	private String ids;

	private String mainPage;

	@Resource(name = "baseDao")
	private BaseDao<TrainWEProductChild> weChildDao;

	@Resource(name = "baseDao")
	private BaseDao<TrainWEProduct> weDao;

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
	private Integer trainWeId;

	// 显示
	public String getAllTrainWEProductChild() {
		mainPage = "TrainWEProductChild.jsp";

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = weChildDao.count("select count(*) from TrainWEProductChild");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllTrainWEProductChild_AdminTrainWEChildAction.action",
				count, Integer.parseInt(page), 6, null);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);
		request.getSession().setAttribute("page", page);
		
		trainWEProductChilds = weChildDao.find("from TrainWEProductChild", new Object[] {}, pageBean);
		trainWEProducts = weDao.find("from TrainWEProduct");
		
		return "getAllTrainWEProductChild";
	}

	// 添加培训we课堂
	public String addTrainWEProductChild() {
		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			trainWEProductChild.setImgUrl(imgUrl);
		}

		if (video != null && video.exists()) {
			
			String videoUrl = fileUploadUtil.updateSmbFile(video, videoFileName,"media");
			
//			String videoUrl = fileUploadUtil.updateFile(video, videoFileName,
//					"media");
			
			trainWEProductChild.setVideoUrl(videoUrl);
		}
		
		trainWEProductChild.setParticipation(0);
		trainWEProductChild.setVisitnum(0);
		
		if (trainWEProductChild.getMode()==0||trainWEProductChild.getMode()==2) {
			trainWEProductChild.setChatStatus(1);
		}
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(new Date().getTime());
		trainWEProductChild.setPublicTime(time);
		  
		weChildDao.save(trainWEProductChild);
		return "addTrainWEProductChild";
	}

	// 保存
	public String updateTrainWEProductChild() {
		TrainWEProductChild b = weChildDao.get(TrainWEProductChild.class, trainWEProductChild.getId());
		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			FileUploadUtil.deleteFile(b.getImgUrl());
			trainWEProductChild.setImgUrl(imgUrl);
		} else {
			if (b != null && b.getImgUrl() != null) {
				trainWEProductChild.setImgUrl(b.getImgUrl());
			}
		}

		if (video != null && video.exists()) {
			
			String videoUrl = fileUploadUtil.updateSmbFile(video, videoFileName,"media");

			String resUrl = trainWEProductChild.getVideoUrl().replace("http://sxyres.000861.com/", "");
			FileUploadUtil.DelRemoteResource(resUrl);
			
			trainWEProductChild.setVideoUrl(videoUrl);
		} else {
			if (b != null && b.getVideoUrl() != null) {
				trainWEProductChild.setVideoUrl(b.getVideoUrl());
			}
		}
		weChildDao.evict(b);
		if (trainWEProductChild.getMode()==0||trainWEProductChild.getMode()==2) {
			trainWEProductChild.setChatStatus(1);
		}
		weChildDao.update(trainWEProductChild);
		return "updateTrainWEProductChild";
	}

	// 删除一条
	public String deleteTrainWEProductChild() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		
		TrainWEProductChild index = weChildDao.get(TrainWEProductChild.class, trainWEChildId);
		if (StringUtil.isNotEmpty(index.getImgUrl())) {
			FileUploadUtil.deleteFile(index.getImgUrl());
		}
		if (StringUtil.isNotEmpty(index.getVideoUrl())) {
			String resUrl = index.getVideoUrl().replace("http://sxyres.000861.com/", "");
			FileUploadUtil.DelRemoteResource(resUrl);
		}		
		
		weChildDao.delete(index);
		
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 删除多条
	public String deleteTrainWEChilds() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		
		String[] idList = ids.split(",");
		for (String string : idList) {
			TrainWEProductChild index = weChildDao.get(TrainWEProductChild.class,
					Integer.parseInt(string));
			if (StringUtil.isNotEmpty(index.getImgUrl())) {
				FileUploadUtil.deleteFile(index.getImgUrl());
			}
			if (StringUtil.isNotEmpty(index.getVideoUrl())) {
				String resUrl = index.getVideoUrl().replace("http://sxyres.000861.com/", "");
				FileUploadUtil.DelRemoteResource(resUrl);
			}	
			
			weChildDao.delete(index);
		}
		
		result.put("success", true);
		try {
			ResponseUtil.write(response, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	// 通过信息查询
	public String findByConditionChild() {
		mainPage = "TrainWEProductChild.jsp";
		
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		System.out.println("title:"+title+",id:"+trainWeId);
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = weChildDao.countByMap(
				"select count(*) from TrainWEProductChild",
				getConditionByMap(title, trainWeId));
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/findByConditionChild_AdminTrainWEChildAction.action", count,
				Integer.parseInt(page), 6, "title=" + title + "&id="
						+ (trainWeId == null ? "" : trainWeId));
		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		trainWEProductChilds = weChildDao.findPageByMap("from TrainWEProductChild",
				getConditionByMap(title, trainWeId), pageBean);
		
		trainWEProducts = weDao.find("from TrainWEProduct");
		
		return "findByConditionChild";
	}

	
	private Map<String, Object> getConditionByMap(String title,
			Integer trainWeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(title)) {
			map.put("title", title);
		}
		if (StringUtil.isNotEmpty(trainWeId)) {
			map.put("trainWEProduct",
					weDao.get(TrainWEProduct.class,trainWeId));
		}
		return map;
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

	public File getVideo() {
		return video;
	}

	public void setVideo(File video) {
		this.video = video;
	}

	public String getVideoContentType() {
		return videoContentType;
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

	public Integer getTrainWEChildId() {
		return trainWEChildId;
	}

	public List<TrainWEProductChild> getTrainWEProductChilds() {
		return trainWEProductChilds;
	}

	public TrainWEProductChild getTrainWEProductChild() {
		return trainWEProductChild;
	}

	public void setTrainWEChildId(Integer trainWEChildId) {
		this.trainWEChildId = trainWEChildId;
	}

	public void setTrainWEProductChilds(
			List<TrainWEProductChild> trainWEProductChilds) {
		this.trainWEProductChilds = trainWEProductChilds;
	}

	public Integer getTrainWeId() {
		return trainWeId;
	}

	public void setTrainWeId(Integer trainWeId) {
		this.trainWeId = trainWeId;
	}

	public void setTrainWEProductChild(TrainWEProductChild trainWEProductChild) {
		this.trainWEProductChild = trainWEProductChild;
	}
}
