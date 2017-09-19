package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.domain.Certificate;
import ru.technoserv.services.CertificateService;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private static final Logger logger = Logger.getLogger(CertificateServiceImpl.class);

    @Autowired
    CertificateDao certificateDao;

    @Override
    public void create(Certificate certificate) {
        logger.info("Создание сертификата");
        certificateDao.create(certificate);
    }

    @Override
    public Certificate readCertByNum(int certNum) {
        logger.info("Чтение сертификата c номером: " + certNum);
        Certificate certificate = certificateDao.readCertByNum(certNum);
        return certificate;
    }

    @Override
    public List<Certificate> readAllCertsByEmpID(int empID) {
        logger.info("Чтение всех сертификатов сотрудника с ID: " + empID);
        List<Certificate> allCerts = certificateDao.readAllCertsByEmpID(empID);
        return allCerts;
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
