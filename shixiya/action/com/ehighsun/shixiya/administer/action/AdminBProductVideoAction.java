package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.service.BProductChapterService;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.BProductVideoService;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductVideoAction extends BaseAction<BProductVideo> {

	private File video;
	private String videoContentType;
	private String videoFileName;

	private String page;
	private String ids;
	private Integer chapterid;
	private Integer courseId;
	
	private String trainweChildSelect;
	
	
	@Autowired
	private BProductVideoService bProductVideoService;
	@Autowired
	private BProductChapterService bProductChapterService;
	@Resource(name = "bProductCourseService")
	private BProductCourseService bpCourseService;
	@Autowired
	private TrainWEService trainWEServiceImpl;

	public Integer getChapterid() {
		return chapterid;
	}

	public void setChapterid(Integer chapterid) {
		this.chapterid = chapterid;
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

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String showVideo() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addVideo.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductVideo> list = bProductVideoService.findBVideo(
				"from BProductVideo", null, pageBean);
		Long count = bProductVideoService
				.countVideos("select count(*) from BProductVideo");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminVideo_showVideo.action", count,
				Integer.parseInt(page), 6, null);

		List<BProductCourse> courseList = bpCourseService.findBCourse();
		List<TrainWEProduct> trainweList = trainWEServiceImpl.getAllTrainWEProducts();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("video", list);
		this.getSession().setAttribute("courseList", courseList);
		this.getSession().setAttribute("trainweList", trainweList);

		return "showVideo";
	}

	public String addVideo() {

		HttpServletRequest request = ServletActionContext.getRequest();
		if (videoFileName != null) {
			
			FileUploadUtil fileUtil = new FileUploadUtil();
			
			String savePath = fileUtil.updateSmbFile(video, videoFileName, "media");
			
//			String houzhui = videoFileName
//					.substring(videoFileName.indexOf('.'));
//			String randomDir = this.generateRandomDir(UUID.randomUUID()
//					.toString());
//			String path = request.getSession().getServletContext()
//					.getRealPath("/media/" + randomDir);
//			String saveName = UUID.randomUUID().toString();
//			String savePath = "media/" + randomDir + "/" + saveName + houzhui;
//			File file = new File(path, saveName + houzhui);
//			try {
//				path = file.getCanonicalPath();
//				FileUtils.copyFile(video, file);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			BProductVideo video = bProductVideoService.findVideoById(model
					.getId());
			if (video != null) {
				if (video.getVideoUrl() != null) {
					
					String resUrl = video.getVideoUrl().replace("http://sxyres.000861.com/", "");
					FileUploadUtil.DelRemoteResource(resUrl);
//					String p = request.getSession().getServletContext()
//							.getRealPath("/" + video.getVideoUrl());
//					File f = new File(p);
//					f.delete();
				}
			}

			model.setVideoUrl(savePath);
		} else {

			BProductVideo video = bProductVideoService.findVideoById(model
					.getId());
			if (video != null && video.getVideoUrl() != null) {
				model.setVideoUrl(video.getVideoUrl());
			}
		}
		
		if(StringUtil.isNotEmpty(trainweChildSelect)) {
			BProductVideo video = bProductVideoService.findVideoById(model
					.getId());
			if (video != null) {
				if (video.getVideoUrl() != null) {
					String p = request.getSession().getServletContext()
							.getRealPath("/" + video.getVideoUrl());
					File f = new File(p);
					f.delete();
				}
			}

			model.setVideoUrl(trainweChildSelect);
		}

		BProductChapter bc = new BProductChapter();
		bc.setId(chapterid);
		model.setBProductChapter(bc);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setPublicTime(format.format(new Date()));
		
		if (model.getMode()==0 || model.getMode()==2) {
			model.setChatStatus(1);
		}

		bProductVideoService.addVideo(model);

		return "addVideo";
	}

	public String deleteVideo() {
		HttpServletRequest request = ServletActionContext.getRequest();

		BProductVideo video = bProductVideoService.findVideoById(model.getId());
		
		String resUrl = video.getVideoUrl().replace("http://sxyres.000861.com/", "");
		FileUploadUtil.DelRemoteResource(resUrl);
//		String path = request.getSession().getServletContext()
//				.getRealPath("/" + video.getVideoUrl());
//		File f = new File(path);
//		f.delete();

		bProductVideoService.deleteVideo(video);

		return "deleteVideo";
	}

	public String deleteVideos() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String[] str = ids.split(",");

		for (int i = 0; i < str.length; i++) {

			BProductVideo video = bProductVideoService.findVideoById(Integer
					.parseInt(str[i]));
			
			String resUrl = video.getVideoUrl().replace("http://sxyres.000861.com/", "");
			FileUploadUtil.DelRemoteResource(resUrl);
			
//			String path = request.getSession().getServletContext()
//					.getRealPath(video.getVideoUrl());
//			File f = new File(path);
//			f.delete();
			bProductVideoService.deleteVideo(video);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deleteVideos";
	}

	public String findByMap() {

		BProductChapter bc = bProductChapterService.findChapterById(chapterid);

		model.setBProductChapter(bc);
		// 条件的封装
		Map<String, Object> map = this.getMap(model);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addVideo.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductVideo> list = bProductVideoService.findPageByMap(
				"from BProductVideo", map, pageBean);
		Long count = bProductVideoService.countByMap(
				"select count(*) from BProductVideo", map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminVideo_findByMap.action", count,
				Integer.parseInt(page), 6, null);

		List<BProductChapter> chapterList = bProductChapterService
				.findChapters();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("video", list);
		this.getSession().setAttribute("chapterList", chapterList);

		return "findByMap";
	}

	public Map<String, Object> getMap(BProductVideo model) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (model != null) {

			if (model.getTitle() != null && !"".equals(model.getTitle())) {

				map.put("title", model.getTitle());
			}

			if (model.getBProductChapter() != null) {

				map.put("BProductChapter", model.getBProductChapter());
			}

		}

		return map;

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

	public String getChapterAjax() {
		HttpServletResponse response = ServletActionContext.getResponse();
		List<BProductChapter> chapters = bProductChapterService
				.findChapterByCourseIdAjax(courseId);

		try {
			ResponseUtil.write(response, JSON.toJSONString(chapters));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getTrainweChildSelect() {
		return trainweChildSelect;
	}

	public void setTrainweChildSelect(String trainweChildSelect) {
		this.trainweChildSelect = trainweChildSelect;
	}

}
