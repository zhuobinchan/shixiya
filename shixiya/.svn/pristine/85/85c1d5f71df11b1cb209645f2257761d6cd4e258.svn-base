package com.ehighsun.shixiya.hr.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiabin.entity.Zone;
import jiabin.service.ZoneService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.HrAdminServer;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GetHrIndexContentAction extends ActionSupport {

	private List<HrCommentList> hrCommentLists;
	private List<CProductHR> hrs;
	private String mainPage;// 设置主页右边显示的页面

	private String id; // 周点评Id
	private String ids; // 周点评Ids,格式为“1,2,3,4”的字符串
	private String title; // 周点评标题
	private String hrId; // hrId
	private String introduction; // 周点评简介
	private Integer state;
	private Integer mode;
	private Integer sectionId;
	private Integer visitnum;

	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private HttpServletRequest request = ServletActionContext.getRequest();

	// 上传视频
	private File video;
	private String videoContentType;
	private String videoFileName;
	// 上传图片
	private File img;
	private String imgContentType;
	private String imgFileName;

	@Resource(name = "baseDao")
	private BaseDao<HrCommentList> hrCommentListDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductHR> hrListDao;
	@Autowired
	private HrAdminServer hrAdminServer;
	@Resource(name = "zoneService")
	private ZoneService zoneService;

	public String getHrCommentList() {

		hrCommentLists = hrCommentListDao.find("From HrCommentList order by publicTime desc");

		System.out.println(hrCommentLists.get(2).getIntroduction());

		return "getHrCommentList";
	}

	/* 返还数据给学生Hr周点评主页 */
	public String hrAdminIndex() {

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		hrCommentLists = hrCommentListDao.find("From HrCommentList order by publicTime desc",
				new Object[] {}, pageBean);
		Long count = hrCommentListDao
				.count("select count(*) from HrCommentList");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/hrAdminIndex_hr.action", count,
				Integer.parseInt(page), 6, null);

		hrs = hrListDao.find("From CProductHR");

		ServletActionContext.getRequest().getSession()
				.setAttribute("pageCode", pageCode);
		
		ServletActionContext.getRequest().getSession()
				.setAttribute("page", page);

		List<Zone> zonelist = zoneService.findAllZoneList();
		ServletActionContext.getRequest().getSession()
				.setAttribute("zonelist", zonelist);

		mainPage = "hrAdminIndex.jsp";
		return "hrAdminIndex";
	}

	/* 返还筛选数据给管理员Hr周点评主页 */
	public String hrCommentFilter() {

		hrCommentLists = hrAdminServer.hrCommentListFilter(request, title,
				hrId, "", page);
		hrs = hrListDao.find("From CProductHR");
		mainPage = "hrAdminIndex.jsp";

		return "hrCommentFilter";
	}

	/* 添加Hr周点评 */
	public String addHrComment() {

		hrAdminServer.saveHrcommentList(title, hrId, introduction, img,
				imgFileName, video, videoFileName, sectionId, visitnum,state,mode);

		return "addHrComment";
	}

	/* 添加Hr周点评 */
	public String updateHrComment() {

		hrAdminServer.updateHrcommentList(id, title, hrId, introduction, img,
				imgFileName, video, videoFileName, sectionId, visitnum,state,mode);

		return "updateHrComment";
	}

	/* 删除单条或多条数据 */
	public String delHrComment() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();

		if (ids == null && id != null) {
			

			
			hrAdminServer.delHrcommentList(id);
		} else if (ids != null && id == null)
			hrAdminServer.delGroupHrcommentList(ids);

		String result = "{'success':'true'}";
		out.print(result);

		return null;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public List<CProductHR> getHrs() {
		return hrs;
	}

	public void setHrs(List<CProductHR> hrs) {
		this.hrs = hrs;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public List<HrCommentList> getHrCommentLists() {
		return hrCommentLists;
	}

	public void setHrCommentLists(List<HrCommentList> hrCommentLists) {
		this.hrCommentLists = hrCommentLists;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String enterHrChating() {

		return "enterHrChating";
	}

	public Integer getVisitnum() {
		return visitnum;
	}

	public void setVisitnum(Integer visitnum) {
		this.visitnum = visitnum;
	}

}
