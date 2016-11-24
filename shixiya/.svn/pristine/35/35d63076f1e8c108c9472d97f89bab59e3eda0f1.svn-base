package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductCommentService {
	@Transactional
	public List<CProductComment> getCommentsByCondition(String commentContent,
			String broadcastName, Integer commentType,
			HttpServletRequest request, String page);

	@Transactional
	public List<CProductComment> getAllComments();

	@Transactional
	public List<CProductComment> getAllComments(PageBean pageBean);

	@Transactional
	public void deleteComment(Integer commentId);

	@Transactional
	public void deleteCommentList(String ids);

	@Transactional
	public Long countComment(String hql);

}
