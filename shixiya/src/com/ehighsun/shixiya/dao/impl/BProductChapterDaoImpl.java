package com.ehighsun.shixiya.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ehighsun.shixiya.dao.BProductChapterDao;
import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;

@Repository("bProductChapterDao")
public class BProductChapterDaoImpl extends BaseDaoImpl<BProductChapter>
		implements BProductChapterDao {

	public void addBChapter(BProductChapter bProductChapter) {

		this.saveOrUpdate(bProductChapter);
	}

	public void deleteBChapter(BProductChapter bProductChapter) {

		this.delete(bProductChapter);
	}

	public List<BProductChapter> findChapterByCourseId(Integer courseId) {

		String hql = "from BProductChapter where BProductCourse = ?";

		BProductCourse bc = new BProductCourse();
		bc.setId(courseId);
		return this.find(hql, new Object[] { bc });

	}

	@Override
	public List<BProductChapter> findChapterByMap(Map<String, Object> map) {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from BProductChapter");
		stringBuffer.append(" where 1=1 ");

		for (Entry<String, Object> entry : map.entrySet()) {// 把查询条件放到where的后面
			stringBuffer.append(" and " + entry.getKey() + "=:"
					+ entry.getKey());
		}

		Query query = this.getCurrentSession().createQuery(
				stringBuffer.toString());

		/**
		 * 给hql语句的参数赋值
		 */
		for (Entry<String, Object> entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.list();
	}

	@Override
	public void updateBChapter(BProductChapter bProductChapter) {
		// TODO Auto-generated method stub
		this.update(bProductChapter);
	}

	@Override
	public List<BProductChapter> findChapterByCourseIdAjax(Integer courseId) {

		return this
				.find("select new BProductChapter(id, title, introduction) from BProductChapter where B体验式课程ID  = "
						+ courseId);
	}

}
