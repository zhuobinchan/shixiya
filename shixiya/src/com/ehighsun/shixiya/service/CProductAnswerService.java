package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.CProductAnswer;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductAnswerService {
	@Transactional
	public boolean addAnswer(Integer studentId, Integer questionId,
			String answer);

	@Transactional
	public List<Integer> getAnswersByBroadcastId(Integer broadcastId);

	@Transactional
	public List<Integer> getAnswersByStudentId(Integer studentId);
	
	@Transactional
	public List<CProductAnswer> findAnswers(String hql, Object[] param,PageBean pageBean);

	@Transactional
	public Long countAnwer(String hql);
	
	@Transactional
	public void deleteAnswer(CProductAnswer answer);
	
	@Transactional
	public Long countAnwer(String hql,Map<String, Object> o);

	List<CProductAnswer> findAnswer(String hql, Map<String, Object> o,
			PageBean pageBean);
	
}
