package ru.technoserv.services;

import ru.technoserv.domain.AuditInfo;
import ru.technoserv.domain.SearchDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AuditService {
    void createRecord(AuditInfo auditInfo);

    List<AuditInfo> getRecordsOfPeriodForDepartment(SearchDate searchDate, Integer depId);

    List<AuditInfo> getRecordsOfPeriodForEmployee(SearchDate searchDate, Integer empId);
}
