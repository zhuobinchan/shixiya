package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;

public interface RecruitResumeSubmitSerivce {
	@Transactional
	public void addRecruitResumeSubmit(RecruitResumeSubmit rrs);

	@Transactional
	public void deleteRecruitResumeSubmit(Integer id);

	@Transactional
	public void deleteRecruitResumeSubmits(String[] ids);

	@Transactional
	public void updateRecruitResumeSubmit(RecruitResumeSubmit rrs);

	@Transactional
	public RecruitResumeSubmit getRecruitResumeSubmitById(Integer id);

	@Transactional
	public List<RecruitResumeSubmit> findRecruitResumeSubmits(String hql,
			Object[] param, PageBean pageBean);

	@Transactional
	public Long countRecruitResumeSubmit(String hql);

	@Transactional
	public List<RecruitResumeSubmit> findByCondition(String hql,
			Map<String, Object> o, PageBean pageBean);

	@Transactional
	public List<RecruitResumeSubmit> findByCondition(String hql,
			Map<String, Object> o, Map<String, String> order, PageBean pageBean);

	@Transactional
	public Long countByCondition(String hql, Map<String, Object> o);

	@Transactional
	public List<RecruitResumeSubmit> findSubmitByStudentId(Integer sid);

	@Transactional
	public List<RecruitResumeSubmit> findSubmitByRecruitInfo(Integer sid);

}
