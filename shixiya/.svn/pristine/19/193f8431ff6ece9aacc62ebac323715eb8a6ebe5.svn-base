package com.ehighsun.shixiya.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ehighsun.shixiya.dao.BProductAdvertisementDao;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;

@Repository("bProductAdvertisementDao")
public class BProductAdvertisementDaoImpl extends
		BaseDaoImpl<BProductAdvertisement> implements BProductAdvertisementDao {

	@Override
	public void addBAdvertisement(BProductAdvertisement bProductAdvertisement) {

		this.save(bProductAdvertisement);
	}

	@Override
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement) {

		this.delete(bProductAdvertisement);
	}

	@Override
	public List<BProductAdvertisement> findADvertisment(Integer courseId) {

		String hql = "from BProductAdvertisement where BProductCourse = ?";

		BProductCourse bc = new BProductCourse();
		bc.setId(courseId);
		return this.find(hql, new Object[] { bc });

	}

	@Override
	public List<BProductAdvertisement> findAllADvertisment() {

		String hql = "from BProductAdvertisement where status = 1";

		return this.find(hql);

	}

	@Override
	public BProductAdvertisement findADvertismentById(Integer Id) {
		String hql = "from BProductAdvertisement where id = ?";
		List<BProductAdvertisement> lists = this.find(hql, new Object[] { Id });
		if (lists != null && lists.size() == 1) {
			return lists.get(0);
		}
		return null;
	}

}
