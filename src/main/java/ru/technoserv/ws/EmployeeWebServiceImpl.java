package ru.technoserv.ws;

import org.springframework.beans.factory.annotation.Autowired;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.Grade;
import ru.technoserv.domain.Position;
import ru.technoserv.services.EmployeeService;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(
        portName = "EmployeeWebServiceImpl",
        serviceName = "EmployeeWebServiceImplService",
        wsdlLocation = "WEB-INF/wsdl/EmployeeWebServiceImplService.wsdl",
        endpointInterface = "ru.technoserv.ws.EmployeeWebService",
        targetNamespace = "http://ws.technoserv.ru/"
)
public class EmployeeWebServiceImpl implements EmployeeWebService {

    @Autowired
    private EmployeeService employeeService;


    @Override
    public void changePosition(int id,  int positionId) {
        Employee employee = employeeService.getEmployee(id);
        Position newPosition = new Position();
        newPosition.setId(positionId);
        employee.setPosition(newPosition);
        employeeService.changeEmployee(employee);
    }

    @Override
    public void changeGrade(int id, int gradeId) {
        Employee employee = employeeService.getEmployee(id);
        Grade newGrade = new Grade();
        newGrade.setId(gradeId);
        employee.setGrade(newGrade);
        employeeService.changeEmployee(employee);
    }

    @Override
    public void changeSalary(int id, BigDecimal salary) {
        Employee employee = employeeService.getEmployee(id);
        employee.setSalary(salary);
        employeeService.changeEmployee(employee);
    }
}
