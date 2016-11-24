package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiabin.entity.Zone;
import jiabin.service.SectionService;
import jiabin.service.ZoneService;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.LablePaste;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.CProductHRService;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.RoleService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCProductBroadcastAction extends ActionSupport {
	private String mainPage;

	private Integer broadcastId; // 直播间的id
	private String broadcastIds;// 多条的直播间的数据信息
	private Integer sectionId;

	private List<CProductBroadcast> broadcasts;// 获取所有C组团直播间
	private List<LablePaste> lablePastes;

	// 用于分页的使用start
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private CProductBroadcast broadcast;
	private String lablename;
	private Integer lableId;
	
	// 用于分页的使用end

	private List<CProductHR> cpHr;// 获取所有C组团hr公司 进行分类

	// 上传视频
	private File video;
	private String videoContentType;
	private String videoFileName;
	// 上传图片
	private File img;
	private String imgContentType;
	private String imgFileName;

	@Resource(name = "cProductBroadcastService")
	private CProductBroadcastService cProductBroadcastService;
	@Resource(name = "sectionService")
	private SectionService sectionService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	@Resource(name = "cProductHRService")
	private CProductHRService cpHRService;
	@Resource(name = "lableService")
	private LableService lableService;
	@Resource(name = "baseDao")
	private BaseDao<LablePaste> lablePasteDao;

	private HttpServletRequest request = ServletActionContext.getRequest();

	private FileUploadUtil fileUploadUtil = new FileUploadUtil();

	
	

	public Integer getLableId() {
		return lableId;
	}

	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public List<CProductBroadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(List<CProductBroadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public CProductBroadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(CProductBroadcast broadcast) {
		this.broadcast = broadcast;
	}

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public String getBroadcastIds() {
		return broadcastIds;
	}

	public void setBroadcastIds(String broadcastIds) {
		this.broadcastIds = broadcastIds;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public List<CProductHR> getCpHr() {
		return cpHr;
	}

	public void setCpHr(List<CProductHR> cpHr) {
		this.cpHr = cpHr;
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

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getLablename() {
		return lablename;
	}

	public void setLablename(String lablename) {
		this.lablename = lablename;
	}

	public List<LablePaste> getLablePastes() {
		return lablePastes;
	}

	public void setLablePastes(List<LablePaste> lablePastes) {
		this.lablePastes = lablePastes;
	}

	// 通过直播间名字查询
	public String findByName() {
		mainPage = "CPBroadcastSection.jsp";

		cpHr = cpHRService.getAllCProductHRs();

		broadcasts = cProductBroadcastService.findByCondition(
				broadcast.getName(), broadcast.getCProductHR().getHrId(),
				request, page);

		return "findByName";
	}

	// 获取所有的直播间信息
	public String getAllCPBroadcast() {

		mainPage = "CPBroadcastSection.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		cpHr = cpHRService.getAllCProductHRs();
		broadcasts = cProductBroadcastService.getAllCPBroadcast(pageBean);
		lablePastes = lablePasteDao.find("From LablePaste");

		Long count = cProductBroadcastService
				.countBroadcast("select count(*) from CProductBroadcast");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllCPBroadcast_AdminCPBroadcastAction.action",
				count, Integer.parseInt(page), 6, null);

		ActionContext.getContext().getSession().put("pageCode", pageCode);
		ActionContext.getContext().getSession().put("page", page);

		List<Zone> zonelist = zoneService.findAllZoneList();
		ServletActionContext.getRequest().getSession()
				.setAttribute("zonelist", zonelist);

		List<Lable> llist = lableService.findLable();
		ServletActionContext.getRequest().getSession()
				.setAttribute("lableList", llist);

		return "getAllCPBroadcast";
	}

	public String addBroadcast() {

		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			broadcast.setImgUrl(imgUrl);
		}

		if (video != null && video.exists()) {
			String videoUrl = fileUploadUtil.updateSmbFile(video, videoFileName,
					"media");
			broadcast.setVideoUrl(videoUrl);
		}

		broadcast.setLable(lablename);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		broadcast.setPublicTime(time);
		
		broadcast.setParticipation(0);
		if (broadcast.getMode()==0 || broadcast.getMode()==2) {
			broadcast.setChatStatus(1);
		}
		
		cProductBroadcastService.saveCPBroadcast(broadcast);

		return "addBroadcast";
	}

	// 保存或更新直播间
	public String saveOrUpdate() {

		if (img != null && img.exists()) {

			String imgUrl = fileUploadUtil.updateFile(img, imgFileName,
					"images");
			broadcast.setImgUrl(imgUrl);
		} else {

			CProductBroadcast b = cProductBroadcastService.findById(broadcast
					.getId());
			if (b != null && b.getImgUrl() != null) {
				broadcast.setImgUrl(b.getImgUrl());
//				String path = ServletActionContext.getRequest().getSession()
//						.getServletContext().getRealPath("/" + b.getImgUrl());
//				File f = new File(path);
//				f.delete();
			}
		}

		if (video != null && video.exists()) {
			String videoUrl = fileUploadUtil.updateSmbFile(video, videoFileName,
					"media");
			broadcast.setVideoUrl(videoUrl);
		} else {

			CProductBroadcast b = cProductBroadcastService.findById(broadcast
					.getId());
			if (b != null && b.getVideoUrl() != null) {
				broadcast.setVideoUrl(b.getVideoUrl());
//				String path = ServletActionContext.getRequest().getSession()
//						.getServletContext().getRealPath("/" + b.getVideoUrl());
//				File f = new File(path);
//				f.delete();
			}
		}

		broadcast.setLable(lablename);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		broadcast.setPublicTime(time);
		
		if (broadcast.getMode()==0 || broadcast.getMode()==2) {
			broadcast.setChatStatus(1);
		}

		cProductBroadcastService.saveOrUpdateCPBroadcast(broadcast);

		return "saveBroadcast";
	}

	// 删除以一条直播间信息
	public String delete() throws Exception {
		System.out.println("broadcastId:" + broadcastId);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		cProductBroadcastService.deleteCPBroadcastById(broadcastId);
		
		
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return "delete";
	}

	// 删除多条的直播间信息
	public String deleteBroadcasts() throws Exception {
		System.out.println(broadcastIds);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		cProductBroadcastService.deleteBroadcastList(broadcastIds);
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return "deleteBroadcasts";
	}

}
