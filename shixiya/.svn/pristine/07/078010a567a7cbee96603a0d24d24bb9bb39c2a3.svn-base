package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RIBussiness;

public interface RIBussinessService {

	@Transactional
	public void addRIBussiness(RIBussiness bussiness);

	@Transactional
	public void deleteRIBussiness(Integer id);

	@Transactional
	public void deleteRIBussinesses(String[] ids);

	@Transactional
	public void updateRIBussiness(RIBussiness bussiness);

	@Transactional
	public RIBussiness getRIBussinessById(Integer id);

	@Transactional
	public List<RIBussiness> findRIBussinesss(String hql, List<Object> param,
			PageBean pageBean);

	@Transactional
	public Long countRIBussiness(String hql);

	@Transactional
	public List<RIBussiness> findByCondition(String hql, Map<String, Object> o,
			PageBean pageBean);

	@Transactional
	public Long countByCondition(String hql, Map<String, Object> o);

	@Transactional
	public RIBussiness findRiBussinessByEmail(String eamil);

	@Transactional
	public List<RIBussiness> findRiBussinesses();

}
