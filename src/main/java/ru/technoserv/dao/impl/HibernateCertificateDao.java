package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.domain.Certificate;


import java.util.List;

//TODO Исключения

@Repository
@Transactional
public class HibernateCertificateDao implements CertificateDao {

    private static final Logger logger = Logger.getLogger(HibernateCertificateDao.class);

    @Autowired
    EhCacheCacheManager cacheManager;

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override

    @Caching(evict = {@CacheEvict(cacheNames = "empCertificates", key = "#certificate.ownerId", beforeInvocation = true)})
    public void create(Certificate certificate) {
        logger.info("Запрос к базе на создание сертификата");
        Session session = getSession();
        try {
            session.save(certificate);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",ex);
        }
    }

    @Override
    public Certificate readCertByNum(int certNum) {
        logger.info("Запрос к базе на чтение сертификата с номером: " + certNum);
        Session session = getSession();
        Certificate certificate;
        try {
            certificate = (Certificate) session.get(Certificate.class, certNum);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",ex);
        }
        if (certificate == null) {
            throw new RuntimeException("5 - сертификат не найден");
        }
        return certificate;
    }

    @Override
    @Cacheable(cacheNames = "empCertificates", key = "#empID")
    public List<Certificate> readAllCertsByEmpID(int empID) {
        logger.info("Запрос к базе на чтение всех сертификатов сотрудника с ID: " + empID);
        Session session = getSession();
        List<Certificate> allCerts;
        try {
            allCerts = session.createQuery("from Certificate C where C.ownerId = " + empID).list();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",ex);
        }
        if (allCerts.isEmpty()) {
            throw new RuntimeException("5 - сертификаты не найдены");
        }
        return allCerts;
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "empCertificates", allEntries = true, beforeInvocation = true)})
    public void deleteCertByNum(int certNum) {
        logger.info("Запрос к базе на удаление сертификата с номером: " + certNum);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.number = "
                    + certNum).executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы", ex);
        }
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "empCertificates", key = "#empID", beforeInvocation = true)})
    public void deleteAllCertsByEmpID(int empID) {
        logger.info("Запрос к базе на удаление всех сертификатов сотрудника с ID: " + empID);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.ownerId = "
                    + empID).executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы", ex);
        }
    }
}
