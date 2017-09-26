package ru.technoserv.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.technoserv.domain.GetEmployeeRequest;
import ru.technoserv.domain.GetEmployeeResponse;
import ru.technoserv.services.EmployeeService;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://tscintern.ru/employees";

    @Autowired
    private EmployeeService employeeService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        response.setEmployee(employeeService.getEmployee(request.getEmpID()));

        return response;
    }
}
