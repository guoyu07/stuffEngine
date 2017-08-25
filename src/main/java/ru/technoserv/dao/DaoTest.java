package ru.technoserv.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DaoConfig.xml");
        EmployeeDao employeeDao =(EmployeeDao) context.getBean("employeeDao");
//        Employee emp = new Employee(2, "Иван", "Иванов");
//        employeeDao.create(emp);
//        emp = new Employee(1, "Петр", "Петров");
//        employeeDao.create(emp);
//        emp = new Employee(3, "Вася", "Васин");
//        employeeDao.create(emp);
//        emp = new Employee(5, "Денис", "Денисов");
//        employeeDao.create(emp);

        Employee emp1 = employeeDao.read(2);
        System.out.println(emp1.toString());

    }
}
