package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductCommentService {

	@Transactional
	public List<BProductComment> getAllComments();

	@Transactional
	public List<BProductComment> getAllComments(PageBean pageBean);

	@Transactional
	public void deleteComment(Integer commentId);

	@Transactional
	public void deleteCommentList(String ids);

	@Transactional
	public Long countComment(String hql);

	@Transactional
	public List<BProductComment> getCommentsByCondition(String commentContent, Integer videoId,
			Integer commentType, HttpServletRequest request, String page);

	
}
