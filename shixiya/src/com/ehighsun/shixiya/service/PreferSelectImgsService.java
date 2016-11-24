package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferSelectImgs;

public interface PreferSelectImgsService {
	@Transactional
	public List<PreferSelectImgs> findImgs(String hql, Object[] param,
			PageBean pageBean);

	@Transactional
	public List<PreferSelectImgs> findImgs(String hql, Object[] param);

	@Transactional
	public List<PreferSelectImgs> getAllImgs();

	@Transactional
	public void addImg(PreferSelectImgs preferSelectImgs);

	@Transactional
	public PreferSelectImgs findPreferSelectImgsById(Integer id);

	@Transactional
	public void deletePreferSelectImg(Integer id);

	@Transactional
	public void deletePreferSelectImgs(String[] ids);

	@Transactional
	public List<PreferSelectImgs> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean);

	@Transactional
	public Long countImgs(String hql);

	@Transactional
	public void updateImgs(PreferSelectImgs preferSelectImgs);

	@Transactional
	public Long countAdvs(String hal, Map<String, Object> map);

}
