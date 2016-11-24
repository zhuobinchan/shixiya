package com.ehighsun.shixiya.hr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GetHrCommentAction extends ActionSupport {
	
	private List<HrComment> hrComments;
	private List<CProductHR> hrs;
	
	private String id;
	private String ids;
	private String hrId;
	private String title;
	private String recordType;
	private String content;
	
	private String page;
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	private String mainPage;//设置主页右边显示的页面
	
	@Resource(name="baseDao")
	private BaseDao<HrComment> hrCommentDao;
	@Resource(name="baseDao")
	private BaseDao<CProductHR> hrDao;
	@Resource(name="baseDao")
	private BaseDao<HrCommentList> hrCommentListDao;
	

	/* 获取所有Hr评论 */
	public String getAllHrComment(){
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		
		hrComments = hrCommentDao.find("From HrComment",new Object[]{},pageBean);
		hrs = hrDao.find("From CProductHR");
		
		Long count = hrCommentDao.count("select count(*) from HrComment");
		
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllHrComment_hrComment.action", count,
				Integer.parseInt(page), 6, null);
		
		request.getSession().setAttribute("pageCode", pageCode);
		
		mainPage = "hrCommentIndex.jsp";
		
		return "getAllHrComment";
	}
	
	/* 返还筛选后的Hr评论 */
	public String hrCommentFilter(){
		
		
		String hql = "From HrComment where 评论内容 like '%"+content+"%'"
				+ (hrId.equals("")?"":" and HRID='"+hrId+"' ")
				+ (recordType.equals("")?"":" and 评论类型='"+recordType+"'");
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		
		hrComments = hrCommentDao.find(hql,new Object[]{},pageBean);
		Long count = hrCommentDao.count("select count(*) "+hql);
		
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/hrCommentFilter_hrComment.action", count,
				Integer.parseInt(page), 6, "content="+content+"&hrId="+hrId+"&recordType="+recordType);
		
		request.getSession().setAttribute("pageCode", pageCode);
		
		hrs = hrDao.find("From CProductHR");
		mainPage = "hrCommentIndex.jsp";
		
		return "hrCommentFilter";
	}
	
	/* 删除单条或多条数据*/
	public String delHrComment() throws IOException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
	
		System.out.println("ids="+ids+"id="+id);
		if(ids==null && id!=null) {
			String sql = "delete from hr点评表  where 评论ID='"+id+"'";
			hrCommentDao.executeSql(sql);
		}else if(ids!=null && id==null) {
			String[] idStrs = ids.split(",");
			for(String id:idStrs){
				String sql = "delete from hr点评表  where 评论ID='"+id+"'";
				hrCommentDao.executeSql(sql);
			}
		}
		
		
		String result = "{'success':'true'}";
		out.print(result);
		
		return null;
	}
	
	
	public List<HrComment> getHrComments() {
		return hrComments;
	}

	public void setHrComments(List<HrComment> hrComments) {
		this.hrComments = hrComments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
