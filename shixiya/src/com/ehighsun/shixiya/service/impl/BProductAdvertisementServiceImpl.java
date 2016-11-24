package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BProductAdvertisementDao;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductAdvertisementService;
@Service("bProductAdvertisementService")
public class BProductAdvertisementServiceImpl implements BProductAdvertisementService {

	@Autowired
	private BProductAdvertisementDao advertisementDao;
	
	@Override
	public void addAdvertisement(BProductAdvertisement bProductAdvertisement) {
			advertisementDao.addBAdvertisement(bProductAdvertisement);
	}

	@Override
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement) {
		
		advertisementDao.delete(bProductAdvertisement);
	}


	public List<BProductAdvertisement> findAdvertisement(Integer id) {
		
		return advertisementDao.findADvertisment(id);
	}
	
	public List<BProductAdvertisement> findAllAdvertisement() {
		
		return advertisementDao.findAllADvertisment();
	}

	@Override
	public BProductAdvertisement findAdvertisementById(Integer id) {
		// TODO Auto-generated method stub
		return advertisementDao.findADvertismentById(id);
	}

	@Override
	public List<BProductAdvertisement> findAdv(String hql, Object[] param,
			PageBean pageBean) {
		
		return advertisementDao.find(hql, param, pageBean);
	}

	@Override
	public Long countAdvs(String hql) {
		// TODO Auto-generated method stub
		return advertisementDao.count(hql);
	}

	@Override
	public List<BProductAdvertisement> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		
		return advertisementDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public void saveAdvs(BProductAdvertisement bProductAdvertisement) {
		// TODO Auto-generated method stub
		advertisementDao.update(bProductAdvertisement);
	}

	@Override
	public List<BProductAdvertisement> findAllAdvertisementByState() {
	
		return advertisementDao.find("from BProductAdvertisement where status = ?", new Object[]{1});
	}

	
	
}
