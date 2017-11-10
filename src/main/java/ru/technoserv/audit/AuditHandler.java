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
import ru.technoserv.domain.AuditInfo;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.services.AuditService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("ArgNamesWarningsInspection")
@Aspect
@Component
public class AuditHandler {

    private AuditService auditService;

    @Autowired
    public AuditHandler(AuditService service){
        auditService = service;
    }

    @Pointcut("within(ru.technoserv.controller.DepartmentController)")
    public void deptController(){
        //pointcut
    }

    @Pointcut("within(ru.technoserv.controller.EmployeeController)")
    public void empController() {
        //pointcut
    }

//    @Pointcut("within(ru.technoserv.controller.CertificateController)")
//    public void certController() {
//        //pointcut
//    }

    @Pointcut("execution(* *.*(..))")
    public void anyMethod() {
        //pointcut
    }

    @Pointcut("execution(* ru.technoserv.controller.EmployeeController.getDepartmentStuff(..))")
    public void getStuff(){
        //pointcut
    }

    @Pointcut("execution(* ru.technoserv.controller.EmployeeController.getPartEmployees(..))")
    public void partEmployees(){
        //pointcut
    }

    @AfterReturning(pointcut = "empController() && anyMethod() && args(empId, request)", returning = "result", argNames = "joinPoint,result,empId,request")
    public void handleAfterMethodWithEmpIdRequestParams(JoinPoint joinPoint, Object result, Integer empId, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, empId, request.getRemoteAddr(), "Id: "+empId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "empController() && anyMethod() && args(empId, request)", throwing = "e")
    public void handleAfterMethodWithEmpIdThrow(JoinPoint joinPoint, Integer empId, HttpServletRequest request, Throwable e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, empId, request.getRemoteAddr(), "Id: "+empId, action, e.getMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "empController() && anyMethod() && args(emp, request)", returning = "result")
    public void handleAfterMethodWithEmpRequestParams(JoinPoint joinPoint, Object result, Employee emp, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        Employee retEmp = (Employee) result;
        AuditInfo auditRecord = new AuditInfo(null, retEmp.getEmpID(), request.getRemoteAddr(), emp.toString(), action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "empController() && anyMethod() && args(emp, request)", throwing= "e")
    public void handleAfterMethodWithEmpThrows(JoinPoint joinPoint, Employee emp, HttpServletRequest request, Throwable e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, emp.getEmpID()==0?null:emp.getEmpID(), request.getRemoteAddr(), emp.toString(), action, e.getMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "empController() && anyMethod() &&  args(request)", returning = "result")
    public void handleAfterMethodAllEmp(JoinPoint joinPoint, Object result,  HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        ResponseEntity<List<Employee>> list = (ResponseEntity<List<Employee>>) result;
        for (Employee e:
             list.getBody()) {
            AuditInfo auditRecord = new AuditInfo(null, e.getEmpID(), request.getRemoteAddr(), "Get all emp", action);
            auditService.createRecord(auditRecord);
        }

    }

    @AfterThrowing(pointcut = "empController() && anyMethod() &&  args(request)", throwing = "e")
    public void handleAfterMethodAllEmpThrow(JoinPoint joinPoint, Throwable e, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());

            AuditInfo auditRecord = new AuditInfo(null,null, request.getRemoteAddr(), "Get all emp", action, e.getMessage());
            auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "empController() && anyMethod() && args(start, num, request) )", returning = "result")
    public void handleAfterMethodPartEmp(JoinPoint joinPoint, Object result, int start, int num, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        ResponseEntity<List<Employee>> list = (ResponseEntity<List<Employee>>) result;
        for (Employee e:
                list.getBody()) {
            AuditInfo auditRecord = new AuditInfo(null, e.getEmpID(), request.getRemoteAddr(), "Start:num - " +start +":"+num, action);
            auditService.createRecord(auditRecord);
        }

    }

    @AfterThrowing(pointcut = "empController() && anyMethod() && args(start, num, request) )", throwing = "e")
    public void handleAfterMethodPartEmpThrow(JoinPoint joinPoint, Throwable e, int start, int num, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());

        AuditInfo auditRecord = new AuditInfo(null,null, request.getRemoteAddr(), "Start:num - " +start +":"+num, action, e.getMessage());
        auditService.createRecord(auditRecord);
    }

//    @AfterReturning(pointcut = "certController() && anyMethod() && args(.., request) )", returning = "result")
//    public void handleAfterMethodintCert(JoinPoint joinPoint, Object result, HttpServletRequest request) {
//        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
//                .getMethod()).getAnnotation(RequestMapping.class).name());
//            AuditInfo auditRecord = new AuditInfo(null, null, request.getRemoteAddr(), "" , action);
//            auditService.createRecord(auditRecord);
//
//    }

    @AfterThrowing(pointcut = "empController() && anyMethod() && args(.., request) )", throwing = "e")
    public void handleAfterMethodIntCertThrow(JoinPoint joinPoint, Throwable e, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, null, request.getRemoteAddr(), "", action, e.getMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "getStuff() " +
            "&& args(depId,..,request)", returning = "result")
    public void handleAfterCreateDepartment(JoinPoint joinPoint,
                                            Integer depId, Object result, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(),"Id: "+depId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "getStuff() && args(depId,.., request)", throwing = "e")
    public void handleAfterMethodWithDepIdRequestParams( JoinPoint joinPoint, Throwable e,HttpServletRequest request,   Integer depId) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), "Id: "+depId, action, e.getMessage()  );
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "deptController() && anyMethod() && args(depId,..,request)", returning = "result")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint, Object result, Integer depId, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), "Id: "+depId, action);
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "deptController() && anyMethod() " +
            "&& args(department, request)", returning = "result")
    public void handleAfterCreateDepartment(JoinPoint joinPoint, Object result,
                                            Department department, HttpServletRequest request) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        Integer depId = ((ResponseEntity<Department>)result).getBody().getId();
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), department.toString(), action);
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "deptController() && anyMethod() && args(depId,..,request)", throwing = "e")
    public void handleAfterMethodWithDepIdRequestParams(JoinPoint joinPoint,  Integer depId, HttpServletRequest request, Throwable e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(depId, null, request.getRemoteAddr(), "Id: "+depId, action, e.getMessage()  );
        auditService.createRecord(auditRecord);
    }

    @AfterThrowing(pointcut = "deptController() && anyMethod() " +
            "&& args(department, request)", throwing = "e")
    public void handleAfterCreateDepartment(JoinPoint joinPoint,
                                            Department department, HttpServletRequest request, Throwable e) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(department.getId(), null, request.getRemoteAddr(), department.toString(), action, e.getMessage());
        auditService.createRecord(auditRecord);
    }

    @AfterReturning(pointcut = "deptController() && args(request,..)", returning = "result")
    public void handleAfterGetAllDeps(JoinPoint joinPoint, HttpServletRequest request, Object result) {
        int action = Integer.parseInt((((MethodSignature)joinPoint.getSignature())
                .getMethod()).getAnnotation(RequestMapping.class).name());
        AuditInfo auditRecord = new AuditInfo(null, null, request.getRemoteAddr(), null, action);
        auditService.createRecord(auditRecord);
    }

}
