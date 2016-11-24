package com.ehighsun.shixiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.CProductHRService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("cProductHRService")
public class CProductHRServiceImpl implements CProductHRService {

	@Autowired
	private BaseDao<CProductHR> baseDao;

	@Override
	public void addOrUpdate(CProductHR cProductHR) {
		// TODO Auto-generated method stub
		baseDao.merge(cProductHR);
	}

	@Override
	public List<CProductHR> findCProductHR(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countCProductHR(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<CProductHR> findPageByMap(String hql, Map<String, Object> map,
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
	public void deleteCProductHR(CProductHR cProductHR) {
		// TODO Auto-generated method stub
		baseDao.delete(cProductHR);
	}

	@Override
	public CProductHR findHRById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get("from CProductHR where hrId = ?",
				new Object[] { id });
	}

	@Override
	public boolean cpHrLogin(String telephone, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(telephone)) {
			map.put("telephone", telephone);
		}
		if (StringUtil.isEmpty(telephone)) {
			return false;
		}
		if (StringUtil.isNotEmpty(password)) {
			password = StringUtil.md5(password);
			map.put("password", password);
		}
		if (StringUtil.isEmpty(password)) {
			return false;
		}
		return baseDao.findByMap("from CProductHR", map).size() == 1;
	}

	@Override
	public CProductHR findByTelephone(String telephone) {

		return baseDao.find("from CProductHR where telephone = ?",
				new Object[] { telephone }).get(0);
	}

	@Override
	public List<CProductHR> getAllCProductHRs() {
		return baseDao.find("from CProductHR");
	}

}
