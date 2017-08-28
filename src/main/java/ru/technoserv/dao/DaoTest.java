package ru.technoserv.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DaoTest {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DAOConfig.class);
        ctx.refresh();

        try{
            Class.forName("oracle.jdbc.OracleDriver");
        }catch (Exception e){
            e.printStackTrace();
        }

        //ApplicationContext context = new ClassPathXmlApplicationContext("DaoConfig.xml");
        EmployeeDao employeeDao =(EmployeeDao) ctx.getBean("employeeDao");
        Employee emp = new Employee("Иван", "Иванов");
        employeeDao.create(emp);
        emp = new Employee("Петр", "Петров");
        employeeDao.create(emp);
        emp = new Employee("Вася", "Васин");
        employeeDao.create(emp);
        emp = new Employee("Денис", "Денисов");
        employeeDao.create(emp);
//        Employee emp1 = employeeDao.read(2);
//        System.out.println(emp1.toString());

        Employee emp1 = employeeDao.read("Вася", "Васин");
        System.out.println(emp1.toString());


    }
}
