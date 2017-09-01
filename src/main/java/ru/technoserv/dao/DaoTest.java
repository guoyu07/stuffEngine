package ru.technoserv.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;


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

//        //ApplicationContext context = new ClassPathXmlApplicationContext("DaoConfig.xml");
         EmployeeDao employeeDao =(EmployeeDao) ctx.getBean("employeeDao");
//        employeeDao.updateDept(100, "LOLOLO");
//        employeeDao.updatePosition(100, "Гуру");
//        employeeDao.updateGrade(100, "C");
//        employeeDao.updateSalary(100, new BigDecimal(5656));


        Employee testEmp = new Employee();
        testEmp.setEmpID(101);
        testEmp.setPosition("Продавец");
        testEmp.setGrade("B");
        testEmp.setDepartment("Dept13");
        testEmp.setFirstName("Trevor");
        testEmp.setLastName("Silly");
        testEmp.setPatrName("FOOL");
        testEmp.setGender('W');
        Date d = new Date(80000000);
        testEmp.setBirthday(d);
        testEmp.setSalary(new BigDecimal("7777"));
        System.out.println(testEmp);
        employeeDao.create(testEmp);
    }
}
