package com.ehighsun.shixiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductQuestionService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("cProductQuestionService")
public class CProductQuestionServiceImpl implements CProductQuestionService {
	@Resource(name = "baseDao")
	private BaseDao<CProductQuestion> cpQuestionDao;

	@Override
	public List<CProductQuestion> getAllCProductQuestions() {
		return cpQuestionDao.find("from CProductQuestion");
	}

	@Override
	public void saveOrUpdateQuestion(CProductQuestion question) {
		cpQuestionDao.saveOrUpdate(question);

	}

	@Override
	public void deleteQuestion(Integer questionId) {
		CProductQuestion index = cpQuestionDao.get(CProductQuestion.class,
				questionId);
		cpQuestionDao.delete(index);

	}

	@Override
	public void deleteQuestions(String ids) {
		String[] index = ids.split(",");
		for (String string : index) {
			CProductQuestion question = cpQuestionDao.get(
					CProductQuestion.class, Integer.parseInt(string));
			cpQuestionDao.delete(question);
		}

	}

	@Override
	public List<CProductQuestion> findByCondition(CProductQuestion question,
			HttpServletRequest request, String page) {

		// 以下为分页功能
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		Long count = cpQuestionDao.countByMap("select count(*) from CProductQuestion",getMapByQuestion(question));

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/findByCondition_AdminCProductQuestionAction.action",
						count, Integer.parseInt(page), 10, 
						"question.title="+question.getTitle()
						+"&question.CProductBroadcast.id="+(question.getCProductBroadcast().getId()==null?"":question.getCProductBroadcast().getId())
						);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return cpQuestionDao.findPageByMap("from CProductQuestion",
				getMapByQuestion(question), pageBean);

	}

	// 获取
	private Map<String, Object> getMapByQuestion(CProductQuestion question) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (question != null) {

			if (question.getTitle() != null && !"".equals(question.getTitle())) {
				System.out.println(question.getTitle() + "//////////");
				map.put("title", question.getTitle());
			}
			if (question.getCProductBroadcast().getId() != null) {
				System.out.println(question.getCProductBroadcast().getId()
						+ "//////////");
				map.put("CProductBroadcast", question.getCProductBroadcast());
			}

		}
		return map;
	}

	@Override
	public List<CProductQuestion> getAllCProductQuestions(PageBean pageBean) {
		return cpQuestionDao.find("from CProductQuestion", new Object[] {},
				pageBean);
	}

}
