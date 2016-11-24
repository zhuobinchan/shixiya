package com.ehighsun.BBS.Listener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ehighsun.BBS.MyTimerTask.SaveCountTimerTask;

public class CountListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ServletContext context = arg0.getServletContext();
		
		Map<Integer, Integer> countMap = new LinkedHashMap<Integer, Integer>();
		context.setAttribute("countMap", countMap);
		
		List<String> addrList = new ArrayList<String>();
		addrList.add("//http://localhost:8080/shixiya/BBS_enterBProductCourse.action");
		
		context.setAttribute("addrList", addrList);
		
		Timer timer = new Timer();  
        timer.schedule(new SaveCountTimerTask(), 1000,3600000);  //一个小时保存一次统计
	
	}

	
	
}
