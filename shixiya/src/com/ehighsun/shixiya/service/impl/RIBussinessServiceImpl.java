package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.service.RIBussinessService;
import com.ehighsun.shixiya.service.RecruitmentInfoService;

@Service("riBussinessServiceImpl")
public class RIBussinessServiceImpl implements RIBussinessService {

	@Resource(name = "baseDao")
	private BaseDao<RIBussiness> baseDao;
	@Resource(name = "recruitmentInfoServiceImpl")
	private RecruitmentInfoService recruitmentInfoService;

	@Override
	public void addRIBussiness(RIBussiness bussiness) {
		// TODO Auto-generated method stub
		baseDao.save(bussiness);
	}

	@Override
	public void deleteRIBussiness(Integer id) {
		// TODO Auto-generated method stub
		RIBussiness bussiness = baseDao.get(RIBussiness.class, id);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (bussiness != null && bussiness.getFileUrl() != null) {
			String oldPath = request.getSession().getServletContext()
					.getRealPath("/" + bussiness.getFileUrl());
			File f = new File(oldPath);
			f.delete();

		}

		if (bussiness != null) {
			Set<RecruitmentInfo> infos = bussiness.getRecruitmentInfos();
			for (RecruitmentInfo info : infos) {
				recruitmentInfoService.deleteRecruitmentInfo(info.getId());
			}

		}

		if (bussiness != null) {
			this.evitRIBussiness(bussiness);
			baseDao.delete(bussiness);
		}
	}

	@Override
	public void deleteRIBussinesses(String[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			this.deleteRIBussiness(Integer.parseInt(ids[i]));
		}
	}

	@Override
	public void updateRIBussiness(RIBussiness bussiness) {
		// TODO Auto-generated method stub
		baseDao.update(bussiness);
	}

	@Override
	public RIBussiness getRIBussinessById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(RIBussiness.class, id);
	}

	@Override
	public List<RIBussiness> findRIBussinesss(String hql, List<Object> param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countRIBussiness(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<RIBussiness> findByCondition(String hql, Map<String, Object> o,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, o, pageBean);
	}

	@Override
	public Long countByCondition(String hql, Map<String, Object> o) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, o);
	}

	public void evitRIBussiness(RIBussiness ris) {
		baseDao.evict(ris);
	}

	@Override
	public RIBussiness findRiBussinessByEmail(String email) {
		List<RIBussiness> list = baseDao.find(
				"from RIBussiness where email = ? and state = 1",
				new Object[] { email });

		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<RIBussiness> findRiBussinesses() {

		return baseDao.find("from RIBussiness where state = 1");
	}

}
