package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.service.RecruitResumeSubmitSerivce;

@Service("recruitResumeSubmitSerivceImpl")
public class RecruitResumeSubmitSerivceImpl implements
		RecruitResumeSubmitSerivce {

	@Resource(name = "baseDao")
	private BaseDao<RecruitResumeSubmit> baseDao;

	@Override
	public void addRecruitResumeSubmit(RecruitResumeSubmit rrs) {
		// TODO Auto-generated method stub
		baseDao.save(rrs);
	}

	@Override
	public void deleteRecruitResumeSubmit(Integer id) {
		// TODO Auto-generated method stub
		RecruitResumeSubmit rrs = baseDao.get(RecruitResumeSubmit.class, id);
		if (rrs != null) {
			baseDao.delete(rrs);
		}
	}

	@Override
	public void deleteRecruitResumeSubmits(String[] ids) {
		// TODO Auto-generated method stub

		for (int i = 0; i < ids.length; i++) {
			this.deleteRecruitResumeSubmit(Integer.parseInt(ids[i]));
		}

	}

	@Override
	public void updateRecruitResumeSubmit(RecruitResumeSubmit rrs) {
		// TODO Auto-generated method stub
		baseDao.update(rrs);
	}

	@Override
	public RecruitResumeSubmit getRecruitResumeSubmitById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(RecruitResumeSubmit.class, id);
	}

	@Override
	public List<RecruitResumeSubmit> findRecruitResumeSubmits(String hql,
			Object[] param, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countRecruitResumeSubmit(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<RecruitResumeSubmit> findByCondition(String hql,
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
	public List<RecruitResumeSubmit> findByCondition(String hql,
			Map<String, Object> o, Map<String, String> order, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, o, order, pageBean);
	}

	@Override
	public List<RecruitResumeSubmit> findSubmitByStudentId(Integer sid) {
		// TODO Auto-generated method stub
		return baseDao.find("from RecruitResumeSubmit where student.id = "
				+ sid);
	}

	@Override
	public List<RecruitResumeSubmit> findSubmitByRecruitInfo(Integer sid) {
		// TODO Auto-generated method stub
		RecruitmentInfo info = new RecruitmentInfo();
		info.setId(sid);
		return baseDao.find(
				"from RecruitResumeSubmit where recruitmentInfo = ?",
				new Object[] { info });
	}

}
