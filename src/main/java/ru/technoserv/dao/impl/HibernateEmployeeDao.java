package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.exceptions.DepartmentNotFoundException;
import ru.technoserv.exceptions.EmployeeException;
import ru.technoserv.exceptions.EmployeeNotFoundException;
import ru.technoserv.exceptions.EmployeeTheHeadOfDepartment;


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
    public void create(Employee employee) {
        logger.info("Запрос к базе на создание сотрудника");
        Session session = getSession();
        try{
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new EmployeeException(0);
        }
    }

    @Override
    public Employee read(int empID) {
        logger.info("Запрос к базе на получение сотрудника");
        Employee dbEmployee;
        Session session = getSession();
        try {
            session.beginTransaction();
            dbEmployee = (Employee) session.get(Employee.class, empID);
            session.getTransaction().commit();
        }catch (RuntimeException e) {
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new EmployeeException(empID);
        }
        if(dbEmployee ==null) throw new EmployeeNotFoundException(empID);
        return dbEmployee;
    }

    @Override
    public void delete(int empID) {
        logger.info("Запрос к базе на удаление сотрудника");
        Session session = getSession();
        try{
            session.beginTransaction();
            session.delete( session.get(Employee.class, empID));
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new EmployeeTheHeadOfDepartment(empID);
        }
    }

    @Override
    public List<Employee> getAllFromDept(int deptID) {
        logger.info("Запрос к базе на получение всех сотрудников из отдела");
        Session session = getSession();
        List<Employee> employees;
        try{
            session.beginTransaction();
            employees = session.createQuery("from Employee E where E.department ="+deptID).list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentNotFoundException(deptID);
        }
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("Запрос к базе на изменение сотрудника");
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new EmployeeException(employee.getEmpID());
        }
        return read(employee.getEmpID());
    }

    public List<Employee> getAllEmployees(){
        logger.info("Запрос к базе на получение всех сотрудников");
        Session session = getSession();
        List<Employee> employees;
        try{
            session.beginTransaction();
            employees = session.createQuery("from Employee").list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new EmployeeException(0);
        }
        return employees;
    }


}
