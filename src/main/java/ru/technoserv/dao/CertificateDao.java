package ru.technoserv.dao;

import ru.technoserv.domain.Certificate;

import java.util.List;

public interface CertificateDao {

    void create(Certificate certificate);

    Certificate readCertByNum(int certNum);

    List<Certificate> readAllCertsByEmpID(int empID);

    void deleteCertByNum(int certNum);

    void deleteAllCertsByEmpID(int empID);

}
