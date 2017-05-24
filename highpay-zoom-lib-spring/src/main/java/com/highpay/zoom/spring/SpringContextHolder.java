package com.highpay.zoom.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 *
 * spring context静态工具类
 *
 * @author jisheng_qiu@kingdee.com
 * @time 2017/5/23 12:35
 * since 1.0
 */
public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext applicationContext;

	public SpringContextHolder() {
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map beanMaps = applicationContext.getBeansOfType(clazz);
		return beanMaps != null && !beanMaps.isEmpty()? (T) beanMaps.values().iterator().next() :null;
	}

	private static void checkApplicationContext() {
		if(applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
