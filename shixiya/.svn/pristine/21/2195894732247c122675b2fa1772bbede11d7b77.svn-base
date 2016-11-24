package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;

public interface PreferenceSelectEnteredService {

	@Transactional
	public void addPreferenceSelectEntered(PreferenceSelectEntered pse);

	@Transactional
	public void deletePreferenceSelectEntered(Integer id);

	@Transactional
	public void deletePreferenceSelectEntereds(String ids);

	@Transactional
	public void updatePreferenceSelectEntered(PreferenceSelectEntered pse);

	@Transactional
	public PreferenceSelectEntered getPreferenceSelectEnteredById(Integer id);

	@Transactional
	public List<PreferenceSelectEntered> findPreferenceSelectEntereds(
			String hql, Object[] param, PageBean pageBean);

	@Transactional
	public Long countPreferenceSelectEntered(String hql);

	@Transactional
	public List<PreferenceSelectEntered> findByCondition(String hql,
			Map<String, Object> o, PageBean pageBean);

	@Transactional
	public Long countByCondition(String hql, Map<String, Object> o);

	@Transactional
	public List<PreferenceSelectEntered> findByPreferenceSelectId(Integer id);

	@Transactional
	public List<PreferenceSelectEntered> findByNoSendEmail(Integer id);

	@Transactional
	public List<PreferenceSelectEntered> findByPSelectId(Integer id);

}
