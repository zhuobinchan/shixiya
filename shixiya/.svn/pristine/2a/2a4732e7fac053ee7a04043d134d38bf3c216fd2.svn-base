package com.ehighsun.shixiya.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.DicInfo;
import com.ehighsun.shixiya.service.DicInfoService;

@Service("dicInfoService")
public class DicInfoServiceImpl implements DicInfoService{

	
	@Resource(name = "baseDao")
	private BaseDao<DicInfo> baseDao;
	
	@Override
	public DicInfo findDicInfoByCode(String code) {
		// TODO Auto-generated method stub
		return baseDao.get("from DicInfo where code = ?", new Object[]{code});
	}

}
