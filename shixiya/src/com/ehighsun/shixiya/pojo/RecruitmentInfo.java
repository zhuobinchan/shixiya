package com.ehighsun.shixiya.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

// default package

/**
 * RecruitmentInfo entity. @author MyEclipse Persistence Tools
 */

@JSONType(orders = { "RIBussiness" })
public class RecruitmentInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private RIBussiness RIBussiness;
	private String name;
	private String address;
	private String area;
	private String publishTime;
	private String workType;
	private String positionType;
	private String workingDay;
	private String advantage;
	private String salary;
	private Integer population;
	private String introduction;
	private String demand;
	private String workTime;
	private String remark;
	private String logoUrl;
	private Integer checkState;// 1是0否
	private Integer topState;// 1是0否
	private Integer stopState;// 1是0否
	
	private Integer isWebMagic;//是否爬虫信息
	private String tagerUrl;//目标链接

	// Constructors

	/** default constructor */
	public RecruitmentInfo() {
	}

	/** full constructor */
	public RecruitmentInfo(RIBussiness RIBussiness, String name,
			String address, String publishTime, String workType, String salary,
			Integer population, String introduction, String demand,
			String workTime, String remark,String positionType,String workingDay,String advantage) {
		this.RIBussiness = RIBussiness;
		this.name = name;
		this.address = address;
		this.publishTime = publishTime;
		this.workType = workType;
		this.salary = salary;
		this.population = population;
		this.introduction = introduction;
		this.demand = demand;
		this.workTime = workTime;
		this.remark = remark;
		this.positionType = positionType;
		this.workingDay = workingDay;
		this.advantage = advantage;

	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Integer getTopState() {
		return topState;
	}

	public void setTopState(Integer topState) {
		this.topState = topState;
	}

	public Integer getStopState() {
		return stopState;
	}

	public void setStopState(Integer stopState) {
		this.stopState = stopState;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSONField(serialize = false)
	public RIBussiness getRIBussiness() {
		return this.RIBussiness;
	}

	public void setRIBussiness(RIBussiness RIBussiness) {
		this.RIBussiness = RIBussiness;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDemand() {
		return this.demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getWorkTime() {
		return this.workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(String workingDay) {
		this.workingDay = workingDay;
	}

	public Integer getIsWebMagic() {
		return isWebMagic;
	}

	public void setIsWebMagic(Integer isWebMagic) {
		this.isWebMagic = isWebMagic;
	}

	public String getTagerUrl() {
		return tagerUrl;
	}

	public void setTagerUrl(String tagerUrl) {
		this.tagerUrl = tagerUrl;
	}

}