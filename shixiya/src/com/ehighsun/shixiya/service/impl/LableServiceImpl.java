package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.LableService;

@Service("lableService")
public class LableServiceImpl implements LableService {

	@Resource(name = "baseDao")
	private BaseDao<Lable> baseDao;

	@Override
	public List<Lable> findLable() {

		return baseDao.find("from Lable");
	}

	@Override
	public Lable findLableById(Integer id) {

		List<Lable> list = baseDao.find("from Lable where lableId = " + id);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;

	}

	@Override
	public List<Lable> findLable(String hql, Object[] param, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public void addLable(Lable lable) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(lable);
	}

	@Override
	public void deleteLable(Lable lable) {
		// TODO Auto-generated method stub
		baseDao.delete(lable);
	}

	@Override
	public List<Lable> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countLable(String hql, Object[] o) {
		// TODO Auto-generated method stub
		return baseDao.count(hql, o);
	}

	@Override
	public void saveLable(Lable Lable) {
		// TODO Auto-generated method stub
		baseDao.update(Lable);
	}

	@Override
	public List<Lable> findLablesByType(Integer type) {
		return baseDao.find("from Lable where type = " + type);
	}

}
