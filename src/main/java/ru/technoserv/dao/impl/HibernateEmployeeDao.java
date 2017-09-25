package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.DepartmentNotFoundException;
import ru.technoserv.exceptions.EmployeeException;
import ru.technoserv.exceptions.EmployeeNotFoundException;
import ru.technoserv.exceptions.EmployeeTheHeadOfDepartment;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class HibernateEmployeeDao implements EmployeeDao {

    private static final Logger logger = Logger.getLogger(HibernateEmployeeDao.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Integer create(EmployeeHistory employee) {
        logger.info("Запрос к базе на создание сотрудника");
        Serializable empID;
        Session session = getSession();
        try{
            empID = session.save(employee);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw new EmployeeException(0);
        }

        return (Integer) empID;
    }

    @Override
    @Cacheable(cacheNames = "employee", key = "#empID")
    public EmployeeHistory read(int empID) {
        logger.info("Запрос к базе на получение сотрудника");
        EmployeeHistory empHistory;
        String hql = "from EmployeeHistory E where (E.empID = :empId) and (E.isActive = :state)";

        Session session = getSession();
        try {
            empHistory = (EmployeeHistory) session.createQuery(hql)
                    .setInteger("empId", empID)
                    .setBoolean("state", true).uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage());
            throw new EmployeeException(empID);
        }
        if(empHistory==null) throw new EmployeeNotFoundException(empID);

        return empHistory;
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "employee", key = "#empID", beforeInvocation = true),
            @CacheEvict(cacheNames = "employeeStory", key = "#empID", beforeInvocation = true)})
    public void delete(int empID) {
        logger.info("Запрос к базе на удаление сотрудника");
        String hql =
                "update EmployeeHistory E set E.isActive = false, " +
                        "E.endDate = :currentTimestamp, E.isDeleted = true" +
                        " where (E.empID = :empID) and (E.isActive = :state)";
        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        Session session = getSession();
        try{
            session.createQuery(hql)
                    .setTimestamp("currentTimestamp", currentTime)
                    .setInteger("empID", empID)
                    .setBoolean("state", true)
                    .executeUpdate();
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw new EmployeeException(empID);
        }
    }

    @Override
    //TODO Добавить еще один кэш "deptEmployees"?
    public List<EmployeeHistory> getAllFromDept(int deptID) {
        logger.info("Запрос к базе на получение всех сотрудников из отдела");
        List<EmployeeHistory> empListHistory;
        String hql = "from EmployeeHistory E where (E.isActive = true) and (E.department.id = :depId)";

        Session session = getSession();

        try {
            empListHistory = (List<EmployeeHistory>) session.createQuery(hql)
                    .setInteger("depId", deptID).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new DepartmentNotFoundException(deptID);
        }

        return empListHistory;
    }

    @Override
    @Cacheable(cacheNames = "employeeStory", key = "#empID")
    public List<EmployeeHistory> getEmployeeStory(int empID) {
        logger.info("Запрос к базе на получение истории изменений сотрудника");
        String hql = "from EmployeeHistory E where (E.empID = :empId)";
        List<EmployeeHistory> employeeHistoryList;

        Session session = getSession();
        try {
            employeeHistoryList = (List<EmployeeHistory>) session.createQuery(hql)
                    .setInteger("empId", empID)
                    .list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EmployeeException(0);
        }

        return employeeHistoryList;
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "employee", key = "employee.empID", beforeInvocation = true),
            @CacheEvict(cacheNames = "employeeStory", key = "#employee.empID", beforeInvocation = true)})
    public EmployeeHistory updateEmployee(EmployeeHistory employee) {
        logger.info("Запрос к базе на изменение сотрудника");
        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        String hql = "update EmployeeHistory E set E.isActive = false, E.endDate = :currentTimestamp " +
                "where (E.empID = :empId) and (E.isActive = :state)";

        Session session = getSession();
        try {
            session.createQuery(hql)
                    .setTimestamp("currentTimestamp", currentTime)
                    .setInteger("empId", employee.getEmpID())
                    .setBoolean("state", true).executeUpdate();
            employee.setStartDate(currentTime);
            session.save(employee);
        } catch (HibernateException e){
            logger.error(e.getMessage());
            throw new EmployeeException(employee.getEmpID());
        }

        return read(employee.getEmpID());
    }

}
