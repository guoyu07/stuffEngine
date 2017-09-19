package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка при вставке сертификата в базу");
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
            throw new RuntimeException("Ошибка при чтении сертификата с номером: " + certNum);
        }
        if (certificate == null) {
            throw new RuntimeException("Не найден сертификат с номеромЖ:" + certNum);
        }
        return certificate;
    }

    @Override
    public List<Certificate> readAllCertsByEmpID(int empID) {
        logger.info("Запрос к базе на чтеие всех сертификатов сотрудника с id: " + empID);
        Session session = getSession();
        List<Certificate> allCerts;
        try{
            allCerts = session.createQuery("from Certificate C where C.ownerId = " + empID).list();
        }catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка при чтении сертификатов сотрудника с id: " +empID);
        }
        if (allCerts==null) {
            throw new RuntimeException("Не найдены сертификаты для сотрудника с id: " + empID);
        }
        return allCerts;
    }

    @Override
    public void deleteCertByNum(int certNum) {
        logger.info("Запрос к базе на удаление сертификата с номером: " + certNum);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.number = "
                    + certNum).executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка во время удаления сертификата с номером: " + certNum);
        }
    }

    @Override
    public void deleteAllCertsByEmpID(int empID) {
        logger.info("Запрос к базе на удаление всех сертификатов сотрудника с id: " + empID);
        Session session = getSession();
        try {
            session.createQuery("DELETE Certificate C where C.ownerId = "
                    + empID).executeUpdate();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException("Ошибка во время удаления всех сертификатов сотрудника с id: " + empID);
        }
    }
}
