package ru.technoserv.services;

import ru.technoserv.domain.Certificate;

import java.util.List;

public interface CertificateService {

    Certificate create(Certificate certificate);

    Certificate readCertByNum(int certNum);

    List<Certificate> readAllCertsByEmpID(int empID);

    void deleteCertByNum(int certNum);

    void deleteAllCertsByEmpID(int empID);
}
