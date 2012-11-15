package com.ztgame.admin.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Servlet3.0初始化的配置类.
 * <p>
 * 可代替原来的<b>web.xml</b>配置文件.<br>
 * Servlet3.0规范后，再也不需要<b>web.xml</b>了<br>
 * 你可以使用@WebServlet(javax.servlet.annotation.WebServlet)注解来配置你的Servlet.
 * 
 * @author 小流氓
 * @since JDK 1.7
 * @version 1.0 2012-10-10
 */
public class DefaultWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		// 初始化一个注解配置WEB应用的Context
		// http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/beans.html#beans-java
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		// 注册一个Spring配置类，就不在要<b>applicationContext.xml</b>文件了
		context.register(DefaultAppConfig.class);
		/**
		 * 下面配置可等价于下面三行代码。
		 * <pre>
		 * <web-app>
		 *     <servlet>
		 *         <servlet-name>dispatcher</servlet-name>
		 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		 *         <load-on-startup>1</load-on-startup>
		 *     </servlet>
		 *     <servlet-mapping>
		 *         <servlet-name>dispatcher</servlet-name>
		 *         <url-pattern>/</url-pattern>
		 *     </servlet-mapping>
		 * </web-app>
		 * </pre>
		 */
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}