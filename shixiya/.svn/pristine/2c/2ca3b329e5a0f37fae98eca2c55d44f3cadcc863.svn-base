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

public class SeedUrlZhiLianZhaoPing implements PageProcessor{
	 // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    
	
	@Override
	public void process(Page page) {
		
		
		List<String> list = page.getHtml().xpath("//*[@class=\"searchResultListUl\"]/li")
				.all();
		for (String string : list) {
			Html html = new Html(string);
			String tarUrl=html.xpath("//*[@class=\"searchResultJobinfo\"]/p/a/@href").toString();
			if (tarUrl!=null) {
				System.out.println(tarUrl);
				if (tarUrl.contains("http://xiaoyuan.zhaopin.com/other/page?r=http://jobs.zhaopin.com/")) {
					tarUrl = tarUrl.replace("http://xiaoyuan.zhaopin.com/other/page?r=", "");
				}
				page.putField(UUID.randomUUID().toString(),tarUrl);
			}
			
		}
		List<String> urls = new ArrayList<>();
		for(int i =2;i<=3;i++){//爬取的页数
			urls.add("http://xiaoyuan.zhaopin.com/part/industry/0/763_0_0_0_-1_0_"+i+"_0");
		}
		page.addTargetRequests(urls);
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) throws JMException {

		Spider spider = Spider.create(new SeedUrlZhiLianZhaoPing())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://xiaoyuan.zhaopin.com/part/industry/0/763_0_0_0_-1_0_1_0")
				// 开启5个线程抓取
				 .addPipeline(new MyPipeline("D:\\webmagic","ZhiLianZhaoPingUrlTager"))
//				.addPipeline(new ConsolePipeline())
				.thread(1);
				// 启动爬虫
//		MySpiderMonitor.instance().register(spider);		
		spider.run();
	
	}

}
