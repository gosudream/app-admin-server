package com.ztgame.admin.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源配置类.
 * <p>
 * DB配置可以使用Spring注入的
 * <pre>
 * 		@PropertySource("/home/ztgame/config/jdbc.properties")
 * 		public class DataSourceConfig {
 * 			@Value("${jdbc.driverClass}") String driverClass;
 * 			@Value("${jdbc.url}") String url;
 * 			@Value("${jdbc.user}") String user;
 * 			@Value("${jdbc.password}") String password;
 * 		}
 * </pre>
 * 
 * @author 小流氓
 * @since JDK 1.7
 * @version 1.0 2012-10-10
 */
@Configuration
@EnableTransactionManagement
//这个注解1.1.1以后包里才有
@EnableJpaRepositories(
		basePackages = "com.ztgame.springmvc.repository",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManagerFactory"
		)
public class DataSourceConfig {
	@Bean
	public DataSource someDataSource() {
		// 这里并没有使用Spring的注入方式，再看看我们的配置文件 ，乐了吧！
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db_webgame_xiaoe?useUnicode=true&amp;characterEncoding=utf-8");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		return new LazyConnectionDataSourceProxy(dataSource);
	}
	
	@Bean
    public AbstractEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(someDataSource());
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactory.setPackagesToScan("com.ztgame.springmvc.domain");
        return entityManagerFactory;
    }

    @Bean
    public AbstractJpaVendorAdapter vendorAdapter() {
        AbstractJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}
