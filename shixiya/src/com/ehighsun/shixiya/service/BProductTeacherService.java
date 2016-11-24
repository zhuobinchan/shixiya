package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductChapter;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;

public interface BProductTeacherService {
	@Transactional
	public String add(BProductTeacher BTeacher);
	@Transactional
	public BProductTeacher findBTeacherById(Integer id);
	@Transactional
	public List<BProductTeacher> findBTeacher();
	@Transactional
	public String deleteBTeacher(BProductTeacher BTeacher);
	@Transactional
	public List<BProductTeacher> findBTeacher(String hql, Object[] param);
	@Transactional
	public BProductTeacher findBTeacherById(String hql, Object[] param);
	@Transactional
	public Long countTeacher(String hql);
	@Transactional
	public void saveOrAdd(BProductTeacher BTeacher);
	List<BProductTeacher> findBTeacher(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	List<BProductTeacher> findByMap(String hql, Map<String,Object> map);
	@Transactional
	public List<BProductTeacher> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean); 
	@Transactional
	public Long countByMap(String hql,Map<String,Object> map);
	@Transactional
	public boolean bpTeacherLogin(String telephone, String password);
	@Transactional
	public BProductTeacher findByTelephone(String telephone);

}
