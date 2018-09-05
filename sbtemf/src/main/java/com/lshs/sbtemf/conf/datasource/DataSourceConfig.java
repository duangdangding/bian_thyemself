package com.lshs.sbtemf.conf.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: LuShao
 * @create: 2018-09-03 17:04
 **/
@Configuration
public class DataSourceConfig {

	@Bean(name = "bianDS")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.bian") // application.properteis中对应属性的前缀
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "bokeDS")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.boke") // application.properteis中对应属性的前缀
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源: 通过AOP在不同数据源之间动态切换
	 * @return
	 */
	//自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	@Primary
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(dataSource1());

		// 配置多数据源
		Map<Object, Object> dsMap = new HashMap(5);
		dsMap.put("bian", dataSource1());
		dsMap.put("boke", dataSource2());

		dynamicDataSource.setTargetDataSources(dsMap);

		return dynamicDataSource;
	}

	/**
	 * 配置@Transactional注解事物
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}
}
