package com.ehighsun.shixiya.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.RoleInfoTransform;
import com.ehighsun.shixiya.service.RoleService;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name="baseDao")
	private BaseDao<RoleInfoTransform> baseDao;

	@Override
	public RoleInfoTransform findByRoseId(String id,Integer type) {
		
		return baseDao.get("from RoleInfoTransform where roleId = ? and roleType=?", new Object[]{id,type});
		
	}

	@Override
	public void saveRoleInfo(RoleInfoTransform roleInfoTransform) {
	
		baseDao.save(roleInfoTransform);
	}
	
	
}
