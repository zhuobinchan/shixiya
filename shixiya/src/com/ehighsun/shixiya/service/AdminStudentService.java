package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;

public interface AdminStudentService {

	
	@Transactional
	public List<Student> findStudent(String hql, Object[] param,
			PageBean pageBean);
	@Transactional
	public Long countStudent(String hql);
	@Transactional
	public List<Student> findPageByMap(String hql, Map<String, Object> map,PageBean pageBean); 
	@Transactional
	public Long countByMap(String hql,Map<String,Object> map);
	@Transactional
	public void deleteStudent(Student student);
	@Transactional
	public Student findStudentById(Integer id);
	@Transactional
	public void updateStudent(Student student);
	@Transactional
	public Student  findStudentByName(String name);
	
	@Transactional
	public List<Student> getAllStudents();
	
	@Transactional
	public Student getStudentByResume(String name,String telephone,String email);
	
}
