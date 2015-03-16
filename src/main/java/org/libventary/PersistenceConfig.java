package org.libventary;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableJpaRepositories(basePackages = "com.cohesiva.cqrskrk.projection")
@EnableTransactionManagement
public class PersistenceConfig {

    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource() throws IllegalStateException, PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSource.setUser(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.minPoolSize", "2")));
        dataSource.setInitialPoolSize(dataSource.getMinPoolSize());
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.maxPoolSize", "16")));

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws IllegalStateException, PropertyVetoException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("hibernate.show_sql", "false")));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName("cqrs-krk-pu");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.axonframework.eventstore.jpa", "org.axonframework.saga.repository.jpa", "com.cohesiva.cqrskrk.projection");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        
        factory.afterPropertiesSet();
        
        return factory.getObject();
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", "false"));
        properties.put("hibernate.ejb.entitymanager_factory_name", "krk-cqrf-emf");
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "update"));
        properties.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.class.getName());
        return properties;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() throws IllegalStateException, PropertyVetoException {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
