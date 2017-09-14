package ru.technoserv.config;

import org.hibernate.Hibernate;
import org.hibernate.ejb.HibernatePersistence;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.ORACLE;

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:database.properties")
//@EnableJpaRepositories("ru.technoserv")
public class DAOConfig {
    //TODO перевести настройки в файл ресурсов
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "test_a";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:oracle:thin:@//89.108.84.144:1521/BPM8";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "test_a";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "ru.technoserv.domain";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return  dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties(){
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, true);
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.OracleDialect");
        //TODO перевести настройки в файл ресурсов
        properties.put("hibernate.current_session_context_class", "thread");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateTemplate hibernateTemplate(){
        return new HibernateTemplate(sessionFactory().getObject());
    }



}
