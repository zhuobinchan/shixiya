package com.ehighsun.shixiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductQuestion;
import com.ehighsun.shixiya.pojo.CProductQuestionList;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductQuestionListService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("cProductQuestionListService")
public class CProductQuestionListServiceImpl implements
		CProductQuestionListService {

	@Resource(name = "baseDao")
	private BaseDao<CProductQuestionList> baseDao;

	@Resource(name = "baseDao")
	private BaseDao<CProductQuestion> cpQuestionDao;

	@Override
	public void saveOrUpdate(CProductQuestionList cProductQuestionList) {
		baseDao.saveOrUpdate(cProductQuestionList);
	}

	@Override
	public List<CProductQuestionList> getAllQuestionLists() {
		return baseDao.find("from CProductQuestionList");
	}

	@Override
	public void deleteQuestionList(Integer cpQustionListId) {
		baseDao.delete(baseDao.get(CProductQuestionList.class, cpQustionListId));
	}

	@Override
	public void deleteQuestionLists(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			System.out.println(string);
			baseDao.delete(baseDao.get(CProductQuestionList.class,
					Integer.parseInt(string)));
		}

	}

	@Override
	public List<CProductQuestionList> getQuestionListsByCondition(
			String content, Integer CProductQuestionId, HttpServletRequest request, String page) {
		
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		Long count = baseDao.countByMap(
				"select count(*) from CProductQuestionList",
				getMapByQuestion(content, CProductQuestionId));

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getCPQustionListByCondition_AdminCProductQuestionListAction.action", count,
				Integer.parseInt(page), 10, "questionListTitle=" + content
						+ "&cpQuestionId="
						+ (CProductQuestionId == null ? "" : CProductQuestionId));

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);
		
		
		
		return baseDao.findPageByMap("from CProductQuestionList",
				getMapByQuestion(content, CProductQuestionId),pageBean);
	}

	// 获取
	private Map<String, Object> getMapByQuestion(String content,
			Integer CProductQuestionId) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtil.isNotEmpty(content)) {
			System.out.println(content + "//////////");
			map.put("title", content);
		}
		if (StringUtil.isNotEmpty(CProductQuestionId)) {
			System.out.println(CProductQuestionId + "//////////");

			map.put("CProductQuestion", cpQuestionDao.get(
					CProductQuestion.class, CProductQuestionId));
		}

		return map;
	}

	@Override
	public List<CProductQuestionList> getAllQuestionLists(PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find("from CProductQuestionList", new Object[] {},
				pageBean);
	}
}
