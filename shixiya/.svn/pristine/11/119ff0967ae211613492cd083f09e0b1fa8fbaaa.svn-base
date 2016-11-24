package com.ehighsun.shixiya.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelect;

public interface PreferenceSelectService {

	@Transactional
	public void addPreferenceSelect(PreferenceSelect ps);

	@Transactional
	public void deletePreferenceSelect(Integer id);

	@Transactional
	public void deletePreferenceSelects(String[] ids);

	@Transactional
	public void updatePreferenceSelect(PreferenceSelect ps);

	@Transactional
	public PreferenceSelect getPreferenceSelectById(Integer id);

	@Transactional
	public List<PreferenceSelect> findPreferenceSelects(String hql,
			List<Object> param, PageBean pageBean);

	@Transactional
	public Long countPreferenceSelect(String hql, List<Object> param);

	@Transactional
	public List<PreferenceSelect> findAllPreferenceSelect();

	@Transactional
	public void evitPreferenceSelect(PreferenceSelect preferenceSelect);

}
