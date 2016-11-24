package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductQuestionListService {
	@Transactional
	public void saveOrUpdate(CProductQuestionList cProductQuestionList);

	@Transactional
	public List<CProductQuestionList> getAllQuestionLists();

	@Transactional
	public void deleteQuestionList(Integer cpQustionListId);

	@Transactional
	public void deleteQuestionLists(String cpQustionListIds);

	@Transactional
	public List<CProductQuestionList> getQuestionListsByCondition(
			String content, Integer CProductQuestionId, HttpServletRequest request, String page);

	@Transactional
	public List<CProductQuestionList> getAllQuestionLists(PageBean pageBean);

}
