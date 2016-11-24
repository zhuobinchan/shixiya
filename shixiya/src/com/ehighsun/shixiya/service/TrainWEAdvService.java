package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEAdv;

public interface TrainWEAdvService {
	@Transactional
	public List<TrainWEAdv> findAdv(String hql, Object[] param,
			PageBean pageBean);

	@Transactional
	public List<TrainWEAdv> getAllAdvs();

	@Transactional
	public void addAdvertisement(TrainWEAdv trainWEAdv);

	@Transactional
	public TrainWEAdv findAdvertisementById(Integer id);

	@Transactional
	public void deleteAdvertisement(TrainWEAdv trainWEAdv);

	@Transactional
	public List<TrainWEAdv> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean);

	@Transactional
	public Long countAdvs(String hql);

	@Transactional
	public void saveAdvs(TrainWEAdv TrainWEAdv);
}
