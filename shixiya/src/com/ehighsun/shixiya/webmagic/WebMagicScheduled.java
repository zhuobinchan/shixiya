package com.ehighsun.shixiya.webmagic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.management.JMException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ehighsun.shixiya.util.FileUploadUtil;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;


@Component("webMagicScheduled")
public class WebMagicScheduled {
	@Resource(name="updateToMysqlByWebMagicPipeline")
	UpdateToMysqlByWebMagicPipeline updateToMysqlByWebMagicPipeline;
	
//	@Scheduled(cron = "0 45 23 * * ?")
	public void webmagicShixisheng() throws InterruptedException {
		
		//爬取应届生
		Spider seedUrlYingJieShengSpider = Spider.create(new SeedUrlYingJieSheng())
				.addUrl("http://www.yingjiesheng.com/guangzhou-morejob-1.html")
				.addPipeline(new MyPipeline("D:\\webmagic","YingJieShengUrlTager"))
				.thread(1);
			
		Spider tagerHtmlYingJieShengSpider = Spider.create(new TagerHtmlYingJieSheng())
				// 开启5个线程抓取
				 .addPipeline(updateToMysqlByWebMagicPipeline)
				.thread(5);
		// 启动爬虫
		Thread t1 =new Thread(seedUrlYingJieShengSpider);
		t1.start();
		t1.sleep(5000);
		t1.join();
		List<String> urls = new ArrayList<>();
		// 启动爬虫
			try {
				FileReader YingJieShengUrlTager = new FileReader(
						"D:\\webmagic\\YingJieShengUrlTager.txt");
				BufferedReader br = new BufferedReader(YingJieShengUrlTager);

				String str = null;

				while ((str = br.readLine()) != null) {
					urls.add(str);
				}
				br.close();
				YingJieShengUrlTager.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			tagerHtmlYingJieShengSpider.addUrl(urls.toArray(new String[urls.size()]));
			Thread t1_1 = new Thread(tagerHtmlYingJieShengSpider);
			t1_1.start();
			t1_1.sleep(4000);
			t1_1.join();
			if (tagerHtmlYingJieShengSpider.isExitWhenComplete()){
				FileUploadUtil.deleteFile("D:\\webmagic\\YingJieShengUrlTager.txt");
			}
		
		
		
		//爬取智联招聘
		Spider seedurlZhiLianZhaoPing = Spider.create(new SeedUrlZhiLianZhaoPing())
				.addUrl("http://xiaoyuan.zhaopin.com/part/industry/0/763_0_0_0_-1_0_1_0")
				 .addPipeline(new MyPipeline("D:\\webmagic","ZhiLianZhaoPingUrlTager"))
				.thread(1);
			
		Spider tagerHtmlZhiLianZhaoPing = Spider.create(new TagerHtmlZhiLianZhaoPing())
				// 开启5个线程抓取
				 .addPipeline(updateToMysqlByWebMagicPipeline)
				.thread(5);
		// 启动爬虫
		Thread t2 = new Thread(seedurlZhiLianZhaoPing);
		t2.start();
		t2.sleep(3000);
		t2.join();
		// 启动爬虫
			urls = new ArrayList<>();
			try {
				FileReader ZhiLianZhaoPingUrlTager = new FileReader(
						"D:\\webmagic\\ZhiLianZhaoPingUrlTager.txt");
				BufferedReader br = new BufferedReader(ZhiLianZhaoPingUrlTager);

				String str = null;

				while ((str = br.readLine()) != null) {
					urls.add(str);
				}
				br.close();
				ZhiLianZhaoPingUrlTager.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			tagerHtmlZhiLianZhaoPing.addUrl(urls.toArray(new String[urls.size()]));
			Thread t2_1 = new Thread(tagerHtmlZhiLianZhaoPing);
			t2_1.start();
			t2_1.sleep(2000);
			t2_1.join();
			if (tagerHtmlZhiLianZhaoPing.isExitWhenComplete()){
				FileUploadUtil.deleteFile("D:\\webmagic\\ZhiLianZhaoPingUrlTager.txt");
			}
		
		
		//爬取前程无忧
		Spider seedurlQianChengWuYou = Spider
				.create(new SeedUrlQianChengWuYou())
				.addUrl("http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=030200%2C00&district=000000&funtype=0000&industrytype=00&issuedate=4&providesalary=99&keywordtype=2&curr_page=1&lang=c&stype=1&postchannel=0100&workyear=99&cotype=99&degreefrom=99&jobterm=03&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9")
				// 开启1个线程抓取
				.addPipeline(new MyPipeline("D:\\webmagic","QianChengWuYouUrlTager"))
				.thread(1);

		Spider tagerHtmlQianChengWuYou = Spider
				.create(new TagerHtmlQianChengWuYou())
				// 开启5个线程抓取
				.addPipeline(updateToMysqlByWebMagicPipeline)
//				.addPipeline(new ConsolePipeline())
				.thread(5);
		// 启动爬虫
		Thread t3 = new Thread(seedurlQianChengWuYou);
		t3.start();
		t3.sleep(1000);
		t3.join();
		// 启动爬虫
			urls = new ArrayList<>();
			try {
				FileReader QianChengWuYouUrlTager = new FileReader(
						"D:\\webmagic\\QianChengWuYouUrlTager.txt");
				BufferedReader br = new BufferedReader(QianChengWuYouUrlTager);

				String str = null;

				while ((str = br.readLine()) != null) {
					urls.add(str);
				}
				br.close();
				QianChengWuYouUrlTager.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("urls.size():"+urls.size());
			tagerHtmlQianChengWuYou.addUrl(urls.toArray(new String[urls.size()]));
			Thread t3_1 = new Thread(tagerHtmlQianChengWuYou);
			t3_1.start();
			t3_1.join();
			if (tagerHtmlQianChengWuYou.isExitWhenComplete()) {
				FileUploadUtil.deleteFile("D:\\webmagic\\QianChengWuYouUrlTager.txt");
			}
		}
		
}