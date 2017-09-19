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
@RequestMapping (value="/certificate",  produces = {"application/json; charset=UTF-8"})
public class CertificateController {

    private static final Logger logger = Logger.getLogger(CertificateController.class);

    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "/{certNum}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> readCertByNum(@PathVariable (name = "certNum") Integer certNum) {
        logger.info("Request на получение сертификата с номером: " + certNum);
        Certificate certificate = certificateService.readCertByNum(certNum);
        String json = GsonUtility.toJson(certificate);
        logger.info("JSON ответ на получение сертификата по номеру: " + json);
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "allByEmp/{empID}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> readAllCertsByEmpID(@PathVariable (name = "empID") Integer empID) {
        logger.info("Request на получение всех сертификатов сотрудника с id: " + empID);
        List<Certificate> allCerts = certificateService.readAllCertsByEmpID(empID);
        String json = GsonUtility.toJson(allCerts);
        logger.info("JSON ответ на получение всех сертификатов сотрудника: " + json);
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

}
