package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductAdvService;

@Service("cProductAdvService")
public class CProductAdvServiceImpl implements CProductAdvService{

	@Resource(name="baseDao")
	private BaseDao<CProductAdv> baseDao;
	

	@Override
	public List<CProductAdv> findAdv(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}


	@Override
	public Long countAdvs(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}


	@Override
	public void addAdvertisement(CProductAdv cProductAdv) {
		// TODO Auto-generated method stub
		baseDao.save(cProductAdv);
	}


	@Override
	public CProductAdv findAdvertisementById(Integer id) {
		String hql = "from CProductAdv where id = ?";
		List<CProductAdv> lists = baseDao.find(hql, new Object[]{id});
		if(lists!=null && lists.size()==1){
			return lists.get(0);
		}
		return null;
	}


	@Override
	public void deleteAdvertisement(CProductAdv cProductAdv) {
		// TODO Auto-generated method stub
		baseDao.delete(cProductAdv);
	}


	@Override
	public List<CProductAdv> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}


	@Override
	public void saveAdvs(CProductAdv cProductAdv) {
		// TODO Auto-generated method stub
		baseDao.update(cProductAdv);
	}

	
	
}
