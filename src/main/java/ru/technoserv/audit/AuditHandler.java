package ru.technoserv.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.technoserv.controller.GsonUtility;
import ru.technoserv.domain.AuditInfo;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.exceptions.CommonException;
import ru.technoserv.services.AuditService;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuditHandler {

    @Autowired
    private AuditService auditService;

    @Pointcut("within(ru.technoserv.controller.DepartmentController)")
    public void deptController() {
    }

    @Pointcut("within(ru.technoserv.controller.EmployeeController)")
    public void empController() {

    }

    @Pointcut("within(ru.technoserv.controller.*)")
    public void allControllers(){

    }

    @Pointcut("execution(* *.*(..))")
    public void anyMethod() {
    }

    @Pointcut("execution(* ru.technoserv.controller.EmployeeController.getDepartmentStuff(..))")
    public void getStuff(){}

    @AfterReturning(pointcut = "deptController() && anyMethod() && args(depId,..,request)", returning = "result")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint, Object result, Integer depId, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), ""+depId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "deptController() && anyMethod() " +
            "&& args(department, request)", returning = "result")
    public void handleAfterCreateDepartment(JoinPoint joinPoint, Object result,
                                            Department department, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        Integer depId = ((ResponseEntity<Department>)result).getBody().getId();
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), ""+department, action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "deptController() && anyMethod() && args(depId,..,request)", throwing = "e")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint,  Integer depId, HttpServletRequest request, CommonException e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), ""+depId, action, e.getShortMessage()  );
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "deptController() && anyMethod() " +
            "&& args(department, request)", throwing = "e")
    public void handleAfterCreateDepartment(JoinPoint joinPoint,
                                            Department department, HttpServletRequest request, CommonException e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());

        AuditInfo auditRecord = new AuditInfo(department.getId(), null, request.getRemoteAddr(), ""+department, action, e.getShortMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "empController() && anyMethod() && args(empId,..,request)  &&  !getStuff()", returning = "result")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint,  Integer empId,Object result, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, empId, request.getRemoteAddr(), ""+empId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "empController() && anyMethod()  && !getStuff()" +
            "&& args(employee, request)", returning = "result")
    public void handleAfterCreateEmployee(JoinPoint joinPoint,
                                            Employee employee, Object result, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        ResponseEntity<Employee> re = (ResponseEntity<Employee>)result;
        Integer empId = re.getBody().getEmpID();
        AuditInfo auditRecord = new AuditInfo(null,empId , request.getRemoteAddr(), employee.toString(), action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "empController() && anyMethod() && args(empId,..,request)  && !getStuff()", throwing = "e")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint, CommonException e,  Integer empId, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        //TO DO
        AuditInfo auditRecord = new AuditInfo(null, empId, request.getRemoteAddr(), ""+empId, action, e.getShortMessage()  );
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "empController() && anyMethod()  && !getStuff() " +
            "&& args(employee, request)", throwing = "e")
    public void handleAfterCreateDepartment(JoinPoint joinPoint,
                                            Employee employee, HttpServletRequest request, CommonException e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());

        AuditInfo auditRecord = new AuditInfo(null, employee.getEmpID(), request.getRemoteAddr(), employee.toString(), action, e.getShortMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "getStuff() " +
            "&& args(depId,..,request)", returning = "result")
    public void handleAfterCreateDepartment(JoinPoint joinPoint,
                                            Integer depId, Object result, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(),""+depId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "getStuff() && args(depId,.., request)", throwing = "e")
    public void handleAfterMethodWithDepIdRequestParams( JoinPoint joinPoint, CommonException e,HttpServletRequest request,   Integer depId) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());

        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), ""+depId, action, e.getShortMessage()  );
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "allControllers() && args(request,..)", throwing = "e")
    public void handleAfterUnknownError( JoinPoint joinPoint, CommonException e,HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        //TO DO
        AuditInfo auditRecord = new AuditInfo(null, null, request.getRemoteAddr(), "unknown", action, e.getShortMessage()  );
        auditService.createRecord(auditRecord);
    }

}