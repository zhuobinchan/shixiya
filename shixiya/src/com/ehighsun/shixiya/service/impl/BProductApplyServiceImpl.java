package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BProductApplyDao;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.BProductApplyService;
@Service("bProductApplyService")
public class BProductApplyServiceImpl implements BProductApplyService {
	@Autowired
	private BProductApplyDao bProductApplyDao;
	
	@Override
	public void AddBProductApply(BProductStudentApply bProductStudentApply) {
		// TODO Auto-generated method stub
		bProductApplyDao.AddBProductApply(bProductStudentApply);
	}

	@Override
	public void deleteBProductApply(BProductStudentApply bProductStudentApply) {
		// TODO Auto-generated method stub
		bProductApplyDao.deleteBProductApply(bProductStudentApply);
	}

	@Override
	public List<BProductStudentApply> findBProductApply(
			) {
		// TODO Auto-generated method stub
		return bProductApplyDao.findBProductApply();
	}

	@Override
	public BProductStudentApply findBProductApplyByStudentId(Student student,Integer id) {
		
		return bProductApplyDao.findBProductApplyByStudentId(student,id);
	}
	
	@Override
	public int countBpApplyByCourse(Integer courseId){
		return bProductApplyDao.countBpApplyByCourse(courseId);
	}
	
	@Override /*判断某课程是否报名或报名成功*/
	public String judgeApplyStatus(int studentId,int courseId){
		return bProductApplyDao.judgeApplyStatus(studentId, courseId);
	};
}
