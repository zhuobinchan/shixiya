package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.service.CProductService;

@Service("cProductService")
public class CProductServiceImpl implements CProductService {
	@Resource(name = "baseDao")
	BaseDao<CProductStudentApply> cpStudentApplyDao;
	@Resource(name = "baseDao")
	BaseDao<CProductBroadcast> cpBroadcastDao;

	@Override
	public List<CProductBroadcast> findByStduentId(String studentTelephone) {
		List<Integer> allBroadcastId = cpStudentApplyDao
				.executeOurSql("select 直播id from c组团报名表 where 手机号码 ='"
						+ studentTelephone + "'");
		List<CProductBroadcast> result = new ArrayList<>();
		for (Integer integer : allBroadcastId) {
			result.add(cpBroadcastDao.get(CProductBroadcast.class, integer));
		}
		return result;

	}
}
