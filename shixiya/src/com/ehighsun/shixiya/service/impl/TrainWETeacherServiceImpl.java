package com.ehighsun.shixiya.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("trainWETeacherService")
public class TrainWETeacherServiceImpl implements TrainWETeacherService {
	@Resource(name = "baseDao")
	private BaseDao<TrainWETeacher> baseDao;

	@Override
	public List<TrainWETeacher> getAllTrainWETeachers() {
		return baseDao.find("from TrainWETeacher");
	}

	@Override
	public TrainWETeacher getTrainWETeacherById(Integer id) {
		return baseDao.get(TrainWETeacher.class, id);
	}

	@Override
	public List<TrainWETeacher> getAllTrainWETeachers(
			HttpServletRequest request, String page) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = baseDao.count("select count(*) from TrainWETeacher");

		String pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/getAllTrainWETeacher_AdminTrainWETeacherAction.action",
						count, Integer.parseInt(page), 6, null);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.find("from TrainWETeacher", new Object[] {}, pageBean);
	}

	@Override
	public void addTrainWETeacher(TrainWETeacher trainWETeacher) {
		if (StringUtil.isNotEmpty(trainWETeacher.getPassword())) {
			trainWETeacher.setPassword(StringUtil.md5(trainWETeacher
					.getPassword()));
		}
		baseDao.save(trainWETeacher);
	}

	@Override
	public void updateTrainWETeacher(TrainWETeacher trainWETeacher) {
		baseDao.update(trainWETeacher);
	}

	@Override
	public void evitTrainWETeacher(TrainWETeacher trainWETeacher) {
		baseDao.evict(trainWETeacher);
	}

	@Override
	public List<TrainWETeacher> getTrainWETeacherByCondition(String name,
			String telephone, String email, HttpServletRequest request,
			String page) {
		Map<String, Object> map = getConditionByMap(name, telephone, email);
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = baseDao.countByMap("select count(*) from TrainWETeacher",
				map);
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/findByCondition_AdminTrainWETeacherAction.action",
				count, Integer.parseInt(page), 6, "name=" + name
						+ "&telephone=" + telephone + "&email=" + email);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.findPageByMap("from TrainWETeacher", map, pageBean);
	}

	private Map<String, Object> getConditionByMap(String name,
			String telephone, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtil.isNotEmpty(telephone)) {
			map.put("telephone", telephone);
		}
		if (StringUtil.isNotEmpty(email)) {
			map.put("email", email);
		}
		return map;
	}

	@Override
	public void deleteTrainWETeacherById(Integer id) {
		TrainWETeacher index = baseDao.get(TrainWETeacher.class, id);
		if (StringUtil.isNotEmpty(index.getHeadImgUrl())) {
			FileUploadUtil.deleteFile(index.getHeadImgUrl());
		}
		baseDao.delete(index);
	}

	@Override
	public void deleteTrainWETeacherByIds(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			TrainWETeacher index = baseDao.get(TrainWETeacher.class,
					Integer.parseInt(string));
			if (StringUtil.isNotEmpty(index.getHeadImgUrl())) {
				FileUploadUtil.deleteFile(index.getHeadImgUrl());
			}
			baseDao.delete(index);
		}

	}

	@Override
	public boolean trainTeacherLogin(String telephone, String password) {

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
		return baseDao.findByMap("from TrainWETeacher", map).size() == 1;

	}

	@Override
	public TrainWETeacher findByTelephone(String telephone) {
		return baseDao.find("from TrainWETeacher where telephone = ?",
				new Object[] { telephone }).get(0);
	}
}
