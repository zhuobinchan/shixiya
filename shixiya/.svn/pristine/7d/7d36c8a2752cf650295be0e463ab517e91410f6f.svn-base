package com.ehighsun.shixiya.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.pojo.HrCommentList;


public interface HrAdminServer {
	@Transactional
	public List<HrCommentList> hrCommentListFilter(HttpServletRequest request,String title, String hrId,
			String startTime,String page);

	@Transactional
	public void saveHrcommentList(String title, String hrId, String introduction,
			File img,String imgFileName,File video,String videoFileName,Integer sectionId,Integer visitnum,Integer state,Integer mode);

	@Transactional
	public void updateHrcommentList(String id,String title, String hrId, String introduction,
			File img,String imgFileName,File video,String videoFileName,Integer sectionId,Integer visitnum,Integer state,Integer mode);
	
	@Transactional
	public void delHrcommentList(String id);

	@Transactional
	public void delGroupHrcommentList(String idsString);
	
	@Transactional
	public List<HrCommentList> getAll();
	
	@Transactional
	public List<HrCommentList> findHrCommentListByLable(int lableId);
	
	@Transactional
	public List<HrComment> getHrCommentsByHrid(int hrId);

	List<HrCommentList> getHrCommentListsByHrId(Integer hrId);

}
