package ru.technoserv.services;

import ru.technoserv.domain.AuditInfo;

import java.util.Date;
import java.util.List;

public interface AuditService {
    void createRecord(AuditInfo auditInfo);

    List<AuditInfo> getRecordsOfPeriodForDepartment(Date fromDate, Date toDate, Integer depId);

    List<AuditInfo> getRecordsOfPeriodForEmployee(Date fromDate, Date toDate, Integer empId);
}
