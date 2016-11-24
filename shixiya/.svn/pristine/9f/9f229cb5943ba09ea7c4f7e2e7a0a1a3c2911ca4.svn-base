package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductChapterService {

	@Transactional
	public void addBChapter(BProductChapter bProductChapter);
	@Transactional
	public void deleteBChapter(BProductChapter bProductChapter);
	@Transactional
	public void updateChapter(BProductChapter bProductChapter);
	@Transactional
	public List<BProductChapter> findChapterByCourseId(Integer courseId);
	@Transactional
	public BProductChapter findChapterById(Integer Id);
	@Transactional
	public List<BProductChapter> findChapters();
	@Transactional
	public List<BProductChapter> findChapterByMap(Map<String,Object> map);
	@Transactional
	public void addVideo(BProductVideo video);
	@Transactional
	public void deleteVideo(BProductVideo video);
	@Transactional
	public List<BProductVideo> findVideo(Integer id);
	@Transactional
	public BProductVideo findVideoById(Integer id);
	@Transactional
	List<BProductChapter> findBChapter(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public Long countChapters(String hql);
	@Transactional
	public List<BProductChapter> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean); 
	@Transactional
	public Long countByMap(String hql,Map<String,Object> map);
	@Transactional
	public List<BProductChapter>  findChapterByCourseIdAjax(Integer courseId);
}
