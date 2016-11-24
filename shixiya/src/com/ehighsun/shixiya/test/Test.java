package com.ehighsun.shixiya.test;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.text.html.HTML;

import com.ehighsun.shixiya.util.StringUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class Test {
	@org.junit.Test
	public void aa() {
//		String a = "2016年11月15日 至 2016年12月15日";
//		
////		System.out.println(StringUtil.isContains(a, new String[]{"年","月","日","至"}));
//		System.out.println(a.substring(0,11));
//		a = a.substring(0,11);
//		SimpleDateFormat cnsdf = new SimpleDateFormat("yyyy年MM月dd日");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			String b = sdf.format(cnsdf.parse(a));
//			System.out.println(StringUtil.isDate(b));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			System.out.println(sdf.format(Calendar.getInstance().getTime()));
//			e.printStackTrace();
//		}
		
		Map<String, Object> map = new HashMap<>();
		System.out.println(map.get("123"));
	}
}
