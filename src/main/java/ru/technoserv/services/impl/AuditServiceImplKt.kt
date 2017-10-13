package ru.technoserv.services.impl

import org.springframework.stereotype.Service
import ru.technoserv.dao.AuditDao
import ru.technoserv.domain.AuditInfo
import ru.technoserv.domain.SearchDate
import ru.technoserv.services.AuditService
import java.text.SimpleDateFormat
import java.util.*

/**
 * Сервис для аудита
 */
@Service
class AuditServiceImplKt(private val dao :AuditDao) : AuditService {

    private var formatter : SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd")

    override fun createRecord(auditInfo: AuditInfo?) {
        dao.createRecord(auditInfo)
    }

    override fun getRecordsOfPeriodForDepartment(searchDate: SearchDate, depId: Int?): MutableList<AuditInfo> {
        var from : Date = formatter.parse(searchDate.from)
        var to : Date = formatter.parse(searchDate.to)
        return dao.getRecordsOfPeriodForDepartment(from, to, depId);
    }

    override fun getRecordsOfPeriodForEmployee(searchDate: SearchDate, empId: Int?): MutableList<AuditInfo> {
       var from : Date = formatter.parse(searchDate.from)
        var to : Date = formatter.parse(searchDate.to)
       return dao.getRecordsOfPeriodForEmployee(from, to, empId);
    }
}