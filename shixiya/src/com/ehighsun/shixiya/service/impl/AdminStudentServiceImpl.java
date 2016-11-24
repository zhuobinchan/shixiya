package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("adminStudentService")
public class AdminStudentServiceImpl implements AdminStudentService {

	@Autowired
	private BaseDao<Student> baseDao;

	@Override
	public List<Student> findStudent(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countStudent(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<Student> findPageByMap(String hql, Map<String, Object> map,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, map);
	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		baseDao.delete(student);
	}

	@Override
	public Student findStudentById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get("from Student where id = ?", new Object[] { id });
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		baseDao.update(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return baseDao.find("from Student");
	}

	@Override
	public Student findStudentByName(String name) {
		// TODO Auto-generated method stub

		List<Student> s = baseDao.find("from Student where name = ?",
				new Object[] { name });
		if (s != null && s.size() > 0) {
			return s.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Student getStudentByResume(String name, String telephone,
			String email) {
		List<Student> students= new ArrayList<>();
		if (StringUtil.isNotEmpty(name)&&StringUtil.isNotEmpty(telephone)) {
			students = baseDao.find("from Student where name = ? and telephone = ?", new Object[]{name,telephone});
			if (students.size()!=0) {
				return students.get(0);
			}
		}
		
		
		if (StringUtil.isNotEmpty(name)&&StringUtil.isNotEmpty(email)) {
		students =baseDao.find("from Student where name = ? and email = ?",new Object[]{name,email});
			if (students.size()!=0) {
				return students.get(0);
			}
		}
		if (StringUtil.isNotEmpty(telephone)&&StringUtil.isNotEmpty(email)) {
		students =baseDao.find("from Student where telephone = ? and email = ?",new Object[]{telephone,email});
			if (students.size()!=0) {
				return students.get(0);
			}
		}
		return null;
	}

}
