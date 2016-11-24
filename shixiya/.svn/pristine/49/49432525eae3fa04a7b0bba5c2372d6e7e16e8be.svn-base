package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductAnswer;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductAnswerService;

@Service("cProductAnswerService")
public class CProductAnswerServiceImpl implements CProductAnswerService {

	@Resource(name = "baseDao")
	private BaseDao<CProductAnswer> baseDao;

	@Resource(name = "baseDao")
	private BaseDao<CProductQuestion> cpQuestionDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductQuestionList> cpQuestionListDao;

	@Override
	public boolean addAnswer(Integer studentId, Integer questionId,
			String answer) {
		System.out.println("insert into c组答案表 ( 答案内容, 问题ID, 学生ID ) values ('"
				+ answer + "'," + questionId + "," + studentId + ")");
		baseDao.executeSql("insert into c组答案表 ( 答案内容, 问题ID, 学生ID ) values ('"
				+ answer + "'," + questionId + "," + studentId + ")");
		return true;
	}

	@Override
	public List<Integer> getAnswersByBroadcastId(Integer broadcastId) {
		try {
			List<Integer> cpQuestion = cpQuestionDao
					.executeOurSql("select 问题ID from c组直播间问题表  where 直播间ID ="
							+ broadcastId);

			Integer cpQuestionId = cpQuestion.get(0);

			List<Integer> cpQuestionLists = cpQuestionListDao
					.executeOurSql("select 问题详细ID from c组问题项表  where 问题ID ="
							+ cpQuestionId);

			Integer cpQuestionListId = cpQuestionLists.get(0);

			List<Integer> cpAnswers = baseDao
					.executeOurSql("select 答案ID from c组答案表  where 问题ID ="
							+ cpQuestionListId);

			return cpAnswers;
		} catch (IndexOutOfBoundsException e) {
			return new ArrayList<>();
		}

	}

	@Override
	public List<Integer> getAnswersByStudentId(Integer studentId) {
		try {
			return baseDao.executeOurSql("select 答案ID from c组答案表 where 学生ID = "
					+ studentId);
		} catch (IndexOutOfBoundsException e) {
			return new ArrayList<>();
		}

	}

	@Override
	public List<CProductAnswer> findAnswers(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countAnwer(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public void deleteAnswer(CProductAnswer answer) {
		// TODO Auto-generated method stub
		 baseDao.delete(answer);
	}

	@Override
	public Long countAnwer(String hql,Map<String, Object> o) {
		
		return baseDao.countByMap(hql, o);
	}

	@Override
	public List<CProductAnswer> findAnswer(String hql, Map<String, Object> o,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, o, pageBean);
	}
	
}
