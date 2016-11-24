package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductCourseService {
	@Transactional
	public String addBCourse(BProductCourse bProductCourse);

	@Transactional
	public String updateBCourse(BProductCourse bProductCourse);

	@Transactional
	public String deleteBCourse(BProductCourse bProductCourse);

	@Transactional
	public List<BProductCourse> findBCourse();
	
	@Transactional
	public List<BProductCourse> findBCourse(PageBean pageBean);

	@Transactional
	public String deleteBCourse(int[] ids);

	@Transactional
	public void addAdvertisement(List<BProductAdvertisement> lists);

	@Transactional
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement);

	@Transactional
	public List<BProductAdvertisement> findAdvertisement(Integer id);

	@Transactional
	public BProductAdvertisement findAdvertisementById(Integer id);

	@Transactional
	List<BProductCourse> findBCourse(String hql, Object[] param,
			PageBean pageBean);

	@Transactional
	public Long countCourses(String hql);

	@Transactional
	public BProductCourse findBCourseById(Integer id);

	@Transactional
	public List<BProductCourse> findBCourseByLable(String lableName,PageBean pageBean);

	@Transactional
	List<BProductCourse> findByMap(String hql, Map<String, Object> map);

	@Transactional
	public List<BProductCourse> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean);

	@Transactional
	public Long countByMap(String hql, Map<String, Object> map);

	@Transactional
	public List<BProductCourse> findBProductCoursesByTeacher(
			BProductTeacher teacher);

	@Transactional
	public List<BProductCourse> getBProductCoursesByLableName(String lableNames);
	
	@Transactional
	public List<BProductCourse> getBProductCoursesByTimeQuantum(Integer timeQuantum,PageBean pageBean);
}
