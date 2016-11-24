package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductCommentService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("cProductCommentService")
public class CProductCommentServiceImpl implements CProductCommentService {

	@Resource(name = "baseDao")
	private BaseDao<CProductComment> baseDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductBroadcast> cpBroadcaseDao;

	@Override
	public List<CProductComment> getCommentsByCondition(String commentContent,
			String broadcastName, Integer commentType,
			HttpServletRequest request, String page) {
		StringBuffer hqlBuffer = new StringBuffer();

		hqlBuffer.append("from CProductComment where 评论内容 like '%"
				+ (StringUtil.isEmpty(commentContent) ? "" : commentContent)
				+ "%'");

		if (StringUtil.isNotEmpty(broadcastName)) {
			List<Integer> broadcastIdList = cpBroadcaseDao
					.executeOurSql("select 直播间ID from c组团直播间管理 where 直播间名称 like '%"
							+ broadcastName + "%'");
			if (!broadcastIdList.isEmpty() && broadcastIdList != null) {
				StringBuffer broadcastIdStr = new StringBuffer();
				for (Integer integer : broadcastIdList) {
					broadcastIdStr.append(integer).append(",");
				}
				String resultStr = broadcastIdStr.substring(0,
						broadcastIdStr.length() - 1);
				hqlBuffer.append("and 直播间ID in(" + resultStr + ")");
			}

		}

		if (StringUtil.isNotEmpty(commentType)) {
			hqlBuffer.append("and 评论类型 = '" + commentType + "'");
		}
		System.out.println(hqlBuffer.toString());

		// 以下为分页功能
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

		Long count = baseDao.count("select count(*) "+hqlBuffer.toString());

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/getCommentsByCondition_AdminCProductCommentAction.action",
						count, Integer.parseInt(page), 10, "commentContent="+ commentContent 
						+ "&broadcastName="+ broadcastName 
						+ "&commentType="+ (commentType == null ? "" : commentType));

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.find(hqlBuffer.toString(),new Object[]{},pageBean);

	}

	@Override
	public List<CProductComment> getAllComments() {

		return baseDao.find("from CProductComment");
	}

	@Override
	public List<CProductComment> getAllComments(PageBean pageBean) {

		return baseDao.find("from CProductComment", new Object[] {}, pageBean);
	}

	@Override
	public void deleteComment(Integer commentId) {
		baseDao.delete(baseDao.get(CProductComment.class, commentId));

	}

	@Override
	public void deleteCommentList(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			System.out.println(string);
			baseDao.delete(baseDao.get(CProductComment.class,
					Integer.parseInt(string)));
		}

	}

	@Override
	public Long countComment(String hql) {

		return baseDao.count(hql);
	}

}
