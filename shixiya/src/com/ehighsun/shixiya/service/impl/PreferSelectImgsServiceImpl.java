package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferSelectImgs;
import com.ehighsun.shixiya.service.PreferSelectImgsService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("preferSelectImgsServiceImpl")
public class PreferSelectImgsServiceImpl implements PreferSelectImgsService {

	@Resource(name = "baseDao")
	private BaseDao<PreferSelectImgs> baseDao;

	@Override
	public List<PreferSelectImgs> findImgs(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public List<PreferSelectImgs> findImgs(String hql,Object[] param) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param);
	}
	
	@Override
	public List<PreferSelectImgs> getAllImgs() {
		// TODO Auto-generated method stub
		return baseDao.find("from PreferSelectImgs");
	}

	@Override
	public void addImg(PreferSelectImgs preferSelectImgs) {
		// TODO Auto-generated method stub
		baseDao.save(preferSelectImgs);
	}

	@Override
	public PreferSelectImgs findPreferSelectImgsById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(PreferSelectImgs.class, id);
	}

	@Override
	public void deletePreferSelectImg(Integer id) {
		// TODO Auto-generated method stub

		PreferSelectImgs img = this.findPreferSelectImgsById(id);
		if (img != null) {
			if (StringUtil.isNotEmpty(img.getUrl())) {
				HttpServletRequest request = ServletActionContext.getRequest();
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				File file = new File(path, img.getUrl());
				file.delete();
			}
			baseDao.delete(img);
		}
	}

	@Override
	public List<PreferSelectImgs> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countImgs(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public void updateImgs(PreferSelectImgs preferSelectImgs) {
		// TODO Auto-generated method stub
		baseDao.merge(preferSelectImgs);
	}

	@Override
	public void deletePreferSelectImgs(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			this.deletePreferSelectImg(Integer.parseInt(ids[i]));
		}
	}

	@Override
	public Long countAdvs(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, map);
	}

}
