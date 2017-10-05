package ru.technoserv.services.impl

import org.springframework.stereotype.Service
import ru.technoserv.dao.AuditDao
import ru.technoserv.domain.AuditInfo
import ru.technoserv.services.AuditService
import java.util.Date

@Service
class AuditServiceImplKt(private val dao :AuditDao) : AuditService {

    override fun createRecord(auditInfo: AuditInfo?) {
        dao.createRecord(auditInfo)
    }

    override fun getRecordsOfPeriodForDepartment(fromDate: Date?, toDate: Date?, depId: Int?): MutableList<AuditInfo> {
        return dao.getRecordsOfPeriodForDepartment(fromDate, toDate, depId);
    }

    override fun getRecordsOfPeriodForEmployee(fromDate: Date?, toDate: Date?, empId: Int?): MutableList<AuditInfo> {
       return dao.getRecordsOfPeriodForEmployee(fromDate, toDate, empId);
    }
}