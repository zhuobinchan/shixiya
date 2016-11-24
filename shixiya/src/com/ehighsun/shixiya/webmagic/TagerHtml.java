package com.ehighsun.shixiya.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class TagerHtml implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    
	
	@Override
	public void process(Page page) {
		
		page.putField("saleNum", page.getHtml().xpath("//*[@class=\"salaNum\"]/tidyText()")); //工资薪酬
    	page.putField("companyName", page.getHtml().xpath("//*[@class=\"companyName\"]/tidyText()")); //公司名
    	page.putField("intruduction", page.getHtml().xpath("//*[@class=\"posMsg\"]/tidyText()")); //职位描述
    	page.putField("companyIntruduction", page.getHtml().xpath("//*[@id=\"gongsi\"]/p/tidyText()")); //公司描述
    	page.putField("workAddress", page.getHtml().xpath("//*[@class=\"posinfo\"]/div/ul/li[3]/span[2]/text()")); //工作地点
    	page.putField("name",page.getHtml().xpath("//*[@class=\"headConLeft\"]/h1/text()")); //职位名称
//		System.out.println(page.getHtml());
    	
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {

		Spider.create(new TagerHtml())
				.addUrl("http://gz.58.com/zpshengchankaifa/27915912091201x.shtml?psid=127486263193836002952960740&entinfo=27915912091201_j")
				// 开启5个线程抓取
				 .addPipeline(new MyPipeline("D:\\webmagic\\tagerHtml"))
				.thread(1)
				// 启动爬虫
				.run();
	}

}
