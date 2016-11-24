package com.ehighsun.shixiya.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductChapterDao extends BaseDao<BProductChapter> {

	
	@Transactional
	public void addBChapter(BProductChapter bProductChapter);
	@Transactional
	public void deleteBChapter(BProductChapter bProductChapter);
	@Transactional
	public void updateBChapter(BProductChapter bProductChapter);
	@Transactional
	public List<BProductChapter> findChapterByCourseId(Integer courseId);
	@Transactional
	public List<BProductChapter> findChapterByMap(Map<String,Object> map);
	@Transactional
	public List<BProductChapter> findChapterByCourseIdAjax(Integer courseId);
	
	
}
