package ru.technoserv.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.MyRuntimeException;
import ru.technoserv.exceptions.StuffExceptions;


import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class HibernateDepartmentDao implements DepartmentDao {

    private static final Logger logger = Logger.getLogger(HibernateEmployeeDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateDepartmentDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }



    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    @Override
    @CacheEvict(cacheNames = "subdepts", allEntries = true, beforeInvocation = true)
    public Integer create(Department department) {
        logger.info("Запрос к базе на создание отдела");
        Serializable id;
        Session session = getSession();
        try{
            id = session.save(department);
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }

        return (Integer) id;
    }

    @Override
    @Cacheable(cacheNames = "department", key = "#depId")
    public Department readById(Integer depId) {
        logger.info("Запрос к базе на получение отдела c ID: " + depId);
        Department department;
        Session session = getSession();
        try{
            department = (Department) session.get(Department.class, depId);
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
        if(department==null) throw new MyRuntimeException(StuffExceptions.NOT_FOUND);

        return department;
    }

    @Override
    @Caching(evict ={@CacheEvict(cacheNames = "subdepts", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "department", key = "#department.id")})
    public Department updateDept(Department department) {
        logger.info("Запрос к базе на изменение отдела c ID: " + department.getId());
        Session session = getSession();
        try{
            session.update(department);
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
        return readById(department.getId());
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "subdepts", allEntries = true, beforeInvocation = true),
            @CacheEvict(cacheNames = "department", key = "#depId", beforeInvocation = true)})
    public void delete(Integer depId) {
        logger.info("Запрос к базе на удаление отдела c ID: " + depId);
        Department department = readById(depId);
        Session session = getSession();
        try{
            session.delete(department);
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }
    }


    @Override
    public EmployeeHistory getDeptHead(Integer depId) {
        logger.info("Запрос к базе на чтение начальника отдела c ID: " + depId);
        return new EmployeeHistory();
    }

    @Override
    public List<Department> getDepartmentsList() {
        logger.info("Запрос к базе на чтение списка всех отделов");
        List<Department> dhList;

        Session session = getSession();
        try{
            Criteria crit = session.
                    createCriteria(Department.class);
            dhList = crit.list();
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }

        return dhList;
    }

    @Override
    @Cacheable(cacheNames = "subdepts", key = "#depId")
    public List<Department> getAllSubDepts(Integer depId) {
        logger.info("Запрос к базе на чтение подразделений отдела с ID: " + depId);
        List<Department> departments;
        Session session = getSession();
        try{
            String sqlQueryForSubDepts1 = "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT START WITH PARENT_DEPT_ID = :deptId CONNECT BY  PRIOR  DEPT_ID = PARENT_DEPT_ID";
            departments = session.createSQLQuery(sqlQueryForSubDepts1)
                    .addEntity(Department.class)
                    .setParameter("deptId", depId)
                    .list();
        }catch (HibernateException e){
            logger.error(e.getStackTrace());
            throw new MyRuntimeException(StuffExceptions.DATABASE_ERROR);
        }

        return departments;
    }

    @Override
    public List<Department> getLevelBelowSubDepts(Integer depId) {
        throw new NotYetImplementedException();
    }
}
