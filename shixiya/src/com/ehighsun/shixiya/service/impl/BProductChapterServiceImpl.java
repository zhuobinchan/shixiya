package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BProductChapterDao;
import com.ehighsun.shixiya.dao.BProductVideoDao;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductChapterService;

@Service("bProductChapterService")
public class BProductChapterServiceImpl implements BProductChapterService {

	@Autowired
	private BProductChapterDao bProductChapterDao;
	@Autowired
	private BProductVideoDao bProductVideoDao;

	@Override
	public void addBChapter(BProductChapter bProductChapter) {

		bProductChapterDao.addBChapter(bProductChapter);

	}

	@Override
	public void deleteBChapter(BProductChapter bProductChapter) {
		// TODO Auto-generated method stub
		bProductChapterDao.delete(bProductChapter);
	}

	@Override
	public List<BProductChapter> findChapterByCourseId(Integer courseId) {

		return bProductChapterDao.findChapterByCourseId(courseId);
	}

	@Override
	public List<BProductChapter> findChapterByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bProductChapterDao.findChapterByMap(map);
	}

	@Override
	public void updateChapter(BProductChapter bProductChapter) {
		bProductChapterDao.update(bProductChapter);
	}

	@Override
	public void addVideo(BProductVideo video) {
		bProductVideoDao.addVideo(video);

	}

	@Override
	public void deleteVideo(BProductVideo video) {
		// TODO Auto-generated method stub
		bProductVideoDao.deleteVideo(video);
	}

	@Override
	public List<BProductVideo> findVideo(Integer id) {
		// TODO Auto-generated method stub
		return bProductVideoDao.findVideo(id);
	}

	@Override
	public BProductVideo findVideoById(Integer id) {
		// TODO Auto-generated method stub
		return bProductVideoDao.findVideoById(id);
	}

	@Override
	public List<BProductChapter> findBChapter(String hql, Object[] param,
			PageBean pageBean) {

		return bProductChapterDao.find(hql, param, pageBean);
	}

	@Override
	public Long countChapters(String hql) {

		return bProductChapterDao.count(hql);
	}

	@Override
	public List<BProductChapter> findChapters() {
		// TODO Auto-generated method stub
		return bProductChapterDao.find("from BProductChapter");
	}

	@Override
	public BProductChapter findChapterById(Integer Id) {
		// TODO Auto-generated method stub
		List<BProductChapter> list = bProductChapterDao.find(
				"from BProductChapter where id = ?", new Object[] { Id });

		if (list != null && list.size() == 1) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public List<BProductChapter> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		// TODO Auto-generated method stub
		return bProductChapterDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bProductChapterDao.countByMap(hql, map);
	}

	@Override
	public List<BProductChapter> findChapterByCourseIdAjax(Integer courseId) {
		return bProductChapterDao.findChapterByCourseIdAjax(courseId);

	}
}
