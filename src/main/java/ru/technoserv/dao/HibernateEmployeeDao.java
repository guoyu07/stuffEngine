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
    public int getID() {
        return getAllEmployees().size();
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
            throw e;
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
            throw e;
        }
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
            throw e;
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
            throw e;
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
            throw e;
        }
        return read(employee.getEmpID());
    }

    public List<Employee> getAllEmployees(){
        Session session = getSession();
        return session.createQuery("from Employee").list();
    }


}
