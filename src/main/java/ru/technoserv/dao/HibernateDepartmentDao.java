package ru.technoserv.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.resource.NotSupportedException;
import java.util.List;
@Repository
@Transactional
public class HibernateDepartmentDao implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EmployeeDao employeeDao;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    private static String sqlQueryForSubDepts1 = "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT START WITH PARENT_DEPT_ID = ";

    private static String sqlQueryForSubDepts2 =
            "CONNECT BY  PRIOR  DEPT_ID = PARENT_DEPT_ID";

    @Override
    public int getID() {
        return getDepartmentsList().size();
    }

    @Override
    public void create(Department department) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Department readById(Integer depId) {
        Department department;
        Session session = getSession();
        try{
            session.beginTransaction();
            department = (Department) session.get(Department.class, depId);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
        return department;
    }

    @Override
    public Department updateDept(Department department) {
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
        return readById(department.getId());
    }


    @Override
    public void delete(Integer depId) {
        Department department = readById(depId);
        Session session = getSession();
        try{
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Employee getDeptHead(Integer depId) {
        Department department = readById(depId);
        return employeeDao.read(department.getDeptHeadId());
    }

    @Override
    public List<Department> getDepartmentsList() {
        List departments;
        Session session = getSession();
        try{
            session.beginTransaction();
            departments = session.createQuery("from Department ").list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
        return departments;
    }

    @Override
    public List<Department> getAllSubDepts(Integer depId) {
        List departments;
        Session session = getSession();
        try{
            session.beginTransaction();
            departments = session.createSQLQuery(sqlQueryForSubDepts1+depId+sqlQueryForSubDepts2).list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            session.getTransaction().rollback();
            throw e;
        }
        return departments;
    }

    @Override
    public List<Department> getLevelBelowSubDepts(Integer depId) {
        throw new NotImplementedException();
    }
}
