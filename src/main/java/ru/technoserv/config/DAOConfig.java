package ru.technoserv.config;

import org.hibernate.Hibernate;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.technoserv.audit.AuditHandler;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.ORACLE;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DAOConfig {

    @Autowired
    Environment environment;

    private String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private String PROPERTY_NAME_DATABASE_URL = "db.url";
    private String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "packages.to.scan";
    private String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private String PROPERTY_NAME_CURRENT_SESSION_CONTEXT = "current_session_context_class";



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(environment.getProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return  dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties(){
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        //TODO перевести настройки в файл ресурсов
        properties.put(PROPERTY_NAME_CURRENT_SESSION_CONTEXT, environment.getProperty(PROPERTY_NAME_CURRENT_SESSION_CONTEXT));
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}