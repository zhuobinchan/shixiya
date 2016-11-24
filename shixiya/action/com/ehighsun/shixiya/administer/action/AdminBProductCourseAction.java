package com.ehighsun.shixiya.administer.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jiabin.service.SectionService;
import jiabin.service.ZoneService;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.BProductTeacherService;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.RoleService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductCourseAction extends BaseAction<BProductCourse> {

	private List<File> advertisement;
	private String[] advertisementContentType;
	private String[] advertisementFileName;

	private File image;
	private String imageContentType;
	private String imageFileName;

	private String page;
	private Integer teacherId;
	private String lablename;
	private Integer sectionId;

	private String ids;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getLablename() {
		return lablename;
	}

	public void setLablename(String lablename) {
		this.lablename = lablename;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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

	private Integer adId;

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public List<File> getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(List<File> advertisement) {
		this.advertisement = advertisement;
	}

	public String[] getAdvertisementContentType() {
		return advertisementContentType;
	}

	public void setAdvertisementContentType(String[] advertisementContentType) {
		this.advertisementContentType = advertisementContentType;
	}

	public String[] getAdvertisementFileName() {
		return advertisementFileName;
	}

	public void setAdvertisementFileName(String[] advertisementFileName) {
		this.advertisementFileName = advertisementFileName;
	}

	@Autowired
	private BProductCourseService bProductCourseService;
	@Autowired
	private BProductTeacherService bProductTeacherService;
	@Resource(name = "lableService")
	private LableService lableService;
	@Resource(name = "sectionService")
	private SectionService sectionService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "zoneService")
	private ZoneService zoneService;

	public String showCourse() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addCourse.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductCourse> list = bProductCourseService.findBCourse(
				"from BProductCourse", null, pageBean);
		Long count = bProductCourseService
				.countCourses("select count(*) from BProductCourse");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminCourse_showCourse.action", count,
				Integer.parseInt(page), 6, null);

		List<BProductTeacher> tlist = bProductTeacherService.findBTeacher();
		List<Lable> llist = lableService.findLable();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("page", page);
		this.getSession().setAttribute("course", list);
		this.getSession().setAttribute("teacherList", tlist);
		this.getSession().setAttribute("lableList", llist);

		return "showCourse";
	}

	public String addCourse() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(imageFileName);
		if (imageFileName != null) {
			
//			
//			String houzhui = imageFileName
//					.substring(imageFileName.indexOf('.'));
//			String randomDir = this.generateRandomDir(UUID.randomUUID()
//					.toString());
//			String path = request.getSession().getServletContext()
//					.getRealPath("/images/" + randomDir);
//			String saveName = UUID.randomUUID().toString();
//			String savePath = "images/" + randomDir + "/" + saveName + houzhui;
//			File file = new File(path, saveName + houzhui);
//			try {
//				path = file.getCanonicalPath();
//				FileUtils.copyFile(image, file);
//			} catch (IOException e) {
//
//				e.printStackTrace();
//			}
			
			FileUploadUtil fileUtil = new FileUploadUtil();
			String savePath = fileUtil.updateFile(image, imageFileName, "images");
			
			
			model.setImageUrl(savePath);

			// 更新时删除文件
			BProductCourse c = bProductCourseService.findBCourseById(model
					.getId());
			if (c != null && c.getImageUrl() != null) {
				
//				String resUrl = c.getImageUrl().replace("sxyres.000861.com/", "");
//				
//				FileUploadUtil.DelRemoteResource(resUrl);
				
				String oldPath = request.getSession().getServletContext()
						.getRealPath("/"+c.getImageUrl());
				File f = new File(oldPath);
				f.delete();
			}

		} else {
			BProductCourse c = bProductCourseService.findBCourseById(model
					.getId());
			if (c != null && c.getImageUrl() != null) {
				model.setImageUrl(c.getImageUrl());
			}
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		BProductTeacher teacher = bProductTeacherService
				.findBTeacherById(teacherId);

		model.setPublicTime(time);
		Administer admin = (Administer) getSession().getAttribute("user");
		model.setPublisher(admin.getName());
		model.setBProductTeacher(teacher);
		model.setLable(lablename);

		if (model.getParticipation() == null) {

			model.setParticipation(0);
		}
		bProductCourseService.addBCourse(model);

		return "addCourseSuccess";
	}

	public String deleteCourse() {

		BProductCourse bc = bProductCourseService
				.findBCourseById(model.getId());
		if (bc != null) {
			model.setBProductTeacher(null);
			if (bc.getImageUrl() != null) {
				
//				String resUrl = bc.getImageUrl().replace("sxyres.000861.com/", "");
//				FileUploadUtil.DelRemoteResource(resUrl);;
				String path = ServletActionContext.getRequest().getRealPath(
						"/" + bc.getImageUrl());
				File f = new File(path);
				f.delete();
			}
		}
		bProductCourseService.deleteBCourse(bc);

		return "deleteCourse";
	}

	public String deleteCourses() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = null;
		String[] str = ids.split(",");

		for (int i = 0; i < str.length; i++) {
			BProductCourse bc = bProductCourseService.findBCourseById(Integer
					.parseInt(str[i]));

			if (bc != null) {
				if (bc.getImageUrl() != null) {
					
//					String resUrl = bc.getImageUrl().replace("sxyres.000861.com/", "");
//					FileUploadUtil.DelRemoteResource(resUrl);;
					path = request.getSession().getServletContext()
							.getRealPath("/" + bc.getImageUrl());
					File f = new File(path);
					f.delete();
				}
			}

			bProductCourseService.deleteBCourse(bc);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "deleteCourses";
	}

	// public String addAdvertisements(){
	//
	// String path = null;
	// String saveName = null;
	// HttpServletRequest request = ServletActionContext.getRequest();
	// String houzhui = null;
	// String randomDir = null;
	// String savePath = null;
	// List<BProductAdvertisement> lists = new
	// ArrayList<BProductAdvertisement>();
	//
	// for (int i = 0; i < advertisement.size(); i++) {
	//
	//
	// houzhui =
	// advertisementFileName[i].substring(advertisementFileName[i].indexOf('.'));
	// randomDir = this.generateRandomDir(UUID.randomUUID().toString());
	// path = request.getSession().getServletContext().getRealPath(randomDir);
	// saveName = UUID.randomUUID().toString();
	// savePath = randomDir+"/"+saveName+houzhui;
	// File file = new File(path, saveName+houzhui);
	// try {
	// path = file.getCanonicalPath();
	// FileUtils.copyFile(advertisement.get(i), file);
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	//
	// BProductAdvertisement bpa = new BProductAdvertisement();
	// bpa.setImageUrl(savePath);
	// bpa.setBProductCourse(model);
	//
	//
	// lists.add(bpa);
	//
	// }
	//
	// bProductCourseService.addAdvertisement(lists);
	//
	// return "addAdvertisementsSuccess";
	// }

	public String findAdvertisements() {
		List<BProductAdvertisement> lists = bProductCourseService
				.findAdvertisement(model.getId());
		this.getSession().setAttribute("advertisements", lists);
		return "findAdvertisementsSuccess";
	}

	public String deleteAdvertisement() {

		Boolean b = null;
		BProductAdvertisement ad = bProductCourseService
				.findAdvertisementById(adId);
		if (ad != null) {

			File f = new File(ad.getImageUrl());
			b = f.delete();
			bProductCourseService.deleteAdvertisement(ad);
		}
		if (b) {
			return "deleteAdvertisementSuccess";
		} else {
			return "deleteAdvertisementFail";
		}

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

	public String findByMap() {

		if (teacherId != null) {
			BProductTeacher t = new BProductTeacher();
			t.setId(teacherId);
			model.setBProductTeacher(t);
		}

		Map<String, Object> map = this.getMap(model);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "addCourse.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		List<BProductCourse> list = bProductCourseService.findPageByMap(
				"from BProductCourse", map, pageBean);
		Long count = bProductCourseService.countByMap(
				"select count(*) from BProductCourse", map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/adminCourse_findByMap.action", count,
				Integer.parseInt(page), 6, null);

		List<BProductTeacher> tlist = bProductTeacherService.findBTeacher();

		List<Lable> llist = lableService.findLable();

		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("course", list);
		this.getSession().setAttribute("teacherList", tlist);
		this.getSession().setAttribute("lableList", llist);

		return "findByMap";

	}

	public Map<String, Object> getMap(BProductCourse model) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (model != null) {

			if (model.getTitle() != null && !"".equals(model.getTitle())) {
				System.out.println(model.getTitle() + "//////////////");
				map.put("title", model.getTitle());
			}
			if (model.getStartTime() != null
					&& !"".equals(model.getStartTime())) {
				System.out.println(model.getStartTime() + "//////////////");
				map.put("startTime", model.getStartTime());
			}
			if (model.getEndTime() != null && !"".equals(model.getEndTime())) {
				System.out.println(model.getEndTime() + "//////////////");
				map.put("endTime", model.getEndTime());
			}
			if (model.getBProductTeacher() != null) {

				map.put("BProductTeacher", model.getBProductTeacher());
			}

		}

		return map;

	}

}
