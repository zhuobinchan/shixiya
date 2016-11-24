package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.service.ResumeService;

@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {
	@Resource(name = "baseDao")
	private BaseDao<Resume> resumeDao;

	@Override
	public void addResume(Resume resume) {
		// TODO Auto-generated method stub
		resumeDao.save(resume);
	}

	@Override
	public void deleteResume(Resume resume) {
		// TODO Auto-generated method stub
		resumeDao.delete(resume);
	}

	@Override
	public void updateResume(Resume resume) {
		// TODO Auto-generated method stub
		resumeDao.update(resume);
	}

	@Override
	public List<Resume> findResume() {
		// TODO Auto-generated method stub
		return resumeDao.find("from Resume");
	}

	public List<Resume> findResumeByMap(Map<String, Object> map,
			PageBean pageBean) {

		return resumeDao.findPageByMap("from Resume", map, pageBean);
	}

	@Override
	public Resume findResumeById(Integer id) {
		// TODO Auto-generated method stub
		return resumeDao.get(Resume.class, id);
	}

	@Override
	public Long countResume(String hql) {
		// TODO Auto-generated method stub
		return resumeDao.count(hql);
	}

	@Override
	public List<Resume> findResume(String hql, Object[] param, PageBean pageBean) {
		// TODO Auto-generated method stub
		return resumeDao.find(hql, param, pageBean);
	}

	@Override
	public List<Resume> findResumeByName(String name) {
		// TODO Auto-generated method stub
		return resumeDao.find("from Resume where name = ?",
				new Object[] { name });
	}

	@Override
	public Resume getResumeByStudentId(Integer studnetId) {
		List<Resume> index = resumeDao.find("from Resume where 学生ID = "
				+ studnetId);
		return index.size() == 1 ? index.get(0) : null;
	}

	@Override
	public List<Integer> executeOurSql(String sql) {
		return resumeDao.executeOurSql(sql);

	}

	@Override
	public Integer executeSql(String sql) {
		return resumeDao.executeSql(sql);
	}
}
