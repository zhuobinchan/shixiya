package com.ehighsun.BBS.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CountFilter implements Filter{

	
	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		
		ServletContext context = filterConfig.getServletContext();
		Map<Integer, Integer> countMap = (Map<Integer, Integer>) context.getAttribute("countMap");
		List<String> addrList = (List<String>) context.getAttribute("addrList");
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		String url = request.getRequestURL().toString();
		
		if(addrList.contains(url)){
			
			String[] str = url.split("?");
			Integer id = Integer.parseInt(str[1].split("=")[1]);
			if(countMap.containsKey(str[1])){
				Integer count = countMap.get(id);
				
				countMap.put(id, count+1);
			}else{
				countMap.put(id, 1);
			}
			
		}
		
		context.setAttribute("countMap", countMap);
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
