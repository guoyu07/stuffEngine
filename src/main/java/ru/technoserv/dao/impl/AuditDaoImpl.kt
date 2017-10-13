package ru.technoserv.dao.impl

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository
import ru.technoserv.dao.AuditDao
import ru.technoserv.domain.AuditInfo
import java.util.*
import javax.transaction.Transactional

/**
 * Запись аудита  в базу данных
 */
@Repository
@Transactional
open class AuditDaoImpl(private val sessionFactory: SessionFactory) : AuditDao {

    fun getSession() :Session = sessionFactory.currentSession;

    override fun getRecordsOfPeriodForDepartment(fromDate: Date?, toDate: Date?, depId: Int?): MutableList<AuditInfo> {
        return getSession().createQuery("from AuditInfo A where A.deptId =:id and A.requestDate>:since and A.requestDate < :to")
                .setParameter("id", depId)
                .setTimestamp("since", fromDate)
                .setTimestamp("to", toDate)
                .list() as MutableList<AuditInfo>;
    }

    override fun getRecordsOfPeriodForEmployee(fromDate: Date?, toDate: Date?, empId: Int?): MutableList<AuditInfo> {
        return getSession().createQuery("from AuditInfo A where A.empId =:id and A.requestDate>:since and A.requestDate < :to")
                .setParameter("id", empId)
                .setTimestamp("since", fromDate)
                .setTimestamp("to", toDate)
                .list() as MutableList<AuditInfo>;
    }

    override fun createRecord(auditInfo: AuditInfo?) {
        getSession().save(auditInfo);
    }

}