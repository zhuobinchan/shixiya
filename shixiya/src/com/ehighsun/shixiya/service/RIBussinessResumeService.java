package com.ehighsun.shixiya.service;

import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.pojo.RIBussinessResume;

public interface RIBussinessResumeService {

	@Transactional
	public RIBussinessResume findResumeByStudentId(Integer sid);

	@Transactional
	public void addResume(RIBussinessResume resume);

}
