package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BProductVideoDao;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.BProductVideo;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductVideoService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("bProductVideoService")
public class BProductVideoServiceImpl implements BProductVideoService{

	
	@Autowired
	private BProductVideoDao bProductVideoDao;
	
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
	public List<BProductVideo> findBVideo(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return bProductVideoDao.find(hql, param, pageBean);
	}

	@Override
	public Long countVideos(String hql) {
		// TODO Auto-generated method stub
		return bProductVideoDao.count(hql);
	}

	@Override
	public List<BProductVideo> findByMap(String hql, Map<String, Object> map) {
		
		return bProductVideoDao.findByMap(hql, map);
	}

	@Override
	public List<BProductVideo> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		// TODO Auto-generated method stub
		return bProductVideoDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bProductVideoDao.countByMap(hql, map);
	}

	@Override
	public List<BProductVideo> findByChapterId(Integer id) {
		// TODO Auto-generated method stub
		
		BProductChapter chapter = new BProductChapter();
		chapter.setId(id);
		return bProductVideoDao.find("from BProductVideo where BProductChapter = ?",new Object[]{chapter});
	}

	
}
