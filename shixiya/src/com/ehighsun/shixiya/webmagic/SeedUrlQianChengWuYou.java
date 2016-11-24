package com.ehighsun.shixiya.webmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.JMException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class SeedUrlQianChengWuYou implements PageProcessor{
	 // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    
	
	@Override
	public void process(Page page) {
		
		List<String> list = page.getHtml().xpath("//*[@id=\"resultList\"]/*[@class=\"el\"]")
				.all();
		for (String string : list) {
			Html html = new Html(string);
			String tarUrl=html.xpath("//*/p/span/a/@href").toString();
			if (tarUrl!=null) {
				System.out.println(tarUrl);
				page.putField(UUID.randomUUID().toString(),tarUrl);
			}
		}
		List<String> urls = new ArrayList<>();
		for(int i =2;i<=4;i++){//爬取的页数
			urls.add("http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=030200%2C00&district=000000&funtype=0000&industrytype=00&issuedate=4&providesalary=99&keywordtype=2&curr_page="+i+"&lang=c&stype=1&postchannel=0100&workyear=99&cotype=99&degreefrom=99&jobterm=03&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9");
		}
		page.addTargetRequests(urls);
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) throws JMException {

		Spider spider = Spider.create(new SeedUrlQianChengWuYou())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=030200%2C00&district=000000&funtype=0000&industrytype=00&issuedate=4&providesalary=99&keywordtype=2&curr_page=1&lang=c&stype=1&postchannel=0100&workyear=99&cotype=99&degreefrom=99&jobterm=03&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9")
				// 开启5个线程抓取
				 .addPipeline(new MyPipeline("D:\\webmagic","QianChengWuYouUrlTager"))
//				.addPipeline(new ConsolePipeline())
				.thread(1);
				// 启动爬虫
//		MySpiderMonitor.instance().register(spider);		
		spider.run();
	
	}

}
