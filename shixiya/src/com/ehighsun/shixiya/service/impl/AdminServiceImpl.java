package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BaseDao<Administer> baseDao;

	@Override
	public void addOrUpdate(Administer administer) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(administer);
	}

	@Override
	public List<Administer> findAdminister(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countAdminister(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<Administer> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, map);
	}

	@Override
	public void deleteAdminister(Administer administer) {
		// TODO Auto-generated method stub
			baseDao.delete(administer);
	}

	@Override
	public Administer findAdminById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get("from Administer where id = ?", new Object[]{id});
		
	}

	@Override
	public Administer findAdminByName(String name) {
		
		return baseDao.get("from Administer where name = ?",new Object[]{name});
	}

	

}
