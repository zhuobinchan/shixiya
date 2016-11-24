package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelectAdv;
import com.ehighsun.shixiya.pojo.TrainWEAdv;
import com.ehighsun.shixiya.service.PreferenceSelectAdvService;

@Service("preferenceSelectAdvService")
public class PreferenceSelectAdvServiceImpl implements
		PreferenceSelectAdvService {

	@Resource(name = "baseDao")
	private BaseDao<PreferenceSelectAdv> baseDao;

	@Override
	public List<PreferenceSelectAdv> findAdv(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public void addAdvertisement(PreferenceSelectAdv preferenceSelectAdv) {
		// TODO Auto-generated method stub
		baseDao.save(preferenceSelectAdv);
	}

	@Override
	public PreferenceSelectAdv findAdvertisementById(Integer id) {
		String hql = "from PreferenceSelectAdv where id = ?";
		List<PreferenceSelectAdv> lists = baseDao.find(hql, new Object[] { id });
		if (lists != null && lists.size() == 1) {
			return lists.get(0);
		}
		return null;
	}

	@Override
	public void deleteAdvertisement(PreferenceSelectAdv preferenceSelectAdv) {
		// TODO Auto-generated method stub
		baseDao.delete(preferenceSelectAdv);
	}

	@Override
	public List<PreferenceSelectAdv> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countAdvs(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public void saveAdvs(PreferenceSelectAdv preferenceSelectAdv) {
		// TODO Auto-generated method stub
		baseDao.update(preferenceSelectAdv);
	}

	@Override
	public List<PreferenceSelectAdv> getAllAdvs() {
		// TODO Auto-generated method stub
		return baseDao.find("from PreferenceSelectAdv where status = 1");
	}

}
