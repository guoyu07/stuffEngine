package ru.technoserv.audit;

import org.aspectj.lang.annotation.Aspect;

/**
 * Класс следящий за действиями запроса пользователя, собирает информацию в {@link VisitStory}
 */
@Aspect
public class Visitor {

   /* @Pointcut("execution(* ru.technoserv.services.EmployeeService.createEmployee(Employee)) && args(employee)")
    public void addUser(Employee employee){}

    private VisitStory visitStory = new VisitStory();


    @After("addUser(employee)")
    public void recordVisit(Employee employee){
        DataTimeService date = new CurrentTime();
        visitStory.setVisitor("Default");
        visitStory.setServerActions("addEmployee : " + employee.toString());
        System.out.println("User was added "+employee.toString() +" time "+ date.getCurrentTime());
    }*/
}
