package ru.technoserv.dao.impl

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

import ru.technoserv.dao.AuditDao
import ru.technoserv.domain.AuditInfo
import java.util.Date
import javax.transaction.Transactional

@Repository
@Transactional
open class AuditDaoImpl(private val sessionFactory: SessionFactory) : AuditDao {



    fun getSession() :Session = sessionFactory.currentSession;

    override fun createRecord(auditInfo: AuditInfo?) {
        getSession().save(auditInfo);
    }

    override fun getRecordsOfPeriodForDepartment(fromDate: Date?, toDate: Date?, depId: Int?): MutableList<AuditInfo> {
        return getSession().createSQLQuery("SELECT * FROM AUDIT_INFO WHERE DEPARTMENT_ID=$depId").addEntity(AuditInfo::class.java).list() as MutableList<AuditInfo>;
    }

    override fun getRecordsOfPeriodForEmployee(fromDate: Date?, toDate: Date?, empId: Int?): MutableList<AuditInfo> {
        return getSession().createSQLQuery("SELECT * FROM AUDIT_INFO WHERE EMPLOYEE_ID=$empId").addEntity(AuditInfo::class.java).list() as MutableList<AuditInfo>;
    }


}