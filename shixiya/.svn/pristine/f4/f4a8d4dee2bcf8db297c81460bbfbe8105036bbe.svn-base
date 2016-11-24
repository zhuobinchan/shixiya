package com.ehighsun.shixiya.thirdparty;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

@Component("clearZip")
public class ClearZip {

	// @Scheduled(cron = "0 0 1 * * ?")
	public void run() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String zippath = request.getSession().getServletContext()
				.getRealPath("/zip");
		File zipFile = new File(zippath);
		if (zipFile.exists()) {

			File[] files = zipFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}

		}

	}

}
