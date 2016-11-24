package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.PageBean;


public interface AdminService {
	@Transactional
	public void addOrUpdate(Administer administer);
	@Transactional
	public List<Administer> findAdminister(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public Long countAdminister(String hql);
	@Transactional
	public List<Administer> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean); 
	@Transactional
	public Long countByMap(String hql,Map<String,Object> map);
	@Transactional
	public void deleteAdminister(Administer administer);
	@Transactional
	public Administer findAdminById(Integer id);
	@Transactional
	public Administer findAdminByName(String name);

	
}
