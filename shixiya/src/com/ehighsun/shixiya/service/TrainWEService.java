package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWETeacher;

public interface TrainWEService {
	@Transactional
	public TrainWEProduct findTrainWeProductById(Integer id);
	@Transactional
	public List<TrainWEProduct> getAllTrainWEProducts();
	
	@Transactional
	public List<TrainWEProduct> getAllTrainWEProducts(PageBean pageBean);

	
	@Transactional
	public List<TrainWEProduct> getAllTrainWEProducts(
			HttpServletRequest request, String page);

	@Transactional
	public TrainWEProduct getTrainWeById(Integer id);

	@Transactional
	public void addTrainWEProduct(TrainWEProduct trainWEProduct);

	@Transactional
	public void updateTrainWEProduct(TrainWEProduct trainWEProduct);

	@Transactional
	public void evitTrainWEProduct(TrainWEProduct trainWEProduct);

	@Transactional
	public void deleteTrainWEProductById(Integer id);

	@Transactional
	public void deleteTrainWEProducts(String ids);

	@Transactional
	public List<TrainWEProduct> findTrainWEProductsByCondition(String title,
			Integer teacherId, HttpServletRequest request, String page);
	
	public List<TrainWEProduct> findTrainWeProductByTrainer(
			TrainWETeacher trainer);
	
	@Transactional
	public List<TrainWEProduct> getTrainWEByTimeQuantum(String timeQuantum,PageBean pageBean);
	
	@Transactional
	public List<TrainWEProduct> getTrainWEByLable(String lable,PageBean pageBean);
}
