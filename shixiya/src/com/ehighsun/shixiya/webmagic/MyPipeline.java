package com.ehighsun.shixiya.webmagic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class MyPipeline extends FilePersistentBase implements  Pipeline {

	  private Logger logger = LoggerFactory.getLogger(getClass());
	  private String fileName;
	    /**
	     * new JsonFilePageModelPipeline with default path "/data/webmagic/"
	     */
	    public MyPipeline() {
	        setPath("/data/webmagic");
	    }

	    public MyPipeline(String path) {
	        setPath(path);
	    }
	    public MyPipeline(String path,String fileName) {
	        setPath(path);
	        this.fileName = fileName;
	    }

	    @Override
	    public void process(ResultItems resultItems, Task task) {
	        String path = this.path+"\\"+fileName+".txt";
	        System.out.println("get page: " + resultItems.getRequest().getUrl());
	        try {
	            PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(path),true));
	            StringBuffer result = new StringBuffer();
	            
	            for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
	            	result.append(entry.getValue().toString()+"\n");
	            }
	            printWriter.write(result.toString());
//	            printWriter.append(result.toString());
//	            System.out.println(JSON.toJSONString(resultItems.getAll()));
	            printWriter.close();
	        } catch (IOException e) {
	            logger.warn("write file error", e);
	        }
	    }

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

}
