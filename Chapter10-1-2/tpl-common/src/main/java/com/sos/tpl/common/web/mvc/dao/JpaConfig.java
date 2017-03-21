package com.sos.tpl.common.web.mvc.dao;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StringUtils;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass=BaseRepositoryFactoryBean.class)
public class JpaConfig {
	@ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
	@ConditionalOnProperty(prefix = DataSourceProperties.PREFIX, name = "url")
	@Configuration
	protected static class NonEmbeddedConfiguration {
		private static Log log = LogFactory.getLog(NonEmbeddedConfiguration.class);
		@Autowired
		private DataSourceProperties properties;
		@Value("${"+DataSourceProperties.PREFIX+".signword:}")
		private String signword;
		@Bean
		@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
		public DataSource dataSource() {
			log.info("create jdbc datasource from JpaConfig.");
			DataSourceBuilder factory = DataSourceBuilder
					.create(this.properties.getClassLoader())
					.driverClassName(this.properties.getDriverClassName())
					.url(this.properties.getUrl())
					.username(this.properties.getUsername())
					.password(StringUtils.hasText(this.properties.getPassword())?this.properties.getPassword():signword);
			return factory.build();
		}

	}
}
