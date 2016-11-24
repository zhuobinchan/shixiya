package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductAnswer;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.service.HrCheckAnswerService;
import com.ehighsun.shixiya.websocket.HrCheckAnswer;

@Service("hrCheckAnswerService")
public class HrCheckAnswerServiceImpl implements HrCheckAnswerService {

	@Resource(name = "baseDao")
	private BaseDao<CProductBroadcast> cpBroadcastDao;

	@Resource(name = "baseDao")
	private BaseDao<CProductQuestion> cpQuestionDao;

	@Resource(name = "baseDao")
	private BaseDao<CProductAnswer> cpAnswerDao;

	@Override
	public List<HrCheckAnswer> getHrCheckAnswer(Integer broadcastId) {
		List<HrCheckAnswer> hrCheckAnswers = new ArrayList<>();

		CProductBroadcast broadcast = cpBroadcastDao.get(
				CProductBroadcast.class, broadcastId);
		try {
			Set<CProductStudentApply> cpStudentApplies = broadcast
					.getCProductStudentApplies();
			System.out.println(111 + "\\\\\\\\\\\\\\\\\\\\");
			for (CProductStudentApply cProductStudentApply : cpStudentApplies) {// 填写学生的信息

				if (cProductStudentApply.getStatus() == 1) {
					HrCheckAnswer hrCheckAnswer = new HrCheckAnswer();
					hrCheckAnswer.setName(cProductStudentApply.getStudent()
							.getName());
					hrCheckAnswer.setHeadImg(cProductStudentApply.getStudent()
							.getHeadUrl());
					hrCheckAnswer.setStudentId(cProductStudentApply
							.getStudent().getId());
					hrCheckAnswers.add(hrCheckAnswer);
				}
			}
			System.out.println(222 + "\\\\\\\\\\\\\\\\\\\\");
			Set<CProductQuestion> cpQuestion = broadcast.getCProductQuestions();
			Set<CProductQuestionList> cpQuestionLists = new HashSet<>();
			String questionTitle = new String();
			for (CProductQuestion cProductQuestion : cpQuestion) {// 获取问卷列表
				questionTitle = cProductQuestion.getTitle();
				cpQuestionLists = cProductQuestion.getCProductQuestionLists();
				break;
			}
			System.out.println(333 + "\\\\\\\\\\\\\\\\\\\\");
			List<Map<Integer, String>> cpQuestionList = new ArrayList<>();// 获取问题列表
			for (CProductQuestionList cProductQuestionList : cpQuestionLists) {
				Map<Integer, String> questionMap = new HashMap<Integer, String>();
				questionMap.put(cProductQuestionList.getId(),
						cProductQuestionList.getTitle());
				cpQuestionList.add(questionMap);
			}
			System.out.println(444 + "\\\\\\\\\\\\\\\\\\\\");

			Iterator<HrCheckAnswer> listIterator = hrCheckAnswers.iterator();
			outer: while (listIterator.hasNext()) {
				HrCheckAnswer hrCheckAnswer = listIterator.next();
				hrCheckAnswer.setQuestionTitle(questionTitle);
				Integer studentId = hrCheckAnswer.getStudentId();
				Map<Map<Integer, String>, String> answerMap = new HashMap<>();

				for (Map<Integer, String> questionMap : cpQuestionList) {

					for (Map.Entry<Integer, String> map : questionMap
							.entrySet()) {
						Integer questionId = map.getKey();

						List<String> studentAnswerList = cpAnswerDao
								.executeOurSql("select 答案内容  from c组答案表 where 问题ID = "
										+ questionId
										+ " and 学生ID = "
										+ studentId + ";");

						if (studentAnswerList.size() == 0) {// 判断是否有进去答题，如果没有则把这位学生移除（PS：提交空卷也算有答题）
							listIterator.remove();
							continue outer;
						}

						String answer = studentAnswerList.get(0);
						Map<Integer, String> mapKey = new HashMap<>();
						mapKey.put(map.getKey(), map.getValue());
						answerMap.put(mapKey, answer);
						System.out.println(map.getKey() + ":" + map.getValue());

					}

				}
				hrCheckAnswer.setAnswerMap(answerMap);
			}
			return hrCheckAnswers;

		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
}
