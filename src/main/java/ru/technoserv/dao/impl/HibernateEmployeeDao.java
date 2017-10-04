package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;



import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class HibernateEmployeeDao implements EmployeeDao {

    private static final Logger logger = Logger.getLogger(HibernateEmployeeDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Integer create(Employee employee) {
        logger.info("Запрос к базе на создание сотрудника");
        employee.setEmpID(null); //предотвращение создания дубля, если прислали сотрудника с заранее заданным id
        EmployeeHistory employeeHistory = new EmployeeHistory(employee);
        Serializable empID;
        Session session = getSession();
        try{
            empID = session.save(employeeHistory);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }
        return (Integer) empID;
    }

    @Override
    @Cacheable(cacheNames = "employee", key = "#empID")
    public Employee read(int empID) {
        logger.info("Запрос к базе на чтение сотрудника с ID: " + empID);
        Employee employee;
        Session session = getSession();
        try {

            employee = (Employee) session.createSQLQuery("SELECT ID, CHRON_ID, POSITION_ID, GRADE_ID, DEPARTMENT_ID, LAST_NAME, FIRST_NAME, PATR_NAME, GENDER, BIRTHDAY, SALARY FROM EMPLOYEE2 WHERE CHRON_ID = " + empID+" AND IS_ACTIVE=1").addEntity(Employee.class).uniqueResult();
                    //.setBoolean("state", true).uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage());

            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }
        if(employee==null) throw new RuntimeException("5 - запрашиваемый объект не найден");

        return employee;
    }

    @Override
    @CacheEvict(cacheNames = "employee", key = "#empID")
    public void delete(int empID) {
        logger.info("Запрос к базе на удаление сотрудника с ID: " + empID);
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
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }
    }

    @Override
    public List<Employee> getAllFromDept(int deptID) {
        logger.info("Запрос к базе на чтение всех сотрудников отдела c ID: " + deptID);
        List<Employee> empList;
        String sql = "SELECT ID, CHRON_ID, POSITION_ID, GRADE_ID, DEPARTMENT_ID, LAST_NAME, FIRST_NAME, PATR_NAME, GENDER, BIRTHDAY, SALARY FROM EMPLOYEE2 WHERE IS_ACTIVE=1 AND DEPARTMENT_ID="+deptID;

        Session session = getSession();

        try {
            empList = (List<Employee>) session.createSQLQuery(sql).addEntity(Employee.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }

        return empList;
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Запрос к базе на чтение всех сотрудников");
        List<Employee> empList;
        String sql = "SELECT ID, CHRON_ID, POSITION_ID, GRADE_ID, DEPARTMENT_ID, LAST_NAME, FIRST_NAME, PATR_NAME, GENDER, BIRTHDAY, SALARY FROM EMPLOYEE2 WHERE IS_ACTIVE=1";

        Session session = getSession();

        try {
            empList = (List<Employee>) session.createSQLQuery(sql).addEntity(Employee.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }

        return empList;
    }

    @Override
    public List<EmployeeHistory> getEmployeeStory(int empID) {
        logger.info("Запрос к базе на чтение истории изменений сотрудника с ID: " + empID);
        String hql = "from EmployeeHistory E where (E.empID = :empId)";
        List<EmployeeHistory> employeeHistoryList;

        Session session = getSession();
        try {
            employeeHistoryList = (List<EmployeeHistory>) session.createQuery(hql)
                    .setInteger("empId", empID)
                    .list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }
        return employeeHistoryList;
    }

    @Override
    @CacheEvict(cacheNames = "employee", key = "#employee.empID", beforeInvocation = true)
    public Employee updateEmployee(Employee employee) {
        EmployeeHistory employeeHistory = new EmployeeHistory(employee);
        logger.info("Запрос к базе на изменение сотрудника с ID: " + employee.getEmpID());
        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        String hql = "update EmployeeHistory E set E.isActive = false, E.endDate = :currentTimestamp " +
                "where (E.empID = :empId) and (E.isActive = :state)";

        Session session = getSession();
        try {
            session.createQuery(hql)
                    .setTimestamp("currentTimestamp", currentTime)
                    .setInteger("empId", employee.getEmpID())
                    .setBoolean("state", true).executeUpdate();
            employeeHistory.setStartDate(currentTime);
            session.save(employeeHistory);
        } catch (HibernateException e){
            logger.error(e.getMessage());
            throw new RuntimeException("1 - неудачный запрос данных из базы",e);
        }
        return read(employee.getEmpID());
    }

}
