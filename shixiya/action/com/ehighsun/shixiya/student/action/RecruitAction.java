package com.ehighsun.shixiya.student.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RIBussinessResume;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.service.RIBussinessService;
import com.ehighsun.shixiya.service.RecruitmentInfoService;
import com.ehighsun.shixiya.service.SendEmailService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.SendEmailUtil;
import com.opensymphony.xwork2.ActionSupport;

public class RecruitAction extends ActionSupport {

	private HttpServletResponse response = ServletActionContext.getResponse();
	
	@Resource(name = "recruitmentInfoServiceImpl")
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name = "riBussinessServiceImpl")
	private RIBussinessService riBussinessService;
	@Resource(name = "lableService")
	private LableService lableService;
	@Resource(name="baseDao")
	private BaseDao<RecruitResumeSubmit> recruitSubmitDao;
	@Resource(name="sendEmailServiceImpl")
	private SendEmailService sendEmailService;
	
	private List<RecruitmentInfo> recruitmentInfos;
	private RecruitmentInfo recruitmentInfo;
	private RecruitResumeSubmit recruitResumeSubmit;
	private List<Lable> lables;
	
	private Integer recruitId;
	private Integer companyId;
	private String lableName;
	private String applyStatus;
	
	private Integer AjaxPage;
	
	private Student student = (Student) ServletActionContext.getContext().getSession().get("student");
	
	/*获取所有工作岗位*/
	public void getAllRecruitmentByAjax(){
		PageBean pageBean = new PageBean(AjaxPage, 5);
		recruitmentInfos = recruitmentInfoService.findALLRecruitmentInfos(pageBean);
		
		ResponseUtil.write(response, JSONObject.toJSONString(recruitmentInfos,SerializerFeature.DisableCircularReferenceDetect));
		
	}
	
	/*获取工作岗位ById*/
	public String getRecruitmentsById(){
		
		recruitmentInfo = recruitmentInfoService.getRecruitmentInfoById(recruitId);
		
		String sql = "select 应聘ID  from 实习生招聘资讯应聘表  " + " where 学生ID='" + student.getId()
				+ "'" + " && 岗位ID='" + recruitId + "'";

		List result = recruitSubmitDao.executeOurSql(sql);

		if (result == null || result.size() == 0) {
			applyStatus = "noApply";
		} else applyStatus = "applied";
		
		
		return "getRecruitmentsById";
	}	

	/*获取所有工作岗位标签*/
	public void getRecruitmentLableByAjax(){
		
		lables = lableService.findLablesByType(2);
		
		ResponseUtil.write(response, JSONObject.toJSONString(lables,SerializerFeature.DisableCircularReferenceDetect));
		
	}
	
	/*获取工作岗位By标签名*/
	public void getRecruitmentsByLable(){
		PageBean pageBean = new PageBean(AjaxPage, 5);
		
		lableName = lableName==null?"":lableName;
		recruitmentInfos = recruitmentInfoService.findRecruitmentInfosByLable(lableName,pageBean);
		
		ResponseUtil.write(response, JSONObject.toJSONString(recruitmentInfos,SerializerFeature.DisableCircularReferenceDetect));
	}
	
	public String applyRecruit(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(new Date());
		Integer recruitSubmitId = null;
		try {
			String select_sql = "select 应聘ID from 实习生招聘资讯应聘表 where 学生ID="+student.getId()+" and 岗位ID="+recruitId;
			List result = recruitSubmitDao.executeOurSql(select_sql);
			
			if(result == null || result.size()==0){
				String sql = "insert into 实习生招聘资讯应聘表  (学生ID,岗位ID,微信用户的openId,学生姓名,邮箱,电话号码,报名时间,企业ID) "
						+ "values('"+student.getId()+"','"+recruitId+"','"+student.getOpenId()+"','"+student.getName()+"','"
						+student.getEmail()+"','"+student.getTelephone()+"','"+today+"','"+companyId+"'"
						+")";
				
				recruitSubmitDao.executeSql(sql);
				
				select_sql = "select 应聘ID from 实习生招聘资讯应聘表 where 学生ID="+student.getId()+" and 岗位ID="+recruitId;
				result = recruitSubmitDao.executeOurSql(select_sql);
				
				recruitSubmitId = (Integer) result.get(0);					
			}
	
			
		} catch (Exception e) {
			System.out.println("实习生招聘资讯应聘表,插入异常");
			e.printStackTrace();
		}

		judgeIsExistRIBussinessResume(recruitSubmitId);
		
		return "applyRecruitSuccess";
	}
	
	public void judgeIsExistRIBussinessResume(Integer recruitId){
		
		String sql = "select 简历ID from 实习生招聘学生简历  where sid="+student.getId();
		
		List recruitSubmit = recruitSubmitDao.executeOurSql(sql);
		
		if(recruitSubmit == null || recruitSubmit.size()==0){
			
			String toEmail = student.getEmail();
//			sendEmailService.sendEmailByHtml(toEmail,"ml_21233qq@163.com","实习生招聘资讯应聘简历系统",
//					"<a href='http://zhuobinchan.tunnel.2bdata.com/shixiya/submitResume_showSubmitResume?id="+recruitId+"'>实习生招聘资讯应聘简历系统</a>");
			SendEmailUtil.sendEmailByHtml(toEmail,"实习生招聘资讯应聘简历系统",
				"<a href='http://sxy.000861.com/shixiya/submitResume_showSubmitResume?id="+recruitId+"'>实习生招聘资讯应聘简历系统</a>");
		
		
		}
		
	}
	
	
	
	
	
	public List<RecruitmentInfo> getRecruitmentInfos() {
		return recruitmentInfos;
	}

	public void setRecruitmentInfos(List<RecruitmentInfo> recruitmentInfos) {
		this.recruitmentInfos = recruitmentInfos;
	}

	public RecruitmentInfo getRecruitmentInfo() {
		return recruitmentInfo;
	}

	public void setRecruitmentInfo(RecruitmentInfo recruitmentInfo) {
		this.recruitmentInfo = recruitmentInfo;
	}

	public Integer getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(Integer recruitId) {
		this.recruitId = recruitId;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAjaxPage() {
		return AjaxPage;
	}

	public void setAjaxPage(Integer ajaxPage) {
		AjaxPage = ajaxPage;
	}
	
	
}
