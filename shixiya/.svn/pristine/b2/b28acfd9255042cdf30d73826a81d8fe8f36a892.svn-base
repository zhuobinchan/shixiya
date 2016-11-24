package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.TrainWETeacher;

public interface TrainWETeacherService {
	@Transactional
	public List<TrainWETeacher> getAllTrainWETeachers();

	@Transactional
	public List<TrainWETeacher> getAllTrainWETeachers(
			HttpServletRequest request, String page);

	@Transactional
	public TrainWETeacher getTrainWETeacherById(Integer id);

	@Transactional
	public void addTrainWETeacher(TrainWETeacher trainWETeacher);

	@Transactional
	public void updateTrainWETeacher(TrainWETeacher trainWETeacher);

	@Transactional
	public void evitTrainWETeacher(TrainWETeacher trainWETeacher);

	@Transactional
	public List<TrainWETeacher> getTrainWETeacherByCondition(String name,
			String telephone, String email, HttpServletRequest request,
			String page);

	@Transactional
	public void deleteTrainWETeacherById(Integer id);

	@Transactional
	public void deleteTrainWETeacherByIds(String ids);
	
	public boolean trainTeacherLogin(String telephone, String password);
	
	@Transactional
	public TrainWETeacher findByTelephone(String telephone);
}
