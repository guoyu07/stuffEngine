package ru.technoserv.dao;

import ru.technoserv.domain.AuditInfo;

import java.util.Date;
import java.util.List;

public interface AuditDao {

    void createRecord(AuditInfo auditInfo);

    List<AuditInfo> getRecordsOfPeriodForDepartment(Date fromDate, Date toDate, Integer depId);

    List<AuditInfo> getRecordsOfPeriodForEmployee(Date fromDate, Date toDate, Integer empId);
}
