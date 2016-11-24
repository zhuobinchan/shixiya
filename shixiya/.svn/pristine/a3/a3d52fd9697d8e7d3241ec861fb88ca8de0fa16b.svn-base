package com.ehighsun.shixiya.webmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.JMException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class SeedUrlYingJieSheng  implements PageProcessor{
	 // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    
	
	@Override
	public void process(Page page) {
		
		List<String> list = page.getHtml().xpath("//*[@id=\"tb_job_list\"]/tr")
				.all();
		for (String string : list) {
			Html html = new Html(string);
			Selectable tarUrl=html.xpath("//*/a/@href");
			
			if (tarUrl.toString()!=null) {
				System.out.println(tarUrl);
				page.putField(UUID.randomUUID().toString(),
						tarUrl);
			}
			
		}
		List<String> urls = new ArrayList<>();
		for(int i =2;i<=2;i++){//爬取的页数
			urls.add("http://www.yingjiesheng.com/guangzhou-morejob-"+i+".html");
		}
		page.addTargetRequests(urls);
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) throws JMException {

		Spider spider = Spider.create(new SeedUrlYingJieSheng())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://www.yingjiesheng.com/guangzhou-morejob-1.html")
				// 开启5个线程抓取
				 .addPipeline(new MyPipeline("D:\\webmagic","YingJieShengUrlTager"))
				.thread(1);
				// 启动爬虫
//		MySpiderMonitor.instance().register(spider);		
		spider.start();
	
	}
}
