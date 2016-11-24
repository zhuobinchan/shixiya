package com.ehighsun.shixiya.service;

import java.util.List;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductEmploy;

public interface CProductEmployService {
	@Transactional
	public void saveOrUpdateEmploy(CProductEmploy employ);

	@Transactional
	public long countEmployByBroadcast(CProductBroadcast broadcast);

	@Transactional
	public List<CProductEmploy> getCProductEmploysByBroadcast(
			CProductBroadcast broadcast);
	
	@Transactional
	public void deleteEmploys(String employIds);
}
