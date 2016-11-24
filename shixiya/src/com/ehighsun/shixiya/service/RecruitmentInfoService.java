package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;

public interface RecruitmentInfoService {

	@Transactional
	public void addRecruitmentInfo(RecruitmentInfo info);

	@Transactional
	public void deleteRecruitmentInfo(Integer id);

	@Transactional
	public void deleteRecruitmentInfos(String[] ids);

	@Transactional
	public void updateRecruitmentInfo(RecruitmentInfo info);

	@Transactional
	public RecruitmentInfo getRecruitmentInfoById(Integer id);

	@Transactional
	public List<RecruitmentInfo> findRecruitmentInfos(String hql,
			Object[] param, PageBean pageBean);

	@Transactional
	public Long countRecruitmentInfo(String hql);

	@Transactional
	public List<RecruitmentInfo> findByCondition(String hql,
			Map<String, Object> o, PageBean pageBean);

	@Transactional
	public Long countByCondition(String hql, Map<String, Object> o);

	@Transactional
	public List<RecruitmentInfo> findALLRecruitmentInfos(PageBean pageBean);

	@Transactional
	public List<RecruitmentInfo> findRecruitmentInfoByRIBussinessId(Integer id);
	
	@Transactional
	public List<RecruitmentInfo> findRecruitmentInfosByLable(String lableName,PageBean pageBean);

}
