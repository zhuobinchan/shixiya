package com.ehighsun.shixiya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;

public interface CProductBroadcastService {

	@Transactional
	public List<CProductBroadcast> findByCondition(String name, Integer hrId,
			HttpServletRequest request, String page);

	@Transactional
	public List<CProductBroadcast> findBroadcastsByLable(String lableName);

	@Transactional
	public CProductBroadcast findById(int id);

	@Transactional
	public List<CProductBroadcast> getAllCPBroadcast();

	@Transactional
	public List<CProductBroadcast> getAllCPBroadcast(PageBean pageBean);

	@Transactional
	public void saveOrUpdateCPBroadcast(CProductBroadcast cpBroadcast);

	@Transactional
	public void deleteCPBroadcastById(Integer id);// 通过主键删除

	@Transactional
	public List<CProductBroadcast> getCProductBroadcastByStudent(Student student);

	@Transactional
	public List<CProductBroadcast> getCPBroadcastByHrId(Integer hrId);

	@Transactional
	public void deleteBroadcastList(String ids);

	@Transactional
	/* 通过标签字符窜 获取直播间信息 */
	public List<CProductBroadcast> getBroadcastByLableNames(String lableNames);

	@Transactional
	/* 判断观看某直播是否报名或报名成功 */
	public String judgeApplyStatus(int studentId, int broadcastId);

	@Transactional
	/* 判断观看某直播是否报名或报名成功 */
	public void saveCPBroadcast(CProductBroadcast cpBroadcast);

	@Transactional
	public Long countBroadcastMap(String name, Integer hrId);

	@Transactional
	Long countBroadcast(String hql);

}
