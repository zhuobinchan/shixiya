package com.ehighsun.shixiya.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dayee.wintalent.service.v8.ExternalResumeService;
import com.dayee.wintalent.service.v8.ExternalResumeServicePortType;
import com.dayee.wintalent.service.v8.pojo.PersonalInformations;
import com.dayee.wintalent.service.v8.pojo.ResumeContents;
import com.dayee.wintalent.service.v8.pojo.ResumeList;
import com.dayee.wintalent.service.v8.pojo.Resumes;
import com.ehighsun.shixiya.pojo.DicInfo;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.service.AdminStudentService;
import com.ehighsun.shixiya.service.DicInfoService;
import com.ehighsun.shixiya.service.ResumeService;

public class ThirdPartyUtil {

	private static ResumeService resumeService;
	private static DicInfoService dicInfoService;
	private static AdminStudentService studentService;

	public static String getResume(String size) throws SAXException,
			IOException, ParserConfigurationException {

		System.out.println("开始执行");
		resumeService = SpringContextUtil.getBean("resumeService");
		dicInfoService = SpringContextUtil.getBean("dicInfoService");

		ExternalResumeService service = new ExternalResumeService();

		ExternalResumeServicePortType externalResumeServicePortType = service
				.getExternalResumeServiceHttpPort();

		String result = externalResumeServicePortType
				.readEntryInformation("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<Condition>"
						+ "<corpCode><![CDATA[highsun]]></corpCode>"
						+ "<userName><![CDATA[highsun]]></userName>"
						+ "<password><![CDATA[s59WJu7vTVELIjcJ]]></password>"
						+ "<applyStatus><![CDATA[1,2,3,7,9]]></applyStatus>"
						+ "<currentPage><![CDATA[1]]></currentPage>"
						+ "<rowSize><![CDATA["
						+ size
						+ "]]></rowSize>"
						+ "<cType><![CDATA[1]]></cType>" + "</Condition>");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(result)));
		Element root = doc.getDocumentElement();

		if (root.getAttribute("code").equals("2")) {

			System.out.println("已无简历");
			return "999";// 无简历

		} else {

			ResumeList rl = new ResumeList();
			String applyIds = getApplyIds(root);
			if (!applyIds.equals("")) {

				JAXBContext context = null;
				try {
					context = JAXBContext.newInstance(ResumeList.class);
					Unmarshaller unmarshaller = context.createUnmarshaller();
					rl = (ResumeList) unmarshaller.unmarshal(new StringReader(
							result));
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				try {
					saveResume(rl, applyIds.split(","));
				} catch (ConstraintViolationException e) {
					System.out.println("从大易插入简历时,sql重复插入,正常的异常！");
				}

				 String status = getStatus(applyIds,
				 externalResumeServicePortType);
				 switch (status) {
				 case "00":
				
				 try {
				 saveResume(rl, applyIds.split(","));
				 } catch (ConstraintViolationException e) {
				 System.out.println("从大易插入简历时,sql重复插入,正常的异常！");
				 }
				 return "00";// 成功
				
				 default:
				 return status;
				 }

			}
			return "";
		}

	}

	public static String getApplyIds(Element root) {

		NodeList resumes = root.getElementsByTagName("Resume");
		String applyIds = "";
		for (int i = 0; i < resumes.getLength(); i++) {

			Element resume = (Element) resumes.item(i);
			applyIds = applyIds + resume.getAttribute("applyId");
			applyIds = applyIds + ",";

		}
		applyIds = applyIds.substring(0, applyIds.length() - 1);

		return applyIds;
	}

	public static String getResumeXmL(
			ExternalResumeServicePortType externalResumeServicePortType) {

		String result = externalResumeServicePortType
				.readEntryInformation("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<Condition>"
						+ "<corpCode><![CDATA[highsun]]></corpCode>"
						+ " <userName><![CDATA[highsun]]></userName>"
						+ " <password><![CDATA[s59WJu7vTVELIjcJ]]></password>"
						+ "<applyStatus><![CDATA[1,2,3,7,9]]></applyStatus>"
						+ "<currentPage><![CDATA[1]]></currentPage>"
						+ "<rowSize><![CDATA[5]]></rowSize>"
						+ "<cType><![CDATA[1]]></cType>" + "</Condition>");

		return result;
	}

	public static void saveResume(ResumeList rl, String[] applyId) {
		studentService = SpringContextUtil.getBean("adminStudentService");

		int i = 0;
		List<Resumes> resumes = rl.getResume();
		if (resumes != null) {

			for (Resumes resume : resumes) {

				Resume r = new Resume();
				JSONObject jsonBasicInfo = JSONObject.fromObject(resume
						.getBasicInfo());
				String basicInfo = jsonBasicInfo.toString();
				r.setBasicInfo(basicInfo);

				ResumeContents resumecontent = resume.getResumeContent();

				PersonalInformations personalInformation = resumecontent
						.getPersonalInformation();
				JSONObject jsonPersonalInformation = JSONObject
						.fromObject(personalInformation);
				String personalInformationstr = jsonPersonalInformation
						.toString();
				String name = personalInformation.getName();
				String email = personalInformation.getEmail();
				String phone = personalInformation.getMobilePhone();
				String photo = personalInformation.getPhoto();
				r.setSchool(personalInformation.getSchoolName());

				if (personalInformation.getMajor() != null
						&& !"".equals(personalInformation.getMajor())) {

					if (personalInformation.getMajor().startsWith("0/")) {
						DicInfo dicInfo = dicInfoService
								.findDicInfoByCode(personalInformation
										.getMajor());
						if (dicInfo != null) {
							r.setMajor(dicInfo.getName());

						}
					} else {
						r.setMajor(personalInformation.getMajor());

					}

				}

				r.setGraduatTime(personalInformation.getGraduateDate());
				r.setPersonalInformation(personalInformationstr);
				r.setName(name);
				r.setTelephone(phone);
				r.setEmail(email);
				r.setPhoto(photo);
				r.setSelfAssessment(resumecontent.getSelfAssessment());

				JSONObject jsonCareerObjectives = JSONObject
						.fromObject(resumecontent.getCareerObjective());
				r.setCareerObjective(jsonCareerObjectives.toString());

				JSONObject jsonEducationBackgrounds = JSONObject
						.fromObject(resumecontent.getEducationBackground());
				r.setEducationBackground(jsonEducationBackgrounds.toString());

				r.setTraining(resumecontent.getTraining());
				r.setEnglishProficiency(resumecontent.getEnglishProficiency());
				r.setOtherLanguageSkills(resumecontent.getOtherLanguageSkills());
				r.setiTSkills(resumecontent.getITSkills());

				String workingExperienceStr = JSONArray.fromObject(
						resumecontent.getWorkingExperience()).toString();
				r.setWorkingExperience(workingExperienceStr);

				r.setHonorsAndRewards(resumecontent.getHonorsAndRewards());
				r.setFamilyRelationship(resumecontent.getFamilyRelationship());

				r.setAdditioinalInfo(JSONObject.fromObject(
						resumecontent.getAdditioinalInfo()).toString());

				r.setProfessionalSkills(resumecontent.getProfessionalSkills());
				r.setCertificate(resumecontent.getCertificate());
				r.setLanguage(resumecontent.getLanguage());

				r.setProjectExperience(JSONArray.fromObject(
						resumecontent.getProjectExperience()).toString());

				r.setResumeurl(resumecontent.getResumeurl());
				r.setApplicationUrl(resumecontent.getApplicationUrl());

				r.setApplyId(applyId[i++]);
				r.setStudent(studentService.getStudentByResume(name, phone,
						email));

				resumeService.addResume(r);

			}

		}

	}

	public static String getStatus(String applyIds,
			ExternalResumeServicePortType externalResumeServicePortType) {

		String status = "";
		status = externalResumeServicePortType
				.callbackToChangeStatus("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<Condition>"
						+ " <corpCode><![CDATA[highsun]]></corpCode>"
						+ " <userName><![CDATA[highsun]]></userName>"
						+ " <password><![CDATA[s59WJu7vTVELIjcJ]]></password>"
						+ "<applyIds><![CDATA["
						+ applyIds
						+ "]]></applyIds>"
						+ "<cType><![CDATA[1]]></cType>"
						+ "<result><![CDATA[0]]></result>" + "</Condition>");

		return status;
	}

}
