package com.ehighsun.shixiya.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Resume;

public interface ResumeService {

	@Transactional
	public void addResume(Resume resume);

	@Transactional
	public void deleteResume(Resume resume);

	@Transactional
	public void updateResume(Resume resume);

	@Transactional
	public List<Resume> findResume();

	@Transactional
	public List<Resume> findResumeByMap(Map<String, Object> map,
			PageBean pageBean);

	@Transactional
	public Resume findResumeById(Integer id);

	@Transactional
	public List<Resume> findResume(String hql, Object[] param, PageBean pageBean);

	@Transactional
	public Long countResume(String hql);

	@Transactional
	public List<Resume> findResumeByName(String param);

	@Transactional
	public Resume getResumeByStudentId(Integer studnetId);

	@Transactional
	public List<Integer> executeOurSql(String sql);

	@Transactional
	public Integer executeSql(String sql);
}
