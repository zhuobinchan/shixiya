package com.ehighsun.shixiya.commonality.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements RequestAware,
		ServletResponseAware, SessionAware, ApplicationAware, ModelDriven<T> {
	protected T model; // model 有可能为 user，student,teacher等等........
	protected Map<String, Object> jsonMap = null;
	protected List<T> jsonList = null;
	protected InputStream inputStream = null;
	protected Map<String, Object> application;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	private HttpServletResponse response;

	/**
	 * 通过反射动态的创建对象
	 */
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public List<T> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	protected String ObjectToJson(T obj) {
		return JSON.toJSONString(obj);
	}

	protected String ObjectToJson(T obj, SimplePropertyPreFilter filter) {
		return JSON.toJSONString(obj, filter);
	}

	protected T JsonToObject(String JsonString, Class<T> clazz) {
		return JSON.parseObject(JsonString, clazz);
	}

	protected void writeResult(String result, boolean removeCache) {
		PrintWriter writer = null;
		try {
			if (removeCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}
			response.setContentType("text/html; charset=utf-8");
			writer = response.getWriter();
			writer.print(result);
			writer.flush();
		} catch (IOException e) {
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
}
