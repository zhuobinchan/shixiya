package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductAdvService {

	@Transactional
	public List<CProductAdv> findAdv(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public void addAdvertisement(CProductAdv cProductAdv);
	@Transactional
	public CProductAdv findAdvertisementById(Integer id);
	@Transactional
	public void deleteAdvertisement(CProductAdv cProductAdv);
	@Transactional
	 public List<CProductAdv> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean);
	@Transactional
	public Long countAdvs(String hql);
	@Transactional
	public void saveAdvs(CProductAdv cProductAdv);
}
