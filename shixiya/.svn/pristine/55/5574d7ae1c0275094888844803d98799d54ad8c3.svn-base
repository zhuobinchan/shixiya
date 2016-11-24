package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.TrainWEComment;

public interface TrainWECommentService {
	@Transactional
	public List<TrainWEComment> getAllComments(HttpServletRequest request,
			String page);

	@Transactional
	public void deleteCommentById(Integer id);

	@Transactional
	public void deleteComments(String ids);

	@Transactional
	public List<TrainWEComment> getCommentsByCondition(String trainWEName,
			String commentContent, String commentType,
			HttpServletRequest request, String page);
}
