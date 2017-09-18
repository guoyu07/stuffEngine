package ru.technoserv.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.technoserv.domain.AuditInfo;
import ru.technoserv.dao.AuditDao;
import ru.technoserv.services.AuditService;

import java.sql.Date;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditDao auditDao;

    @Override
    public void createRecord(AuditInfo auditInfo) {
        auditDao.createRecord(auditInfo);
    }

    @Override
    public List<AuditInfo> getRecordsOfPeriodForDepartment(Date fromDate, Date toDate, Integer depId) {
        return auditDao.getRecordsOfPeriodForDepartment(fromDate, toDate, depId);
    }

    @Override
    public List<AuditInfo> getRecordsOfPeriodForEmployee(Date fromDate, Date toDate, Integer empId) {
        return auditDao.getRecordsOfPeriodForEmployee(fromDate, toDate, empId);
    }
}
