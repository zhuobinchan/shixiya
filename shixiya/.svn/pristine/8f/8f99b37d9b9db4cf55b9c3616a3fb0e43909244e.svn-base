package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEAdv;
import com.ehighsun.shixiya.service.TrainWEAdvService;

@Service("trainWEAdvService")
public class TrainWEAdvServiceImpl implements TrainWEAdvService {

	@Resource(name = "baseDao")
	private BaseDao<TrainWEAdv> baseDao;

	@Override
	public List<TrainWEAdv> findAdv(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public void addAdvertisement(TrainWEAdv trainWEAdv) {
		// TODO Auto-generated method stub
		baseDao.save(trainWEAdv);
	}

	@Override
	public TrainWEAdv findAdvertisementById(Integer id) {
		String hql = "from TrainWEAdv where id = ?";
		List<TrainWEAdv> lists = baseDao.find(hql, new Object[] { id });
		if (lists != null && lists.size() == 1) {
			return lists.get(0);
		}
		return null;
	}

	@Override
	public void deleteAdvertisement(TrainWEAdv trainWEAdv) {
		// TODO Auto-generated method stub
		baseDao.delete(trainWEAdv);
	}

	@Override
	public List<TrainWEAdv> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countAdvs(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public void saveAdvs(TrainWEAdv TrainWEAdv) {
		// TODO Auto-generated method stub
		baseDao.update(TrainWEAdv);
	}

	@Override
	public List<TrainWEAdv> getAllAdvs() {
		// TODO Auto-generated method stub
		return baseDao.find("from TrainWEAdv where status = 1");
	}

}
