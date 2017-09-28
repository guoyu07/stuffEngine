package ru.technoserv.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.domain.Certificate;
import ru.technoserv.services.CertificateService;

import java.util.List;

@RestController
@RequestMapping(value = "/certificate", produces = {"application/json; charset=UTF-8"})
public class CertificateController {

    private static final Logger logger = Logger.getLogger(CertificateController.class);

    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"} )
    public String createCertificate(@RequestBody Certificate certificate) {
        logger.info("Получен request на создание сертификата");
        logger.info("Cоздаваемый сертификат: " + certificate.toString());
        certificateService.create(certificate);
        return "created";
    }

    @RequestMapping(value = "/certnum/{certNum}", method = RequestMethod.GET)
    public ResponseEntity<?> readCertByNum(@PathVariable(name = "certNum") int certNum) {
        logger.info("Получен request на чтение сертификата с номером: " + certNum);
        Certificate certificate = certificateService.readCertByNum(certNum);
        logger.info("Возвращаемый сертификат: " + certificate.toString());
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @RequestMapping(value = "/empid/{empID}", method = RequestMethod.GET)
    public ResponseEntity<?> readAllCertsByEmpID(@PathVariable(name = "empID") int empID) {
        logger.info("Получен request на чтение всех сертификатов сотрудника с ID: " + empID);
        List<Certificate> allCerts = certificateService.readAllCertsByEmpID(empID);
        logger.info("Возвращаемые сертификаты сотрудника: " + allCerts);
        for (Certificate cert : allCerts) logger.info(cert.toString());
        return new ResponseEntity<>(allCerts, HttpStatus.OK);
    }

    @RequestMapping(value = "/certnum/{certNum}", method = RequestMethod.DELETE)
    public String deleteCertByNum(@PathVariable(name = "certNum") int certNum) {
        logger.info("Получен request на удаление сертификата по номеру: " + certNum);
        certificateService.deleteCertByNum(certNum);
        logger.info("Сертификат успешно удален");
        return "deleted";
    }

    @RequestMapping(value = "/empid/{empID}", method = RequestMethod.DELETE)
    public String deleteAllCertsByEmpID(@PathVariable(name = "empID") int empID) {
        logger.info("Request на удаление всех сертификатов сотрудника с ID: " + empID);
        certificateService.deleteAllCertsByEmpID(empID);
        logger.info("Сертификаты успешно удалены");
        return "deleted";
    }


}
