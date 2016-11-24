package com.ehighsun.shixiya.webmagic;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.RecruitmentInfo;
import com.ehighsun.shixiya.util.StringUtil;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("updateToMysqlByWebMagicPipeline")
public class UpdateToMysqlByWebMagicPipeline implements  Pipeline  {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "baseDao")
	BaseDao<RecruitmentInfo> baseDao;
	  
	@Override
	public void process(ResultItems resultItems, Task task) {
		RecruitmentInfo recruitmentInfo = new RecruitmentInfo();

		recruitmentInfo.setArea(resultItems.get("RIArea")==null?null:resultItems.get("RIArea").toString());
		recruitmentInfo.setName(resultItems.get("RIName")==null?null:resultItems.get("RIName").toString());
		
		String RIPublishTime = resultItems.get("RIPublishTime")==null?new String():resultItems.get("RIPublishTime").toString();
		if (StringUtil.isContains(RIPublishTime, new String[]{"年","月","日","至"})) {
			RIPublishTime = RIPublishTime.substring(0, 11);
			SimpleDateFormat cnsdf = new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				RIPublishTime = sdf.format(cnsdf.parse(RIPublishTime))+" 00:00:00";
			} catch (ParseException e) {
				RIPublishTime = sdf.format(Calendar.getInstance().getTime());
			}
		}else if (StringUtil.isDate(RIPublishTime)) {
			RIPublishTime =RIPublishTime+" 00:00:00";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			RIPublishTime = sdf.format(Calendar.getInstance().getTime());
		}
		recruitmentInfo.setPublishTime(RIPublishTime);
		recruitmentInfo.setWorkType(" ");
		
		recruitmentInfo.setIntroduction(resultItems.get("RIIntroduction")==null?null:resultItems.get("RIIntroduction").toString());
		recruitmentInfo.setIsWebMagic(1);
		recruitmentInfo.setCheckState(1);
		recruitmentInfo.setStopState(0);
		recruitmentInfo.setWorkingDay("每周5天");
		recruitmentInfo.setPositionType("实习生");
		recruitmentInfo.setAdvantage("来源其他网站");
		recruitmentInfo.setSalary(resultItems.get("RISalary")==null?"面议":resultItems.get("RISalary").toString());
		
		recruitmentInfo.setTagerUrl(resultItems.getRequest().getUrl());
		baseDao.save(recruitmentInfo);
	}

}
