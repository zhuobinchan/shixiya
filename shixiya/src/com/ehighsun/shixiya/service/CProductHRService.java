package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.PageBean;

public interface CProductHRService {

	@Transactional
	public void addOrUpdate(CProductHR cProductHR);

	@Transactional
	public List<CProductHR> findCProductHR(String hql, Object[] param,
			PageBean pageBean);

	@Transactional
	public Long countCProductHR(String hql);

	@Transactional
	public List<CProductHR> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean);

	@Transactional
	public Long countByMap(String hql, Map<String, Object> map);

	@Transactional
	public void deleteCProductHR(CProductHR cProductHR);

	@Transactional
	public CProductHR findHRById(Integer id);

	@Transactional
	public boolean cpHrLogin(String telephone, String password);

	@Transactional
	public CProductHR findByTelephone(String telephone);

	@Transactional
	public List<CProductHR> getAllCProductHRs();

}
