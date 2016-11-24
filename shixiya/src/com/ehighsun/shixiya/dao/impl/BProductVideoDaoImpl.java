package com.ehighsun.shixiya.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ehighsun.shixiya.dao.BProductVideoDao;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductVideo;

@Repository("bProductVideoDao")
public class BProductVideoDaoImpl extends BaseDaoImpl<BProductVideo> implements BProductVideoDao {

	@Override
	public void addVideo(BProductVideo video) {
		// TODO Auto-generated method stub
		this.merge(video);
	}

	@Override
	public void deleteVideo(BProductVideo video) {
		// TODO Auto-generated method stub
		this.delete(video);
	}

	@Override
	public List<BProductVideo> findVideo(Integer chapterId) {
		// TODO Auto-generated method stub
		String hql = "from BProductVideo where BProductChapter = ?";
		
		BProductChapter bc = new BProductChapter();
		bc.setId(chapterId);
		return this.find(hql, new Object[]{bc});
	}

	@Override
	public BProductVideo findVideoById(Integer id) {
		
		String hql = "from BProductVideo where id = ?";
		List<BProductVideo> lists = this.find(hql, new Object[]{id});
		if(lists!=null && lists.size()==1){
			return lists.get(0);
		}
		return null;
	}

}
