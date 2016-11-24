package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.service.RecruitResumeSubmitSerivce;
import com.ehighsun.shixiya.service.RecruitmentInfoService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("recruitmentInfoServiceImpl")
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

	@Resource(name = "baseDao")
	private BaseDao<RecruitmentInfo> baseDao;
	@Resource(name = "recruitResumeSubmitSerivceImpl")
	private RecruitResumeSubmitSerivce recruitResumeSubmitSerivce;

	@Override
	public void addRecruitmentInfo(RecruitmentInfo info) {
		// TODO Auto-generated method stub
		baseDao.save(info);
	}

	@Override
	public void deleteRecruitmentInfo(Integer id) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		RecruitmentInfo info = baseDao.get(RecruitmentInfo.class, id);

		if (info != null) {
			List<RecruitResumeSubmit> submits = recruitResumeSubmitSerivce
					.findSubmitByRecruitInfo(info.getId());
			if (submits != null) {
				for (int i = 0; i < submits.size(); i++) {
					recruitResumeSubmitSerivce
							.deleteRecruitResumeSubmit(submits.get(i).getId());
				}
			}

		}

		if (info != null) {
			if (StringUtil.isNotEmpty(info.getLogoUrl())) {
				File file = new File(path, info.getLogoUrl());
				file.delete();
			}
			baseDao.delete(info);
		}

	}

	@Override
	public void deleteRecruitmentInfos(String[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			this.deleteRecruitmentInfo(Integer.parseInt(ids[i]));
		}
	}

	@Override
	public void updateRecruitmentInfo(RecruitmentInfo info) {
		// TODO Auto-generated method stub
		baseDao.merge(info);
	}

	@Override
	public RecruitmentInfo getRecruitmentInfoById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(RecruitmentInfo.class, id);
	}

	@Override
	public List<RecruitmentInfo> findRecruitmentInfos(String hql,
			Object[] param, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countRecruitmentInfo(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<RecruitmentInfo> findByCondition(String hql,
			Map<String, Object> o, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, o, pageBean);
	}

	@Override
	public Long countByCondition(String hql, Map<String, Object> o) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, o);
	}

	@Override
	public List<RecruitmentInfo> findALLRecruitmentInfos(PageBean pageBean) {

		return baseDao
				.find("from RecruitmentInfo where (checkState = 1 and stopState = 0) order by topState desc, publishTime desc",new Object[]{},pageBean);
	}

	@Override
	public List<RecruitmentInfo> findRecruitmentInfosByLable(String lableName,PageBean pageBean) {

		return baseDao
				.find("from RecruitmentInfo where (checkState = 1 and stopState = 0) and workType like '%"
						+ lableName + "%' order by publishTime desc",new Object[]{},pageBean);
	}

	@Override
	public List<RecruitmentInfo> findRecruitmentInfoByRIBussinessId(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.find("from RecruitmentInfo where RIBussiness.id = " + id
				+ " and checkState = 1");
	}

}
