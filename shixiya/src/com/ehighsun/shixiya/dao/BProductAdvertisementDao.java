package com.ehighsun.shixiya.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;

public interface BProductAdvertisementDao extends BaseDao<BProductAdvertisement>{
	@Transactional
	public void addBAdvertisement(BProductAdvertisement bProductAdvertisement);
	@Transactional
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement);
	@Transactional
	public List<BProductAdvertisement> findADvertisment(Integer courseId);
	@Transactional
	public List<BProductAdvertisement> findAllADvertisment();
	@Transactional
	public BProductAdvertisement findADvertismentById(Integer Id);
}
