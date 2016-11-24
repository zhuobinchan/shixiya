package com.ehighsun.shixiya.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductVideo;

public interface BProductVideoDao extends BaseDao<BProductVideo> {
	
	@Transactional
	public void addVideo(BProductVideo video);
	@Transactional
	public void deleteVideo(BProductVideo video);
	@Transactional
	public List<BProductVideo> findVideo(Integer chapterId);
	@Transactional
	public BProductVideo findVideoById(Integer id);

}
