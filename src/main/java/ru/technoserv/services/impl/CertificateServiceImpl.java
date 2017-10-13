package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.domain.Certificate;
import ru.technoserv.services.CertificateService;

import java.util.List;

/**
 * Управление информацией о сертификатах
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    private static final Logger logger = Logger.getLogger(CertificateServiceImpl.class);

    private CertificateDao certificateDao;

    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao){
        this.certificateDao = certificateDao;
    }



    @Override
    public Certificate create(Certificate certificate) {
        logger.info("Создание сертификата");
        certificateDao.create(certificate);
        return  certificateDao.readCertByNum(certificate.getNumber());
    }

    @Override
    public Certificate readCertByNum(int certNum) {
        logger.info("Чтение сертификата c номером: " + certNum);
        return certificateDao.readCertByNum(certNum);
    }

    @Override
    public List<Certificate> readAllCertsByEmpID(int empID) {
        logger.info("Чтение всех сертификатов сотрудника с ID: " + empID);
        return certificateDao.readAllCertsByEmpID(empID);
    }

    @Override
    public void deleteCertByNum(int certNum) {
        logger.info("Удаление сертификата с номером: " + certNum);
        certificateDao.deleteCertByNum(certNum);
    }

    @Override
    public void deleteAllCertsByEmpID(int empID) {
        logger.info("Удаление всех сертификатов сотрудника с ID: " + empID);
        certificateDao.deleteAllCertsByEmpID(empID);
    }
}
