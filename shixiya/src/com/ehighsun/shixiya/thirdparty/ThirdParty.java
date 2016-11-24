package com.ehighsun.shixiya.thirdparty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.ehighsun.shixiya.service.ResumeService;
import com.ehighsun.shixiya.util.ThirdPartyUtil;

@Component("thirdParty")
public class ThirdParty {

	@Resource(name = "resumeService")
	private ResumeService resumeService;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void run() {
		System.out.println("定时任务获取大易数据开始！！！");
		try {
			while (!ThirdPartyUtil.getResume("5").equals("999")) {
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("数据开始去重！！！");
		DistinctResume();
		System.out.println("定时任务结束！！！");
	}

	// 数据去重
	private void DistinctResume() {

		List<Integer> index = new ArrayList<>();

		while (true) {
			index = resumeService
					.executeOurSql("SELECT MIN(`简历ID`) FROM `简历管理` GROUP BY `姓名` ,`手机号码` ,`邮箱` HAVING COUNT(*)>1");
			if (index.size() == 0) {
				break;
			}

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM `简历管理` WHERE `简历ID` IN (");
			for (Integer integer : index) {
				sql.append(integer).append(",");
			}
			String resultSql = sql.substring(0, sql.length() - 1);
			resultSql = resultSql + ");";
			resumeService.executeSql(resultSql);
		}

	}

}
