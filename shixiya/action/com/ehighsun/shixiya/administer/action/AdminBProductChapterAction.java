package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductChapterService;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.BProductVideoService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductChapterAction extends BaseAction<BProductChapter>{
	
	private File video;
	private String videoContentType;
	private String videoFileName;
	private BProductVideo bv;
	
	
	private Integer courseId;
	private String page;
	private String ids;
	
	
	@Autowired
	private BProductChapterService bProductChapterService;
	@Autowired
	private BProductCourseService bProductCourseService;
	@Autowired
	private BProductVideoService bProductVideoService;
	
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public BProductVideo getBv() {
		return bv;
	}

	public void setBv(BProductVideo bv) {
		this.bv = bv;
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

	
	


	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	
	public String showChapter(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addchapter.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<BProductChapter> list = bProductChapterService.findBChapter("from BProductChapter", null, pageBean);
		Long count = bProductChapterService.countChapters("select count(*) from BProductChapter");
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminChapter_showChapter.action", count, Integer.parseInt(page),6,null);
		
		List<BProductCourse> clist = bProductCourseService.findBCourse();
		
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("chapter", list);
		this.getSession().setAttribute("courseList", clist);
		
		return "showChapter";
	}
	
	
	public String addChapter(){
		
	
//		BProductCourse bc = new BProductCourse();
//		bc.setId(courseId);
//		model.setBProductCourse(bc);
		bProductChapterService.addBChapter(model);
	
		
		return "addChapter";
	}
	
	public String deleteChapter(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = null;
		BProductChapter bc = bProductChapterService.findChapterById(model.getId());
		Set<BProductVideo> set = bc.getBProductVideos();
		for (BProductVideo bProductVideo : set) {
			
			path = request.getSession().getServletContext().getRealPath(bProductVideo.getVideoUrl());
			File f = new File(path);
			f.delete();
			bProductVideoService.deleteVideo(bProductVideo);
		}
		bProductChapterService.deleteBChapter(bc);
		return "deleteChapter";
	}
	
	public String deleteChapters(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = null;
		String[] str = ids.split(",");

		for (int i = 0; i < str.length; i++) {
			BProductChapter bc = bProductChapterService.findChapterById(Integer.parseInt(str[i]));
			Set<BProductVideo> set = bc.getBProductVideos();
			for (BProductVideo bProductVideo : set) {
				
				path = request.getSession().getServletContext().getRealPath(bProductVideo.getVideoUrl());
				if(StringUtil.isNotEmpty(path)){
					File f = new File(path);
					f.delete();
				}
				
				bProductVideoService.deleteVideo(bProductVideo);
			}
			bProductChapterService.deleteBChapter(bc);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "deleteChapters";
	}
	
	public String findChapterByCourseId(){
		List<BProductChapter> lists = bProductChapterService.findChapterByCourseId(courseId);
		this.getSession().setAttribute("chapter", lists);
		return "findChapterSuccess";
	}
	
	public String findChapterByMap(){
	
		BProductChapter bpc = model;
		if(courseId!=null){
			
			BProductCourse bc = new BProductCourse();
			bc.setId(courseId);
			model.setBProductCourse(bc);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		if(bpc.getTitle()!=null && !"".equals(bpc.getTitle())){
			map.put("title", bpc.getTitle());
		}
		if(bpc.getBProductCourse()!=null){
			map.put("BProductCourse", bpc.getBProductCourse());
		}
	
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addchapter.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<BProductChapter> list = bProductChapterService.findPageByMap("from BProductChapter", map, pageBean);
		Long count = bProductChapterService.countByMap("select count(*) from BProductChapter",map);
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/adminChapter_findChapterByMap.action", count, Integer.parseInt(page),6,null);
		
		List<BProductCourse> clist = bProductCourseService.findBCourse();
		
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("chapter", list);
		this.getSession().setAttribute("courseList", clist);
		
		
		return "findByMap";
		
	}
	
	public String updateChapter(){
		bProductChapterService.updateChapter(model);
		return "updateChapterSuccess";
	}
	
	
	

	
	public String addVideo(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();

		String houzhui = videoFileName.substring(videoFileName.indexOf('.'));	
		String randomDir = this.generateRandomDir(UUID.randomUUID().toString());
		String path = request.getSession().getServletContext().getRealPath(randomDir);
		String saveName =  UUID.randomUUID().toString();
		String savePath = randomDir+"/"+saveName+houzhui;
		File file = new File(path, saveName+houzhui);
		try {
			path = file.getCanonicalPath();
			FileUtils.copyFile(video, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		bv.setVideoUrl(savePath);
		
		
		bProductChapterService.addVideo(bv);
		
		return "addVideoSuccess";
	}
	
	public String deleteVideo(){
		
		Boolean b = null;
		BProductVideo bpv = bProductChapterService.findVideoById(bv.getId());
		if(bpv!=null){
			File f = new File(bpv.getVideoUrl());
			b = f.delete();
			bProductChapterService.deleteVideo(bv);
		}
		if(b){
			return "deleteVideoSuccess";
		}else{
			return "deleteVideoFail";
		}
		
	}
	
	public String findVideoByChapterId(){
		List<BProductVideo> lists = bProductChapterService.findVideo(model.getId());
		this.getSession().setAttribute("video", lists);
		return "findVideoSuccess";
	}
	
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public  String generateRandomDir(String uuidFileName) {
		// 获得唯一文件名的hashcode
		int hashcode = uuidFileName.hashCode();
		// 获得一级目录
		int d1 = hashcode & 0xf;       
		// 获得二级目录
		int d2 = (hashcode >>> 4) & 0xf;

		return d2 + "/" + d1;// 共有256目录l
	}

	
}
