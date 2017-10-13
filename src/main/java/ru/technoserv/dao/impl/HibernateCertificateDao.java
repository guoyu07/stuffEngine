package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.domain.Certificate;
import ru.technoserv.exceptions.MyRuntimeException;
import ru.technoserv.exceptions.StuffExceptions;


import java.util.List;

@Repository
@Transactional
public class HibernateCertificateDao implements CertificateDao {

    private static final Logger logger = Logger.getLogger(HibernateCertificateDao.class);

    @Autowired
    public HibernateCertificateDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

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
            logger.error(ex.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
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
            logger.error(ex.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
        if (certificate == null) {
            throw new MyRuntimeException(StuffExceptions.NOT_FOUND);
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
            allCerts = session.createQuery("from Certificate C where C.ownerId = :empID").setParameter("empID", empID).list();
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
        if (allCerts.isEmpty()) {
            throw new MyRuntimeException(StuffExceptions.NOT_FOUND);
        }
        return allCerts;
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "empCertificates", allEntries = true, beforeInvocation = true)})
    public void deleteCertByNum(int certNum) {
        logger.info("Запрос к базе на удаление сертификата с номером: " + certNum);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.number = :certNum")
                    .setParameter("certNum", certNum).executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "empCertificates", key = "#empID", beforeInvocation = true)})
    public void deleteAllCertsByEmpID(int empID) {
        logger.info("Запрос к базе на удаление всех сертификатов сотрудника с ID: " + empID);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.ownerId = :ownerId")
                    .setParameter("ownerId", empID)
                    .executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
    }
}
