package com.hello.config;

import com.hello.model.Role;
import com.hello.model.User;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.hello.dao")
@PropertySource("classpath:db/db.properties")
public class HibernateConfig {

    private Environment environment;

    @Autowired
    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Properties propertiesHibernate() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("DIALECT"));
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("SHOW_SQL"));
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, environment.getProperty("HBM2DDL_AUTO"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jbdcDriverMySQL"));
        dataSource.setUrl(environment.getProperty("connectionURL"));
        dataSource.setUsername(environment.getProperty("userNameDb"));
        dataSource.setPassword(environment.getProperty("userPassword"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.hello.model"});
        sessionFactory.setAnnotatedClasses(User.class, Role.class);
        sessionFactory.setHibernateProperties(propertiesHibernate());
        return sessionFactory;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
