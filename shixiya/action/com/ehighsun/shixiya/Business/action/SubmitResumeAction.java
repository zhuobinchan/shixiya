package com.ehighsun.shixiya.Business.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ehighsun.shixiya.ShowWorld.PoiWordToHtml;
import com.ehighsun.shixiya.pojo.RIBussinessResume;
import com.ehighsun.shixiya.pojo.RecruitResumeSubmit;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.RIBussinessResumeService;
import com.ehighsun.shixiya.service.RecruitResumeSubmitSerivce;
import com.ehighsun.shixiya.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SubmitResumeAction extends ActionSupport {

	public String submitName;
	public String submitEmail;
	public String submitPhone;
	public Integer id;
	public Integer sid;

	public File word;
	private String wordContentType;
	private String wordFileName;

	private RecruitResumeSubmit submit;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public RecruitResumeSubmit getSubmit() {
		return submit;
	}

	public void setSubmit(RecruitResumeSubmit submit) {
		this.submit = submit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public File getWord() {
		return word;
	}

	public void setWord(File word) {
		this.word = word;
	}

	public String getWordContentType() {
		return wordContentType;
	}

	public void setWordContentType(String wordContentType) {
		this.wordContentType = wordContentType;
	}

	public String getWordFileName() {
		return wordFileName;
	}

	public void setWordFileName(String wordFileName) {
		this.wordFileName = wordFileName;
	}

	@Resource(name = "recruitResumeSubmitSerivceImpl")
	private RecruitResumeSubmitSerivce recruitResumeSubmitSerivce;
	@Resource(name = "riBussinessResumeService")
	private RIBussinessResumeService riBussinessResumeService;

	public String showSubmitResume() {

		submit = recruitResumeSubmitSerivce.getRecruitResumeSubmitById(id);

		return "showSubmitResume";
	}

	public String updateSubmitResume() {

		RIBussinessResume resume = null;
		resume = riBussinessResumeService.findResumeByStudentId(sid);
		if (resume == null) {
			resume = new RIBussinessResume();
		}
		updateRecruitResumeSubmit(id);
		List<String> list = saveWord(resume);
		try {
			saveHtml(resume, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Student student = new Student();
		student.setId(sid);
		resume.setStudent(student);

		riBussinessResumeService.addResume(resume);

		return "updateSubmitResume";
	}

	public void updateRecruitResumeSubmit(Integer id) {

		int flag = 0;
		submit = recruitResumeSubmitSerivce.getRecruitResumeSubmitById(id);
		if (submit != null) {
			if (StringUtil.isNotEmpty(submitName)) {
				if (!submitName.equals(submit.getName())
						|| StringUtil.isEmpty(submit.getName())) {
					submit.setName(submitName);
					flag = 1;
				}
			}

			if (StringUtil.isNotEmpty(submitEmail)) {
				if (!submitEmail.equals(submit.getEmail())
						|| StringUtil.isEmpty(submit.getEmail())) {
					submit.setEmail(submitEmail);
					flag = 1;
				}
			}

			if (StringUtil.isNotEmpty(submitPhone)) {
				if (!submitPhone.equals(submit.getTelephone())
						|| StringUtil.isEmpty(submit.getTelephone())) {
					submit.setTelephone(submitPhone);
					flag = 1;
				}
			}
		}

		if (flag == 1) {
			recruitResumeSubmitSerivce.updateRecruitResumeSubmit(submit);
		}

	}

	public List<String> saveWord(RIBussinessResume bussinessResume) {
		List<String> list = new ArrayList<String>();
		if (word != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			if (StringUtil.isNotEmpty(bussinessResume.getWorldUrl())) {

				String path = request.getSession().getServletContext()
						.getRealPath("/" + bussinessResume.getWorldUrl());
				File file = new File(path);
				file.delete();
			}

			String houzhui = wordFileName.substring(wordFileName.indexOf('.'));
			String randomDir = this.generateRandomDir(UUID.randomUUID()
					.toString());
			String path1 = request.getSession().getServletContext()
					.getRealPath("/words/" + randomDir);
			String saveName = UUID.randomUUID().toString();
			String savePath = "words/" + randomDir + "/" + saveName + houzhui;
			File file = new File(path1, saveName + houzhui);
			try {
				String path = file.getCanonicalPath();
				FileUtils.copyFile(word, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			bussinessResume.setWorldUrl(savePath);
			list.add(path1);
			list.add(saveName + houzhui);
			list.add("words/" + randomDir + "/");

		}
		return list;
	}

	public void saveHtml(RIBussinessResume bussinessResume, List<String> list)
			throws Exception {

		if (word != null && list.size() > 0) {
			System.out.println("saveHTML" + "///////");
			HttpServletRequest request = ServletActionContext.getRequest();
			if (StringUtil.isNotEmpty(bussinessResume.getHtmlUrl())) {

				String path = request.getSession().getServletContext()
						.getRealPath("/" + bussinessResume.getHtmlUrl());
				File file = new File(path);
				file.delete();
			}
			if (StringUtil.isNotEmpty(bussinessResume.getImgUrl())) {

				String[] imgs = bussinessResume.getImgUrl().split(";");
				for (int i = 0; i < imgs.length; i++) {
					String path = request.getSession().getServletContext()
							.getRealPath("/" + imgs[i]);
					File file = new File(path);
					file.delete();
				}

			}

			String path = list.get(0) + "/";
			String nameStr = list.get(1);
			String saveName = null;
			List<String> pigs = new ArrayList<String>();
			try {

				pigs = PoiWordToHtml.toHtml(path, nameStr, pigs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (pigs != null) {
				String picPath = reNamePic(pigs, request.getSession()
						.getServletContext().getRealPath("/"), list.get(2));
				bussinessResume.setImgUrl(picPath);

			}
			String savePath = list.get(2) + pigs.get(pigs.size() - 1);
			bussinessResume.setHtmlUrl(savePath);
		}
	}

	public String reNamePic(List<String> list, String path, String savePath)
			throws IOException {

		File file = null;
		String name = null;
		StringBuilder sbuilder = new StringBuilder();
		for (int i = 0; i < list.size() - 1; i++) {
			savePath = savePath + list.get(i);
			sbuilder.append(savePath + ";");
		}
		sbuilder.deleteCharAt(sbuilder.length() - 1);
		return sbuilder.toString();

	}

	public String generateRandomDir(String uuidFileName) {
		// 获得唯一文件名的hashcode
		int hashcode = uuidFileName.hashCode();
		// 获得一级目录
		int d1 = hashcode & 0xf;
		// 获得二级目录
		int d2 = (hashcode >>> 4) & 0xf;

		return d2 + "/" + d1;// 共有256目录l
	}

}
