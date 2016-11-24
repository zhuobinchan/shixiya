package com.ehighsun.shixiya.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.TrainWEService;
import com.ehighsun.shixiya.service.TrainWETeacherService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("trainWEService")
public class TrainWEServiceImpl implements TrainWEService {
	@Resource(name = "baseDao")
	private BaseDao<TrainWEProduct> trainWeDao;

	@Resource(name = "trainWETeacherService")
	private TrainWETeacherService teacherService;

	@Override
	public List<TrainWEProduct> getAllTrainWEProducts() {

		return trainWeDao
				.find("select new TrainWEProduct( id,  title,  publisher,"
						+ "publicTime,  startTime,  endTime,"
						+ "introduction,"
						+ "imgUrl, lable,"
						+ "visitnum,listViewContent) from TrainWEProduct order by stick desc, publicTime desc");
	}
	
	@Override
	public List<TrainWEProduct> getAllTrainWEProducts(PageBean pageBean) {

		return trainWeDao
				.find("select new TrainWEProduct( id,  title,  publisher,"
						+ "publicTime,  startTime,  endTime,"
						+ "introduction,"
						+ "imgUrl, lable,"
						+ "visitnum,listViewContent) from TrainWEProduct order by stick desc, publicTime desc",new Object[]{},pageBean);
	}

	@Override
	public List<TrainWEProduct> getAllTrainWEProducts(
			HttpServletRequest request, String page) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = trainWeDao.count("select count(*) from TrainWEProduct");

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/getAllTrainWEProduct_AdminTrainWEAction.action",
				count, Integer.parseInt(page), 6, null);

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);
		request.getSession().setAttribute("page", page);

		return trainWeDao
				.find("from TrainWEProduct", new Object[] {}, pageBean);
	}

	@Override
	public TrainWEProduct getTrainWeById(Integer id) {
		return trainWeDao.get(TrainWEProduct.class, id);
	}

	@Override
	public void addTrainWEProduct(TrainWEProduct trainWEProduct) {
		trainWeDao.save(trainWEProduct);
	}

	@Override
	public void updateTrainWEProduct(TrainWEProduct trainWEProduct) {
		trainWeDao.merge(trainWEProduct);
	}

	@Override
	public void evitTrainWEProduct(TrainWEProduct trainWEProduct) {
		trainWeDao.evict(trainWEProduct);

	}

	@Override
	public void deleteTrainWEProductById(Integer id) {
		TrainWEProduct index = trainWeDao.get(TrainWEProduct.class, id);
		if (StringUtil.isNotEmpty(index.getImgUrl())) {
			FileUploadUtil.deleteFile(index.getImgUrl());
		}
		trainWeDao.delete(index);
	}

	@Override
	public void deleteTrainWEProducts(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			TrainWEProduct index = trainWeDao.get(TrainWEProduct.class,
					Integer.parseInt(string));
			if (StringUtil.isNotEmpty(index.getImgUrl())) {
				FileUploadUtil.deleteFile(index.getImgUrl());
			}
			trainWeDao.delete(index);
		}
	}

	@Override
	public List<TrainWEProduct> findTrainWEProductsByCondition(String title,
			Integer teacherId, HttpServletRequest request, String page) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		Long count = trainWeDao.countByMap(
				"select count(*) from TrainWEProduct",
				getConditionByMap(title, teacherId));
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/findByCondition_AdminTrainWEAction.action", count,
				Integer.parseInt(page), 6, "title=" + title + "&teacherId="
						+ (teacherId == null ? "" : teacherId));
		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return trainWeDao.findPageByMap("from TrainWEProduct",
				getConditionByMap(title, teacherId), pageBean);
	}

	private Map<String, Object> getConditionByMap(String title,
			Integer teacherId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(title)) {
			map.put("title", title);
		}
		if (StringUtil.isNotEmpty(teacherId)) {
			map.put("trainWETeacher",
					teacherService.getTrainWETeacherById(teacherId));
		}
		return map;
	}

	@Override
	public List<TrainWEProduct> findTrainWeProductByTrainer(
			TrainWETeacher trainer) {

		return trainWeDao
				.find("from TrainWEProduct where trainWETeacher = ? order by publicTime desc",
						new Object[] { trainer });

	}

	@Override
	public TrainWEProduct findTrainWeProductById(Integer id) {

		String hql = "from TrainWEProduct where id = ? order by publicTime desc";

		List<TrainWEProduct> lists = trainWeDao.find(hql, new Object[] { id });

		if (lists != null && lists.size() == 1) {

			return lists.get(0);

		} else {

			return null;

		}
	}

	@Override
	public List<TrainWEProduct> getTrainWEByTimeQuantum(String timeQuantum,PageBean pageBean) {
		List<Integer> ids = trainWeDao
				.executeOurSql("SELECT `we课堂ID` FROM 培训we课堂管理   where TO_DAYS(`开播时间`) - TO_DAYS(`结束时间`) < "
						+ timeQuantum);
		Integer endPage=pageBean.getStart()+pageBean.getPageSize();
		endPage = endPage>ids.size()?ids.size():endPage;
		try {
			ids=ids.subList(pageBean.getStart(), endPage);
		} catch (Exception e) {
			ids = new ArrayList<>();
		}
		List<TrainWEProduct> result = new ArrayList<>();
		for (Integer id : ids) {
			result.add(trainWeDao.find("select new TrainWEProduct( id,  title,  publisher,"
						+ "publicTime,  startTime,  endTime,"
						+ "introduction,"
						+ "imgUrl, lable,"
						+ "visitnum,listViewContent) from TrainWEProduct where id = "+id).get(0));
		}
		return result;
	}

	@Override
	public List<TrainWEProduct> getTrainWEByLable(String lable,PageBean pageBean) {
		String hql = "select new TrainWEProduct( id,  title,  publisher,"
				+ "publicTime,  startTime,  endTime,"
				+ "introduction,"
				+ "imgUrl, lable,"
				+ "visitnum,listViewContent) from TrainWEProduct where productLable like '%"
				+ lable + "%' order by publicTime desc";

		return trainWeDao.find(hql,new Object[]{},pageBean);
	}

}
