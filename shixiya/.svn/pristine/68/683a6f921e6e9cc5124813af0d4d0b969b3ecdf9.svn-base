package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelectAdv;

public interface PreferenceSelectAdvService {
	@Transactional
	public List<PreferenceSelectAdv> findAdv(String hql, Object[] param,
			PageBean pageBean);

	@Transactional
	public List<PreferenceSelectAdv> getAllAdvs();

	@Transactional
	public void addAdvertisement(PreferenceSelectAdv preferenceSelectAdv);

	@Transactional
	public PreferenceSelectAdv findAdvertisementById(Integer id);

	@Transactional
	public void deleteAdvertisement(PreferenceSelectAdv preferenceSelectAdv);

	@Transactional
	public List<PreferenceSelectAdv> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean);

	@Transactional
	public Long countAdvs(String hql);

	@Transactional
	public void saveAdvs(PreferenceSelectAdv preferenceSelectAdv);
}
