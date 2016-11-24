package com.ehighsun.shixiya.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;

public interface BProductApplyDao extends BaseDao<BProductStudentApply> {
	@Transactional
	public void AddBProductApply(BProductStudentApply bProductStudentApply);

	@Transactional
	public void deleteBProductApply(BProductStudentApply bProductStudentApply);

	@Transactional
	public List<BProductStudentApply> findBProductApply();

	@Transactional
	public BProductStudentApply findBProductApplyByStudentId(Student student,
			Integer cid);
	
	@Transactional /*统计某课程的通过报名人数（既前台的参与人数）*/
	public int countBpApplyByCourse(Integer courseId);
	
	@Transactional /*判断某课程是否报名或报名成功*/
	public String judgeApplyStatus(int studentId,int courseId);
		
}
