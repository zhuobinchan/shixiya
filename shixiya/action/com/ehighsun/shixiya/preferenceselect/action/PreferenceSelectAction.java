package com.ehighsun.shixiya.preferenceselect.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferSelectImgs;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectAdv;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.PreferSelectImgsService;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.util.Function;

public class PreferenceSelectAction extends ActionSupport {

	private PreferenceSelect preferenceSelect;
	private List<PreferenceSelect> preferenceSelects;
	private List<PreferSelectImgs> preferenceSelectImgs;
	private List<PreferenceSelectAdv> preferenceSelectAdvs;
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	private Student student = (Student) ServletActionContext.getContext().getSession().get("student");;
	private int id;
	private String applyStatus;
	
	@Resource(name="baseDao")
	private BaseDao<PreferenceSelect> preferenceSelectDao;
	@Resource(name="baseDao")
	private BaseDao<PreferenceSelectEntered> preferenceApplyDao;
	@Resource(name="baseDao")
	private BaseDao<PreferenceSelectAdv> preferenceAdvDao;
	@Autowired
	private PreferSelectImgsService preferSelectImgsService;
	
	
	private Integer AjaxPage;
	
	public void showAll(){
		PageBean pageBean = new PageBean(AjaxPage, 5);
		preferenceSelects = preferenceSelectDao.find("from PreferenceSelect order by stick desc,id desc",new Object[]{},pageBean);
		preferenceSelectAdvs = preferenceAdvDao.find("from PreferenceSelectAdv");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("contents", preferenceSelects);
		jsonObject.put("advs",preferenceSelectAdvs);
		
//		String preferenceSelect_jsonString = JSONObject.toJSONString(preferenceSelects);
		
		try {
			ResponseUtil.write(response, jsonObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getPSByPageBean() {
		PageBean pageBean = new PageBean(AjaxPage, 5);
		preferenceSelects = preferenceSelectDao.find("from PreferenceSelect order by stick desc,id desc",new Object[]{},pageBean);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("contents", preferenceSelects);
		
		try {
			ResponseUtil.write(response, jsonObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*显示详细页面即堂外优选汇列表点击去的页面,childWeb5.jsp*/
	public String showDetail(){
		
		preferenceSelect = preferenceSelectDao.get(PreferenceSelect.class, id);
		
		String hql = "from PreferSelectImgs where preferenceSelect=?";
		
		preferenceSelectImgs = preferSelectImgsService.findImgs(hql,new Object[]{preferenceSelect});
//		student = (Student) ServletActionContext.getContext().getSession().get("student");
		
		Integer visitNum = preferenceSelect.getVisitNum();
		
		if (visitNum==null || visitNum==0) {
			visitNum = 0 ;
		}else preferenceSelect.setVisitNum(visitNum+1);
		
		preferenceSelectDao.saveOrUpdate(preferenceSelect);
		
		/*判断是否有报名，显示对应的按钮样式*/
		applyStatus = judgeApplyStatus(id,student.getId());
		
		return "showDetail";
	}
	
	/*判断是否有基本信息，没有则跳转去填写，有则直接报名*/
	public String applyPreferenceSelect(){
		
		String telephone = student.getTelephone();
		
		
		/*若有基本信息,则直接给报名表添加记录*/
		if(telephone!=null && !telephone.equals("")){

			applyStatus = judgeApplyStatus(id,student.getId());
			
			if (applyStatus.equals("noApply")) {
				PreferenceSelectEntered preferenceApply = new PreferenceSelectEntered();
				preferenceSelect = preferenceSelectDao.get(PreferenceSelect.class, id);
				preferenceApply.setStudentId(student.getId());
				preferenceApply.setName(student.getName());
				preferenceApply.setTelephone(student.getTelephone());
				preferenceApply.setEmail(student.getEmail());
				preferenceApply.setPreferenceSelect(preferenceSelect);
				preferenceApply.setEmailState(0);
				preferenceApplyDao.save(preferenceApply);
			}
			

			
			return "applySuccess";
			
		}else {
			
			return "applyFail";
		}
		
		
	}
	
	
	/*判断是否有报名*/
	public String judgeApplyStatus(int id, int studentId){
		
		String sql = "select 报名ID from 优选汇报名表  " + " where 学生ID='" + studentId
				+ "'" + " && 堂外优选ID='" + id + "'";

		List result = preferenceSelectDao.executeOurSql(sql);

		if (result == null || result.size() == 0) {
			return "noApply";
		} else {
			return "sccessApply";
		}

	}

	public List<PreferenceSelect> getPreferenceSelects() {
		return preferenceSelects;
	}

	public void setPreferenceSelects(List<PreferenceSelect> preferenceSelects) {
		this.preferenceSelects = preferenceSelects;
	}

	public PreferenceSelect getPreferenceSelect() {
		return preferenceSelect;
	}

	public void setPreferenceSelect(PreferenceSelect preferenceSelect) {
		this.preferenceSelect = preferenceSelect;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public List<PreferenceSelectAdv> getPreferenceSelectAdvs() {
		return preferenceSelectAdvs;
	}

	public void setPreferenceSelectAdvs(
			List<PreferenceSelectAdv> preferenceSelectAdvs) {
		this.preferenceSelectAdvs = preferenceSelectAdvs;
	}

	public List<PreferSelectImgs> getPreferenceSelectImgs() {
		return preferenceSelectImgs;
	}

	public void setPreferenceSelectImgs(List<PreferSelectImgs> preferenceSelectImgs) {
		this.preferenceSelectImgs = preferenceSelectImgs;
	}

	public Integer getAjaxPage() {
		return AjaxPage;
	}

	public void setAjaxPage(Integer ajaxPage) {
		AjaxPage = ajaxPage;
	}

}
