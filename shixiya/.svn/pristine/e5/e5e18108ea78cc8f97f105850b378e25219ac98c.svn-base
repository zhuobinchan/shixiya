package com.ehighsun.shixiya.administer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductChapterService;
import com.ehighsun.shixiya.service.BProductCommentService;
import com.ehighsun.shixiya.service.BProductVideoService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;

public class AdminBProductComment extends BaseAction<BProductComment>{
	
	private String page;
	private String ids;
	private Integer chapterId;
	
	private Integer commentType;
	private Integer videoId;
	private String commentContent;

	
	
	public Integer getCommentType() {
		return commentType;
	}
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
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
	
	
	@Resource(name="bProductCommentService")
	private BProductCommentService bProductCommentService;
	@Resource(name="bProductChapterService")
	private BProductChapterService bChapterService;
	@Resource(name="bProductVideoService")
	private BProductVideoService bProductVideoService;
	
	public String showComment(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "BProductComment.jsp");
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		List<BProductComment> list = bProductCommentService.getAllComments(pageBean);
		Long count = bProductCommentService.countComment("select count(*) from BProductComment");
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/bComment_showComment.action", count, Integer.parseInt(page),6,null);
		List<BProductChapter> chapters = bChapterService.findChapters();
		
	
		this.getSession().setAttribute("pageCode", pageCode);
		this.getSession().setAttribute("comments", list);
		this.getSession().setAttribute("chapters", chapters);
	
		return "showComment";
	}

	
	public String deleteComment(){
		
		bProductCommentService.deleteComment(model.getId());
		
		return "deleteComment";
	}
	
	public String deleteComments(){
		
		bProductCommentService.deleteCommentList(ids);
		
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "deleteComments";
	}
	
	public String findVideo() throws IOException{
		
		System.out.println("////123nldnsndl");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		List<BProductVideo> list = bProductVideoService.findByChapterId(chapterId);
		
		
		StringBuilder sb  =  new StringBuilder("[");
		for (BProductVideo video : list) {
		
			sb.append("{'name':"+"'"+video.getTitle()+"'");
			sb.append(",'id':"+"'"+video.getId()+"'");
			sb.append("},");
		}
		
		int l = sb.length();
		String str = sb.substring(0, l-1);
		str+="]";
		System.out.println(str);
		
		
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();
		out.println(str);  
	    out.flush();  
	    out.close();
		return null;
	}
	
	public String findCommentByCondition(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "BProductComment.jsp");
		
		List<BProductComment> list = bProductCommentService.getCommentsByCondition(commentContent,videoId
				,commentType,request,page);
		
		request.setAttribute("comments", list);
		
		return "findCommentByCondition";
	}
	
	
}
