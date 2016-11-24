package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BProductAdvertisementDao;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductAdvertisement;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApplyId;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.ehighsun.shixiya.service.LableService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("bProductCourseService")
public class BProductCourseServiceImpl implements BProductCourseService {

	@Autowired
	private BaseDao<BProductCourse> baseDao;
	@Autowired
	private BProductAdvertisementDao advertisementDao;
	@Autowired
	private LableService lableService;

	@Resource(name = "baseDao")
	private BaseDao<BProductStudentApplyId> bpsApplyIdDao;

	@Override
	public String addBCourse(BProductCourse bProductCourse) {

		baseDao.merge(bProductCourse);
		;

		return null;
	}

	@Override
	public String updateBCourse(BProductCourse bProductCourse) {

		String str = null;

		BProductCourse bc = findBCourseById(bProductCourse.getId());

		if (bc == null) {
			str = "该课程的不存在";
		} else {
			baseDao.update(bProductCourse);
			str = "课程更新成功";
		}

		return str;

	}

	@Override
	public String deleteBCourse(BProductCourse bProductCourse) {

		baseDao.delete(bProductCourse);
		return "删除成功";
	}

	@Override
	public List<BProductCourse> findBCourse() {

		String hql = "from BProductCourse order by stick desc, publicTime desc";
		return baseDao.find(hql);
	}
	
	@Override
	public List<BProductCourse> findBCourse(PageBean pageBean) {
		String hql = "from BProductCourse order by stick desc, publicTime desc";
		return baseDao.find(hql, new Object[]{}, pageBean);
	}

	@Override
	public String deleteBCourse(int[] ids) {

		for (int i = 0; i < ids.length; i++) {
			BProductCourse bc = new BProductCourse();
			bc.setId(ids[i]);
			baseDao.delete(bc);
		}

		return null;
	}

	@Override
	public BProductCourse findBCourseById(Integer id) {

		String hql = "from BProductCourse where id = ? order by stick desc, publicTime desc";

		List<BProductCourse> lists = baseDao.find(hql, new Object[] { id });

		if (lists != null && lists.size() == 1) {

			return lists.get(0);

		} else {

			return null;

		}

	}

	@Override
	public List<BProductCourse> findBCourseByLable(String lableName,PageBean pageBean) {

		String hql = "from BProductCourse where productLable like '%" + lableName
				+ "%' order by publicTime desc";

		List<BProductCourse> lists = baseDao.find(hql,new Object[]{},pageBean);

		return lists;

	};

	@Override
	public void addAdvertisement(List<BProductAdvertisement> lists) {

		for (BProductAdvertisement bProductAdvertisement : lists) {
			advertisementDao.addBAdvertisement(bProductAdvertisement);
		}

	}

	@Override
	public void deleteAdvertisement(BProductAdvertisement bProductAdvertisement) {

		advertisementDao.delete(bProductAdvertisement);
	}

	public List<BProductAdvertisement> findAdvertisement(Integer id) {

		return advertisementDao.findADvertisment(id);
	}

	@Override
	public BProductAdvertisement findAdvertisementById(Integer id) {
		// TODO Auto-generated method stub
		return advertisementDao.findADvertismentById(id);
	}

	@Override
	public List<BProductCourse> findBCourse(String hql, Object[] param,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countCourses(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<BProductCourse> findByMap(String hql, Map<String, Object> map) {

		return baseDao.findByMap(hql, map);

	}

	@Override
	public List<BProductCourse> findPageByMap(String hql,
			Map<String, Object> map, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, map, pageBean);
	}

	@Override
	public Long countByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, map);
	}

	@Override
	public List<BProductCourse> findBProductCoursesByTeacher(
			BProductTeacher teacher) {
		return baseDao.find("from BProductCourse where BProductTeacher = ? order by publicTime desc",
				new Object[] { teacher });
	}

	@Override
	public List<BProductCourse> getBProductCoursesByLableName(String lableNames) {
		lableNames = lableNames.replaceAll(" ", "");
		String[] lales = StringUtil.ToArray(lableNames);
		StringBuffer hql = new StringBuffer();
		hql.append("from BProductCourse where 1=0 ");
		for (String string : lales) {
			if (StringUtil.isNotEmpty(string)) {
				hql.append("OR lable like '%").append(string).append("%'");
			}

		}
		return baseDao.find(hql.toString());
	}

	@Override
	public List<BProductCourse> getBProductCoursesByTimeQuantum(
			Integer timeQuantum,PageBean pageBean) {
		List<Integer> ids = baseDao
				.executeOurSql("SELECT `B体验式课程ID` FROM b体验式产品课程管理   where TO_DAYS(`课程结束时间`) - TO_DAYS(`课程开始时间`) < "
						+ timeQuantum);
		
		Integer endPage=pageBean.getStart()+pageBean.getPageSize();
		endPage = endPage>ids.size()?ids.size():endPage;
		try {
			ids=ids.subList(pageBean.getStart(), endPage);
		} catch (Exception e) {
			ids = new ArrayList<>();
		}
		List<BProductCourse> result = new ArrayList<>();
		for (Integer id : ids) {
			result.add(baseDao.get(BProductCourse.class, id));
		}

		return result;
	}
}
