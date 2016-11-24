package com.ehighsun.shixiya.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.RIBussinessResume;
import com.ehighsun.shixiya.service.RIBussinessResumeService;

@Service("riBussinessResumeService")
public class RIBussinessResumeServiceImpl implements RIBussinessResumeService {

	@Resource(name = "baseDao")
	private BaseDao<RIBussinessResume> baseDao;

	public RIBussinessResume findResumeByStudentId(Integer sid) {

		List<RIBussinessResume> list = baseDao
				.find("from RIBussinessResume where student.id = " + sid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	public void addResume(RIBussinessResume resume) {
		// TODO Auto-generated method stub
		baseDao.merge(resume);
	}

}
