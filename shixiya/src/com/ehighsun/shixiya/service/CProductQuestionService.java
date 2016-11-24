package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductQuestionService {
	@Transactional
	public List<CProductQuestion> getAllCProductQuestions();

	@Transactional
	public List<CProductQuestion> getAllCProductQuestions(PageBean pageBean);

	@Transactional
	public void saveOrUpdateQuestion(CProductQuestion question);

	@Transactional
	public void deleteQuestion(Integer questionId);

	@Transactional
	public void deleteQuestions(String ids);

	@Transactional
	public List<CProductQuestion> findByCondition(CProductQuestion question, HttpServletRequest request, String page);

}
