package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.CProductAdv;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;

public interface LableService {
	
	@Transactional
	public List<Lable> findLable();
	
	@Transactional
	public Lable findLableById(Integer id);
	
	@Transactional
	public List<Lable> findLable(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public void addLable(Lable lable);
	
	@Transactional
	public void deleteLable(Lable lable);
	@Transactional
	 public List<Lable> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean);
	@Transactional
	public Long countLable(String hql,Object[] o);
	@Transactional
	public void saveLable(Lable Lable);
	
	@Transactional
	public List<Lable> findLablesByType(Integer type);

}
