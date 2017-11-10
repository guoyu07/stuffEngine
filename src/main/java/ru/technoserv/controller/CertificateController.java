package ru.technoserv.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.domain.Certificate;
import ru.technoserv.domain.HeaderEntity;
import ru.technoserv.services.CertificateService;
import ru.technoserv.services.impl.DocumentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/certificate", produces = {"application/json; charset=UTF-8"})
public class CertificateController {

    private static final Logger logger = Logger.getLogger(CertificateController.class);

    private CertificateService certificateService;



    @Autowired
    public  CertificateController(CertificateService certificateService){
        this.certificateService = certificateService;
    }

    @Autowired
    private DocumentService documentService;



    @RequestMapping(name ="17", value = "", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"} )
    public ResponseEntity<?> createCertificate(@RequestBody Certificate certificate, HttpServletRequest request) {
        logger.info("Получен request на создание сертификата");
        logger.info("Cоздаваемый сертификат: " + certificate.toString());
        Certificate certificate1 = certificateService.create(certificate);
        return new ResponseEntity<>(certificate1, HttpStatus.CREATED);
    }

    @RequestMapping(name ="18",value = "/certnum/{certNum}", method = RequestMethod.GET)
    public ResponseEntity<?> readCertByNum(@PathVariable(name = "certNum") int certNum,  HttpServletRequest request) {
        logger.info("Получен request на чтение сертификата с номером: " + certNum);
        Certificate certificate = certificateService.readCertByNum(certNum);
        logger.info("Возвращаемый сертификат: " + certificate.toString());
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @RequestMapping(name ="19",value = "/empid/{empID}", method = RequestMethod.GET)
    public ResponseEntity<?> readAllCertsByEmpID(@PathVariable(name = "empID") int empID,  HttpServletRequest request) {
        logger.info("Получен request на чтение всех сертификатов сотрудника с ID: " + empID);
        List<Certificate> allCerts = certificateService.readAllCertsByEmpID(empID);
        logger.info("Возвращаемые сертификаты сотрудника: " + allCerts);
        for (Certificate cert : allCerts) logger.info(cert.toString());
        return new ResponseEntity<>(allCerts, HttpStatus.OK);
    }

    @RequestMapping(name ="20",value = "/certnum/{certNum}", method = RequestMethod.DELETE)
    public String deleteCertByNum(@PathVariable(name = "certNum") int certNum,  HttpServletRequest request) {
        logger.info("Получен request на удаление сертификата по номеру: " + certNum);
        certificateService.deleteCertByNum(certNum);
        logger.info("Сертификат успешно удален");
        return "deleted";
    }

    @RequestMapping(name ="21",value = "/empid/{empID}", method = RequestMethod.DELETE)
    public String deleteAllCertsByEmpID(@PathVariable(name = "empID") int empID) {
        logger.info("Request на удаление всех сертификатов сотрудника с ID: " + empID);
        certificateService.deleteAllCertsByEmpID(empID);
        logger.info("Сертификаты успешно удалены");
        return "deleted";
    }

    @RequestMapping(name = "22", value = "header/{headerId}", method = RequestMethod.GET)
    public ResponseEntity<?> getHeaderById(@PathVariable(name = "headerId") int headerId, HttpServletRequest request) {
        logger.info("Получен request на чтение header'а с id = " + headerId);
        HeaderEntity header = documentService.getHeaderById(headerId);
        return new ResponseEntity<Object>(header, HttpStatus.OK);
    }

    @RequestMapping(name = "23", value = "headers/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientHeaders(@PathVariable(name = "clientId") int clientId, HttpServletRequest request) {
        logger.info("Получен request на чтение header'ов клиента с id = " + clientId);
        List<HeaderEntity> clientHeaders = documentService.getClientHeaders(clientId);
        return new ResponseEntity<Object>(clientHeaders, HttpStatus.OK);
    }
}
