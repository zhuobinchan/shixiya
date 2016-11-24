package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductVideoService {

	@Transactional
	public void addVideo(BProductVideo video);
	@Transactional
	public void deleteVideo(BProductVideo video);
	@Transactional
	public List<BProductVideo> findVideo(Integer id);
	@Transactional
	public BProductVideo findVideoById(Integer id);
	@Transactional
	List<BProductVideo> findBVideo(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public List<BProductVideo> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean); 
	@Transactional
	public Long countByMap(String hql,Map<String,Object> map);
	@Transactional
	public Long countVideos(String hql);
	@Transactional
	List<BProductVideo> findByMap(String hql,Map<String,Object> map);
	@Transactional
	List<BProductVideo> findByChapterId(Integer id);
	
	
}
