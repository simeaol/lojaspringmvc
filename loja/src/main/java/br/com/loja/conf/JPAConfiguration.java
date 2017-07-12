package br.com.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("padtec");
		dataSource.setUrl("jdbc:mysql://localhost:3306/loja");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		factoryBean.setDataSource(dataSource);

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		factoryBean.setJpaProperties(jpaProperties);
		
		factoryBean.setPackagesToScan("br.com.loja.models");
		
		return factoryBean;

	}
	
	@Bean
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
		return new JpaTransactionManager(entityManagerFactory);
	}
}
