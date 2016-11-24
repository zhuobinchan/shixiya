package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEComment;
import com.ehighsun.shixiya.service.TrainWECommentService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("trainWECommentService")
public class TrainWECommentServiceImpl implements TrainWECommentService {

	@Resource(name = "baseDao")
	private BaseDao<TrainWEComment> baseDao;

	@Override
	public List<TrainWEComment> getAllComments(HttpServletRequest request,
			String page) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = baseDao.count("select count(*) from TrainWEComment");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllTrainWEComment_AdminTrainWEComment.action",
				count, Integer.parseInt(page), 6, null);
		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.find("from TrainWEComment", new Object[] {}, pageBean);
	}

	@Override
	public void deleteCommentById(Integer id) {
		TrainWEComment index = baseDao.get(TrainWEComment.class, id);
		if (StringUtil.isNotEmpty(index.getLocalUrl())) {
			FileUploadUtil.deleteFile(index.getLocalUrl());
		}
		baseDao.evict(index);
		baseDao.delete(index);
	}

	@Override
	public void deleteComments(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			TrainWEComment index = baseDao.get(TrainWEComment.class,
					Integer.parseInt(string));
			if (StringUtil.isNotEmpty(index.getLocalUrl())) {
				FileUploadUtil.deleteFile(index.getLocalUrl());
			}
			baseDao.evict(index);
			baseDao.delete(index);
		}
	}

	@Override
	public List<TrainWEComment> getCommentsByCondition(String trainWEChildName,
			String commentContent, String commentType,
			HttpServletRequest request, String page) {

		StringBuffer hqlBuffer = new StringBuffer();
		
		hqlBuffer.append("from TrainWEComment where 评论内容 like '%"
				+ (StringUtil.isEmpty(commentContent) ? "" : commentContent)
				+ "%'");

		if (StringUtil.isNotEmpty(trainWEChildName)) {
			List<Integer> trainWEIdList = baseDao
					.executeOurSql("select 章节ID from 培训we课堂章节  where 章节名称 like '%"
							+ trainWEChildName + "%'");
			if (!trainWEIdList.isEmpty() && trainWEIdList != null) {
				StringBuffer broadcastIdStr = new StringBuffer();
				for (Integer integer : trainWEIdList) {
					broadcastIdStr.append(integer).append(",");
				}
				String resultStr = broadcastIdStr.substring(0,
						broadcastIdStr.length() - 1);
				hqlBuffer.append("and 章节ID in(" + resultStr + ")");
			}

		}

		if (StringUtil.isNotEmpty(commentType)) {
			hqlBuffer.append("and 评论类型 = '" + commentType + "'");
		}

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Long count = baseDao.count("select count(*) " + hqlBuffer.toString());

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/getByCondition_AdminTrainWEComment.action",
						count, Integer.parseInt(page), 10, "commentContent="
								+ commentContent + "&trainWEName="
								+ trainWEChildName + "&commentType="
								+ (commentType == null ? "" : commentType));
		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.find(hqlBuffer.toString(), new Object[] {}, pageBean);

	}
}
