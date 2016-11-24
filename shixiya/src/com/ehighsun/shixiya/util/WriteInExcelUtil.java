package com.ehighsun.shixiya.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dayee.wintalent.service.v8.pojo.AdditioinalInfos;
import com.dayee.wintalent.service.v8.pojo.BasicInfos;
import com.dayee.wintalent.service.v8.pojo.CareerObjectives;
import com.dayee.wintalent.service.v8.pojo.EducationBackgrounds;
import com.dayee.wintalent.service.v8.pojo.PersonalInformations;
import com.dayee.wintalent.service.v8.pojo.ProjectExperiences;
import com.dayee.wintalent.service.v8.pojo.WorkingExperiences;
import com.ehighsun.shixiya.pojo.CProductEmploy;
import com.ehighsun.shixiya.pojo.DicInfo;
import com.ehighsun.shixiya.pojo.Resume;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.DicInfoService;
import com.opensymphony.xwork2.inject.util.Function;

public class WriteInExcelUtil {

	public static DicInfoService dicInfoService;

	public static File CPEmployToExcel(List<CProductEmploy> employs,
			String tempPath) {
		try {
			WritableWorkbook wwb = null;
			// 创建可写入的Excel工作簿

			File file = new File(tempPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);
			// 创建工作表
			WritableSheet ws = wwb.createSheet("龙虎榜信息", 0);
			// 查询数据库中所有的数据
			// 要插入到的Excel表格的行号，默认从0开始

			List<Label> titleLabels = new ArrayList<Label>();
			titleLabels.add(new Label(0, 0, "录用编号(id)"));
			titleLabels.add(new Label(1, 0, "姓名(name)"));
			titleLabels.add(new Label(2, 0, "手机号码(telephone)"));
			titleLabels.add(new Label(3, 0, "邮箱(email)"));

			for (Label label : titleLabels) {
				ws.addCell(label);
			}

			List<Label> dataLabels = new ArrayList<Label>();

			for (int i = 0; i < employs.size(); i++) {
				dataLabels.add(new Label(0, i + 1, String.valueOf(employs
						.get(i).getId())));
				dataLabels.add(new Label(1, i + 1, employs.get(i).getName()));
				dataLabels.add(new Label(2, i + 1, employs.get(i)
						.getTelephone()));
				dataLabels.add(new Label(3, i + 1, employs.get(i).getEmail()));
			}

			for (Label label : dataLabels) {
				ws.addCell(label);
			}
			// 写进文档
			wwb.write(); // 关闭Excel工作簿对象
			wwb.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static File PersonInfoExcel(List<Student> students,String tempPath) {

		try {
			WritableWorkbook wwb = null;
			// 创建可写入的Excel工作簿

			File file = new File(tempPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);
			// 创建工作表
			WritableSheet ws = wwb.createSheet("学生信息", 0);
			// 查询数据库中所有的数据
			// 要插入到的Excel表格的行号，默认从0开始

			List<Label> titleLabels = new ArrayList<Label>();
			titleLabels.add(new Label(0, 0, "姓名(name)"));
			titleLabels.add(new Label(1, 0, "手机号码(telephone)"));
			titleLabels.add(new Label(2, 0, "邮箱(email)"));

			for (Label label : titleLabels) {
				ws.addCell(label);
			}

			List<Label> dataLabels = new ArrayList<Label>();
			
			System.out.println("students.size():"+students.size());
			for (int i = 0; i < students.size(); i++) {
				System.out.println("students.get(i).getName():"+students.get(i).getName());
				dataLabels.add(new Label(0, i+1, students.get(i).getName()));
				dataLabels.add(new Label(1, i+1, students.get(i).getTelephone()));
				dataLabels.add(new Label(2, i+1, students.get(i).getEmail()));
			}
			

			for (Label label : dataLabels) {
				ws.addCell(label);
			}
			// 写进文档
			wwb.write(); // 关闭Excel工作簿对象
			wwb.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 简历写进excel表格中
	public static File ResumeToExcel(List<Resume> resumes, String tempPath) {// tempPath

		dicInfoService = SpringContextUtil.getBean("dicInfoService");

		try {
			WritableWorkbook wwb = null;
			// 创建可写入的Excel工作簿

			File file = new File(tempPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);
			// 创建工作表
			WritableSheet ws = wwb.createSheet("简历信息", 0);
			// 查询数据库中所有的数据
			// 要插入到的Excel表格的行号，默认从0开始

			List<Label> labels = new ArrayList<Label>();

			labels.add(new Label(0, 0, "编号(id)"));
			labels.add(new Label(1, 0, "简历名称(title)"));
			labels.add(new Label(2, 0, "手机号码(telephone)"));
			labels.add(new Label(3, 0, "身份证(identityCar)"));
			labels.add(new Label(4, 0, "出生日期(birthday)"));
			labels.add(new Label(5, 0, "毕业学校(school)"));
			labels.add(new Label(6, 0, "专业(major)"));
			labels.add(new Label(7, 0, "年级(grade)"));
			labels.add(new Label(8, 0, "简介(introduction)"));
			labels.add(new Label(9, 0, "应聘职位(job)"));
			labels.add(new Label(10, 0, "联系地址(address)"));
			labels.add(new Label(11, 0, "邮箱(email)"));
			labels.add(new Label(12, 0, "姓名(name)"));

			// 大易简历字段

			// 简历基础信息
			List<String> list1 = getName(new BasicInfos());
			int i = 0;
			labels.add(new Label(13, 0, "职位ID(" + list1.get(i++) + ")"));
			labels.add(new Label(14, 0, "招聘项目ID(" + list1.get(i++) + ")"));
			labels.add(new Label(15, 0, "职位名称(" + list1.get(i++) + ")"));
			labels.add(new Label(16, 0, "职位对外名称(" + list1.get(i++) + ")"));
			labels.add(new Label(17, 0, "机构名称(" + list1.get(i++) + ")"));
			labels.add(new Label(18, 0, "添加日期(" + list1.get(i++) + ")"));
			labels.add(new Label(19, 0, "应聘日期(" + list1.get(i++) + ")"));
			labels.add(new Label(20, 0, "招聘类型(" + list1.get(i++) + ")"));
			labels.add(new Label(21, 0, "简历来源(" + list1.get(i++) + ")"));
			labels.add(new Label(22, 0, "渠道ID(" + list1.get(i++) + ")"));
			labels.add(new Label(23, 0, "渠道字典ID(" + list1.get(i++) + ")"));
			labels.add(new Label(24, 0, "渠道名称(" + list1.get(i++) + ")"));
			labels.add(new Label(25, 0, "渠道机构(" + list1.get(i++) + ")"));
			labels.add(new Label(26, 0, "渠道机构名称(" + list1.get(i++) + ")"));
			labels.add(new Label(27, 0, "提交拟录用审核日期(" + list1.get(i++) + ")"));
			labels.add(new Label(28, 0, "拟入职日期(" + list1.get(i) + ")"));

			// 个人基本信息
			List<String> list2 = getName(new PersonalInformations());
			i = 0;
			labels.add(new Label(29, 0, "参加笔试城市(" + list2.get(i++) + ")"));
			labels.add(new Label(30, 0, "学习形式(" + list2.get(i++) + ")"));
			labels.add(new Label(31, 0, "第一学历(" + list2.get(i++) + ")"));
			labels.add(new Label(32, 0, "最高学历(" + list2.get(i++) + ")"));
			labels.add(new Label(33, 0, "接受调剂(" + list2.get(i++) + ")"));
			labels.add(new Label(34, 0, "姓名(" + list2.get(i++) + ")"));
			labels.add(new Label(35, 0, "性别(" + list2.get(i++) + ")"));
			labels.add(new Label(36, 0, "出生日期(" + list2.get(i++) + ")"));
			labels.add(new Label(37, 0, "国籍(" + list2.get(i++) + ")"));
			labels.add(new Label(38, 0, "民族(" + list2.get(i++) + ")"));
			labels.add(new Label(39, 0, "婚姻状况(" + list2.get(i++) + ")"));
			labels.add(new Label(40, 0, "工作年限(" + list2.get(i++) + ")"));
			labels.add(new Label(41, 0, "政治面貌(" + list2.get(i++) + ")"));
			labels.add(new Label(42, 0, "身份证号码(" + list2.get(i++) + ")"));
			labels.add(new Label(43, 0, "证件类型(" + list2.get(i++) + ")"));
			labels.add(new Label(44, 0, "证件号码(" + list2.get(i++) + ")"));
			labels.add(new Label(45, 0, "生源地(" + list2.get(i++) + ")"));
			labels.add(new Label(46, 0, "籍贯(" + list2.get(i++) + ")"));
			labels.add(new Label(47, 0, "现居住地(" + list2.get(i++) + ")"));
			labels.add(new Label(48, 0, "户口所在地(" + list2.get(i++) + ")"));
			labels.add(new Label(49, 0, "学历(" + list2.get(i++) + ")"));
			labels.add(new Label(50, 0, "学位(" + list2.get(i++) + ")"));
			labels.add(new Label(51, 0, "毕业时间(" + list2.get(i++) + ")"));
			labels.add(new Label(52, 0, "学校名(" + list2.get(i++) + ")"));
			labels.add(new Label(53, 0, "专业(" + list2.get(i++) + ")"));
			labels.add(new Label(54, 0, "家庭电话(" + list2.get(i++) + ")"));
			labels.add(new Label(55, 0, "宿舍电话(" + list2.get(i++) + ")"));
			labels.add(new Label(56, 0, "移动电话(" + list2.get(i++) + ")"));
			labels.add(new Label(57, 0, "微信(" + list2.get(i++) + ")"));
			labels.add(new Label(58, 0, "微博(" + list2.get(i++) + ")"));
			labels.add(new Label(59, 0, "QQ(" + list2.get(i++) + ")"));
			labels.add(new Label(60, 0, "MSN(" + list2.get(i++) + ")"));
			labels.add(new Label(61, 0, "电子邮箱(" + list2.get(i++) + ")"));
			labels.add(new Label(62, 0, "通信地址(" + list2.get(i++) + ")"));
			labels.add(new Label(63, 0, "邮编(" + list2.get(i++) + ")"));
			labels.add(new Label(64, 0, "个人主页(" + list2.get(i++) + ")"));
			labels.add(new Label(65, 0, "目前薪资(" + list2.get(i++) + ")"));
			labels.add(new Label(66, 0, "期望薪资(" + list2.get(i++) + ")"));

			//

			labels.add(new Label(67, 0, "自我评价(SelfAssessment)"));

			// 求职意向
			List<String> list3 = getName(new CareerObjectives());
			i = 0;
			labels.add(new Label(68, 0, "期望工作性质(" + list3.get(i++) + ")"));
			labels.add(new Label(69, 0, "期望工作地点(" + list3.get(i++) + ")"));
			labels.add(new Label(70, 0, "期望职能(" + list3.get(i++) + ")"));
			labels.add(new Label(71, 0, "期望行业(" + list3.get(i++) + ")"));
			labels.add(new Label(72, 0, "期望年薪(" + list3.get(i++) + ")"));
			labels.add(new Label(73, 0, "期望薪酬(" + list3.get(i++) + ")"));
			labels.add(new Label(74, 0, "目前薪酬(" + list3.get(i++) + ")"));
			labels.add(new Label(75, 0, "到岗时间(" + list3.get(i++) + ")"));
			labels.add(new Label(76, 0, "当前行业(" + list3.get(i++) + ")"));
			labels.add(new Label(77, 0, "当前职能(" + list3.get(i++) + ")"));

			// 教育经历
			List<String> list4 = getName(new EducationBackgrounds());
			i = 0;
			labels.add(new Label(78, 0, "开始时间(" + list4.get(i++) + ")"));
			labels.add(new Label(79, 0, "结束时间(" + list4.get(i++) + ")"));
			labels.add(new Label(80, 0, "学校(" + list4.get(i++) + ")"));
			labels.add(new Label(81, 0, "城市(" + list4.get(i++) + ")"));
			labels.add(new Label(82, 0, "学位(" + list4.get(i++) + ")"));
			labels.add(new Label(83, 0, "学历(" + list4.get(i++) + ")"));
			labels.add(new Label(84, 0, "专业(" + list4.get(i++) + ")"));
			labels.add(new Label(85, 0, "专业描述(" + list4.get(i++) + ")"));

			labels.add(new Label(86, 0, "培训经历(Training)"));
			labels.add(new Label(87, 0, "英语能力(EnglishProficiency)"));
			labels.add(new Label(88, 0, "其他外语能力(OtherLanguageSkills)"));
			labels.add(new Label(89, 0, "计算机技能(ITSkills)"));

			labels.add(new Label(90, 0, "工作经历(WorkingExperience)"));

			labels.add(new Label(91, 0, "奖励活动(HonorsAndRewards)"));
			labels.add(new Label(92, 0, "家庭关系(FamilyRelationship)"));

			// 附加信息
			labels.add(new Label(93, 0, "信息名称(Title)"));
			labels.add(new Label(94, 0, "信息内容(Description)"));

			labels.add(new Label(95, 0, "专业技能(ProfessionalSkills)"));
			labels.add(new Label(96, 0, "证书(Certificate)"));
			labels.add(new Label(97, 0, "语言能力(Language)"));

			labels.add(new Label(98, 0, "项目经验(ProjectExperience)"));

			labels.add(new Label(99, 0, "简历链接(resumeurl)"));
			labels.add(new Label(100, 0, "入职申请表链接(ApplicationUrl)"));

			for (Label label2 : labels) {

				ws.addCell(label2);
			}

			List<Label> resumeDate = new ArrayList<Label>();
			for (int k = 0; k < resumes.size(); k++) {

				resumeDate
						.add(new Label(0, k + 1, resumes.get(k).getId() + ""));
				resumeDate.add(new Label(1, k + 1, resumes.get(k).getTitle()));
				resumeDate.add(new Label(2, k + 1, resumes.get(k)
						.getTelephone()));
				resumeDate.add(new Label(3, k + 1, resumes.get(k)
						.getIdentityCar()));
				resumeDate
						.add(new Label(4, k + 1, resumes.get(k).getBirthday()));
				resumeDate.add(new Label(5, k + 1, resumes.get(k).getSchool()));
				resumeDate.add(new Label(6, k + 1, resumes.get(k).getMajor()));
				resumeDate.add(new Label(7, k + 1, resumes.get(k).getGrade()));
				resumeDate.add(new Label(8, k + 1, resumes.get(k)
						.getIntroduction()));
				resumeDate.add(new Label(9, k + 1, resumes.get(k).getJob()));
				resumeDate
						.add(new Label(10, k + 1, resumes.get(k).getAddress()));
				resumeDate.add(new Label(11, k + 1, resumes.get(k).getEmail()));
				resumeDate.add(new Label(12, k + 1, resumes.get(k).getName()));

				String basicInfo = resumes.get(k).getBasicInfo();
				if (basicInfo != null && !"".equals(basicInfo)) {
					JSONObject obj = new JSONObject().fromObject(basicInfo);
					BasicInfos b = (BasicInfos) JSONObject.toBean(obj,
							BasicInfos.class);
					resumeDate.add(new Label(13, k + 1, b.getPostId()));
					resumeDate.add(new Label(14, k + 1, b.getProjectId()));
					resumeDate.add(new Label(15, k + 1, b.getPostName()));
					resumeDate
							.add(new Label(16, k + 1, b.getExternalPostName()));
					resumeDate.add(new Label(17, k + 1, b.getPostOrgName()));
					resumeDate.add(new Label(18, k + 1, b.getAddDate()));
					resumeDate.add(new Label(19, k + 1, b.getApplyDate()));
					String type = getType(b.getRecruitType());
					resumeDate.add(new Label(20, k + 1, type));
					resumeDate.add(new Label(21, k + 1, b.getResumeSource()));
					resumeDate.add(new Label(22, k + 1, b.getNetChannelId()));
					resumeDate.add(new Label(23, k + 1, b.getChannelDicId()));
					resumeDate.add(new Label(24, k + 1, b.getChannelName()));
					resumeDate.add(new Label(25, k + 1, b.getChannelOrgId()));
					resumeDate.add(new Label(26, k + 1, b.getChannelOrgName()));
					resumeDate.add(new Label(27, k + 1, b
							.getSubmitPreHireAuditDate()));
					resumeDate.add(new Label(28, k + 1, b
							.getExpectedRecruitment()));
				} else {
					resumeDate.add(new Label(13, k + 1, ""));
					resumeDate.add(new Label(14, k + 1, ""));
					resumeDate.add(new Label(15, k + 1, ""));
					resumeDate.add(new Label(16, k + 1, ""));
					resumeDate.add(new Label(17, k + 1, ""));
					resumeDate.add(new Label(18, k + 1, ""));
					resumeDate.add(new Label(19, k + 1, ""));
					resumeDate.add(new Label(20, k + 1, ""));
					resumeDate.add(new Label(21, k + 1, ""));
					resumeDate.add(new Label(22, k + 1, ""));
					resumeDate.add(new Label(23, k + 1, ""));
					resumeDate.add(new Label(24, k + 1, ""));
					resumeDate.add(new Label(25, k + 1, ""));
					resumeDate.add(new Label(26, k + 1, ""));
					resumeDate.add(new Label(27, k + 1, ""));
					resumeDate.add(new Label(28, k + 1, ""));
				}

				String pInfor = resumes.get(k).getPersonalInformation();
				if (pInfor != null && !"".equals(pInfor)) {

					JSONObject obj1 = new JSONObject().fromObject(pInfor);
					PersonalInformations pi = (PersonalInformations) JSONObject
							.toBean(obj1, PersonalInformations.class);
					resumeDate.add(new Label(29, k + 1, pi
							.getWrittenExaminationCity()));
					resumeDate.add(new Label(30, k + 1, pi.getStudyType()));
					resumeDate.add(new Label(31, k + 1, pi.getFirstDegree()));
					resumeDate.add(new Label(32, k + 1, getValue(pi
							.getHighestDegree())));
					resumeDate.add(new Label(33, k + 1, pi
							.getAcceptAdjustment()));
					resumeDate.add(new Label(34, k + 1, pi.getName()));
					resumeDate.add(new Label(35, k + 1,
							getValue(pi.getGender())));
					resumeDate.add(new Label(36, k + 1, pi.getDateOfBirth()));
					resumeDate.add(new Label(37, k + 1, getValue(pi
							.getNationality())));
					resumeDate.add(new Label(38, k + 1,
							getValue(pi.getNation())));
					resumeDate.add(new Label(39, k + 1, pi.getMaritalStatus()));
					resumeDate.add(new Label(40, k + 1, getValue(pi
							.getWorkYear())));
					resumeDate
							.add(new Label(41, k + 1, pi.getPoliticalStatus()));
					resumeDate.add(new Label(42, k + 1, pi.getIDNUM()));
					resumeDate.add(new Label(43, k + 1, pi.getIDType()));
					resumeDate.add(new Label(44, k + 1, pi.getIDNumber()));
					resumeDate.add(new Label(45, k + 1, pi.getOriginPlace()));
					resumeDate.add(new Label(46, k + 1, pi.getHometown()));
					resumeDate.add(new Label(47, k + 1, getValue(pi
							.getCurrentCity())));
					resumeDate.add(new Label(48, k + 1, pi.getHUKOU()));
					resumeDate.add(new Label(49, k + 1, pi.getDiploma()));
					resumeDate.add(new Label(50, k + 1, pi.getDegree()));
					resumeDate.add(new Label(51, k + 1, pi.getGraduateDate()));
					resumeDate.add(new Label(52, k + 1, pi.getSchoolName()));
					resumeDate
							.add(new Label(53, k + 1, getValue(pi.getMajor())));
					resumeDate.add(new Label(54, k + 1, pi.getTelHome()));
					resumeDate.add(new Label(55, k + 1, pi.getTelDomitory()));
					resumeDate.add(new Label(56, k + 1, pi.getMobilePhone()));
					resumeDate.add(new Label(57, k + 1, pi.getWeChat()));
					resumeDate.add(new Label(58, k + 1, pi.getMicroBlog()));
					resumeDate.add(new Label(59, k + 1, pi.getQQ()));
					resumeDate.add(new Label(60, k + 1, pi.getMSN()));
					resumeDate.add(new Label(61, k + 1, pi.getEmail()));
					resumeDate.add(new Label(62, k + 1, pi.getAddress()));
					resumeDate.add(new Label(63, k + 1, pi.getZipCode()));
					resumeDate.add(new Label(64, k + 1, pi
							.getPersonalHomepageBlog()));
					resumeDate.add(new Label(65, k + 1, pi.getCurrentSalary()));
					resumeDate
							.add(new Label(66, k + 1, pi.getExpectedSalary()));
				}
				// else{
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				//
				// }

				resumeDate.add(new Label(67, k + 1, resumes.get(k)
						.getSelfAssessment()));

				String careerObjective = resumes.get(k).getCareerObjective();
				if (careerObjective != null && !"".equals(careerObjective)) {
					JSONObject obj2 = new JSONObject()
							.fromObject(careerObjective);
					CareerObjectives co = (CareerObjectives) JSONObject.toBean(
							obj2, CareerObjectives.class);
					resumeDate.add(new Label(68, k + 1, co
							.getDesiredTypeOfEmployment()));
					resumeDate.add(new Label(69, k + 1, getValue(co
							.getDesiredLocation())));
					resumeDate.add(new Label(70, k + 1, getValue(co
							.getDesiredPosition())));
					resumeDate.add(new Label(71, k + 1, getValue(co
							.getDesiredIndustry())));
					resumeDate.add(new Label(72, k + 1, getValue(co
							.getExpectedAnnualSalary())));
					resumeDate.add(new Label(73, k + 1, co
							.getExpectedSalaryBeforeTax()));
					resumeDate.add(new Label(74, k + 1, getValue(co
							.getCurrentSalary())));
					resumeDate.add(new Label(75, k + 1, getValue(co
							.getOnBoardTime())));
					resumeDate.add(new Label(76, k + 1, getValue(co
							.getCurrentIndustry())));
					resumeDate.add(new Label(77, k + 1, getValue(co
							.getCurrentFunction())));
				}
				// else{
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// }

				String educationBackground = resumes.get(k)
						.getEducationBackground();
				if (educationBackground != null
						&& !"".equals(educationBackground)) {
					JSONObject obj3 = new JSONObject()
							.fromObject(educationBackground);
					EducationBackgrounds eb = (EducationBackgrounds) JSONObject
							.toBean(obj3, EducationBackgrounds.class);
					resumeDate.add(new Label(78, k + 1, eb.getStartDate()));
					resumeDate.add(new Label(79, k + 1, eb.getEndDate()));
					resumeDate.add(new Label(80, k + 1, eb.getSchoolName()));
					resumeDate
							.add(new Label(81, k + 1, getValue(eb.getCity())));
					resumeDate.add(new Label(82, k + 1, eb.getDegree()));
					resumeDate.add(new Label(83, k + 1, getValue(eb
							.getDiploma())));
					resumeDate
							.add(new Label(84, k + 1, getValue(eb.getMajor())));
					resumeDate.add(new Label(85, +1, eb.getMajorDescription()));
				}
				// else{
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				//
				// }

				resumeDate.add(new Label(86, k + 1, resumes.get(k)
						.getTraining()));
				resumeDate.add(new Label(87, k + 1, resumes.get(k)
						.getEnglishProficiency()));
				resumeDate.add(new Label(88, k + 1, resumes.get(k)
						.getOtherLanguageSkills()));
				resumeDate.add(new Label(89, k + 1, resumes.get(k)
						.getiTSkills()));

				String workingExperience = resumes.get(k)
						.getWorkingExperience();
				if (workingExperience != null && !"".equals(workingExperience)) {
					JSONArray arr = new JSONArray()
							.fromObject(workingExperience);
					List<WorkingExperiences> we = (List<WorkingExperiences>) arr
							.toCollection(arr, WorkingExperiences.class);
					String weStr = "";
					for (WorkingExperiences workingExperiences : we) {
						weStr = weStr + workingExperiences.toString();
					}
					resumeDate.add(new Label(90, k + 1, weStr));
				}
				// else{
				// resumeDate.add(new Label(0, k + 1,""));
				// }

				resumeDate.add(new Label(91, k + 1, resumes.get(k)
						.getHonorsAndRewards()));
				resumeDate.add(new Label(92, k + 1, resumes.get(k)
						.getFamilyRelationship()));

				String additioinalInfo = resumes.get(k).getAdditioinalInfo();
				if (additioinalInfo != null && !"".equals(additioinalInfo)) {
					JSONObject obj4 = new JSONObject()
							.fromObject(additioinalInfo);
					AdditioinalInfos ai = (AdditioinalInfos) JSONObject.toBean(
							obj4, AdditioinalInfos.class);
					resumeDate.add(new Label(93, k + 1, ai.getTitle()));
					resumeDate.add(new Label(94, k + 1, ai.getDescription()));

				}
				// }else{
				// resumeDate.add(new Label(0, k + 1,""));
				// resumeDate.add(new Label(0, k + 1,""));
				// }

				resumeDate.add(new Label(95, k + 1, resumes.get(k)
						.getProfessionalSkills()));
				resumeDate.add(new Label(96, k + 1, resumes.get(k)
						.getCertificate()));
				resumeDate.add(new Label(97, k + 1, resumes.get(k)
						.getLanguage()));

				String projectExperience = resumes.get(k)
						.getProjectExperience();
				if (projectExperience != null && !"".equals(projectExperience)) {
					JSONArray arr1 = new JSONArray()
							.fromObject(projectExperience);
					List<ProjectExperiences> pe = (List<ProjectExperiences>) arr1
							.toCollection(arr1, ProjectExperiences.class);
					String peStr = "";
					for (ProjectExperiences projectExperiences : pe) {
						peStr = peStr + projectExperiences.toString();
					}
					resumeDate.add(new Label(98, k + 1, peStr));
				}
				// }else{
				// resumeDate.add(new Label(0, k + 1,""));
				// }

				resumeDate.add(new Label(99, k + 1, resumes.get(k)
						.getResumeurl()));
				resumeDate.add(new Label(100, k + 1, resumes.get(k)
						.getApplicationUrl()));

			}
			for (Label label : resumeDate) {
				ws.addCell(label);
			}
			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static List<String> getName(Object o) {

		List<String> list = new ArrayList<String>();

		Field[] fields = o.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {

			list.add(fields[i].getName());
		}
		return list;
	}

	public static String getType(String str) {
		String type = null;
		switch (str) {
		case "1":
			type = "校园";
			break;
		case "2":
			type = "社会";
			break;
		case "3":
			type = "内招";
			break;
		case "4":
			type = "猎头";
			break;
		case "8":
			type = "内部推荐";
			break;
		case "12":
			type = "实习生";
			break;
		case "13":
			type = "海外";
			break;
		default:
			break;
		}

		return type;
	}

	public static String getValue(String str) {

		String nowStr = "";
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null && !"".equals(strs[i])) {
				DicInfo di = dicInfoService.findDicInfoByCode(strs[i]);
				if (di != null) {
					nowStr = nowStr + di.getName() + ",";
				} else {
					nowStr = nowStr;
				}

			} else {
				nowStr = nowStr;
			}
		}
		return nowStr != "" ? nowStr.substring(0, nowStr.length() - 1) : str;
	}

}
