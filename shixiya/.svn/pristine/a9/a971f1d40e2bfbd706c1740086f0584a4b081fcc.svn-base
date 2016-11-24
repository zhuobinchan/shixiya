package com.ehighsun.shixiya.student.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.service.DayeeService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.ThirdPartyUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CPStudentBroadcastAction extends ActionSupport {

	private List<CProductAdv> cpAdvs;
	private List<CProductBroadcast> cpBroadcasts;
	private CProductBroadcast cpBroadcast;
	private CProductStudentApply cpStudentApply;
	private List<Lable> lables;
	private String lableName;
	
	private int studentId;
	
	private List resumes;
	
	private String targetUrl;

	/* 用于查找该课程的参与人数 */
	private int broadcastId;
	private int countApplyOfCourse;
	private String applyStatus;
	private String isResume;

	@Autowired
	private CProductBroadcastService cpBroadcastService;
	@Resource(name = "baseDao")
	private BaseDao<CProductAdv> cpAdvDao;
	@Resource(name = "baseDao")
	private BaseDao<Lable> lableDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductStudentApply> applyDao;
	@Resource(name = "dayeeService")
	private DayeeService dayeeService;
	
	private Integer AjaxPage;

	/* 进入C产品主页面，获取主页面所需信息 */
	public String showAllBroadcast() {

		lables = lableDao.find("From Lable where type = 1");
		cpAdvs = cpAdvDao.find("From CProductAdv where status=1");
//		cpBroadcasts = cpBroadcastService.getAllCPBroadcast();

		return "showAllBroadcast";
	}

	/* 进入C产品主页面，获取主页面所需信息 */
	public String getBroadcastByAjax() {
		PageBean pageBean = new PageBean(AjaxPage, 5);
		
		cpBroadcasts = cpBroadcastService.getAllCPBroadcast(pageBean);

		HttpServletResponse response = ServletActionContext.getResponse();

//		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
//				CProductBroadcast.class, "id", "name", "publisher",
//				"publicTime", "startTime", "endTime", "introduction", "status",
//				"videoUrl", "imgUrl", "participation","lablePaste","lable");

		String result = JSON.toJSONString(cpBroadcasts,SerializerFeature.DisableCircularReferenceDetect);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/* 进入课程简介报名页面，判断学生的报名状态,并且检查学生是否填写过简历resume，若没有则跳转到简历填写页面 */
	public String judgeApplyStatus() {
		Student student = (Student) ServletActionContext.getContext()
				.getSession().get("student");
		
		if(student==null) {
			
			targetUrl ="judgeApplyStatus_cp?broadcastId="+broadcastId;
			return "nologin";
		
		}

		studentId = student.getId();
		
		/*直播间浏览人数start*/
		cpBroadcast = cpBroadcastService.findById(broadcastId);
		System.out.println("cpBroadcastService.findById(broadcastId):"+broadcastId+cpBroadcast.getName());
		
		if (cpBroadcast.getVisitnum()==null) {
			cpBroadcast.setVisitnum(0);
		}
		cpBroadcast.setVisitnum(cpBroadcast.getVisitnum()+1);
		cpBroadcastService.saveOrUpdateCPBroadcast(cpBroadcast);
		/*直播间浏览人数end*/
		
		judgeResume();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		try {
			if(today.getTime() > df.parse(cpBroadcast.getEndTime()).getTime() ){
				ServletActionContext.getServletContext().setAttribute("isEnd", true);
			}else ServletActionContext.getServletContext().setAttribute("isEnd", false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		applyStatus = cpBroadcastService.judgeApplyStatus(studentId,
				broadcastId);

		return "judgeApplyStatus";
	}
	
	public String judgeResumeForApply(){
		
		judgeResume();
		
		/*判断是否有简历，若报了名，又没有简历，则执行dayeeService.judgeResumeIsExist，去大易搜索简历*/
		if (resumes == null || resumes.size() == 0) {
			isResume = "false";
			List stuIsApply = applyDao.executeOurSql("select 审核状态 from c组团报名表 where 审核状态 = 3 and 学生ID = "+studentId+" and 直播ID="+broadcastId);
			if (stuIsApply == null || stuIsApply.size() == 0) {
				/*该学生还没有报名不做任何操作*/
			}else {
				isResume = dayeeService.judgeResumeIsExist(studentId);
			}
			
		} else{
			isResume = "true";
			applyDao.executeSql("UPDATE c组团报名表 SET 审核状态 = 0 WHERE 审核状态 = 3 and 学生ID = "+studentId+" and 直播ID="+broadcastId);
		}
		
		applyStatus = cpBroadcastService.judgeApplyStatus(studentId,
				broadcastId);
		System.out.println("judgeResumeForApply。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("isResume:"+isResume+",applyStatus:"+applyStatus+",broadcastId:"+broadcastId);
		HttpServletResponse response = ServletActionContext.getResponse();
		
		JSONObject isExistResume = new JSONObject();
		isExistResume.put("isResume", isResume);
		isExistResume.put("applyStatus", applyStatus);
		isExistResume.put("broadcastId", broadcastId);
		
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
		
		resumes = lableDao
				.executeOurSql("select 简历ID from 简历管理 where "+sql1+" "+" or "+sql2+" or "+sql3);

	}

	/* 通过标签筛选课程 */
	public String lableFilter() {

		// lables = lableDao.find("From Lable");
		// cpAdvs = cpAdvDao.find("From CProductAdv");
		cpBroadcasts = cpBroadcastService.findBroadcastsByLable(lableName);

		System.out.println(cpBroadcasts.get(0).getName());
		HttpServletResponse response = ServletActionContext.getResponse();

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				CProductBroadcast.class, "id", "name", "publisher",
				"publicTime", "startTime", "endTime", "introduction", "status",
				"videoUrl", "imgUrl","lablePaste","visitnum");

		String result = JSON.toJSONString(cpBroadcasts, filter);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	


	public List<CProductBroadcast> getCpBroadcasts() {
		return cpBroadcasts;
	}

	public void setCpBroadcasts(List<CProductBroadcast> cpBroadcasts) {
		this.cpBroadcasts = cpBroadcasts;
	}

	public CProductBroadcast getCpBroadcast() {
		return cpBroadcast;
	}

	public void setCpBroadcast(CProductBroadcast cpBroadcast) {
		this.cpBroadcast = cpBroadcast;
	}

	public CProductStudentApply getCpStudentApply() {
		return cpStudentApply;
	}

	public void setCpStudentApply(CProductStudentApply cpStudentApply) {
		this.cpStudentApply = cpStudentApply;
	}

	public List<Lable> getLables() {
		return lables;
	}

	public void setLables(List<Lable> lables) {
		this.lables = lables;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<CProductAdv> getCpAdvs() {
		return cpAdvs;
	}

	public void setCpAdvs(List<CProductAdv> cpAdvs) {
		this.cpAdvs = cpAdvs;
	}

	public int getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
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

	public String getIsResume() {
		return isResume;
	}

	public void setIsResume(String isResume) {
		this.isResume = isResume;
	}

	public List getResumes() {
		return resumes;
	}

	public void setResumes(List resumes) {
		this.resumes = resumes;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public Integer getAjaxPage() {
		return AjaxPage;
	}

	public void setAjaxPage(Integer ajaxPage) {
		AjaxPage = ajaxPage;
	}
	
}
