package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductEmploy;
import com.ehighsun.shixiya.service.CProductEmployService;

@Service("cProductEmployService")
public class CProductEmployServiceImpl implements CProductEmployService {

	@Resource(name = "baseDao")
	private BaseDao<CProductEmploy> baseDao;

	@Override
	public void saveOrUpdateEmploy(CProductEmploy employ) {
		baseDao.saveOrUpdate(employ);
	}

	@Override
	public long countEmployByBroadcast(CProductBroadcast broadcast) {

		return baseDao.find("from CProductEmploy where 直播间ID = ?",
				new Object[] { broadcast.getId() }).size();
	}

	@Override
	public List<CProductEmploy> getCProductEmploysByBroadcast(
			CProductBroadcast broadcast) {

		return baseDao.find("from CProductEmploy where 直播间ID = ?",
				new Object[] { broadcast.getId() });
	}

	@Override
	public void deleteEmploys(String employIds) {
		baseDao.executeSql("DELETE FROM `c组录用表` WHERE `录用ID` IN ("+employIds+")");
	}

}
