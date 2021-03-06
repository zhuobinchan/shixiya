package com.ehighsun.shixiya.webmagic;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class TagerHtmlQianChengWuYou implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
	
	@Override
	public void process(Page page) {
		if (page.getUrl().toString().contains("http://jobs.51job.com/")) {
			page.putField("RIName", page.getHtml().xpath("//*[@class=\"cn\"]/h1/@title").toString().trim()); //岗位名称
			page.putField("RIArea", page.getHtml().xpath("//*[@class=\"cn\"]/span/text()").toString().trim());//工作地区
			page.putField("RIIntroduction", page.getHtml().xpath("//*[@class=\"bmsg\"]/tidyText()").replace("<[^>]*>|\n", "").replace("举报  分享", ""));//岗位内容
			page.putField("RISalary", page.getHtml().xpath("//*[@class=\"cn\"]/strong/tidyText()").toString().trim()); //岗位名称
			
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {

	 Spider spider = Spider.create(new TagerHtmlQianChengWuYou())
				// 开启5个线程抓取
//				 .addPipeline(new MyPipeline("D:\\webmagic\\tagerHtml"))
				.addPipeline(new ConsolePipeline())
				.thread(1);
				// 启动爬虫
	 List<String>urls = new ArrayList<>();
	 try {
		FileReader YingJieShengUrlTager = new FileReader("D:\\webmagic\\QianChengWuYouUrlTager.txt");
		BufferedReader br = new BufferedReader(YingJieShengUrlTager);
        
        String str = null;
       
        while((str = br.readLine()) != null) {
              urls.add(str);
        }
        br.close();
        YingJieShengUrlTager.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 spider.addUrl(urls.toArray(new String[urls.size()]));
	 
	 
	 spider.run();
	}

}
