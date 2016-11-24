package com.ehighsun.shixiya.webmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.JMException;

import com.ehighsun.shixiya.test.Test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class SeedUrl  implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    
	
	@Override
	public void process(Page page) {
		
		List<String> list = page.getHtml().xpath("//*[@id=\"infolist\"]/dl")
				.all();
		for (String string : list) {
			Html html = new Html(string);
			System.out.println(html.xpath("//*/dt/a/@href"));
			page.putField(UUID.randomUUID().toString(),
					html.xpath("//*/dt/a/@href"));
		}
		
		List<String> urls = new ArrayList<>();
		for(int i =2;i<=70;i++){
			urls.add("http://gz.58.com/job/pn"+i+"/?utm_source=market&spm=b-31580022738699-me-f-824.bdpz_biaoti&key=");
		}
		page.addTargetRequests(urls);
		
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws JMException {

		Spider spider = Spider.create(new SeedUrl())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://gz.58.com/job/pn1/?utm_source=market&spm=b-31580022738699-me-f-824.bdpz_biaoti&key=")
				// 开启5个线程抓取
				 .addPipeline(new MyPipeline("D:\\webmagic\\"))
				.thread(1);
				// 启动爬虫
		MySpiderMonitor.instance().register(spider);		
		spider.start();
	
	}

}
