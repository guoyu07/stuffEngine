package ru.technoserv.dao.impl;

/*
@Repository
@Transactional
public class HibernateAuditDao implements AuditDao {


    private static final Logger logger = Logger.getLogger(HibernateEmployeeDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createRecord(AuditInfo auditInfo) {
        getSession().save(auditInfo);
    }

    @Override
    public List<AuditInfo> getRecordsOfPeriodForDepartment(Date fromDate, Date toDate, Integer depId) {

        return (List<AuditInfo>)getSession().createSQLQuery("from AUDIT_INFO where REQUEST_DATE>"+fromDate+" and REQUEST_DATE<"+toDate+" and DEPARTMENT_ID="+depId).list();
    }

    @Override
    public List<AuditInfo> getRecordsOfPeriodForEmployee(Date fromDate, Date toDate, Integer empId) {

        return null;
    }
}
*/