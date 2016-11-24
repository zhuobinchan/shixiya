package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductCommentService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("bProductCommentService")
public class BProductCommentServiceImpl implements BProductCommentService {

	
	@Resource(name = "baseDao")
	private BaseDao<BProductComment> baseDao;
	
	@Override
	public List<BProductComment> getAllComments() {
		// TODO Auto-generated method stub
		return baseDao.find("from BProductComment");
	}

	@Override
	public List<BProductComment> getAllComments(PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find("from BProductComment",new Object[] {}, pageBean);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		BProductComment comment = baseDao.get(BProductComment.class, commentId);
		if(comment!=null){
			baseDao.delete(baseDao.get(BProductComment.class, commentId));	
		}
		
	}

	@Override
	public void deleteCommentList(String ids) {
		// TODO Auto-generated method stub
		
		String[] idList = ids.split(",");
		for (String string : idList) {
			BProductComment comment = baseDao.get(BProductComment.class,Integer.parseInt(string));
			if(comment!=null){
				baseDao.delete(comment);
			}
		}
	}

	@Override
	public Long countComment(String hql) {
		// TODO Auto-generated method stub
		 return baseDao.count(hql);
	}

	@Override
	public List<BProductComment> getCommentsByCondition(String commentContent, Integer videoId,
			Integer commentType, HttpServletRequest request, String page) {
		// TODO Auto-generated method stub
		StringBuffer hqlBuffer = new StringBuffer();

		hqlBuffer.append("from BProductComment where content like '%"
				+ (StringUtil.isEmpty(commentContent) ? "" : commentContent)
				+ "%'");

		if (videoId!=null) {
			
		
				hqlBuffer.append(" and BProductVideo.id = " + videoId);
		

		}

		if (commentType!=null) {
			hqlBuffer.append(" and type = "+commentType);
		}
		System.out.println(hqlBuffer.toString());

		// 以下为分页功能
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		Long count = baseDao.count("select count(*) "+hqlBuffer.toString());

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/bComment_findCommentByCondition.action",
						count, Integer.parseInt(page), 10, "commentContent="+ commentContent 
						+ "&videoId="+ videoId 
						+ "&commentType="+ commentType);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.find(hqlBuffer.toString(),new Object[]{},pageBean);
	}

}
