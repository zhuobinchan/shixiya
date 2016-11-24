package com.ehighsun.shixiya.websocket.config;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class DemoConfig implements ServerApplicationConfig {

	//Enpoint就指的是一个Websocket的一个服务端程序。
	
	//扫描src下所有类@ServerEndpoint注解的类
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scand) { 
		System.out.println();
		System.out.println();
		System.out.println("启动了:"+scand.size());
		System.out.println();
		System.out.println();
		
		//要返回扫描到的带注解的类的集合，提供一定过滤作用，不返回的话当前的Socket不会注册
		return scand;
	}

	//实现接口
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(
			Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
