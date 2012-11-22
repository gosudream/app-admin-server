package com.ztgame.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring基于注解的配置类.
 * <p>
 * 可代替原来的<b>applicationContext.xml</b>配置文件.<br>
 * 这里请不要跟我扯配置文件多么方便,这里不讨论注解和配置的优劣问题. 
 * 
 * @author 小流氓
 * @since JDK 1.7
 * @version 1.0 2012-10-10
 */
@EnableWebMvc
@Configuration
@Import(DataSourceConfig.class)
@ComponentScan(basePackages = "com.ztgame.admin.controller")
public class DefaultAppConfig {
}
