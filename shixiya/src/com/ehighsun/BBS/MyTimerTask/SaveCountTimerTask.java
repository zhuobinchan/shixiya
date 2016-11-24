package com.ehighsun.BBS.MyTimerTask;

import java.util.Map;
import java.util.TimerTask;

import javax.annotation.Resource;

import jiabin.entity.Section;
import jiabin.service.SectionService;

import org.apache.struts2.ServletActionContext;

public class SaveCountTimerTask extends TimerTask{

	@Resource(name="sectionService")
	private SectionService sectionService;
	
	@Override
	public void run() {
		 
		Map<Integer,Integer> countMap = 
				(Map<Integer, Integer>) ServletActionContext.getServletContext().getAttribute("countMap");
		
		for (Integer key : countMap.keySet()) {
			
			Section s = sectionService.findSectionById(key);
			
			s.setLookCount(s.getLookCount()+countMap.get(key));
			
			sectionService.saveSection(s);
			
			
		}
		
		
	}

}
