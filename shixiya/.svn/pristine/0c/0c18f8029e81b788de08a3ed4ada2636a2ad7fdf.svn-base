package com.ehighsun.shixiya.student.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.BProductAdvertisementService;
import com.ehighsun.shixiya.service.BProductApplyService;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.DayeeService;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BPStudentCourseAction extends ActionSupport {

	private List<BProductAdvertisement> bpAdvertisement;
	private List<BProductCourse> bpCourses;
	private BProductCourse bpCourse;

	// private Set<BProductChapter> bpChapters;
	// private List<Set<BProductVideo>> bpVideos;

	private List<BProductStudentApply> bpStudentApply;
	private List<Lable> lables;
	private String lableName;
	private Integer timeQuantum; // 时间长度 1个月=30天 ；3个月=90天；半年=180天；一年内=365天；
	private int studentId;
	
	private String targetUrl;
	
	private List resumes;

	/* 用于查找该课程的参与人数 */
	private int courseId;
	private int countApplyOfCourse;
	private String applyStatus;
	private String isResume;

	@Resource(name = "bProductAdvertisementService")
	private BProductAdvertisementService bpAdvService;
	@Autowired
	private BProductCourseService bpCourseService;
	@Autowired
	private BProductApplyService bpApplyService;
	@Autowired
	private LableService lableService;
	@Resource(name = "baseDao")
	private BaseDao<Resume> resumeDao;
	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;
	@Resource(name = "dayeeService")
	private DayeeService dayeeService;
	
	
	private Integer AjaxPage;
	

	/* 进入主页面获取主页面所需信息 */
	public String showAllCourses() {

		lables = lableService.findLablesByType(1);
		bpAdvertisement = bpAdvService.findAllAdvertisement();
		// bpCourses = bpCourseService.findBCourse();
		// countApplyOfCourse =
		// bpApplyService.countBpApplyByCourse(Integer.parseInt(courseId));

		return "showAllCourses";
	}

	public String showAllCourseAjax() {
		PageBean pageBean = new PageBean(AjaxPage, 5);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			bpCourses = bpCourseService.findBCourse(pageBean);

			ResponseUtil.write(response, JSON.toJSONString(bpCourses));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 进入课程简介报名页面，判断学生的报名状态,并且检查学生是否填写过简历resume，若没有则跳转到简历填写页面 */
	public String judgeApplyStatus() {

		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		if(student==null) {
			
			targetUrl ="judgeApplyStatus_bp?courseId="+courseId;
			return "nologin";
		
		}

		System.out.println("courseId:"+courseId);
		bpCourse = bpCourseService.findBCourseById(courseId);
		// bpChapters = bpCourse.getBProductChapters();
		// bpVideos = bpChapters
		/* 课程浏览数start */
		if (bpCourse.getVisitnum() == null) {
			bpCourse.setVisitnum(0);
		}
		bpCourse.setVisitnum(bpCourse.getVisitnum() + 1);
		bpCourseService.updateBCourse(bpCourse);
		/* 课程浏览数end */
		
		judgeResume();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		try {
			if(today.getTime() > df.parse(bpCourse.getEndTime()).getTime() ){
				ServletActionContext.getServletContext().setAttribute("isEnd", true);
			}else ServletActionContext.getServletContext().setAttribute("isEnd", false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		applyStatus = bpApplyService.judgeApplyStatus(studentId, courseId);

		return "judgeApplyStatus";
	}
	
	public String judgeResumeForApply(){
		
		judgeResume();
		
		/*判断是否有简历，若报了名，又没有简历，则执行dayeeService.judgeResumeIsExist，去大易搜索简历*/
		if (resumes == null || resumes.size() == 0) {
			isResume = "false";
			List stuIsApply = resumeDao.executeOurSql("select 审核状态 from b体验式课程学生报名表 where 审核状态 = 3 and 学生ID = "+studentId+" and 课程ID="+courseId);
			if (stuIsApply == null || stuIsApply.size() == 0) {
				/*该学生还没有报名不做任何操作*/
			}else {
				isResume = dayeeService.judgeResumeIsExist(studentId);
			}
		} else{
			isResume = "true";
			resumeDao.executeSql("UPDATE b体验式课程学生报名表 SET 审核状态 = 0 WHERE 审核状态 = 3 and 学生ID = "+studentId+" and 课程ID="+courseId);
		}
		
		applyStatus = bpApplyService.judgeApplyStatus(studentId, courseId);
		System.out.println("judgeResumeForApply。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("isResume:"+isResume+",applyStatus:"+applyStatus+",courseId:"+courseId);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		JSONObject isExistResume = new JSONObject();
		isExistResume.put("isResume", isResume);
		isExistResume.put("applyStatus", applyStatus);
		isExistResume.put("courseId", courseId);
		
		try {
			ResponseUtil.write(response, isExistResume.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void judgeResume(){
		
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");
		studentId = student.getId();
		
		String sql1 = "(姓名='" + student.getName()	+ "' and 邮箱='"+student.getEmail()+"')";
		String sql2 = "(姓名='" + student.getName()	+ "' and 手机号码='"+student.getTelephone()+"')";
		String sql3 = "(手机号码='"+student.getTelephone()+"' and 邮箱='"+student.getEmail()+"')";
		
		resumes = resumeDao
				.executeOurSql("select 简历ID from 简历管理 where "+sql1+" "+" or "+sql2+" or "+sql3);

	}

	/* 通过'项目类型'标签筛选课程 */
	public String lableFilter() {
		PageBean pageBean = new PageBean(AjaxPage, 5);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			bpCourses = bpCourseService.findBCourseByLable(lableName,pageBean);

			ResponseUtil.write(response, JSON.toJSONString(bpCourses));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 通过时间长度 筛选项目
	public String timeQuantumFilter() {
		PageBean pageBean = new PageBean(AjaxPage, 5);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			bpCourses = bpCourseService
					.getBProductCoursesByTimeQuantum(timeQuantum,pageBean);

			ResponseUtil.write(response, JSON.toJSONString(bpCourses));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 获取B产品学生已报名的课程 */
	public String getApplyCourses() {

		return "getApplyCourses";
	}

	public List<BProductStudentApply> getBpStudentApply() {
		return bpStudentApply;
	}

	public void setBpStudentApply(List<BProductStudentApply> bpStudentApply) {
		this.bpStudentApply = bpStudentApply;
	}

	public List<BProductAdvertisement> getBpAdvertisement() {
		return bpAdvertisement;
	}

	public void setBpAdvertisement(List<BProductAdvertisement> bpAdvertisement) {
		this.bpAdvertisement = bpAdvertisement;
	}

	public int getCountApplyOfCourse() {
		return countApplyOfCourse;
	}

	public void setCountApplyOfCourse(int countApplyOfCourse) {
		this.countApplyOfCourse = countApplyOfCourse;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public List<Lable> getLables() {
		return lables;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public void setLables(List<Lable> lables) {
		this.lables = lables;
	}

	public BProductCourse getBpCourse() {
		return bpCourse;
	}

	public void setBpCourse(BProductCourse bpCourse) {
		this.bpCourse = bpCourse;
	}

	public String getIsResume() {
		return isResume;
	}

	public Integer getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Integer timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public void setIsResume(String isResume) {
		this.isResume = isResume;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public List<BProductCourse> getBpCourses() {
		return bpCourses;
	}

	public void setBpCourses(List<BProductCourse> bpCourses) {
		this.bpCourses = bpCourses;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public List getResumes() {
		return resumes;
	}

	public void setResumes(List resumes) {
		this.resumes = resumes;
	}

	public Integer getAjaxPage() {
		return AjaxPage;
	}

	public void setAjaxPage(Integer ajaxPage) {
		AjaxPage = ajaxPage;
	}

}
