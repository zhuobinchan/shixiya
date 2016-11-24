package com.ehighsun.shixiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.service.BProductTeacherService;
import com.ehighsun.shixiya.util.StringUtil;

@Service("bProductTeacherService")
public class BProductTeacherServiceImpl implements BProductTeacherService {

	@Resource(name = "baseDao")
	private BaseDao<BProductTeacher> baseDao;

	public String add(BProductTeacher BTeacher) {

		String str = null;

		BProductTeacher bt = findBTeacherById(BTeacher.getId());
		if (bt != null) {
			str = "该讲师id已存在";
		} else {
			baseDao.save(BTeacher);
			str = "讲师添加成功";

		}

		return str;

	}

	@Override
	public BProductTeacher findBTeacherById(Integer id) {

		String hql = "from BProductTeacher where id = ?";

		List<BProductTeacher> lists = baseDao.find(hql, new Object[] { id });

		if (lists == null || lists.size() == 0) {

			return null;
		} else {
			return lists.get(0);

		}

	}

	public List<BProductTeacher> findBTeacher() {

		String hql = "from BProductTeacher";

		return baseDao.find(hql);

	}

	public String updateBTeacher(BProductTeacher BTeacher) {

		String str = null;

		BProductTeacher bt = findBTeacherById(BTeacher.getId());
		if (bt == null) {
			str = "该讲师不存在";
		}

		baseDao.update(BTeacher);
		str = "讲师更新成功";

		return str;
	}

	public String deleteBTeacher(BProductTeacher BTeacher) {

		String str = null;

		BProductTeacher bt = findBTeacherById(BTeacher.getId());
		if (bt == null) {
			str = "该讲师不存在";

			return str;
		}

		baseDao.delete(bt);
		str = "讲师删除成功";

		return str;

	}

	@Override
	public List<BProductTeacher> findBTeacher(String hql, Object[] param,
			PageBean pageBean) {

		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countTeacher(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public void saveOrAdd(BProductTeacher BTeacher) {

		baseDao.merge(BTeacher);

	}

	@Override
	public BProductTeacher findBTeacherById(String hql, Object[] param) {
		// TODO Auto-generated method stub
		List<BProductTeacher> list = baseDao.find(hql, param);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<BProductTeacher> findBTeacher(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BProductTeacher> findByMap(String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.findByMap(hql, map);
	}

	@Override
	public List<BProductTeacher> findPageByMap(String hql,
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
	public boolean bpTeacherLogin(String telephone, String password) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtil.isNotEmpty(telephone)) {
			map.put("telephone", telephone);
		}
		if (StringUtil.isEmpty(telephone)) {
			return false;
		}
		if (StringUtil.isNotEmpty(password)) {
			password = StringUtil.md5(password);
			map.put("password", password);
		}
		if (StringUtil.isEmpty(password)) {
			return false;
		}
		return baseDao.findByMap("from BProductTeacher", map).size() == 1;

	}

	@Override
	public BProductTeacher findByTelephone(String telephone) {
		return baseDao.find("from BProductTeacher where telephone = ?",
				new Object[] { telephone }).get(0);
	}
}
