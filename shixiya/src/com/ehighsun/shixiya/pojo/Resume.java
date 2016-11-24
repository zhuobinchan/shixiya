package com.ehighsun.shixiya.pojo;

// default package

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class Resume implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private String title;
	private String uploadTime;
	private String birthday;
	private String school;// 学校的数据库字段改为毕业学校
	private String major;
	private String grade;
	private String introduction;// 自我评价
	private String job;
	private String address;
	private String email;
	private String telephone;
	private String identityCar;
	private String name;
	private String graduatTime;

	// 大易系统简历字段修改，主要是进行字段的添加
	private String applyId;// 简历Id

	private String basicInfo;// 简历基本信息

	private String personalInformation; // 个人基本信息

	private String selfAssessment; // 自我评价

	private String careerObjective;// 求职意向

	private String educationBackground;// 教育经历

	private String training;// 培训经历

	private String englishProficiency;// 英语能力

	private String otherLanguageSkills;// 其他外语能力

	private String iTSkills;// 计算机技能

	private String workingExperience;// 工作经历

	private String honorsAndRewards;// 奖励活动

	private String familyRelationship;// 家庭关系

	private String additioinalInfo;// 附加信息

	private String professionalSkills;// 专业技能

	private String certificate;// 证书

	private String language;// 语言能力

	private String projectExperience;// 项目经验

	private String resumeurl;// 简历链接

	private String applicationUrl;// 入职申请表链接

	private String photo;

	/** default constructor */
	public Resume() {
	}

	public Resume(Integer id, Student student, String title, String uploadTime,
			String birthday, String school, String major, String grade,
			String introduction, String job, String address, String email,
			String telephone, String identityCar, String name, String applyId,
			String basicInfo, String personalInformation,
			String selfAssessment, String careerObjective,
			String educationBackground, String training,
			String englishProficiency, String otherLanguageSkills,
			String iTSkills, String workingExperience, String honorsAndRewards,
			String familyRelationship, String additioinalInfo,
			String professionalSkills, String certificate, String language,
			String projectExperience, String resumeurl, String applicationUrl,
			String photo) {
		super();
		this.id = id;
		this.student = student;
		this.title = title;
		this.uploadTime = uploadTime;
		this.birthday = birthday;
		this.school = school;
		this.major = major;
		this.grade = grade;
		this.introduction = introduction;
		this.job = job;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.identityCar = identityCar;
		this.name = name;
		this.applyId = applyId;
		this.basicInfo = basicInfo;
		this.personalInformation = personalInformation;
		this.selfAssessment = selfAssessment;
		this.careerObjective = careerObjective;
		this.educationBackground = educationBackground;
		this.training = training;
		this.englishProficiency = englishProficiency;
		this.otherLanguageSkills = otherLanguageSkills;
		this.iTSkills = iTSkills;
		this.workingExperience = workingExperience;
		this.honorsAndRewards = honorsAndRewards;
		this.familyRelationship = familyRelationship;
		this.additioinalInfo = additioinalInfo;
		this.professionalSkills = professionalSkills;
		this.certificate = certificate;
		this.language = language;
		this.projectExperience = projectExperience;
		this.resumeurl = resumeurl;
		this.applicationUrl = applicationUrl;
		this.photo = photo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityCar() {
		return identityCar;
	}

	public void setIdentityCar(String identityCar) {
		this.identityCar = identityCar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(String basicInfo) {
		this.basicInfo = basicInfo;
	}

	public String getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(String personalInformation) {
		this.personalInformation = personalInformation;
	}

	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}

	public String getCareerObjective() {
		return careerObjective;
	}

	public void setCareerObjective(String careerObjective) {
		this.careerObjective = careerObjective;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getEnglishProficiency() {
		return englishProficiency;
	}

	public void setEnglishProficiency(String englishProficiency) {
		this.englishProficiency = englishProficiency;
	}

	public String getOtherLanguageSkills() {
		return otherLanguageSkills;
	}

	public void setOtherLanguageSkills(String otherLanguageSkills) {
		this.otherLanguageSkills = otherLanguageSkills;
	}

	public String getiTSkills() {
		return iTSkills;
	}

	public void setiTSkills(String iTSkills) {
		this.iTSkills = iTSkills;
	}

	public String getWorkingExperience() {
		return workingExperience;
	}

	public void setWorkingExperience(String workingExperience) {
		this.workingExperience = workingExperience;
	}

	public String getHonorsAndRewards() {
		return honorsAndRewards;
	}

	public void setHonorsAndRewards(String honorsAndRewards) {
		this.honorsAndRewards = honorsAndRewards;
	}

	public String getFamilyRelationship() {
		return familyRelationship;
	}

	public void setFamilyRelationship(String familyRelationship) {
		this.familyRelationship = familyRelationship;
	}

	public String getAdditioinalInfo() {
		return additioinalInfo;
	}

	public void setAdditioinalInfo(String additioinalInfo) {
		this.additioinalInfo = additioinalInfo;
	}

	public String getProfessionalSkills() {
		return professionalSkills;
	}

	public void setProfessionalSkills(String professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProjectExperience() {
		return projectExperience;
	}

	public void setProjectExperience(String projectExperience) {
		this.projectExperience = projectExperience;
	}

	public String getResumeurl() {
		return resumeurl;
	}

	public void setResumeurl(String resumeurl) {
		this.resumeurl = resumeurl;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getGraduatTime() {
		return graduatTime;
	}

	public void setGraduatTime(String graduatTime) {
		this.graduatTime = graduatTime;
	}

}