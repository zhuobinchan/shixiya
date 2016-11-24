package com.ehighsun.shixiya.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.StudentUpdateIntegralService;

@Service("studentUpdateIntegralService")
public class StudentUpdateIntegralServiceImpl implements
		StudentUpdateIntegralService {
	@Resource(name = "baseDao")
	private BaseDao<Student> studentDao;
	private Student student;

	@Override
	public void ResumesForIntegral(Integer studentId) {// 第一次添加简历 加10分
		student = studentDao.get(Student.class, studentId);
		if (student.getResumes().size() == 0) {
			Integer integral = student.getIntegral();
			integral = integral + 10;
			student.setIntegral(integral);
			studentDao.saveOrUpdate(student);
		}
	}

	@Override
	public void ProductForIntegral(Integer studentId) {
		student = studentDao.get(Student.class, studentId);
		Integer integral = student.getIntegral();
		integral = integral + 10;
		student.setIntegral(integral);
		studentDao.saveOrUpdate(student);
	}

}
