package com.ehighsun.shixiya.Business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.commonality.action.BaseAction;
import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RIBussiness;
import com.ehighsun.shixiya.pojo.RIBussinessResume;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.RIBussinessResumeService;
import com.ehighsun.shixiya.service.RecruitResumeSubmitSerivce;
import com.ehighsun.shixiya.service.RecruitmentInfoService;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.ResponseUtil;
import com.ehighsun.shixiya.util.StringUtil;
import com.ehighsun.shixiya.util.WriteInExcelUtil;
import com.ehighsun.shixiya.util.ZipFileUtil;

public class RecruitResumeSubmitAction extends BaseAction<RecruitResumeSubmit> {

	private String page;
	private String ids;
	private String pageCode;
	public List<RecruitmentInfo> rilist;
	public List<RecruitResumeSubmit> rrsList;
	public Integer sid;

	public String submitName;
	public String submitEmail;
	public String submitPhone;
	public Integer businessId;

	public String fileName;
	public InputStream fileInputStream;
	
	private Student student;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getSubmitName() {
		return submitName;
	}

	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}

	public String getSubmitEmail() {
		return submitEmail;
	}

	public void setSubmitEmail(String submitEmail) {
		this.submitEmail = submitEmail;
	}

	public String getSubmitPhone() {
		return submitPhone;
	}

	public void setSubmitPhone(String submitPhone) {
		this.submitPhone = submitPhone;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public List<RecruitmentInfo> getRilist() {
		return rilist;
	}

	public void setRilist(List<RecruitmentInfo> rilist) {
		this.rilist = rilist;
	}

	public List<RecruitResumeSubmit> getRrsList() {
		return rrsList;
	}

	public void setRrsList(List<RecruitResumeSubmit> rrsList) {
		this.rrsList = rrsList;
	}

	@Resource(name = "recruitmentInfoServiceImpl")
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name = "recruitResumeSubmitSerivceImpl")
	private RecruitResumeSubmitSerivce recruitResumeSubmitSerivce;
	@Resource(name = "riBussinessResumeService")
	private RIBussinessResumeService riBussinessResumeService;
	@Resource(name="baseDao")
	private BaseDao<Student> studentDao;

	public String showRecruitResumeSubmit() {

		RIBussiness bussiness = (RIBussiness) this.getSession().getAttribute(
				"companyer");
		Integer id = bussiness.getId();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RecruitResumeSubmit.jsp");

		Map<String, String> order = new HashMap<String, String>();
		order.put("time", "DESC");
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("businessId", id);

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		rrsList = recruitResumeSubmitSerivce.findByCondition(
				"from RecruitResumeSubmit", o, order, pageBean);
		Long count = recruitResumeSubmitSerivce
				.countRecruitResumeSubmit("select count(*) from RecruitResumeSubmit where businessId = "
						+ id);
		rilist = recruitmentInfoService
				.findRecruitmentInfoByRIBussinessId(bussiness.getId());
		pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/bussinessSubmit_showRecruitResumeSubmit.action",
				count, Integer.parseInt(page), 6, null);

		return "showRecruitResumeSubmit";
	}

	public String deleteRecruitResumeSubmit() {

		recruitResumeSubmitSerivce.deleteRecruitResumeSubmit(model.getId());
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String deleteRecruitResumeSubmits() {

		recruitResumeSubmitSerivce.deleteRecruitResumeSubmits(ids.split(","));
		JSONObject result = new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String findByCondition() {

		RIBussiness bussiness = (RIBussiness) this.getSession().getAttribute(
				"companyer");

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("mainPage", "RecruitResumeSubmit.jsp");

		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		RIBussiness ribussiness = (RIBussiness) this.getSession().getAttribute(
				"companyer");
		Integer id = ribussiness.getId();
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Map<String, Object> o = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();

		o.put("businessId", id);
		if (submitName != null && !"".equals(submitName)) {
			o.put("name", submitName);
			sb.append("submitName=" + submitName);
		}

		if (submitEmail != null && !"".equals(submitEmail)) {
			o.put("email", submitEmail);
			if (sb.toString() != null) {
				sb.append("&submitEmail=" + submitEmail);
			} else {
				sb.append("submitEmail=" + submitEmail);
			}
		}

		if (submitPhone != null && !"".equals(submitPhone)) {
			o.put("telephone", submitPhone);
			if (sb.toString() != null) {
				sb.append("&submitPhone=" + submitPhone);
			} else {
				sb.append("submitPhone=" + submitPhone);
			}
		}

		if (businessId != null) {
			RecruitmentInfo info = new RecruitmentInfo();
			info.setId(businessId);
			o.put("recruitmentInfo", info);
			if (sb.toString() != null) {
				sb.append("&businessId=" + businessId);
			} else {
				sb.append("businessId=" + businessId);
			}
		}

		Map<String, String> order = new HashMap<String, String>();
		order.put("time", "DESC");

		rrsList = recruitResumeSubmitSerivce.findByCondition(
				"from RecruitResumeSubmit", o, order, pageBean);
		Long count = recruitResumeSubmitSerivce.countByCondition(
				"select count(*) from RecruitResumeSubmit", o);
		rilist = recruitmentInfoService
				.findRecruitmentInfoByRIBussinessId(bussiness.getId());
		pageCode = PageUtil
				.genPagination(
						request.getContextPath()
								+ "/admin/bussinessSubmit_findByCondition.action"
								+ sb.toString(), count, Integer.parseInt(page),
						6, null);

		return "findByCondition";
	}

	public String visit() {

		RIBussinessResume resume = riBussinessResumeService
				.findResumeByStudentId(sid);
		if (resume != null) {

			JSONObject result = new JSONObject();
			result.put("resume", resume.getHtmlUrl());
			try {
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			JSONObject result = new JSONObject();
			result.put("resume", null);
			try {
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public String downLoadResume() {

		HttpServletRequest request = ServletActionContext.getRequest();
		RIBussinessResume resume = riBussinessResumeService
				.findResumeByStudentId(sid);
		if (resume != null) {
			List<RecruitResumeSubmit> lists = recruitResumeSubmitSerivce
					.findSubmitByStudentId(sid);
			if (StringUtil.isNotEmpty(lists.get(0).getName())) {
				fileName = lists.get(0).getName() + ".doc";
				try {
					fileName = new String(fileName.getBytes(), "ISO8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			String path1 = resume.getWorldUrl();
			if (path1 != null) {
				try {
					fileInputStream = new FileInputStream(new File(path, path1));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return "downLoadResume";
	}

	public String downLoadResumes() throws FileNotFoundException {

		HttpServletRequest request = ServletActionContext.getRequest();
		String zippath = request.getSession().getServletContext()
				.getRealPath("/zip");
		File pathFile = new File(zippath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File file = new File(pathFile, UUID.randomUUID().toString() + ".zip");
		String zipName = null;
		try {
			zipName = file.getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!file.exists()) {
			try {
				System.out.println(file.getCanonicalPath());
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fileName = "all.zip";
		String path = request.getSession().getServletContext().getRealPath("/");
		String[] item = ids.split(",");
		File[] files = new File[item.length];
		RecruitResumeSubmit submit = null;
		RIBussinessResume resume = null;
		for (int i = 0; i < files.length; i++) {
			submit = recruitResumeSubmitSerivce
					.getRecruitResumeSubmitById(Integer.parseInt(item[i]));
			if (submit != null) {
				resume = riBussinessResumeService.findResumeByStudentId(submit
						.getStudent().getId());
				if (StringUtil.isNotEmpty(resume.getWorldUrl())) {
					files[i] = new File(path, resume.getWorldUrl());
				}
			}

		}

		ZipFileUtil.compressFiles2Zip(files, zipName);
		fileInputStream = new FileInputStream(file);

		return "downLoadResume";
	}

	
	
	
	public String downLoadPersonInfo() throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();

//		Student student = studentDao.get(Student.class, sid);
		List<Student> students = studentDao.find("from Student student where student.id in ("+ids+")");
		
		if (students != null) {
			
			String zippath = request.getSession().getServletContext()
					.getRealPath("/zip");

			fileName = "招聘学生信息" + ".xls";
			
	

			try {
					File file = WriteInExcelUtil.PersonInfoExcel(students, zippath+"/"+fileName);
					System.out.println("fileUrl:"+file.getCanonicalPath());
					fileInputStream = new FileInputStream(file);

					fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			}
		}

		return "downLoadPersonInfo";
	}
	
	
	
//	public String downLoadPersonInfos() throws IOException {
//
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		
//		
//		List<Student> students = studentDao.find("from Student where id in ("+ids+")");
//		
//		
//		if (students != null) {
//			
//			String zippath = request.getSession().getServletContext()
//					.getRealPath("/zip");
//
//			fileName = "招聘学生信息" + ".xls";
//			
//	
//
//			try {
//					File file = WriteInExcelUtil.PersonInfoExcel(null,students,zippath+"/"+fileName);
//					System.out.println("fileUrl:"+file.getCanonicalPath());
//					fileInputStream = new FileInputStream(file);
//
//					fileName = new String(fileName.getBytes(), "ISO8859-1");
//			} catch (FileNotFoundException e) {
//					e.printStackTrace();
//			}
//		}
//
//		return "downLoadPersonInfo";
//	}
}
