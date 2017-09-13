package ru.technoserv.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.exceptions.DepartmentException;
import ru.technoserv.exceptions.DepartmentNotEmpty;
import ru.technoserv.exceptions.DepartmentNotFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.resource.NotSupportedException;
import java.util.List;
@Repository
@Transactional
public class HibernateDepartmentDao implements DepartmentDao {

    private static final Logger logger = Logger.getLogger(HibernateEmployeeDao.class);

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
    public void create(Department department) {
        logger.info("Запрос к базе на создание отдела");
        Session session = getSession();
        try{
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentException(0);
        }
    }

    @Override
    public Department readById(Integer depId) {
        logger.info("Запрос к базе на получение отдела");
        Department department;
        Session session = getSession();
        try{
            session.beginTransaction();
            department = (Department) session.get(Department.class, depId);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentException(depId);
        }
        if(department==null) throw new DepartmentNotFoundException(depId);
        return department;
    }

    @Override
    public Department updateDept(Department department) {
        logger.info("Запрос к базе на изменение отдела");
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentException(department.getId());
        }
        return readById(department.getId());
    }


    @Override
    public void delete(Integer depId) {
        logger.info("Запрос к базе на удаление отдела");
        Department department = readById(depId);
        Session session = getSession();
        try{
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentNotEmpty(depId);
        }
    }

    @Override
    public Employee getDeptHead(Integer depId) {
        logger.info("Запрос к базе на получение начальника отдела");
        Department department = readById(depId);
        return employeeDao.read(department.getDeptHeadId());
    }

    @Override
    public List<Department> getDepartmentsList() {
        logger.info("Запрос к базе на получение списка отделов отдела");
        List departments;
        Session session = getSession();
        try{
            session.beginTransaction();
            departments = session.createQuery("from Department ").list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentException(0);
        }
        return departments;
    }

    @Override
    public List<Department> getAllSubDepts(Integer depId) {
        logger.info("Запрос к базе на получение подотделов");
        List departments;
        Session session = getSession();
        try{
            session.beginTransaction();
            departments = session.createSQLQuery(sqlQueryForSubDepts1+depId+sqlQueryForSubDepts2).addEntity(Department.class).list();
            session.getTransaction().commit();
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            throw new DepartmentException(0);
        }
        return departments;
    }

    @Override
    public List<Department> getLevelBelowSubDepts(Integer depId) {
        throw new NotImplementedException();
    }
}
