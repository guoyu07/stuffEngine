package ru.technoserv.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.exceptions.DepartmentNotFoundException;
import ru.technoserv.exceptions.EmployeeException;
import ru.technoserv.exceptions.EmployeeNotFoundException;
import ru.technoserv.exceptions.EmployeeTheHeadOfDepartment;


import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class HibernateEmployeeDao implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Employee employee) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw new EmployeeException(0);
        }
    }

    @Override
    public Employee read(int empID) {
        Employee dbEmployee;
        Session session = getSession();
        try {
            session.beginTransaction();
            dbEmployee = (Employee) session.get(Employee.class, empID);
            session.getTransaction().commit();
        }catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new EmployeeException(empID);
        }
        if(dbEmployee ==null) throw new EmployeeNotFoundException(empID);
        return dbEmployee;
    }

    @Override
    public void delete(int empID) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.delete( session.get(Employee.class, empID));
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw new EmployeeTheHeadOfDepartment(empID);
        }
    }

    @Override
    public List<Employee> getAllFromDept(int deptID) {
        Session session = getSession();
        List<Employee> employees;
        try{
            session.beginTransaction();
            employees = session.createQuery("from Employee E where E.department ="+deptID).list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw new DepartmentNotFoundException(deptID);
        }
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw new EmployeeException(employee.getEmpID());
        }
        return read(employee.getEmpID());
    }

    public List<Employee> getAllEmployees(){
        Session session = getSession();
        List<Employee> employees;
        try{
            session.beginTransaction();
            employees = session.createQuery("from Employee").list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw new EmployeeException(0);
        }
        return employees;
    }


}
