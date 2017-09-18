package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.domain.Certificate;

import java.util.List;

@Repository
@Transactional
public class HibernateCertificateDao implements CertificateDao {

    private static final Logger logger = Logger.getLogger(HibernateCertificateDao.class);

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();

    }

    @Override
    public void create(Certificate certificate) {
        logger.info("Запрос к базе на создание сертификата");
        Session session = getSession();
        try {
            session.save(certificate);
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка при вставке сертификата в базу");
        }

    }

    @Override
    public Certificate readCertByNum(int certNum) {
        logger.info("Запрос к базе на чтение сертификата с номером: " + certNum);
        Session session = getSession();
        Certificate dbCertificate;
        try {
            dbCertificate = (Certificate) session.get(Certificate.class, certNum);
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка при чтении сертификата с номером: " + certNum);
        }
        if (dbCertificate == null) {
            throw new RuntimeException("Сертификат с номером " + certNum + " не найден");
        }
        return dbCertificate;
    }

    @Override
    public List<Certificate> readAllCertsByEmpID(int empID) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteCertByNum(int certId) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAllCertsByEmpID(int empID) {
        throw new RuntimeException("Not implemented");
    }
}
