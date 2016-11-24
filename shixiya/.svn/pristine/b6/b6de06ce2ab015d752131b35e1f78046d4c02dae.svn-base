package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductAdvertisementService {
	@Transactional
	public void addAdvertisement(BProductAdvertisement bProductAdvertisement);
	@Transactional
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement);
	@Transactional
	public List<BProductAdvertisement> findAdvertisement(Integer id);
	@Transactional
	public List<BProductAdvertisement> findAllAdvertisement();
	@Transactional
	public List<BProductAdvertisement> findAllAdvertisementByState();
	@Transactional
	public BProductAdvertisement findAdvertisementById(Integer id);
	@Transactional
	List<BProductAdvertisement> findAdv(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public Long countAdvs(String hql);
	@Transactional
	 public List<BProductAdvertisement> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean);
	@Transactional
	public void saveAdvs(BProductAdvertisement bProductAdvertisement);
	
	
}
