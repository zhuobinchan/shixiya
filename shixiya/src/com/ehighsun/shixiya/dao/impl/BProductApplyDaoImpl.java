package com.ehighsun.shixiya.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ehighsun.shixiya.dao.BProductApplyDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.Student;

@Repository("bProductApplyDao")
public class BProductApplyDaoImpl extends BaseDaoImpl<BProductStudentApply>
		implements BProductApplyDao {

	@Override
	public void AddBProductApply(BProductStudentApply bProductStudentApply) {
		// TODO Auto-generated method stub
		this.save(bProductStudentApply);
	}

	@Override
	public void deleteBProductApply(BProductStudentApply bProductStudentApply) {
		// TODO Auto-generated method stub
		this.delete(bProductStudentApply);
	}

	@Override
	public List<BProductStudentApply> findBProductApply() {

		String hql = "from BProductStudentApply";

		return this.find(hql);

	}

	@Override
	public BProductStudentApply findBProductApplyByStudentId(Student student,
			Integer cid) {
		// TODO Auto-generated method stub
		String hql = "from BProductStudentApply where student = ? and BProductCourse = ?";

		BProductCourse course = new BProductCourse();
		course.setId(cid);
		List<BProductStudentApply> lists = this.find(hql, new Object[] {
				student, course });
		if (lists != null && lists.size() == 1) {
			return lists.get(0);
		} else {
			return null;
		}

	}

	@Override
	public int countBpApplyByCourse(Integer courseId) {

		String sql = "select count(*) b体验式课程学生报名表  " + " where 课程ID='"
				+ courseId + "'" + " && 审核状态=1";

		return this.executeSql(sql);

	}

	@Override
	public String judgeApplyStatus(int studentId, int courseId) {

		String sql = "select 审核状态  from b体验式课程学生报名表  " + " where 学生ID='"
				+ studentId + "'" + " && 课程ID='" + courseId + "'";

		List result = this.executeOurSql(sql);

		if (result == null || result.size() == 0) {
			return "noApply";
		} else if (result.get(0).toString().equals("0")) {
			return "waitingApply";
		} else if (result.get(0).toString().equals("1")) {
			return "sccessApply";
		} else if (result.get(0).toString().equals("2")) {
			return "failApply";
		} else if (result.get(0).toString().equals("3")) {
			return "applyWithoutResume";
		}

		return "noApply";

	}

}
