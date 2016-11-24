package com.ehighsun.shixiya.commonality.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductStudentApply;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductEmploy;
import com.ehighsun.shixiya.pojo.CProductStudentApply;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.service.CProductEmployService;
import com.ehighsun.shixiya.service.ResumeService;
import com.ehighsun.shixiya.util.StringUtil;
import com.ehighsun.shixiya.util.WriteInExcelUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadResumeAction extends ActionSupport {
	// 批量下载
	private List<String> filenameList;
	// 下载单个文件
	private String filename;
	private InputStream stream;

	private Integer resumeId;
	private String resumeIds;

	private Integer broadcastId;
	private Integer courseId;
	private Integer status;// 审核状态

	@Resource(name = "resumeService")
	private ResumeService resumeService;

	@Resource(name = "baseDao")
	private BaseDao<CProductBroadcast> cpBroadcastDao;
	@Resource(name = "baseDao")
	private BaseDao<BProductCourse> bpCourseDao;
	@Resource(name = "cProductEmployService")
	private CProductEmployService cpEmployService;

	// 下载单个文件
	public String downloadResume() throws UnsupportedEncodingException,
			FileNotFoundException {

		List<Resume> resumes = new ArrayList<>();
		Resume indexResume = resumeService.findResumeById(resumeId);
		resumes.add(indexResume);
		filename = "Resume.xls";
		String path = ServletActionContext.getRequest().getRealPath(
				"/resume/tempResume.xls");
		File downFile = WriteInExcelUtil.ResumeToExcel(resumes, path);

		if (!downFile.exists()) {
			ActionContext.getContext().put("msg", "亲,您要下载的文件不存在");
			return "success";
		} else {
			// 返回文件流信息
			stream = new FileInputStream(downFile);
			ActionContext.getContext().put("Exclmsg", "下载成功");
			return "downloadResume";
		}

	}

	// 下载多个简历
	public String downloadResumeList() throws UnsupportedEncodingException,
			FileNotFoundException {

		List<Resume> resumes = new ArrayList<>();

		String[] str = resumeIds.split(",");
		Resume resume = null;
		for (int i = 0; i < str.length; i++) {
			resume = resumeService.findResumeById(Integer.parseInt(str[i]));
			resumes.add(resume);
		}

		filename = "Resume.xls";
		String path = ServletActionContext.getRequest().getRealPath(
				"/resume/tempResume.xls");
		File downFile = WriteInExcelUtil.ResumeToExcel(resumes, path);

		if (!downFile.exists()) {
			ActionContext.getContext().put("msg", "亲,您要下载的文件不存在");
			return "success";
		} else {
			// 返回文件流信息
			stream = new FileInputStream(downFile);
			ActionContext.getContext().put("Exclmsg", "下载成功");
			return "downloadResume";
		}

	}

	// 通过直播间 下载多个简历
	public String downloadResumeByBroadcast()
			throws UnsupportedEncodingException, FileNotFoundException {

		List<Resume> resumes = new ArrayList<>();
		resumes = getResumesList(broadcastId, status, CProductBroadcast.class);
		filename = "BroadcastResume.xls";
		String path = ServletActionContext.getRequest().getRealPath(
				"/resume/tempResume.xls");
		File downFile = WriteInExcelUtil.ResumeToExcel(resumes, path);

		if (!downFile.exists()) {
			ActionContext.getContext().put("msg", "亲,您要下载的文件不存在");
			return "success";
		} else {
			// 返回文件流信息
			stream = new FileInputStream(downFile);
			ActionContext.getContext().put("Exclmsg", "下载成功");
			return "downloadResume";
		}

	}

	// 通过课程 下载多个简历
	public String downloadResumeByCourse() throws UnsupportedEncodingException,
			FileNotFoundException {

		List<Resume> resumes = new ArrayList<>();
		resumes = getResumesList(courseId, status, BProductCourse.class);

		filename = "CourseResume.xls";
		String path = ServletActionContext.getRequest().getRealPath(
				"/resume/tempResume.xls");
		File downFile = WriteInExcelUtil.ResumeToExcel(resumes, path);

		if (!downFile.exists()) {
			ActionContext.getContext().put("msg", "亲,您要下载的文件不存在");
			return "success";
		} else {
			// 返回文件流信息
			stream = new FileInputStream(downFile);
			ActionContext.getContext().put("Exclmsg", "下载成功");
			return "downloadResume";
		}

	}

	// 下载简历，通过状态判断
	// 状态属性 CProductStudentApply的状态属性是 status
	// 状态属性 BProductStudentApply的状态属性是 states
	private List<Resume> getResumesList(Integer id, Integer status,
			Class ProductType) {
		List<Resume> resumes = new ArrayList<>();
		if (ProductType.getSimpleName().equals("CProductBroadcast")) {
			Set<CProductStudentApply> cpStudentApplies = cpBroadcastDao.get(
					CProductBroadcast.class, id).getCProductStudentApplies();

			for (CProductStudentApply cProductStudentApply : cpStudentApplies) {
				if (StringUtil.isNotEmpty(status)) {
					if (status.equals(cProductStudentApply.getStatus())) {
						Set<Resume> applyResumes = cProductStudentApply
								.getStudent().getResumes();

						for (Resume resume1 : applyResumes) {
							resumes.add(resume1);
							break;
						}
					}
					continue;
				}

				Set<Resume> applyResumes = cProductStudentApply.getStudent()
						.getResumes();

				for (Resume resume1 : applyResumes) {
					resumes.add(resume1);
					break;
				}

			}
			System.out.println(resumes.size());
		}
		if (ProductType.getSimpleName().equals("BProductCourse")) {

			Set<BProductStudentApply> bpStudentApplies = bpCourseDao.get(
					BProductCourse.class, courseId).getBProductStudentApplies();

			for (BProductStudentApply bProductStudentApply : bpStudentApplies) {

				if (StringUtil.isNotEmpty(status)) {
					if (status.equals(bProductStudentApply.getStates())) {
						Set<Resume> applyResumes = bProductStudentApply
								.getStudent().getResumes();
						for (Resume resume1 : applyResumes) {
							resumes.add(resume1);
							break;
						}
					}
					continue;
				}
				Set<Resume> applyResumes = bProductStudentApply.getStudent()
						.getResumes();
				for (Resume resume1 : applyResumes) {
					resumes.add(resume1);
					break;
				}

			}
		}
		return resumes;

	}

	// 最终进入线下面试的简历
	public String downloadEmployByBroadcast()
			throws UnsupportedEncodingException, FileNotFoundException {
		List<CProductEmploy> employs = new ArrayList<CProductEmploy>();

		employs = cpEmployService.getCProductEmploysByBroadcast(cpBroadcastDao
				.get(CProductBroadcast.class, broadcastId));

		filename = "FinalEmployList.xls";
		String path = ServletActionContext.getRequest().getRealPath(
				"/resume/tempResume.xls");
		File downFile = WriteInExcelUtil.CPEmployToExcel(employs, path);

		if (!downFile.exists()) {
			ActionContext.getContext().put("msg", "亲,您要下载的文件不存在");
			return "success";
		} else {
			// 返回文件流信息
			stream = new FileInputStream(downFile);
			ActionContext.getContext().put("Exclmsg", "下载成功");
			return "downloadResume";
		}
	}

	public List<String> getFilenameList() {
		return filenameList;
	}

	public void setFilenameList(List<String> filenameList) {
		this.filenameList = filenameList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public String getResumeIds() {
		return resumeIds;
	}

	public void setResumeIds(String resumeIds) {
		this.resumeIds = resumeIds;
	}

	public Integer getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
